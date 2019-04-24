package com.taotao.common.utils;

import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * ftp上传下载工具类
 * <p>Title: FtpUtil</p>
 *
 * @version 1.0
 */
public class FtpUtil {

    private static Log log = LogFactory.getLog(FtpUtil.class);

    private static Session createSession(String username, String password, String ip, Integer port) throws JSchException {
        JSch jsch = new JSch(); // 创建JSch对象
        // 根据用户名，主机ip，端口获取一个Session对象
        Session session = jsch.getSession(username, ip, port);
        log.info("Session created...");
        if (password != null) {
            // 设置密码
            session.setPassword(password);
        }
        // 为Session对象设置properties
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        // 设置timeout时间
        session.setTimeout(60000);
        session.connect(); // 通过Session建立链接
        return session;
    }

    /**
     * 传入一个通道对象
     *
     * @return ChannelSftp返回指向这个通道指定的地址的channel实例
     * @throws JSchException
     */
    private static ChannelSftp getChannel(Session session) throws JSchException {
        log.info("Session connected, Opening Channel...");
        // 打开SFTP通道
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect(); // 建立SFTP通道的连接
        log.info("getChannel successfully");
        return channel;
    }


    /**
     * 关闭channel和session
     *
     * @throws Exception
     */
    private static void closeChannel(ChannelSftp channel, Session session) throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    private static boolean isDirExist(ChannelSftp channel, String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = channel.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    private static void createDir(ChannelSftp channel, String path) throws SftpException {

        if (!isDirExist(channel, path)) {
            String[] split = path.split("/");
            for (String dirName : split) {
                if (Objects.equals("", dirName))
                    continue;
                if (isDirExist(channel, dirName)) {
                    channel.cd(dirName);
                } else {
                    // 建立目录
                    channel.mkdir(dirName);
                    // 进入并设置为当前目录
                    channel.cd(dirName);
                }
            }
        } else {
            channel.cd(path);
        }
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param host     FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) {
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            session = createSession(username, password, host, port);
            channelSftp = getChannel(session);

            if (isDirExist(channelSftp, basePath)) {
                channelSftp.cd(basePath);
            } else {
                createDir(channelSftp, basePath);
            }
            if (isDirExist(channelSftp, filePath)) {
                channelSftp.cd(filePath);
            } else {
                createDir(channelSftp, filePath);
            }

            channelSftp.put(input, filename);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            log.info(String.format("上传文件失败！！原因：%s", e));
        } finally {
            try {
                closeChannel(channelSftp, session);
            } catch (Exception e) {
                log.info(String.format("关闭FTP会话和通道失败！！原因：%s", e));
                e.printStackTrace();
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param host       FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
                                       String fileName, String localPath) {
        boolean result = false;

        return result;
    }

    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream(new File("C:\\360Downloads\\wpcache\\360wallpaper.jpg"));
            boolean flag = uploadFile("101.37.148.209", 22,
                    "ftpuser", "ftpuser",
                    "/home/wangzi/www/images",
                    "/2019/04/24",
                    "qinying.jpg", in);
            System.out.println(flag);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
