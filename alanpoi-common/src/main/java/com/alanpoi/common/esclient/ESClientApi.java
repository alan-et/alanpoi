package com.alanpoi.common.esclient;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * elasticsearch API接口
 * <p>
 * 正确的result格式:
 * <p>
 * POST /{index}/_doc/{_id}
 * {
 * "took": 8,
 * "timed_out": false,
 * "_shards": {
 * "total": 4,
 * "successful": 4,
 * "skipped": 0,
 * "failed": 0
 * },
 * "hits": {
 * "total": {
 * "value": 3,
 * "relation": "eq"
 * },
 * "max_score": 0.2876821,
 * "hits": [
 * {
 * "_index": "im_group_1",
 * "_type": "_doc",
 * "_id": "Kmx8dm0BW_IThWbqocCc",
 * "_score": 0.2876821,
 * "_source": {
 * "id": "111111",
 * "name": "IM项目",
 * "createTime": 1569553679000,
 * "modifyTime": 1569554679000,
 * "createor": {
 * "companyId": "123-123",
 * "userId": "user-124"
 * }
 * }
 * }
 * ]
 * }
 * }
 * <p>
 * DELETE/{index}/_doc/{_id}
 * {
 * "_index": "im_group_1",
 * "_type": "_doc",
 * "_id": "1",
 * "_version": 2,
 * "result": "not_found",
 * "_shards": {
 * "total": 2,
 * "successful": 2,
 * "failed": 0
 * },
 * "_seq_no": 9,
 * "_primary_term": 1
 * }
 * 错误的格式
 * {
 * "status": 400,
 * "error": "error message"
 * }
 */

/**
 * ES Client APi
 *
 * @author pengzhuoxun
 * @since 1.3.4
 */
public class ESClientApi {
    private static final Logger log = LoggerFactory.getLogger(ESClientApi.class);

    private RestClient restClient;

    public ESClientApi(RestClient restClient) {
        this.restClient = restClient;
    }

    /**
     * 新增文档
     *
     * @param index
     * @param _id
     * @param doc
     * @return
     */
    public JSONObject insertDoc(String index, String _id, JSONObject doc) {
        if (null == index || index.equals("")) return null;
        if (null == doc) return null;

        String url = "/" + index + "/_doc/";
        if (!(null == _id || _id.equals(""))) {
            url = url + _id;
        }

        Request req = new Request("POST", url);
        req.setJsonEntity(doc.toJSONString());
        try {
            Response res = restClient.performRequest(req);
            JSONObject jsonRes = null;
            try {
                jsonRes = checkResponse(res);
            } catch (Exception e) {
                log.error("insert doc error. index:{}, id:{}, {}", index, _id, e.getMessage());
                return null;
            }
            return jsonRes;
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    /**
     * 修改文档
     *
     * @param index
     * @param _id
     * @param doc
     * @return
     */
    public JSONObject updateDoc(String index, String _id, JSONObject doc) {
        if (null == index || "".equals(index)) return null;
        if (null == doc) return null;
        String url = "/" + index + "/_doc/" + _id + "/_update";
        Request req = new Request("POST", url);
        req.setJsonEntity(doc.toJSONString());
        try {
            Response res = restClient.performRequest(req);
            JSONObject jsonRes = null;
            try {
                jsonRes = checkResponse(res);
            } catch (Exception e) {
                log.error("updateDoc error. index:{}, id:{}, {}", index, _id, e.getMessage());
                return null;
            }
            return jsonRes;
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    /**
     * 删除指定文档
     *
     * @param index
     * @param _id
     * @return
     */
    public JSONObject deleteDoc(String index, String _id) {
        if (null == index || index.equals("")) return null;
        if (null == _id || _id.equals("")) return null;

        String url = "/" + index + "/_doc/";
        if (!(null == _id || _id.equals(""))) {
            url = url + _id;
        }

        Request req = new Request("DELETE", url);
        try {
            Response res = restClient.performRequest(req);
            JSONObject jsonRes = null;
            try {
                jsonRes = checkResponse(res);
            } catch (Exception e) {
                log.error("delete doc error. index:{}, id:{}, {}", index, _id, e.getMessage());
                return null;
            }
            return jsonRes;
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    public JSONObject deleteDoc(String index, JSONObject query) {
        if (StringUtils.isEmpty(index) || null == query) return null;

        String url = "/" + index + "/_delete_by_query";
        Request req = new Request("POST", url);
        req.setJsonEntity(query.toJSONString());
        try {
            Response res = restClient.performRequest(req);
            JSONObject jsonRes = null;
            try {
                jsonRes = checkResponse(res);
            } catch (Exception e) {
                log.error("delete doc error. index:{}, query:'{}', {}", index, query.toJSONString(), e.getMessage());
                return null;
            }
            return jsonRes;
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    /**
     * 搜索文档
     *
     * @return
     */
    public JSONArray searchDoc(String index, JSONObject query) {
        if (StringUtils.isEmpty(index) || null == query) return null;

        String url = "/" + index + "/_search";
        Request req = new Request("POST", url);
        req.setJsonEntity(query.toJSONString());
        try {
            Response res = restClient.performRequest(req);
            JSONObject jsonRes = null;
            try {
                jsonRes = checkResponse(res);
            } catch (Exception e) {
                log.error("search doc error. index:{}, query:'{}', {}", index, query.toJSONString(), e.getMessage());
                return null;
            }
            return jsonRes.getJSONObject("hits").getJSONArray("hits");
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    public JSONObject checkResponse(Response res) throws Exception {
        int status = res.getStatusLine().getStatusCode();
        if (status >= 400) {
            throw new Exception("http error. status:" + status);
        }
        String jsonStr = EntityUtils.toString(res.getEntity());
        JSONObject jsonRes = JSON.parseObject(jsonStr);
        status = jsonRes.getIntValue("status");
        if (0 != status) {
            throw new Exception("elasticsearch rest api error:'" + jsonStr + "'");
        }
        return jsonRes;
    }

    /**
     * 批处理
     *
     * @return
     */
    public JSONObject bulk(BatchRequest request) throws IOException {
        Response res = performRequest(request);
        try {
            return checkResponse(res);
        } catch (Exception e) {
            log.error("bulk error. {}", e);
            return null;
        }
    }

    private Response performRequest(BatchRequest batchRequest) throws IOException {
        Request req = batchRequest.transfer();
        return restClient.performRequest(req);
    }


}
