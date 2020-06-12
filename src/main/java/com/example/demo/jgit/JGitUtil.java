package com.example.demo.jgit;

import org.eclipse.jgit.api.Git;

import java.io.IOException;
import java.nio.file.Path;

public class JGitUtil {

    public Git gitOpen(Path path) throws IOException {

        Git git = Git.open(path.toFile());

        return git;
    }


}
