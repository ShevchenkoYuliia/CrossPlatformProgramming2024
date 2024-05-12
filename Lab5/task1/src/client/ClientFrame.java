package client;

import interfaces.Result;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class ClientFrame extends JFrame {
    private JLabel port, address, number;
    private JTextArea textArea;
    private JButton calcButton;
    private JButton clearButton;
    private JButton exitButton;
    private JTextField addressField;
    private JTextField portField;
    private JTextField numberField;

    public ClientFrame() {
        setTitle("TCP Server");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel portPanel = new JPanel(new FlowLayout());
        addressField = new JTextField("localhost",10);
        portField = new JTextField(10);
        numberField = new JTextField(10);
        address = new JLabel("IP Address:");
        port = new JLabel("Port:");
        number = new JLabel("N:");
        portPanel.add(address);
        portPanel.add(addressField);
        portPanel.add(port);
        portPanel.add(portField);
        portPanel.add(number);
        portPanel.add(numberField);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(portPanel, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        calcButton = new JButton("Calculate");
        clearButton = new JButton("Clear Result");
        exitButton = new JButton("Exit Program");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(calcButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        add(topPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                numberField.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void calculate() {
        String ipAddress = addressField.getText();
        int port;
        int num;
        try {
            port = Integer.parseInt(portField.getText());
        } catch (NumberFormatException e) {
            textArea.append("Invalid port\n");
            return;
        }
        try {
            num = Integer.parseInt(numberField.getText());
        } catch (NumberFormatException e) {
            textArea.append("Invalid number\n");
            return;
        }
        try (Socket clientSocket = new Socket(ipAddress, port);
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            textArea.append("Connected to server\n");
            JobOne job = new JobOne(num);
            out.writeObject(job);
            textArea.append("Submitted a job for execution\n");
            Result result = (Result) in.readObject();
            textArea.append("result = " + result.output() + ", time taken = " + result.scoreTime() + "ns\n");
        } catch (IOException | ClassNotFoundException e) {
            if (e.getMessage().contains("Connection refused")) {
                textArea.append("Server is not available\n");
            } else {
                textArea.append("Error: " + e.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClientFrame frame = new ClientFrame();
                frame.setVisible(true);
            }
        });
    }
}