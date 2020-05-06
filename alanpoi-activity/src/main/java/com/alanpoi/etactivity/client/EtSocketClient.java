package com.alanpoi.etactivity.client;

import com.alanpoi.common.util.ApplicationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;

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

    public String send(byte[] data) {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {
            socket = createClient();
            if (socket == null) return null;
            outputStream = socket.getOutputStream();
            socket.getOutputStream().write(data);
            //后续只能接受数据
            socket.shutdownOutput();

            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }

            return sb.toString();
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
        return null;
    }
}
