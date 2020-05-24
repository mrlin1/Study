package com.example.demo.jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(generator = "user_id")
    @GenericGenerator(name = "user_id", strategy = "uuid")
    @Column(name = "user_id", length = 32)
    private String userId;

}
