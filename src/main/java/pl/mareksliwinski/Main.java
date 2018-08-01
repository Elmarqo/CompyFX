package pl.mareksliwinski;

import controllers.MainController;
import controllers.Toggle1ScreenController;
import controllers.Toggle2ScreenController;
import controllers.Toggle3ScreenController;
import javafx.application.Application;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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

        secondaryStage.setTitle(" JEST W LISTA I LISTA 2");
        secondaryStage.initOwner(primaryStage);
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.setX(500);
        secondaryStage.setY(300);
        secondaryStage.resizableProperty().set(false);
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

    public void loadToggleButton2Screen(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/toggle2Screen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane);
        Stage thirdStage = new Stage();

        Toggle2ScreenController toggle2ScreenController = loader.getController();
        toggle2ScreenController.setMain(this, thirdStage);

        thirdStage.setTitle("JEST W LISTA 2, NIE MA W LISTA");
        thirdStage.initOwner(primaryStage);
        thirdStage.initModality(Modality.WINDOW_MODAL);
        thirdStage.setX(600);
        thirdStage.setY(300);
        thirdStage.resizableProperty().set(false);
        thirdStage.setScene(scene);
        thirdStage.show();
    }

    public void loadToggleButton3Screen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/toggle3Screen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane);
        Stage fourthStage = new Stage();

        Toggle3ScreenController toggle3ScreenController = loader.getController();
        toggle3ScreenController.setMain(this, fourthStage);

        fourthStage.setTitle("JEST W LISTA, NIE MA W LISTA 2");
        fourthStage.initOwner(primaryStage);
        fourthStage.initModality(Modality.WINDOW_MODAL);
        fourthStage.setX(700);
        fourthStage.setY(300);
        fourthStage.resizableProperty().set(false);
        fourthStage.setScene(scene);
        fourthStage.show();
    }
}
