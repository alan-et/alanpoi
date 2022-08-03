package com.alanpoi.elasticsearch.client;


import com.alanpoi.common.util.StringUtils;
import com.alanpoi.elasticsearch.ESOpType;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NByteArrayEntity;
import org.elasticsearch.client.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ES Batch request
 *
 * @author pengzhuoxun
 * @since 1.3.4
 */
public class BatchRequest {

    private String bulk_uri = "/_bulk";
    private List<String> entityList;
    private String _index;
    private String method = "POST";
    private static final String DEFAULT_TYPE = "_doc";


    public static BatchRequest build() {
        return new BatchRequest();
    }

    public static BatchRequest build(String _index) {
        return new BatchRequest(_index);
    }

    public static BatchRequest build(String _index, String method) {
        return new BatchRequest(_index, method);
    }

    public BatchRequest(String _index) {
        this._index = _index;
        this.bulk_uri = "/" + _index + bulk_uri;
        this.entityList = new ArrayList<>();
    }

    public BatchRequest(String _index, String method) {
        this._index = _index;
        this.method = method;
        this.bulk_uri = "/" + _index + bulk_uri;
        this.entityList = new ArrayList<>();
    }

    public BatchRequest() {
    }


    /**
     * 添加批处理数据
     *
     * @param _id
     * @param opType
     * @param entity
     * @return
     */
    public BatchRequest add(String _id, ESOpType opType, String entity) {
        return add(_index, _id, opType, entity);
    }

    public BatchRequest add(String _index, String _id, ESOpType opType, String entity) {
        return add(_index, DEFAULT_TYPE, _id, opType, entity);
    }

    public BatchRequest add(String _index, String _type, String _id, ESOpType opType, String entity) {
        if ((opType == ESOpType.delete || opType == ESOpType.update) && StringUtils.isBlank(_id)) {
            throw new IllegalArgumentException("deletion or update cannot be missing `_id`");
        }
        if (StringUtils.isBlank(_index)) _index = this._index;
        if (StringUtils.isBlank(_type)) _type = DEFAULT_TYPE;
        entityList.add(buildIndex(_index, _type, opType, _id));
        if (opType == ESOpType.create || opType == ESOpType.index || opType == ESOpType.update) {
            entityList.add(entity + "\n");
        }
        return this;
    }

    private String buildIndex(String _index, String _type, ESOpType opType, String _id) {
        boolean bool_id = StringUtils.isNotBlank(_id);
        boolean bool_index = StringUtils.isNotBlank(_index);
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("\"").append(opType.name()).append("\":{");
        if (bool_id && bool_index) {
            stringBuilder.append("\"_index\":\"").append(_index).append("\"");
            stringBuilder.append(",\"_type\":\"").append(_type).append("\"");
            stringBuilder.append(",\"_id\":\"").append(_id).append("\"");
        } else if (bool_id) {
            stringBuilder.append("\"_id\":\"").append(_id).append("\"");
            stringBuilder.append(",\"_type\":\"").append(_type).append("\"");
        } else if (bool_index) {
            stringBuilder.append("\"_index\":\"").append(_index).append("\"");
            stringBuilder.append(",\"_type\":\"").append(_type).append("\"");
        }
        stringBuilder.append("}");
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    public Request transfer() throws IOException {
        Request request = new Request(this.method, this.bulk_uri);
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : entityList) {
            stringBuilder.append(str);
        }
        byte[] dataByte = stringBuilder.toString().getBytes("utf-8");
        NByteArrayEntity byteArrayEntity = new NByteArrayEntity(dataByte, 0, dataByte.length, ContentType.APPLICATION_JSON);
        request.setEntity(byteArrayEntity);
        return request;
    }
}
