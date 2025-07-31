
package dao;

import model.Answer;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Gerencia operações CRUD para respostas
public class AnswerDAO {
    // Insere nova resposta
    public void create(Answer answer) throws SQLException {
        String sql = "INSERT INTO answers (question_id, user_id, body) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, answer.getQuestionId());
            stmt.setInt(2, answer.getUserId());
            stmt.setString(3, answer.getBody());
            stmt.executeUpdate();
        }
    }

    // Lista respostas de uma pergunta específica
    public List<Answer> findByQuestionId(int questionId) throws SQLException {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT * FROM answers WHERE question_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Answer a = new Answer();
                a.setId(rs.getInt("id"));
                a.setQuestionId(rs.getInt("question_id"));
                a.setUserId(rs.getInt("user_id"));
                a.setBody(rs.getString("body"));
                a.setCreatedAt(rs.getString("created_at"));
                answers.add(a);
            }
        }
        return answers;
    }
}
