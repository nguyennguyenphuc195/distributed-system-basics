package com.me.common.process;

import com.me.common.communicate.ClientSide;
import com.me.common.communicate.ServerSide;
import com.me.common.message.SimpleMessage;
import com.me.common.state.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Process {
    private ServerSide server;
    private State state;
    private String ip;
    private int port;
    private int[] neighborPorts;
    private Thread listenThread;

    public Process(String ip, int port, int startTimestamp, int[] neighborPorts) {
        this.state = new State(port, startTimestamp);
        this.ip     = ip;
        this.port   = port;
        this.server = new ServerSide(port, state);
        this.neighborPorts = neighborPorts;

        this.listenThread = new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void startListen() {
        listenThread.start();
    }

    public void commandLine() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        //read

        do {
            str = br.readLine().trim();
            if (str.startsWith("send")) {
                ClientSide client = new ClientSide(neighborPorts[0], ip);
                String msg = str.split(":")[1];

                state.setTimestamp(state.getTimestamp() + 1);
                SimpleMessage ack = client.send(port, state.getTimestamp(), msg);
                if (ack != null) {
                    state.setTimestamp(Math.max(state.getTimestamp(), ack.getTimestamp()) + 1);
                }
                System.out.println(ack);
            } else if (str.startsWith("query")) {
                System.out.println(state);
            } else if (str.startsWith("action")) {
                state.setTimestamp(state.getTimestamp() + 1);
            }
        } while (!str.equals("stop"));

        listenThread.interrupt();
        System.exit(0);
    }
}
