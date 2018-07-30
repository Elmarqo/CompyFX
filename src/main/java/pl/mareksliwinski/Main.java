package pl.mareksliwinski;

import controllers.MainController;
import controllers.Toggle1ScreenController;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static final String MAIN_SCREEN = "/fxml/mainScreen.fxml";
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        loadMainScreen();
    }

    public void loadMainScreen() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(MAIN_SCREEN));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainController mainController = loader.getController();

        mainController.setMain(this);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("CompyFX");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.resizableProperty().setValue(true);
        primaryStage.show();
    }

    public void loadToggleButton1Screen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/toggle1Screen.fxml"));
        Pane anchorPane = null;
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(anchorPane);
        Stage secondaryStage = new Stage();
        Toggle1ScreenController toggle1ScreenController = loader.getController();
        toggle1ScreenController.setMain(this, secondaryStage);
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }
}
