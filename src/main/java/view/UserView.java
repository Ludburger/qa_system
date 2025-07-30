package view;

import dao.QuestionDAO;
import model.Question;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Painel para usuários padrão:
 * - Formulário de criação de pergunta
 * - Botão para listar perguntas e respostas existentes
 */
public class UserView extends JFrame {
    public UserView(User user) {
        setTitle("User Dashboard");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //  FORMULÁRIO 

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Create a new question"));
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        // Label "Title"
        c.gridx = 0; c.gridy = 0;
        formPanel.add(new JLabel("Title:"), c);

        // Campo de título com largura fixa (200px)
        JTextField titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(200, 25));
        c.gridx = 1; c.gridy = 0;
        formPanel.add(titleField, c);

        // Label "Body"
        c.gridx = 0; c.gridy = 1;
        formPanel.add(new JLabel("Body:"), c);

        // Área de corpo (mais larga)
        JTextArea bodyArea = new JTextArea(8, 40);
        formPanel.add(new JScrollPane(bodyArea), 
                      new GridBagConstraints(1,1,2,1,1,1,GridBagConstraints.NORTHWEST,
                                             GridBagConstraints.BOTH, new Insets(5,5,5,5),0,0));

        // Botão de submissão
        JButton submitBtn = new JButton("Submit Question");
        c.gridx = 1; c.gridy = 2;
        formPanel.add(submitBtn, c);

        //  BOTÃO DE LISTAGEM 
        JButton viewBtn = new JButton("View Questions and Answers");

        // MONTAGEM FINAL

        add(formPanel, BorderLayout.CENTER);
        JPanel bottom = new JPanel();
        bottom.add(viewBtn);
        add(bottom, BorderLayout.SOUTH);

        // AÇÕES

        submitBtn.addActionListener(e -> {
            Question q = new Question();
            q.setUserId(user.getId());
            q.setTitle(titleField.getText());
            q.setBody(bodyArea.getText());
            try {
                new QuestionDAO().create(q);
                JOptionPane.showMessageDialog(this, "Question posted!");
                titleField.setText("");
                bodyArea.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        viewBtn.addActionListener(e -> new QuestionListView(user));

        setVisible(true);
    }
}