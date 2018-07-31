package controllers;

import javafx.stage.Stage;
import pl.mareksliwinski.Main;

public class Toggle3ScreenController {

    private Main main;
    private Stage fourthStage;

    public void setMain(Main main, Stage fourthStage) {
        this.main = main;
        this.fourthStage = fourthStage;
    }

    public void exit() {
        fourthStage.close();
    }

    public void save() {

    }
}
