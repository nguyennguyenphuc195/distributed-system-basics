package com.me;

import com.me.common.process.Process;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String ip     = "192.168.100.2";
        int port      = Integer.parseInt(args[0]);
        int otherPort = Integer.parseInt(args[1]);
        int starting  = Integer.parseInt(args[2]);

        int []neighbors = { otherPort };
        Process process = new Process(ip, port, starting, neighbors);
        process.startListen();
        process.commandLine();
    }
}
