package view;

import dao.AnswerDAO;
import dao.QuestionDAO;
import model.Answer;
import model.Question;
import model.User;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class QuestionListView extends JFrame {

    public QuestionListView(User user) {
        setTitle("Questions List");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        try {
            QuestionDAO qDao = new QuestionDAO();
            AnswerDAO aDao = new AnswerDAO();

            List<Question> questions = user.getRole().equals("admin")
                    ? qDao.readAll()
                    : qDao.readByUserId(user.getId());

            for (Question q : questions) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setBorder(BorderFactory.createTitledBorder(q.getTitle()));

                JTextArea body = new JTextArea(q.getBody());
                body.setEditable(false);
                panel.add(body);

                // BOTÃO PARA VER RESPOSTAS
                JButton viewAnswersBtn = new JButton("View Answers");
                panel.add(viewAnswersBtn);
                viewAnswersBtn.addActionListener(e -> {
                    try {
                        List<Answer> answers = aDao.findByQuestionId(q.getId());
                        StringBuilder msg = new StringBuilder("Answers:\n");
                        for (Answer a : answers) {
                            msg.append("- ").append(a.getBody()).append("\n");
                        }
                        JOptionPane.showMessageDialog(this, msg.toString());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });

                // SOMENTE ADMIN PODE RESPONDER
                if ("admin".equals(user.getRole())) {
                    JButton answerBtn = new JButton("Answer");
                    panel.add(answerBtn);
                    answerBtn.addActionListener(e -> new AnswerFormView(user, q));
                }

                // DELETE em perguntas RESPONDIDAS
                if (!aDao.findByQuestionId(q.getId()).isEmpty()) {
                    JButton deleteBtn = new JButton("Delete Question");
                    panel.add(deleteBtn);
                    deleteBtn.addActionListener(e -> {
                        try {
                            int ok = JOptionPane.showConfirmDialog(
                                    this,
                                    "Delete this answered question?",
                                    "Confirm",
                                    JOptionPane.YES_NO_OPTION
                            );
                            if (ok == JOptionPane.YES_OPTION) {
                                qDao.delete(q.getId());
                                dispose();
                                new QuestionListView(user); // refresh
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                        }
                    });
                }

                add(panel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
}