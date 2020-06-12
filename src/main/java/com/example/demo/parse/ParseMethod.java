package com.example.demo.parse;

import com.example.demo.jgit.BlameEntity;
import lombok.Data;

import java.util.List;

@Data
public class ParseMethod extends AbsMember {

    private String name;
    private String type;
    private String returnType;
    private List<BlameEntity> blames;

}
