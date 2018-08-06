package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.mareksliwinski.Main;
import pl.mareksliwinski.Operations;

public class Toggle3ScreenController {

    Operations operations = new Operations();
    private Main main;
    private Stage fourthStage;

    public void setMain(Main main, Stage fourthStage) {
        this.main = main;
        this.fourthStage = fourthStage;
    }

    public Stage getFourthStage() {
        return fourthStage;
    }

    @FXML
    private Label label;

    @FXML
    private JFXButton saveButton;


    public void exit() {
        fourthStage.close();
    }

    public void save() {
        operations.saveToFile(Operations.getResultListCompareDiff2(), getFourthStage());
    }

    public void initialize() {
        label.setText(Operations.text);
        IntegerProperty size = new SimpleIntegerProperty(Operations.getResultListCompareDiff2().size());
        saveButton.disableProperty().bind(size.isEqualTo(0));
    }
}
