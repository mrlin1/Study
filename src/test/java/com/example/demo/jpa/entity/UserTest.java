package com.example.demo.jpa.entity;

import com.example.demo.jpa.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testImport() {

        User user = new User();

        userRepository.save(user);
    }
}
