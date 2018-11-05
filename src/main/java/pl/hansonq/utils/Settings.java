package pl.hansonq.utils;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class Settings {
    private static String serwer;
    private static String login;
    private static String password;
    private static String database;
    private static String systemDatabase;
    private static String lastVisitedDirectory;
    private static String sqlLink;
    private static String docPath;
    private static String picturesPath;
    private static String last_path;
    private static String separator;
    private static String quotas;
    private static String listOfInvoices;
    private static String kodUrzZew;
    private static String magazyn;
    private static String system;



    private static int IdCechaNazwaInternetowa;
    private static int IdOpakowanieZbiorcze;

    public Settings() {
        loadSettings();

        DOMConfigurator.configure("log4j.xml");
    }

    public static String getSerwer() {
        return serwer;
    }

    public static void setSerwer(String serwer) {
        Settings.serwer = serwer;
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

    public static String getDocPath() {
        return docPath;
    }

    public static void setDocPath(String docPath) {
        Settings.docPath = docPath;
    }

    public static String getPicturesPath() {
        return picturesPath;
    }

    public static void setPicturesPath(String picturesPath) {
        Settings.picturesPath = picturesPath;
    }

    public static String getLast_path() {
        return last_path;
    }

    public static void setLast_path(String last_path) {
        Settings.last_path = last_path;
    }

    public static String getSeparator() {
        return separator;
    }

    public static void setSeparator(String separator) {
        Settings.separator = separator;
    }

    public static String getQuotas() {
        return quotas;
    }

    public static void setQuotas(String quotas) {
        Settings.quotas = quotas;
    }

    public static String getListOfInvoices() {
        return listOfInvoices;
    }

    public static void setListOfInvoices(String listOfInvoices) {
        Settings.listOfInvoices = listOfInvoices;
    }

    public static String getKodUrzZew() {
        return kodUrzZew;
    }

    public static void setKodUrzZew(String kodUrzZew) {
        Settings.kodUrzZew = kodUrzZew;
    }

    public static String getMagazyn() {
        return magazyn;
    }

    public static void setMagazyn(String magazyn) {
        Settings.magazyn = magazyn;
    }

    final static Logger logger = Logger.getLogger(Settings.class);


    public static String getSystem() {
        return system;
    }

    public static void setSystem(String system) {
        Settings.system = system;
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
            Settings.setSerwer(prop.getProperty("serwer"));
            Settings.setLogin(prop.getProperty("login"));
            Settings.setPassword(prop.getProperty("password"));
            Settings.setDatabase(prop.getProperty("database"));
            Settings.setSystemDatabase(prop.getProperty("systemDatabase"));
            Settings.setSqlLink(prop.getProperty("sqlLink"));
            Settings.setDocPath(prop.getProperty("docPath"));
            Settings.setPicturesPath(prop.getProperty("picturesPath"));
            Settings.setQuotas(prop.getProperty("quotas"));
            Settings.setSeparator(prop.getProperty("separator"));
            Settings.setListOfInvoices(prop.getProperty("invoices"));
            Settings.setKodUrzZew(prop.getProperty("kodUrzZew"));
            Settings.setMagazyn(prop.getProperty("magazyn"));
           Settings.setSystem(prop.getProperty("system"));


            //  Settings.setLastVisitedDirectory(prop.getProperty("lastVisitedDirectory"));
            //  loadConnectorSettings();


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Błąd odczytu ustawień","EDI INTER-ELEKTRO",JOptionPane.ERROR_MESSAGE);
            Utils.createSimpleDialog("Odczyt danych", "", "Błąd odczytu ustawień !", Alert.AlertType.ERROR);


        }
       finally {
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


//    public static void saveDirectory(String directory) {
//
//        Properties prop = new Properties();
//        OutputStream output = null;
//
//        try {
//
//            output = new FileOutputStream("config.properties", true);
//
//            prop.setProperty("lastVisitedDirectory", directory);
//
//            // save properties to project root folder
//            prop.storeToXML(output, null);
//            Settings.loadSettings();
//
//
//        } catch (Exception ex) {
//            Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisu ustawień !\n" +
//                    "\"Sprawdź poprawność wprowadzonych danych !\"", Alert.AlertType.ERROR));
//
//
//        } finally {
//            if (output != null) {
//                try {
//                    output.close();
//                } catch (IOException e) {
//                    Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisuu ustawień !\n" +
//                            "Sprawdź poprawność wprowadzonych danych !", Alert.AlertType.ERROR));
//
//                }
//            }
//        }
//    }


}