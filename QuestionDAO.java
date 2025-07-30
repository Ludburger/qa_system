
package dao;

import model.Question;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Gerencia operações CRUD para perguntas
public class QuestionDAO {
    // Insere nova pergunta
    public void create(Question question) throws SQLException {
        String sql = "INSERT INTO questions (user_id, title, body) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, question.getUserId());
            stmt.setString(2, question.getTitle());
            stmt.setString(3, question.getBody());
            stmt.executeUpdate();
        }
    }

    // Lista todas as perguntas
    public List<Question> readAll() throws SQLException {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setUserId(rs.getInt("user_id"));
                q.setTitle(rs.getString("title"));
                q.setBody(rs.getString("body"));
                q.setCreatedAt(rs.getString("created_at"));
                list.add(q);
            }
        }
        
        return list;
    }
       public List<Question> readByUserId(int userId) throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setUserId(rs.getInt("user_id"));
                q.setTitle(rs.getString("title"));
                q.setBody(rs.getString("body"));
                q.setCreatedAt(rs.getString("created_at"));
                questions.add(q);
            }
        }
        return questions;
    }
        public void delete(int questionId) throws SQLException {
        String sql = "DELETE FROM questions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionId);
            stmt.executeUpdate();
        }
    }
}
