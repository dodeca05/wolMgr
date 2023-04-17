package com.pro.WOLmgr.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Service
public class WakeOnLanService {

    //Todo: db에 컴퓨터 정보가 담긴 entity 작성해서 해당 entity를 parsing 해서 packet을 보내자

    public void sendMagicPacket(String macAddress) throws IOException{//broadcast
        sendMagicPacket("192.168.0.255",macAddress);
    }
    public void sendMagicPacket(String targetIP, String macAddress) throws IOException {
        byte[] macBytes = getMacBytes(macAddress);

        // WOL 패킷 생성
        byte[] wolPacket = new byte[6 + 16 * macBytes.length];
        for (int i = 0; i < 6; i++) {
            wolPacket[i] = (byte) 0xff;
        }
        for (int i = 6; i < wolPacket.length; i += macBytes.length) {
            System.arraycopy(macBytes, 0, wolPacket, i, macBytes.length);
        }

        // UDP 패킷 생성
        InetAddress address = InetAddress.getByName(targetIP);
        int port = 9; // WOL 포트 번호
        DatagramPacket packet = new DatagramPacket(wolPacket, wolPacket.length, address, port);

        // 소켓 생성 및 WOL 패킷 전송
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        socket.close();


    }
    private static byte[] getMacBytes(String macAddress) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macAddress.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("잘못된 MAC 주소입니다.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 MAC 주소입니다.", e);
        }
        return bytes;
    }

}
