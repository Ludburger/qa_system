
package model;

// Representa uma pergunta feita por um usuário
public class Question {
    private int id;         // ID da pergunta
    private int userId;     // Autor (user_id)
    private String title;   // Título
    private String body;    // Conteúdo
    private String createdAt;// Data de criação

    // Getters e setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

}