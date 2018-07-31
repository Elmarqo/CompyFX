package controllers;

import com.jfoenix.controls.JFXButton;
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

    @FXML
    private Label label;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    public void exit() {
        secondaryStage.close();
    }

    @FXML
    public void save() {

    }

    @FXML
    public void initialize() {
        label.setText(operations.tekst());
    }

    public void setTextLabel(String text) {

    }
}
