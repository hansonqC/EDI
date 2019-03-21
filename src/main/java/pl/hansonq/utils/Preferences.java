package pl.hansonq.utils;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.*;

public class Preferences {
    public static final String CONFIG_FILE = "config.txt";
    final static Logger logger = Logger.getLogger(Preferences.class);
    String login;
    String password;
    String serwer;
    String kodUrzZew;
    String magazyn;
    String database;
    String systemDatabase;
    String sqlLink;
    String sqlLinkSystem;
    String invoices;
    String idCechaPSB;
    String lastLogin;

    public Preferences() {
        DOMConfigurator.configure("log4j.xml");
        login = "sysdba";
        password = "masterkey";
        serwer = "rajmanx";
        kodUrzZew = "EDI_IMPORT";
        magazyn = "Magazyn główny";
        database = "/var/local/baza/RAJMANSJ_TEST.gdb";
       sqlLink = "jdbc:firebirdsql:rajmanx/3050:/var/local/baza/RAJMANSJ_TEST.gdb?sql_dialect=3&amp;encoding=UTF8";
        systemDatabase = "/var/local/baza/systemst.gdb";
        sqlLinkSystem = "jdbc:firebirdsql:rajmanx/3050:/var/local/baza/systemst.gdb?sql_dialect=3&amp;encoding=UTF8";
        invoices = "\\c:\\users\\";
        idCechaPSB="10011";
        lastLogin = "EDI_IMPORT";

    }

    public static String getConfigFile() {
        return CONFIG_FILE;
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSerwer() {
        return serwer;
    }

    public void setSerwer(String serwer) {
        this.serwer = serwer;
    }

    public String getKodUrzZew() {
        return kodUrzZew;
    }

    public void setKodUrzZew(String kodUrzZew) {
        this.kodUrzZew = kodUrzZew;
    }

    public String getMagazyn() {
        return magazyn;
    }

    public void setMagazyn(String magazyn) {
        this.magazyn = magazyn;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getSystemDatabase() {
        return systemDatabase;
    }

    public void setSystemDatabase(String systemDatabase) {
        this.systemDatabase = systemDatabase;
    }

    public String getSqlLink() {
        return sqlLink;
    }

    public void setSqlLink(String sqlLink) {
        this.sqlLink = sqlLink;
    }

    public String getSqlLinkSystem() {
        return sqlLinkSystem;
    }

    public void setSqlLinkSystem(String sqlLinkSystem) {
        this.sqlLinkSystem = sqlLinkSystem;
    }

    public String getInvoices() {
        return invoices;
    }

    public void setInvoices(String invoices) {
        this.invoices = invoices;
    }

    public String getIdCechaPSB() {
        return idCechaPSB;
    }

    public void setIdCechaPSB(String idCechaPSB) {
        this.idCechaPSB = idCechaPSB;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public static void initConfig() {
        Writer writer = null;

        try {
            Preferences preferences = new Preferences();
            Gson gson = new Gson();
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(preferences, writer);
        } catch (IOException e) {
            logger.debug(e.fillInStackTrace());
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                logger.debug(ex.fillInStackTrace());
            }
        }


    }

    public static Preferences getPreferences() {
        Gson gson = new Gson();
        Preferences preferences = new Preferences();
        try {
            preferences = gson.fromJson(new FileReader(CONFIG_FILE), Preferences.class);
        } catch (FileNotFoundException e) {
            initConfig();
            e.printStackTrace();
        }
        return preferences;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", serwer='" + serwer + '\'' +
                ", kodUrzZew='" + kodUrzZew + '\'' +
                ", magazyn='" + magazyn + '\'' +
                ", database='" + database + '\'' +
                ", systemDatabase='" + systemDatabase + '\'' +
                ", sqlLink='" + sqlLink + '\'' +
                ", sqlLinkSystem='" + sqlLinkSystem + '\'' +
                ", invoices='" + invoices + '\'' +
                ", idCechaPSB='" + idCechaPSB + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                '}';
    }
}
