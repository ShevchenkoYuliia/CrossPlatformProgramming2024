package bulletinBoardService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class test extends JFrame{
    private JLabel portLabel, addressLabel, nameLabel;
    private JTextArea textArea;
    private JTextField textFieldMsg, addressField, portField, nameField;
    private JButton sendButton, finishButton, clearButton, disconnectButton, connectButton;
    private Messanger messenger = null;
    private InetAddress address = null;
    private int port;
    private String name;

    public test() {
        setTitle("Chat");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        JPanel messagePanel = new JPanel(new FlowLayout());
        textFieldMsg = new JTextField(40);
        messagePanel.add(textFieldMsg);
        sendButton = new JButton("Send");
        messagePanel.add(sendButton);

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(150, 300));


        addressLabel = new JLabel("Group");
        infoPanel.add(addressLabel);
        addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(80, 25));
        infoPanel.add(addressLabel);
        portLabel = new JLabel("Port   ");
        portField = new JTextField();
        portField.setPreferredSize(new Dimension(80, 25));
        infoPanel.add(addressLabel);
        nameLabel = new JLabel("Name");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(80, 25));


        infoPanel.add(addressLabel);
        infoPanel.add(addressField);
        infoPanel.add(portLabel);
        infoPanel.add(portField);
        infoPanel.add(nameLabel);
        infoPanel.add(nameField);

        JPanel topPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        connectButton = new JButton("Connect");
        disconnectButton = new JButton("Disconnect");
        clearButton = new JButton("Clear");
        finishButton = new JButton("Finish");
        buttonPanel.add(disconnectButton);
        buttonPanel.add(connectButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(finishButton);

        add(messagePanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    address = InetAddress.getByName(addressField.getText());
                    port = Integer.parseInt(portField.getText());
                    name = nameField.getText();
                    UITasks ui = (UITasks) Proxy.newProxyInstance(getClass().getClassLoader(),
                            new Class[]{UITasks.class}, new EDTInvocationHandler(new UITaskImpl()));
                    messenger = new MessangerImpl(address, port, name, ui);
                    messenger.start();
                    connectButton.setEnabled(false);
                    disconnectButton.setEnabled(true);
                    addressField.setEnabled(false);
                    portField.setEnabled(false);
                    nameField.setEnabled(false);
                } catch (UnknownHostException unknownHostException) {
                    unknownHostException.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Unknown host: " + unknownHostException.getMessage());
                } catch (IllegalArgumentException illegalArgumentException) {
                    JOptionPane.showMessageDialog(null, "Invalid address: " + illegalArgumentException.getMessage());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error creating multicast socket: " + ioException.getMessage());
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (messenger!=null) {
                    messenger.send();
                }
            }
        });
        disconnectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (messenger!=null) {
                    messenger.stop();
                }
                connectButton.setEnabled(true);
                disconnectButton.setEnabled(false);
                addressField.setEnabled(true);
                portField.setEnabled(true);
                nameField.setEnabled(true);
            }
        });
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textFieldMsg.setText("");
            }
        });
        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (messenger!=null) {
                    messenger.stop();
                }
                System.exit(0);
            }
        });

        setPreferredSize(new Dimension(612, 400));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {

        new test();
        new test();
        new test();
        new test();

    }

    private class UITaskImpl implements UITasks{
        public String getMessage() {
            String res = textFieldMsg.getText();
            textFieldMsg.setText("");
            return res;
        }
        public void setText(String text) {
            textArea.append(text + "\n");
        }
    }
}