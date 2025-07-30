
package view;

import dao.AnswerDAO;
import model.Answer;
import model.Question;
import model.User;

import javax.swing.*;
import java.sql.SQLException;

// Tela para responder perguntas
public class AnswerFormView extends JFrame {
    public AnswerFormView(User user, Question question) {
        setTitle("Answer Question");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel questionLabel = new JLabel("Question: " + question.getTitle());
        questionLabel.setBounds(10, 10, 360, 25);
        add(questionLabel);

        JTextArea bodyArea = new JTextArea();
        bodyArea.setBounds(10, 40, 360, 150);
        add(bodyArea);

        JButton submitButton = new JButton("Submit Answer");
        submitButton.setBounds(240, 200, 130, 30);
        add(submitButton);

        submitButton.addActionListener(e -> {
            Answer answer = new Answer();
            answer.setQuestionId(question.getId());
            answer.setUserId(user.getId());
            answer.setBody(bodyArea.getText());

            try {
                new AnswerDAO().create(answer);
                JOptionPane.showMessageDialog(this, "Answer submitted.");
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
