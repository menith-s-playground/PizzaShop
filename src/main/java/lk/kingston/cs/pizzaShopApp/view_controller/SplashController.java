package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class SplashController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void initialize() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> progressBar.setProgress(0.1)), // 10%
                new KeyFrame(Duration.seconds(1.25), e -> progressBar.setProgress(0.4)), // 40%
                new KeyFrame(Duration.seconds(2.5), e -> progressBar.setProgress(0.8)), // 80%
                new KeyFrame(Duration.seconds(5), e -> progressBar.setProgress(1)) // 100%
        );
        timeline.setCycleCount(1);
        timeline.play();
    }
}
