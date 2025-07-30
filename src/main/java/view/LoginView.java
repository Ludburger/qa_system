package view;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

// Tela de Login
public class LoginView extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginView() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 20, 90, 25);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(100, 20, 160, 25);
        add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 60, 90, 25);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 60, 160, 25);
        add(passwordField);

        // Buttons
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 75, 25);
        add(loginButton);
        registerButton = new JButton("Register");
        registerButton.setBounds(185, 100, 90, 25);
        add(registerButton);

        // Login action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                UserDAO dao = new UserDAO();
                try {
                    List<User> users = dao.readAll();
                    for (User user : users) {
                        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                            JOptionPane.showMessageDialog(null, "Welcome, " + user.getName());
                            dispose();

                            // abre a próxima tela conforme o papel
                            if ("admin".equals(user.getRole())) {
                                // admins veem a lista completa de perguntas e podem responder
                                new QuestionListView(user);
                            } else {
                                // usuários padrão veem o painel de criação + botão de listar
                                new UserView(user);
                            }

                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Register action
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterView();
        });

        setVisible(true);
    }
}