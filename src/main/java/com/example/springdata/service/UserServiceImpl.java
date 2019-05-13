package com.example.springdata.service;

import com.example.springdata.model.User;
import com.example.springdata.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public void createUser(User user) {
        repository.save(user);
    }

    @Override
    public void updateUser(User user) {
        repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> findAllByBirthDay(int month, int day) {
        return repository.findByMatchMonthAndMatchDay(month, day);
    }

    @Override
    public User findOneByEmail(String email) {
        return repository.findOneByEmail(email);
    }
}
