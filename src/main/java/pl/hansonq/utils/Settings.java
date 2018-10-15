package pl.hansonq.utils;


import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.Properties;

public class Settings {
    private static String login;
    private static String password;
    private static String database;
    private static String systemDatabase;

    private static String sqlLink;
    private static int IdCechaNazwaInternetowa;
    private static int IdOpakowanieZbiorcze;

    public Settings() {
        loadSettings();
    }

    public static String getSystemDatabase() {
        return systemDatabase;
    }

    public static void setSystemDatabase(String systemDatabase) {
        Settings.systemDatabase = systemDatabase;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        Settings.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Settings.password = password;
    }

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        Settings.database = database;
    }

    public static String getSqlLink() {
        return sqlLink;
    }

    public static void setSqlLink(String sqlLink) {
        Settings.sqlLink = sqlLink;
    }


    public static void loadSettings() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
            if (input == null) {
                System.out.println("Brak pliku z ustawieniami");
                return;
            }
            prop.loadFromXML(input);
            Settings.setLogin(prop.getProperty("login"));
            Settings.setPassword(prop.getProperty("password"));
            Settings.setDatabase(prop.getProperty("database"));
            Settings.setSystemDatabase(prop.getProperty("systemDatabase"));
            Settings.setSqlLink(prop.getProperty("sqlLink"));

            //  loadConnectorSettings();


        } catch (Exception ex) {
            Utils.createSimpleDialog("Odczyt danych", "", "Błąd odczytu ustawień !", Alert.AlertType.ERROR);


        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Utils.createSimpleDialog("Odczyt danych", "", "Błąd odczytu ustawień !", Alert.AlertType.ERROR);

                }
            }
        }
    }

    /*    private static void loadConnectorSettings() {

            FtpClient.setUser(Settings.getFtpLogin());
            FtpClient.setPass(Settings.getFtpPassword());
            FtpClient.setServer(Settings.getFtpServer());
            FtpClient.setPort(Settings.getFtpPort());
            FtpClient.setPath(Settings.getFtpPath());
            FtpClient.setFtpJsonFile(Settings.getFtpJsonFile());
           // JOptionPane.showMessageDialog(null,FirebirdConnector.getSqlClass());


        }*/
    public static void saveSettings(boolean choose) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties", true);

            prop.setProperty("auto", String.valueOf(choose));

            // save properties to project root folder
            prop.storeToXML(output, null);
            Settings.loadSettings();


        } catch (Exception ex) {
            Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisu ustawień !\n" +
                    "\"Sprawdź poprawność wprowadzonych danych !\"", Alert.AlertType.ERROR));


        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisuu ustawień !\n" +
                            "Sprawdź poprawność wprowadzonych danych !", Alert.AlertType.ERROR));

                }
            }
        }
    }
}