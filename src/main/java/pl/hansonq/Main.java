package pl.hansonq;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import pl.hansonq.utils.Preferences;
import pl.hansonq.utils.Settings;

import javax.swing.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


//        Settings.loadSettings();
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
//        primaryStage.setTitle("EDI RAJMAN");
//        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));//("file:logo.png"));
//        primaryStage.setResizable(false);
//        primaryStage.initStyle(StageStyle.DECORATED);
//        Scene scene = new Scene(root, 500, 570);
//        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
//        //scene.setUserAgentStylesheet(STY);
//        primaryStage.setScene(scene);
//        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent e) {
//                Platform.exit();
//                System.exit(0);
//
//            }
//        });
//        primaryStage.setMaximized(false);
//        // System.out.println(FirebirdConnector.getSqlLink());
//        primaryStage.show();
//


        /////  Z LOGOWANIEM


     //   Settings.loadSettings();
        Preferences.getPreferences();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("EDI RAJMAN");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));//("file:logo.png"));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root, 280, 154);
        //     scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
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


//        public void start(Stage theStage)
//        {
//            theStage.setTitle( "Timeline Example" );
//
//            Group root = new Group();
//            Scene theScene = new Scene( root );
//            theStage.setScene( theScene );
//
//            Canvas canvas = new Canvas( 512, 512 );
//            root.getChildren().add(canvas);
//
//            GraphicsContext gc = canvas.getGraphicsContext2D();
//
//            Image earth = new Image( "earth.png" );
//            Image sun   = new Image( "sun.png" );
//            Image space = new Image( "space.png" );
//
//            final long startNanoTime = System.nanoTime();
//
//            new AnimationTimer()
//            {
//                public void handle(long currentNanoTime)
//                {
//                    double t = (currentNanoTime - startNanoTime) / 1000000000.0;
//
//                    double x = 232 + 128 * Math.cos(t);
//                    double y = 232 + 128 * Math.sin(t);
//
//                    // background image clears canvas
//                    gc.drawImage( space, 0, 0 );
//                    gc.drawImage( earth, x, y );
//                    gc.drawImage( sun, 196, 196 );
//                }
//            }.start();
//
//            theStage.show();
//        }
//
//

    }

    public static void main(String[] args) {

        launch(args);

    }
}