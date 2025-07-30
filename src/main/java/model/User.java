
package model;

// Representa um usuário no sistema
public class User {
    private int id;         // Identificador único
    private String name;    // Nome completo
    private String email;   // Email de login
    private String password;// Senha (texto puro por simplicidade)
    private String role;    // Papel: 'user' ou 'admin'
    private String createdAt;// Data de registro

    // Getters e setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
