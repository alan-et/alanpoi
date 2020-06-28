package com.alanpoi.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

/**
 * NetworkUtil
 * Created by Janon on 2014/10/30.
 */
public class NetworkUtil {
    private static final Logger LOG = LoggerFactory.getLogger(NetworkUtil.class);
    private static String localIP = null;
    private static String localIPMark = null;

    private static String netIP = null;
    private static String netIPMark = null;

    private static void init() {
        localIP = null;
        localIPMark = null;
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();

                    if (!ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                        if (ip.isSiteLocalAddress()) {// 内网IP
                            String _localIP = ip.getHostAddress();
                            if (localIP == null && !_localIP.endsWith(".1")) {
                                localIP = _localIP;
                                byte[] ipAddress = ip.getAddress();
                                if (ipAddress.length == 4) {
                                    localIPMark = (ipAddress[2] & 0xff) + "." + (ipAddress[3] & 0xff);
                                } else {
                                    localIPMark = localIP;
                                }
                            }
                            LOG.info("IP:" + _localIP);
                        } else {// 外网IP
                            String netip = ip.getHostAddress();
                            if(netIP == null && !netip.endsWith(".1")){
                                netIP = netip;
                                byte[] ipAddress = ip.getAddress();
                                if (ipAddress.length == 4) {
                                    netIPMark = (ipAddress[2] & 0xff) + "." + (ipAddress[3] & 0xff);
                                } else {
                                    netIPMark = netIP;
                                }
                            }

                            LOG.info("IP:" + netip);
                        }
                    }
                }
            }
        } catch (Throwable ignore) {
        }
    }

    public static String getLocalIP() {
        if (localIP == null && netIP == null) init();
        return localIP != null ? localIP : netIP;
    }

    public static String getLocalIPMark() {
        if (localIP == null && netIP == null) init();

        return localIPMark != null ? localIPMark : netIPMark;
    }

    /**
     * 获取ip地址
     */
    public static final String getIpAddr(final HttpServletRequest request) throws Exception {
        if (request == null) {
            throw (new Exception("======================request为null======================"));
        }

        //x-forwarded-for是一个 Squid 开发的字段，只有在通过了HTTP代理或者负载均衡服务器时才会添加该项。当客户端请求被转发，
        //格式为X-Forwarded-For:client1,proxy1,proxy2，一般情况下，第一个ip为客户端真实ip，
        //IP将会追加在其后并以逗号隔开，后面的为经过的代理服务器ip。现在大部分的代理都会加上这个请求头。
        String ipString = request.getHeader("x-forwarded-for");
        //用apache http做代理时一般会加上Proxy-Client-IP请求头
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        //WL-Proxy-Client-IP是他的weblogic插件加上的头。
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        //HTTP_CLIENT_IP ：有些代理服务器会加上此请求头。
        if (ipString == null || ipString.length() == 0 || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("HTTP_CLIENT_IP");
            System.out.println("HTTP_CLIENT_IP ipString: " + ipString);
        }
        if (ipString == null || ipString.length() == 0 || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("HTTP_X_FORWARDED_FOR");
            System.out.println("HTTP_X_FORWARDED_FOR ipString: " + ipString);
        }
        //nginx代理一般会加上此请求头。
        if (ipString == null || ipString.length() == 0 || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("X-Real-IP");
            System.out.println("X-Real-IP ipString: " + ipString);
        }
        //当不是上述（代理）方式访问时，request.getRemoteAddr()直接获取客户真实ip
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr(); //客户端未经过代理，直接访问服务器端(nginx,squid,haproxy)；
        }
        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }
        return ipString;
    }

    /**
     * 获取外网ip
     * @throws Exception
     */
    public static String getOuterNetIp(final HttpServletRequest request) throws Exception {
        String ipAddr = getIpAddr(request);//获取ip地址
        boolean internalIp = internalIp(ipAddr);//判断ip是否内网ip
        if(!internalIp){//外网地址直接返回
            return ipAddr;
        }
        String result = "";
        URLConnection connection;
        BufferedReader in = null;
        try {
            URL url = new URL("http://www.icanhazip.com");
            connection = url.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "KeepAlive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
        return result;
    }
    /**
     * 判断是否内网ip
     */
    public static boolean internalIp(String ip) {
        if ("127.0.0.1".equalsIgnoreCase(ip))
            return true;
        if ("0:0:0:0:0:0:0:1".equals(ip))
            return true;
        byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
        return internalIp(addr);
    }

    /**
     * 判断解析后的ip是否内网
     */
    public static boolean internalIp(byte[] addr) {
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 127.0.0.1/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;
        }
    }
}
