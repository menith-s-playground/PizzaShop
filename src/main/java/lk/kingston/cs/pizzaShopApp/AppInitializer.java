package lk.kingston.cs.pizzaShopApp;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader splashLoader = new FXMLLoader(getClass().getResource("/views/SplashScreen.fxml"));
        Scene splashScene = new Scene(splashLoader.load());
        primaryStage.setScene(splashScene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Pizza Shop - Loading");
        primaryStage.show();
        primaryStage.centerOnScreen();

        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            try {
                FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/views/MenuView.fxml"));
                primaryStage.setScene(new Scene(mainLoader.load()));
                primaryStage.setTitle("Pizza Shop");
                primaryStage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }
}
