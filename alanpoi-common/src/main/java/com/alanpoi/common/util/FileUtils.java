package com.alanpoi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtils {

    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static final int BYTESIZE = 1024;                                        //每次读取的大小 1KB

    /**
     * 将文件流创建临时文件；
     *
     * @param is     待转化的文件流
     * @param prefix 临时文件名前缀
     * @param suffix 后缀名
     * @return
     * @throws IOException
     */
    public static File saveTempFile(InputStream is, String prefix, String suffix, String dir) throws IOException {
        File file = new File(dir);
        if (!file.exists()) file.mkdir();
        File temp = File.createTempFile(prefix, suffix, file);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(new FileOutputStream(temp));  //把文件流转为文件，保存在临时目录
            int len = 0;
            byte[] buf = new byte[10 * BYTESIZE]; //缓冲区
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            try {
                if (bos != null) bos.close();
                if (bis != null) bis.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return temp;
    }

    /**
     * 删除临时文件
     *
     * @param file
     */
    public static void deleteTempFile(File file) {
        if (file == null) return;
        file.delete();
    }

    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1;
    }
}
