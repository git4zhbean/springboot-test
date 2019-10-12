package com.dxy.zhbean.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/19
 */
public class NetWorkUtils {
    // 内网IP
    public static final String INTRANET_IP = getIntranetIp();
    // 外网IP
    public static final String INTERNET_IP = getInternetIp();
    //外网ip字节数组
    public static final byte[] INTERNET_IP_BYTES = getInternetIpBytes();
    // 内网IP字节数组
    public static final byte[] INTRANET_IP_BYTES = getIntranetIpBytes();

    private NetWorkUtils(){}

    /**
     * 获得内网IP
     * @return 内网IP
     */
    private static String getIntranetIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     * @return 外网IP
     */
    private static String getInternetIp(){
        try{
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements())
            {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements())
                {
                    ip = addrs.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INTRANET_IP))
                    {
                        return ip.getHostAddress();
                    }
                }
            }

            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     * @return 外网IP
     */
    private static byte[] getInternetIpBytes(){
        return ipv4Address2BinaryArray(getInternetIp());
    }

    /**
     * 获取内网ip
     * @return
     */
    private static byte[] getIntranetIpBytes() {
        return ipv4Address2BinaryArray(getIntranetIp());
    }

    /**
     * 将给定的用十进制分段格式表示的ipv4地址字符串转换成字节数组
     * @param ipAdd
     * @return
     */
    private static byte[] ipv4Address2BinaryArray(String ipAdd){
        byte[] binIP = new byte[4];
        String[] strs = ipAdd.split("\\.");
        for(int i=0;i<strs.length;i++){
            binIP[i] = (byte) Integer.parseInt(strs[i]);
        }
        return binIP;
    }

    /** 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址   */
    public static String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    //判断ip是否可以连通（相当于ping ip）
    public static boolean isIPValid(String ip) throws IOException {
        InetAddress address = InetAddress.getByName(ip);
        return address.isReachable(2000);
    }

    //判断服务是否可用
    public static boolean isServerValid(String url) throws IOException {
        URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setConnectTimeout(2000);
        return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public static void main(String[] args){
        System.out.println(INTRANET_IP);
        System.out.println(INTERNET_IP);
    }
}
