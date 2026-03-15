package io.aligntime.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "users", schema="auth")
public class User {
    @Id
    private UUID id;


    private String first_name;
    private String last_name;
    private String email;
    private String password;

    private ZonedDateTime created_by;
    private boolean admin;
    private boolean verified;

    protected User() {}

    public User(String first_name, String last_name, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("User %s has email: %s", id.toString(), email);
    }

}
