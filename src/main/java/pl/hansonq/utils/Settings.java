package pl.hansonq.utils;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class Settings {
    private static String serwer;// = "rajmanx";
    private static String login; //= "sysdba";
    private static String password;// = "masterkey";
    private static String database;// = "/var/local/baza/RAJMANSJ_TEST.gdb";
    private static String systemDatabase;// = "C:\\STREAM soft\\STREAM soft SQL\\Baza\\SYSTEMST.FB";
    private static String lastVisitedDirectory;
    private static String sqlLink;// = "jdbc:firebirdsql:localhost/3050:C:\\STREAM soft\\STREAM soft SQL\\Baza\\RAJMANSJ.FB?sql_dialect=3&amp;encoding=UTF8";
    private static String docPath;// = "C:";
    private static String picturesPath;// = "C:";
    private static String last_path;// = "C:";
    private static String separator;// = ";";
    private static String quotas;//= "\"";
    private static String listOfInvoices;// = "C:\\Users\\Mm2017\\Desktop\\EDI_IMPORT_RAJMAN\\RAJMAN FAKTURY XML\\";
    private static String kodUrzZew;// = "EDI_IMPORT";
    private static String magazyn;// = "10001";
    private static String system;
    private static String id_cechy_psb;// = "10011";
    private static String lastLogin;// = "Admin";


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

    public static String getId_cechy_psb() {
        return id_cechy_psb;
    }

    public static void setId_cechy_psb(String id_cechy_psb) {
        Settings.id_cechy_psb = id_cechy_psb;
    }

    public static String getLastLogin() {
        return lastLogin;
    }

    public static void setLastLogin(String lastLogin) {
        Settings.lastLogin = lastLogin;
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
           // JOptionPane.showMessageDialog(null,"ok");
            if ((prop.getProperty("serwer").isEmpty()) || (prop.getProperty("serwer").equals(""))) {
                Settings.setSerwer("uzupełnij");
            } else {
                Settings.setSerwer(prop.getProperty("serwer"));
            }
            Settings.setLogin(prop.getProperty("login"));
            Settings.setPassword(prop.getProperty("password"));
            Settings.setDatabase(prop.getProperty("database"));
            Settings.setSystemDatabase(prop.getProperty("systemDatabase"));
            Settings.setSqlLink(prop.getProperty("sqlLink"));
            //    Settings.setDocPath(prop.getProperty("docPath"));
            //   Settings.setPicturesPath(prop.getProperty("picturesPath"));
            //  Settings.setQuotas(prop.getProperty("quotas"));
            //  Settings.setSeparator(prop.getProperty("separator"));
            Settings.setListOfInvoices(prop.getProperty("invoices"));
          Settings.setKodUrzZew(prop.getProperty("kodUrzZew"));
            Settings.setMagazyn(prop.getProperty("magazyn"));
          //  Settings.setSystem(prop.getProperty("system"));
            Settings.setId_cechy_psb(prop.getProperty("id_cechy_psb"));
            // Settings.setLastLogin(prop.getProperty("lastLogin"));
            //   JOptionPane.showMessageDialog(null,"10");

            if ((prop.getProperty("lastLogin").isEmpty()) || (prop.getProperty("lastLogin").equals(""))) {
                Settings.setLastLogin("uzupełnij");
            //    JOptionPane.showMessageDialog(null, "11");
            } else {
                Settings.setLastLogin(prop.getProperty("lastLogin"));
              //  JOptionPane.showMessageDialog(null, "12");
            }


            //  Settings.setLastVisitedDirectory(prop.getProperty("lastVisitedDirectory"));
            //  loadConnectorSettings();

//input.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Błąd odczytu ustawień\nUtworzono nowy plik config.properties", "EDI RAJMAN", JOptionPane.ERROR_MESSAGE);
            try (BufferedWriter bw = new BufferedWriter(new PrintWriter("config.properties"))) {
                bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
                bw.newLine();
                bw.write("<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">");
                bw.newLine();
                bw.write("<properties>");
                bw.newLine();
                bw.write("<entry key=\"system\"/>");
                bw.newLine();
                bw.write("<entry key=\"kodUrzZew\">EDI_IMPORT</entry>");
                bw.newLine();
                bw.write("<entry key=\"magazyn\">MATERIAŁY BUDOWLANE</entry>");
                bw.newLine();
                bw.write("<entry key=\"invoices\">C:\\Users\\Mm2017\\Desktop\\EDI_IMPORT_RAJMAN\\RAJMAN FAKTURY XML\\</entry>");
                bw.newLine();
                bw.write("<entry key=\"serwer\">rajmanx</entry>");
                bw.newLine();
                bw.write("<entry key=\"login\">sysdba</entry>");
                bw.newLine();
                bw.write("<entry key=\"id_cechy_psb\">10011</entry>");
                bw.newLine();
                bw.write("<entry key=\"sqlLink\">jdbc:firebirdsql:rajmanx/3050:/var/local/baza/RAJMANSJ_TEST.gdb?sql_dialect=3&amp;encoding=UTF8</entry>");
                bw.newLine();
                bw.write("<entry key=\"database\">/var/local/baza/RAJMANSJ_TEST.gdb</entry>");
                bw.newLine();
                bw.write("<entry key=\"systemDatabase\">/var/local/baza/SYSTEMST.gdb</entry>");
                bw.newLine();
                bw.write("<entry key=\"password\">masterkey</entry>");
                bw.newLine();
                bw.write("<entry key=\"lastLogin\">Admin</entry>");
                bw.newLine();
                bw.write("</properties>");
                bw.close();
                JOptionPane.showMessageDialog(null,"Zapisano");


            } catch (IOException e) {
                e.printStackTrace();
                Utils.createSimpleDialog("Odczyt danych", "", "Błąd odczytu ustawień !", Alert.AlertType.ERROR);

            }


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