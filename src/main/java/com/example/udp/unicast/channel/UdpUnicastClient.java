package com.example.udp.unicast.channel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

public class UdpUnicastClient implements Runnable {
    private final int port;

    public UdpUnicastClient(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try(DatagramChannel channel = DatagramChannel.open()) {
            channel.socket().bind(new InetSocketAddress(port));

            while(true) {
                ByteBuffer buf = ByteBuffer.allocate(100);
                buf.clear();
                channel.receive(buf);
                //buf.flip();
                String s = new String(buf.array(), buf.arrayOffset(), buf.position());

                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
