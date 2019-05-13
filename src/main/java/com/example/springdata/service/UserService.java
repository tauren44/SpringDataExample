package com.example.springdata.service;

import com.example.springdata.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User findUserById(Long id);

    List<User> findAll();

    List<User> findAllByBirthDay(int month, int day);

    User findOneByEmail(String email);
}
