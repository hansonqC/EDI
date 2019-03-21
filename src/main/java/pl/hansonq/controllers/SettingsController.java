package pl.hansonq.controllers;


import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import pl.hansonq.dao.DocumentInvoiceDao;
import pl.hansonq.dao.Impl.DocumentInvoiceDaoImpl;
import pl.hansonq.utils.Preferences;
import pl.hansonq.utils.Settings;
import pl.hansonq.utils.Utils;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingsController implements Initializable {
    DocumentInvoiceDao documentInvoiceDao = new DocumentInvoiceDaoImpl();
    List<String> magazyny;
    public static final String CONFIG_FILE = "config.txt";
    @FXML
    private TextField textLogin, textDatabase, textSystemDatabase, textSerwer, textInvoicesPath, textUrzZew, textPSB;
    @FXML
    private PasswordField textPassword;
    @FXML
    private ComboBox magazyn;
    @FXML
    private Button buttonDatabaseChoose, buttonSave, buttonInvoicesPath;

    final static Logger logger = Logger.getLogger(SettingsController.class);
    private ExecutorService executorService;
    private boolean choose;


    public SettingsController() {
        executorService = Executors.newSingleThreadExecutor();
        DOMConfigurator.configure("log4j.xml");
//loadS();

    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            DOMConfigurator.configure("log4j.xml");
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }
        Preferences.getPreferences();
        // choosing();
        //  buttonFileChoose.setOnMouseClicked(e -> fileChooseOpen());
        buttonDatabaseChoose.setOnMouseClicked(e -> databaseChoose());
        buttonInvoicesPath.setOnMouseClicked(e -> docChoose());
        buttonSave.setOnMouseClicked(e -> SavePreferences());
        buttonSave.requestFocus();
        //  buttonSystemDatabaseChoose.setOnMouseClicked(e->SystemDatabaseChoose());
        //  buttonPictureChoose.setOnMouseClicked(e->PicturesPathSetting());
        //   toogleButton.setOnMouseClicked(e -> turning());
        // loadS();
        magazyny = new ArrayList<>();
        try {
            magazyny = documentInvoiceDao.getMagazyn();
            ObservableList<String> lista = FXCollections.observableArrayList(magazyny);

            magazyn.getItems().addAll(lista);
        } catch (Exception ex) {
            logger.debug(ex.fillInStackTrace());
        }
        initDefaultValues();


    }


    private void loadS() {
        try {
            textSerwer.setText(Settings.getSerwer());
            textLogin.setText(Settings.getLogin());
            textPassword.setText(Settings.getPassword());
            textDatabase.setText(Settings.getDatabase());
            textInvoicesPath.setText(Settings.getListOfInvoices());
            textSystemDatabase.setText(Settings.getSystemDatabase());
            textUrzZew.setText(Settings.getKodUrzZew());
            magazyn.setValue(Settings.getMagazyn());
            textPSB.setText(Settings.getId_cechy_psb());
        } catch (Exception ex) {
            logger.debug(ex.fillInStackTrace());
        }
        //  textSystemDatabase.setText(Settings.getSystemDatabase());
        // textDoc.setText(Settings.getDocPath());
        // textPicture.setText(Settings.getPicturesPath());
//        textJson.setText(Settings.getJson());
//        textQuery.setText(Settings.getQuery());
//        textFtpLogin.setText(Settings.getFtpLogin());
//        textFtpPassword.setText(Settings.getFtpPassword());
//        textFtpServer.setText(Settings.getFtpServer());
//        textFtpPort.setText(String.valueOf(Settings.getFtpPort()));
//        textFtpPath.setText(Settings.getFtpPath());kkkkkkkkkkkkkkkkk
//        textFtpJsonFile.setText(Settings.getFtpJsonFile());

    }


    private void initDefaultValues() {
        Preferences preferences = Preferences.getPreferences();
        textSerwer.setText(String.valueOf(preferences.getSerwer()));
        textLogin.setText(String.valueOf(preferences.getLogin()));
        textPassword.setText(String.valueOf(preferences.getPassword()));
        textDatabase.setText(String.valueOf(preferences.getDatabase()));
        textSystemDatabase.setText(String.valueOf(preferences.getSystemDatabase()));
        textInvoicesPath.setText(String.valueOf(preferences.getInvoices()));
        textUrzZew.setText(String.valueOf(preferences.getKodUrzZew()));
        magazyn.setValue(String.valueOf(preferences.getMagazyn()));
        textPSB.setText(Preferences.getPreferences().getIdCechaPSB());


        //  textParametr.setText(String.valueOf(preferences.get));
    }

    private void SavePreferences() {
        Preferences preferences = new Preferences();
        Gson gson = new Gson();
        Writer writer;
        preferences.setSerwer(textSerwer.getText());
        preferences.setLogin(textLogin.getText());
        preferences.setPassword(textPassword.getText());
        preferences.setDatabase(textDatabase.getText());
        preferences.setSystemDatabase(textSystemDatabase.getText());
        preferences.setInvoices(textInvoicesPath.getText());
        preferences.setKodUrzZew(textUrzZew.getText());
        preferences.setMagazyn(magazyn.getValue().toString());
        preferences.setIdCechaPSB(textPSB.getText());
        try {
            writer = new FileWriter(CONFIG_FILE);
            try {

                gson.toJson(preferences, writer);
            } catch (Exception e) {
                logger.debug(e.fillInStackTrace());
            } finally {
                try {
                    writer.close();
                    Stage stage = (Stage) buttonSave.getScene().getWindow();
                    stage.close();
                } catch (Exception ex) {
                    logger.debug(ex.fillInStackTrace());
                }


            }
        } catch (Exception ex) {
            logger.debug(ex.fillInStackTrace());
        }
    }

    private void saveSettings() {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            // set the properties value
            prop.setProperty("serwer", textSerwer.getText());
            prop.setProperty("login", textLogin.getText());
            prop.setProperty("password", textPassword.getText());
            prop.setProperty("database", textDatabase.getText());
            prop.setProperty("systemDatabase", textSystemDatabase.getText());
            prop.setProperty("sqlLink", "jdbc:firebirdsql:" + textSerwer.getText() + "/3050:" + textDatabase.getText() + "?sql_dialect=3&encoding=UTF8");
            prop.setProperty("invoices", textInvoicesPath.getText());
            prop.setProperty("kodUrzZew", textUrzZew.getText());
            prop.setProperty("magazyn", magazyn.getValue().toString());
            prop.setProperty("id_cechy_psb", textPSB.getText());


            // OPCJE FTP
//            prop.setProperty("json", textJson.getText());
//            prop.setProperty("query", textQuery.getText());
//            prop.setProperty("ftpLogin", textFtpLogin.getText());
//            prop.setProperty("ftpPassword", textFtpPassword.getText());
//            prop.setProperty("ftpServer", textFtpServer.getText());
//            prop.setProperty("ftpPort", textFtpPort.getText());
//            prop.setProperty("ftpPath", textFtpPath.getText());
//            prop.setProperty("ftpJsonFile", textFtpJsonFile.getText());
//            prop.setProperty("autosending", String.valueOf(choose));
            // save properties to project root folder
            prop.storeToXML(output, null);
            //    JOptionPane.showMessageDialog(null, "Zapisano");
            try {
                Settings.loadSettings();
                //       JOptionPane.showMessageDialog(null,"ok");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.fillInStackTrace());
                ex.printStackTrace();
            }
            loadS();

            Stage stage = (Stage) buttonSave.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            logger.debug(ex.fillInStackTrace());
            Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisu ustawień !\n" +
                    "\"Sprawdź poprawność wprowadzonych danych !\"", Alert.AlertType.ERROR));


        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    logger.debug(e.fillInStackTrace());
                    Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisu ustawień !\n" +
                            "Sprawdź poprawność wprowadzonych danych !", Alert.AlertType.ERROR));

                }
            }
        }
    }

    private void savePath(String path) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("path.properties");

            // set the properties value
            prop.setProperty("lastPath", path);

            // OPCJE FTP
//            prop.setProperty("json", textJson.getText());
//            prop.setProperty("query", textQuery.getText());
//            prop.setProperty("ftpLogin", textFtpLogin.getText());
//            prop.setProperty("ftpPassword", textFtpPassword.getText());
//            prop.setProperty("ftpServer", textFtpServer.getText());
//            prop.setProperty("ftpPort", textFtpPort.getText());
//            prop.setProperty("ftpPath", textFtpPath.getText());
//            prop.setProperty("ftpJsonFile", textFtpJsonFile.getText());
//            prop.setProperty("autosending", String.valueOf(choose));
            // save properties to project root folder
            prop.storeToXML(output, null);
            Settings.loadSettings();
            loadS();

            Stage stage = (Stage) buttonSave.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisu ustawień !\n" +
                    "\"Sprawdź poprawność wprowadzonych danych !\"", Alert.AlertType.ERROR));


        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisu ustawień !\n" +
                            "Sprawdź poprawność wprowadzonych danych !", Alert.AlertType.ERROR));

                }
            }
        }
    }

/*    private void docChoose() {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Wskaż katalog z dokumentacją kartotek");
      //  FileChooser.ExtensionFilter extFilter1 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fb");
//        FileChooser.ExtensionFilter extFilter2 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fdb");
//        FileChooser.ExtensionFilter extFilter3 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.db");
//        FileChooser.ExtensionFilter extFilter4 =
//                new FileChooser.ExtensionFilter("Wszystkie pliki", "*.*");

//                directoryChooser.getExtensionFilters().add(extFilter1);
//        directoryChooser.getExtensionFilters().add(extFilter2);
//        fileChooser.getExtensionFilters().add(extFilter3);
//        fileChooser.getExtensionFilters().add(extFilter4);
        File file = directoryChooser.showDialog(buttonDocChoose.getScene().getWindow());
                //fileChooser.showOpenDialog(buttonDatabaseChoose.getScene().getWindow());
        if (file != null) {
            textDoc.setText(file.getPath());

        }

    }

    private void PicturesPathSetting() {

        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Wskaż katalog z ze zdjęciami kartotek");
        //  FileChooser.ExtensionFilter extFilter1 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fb");
//        FileChooser.ExtensionFilter extFilter2 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fdb");
//        FileChooser.ExtensionFilter extFilter3 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.db");
//        FileChooser.ExtensionFilter extFilter4 =
//                new FileChooser.ExtensionFilter("Wszystkie pliki", "*.*");

//                directoryChooser.getExtensionFilters().add(extFilter1);
//        directoryChooser.getExtensionFilters().add(extFilter2);
//        fileChooser.getExtensionFilters().add(extFilter3);
//        fileChooser.getExtensionFilters().add(extFilter4);
        File file = directoryChooser.showDialog(buttonPictureChoose.getScene().getWindow());
        //fileChooser.showOpenDialog(buttonDatabaseChoose.getScene().getWindow());
        if (file != null) {
            textPicture.setText(file.getPath());

        }
    }  */


    private void databaseChoose() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wskaż lokalizację bazy danych");
        FileChooser.ExtensionFilter extFilter1 =
                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fb");
        FileChooser.ExtensionFilter extFilter2 =
                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fdb");
        FileChooser.ExtensionFilter extFilter3 =
                new FileChooser.ExtensionFilter("Plik bazy danych", "*.db");
        FileChooser.ExtensionFilter extFilter4 =
                new FileChooser.ExtensionFilter("Wszystkie pliki", "*.*");

        fileChooser.getExtensionFilters().add(extFilter1);
        fileChooser.getExtensionFilters().add(extFilter2);
        fileChooser.getExtensionFilters().add(extFilter3);
        fileChooser.getExtensionFilters().add(extFilter4);
        File file = fileChooser.showOpenDialog(buttonDatabaseChoose.getScene().getWindow());
        if (file != null) {
            textDatabase.setText(file.getPath());

        }

    }

    private void docChoose() {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Wskaż katalog z plikami faktur / PZ");
        //  FileChooser.ExtensionFilter extFilter1 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fb");
//        FileChooser.ExtensionFilter extFilter2 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fdb");
//        FileChooser.ExtensionFilter extFilter3 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.db");
//        FileChooser.ExtensionFilter extFilter4 =
//                new FileChooser.ExtensionFilter("Wszystkie pliki", "*.*");

//                directoryChooser.getExtensionFilters().add(extFilter1);
//        directoryChooser.getExtensionFilters().add(extFilter2);
//        fileChooser.getExtensionFilters().add(extFilter3);
//        fileChooser.getExtensionFilters().add(extFilter4);
        File file = directoryChooser.showDialog(buttonInvoicesPath.getScene().getWindow());
        //fileChooser.showOpenDialog(buttonDatabaseChoose.getScene().getWindow());
        if (file != null) {
            textInvoicesPath.setText(file.getPath() + "\\");

        }

//    private void SystemDatabaseChoose() {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Wskaż lokalizację bazy systemowej");
//        FileChooser.ExtensionFilter extFilter1 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fb");
//        FileChooser.ExtensionFilter extFilter2 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.fdb");
//        FileChooser.ExtensionFilter extFilter3 =
//                new FileChooser.ExtensionFilter("Plik bazy danych", "*.db");
//        FileChooser.ExtensionFilter extFilter4 =
//                new FileChooser.ExtensionFilter("Wszystkie pliki", "*.*");
//
//        fileChooser.getExtensionFilters().add(extFilter1);
//        fileChooser.getExtensionFilters().add(extFilter2);
//        fileChooser.getExtensionFilters().add(extFilter3);
//        fileChooser.getExtensionFilters().add(extFilter4);
//        File file = fileChooser.showOpenDialog(buttonSystemDatabaseChoose.getScene().getWindow());
//        if (file != null) {
//            textSystemDatabase.setText(file.getPath());
//
//        }
//    }


 /*   private void fileChooseOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wskaż lokalizację dla pliku JSON");

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Plik JSON (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(buttonFileChoose.getScene().getWindow());
        if (file != null) {
            textJson.setText(file.getPath());

        }
    }  */


    }
}



