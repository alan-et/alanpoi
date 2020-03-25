package com.alanpoi.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
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
}
