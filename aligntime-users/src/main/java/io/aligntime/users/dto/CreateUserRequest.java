package io.aligntime.users.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid First Name. Only letters are allowed")
    @NotBlank
    private final String first_name;

    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid Last Name. Only letters are allowed")
    @NotBlank
    private final String last_name;

    @Email
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Invalid Email")
    @NotBlank
    private final String email;

    @Size(min = 8)
    @Pattern(regexp = "^[A-Za-z0-9!@$%*?]{9,}$")
    private final String password;

    public CreateUserRequest(String first_name, String last_name, String email, String password) {
        this.first_name = first_name.trim().toLowerCase();
        this.last_name = last_name.trim().toLowerCase();
        this.email = email.trim().toLowerCase();
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
