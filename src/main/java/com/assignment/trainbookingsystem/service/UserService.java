package com.assignment.trainbookingsystem.service;
import com.assignment.trainbookingsystem.model.User;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    User getUserById(UUID id);
}