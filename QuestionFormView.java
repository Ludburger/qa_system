
package view;

import dao.QuestionDAO;
import model.Question;
import model.User;

import javax.swing.*;
import java.sql.SQLException;

// Tela para criar perguntas
public class QuestionFormView extends JFrame {
    public QuestionFormView(User user) {
        setTitle("Create Question");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title label and field
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 20, 80, 25);
        add(titleLabel);
        JTextField titleField = new JTextField();
        titleField.setBounds(100, 20, 260, 25);
        add(titleField);

        // Body label and text area
        JLabel bodyLabel = new JLabel("Body:");
        bodyLabel.setBounds(10, 60, 80, 25);
        add(bodyLabel);
        JTextArea bodyArea = new JTextArea();
        bodyArea.setBounds(100, 60, 260, 120);
        add(bodyArea);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(270, 200, 90, 30);
        add(submitButton);

        submitButton.addActionListener(e -> {
            Question question = new Question();
            question.setUserId(user.getId());
            question.setTitle(titleField.getText());
            question.setBody(bodyArea.getText());

            QuestionDAO dao = new QuestionDAO();
            try {
                dao.create(question);
                JOptionPane.showMessageDialog(this, "Question posted!");
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
