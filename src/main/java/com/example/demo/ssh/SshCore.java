package com.example.demo.ssh;

import com.jcraft.jsch.*;

import java.util.Objects;
import java.util.Properties;

public class SshCore implements AutoCloseable {

    private JSch jsch; //对象
    private Session session; //会话
    private ChannelSftp sftp; //通道

    public SshCore(SshEntity entity) throws JSchException {

        jsch = new JSch();

        try {
            session = jsch.getSession(entity.getUsername(), entity.getHost(), entity.getPort());
            session.setPassword(entity.getPassword());
        } catch (JSchException e) {
            e.printStackTrace();
        }

        UserInfo userInfo = new UserInfo() {
            @Override
            public String getPassphrase() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public boolean promptPassword(String s) {
                return false;
            }

            @Override
            public boolean promptPassphrase(String s) {
                return false;
            }

            @Override
            public boolean promptYesNo(String s) {
                return false;
            }

            @Override
            public void showMessage(String s) {

            }
        };

        session.setUserInfo(userInfo);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        session.setTimeout(30000);

        connect();
    }

    private void connect() throws JSchException {
        session.connect();
        Channel channel = session.openChannel("sftp");
        sftp = (ChannelSftp) channel;
        sftp.connect();
    }

    @Override
    public void close() throws Exception {

        if (Objects.nonNull(sftp) && sftp.isConnected()) {
            sftp.disconnect();
        }

        if (Objects.nonNull(session) && session.isConnected()) {
            session.disconnect();
        }
    }

    public void createDir(String dirPath) throws SftpException {
        sftp.mkdir(dirPath);
    }
}
