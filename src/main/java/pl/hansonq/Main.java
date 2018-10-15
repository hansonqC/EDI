package pl.hansonq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.hansonq.utils.Settings;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        Settings.loadSettings();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
        primaryStage.setTitle("IMPORT KARTOTEK 3.0");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));//("file:logo.png"));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root,950,670);
       // scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
        //scene.setUserAgentStylesheet(STY);
        primaryStage.setScene(scene);
       primaryStage.setMaximized(false);
        // System.out.println(FirebirdConnector.getSqlLink());
        primaryStage.show();


    }

    public static void main(String[] args) {

        launch(args);

    }
}