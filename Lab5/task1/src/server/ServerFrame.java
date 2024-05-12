package server;

import interfaces.Executable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ServerFrame extends JFrame {
    private JLabel label;
    private JTextArea textArea;
    private JButton startButton;
    private JButton stopButton;
    private JButton exitButton;
    private JTextField textField;
    private ServerSocket serverSocket;
    private boolean running = false;

    public ServerFrame() {
        setTitle("TCP Server");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel portPanel = new JPanel(new FlowLayout());
        textField = new JTextField(10);
        label = new JLabel("Working Port:");
        portPanel.add(label);
        portPanel.add(textField);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(portPanel, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        startButton = new JButton("Start Server");
        stopButton = new JButton("Stop Server");
        exitButton = new JButton("Exit Server");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(exitButton);

        add(topPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopServer();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopServer();
                System.exit(0);
            }
        });
    }
    private void startServer() {
        try {
            int port = Integer.parseInt(textField.getText());
            serverSocket = new ServerSocket(port);
            textArea.append("The server is waiting for connections...\n");
            running = true;
            stopButton.setEnabled(true);
            startButton.setEnabled(false);
            new Thread(this::waitForClientConnections).start();
        } catch (IOException e) {
            textArea.append("Error starting server: " + e.getMessage()+"\n");
        } catch (NumberFormatException e) {
            textArea.append("Invalid port\n");
        }
    }
    private void HandleRequest(Socket clientSocket, int connectionNumber) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
            Executable ex = (Executable) in.readObject();
            double startTime = System.nanoTime();
            Object output = ex.execute();
            double endTime = System.nanoTime();
            double completionTime = endTime - startTime;
            ResultImpl result = new ResultImpl(output, completionTime);
            out.writeObject(result);
            textArea.append("\tConnection " + connectionNumber + " result sent.");
        } catch (IOException | ClassNotFoundException e) {
            textArea.append("\nError handling client " + connectionNumber + ": " + e.getMessage());
        } finally {
            clientSocket.close();
            textArea.append(" Finish connection ...\n");
        }
    }
    private void waitForClientConnections() {
        int count = 0;

        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                count++;
                final int currentCount = count;
                new Thread(() -> {
                    try {
                        textArea.append("\tConnection " + currentCount + " starting execution...\n");
                        HandleRequest(clientSocket, currentCount);
                        textArea.append("\tConnection " + currentCount + " [WORK DONE]\n");
                    } catch (IOException | ClassNotFoundException e) {
                        textArea.append("Error handling connection " + currentCount + ": " + e.getMessage());
                    }
                }).start();
            } catch (IOException e) {
                if (!running) {
                    return;
                } else {
                    textArea.append("Error accepting clients: " + e.getMessage());
                }
            }
        }
    }
    private void stopServer() {
        try {
            running = false;
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            textArea.append("The server stops working...\n");
        } catch (IOException e) {
            textArea.append("Error stopping server: " + e.getMessage()+ "\n");
        } finally {
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ServerFrame frame = new ServerFrame();
                frame.setVisible(true);
            }
        });
    }
}