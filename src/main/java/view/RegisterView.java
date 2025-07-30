package view;

import dao.UserDAO;
import model.User;
import javax.swing.JComboBox;

import javax.swing.*;
import java.sql.SQLException;

// Tela de Cadastro
public class RegisterView extends JFrame {

    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterView() {
        setTitle("User Registration");
        setSize(300, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(100, 20, 160, 25);
        add(nameField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 60, 80, 25);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(100, 60, 160, 25);
        add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 100, 80, 25);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 100, 160, 25);
        add(passwordField);

        //Role
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(10, 140, 80, 25);
        add(roleLabel);
        
        String[] roles = {"user", "admin"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        roleCombo.setBounds(100, 140, 160, 25);
        add(roleCombo);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setBounds(100, 180, 160, 25);
        add(registerButton);
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleCombo.getSelectedItem();

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            UserDAO dao = new UserDAO();
            try {
                dao.create(user);
                JOptionPane.showMessageDialog(null, "User registered successfully!");
                dispose();
                new LoginView();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error registering user: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
