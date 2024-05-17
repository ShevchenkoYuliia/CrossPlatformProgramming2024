package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SenderThread extends Thread {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    private volatile boolean stopped = false;

    public SenderThread(DatagramSocket socket, InetAddress address , int port){
        this.socket = socket;
        this.address = address;
        this.port = port;
        this.socket.connect(address, port);
    }
    public void run() {
        try {
            BufferedReader userInput = new BufferedReader(
                    new InputStreamReader(System.in));
            while (true) {
                if (stopped){
                    return;
                }
                String theLine = userInput.readLine();
                if (theLine.equals("."))
                    break;
                byte[] data = theLine.getBytes("UTF-8");
                DatagramPacket output = new DatagramPacket(data, data.length, address,
                        port);
                socket.send(output);
                Thread.yield();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
    public void halt() {
        this.stopped = true;
    }
}
