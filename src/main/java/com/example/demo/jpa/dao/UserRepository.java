package com.example.demo.jpa.dao;

import com.example.demo.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
