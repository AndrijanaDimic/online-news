package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.invoke.StringConcatFactory;

public class User {
    private int id;
    private String name;
    private String email;
    private String lastName;
    private String username;
    private int status;
    private String role;
    private String hashedPassword;

    public User() {
    }

    public User(int id, String name,String lastName, String hashedPassword, String username, int status, String role, String email) {
     this.name = name;
     this.lastName = lastName;
     this.username = username;
     this.hashedPassword=hashedPassword;
     this.role = role;
     this.status = status;
     this.email = email;
     this.id= id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public int getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", role='" + role + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}
