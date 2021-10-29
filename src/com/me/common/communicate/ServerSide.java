package com.me.common.communicate;

import com.me.common.message.SimpleMessage;
import com.me.common.state.State;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerSide {
    private int port;
    private State state;
    public ServerSide(int port, State state) {
        this.port = port;
        this.state = state;
    }

    public void start() throws IOException {
        try (ServerSocket socket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = socket.accept();
                try (ObjectInputStream is  = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    SimpleMessage msg = (SimpleMessage) is.readObject();
                    System.out.println(msg);
                    state.setTimestamp(Math.max(state.getTimestamp(), msg.getTimestamp()) + 1);

                    SimpleMessage reply = new SimpleMessage(port, state.getTimestamp(), "ACK", "");
                    os.writeObject(reply);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                clientSocket.close();
            }
        }
    }
}
