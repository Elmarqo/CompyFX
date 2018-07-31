package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.mareksliwinski.Main;
import pl.mareksliwinski.Operations;


public class Toggle2ScreenController {

    Operations operations = new Operations();
    private Main main;
    private Stage thirdStage;

    public void setMain(Main main, Stage thirdStage) {
        this.main = main;
        this.thirdStage = thirdStage;
    }

    @FXML
    private Label label;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    public void exit() {
        thirdStage.close();
    }

    @FXML
    public void save() {

    }

    @FXML
    public void initialize(){
        label.setText(operations.tekst());
    }
}
