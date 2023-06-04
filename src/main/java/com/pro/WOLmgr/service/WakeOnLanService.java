package com.pro.WOLmgr.service;

import com.pro.WOLmgr.entity.DeviceEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Service
public class WakeOnLanService {

    public void sendMagicPacket(DeviceEntity deviceEntity) throws IOException{//broadcast
        sendMagicPacketDirect("192.168.0.255",deviceEntity.getMacAddress());
        //Todo: 처음에는 ip target을 정해서 보내고 추후 health check로 실패 하면 broadcast로 packet을 다시 보냅니다.
    }

    public void sendMagicPacketBroadCast(String macAddress) throws IOException{//broadcast
        sendMagicPacketDirect("192.168.0.255",macAddress);
    }
    public void sendMagicPacketDirect(String targetIP, String macAddress) throws IOException {
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

        if (hex.length != 6)
            throw new IllegalArgumentException("Invalid MAC address");

        try {
            for (int i = 0; i < 6; i++)
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid MAC address", e);
        }
        return bytes;
    }

}
