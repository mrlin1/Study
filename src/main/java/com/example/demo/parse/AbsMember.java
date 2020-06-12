package com.example.demo.parse;

import lombok.Data;

import java.util.List;

@Data
public abstract class AbsMember extends CodeRange {

    protected String type;

    protected List<AbsMember> childs;

}
