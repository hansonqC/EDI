package pl.hansonq.utils;

//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FtpClient {

//
//    private static String server;// = "ftp://serwer1331271.home.pl";
//    private static int port;// = 21;
//    private static  String user;// = "serwer1331271";
//    private static  String pass;// = "zz9864579";
//    private static String path;
//    private static String ftpJsonFile;
//
//    public static String getFtpJsonFile() {
//        return ftpJsonFile;
//    }
//
//    public static void setFtpJsonFile(String ftpJsonFile) {
//        FtpClient.ftpJsonFile = ftpJsonFile;
//    }
//
//    public static String getPath() {
//        return path;
//    }
//
//    public static void setPath(String path) {
//        FtpClient.path = path;
//    }
//
//    public static String getServer() {
//        return server;
//    }
//
//    public static void setServer(String server) {
//        FtpClient.server = server;
//    }
//
//    public static int getPort() {
//        return port;
//    }
//
//    public static void setPort(int port) {
//        FtpClient.port = port;
//    }
//
//    public static String getUser() {
//        return user;
//    }
//
//    public static void setUser(String user) {
//        FtpClient.user = user;
//    }
//
//    public static String getPass() {
//        return pass;
//    }
//
//    public static void setPass(String pass) {
//        FtpClient.pass = pass;
//    }
//    private static void showServerReply(FTPClient ftpClient) {
//        String[] replies = ftpClient.getReplyStrings();
//        if (replies != null && replies.length > 0) {
//            for (String aReply : replies) {
//                System.out.println("SERVER: " + aReply);
//            }
//        }
//    }
//
//    public static void transfer(){//String serverS, int portS, String userS, String passS, String file)  {
//        String serverS=server;
//        int portS=port;
//        String userS=user;
//        String passS=pass;
//        String file= Settings.getJson();
//
//
//        FTPClient ftpClient = new FTPClient();
//        try {
//
//            try {
//                ftpClient.connect(server, port);
//                showServerReply(ftpClient);
//                int replyCode = ftpClient.getReplyCode();
//                if (!FTPReply.isPositiveCompletion(replyCode)) {
//                    System.out.println("Connect failed");
//                    return;
//                }
//
//                boolean success = ftpClient.login(user, pass);
//                showServerReply(ftpClient);
//
//                if (!success) {
//                    System.out.println("Could not login to the server");
//                    return;
//                }
//                success = ftpClient.changeWorkingDirectory(path);
//                showServerReply(ftpClient);
//
//                if (success) {
//                    System.out.println("Successfully changed working directory.");
//                } else {
//                    System.out.println("Failed to change working directory. See server's reply.");
//                }
//
//                ftpClient.enterLocalPassiveMode();
//                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//
//
//
//        // APPROACH #1: uploads first file using an InputStream
//        File firstLocalFile = new File(file);
//
//        String firstRemoteFile = FtpClient.getPath()+FtpClient.getFtpJsonFile();
//        InputStream inputStream = new FileInputStream(firstLocalFile);
//
//        System.out.println("Start uploading "+ftpJsonFile);
//        boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
//        inputStream.close();
//        if (done) {
//            System.out.println("The first file is uploaded successfully.");
//        }
//
////        // APPROACH #2: uploads second file using an OutputStream
////        File secondLocalFile = new File("E:/Test/Report.doc");
////        String secondRemoteFile = "test/Report.doc";
////        inputStream = new FileInputStream(secondLocalFile);
////
////        System.out.println("Start uploading second file");
////        OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
////        byte[] bytesIn = new byte[4096];
////        int read = 0;
////
////        while ((read = inputStream.read(bytesIn)) != -1) {
////            outputStream.write(bytesIn, 0, read);
////        }
//        inputStream.close();
//    //    outputStream.close();
//
//        boolean completed = ftpClient.completePendingCommand();
//        if (completed) {
//            System.out.println("The first file is uploaded successfully.");
//        }
//
//    } catch (IOException ex) {
//        System.out.println("Error: " + ex.getMessage());
//        ex.printStackTrace();
//    } finally {
//        try {
//            if (ftpClient.isConnected()) {
//                ftpClient.logout();
//                ftpClient.disconnect();
//                System.exit(0);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//            try {
//                ftpClient.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//    }
//
//}

}
