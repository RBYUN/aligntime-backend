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
    private String firstName;

    @Column(nullable = false, length = 25)
    private String lastName;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 255)
    private String salt;

    @Column(nullable = false, updatable = false, insertable = false)
    private ZonedDateTime createdAt;

    @Column(nullable = false)
    private boolean admin = false;

    @Column(nullable = false)
    private boolean verified = false;

    protected User() {}

    public User(String firstName, String lastName, String email, String passwordHash, String salt) {
        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.email = email.toLowerCase();
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
