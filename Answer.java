
package model;

// Representa uma resposta a uma pergunta
public class Answer {
    private int id;          // ID da resposta
    private int questionId;  // Pergunta respondida
    private int userId;      // Autor da resposta
    private String body;     // Conteúdo
    private String createdAt;// Data de criação

    // Getters e setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
