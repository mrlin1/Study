package com.example.demo.ssh;

import lombok.Data;

@Data
public class SshEntity {

    private String host;
    private int port = 22;
    private String username;
    private String password;
    private String rootPassword;

}
