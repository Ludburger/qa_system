package view;

import dao.AnswerDAO;
import dao.QuestionDAO;
import model.Answer;
import model.Question;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class QuestionListView extends JFrame {

    public QuestionListView(User user) {
        setTitle("Questions List");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Painel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Scroll
        JScrollPane scrollPane = new JScrollPane(mainPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        // rolagem
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // scrollpane
        add(scrollPane);

        try {
            QuestionDAO qDao = new QuestionDAO();
            AnswerDAO aDao = new AnswerDAO();

            List<Question> questions = user.getRole().equals("admin")
                    ? qDao.readAll()
                    : qDao.readByUserId(user.getId());

            for (Question q : questions) {
                
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                panel.setBorder(BorderFactory.createTitledBorder(q.getTitle()));

                // scroll texto
                JTextArea body = new JTextArea(q.getBody());
                body.setEditable(false);
                body.setLineWrap(true);
                body.setWrapStyleWord(true);
                JScrollPane bodyScroll = new JScrollPane(body);
                bodyScroll.setPreferredSize(new Dimension(550, 80));
                panel.add(bodyScroll);

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

                if ("admin".equals(user.getRole())) {
                    JButton answerBtn = new JButton("Answer");
                    panel.add(answerBtn);
                    answerBtn.addActionListener(e -> new AnswerFormView(user, q));
                }

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

                // Adicionar cada painel de pergunta ao mainPanel
                mainPanel.add(panel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
}
