package pl.hansonq.utils;
//package pl.

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static Node switchView2(Node node, String fxml, int width, int height, boolean newWindow, StageStyle style, boolean closeOldWindow) {
        Stage stage = (Stage) node.getScene().getWindow();
        if (!newWindow) {
            Parent root = null;
            try {

                root = FXMLLoader.load(Utils.class.getClassLoader().getResource(fxml));
                // root = loader.load();
                stage.setScene(new Scene(root, width, height));
                stage.getIcons().add(new Image(Utils.class.getResourceAsStream("file:logo.png")));

                stage.setResizable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return root;
        } else {
            if (closeOldWindow) {
                stage.close();
            }
            Parent root = null;
            Stage newStage = new Stage();
            try {
                root = FXMLLoader.load(Utils.class.getClassLoader().getResource(fxml));
                // root = loader.load();

                Scene scene = new Scene(root, width, height);
                newStage.getIcons().add(new Image(Utils.class.getResourceAsStream("file:logo.png")));
                newStage.setResizable(false);
                newStage.initStyle(style);
                newStage.setMaximized(true);
                newStage.setTitle("IMPORT KARTOTEK 3.0");
                newStage.setScene(scene);
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return root;
        }
    }


    public static String md5Hash(String pass) {

        try {
            String original = pass;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(original.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            JOptionPane.showMessageDialog(null, sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String shaHash(String message) {
        try {
            MessageDigest sha2 = MessageDigest.getInstance("SHA-256");

            byte[] bytesOfMessage = message.getBytes();
            byte[] bytesOfCryptoMessage = sha2.digest(bytesOfMessage);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < bytesOfCryptoMessage.length; i++) {
                stringBuilder.append(Integer.toHexString(0xFF & bytesOfCryptoMessage[i]));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createSimpleDialog(String name, String header, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(name);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }

    public static Boolean streamHash(int id_uzytkownik, String hashPassword, String password) {

            boolean check=pl.com.stream.lib.code.UserPasswdCheck.checkPassword(id_uzytkownik, hashPassword, password);//id_uzytkownika, hasło w bazie , hasło podane przez użytkownika
            if(check){
                return true;
            }
            if(!check){
                return  false;
            }


        return false;
    }

}