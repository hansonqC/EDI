package pl.hansonq;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import pl.hansonq.utils.Settings;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        Settings.loadSettings();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
        primaryStage.setTitle("EDI INTER-ELEKTRO");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));//("file:logo.png"));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root,500,570);
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
        primaryStage.show();


    }

    public static void main(String[] args) {

        launch(args);

    }
}