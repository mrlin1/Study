package com.example.demo.ssh;

import com.jcraft.jsch.JSchException;
import org.testng.annotations.Test;

public class SshCoreTest {

    @Test
    public void testCreateDir() {

        SshEntity entity = new SshEntity();
        entity.setHost("150.158.116.155");
        entity.setPassword("qaz_1472");
        entity.setUsername("root");
        entity.setPort(22);

        try (SshCore sshCore = new SshCore(entity)) {
            sshCore.createDir();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
