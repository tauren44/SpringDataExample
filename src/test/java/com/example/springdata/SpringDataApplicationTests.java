package com.example.springdata;

import com.example.springdata.config.ApplicationConfig;
import com.example.springdata.model.User;
import com.example.springdata.service.UserService;
import lombok.NoArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@NoArgsConstructor
public class SpringDataApplicationTests {

    @Autowired
    private UserService service;

    @Before
    public void init() {
        User user = new User()
                .setName("vasya")
                .setAge(25)
                .setSalary(2000)
                .setEmail("vasya@email.com")
                .setBirthday(LocalDate.of(1994, 2, 25));
        service.createUser(user);
    }

    @After
    public void drop(){
        service.deleteUser(service.findOneByEmail("vasya@email.com").getId());
    }

    @Test
    public void shouldCreateUser() {
        User created = new User()
                .setName("petya")
                .setAge(28)
                .setSalary(1500)
                .setEmail("petya@email.com")
                .setBirthday(LocalDate.of(1991, 4, 2));
        service.createUser(created);
        assertTrue(service.findAll().contains(created));
    }

    @Test
    public void shouldUpdateUser() {
        User user = service.findOneByEmail("vasya@gmail.com");
        user.setName("TEST");
        service.updateUser(user);
        User updated = service.findUserById(user.getId());
        assertEquals(user.getName(), updated.getName());
    }

    @Test
    public void shouldReturnUserById() {
        User user = service.findOneByEmail("vasya@gmail.com");
        Long id = user.getId();
        User target = service.findUserById(id);
        assertEquals(user, target);
    }

    @Test
    public void shouldDeleteUser() {
        User created = new User()
                .setName("test")
                .setAge(18)
                .setSalary(1000)
                .setEmail("test@email.com")
                .setBirthday(LocalDate.of(2001, 4, 2));
        service.createUser(created);
        service.deleteUser(created.getId());
        assertFalse(service.findAll().contains(created));
    }
}
