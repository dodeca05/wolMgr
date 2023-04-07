package com.pro.WOLmgr.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class HealthCheckService {

    public void healthCheck() {
        healthCheck("175.123.128.90");
    }

    public void healthCheck(String ip) {
        InetAddress inetAddress = null;

        try {
            inetAddress= InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            assert inetAddress != null;
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
