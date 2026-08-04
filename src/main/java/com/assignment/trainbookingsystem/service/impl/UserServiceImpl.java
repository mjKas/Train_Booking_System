package com.assignment.trainbookingsystem.service.impl;

import com.assignment.trainbookingsystem.model.User;
import com.assignment.trainbookingsystem.repository.UserRepository;
import com.assignment.trainbookingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    @Override
    public User updateUser(UUID id, User userDetails) {
        User existingUser = getUserById(id);

        // Ensure that if the email is being changed, it isn't taken by someone else
        if (!existingUser.getEmail().equals(userDetails.getEmail()) &&
                userRepository.existsByEmail(userDetails.getEmail())) {
            throw new IllegalArgumentException("Email is already in use by another account");
        }

        existingUser.setEmail(userDetails.getEmail());
        existingUser.setMfaEnabled(userDetails.isMfaEnabled());

        // Note: Password updates should ideally be handled in a separate method with hashing,
        // but this covers the basic profile details.

        return userRepository.save(existingUser);
    }
}