package com.bioinformatics.spring.security.postgresql.security.services;

import java.util.Set;
import java.util.regex.Pattern;

import com.bioinformatics.spring.security.postgresql.models.Role;
import com.bioinformatics.spring.security.postgresql.models.User;
import com.bioinformatics.spring.security.postgresql.payload.response.BadInputParameters;
import com.bioinformatics.spring.security.postgresql.payload.response.ConflictDataException;
import com.bioinformatics.spring.security.postgresql.repository.UserRepository;
import lombok.extern.java.Log;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log
public class UserService {

    private final UserRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addNewUser(String username, String email, String password, Set<Role> roles) throws ConflictDataException {
        validateEmail(email);
        try {
            User user = new User(username, email, passwordEncoder.encode(password));
            user.setRoles(roles);
            User saved = usersRepository.save(user);
            log.info("User created " + saved);
        } catch (DataIntegrityViolationException exception) {
            throw new ConflictDataException("You cannot use " + email, exception);
        }
    }

    public static void validateEmail(String emailAddress) {
        if (!Pattern.compile("^(.+)@(\\S+)$")
                .matcher(emailAddress)
                .matches()) {
            throw new BadInputParameters("invalid email");
        }
    }

    public void deleteAccount(Long userId) {
        usersRepository.deleteById(userId);
    }
}
