import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConferenceClientGUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField organizationField;
    private ConferenceRegistration server;

    public ConferenceClientGUI() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (ConferenceRegistration) registry.lookup("ConferenceRegistration");
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Conference Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 10, 80, 25);
        frame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 10, 160, 25);
        frame.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 40, 80, 25);
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 40, 160, 25);
        frame.add(emailField);

        JLabel organizationLabel = new JLabel("Organization:");
        organizationLabel.setBounds(10, 70, 80, 25);
        frame.add(organizationLabel);

        organizationField = new JTextField();
        organizationField.setBounds(100, 70, 160, 25);
        frame.add(organizationField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 110, 120, 25);
        frame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    String organization = organizationField.getText();
                    Participant participant = new Participant(name, email, organization);
                    server.registerParticipant(participant);
                    JOptionPane.showMessageDialog(frame, "Registration successful!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error registering participant!");
                }
            }
        });

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new ConferenceClientGUI();
    }
}
