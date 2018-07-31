package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import pl.mareksliwinski.Main;
import pl.mareksliwinski.Operations;

import java.io.File;

public class MainController {

    Operations operations = new Operations();
    Toggle1ScreenController toggle1ScreenController = new Toggle1ScreenController();

    private Main main;

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private JFXButton lista1Button;

    @FXML
    private JFXButton lista2Button;

    @FXML
    private JFXToggleButton toggleButton1;

    @FXML
    private JFXToggleButton toggleButton2;

    @FXML
    private JFXToggleButton toggleButton3;

    @FXML
    private JFXButton runButton;

    @FXML
    public File loadLista1() {
        int list = 0;
        lista1Button.setOnMouseEntered(event -> lista1Button.disarm());
        lista1Button.setOnMouseExited(event -> lista1Button.disarm());
        return getListFile(list);
    }

    @FXML
    public File loadLista2() {
        int list = 1;
        lista2Button.setOnMouseEntered(event -> lista2Button.disarm());
        lista2Button.setOnMouseExited(event -> lista2Button.disarm());
        return getListFile(list);
    }

    public void runToggle1() {
        if (toggleButton1.isSelected()) {
            operations.compareTheSame();
            main.loadToggleButton1Screen();
        }
    }

    public void runToggle2() {
        if (toggleButton2.isSelected()) {
            operations.compareDiff();
            main.loadToggleButton2Screen();
        }
    }

    @FXML
    public void run() {
        runToggle1();
        runToggle2();
    }

    @FXML
    public void exit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void initialize() {
        list1ChangeText();
        list1backToPrimatyText();
        list2ChangeText();
        list2backToPrimatyText();
        toggleButton1ChangeTextColor();
        toggleButton2ChangeTextColor();
        toggleButton3ChangeTextColor();
        enableRunButton();
    }

    public void toggleButton1ChangeTextColor() {
        toggleButton1.selectedProperty().addListener((Observable, oldValue, newValue) -> {
            if (newValue)
                toggleButton1.setTextFill(Color.valueOf("1EC38C"));
            else
                toggleButton1.setTextFill(Color.valueOf("CE584A"));
        });
    }

    public void toggleButton2ChangeTextColor() {
        toggleButton2.selectedProperty().addListener((Observable, oldValue, newValue) -> {
            if (newValue)
                toggleButton2.setTextFill(Color.valueOf("1EC38C"));
            else
                toggleButton2.setTextFill(Color.valueOf("CE584A"));
        });
    }

    public void toggleButton3ChangeTextColor() {
        toggleButton3.selectedProperty().addListener((Observable, oldValue, newValue) -> {
            if (newValue)
                toggleButton3.setTextFill(Color.valueOf("1EC38C"));
            else
                toggleButton3.setTextFill(Color.valueOf("CE584A"));
        });
    }

    public void list1ChangeText() {
        lista1Button.setOnMouseEntered(event -> lista1Button.setText("Wybierz" + "\nplik 1"));
        lista1Button.setTextAlignment(TextAlignment.CENTER);
    }

    public void list2ChangeText() {
        lista2Button.setOnMouseEntered(event -> lista2Button.setText("Wybierz" + "\nplik 2"));
        lista2Button.setTextAlignment(TextAlignment.CENTER);
    }

    public void list1backToPrimatyText() {
        lista1Button.setOnMouseExited(event -> lista1Button.setText("Lista1"));
    }

    public void list2backToPrimatyText() {
        lista2Button.setOnMouseExited(event -> lista2Button.setText("Lista2"));
    }

    public File getListFile(int list) {
        FileChooser[] fileChooser = new FileChooser[2];
        fileChooser[list] = new FileChooser();
        fileChooser[list].getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pliki txt", "*.txt"),
                new FileChooser.ExtensionFilter("Pliki csv", "*.csv"
                ));
        File selectedFile = fileChooser[list].showOpenDialog(null);
        try {
            if (list == 0) {
                lista1Button.setText(selectedFile.getName());
                lista1Button.setOnMouseEntered(event -> lista1Button.setText("Lista"));
                lista1Button.setOnMouseExited(event -> lista1Button.setText(selectedFile.getName()));

                lista1Button.setStyle("-fx-background-color: #B14F23");
                operations.loadList(selectedFile, list);
            } else {
                lista2Button.setText(selectedFile.getName());
                lista2Button.setOnMouseEntered(event -> lista2Button.setText("Lista 2"));
                lista2Button.setOnMouseExited(event -> lista2Button.setText(selectedFile.getName()));
                lista2Button.setStyle("-fx-background-color: #B14F23");
                operations.loadList(selectedFile, list);
            }
        } catch (Exception e) {
            operations.infoAlert("Żaden plik nie został wybrany.");
        }

        return selectedFile;
    }

    public void enableRunButton() {
        BooleanBinding accessToRunButton = lista1Button.styleProperty().isNotEqualTo("-fx-background-color: #B14F23").or
                (lista2Button.styleProperty().isNotEqualTo("-fx-background-color: #B14F23").or(toggleButton1.selectedProperty().not().and
                        (toggleButton2.selectedProperty().not().and(toggleButton3.selectedProperty().not()))));

        runButton.disableProperty().bind(accessToRunButton);
    }
}
