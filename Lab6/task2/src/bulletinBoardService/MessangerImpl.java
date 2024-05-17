package bulletinBoardService;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class MessangerImpl implements Messanger {

    private UITasks ui = null;
    private MulticastSocket group = null;
    private InetAddress addr = null;
    private int port;
    private String name;
    private boolean canceled = false;

    public MessangerImpl(InetAddress addr, int port, String name, UITasks ui) throws IOException {
        this.name = name;
        this.ui = ui;
        this.addr = addr;
        this.port = port;
        if (!addr.isMulticastAddress()) {
            throw new IllegalArgumentException("Not a multicast address");
        }
        group = new MulticastSocket(port);
        group.setTimeToLive(2);
        group.joinGroup(addr);
    }

    public void start() {
        Thread t = new Receiver();
        t.start();
    }

    public void stop() {
        cancel();
        try {
            if (!group.isClosed()) {
                group.leaveGroup(addr);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Помилка від'єднання\n" + e.getMessage());
        } finally {
            group.close();
        }
    }

    public void send() {
        new Sender().start();
    }

    private class Sender extends Thread {
        public void run() {
            try {
                String msg = name + ": " + ui.getMessage();
                byte[] out = msg.getBytes();
                DatagramPacket pkt = new DatagramPacket(out, out.length, addr, port);
                group.send(pkt);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Помилка відправлення\n" + e.getMessage());
            }
        }
    }

    private class Receiver extends Thread {
        public void run() {
            try {
                byte[] in = new byte[512];
                DatagramPacket pkt = new DatagramPacket(in, in.length);
                while (!isCanceled()) {
                    group.receive(pkt);
                    ui.setText(new String(pkt.getData(), 0, pkt.getLength()));
                }
            } catch (Exception e) {
                if (isCanceled()) {
                    JOptionPane.showMessageDialog(null, "З'єднання завершено");
                } else {
                    JOptionPane.showMessageDialog(null, "Помилка прийому\n" + e.getMessage());
                }
            }
        }
    }

    private synchronized boolean isCanceled() {
        return canceled;
    }

    public synchronized void cancel() {
        canceled = true;
    }
}
