package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    public Stage getThirdStage() {
        return thirdStage;
    }

    @FXML
    private Label label;

    @FXML
    private JFXButton saveButton;

    @FXML
    public void exit() {
        thirdStage.close();
    }

    @FXML
    public void save() {
        operations.saveToFile(Operations.getResultListCompareDiff(), getThirdStage());
    }

    @FXML
    public void initialize() {
        label.setText(Operations.text);
        IntegerProperty size = new SimpleIntegerProperty(Operations.getResultListCompareDiff().size());
        saveButton.disableProperty().bind(size.isEqualTo(0));
    }
}
