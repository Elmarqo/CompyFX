package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.mareksliwinski.Main;

public class Toggle1ScreenController {

    private Main main;
    private Stage secondaryStage;

    public void setMain(Main main, Stage secondaryStage) {
        this.main = main;
        this.secondaryStage = secondaryStage;
    }

    @FXML
    private Label label;

    public void setLabel(String string) {
       this.label.setText(string);
    }

    @FXML
    private JFXButton exitButton;

    @FXML
    void exit() {

    }
}
