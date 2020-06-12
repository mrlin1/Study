package com.example.demo.jgit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogEntity {

    private String relationId;

    private String commiter;

    private String email;

    private Date commitDate;

    private String message;

}
