package com.alanpoi.etactivity.client;

import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.common.util.AObject;
import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.etactivity.protocol.ByteBufCache;
import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * et-activity socket client
 *
 * @author pengzhuoxun
 * @since 1.3.0
 */
@Component
public class EtSocketClient {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final static String DEFAULT_HOST = "127.0.0.1";
    private final static int DEFAULT_PORT = 6088;

    @PostConstruct
    public void init() throws IOException {
//        socket=createClient();
//        socket.setKeepAlive(true);
        ByteBufCache.init();
    }

    public Socket createClient() throws IOException {
        Environment environment = ApplicationUtil.getBean(Environment.class);
        String url = environment.getProperty("activity.server.address");
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;
        if (StringUtils.isEmpty(url)) {
            logger.warn("Environment property \"${activity.server.address}\" not exist");
        } else {
            try {
                // 要连接的服务端IP地址和端口
                host = url.split(":")[0];
                port = Integer.valueOf(url.split(":")[1]);
            } catch (Exception e) {
                logger.warn("Environment property \"${activity.server.address}\" configuration error");
            }
        }
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        logger.info(String.format("connect address %s:%s success", host, port));
        return socket;
    }

    public Object send(byte[] data) throws ClassNotFoundException {
        AObject aObject = new AObject();
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {
            socket = createClient();
            if (socket == null) return null;
            outputStream = socket.getOutputStream();
            outputStream.write(data);
            //后续只能接受数据
            socket.shutdownOutput();

            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((len = inputStream.read(bytes)) != -1) {
                output.write(bytes, 0, len);
            }
            return decode(bytes);
        } catch (UnknownHostException e) {
            logger.error("", e);
        } catch (IOException e1) {
            logger.error("", e1);
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return aObject.toString();
    }

    private Object decode(byte[] bytes) throws UnsupportedEncodingException, IOException, ClassNotFoundException {
        ByteBuf buf = ByteBufCache.getByteBuf();
        buf.writeBytes(bytes);

        short code = buf.readShort();
        int version = buf.readInt();
        int length = buf.readInt();
        int erLength = buf.readInt();
        byte[] body = new byte[length];
        if (length > 0) {
            buf.readBytes(body);
        }
        if (erLength > 0) {
            byte[] erMsg = new byte[erLength];
            buf.readBytes(erMsg);
            exception(code, new String(erMsg, "UTF-8"));
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }

    private void exception(short code, String msg) {
        throw new AlanPoiException(code, msg);
    }
}
