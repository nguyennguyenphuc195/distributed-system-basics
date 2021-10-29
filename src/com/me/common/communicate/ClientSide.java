package com.me.common.communicate;

import com.me.common.message.SimpleMessage;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientSide {
    private int port;
    private String host;

    public ClientSide(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public SimpleMessage send(int id, int timestamp, String content) throws IOException {
        SimpleMessage msg = new SimpleMessage(id, timestamp, "REQUEST", content);
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream is = new ObjectInputStream(socket.getInputStream())) {
            os.writeObject(msg);

            SimpleMessage ack = (SimpleMessage) is.readObject();
            return ack;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
