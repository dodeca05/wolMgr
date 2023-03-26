package com.pro.WOLmgr;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WolMgrHealthCheck {
    public static void main(String[] args) {
        InetAddress inetAddress = null;
        String ip = "175.123.128.90";

        try {
            inetAddress= InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            if (inetAddress.isReachable(2000)) {
                System.out.println("Success");
            } else {
                System.out.println("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
