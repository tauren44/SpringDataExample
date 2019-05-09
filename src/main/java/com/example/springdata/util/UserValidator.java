package com.example.springdata.util;

import com.example.springdata.model.User;
import com.example.springdata.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserValidator implements Validator {

    private UserService service;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (user.getAge() < 0) {
            errors.rejectValue("age", "value.negative");
        }
        if(service.findOneByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "email already exists");
        }
    }
}
