package com.example.demo.parse;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParseJavaUtil {

    public static List<ParseMethod> getList() throws IOException {
        Path path = Paths.get("E:\\Daily\\daily-back\\src\\main\\java\\com\\example\\dailyback\\sys\\controller" +
                "\\LoginController.java");

        JavaParser parser = new JavaParser();
        ParseResult<CompilationUnit> parse = parser.parse(path);

        CompilationUnit unit = parse.getResult().get();

        System.out.println(1);

        NodeList<BodyDeclaration<?>> members = unit.getType(0).getMembers();

        List<ParseMethod> collect = IntStream.range(0, members.size()).mapToObj(members::get)
                .filter(BodyDeclaration::isMethodDeclaration)
                .map(BodyDeclaration::asMethodDeclaration)
                .map(method -> {
                    ParseMethod parseMethod = new ParseMethod();
                    parseMethod.setName(method.getNameAsString());
                    parseMethod.setType(method.getTypeAsString());
                    parseMethod.setBegin(method.getRange().get().begin.line);
                    parseMethod.setEnd(method.getRange().get().end.line);
                    return parseMethod;
                }).collect(Collectors.toList());
        return collect;
    }

}
