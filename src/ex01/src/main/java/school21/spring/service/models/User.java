package school21.spring.service.models;

import java.util.Objects;

public class User {
    private Long id;
    private String Email;

    public User() {
    }

    public User(Long id, String email) {
        this.id = id;
        Email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(Email, user.Email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Email='" + Email + '\'' +
                '}';
    }
}
