package com.example.demo.jgit;

import com.example.demo.parse.ParseJavaUtil;
import com.example.demo.parse.ParseMethod;
import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JGitUtilTest {

    @Test
    public void testGitOpen() {
        Path path = Paths.get("H:\\test\\Daily");
        Git git = null;
        try {
            git = Git.open(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        BlameCommand blame = git.blame();
        blame.setFilePath("daily-back/src/main/java/com/example/dailyback/sys/controller/LoginController.java");
        blame.setStartCommit(ObjectId.fromString("6a2897e024504421d73ce9a7fdbb82fa07366809"));

        try {
            BlameResult call = blame.call();
            System.out.println(1);

            RawText rawText = call.getResultContents();

            List<BlameEntity> blameEntityList = IntStream.range(0, rawText.size()).mapToObj(index -> {
                RevCommit commit = call.getSourceCommit(index);
                PersonIdent committer = call.getSourceCommitter(index);
                String line = rawText.getString(index);

                LogEntity log = new LogEntity(commit.getName(), committer.getName(), committer.getEmailAddress(),
                        committer.getWhen(), commit.getShortMessage());
                BlameEntity blameEntity = new BlameEntity(line, index + 1, log);

                return blameEntity;
            }).collect(Collectors.toList());

            List<ParseMethod> parseMethodList = ParseJavaUtil.getList();

            parseMethodList.stream().forEach(method -> {

                Integer begin = method.getBegin();
                Integer end = method.getEnd();

                List<BlameEntity> collect = blameEntityList.stream().filter(tmpBlame -> {
                    return tmpBlame.getLineNo() <= end && tmpBlame.getLineNo() >= begin;
                }).distinct().collect(Collectors.toList());

                method.setBlames(collect);
            });

            System.out.println(1);

        } catch (GitAPIException | IOException e) {
            e.printStackTrace();
        }


        git.close();

    }

    @Test
    public void testGitBlame() {


    }
}
