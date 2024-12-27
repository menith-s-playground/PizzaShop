package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

public class SeasonalSpecialsController {

    @FXML
    private ComboBox<String> discountTypeCombo;
    @FXML
    private TextField discountAmountField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private VBox specialsListContainer;

    @FXML
    private void saveSpecial(ActionEvent event) {
        String specialName = "Summer Special";
        String discountType = discountTypeCombo.getValue();
        String discountAmount = discountAmountField.getText();
        String startDate = startDatePicker.getValue().toString();
        String endDate = endDatePicker.getValue().toString();

        System.out.println("Saving Special:");
        System.out.println("Name: " + specialName);
        System.out.println("Discount Type: " + discountType);
        System.out.println("Amount: " + discountAmount);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);

        Label newSpecialLabel = new Label(specialName + " (" + discountType + "): " + discountAmount + " from " + startDate + " to " + endDate);
        specialsListContainer.getChildren().add(newSpecialLabel);
    }

    @FXML
    private void cancelSpecial(MouseEvent event) {
        discountTypeCombo.setValue(null);
        discountAmountField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
    }
}
