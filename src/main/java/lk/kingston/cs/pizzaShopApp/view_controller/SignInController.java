package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SignInController {

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSingUp;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    public void btnSignInOnAction() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        // Check if fields are empty
        if (username.isEmpty()) {
            showErrorAlert("Validation Error", "Username cannot be empty!");
            txtUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showErrorAlert("Validation Error", "Password cannot be empty!");
            txtPassword.requestFocus();
            return;
        }

        if (username.equals("admin") && password.equals("1234")) {
            Stage mainStage = new Stage();
            mainStage.setResizable(false);
            mainStage.setTitle("Main View");
            mainStage.centerOnScreen();

            try {
                URL fxmlLocation = getClass().getResource("/views/MainView.fxml");  // Ensure this path is correct
                if (fxmlLocation == null) {
                    throw new RuntimeException("MainView.fxml file not found!");
                }
                mainStage.setScene(new Scene(FXMLLoader.load(fxmlLocation)));
            } catch (IOException e) {
                showErrorAlert("Error", "Failed to load MainView.fxml!");
                throw new RuntimeException(e);
            }
            mainStage.show();

            // Close the sign-in window
            Stage currentStage = (Stage) btnSignIn.getScene().getWindow();
            currentStage.close();
        } else {
            // Show error alert for invalid credentials
            showErrorAlert("Login Failed", "Invalid username or password!");
            txtUsername.requestFocus();
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}

