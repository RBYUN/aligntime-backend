package io.aligntime.users.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "users",
        schema = "auth",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_users_email", columnNames = "email")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 25)
    private String first_name;

    @Column(nullable = false, length = 25)
    private String last_name;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password_hash;

    @Column(nullable = false, length = 255)
    private String salt;

    @Column(nullable = false, updatable = false, insertable = false)
    private ZonedDateTime createdAt;

    @Column(nullable = false)
    private boolean admin = false;

    @Column(nullable = false)
    private boolean verified = false;

    protected User() {}

    public User(String first_name, String last_name, String email, String password_hash, String salt) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password_hash = password_hash;
        this.salt = salt;
    }

    public UUID getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }
}
