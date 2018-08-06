package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import pl.mareksliwinski.Main;
import pl.mareksliwinski.Operations;


public class Toggle1ScreenController {

    Operations operations = new Operations();
    private Main main;
    private Stage secondaryStage;

    public void setMain(Main main, Stage secondaryStage) {
        this.main = main;
        this.secondaryStage = secondaryStage;
    }

    public Stage getSecondaryStage() {
        return secondaryStage;
    }

    @FXML
    private Label label;

    @FXML
    private JFXButton saveButton;

    @FXML
    public void exit() {
        secondaryStage.close();
    }

    @FXML
    public void save() {
        operations.saveToFile(Operations.getResultListCompareTheSame(), getSecondaryStage());
    }

    @FXML
    public void initialize() {
        label.setText(Operations.text);
        IntegerProperty size = new SimpleIntegerProperty(Operations.getResultListCompareTheSame().size());
        saveButton.disableProperty().bind(size.isEqualTo(0));
    }
}
