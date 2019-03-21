package pl.hansonq.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;
import pl.hansonq.dao.Impl.UserDaoImpl;
import pl.hansonq.dao.UserDao;
import pl.hansonq.utils.Preferences;
import pl.hansonq.utils.Session;
import pl.hansonq.utils.Settings;
import pl.hansonq.utils.Utils;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private UserDao userdao = new UserDaoImpl();
    public static String uzytkownik = "";
    public static int idUzytkownik = 0;
    private Alert alert;
    final static Logger logger = Logger.getLogger(LoginController.class);
Session userSession;
    @FXML
    private TextField textLogin;

    @FXML
    private PasswordField textPassword;

    @FXML
    public Button buttonLogin, buttonCancel;
    @FXML
    private Label labelInfo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonLogin.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("ok.png"))));
        buttonCancel.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("cancel.jpg"))));
        buttonCancel.setOnMouseClicked(e -> closeApp());
          buttonLogin.setOnMouseClicked(e -> tryLogin());
        buttonLogin.requestFocus();
//        labelClose.setOnMouseClicked(e -> closeApp());
//        labelClose.setOnMouseEntered(e -> changeCursor());
//        labelClose.setOnMouseExited(e -> changeCursor2());
        labelInfo.setOnMouseEntered(e -> changeCursor());
        labelInfo.setOnMouseExited(e -> changeCursor2());
        labelInfo.setOnMouseClicked(e -> info(1));
        // labelInfo.setOnMouseExited(e -> info(0));
        textLogin.setText(Settings.getLastLogin());
        textLogin.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    tryLogin();
                }
            }
        });
        textPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {

                if (ke.getCode().equals(KeyCode.ENTER)) {
                    tryLogin();
                }
            }
        });
        Preferences.getPreferences();
        buttonLogin.requestFocus();
      //  textLogin.setText(Preferences.getPreferences().getLastLogin());
//        textLogin.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event2) {
//                if (event2.getCode().equals(KeyCode.ENTER)) {
//                    tryLogin();
//                }
//            }
//        });
        // Utils.md5Hash("Ania123");


    }

//    @FXML
//    public void onLogin() {
//        tryLogin();
//    }

    private void changeCursor2() {
        Cursor cursor = Cursor.DEFAULT;
        Scene scene = labelInfo.getScene();
        scene.setCursor(cursor);
    }

    private void info(int d) {


        if (d == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("Dane logowania są identyczne jak te,\nktórych używasz w programie\nStreamsoft Prestiż");
            alert.setHeaderText("");
            alert.show();
        }


        //   JOptionPane.showMessageDialog(null, "Dane logowania są identyczne\njak te, których używasz w programie StreamSoft Prestiż","Info",JOptionPane.INFORMATION_MESSAGE);
    }

    private void changeCursor() {
        Cursor cursor = Cursor.HAND;
        Scene scene = labelInfo.getScene();
        scene.setCursor(cursor);
    }

    private void closeApp() {
        System.exit(0);
    }

    private boolean checkLoginData() {
        String login = textLogin.getText();
        String password = textPassword.getText();

        if (login.isEmpty()) {
            Utils.createSimpleDialog("Logowanie", "", "Podaj nazwę użytkownika !", Alert.AlertType.ERROR);
        }
//        if (login.length() <= 3 || password.length() <= 5) {
//            Utils.createSimpleDialog("Logowanie", "", "Dane za krótkie !");
//            textLogin.clear();
//            textPassword.clear();
//        }
        return true;
    }

    private void tryLogin() {
      //  JOptionPane.showMessageDialog(null, "Wywołano metodę logowania");
        String login = textLogin.getText();
        String password = textPassword.getText();
        if ((password.isEmpty()) || (password == null) || (password.equals(""))) {
            password = "";
        }
//        if (!checkLoginData()) {
//            return;
//        }
        if (userdao.login(login, password)) {
         //   JOptionPane.showMessageDialog(null,"ok");
            try {
                this.uzytkownik = userdao.Uzytkownik(login, password);
              //  JOptionPane.showMessageDialog(null, "Wywołano metodę logowania  22");
            } catch (Exception ex) {
                logger.debug(ex.fillInStackTrace());
            }
                userSession=Session.getInstance();
            userSession.setUsername(login);
            userSession.setLogedIn(true);
            try {
                Stage primaryStage = new Stage();
             //   Settings.loadSettings();

                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
                primaryStage.setTitle("EDI RAJMAN");
                primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));//("file:logo.png"));
                primaryStage.setResizable(false);
                primaryStage.initStyle(StageStyle.DECORATED);
                Scene scene = new Scene(root, 500, 570);
                // scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
                //scene.setUserAgentStylesheet(STY);
                primaryStage.setScene(scene);
                primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent e) {
                        Platform.exit();
                        System.exit(0);

                    }
                });
                primaryStage.setMaximized(false);
                // System.out.println(FirebirdConnector.getSqlLink());
           //     JOptionPane.showMessageDialog(null, "Wywołano metodę logowania  33");
                primaryStage.show();
                Stage loginStage;
                loginStage = (Stage) buttonLogin.getScene().getWindow();
                loginStage.close();
            } catch (Exception ex) {
                ex.getMessage();
                logger.debug(ex.fillInStackTrace());
            }
        }
        else{
                Utils.createSimpleDialog("Logowanie", "", "Podano niepoprawne dane !", Alert.AlertType.ERROR);
                textPassword.clear();
            }
        }
    }

