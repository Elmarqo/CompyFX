package pl.mareksliwinski;

import controllers.MainController;
import controllers.Toggle1ScreenController;
import controllers.Toggle2ScreenController;
import controllers.Toggle3ScreenController;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {

    public static final String MAIN_SCREEN = "/fxml/mainScreen.fxml";
    public static final String SECONDARY_SCREEN = "/fxml/toggle1Screen.fxml";
    public static final String THIRD_SCREEN = "/fxml/toggle2Screen.fxml";
    public static final String FOURTH_SCREEN = "/fxml/toggle3Screen.fxml";
    Operations operations = new Operations();
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
        } catch (Exception e) {
            operations.alerts(e.getMessage() + " " +  MAIN_SCREEN);
            Platform.exit();
            System.exit(0);
        }

        MainController mainController = loader.getController();
        mainController.setMain(this);

        Scene scene = new Scene(pane);
        Image icon = new Image(this.getClass().getResourceAsStream("/icons/icon.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("CompyFX");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

    public void loadToggleButton1Screen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(SECONDARY_SCREEN));
        Pane anchorPane = null;
        try {
            anchorPane = loader.load();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Scene scene = new Scene(anchorPane, 620, 400);
        Stage secondaryStage = new Stage();

        Toggle1ScreenController toggle1ScreenController = loader.getController();
        toggle1ScreenController.setMain(this, secondaryStage);

        secondaryStage.setTitle(" JEST W LISTA I LISTA 2");
        secondaryStage.initOwner(primaryStage);
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        setWindowPosition(secondaryStage, 0);
        secondaryStage.resizableProperty().set(false);
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

    public void loadToggleButton2Screen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(THIRD_SCREEN));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Scene scene = new Scene(pane);
        Stage thirdStage = new Stage();

        Toggle2ScreenController toggle2ScreenController = loader.getController();
        toggle2ScreenController.setMain(this, thirdStage);

        thirdStage.setTitle("JEST W LISTA 2, NIE MA W LISTA");
        thirdStage.initOwner(primaryStage);
        thirdStage.initModality(Modality.WINDOW_MODAL);
        setWindowPosition(thirdStage, 130);
        thirdStage.resizableProperty().set(false);
        thirdStage.setScene(scene);
        thirdStage.show();
    }

    public void loadToggleButton3Screen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FOURTH_SCREEN));
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
        setWindowPosition(fourthStage, 260);
        fourthStage.resizableProperty().set(false);
        fourthStage.setScene(scene);
        fourthStage.show();
    }

    public void setWindowPosition(Stage stage, int xPosition) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        double width = dimension.getWidth();
        double height = dimension.getHeight();
        stage.setX((width / 4) + xPosition);
        stage.setY((height / 4));
    }
}
