package com.alanpoi.etactivity.transfer;

import com.alanpoi.etactivity.protocol.ByteBufCache;
import com.alanpoi.etactivity.protocol.EtActivityEntity;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传输层通道
 *
 * @author pengzhuoxun
 * @since 2020-4-3
 */
//@Component
public class SocketChannel {
    protected static final Logger logger = LoggerFactory.getLogger(SocketChannel.class);
    private int PORT;
    public static ServerSocket serverSocket;
    private final static String SERVER_PORT_PROPERTY = "et-activity.server.port";
    private final static int DEFAULT_PORT = 6088;

    @Autowired
    private Environment environment;

    @Autowired
    private ExecutorTools executorTools;

    @PostConstruct
    public void init() throws IOException {
//        Environment environment = ApplicationUtil.getBean(Environment.class);
        if (environment != null) {
            String etPort = environment.getProperty(SERVER_PORT_PROPERTY);
            PORT = StringUtils.isEmpty(etPort) ? DEFAULT_PORT : Integer.valueOf(etPort);
        }
        serverSocket = new ServerSocket(PORT);
        logger.info("et-activity server started, port:{}", PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            executorTools.getExecutor().execute(new SocketThread(socket));
        }
    }

    public void open() {

    }

    public void collect() {

    }

    public void close() {

    }

    class SocketThread implements Runnable {
        private Socket socket;

        public SocketThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream is = null;
            OutputStream os = null;
            try {
                is = socket.getInputStream();
                os = socket.getOutputStream();
                EtActivityEntity etActivityEntity = new EtActivityEntity();
                ETActivityDecode<EtActivityEntity> etActivityDecode = new ETActivityDecode<EtActivityEntity>();
                etActivityDecode.decode(is, etActivityEntity);
                logger.info("call {}.{}", etActivityEntity.getClassName(), etActivityEntity.getMethodName());
                Object result = etActivityEntity.getClass().getMethod(etActivityEntity.getMethodName(), etActivityEntity.getParameterTypes()).invoke(etActivityEntity.getClass(), etActivityEntity.getParam());
                String resStr = JSON.toJSONString(result);
                os.write(resStr.getBytes("UTF-8"));
            } catch (Exception e) {
                logger.error("", e);
            } finally {
                try {
                    if (is != null) is.close();
                    if (os != null) os.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }
}
