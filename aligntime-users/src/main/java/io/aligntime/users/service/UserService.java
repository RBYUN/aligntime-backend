package io.aligntime.users.service;

import io.aligntime.users.dto.CreateUserRequest;
import io.aligntime.users.dto.UserResponse;
import io.aligntime.users.entity.User;
import io.aligntime.users.exception.UserAlreadyExistsException;
import io.aligntime.users.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

@Service
public class UserService {

    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    private final SecureRandom random = new SecureRandom();

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {

        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        char[] passwordChars = request.getPassword().toCharArray();

        try {
            KeySpec spec = new PBEKeySpec(passwordChars, salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            String encodedHash = Base64.getEncoder().encodeToString(hash);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            User newUser = new User(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    encodedHash,
                    encodedSalt
            );
            User savedUser = userRepository.save(newUser);
            return new UserResponse(savedUser.getId(), savedUser.getEmail());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Password Hashing Failed", e);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException(request.getEmail());
        } finally {
            Arrays.fill(passwordChars, '\0');
        }

    }

}
