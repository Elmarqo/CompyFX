package pl.mareksliwinski;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Operations {

    private List<String> list1;
    private List<String> list2;
    private static List<String> resultListCompareTheSame;
    private static List<String> resultListCompareDiff;
    private static List<String> resultListCompareDiff2;
    public static String text;

    public Operations() {
        this.list1 = new ArrayList<>();
        this.list2 = new ArrayList<>();
        Operations.resultListCompareTheSame = new ArrayList<>();
        Operations.resultListCompareDiff = new ArrayList<>();
        Operations.resultListCompareDiff2 = new ArrayList<>();
    }

    public List<String> getList1() {
        return list1;
    }

    public void setList1(List<String> list1) {
        this.list1 = list1;
    }

    public List<String> getList2() {
        return list2;
    }

    public void setList2(List<String> list2) {
        this.list2 = list2;
    }

    public static List<String> getResultListCompareTheSame() {
        return resultListCompareTheSame;
    }

    public static void setResultListCompareTheSame(String elem) {
        Operations.resultListCompareTheSame.add(elem);
    }

    public static List<String> getResultListCompareDiff() {
        return resultListCompareDiff;
    }

    public static void setResultListCompareDiff(String elem) {
        Operations.resultListCompareDiff.add(elem);
    }

    public static List<String> getResultListCompareDiff2() {
        return resultListCompareDiff2;
    }

    public static void setResultListCompareDiff2(String elem) {
        Operations.resultListCompareDiff2.add(elem);
    }

    public void loadList(File selectedFile, int listNumber) {
        if (getList1() == null)
            setList1(list1);
        else if (getList2() == null)
            setList2(list2);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (listNumber == 0)
                    getList1().add(line);
                else
                    getList2().add(line);
            }
        } catch (Exception e) {
            alerts(e.getMessage());
            Platform.exit();
            System.exit(0);
        }
    }

    public void compareTheSame() {
        Collections.sort(getList1());
        Collections.sort(getList2());
        getResultListCompareTheSame().clear();

        for (String elem : getList2()) {
            int index = Collections.binarySearch(getList1(), elem);
            if (index >= 0)
                setResultListCompareTheSame(elem);
        }

        if (getResultListCompareTheSame().size() != 0)
            text = "Liczba rekordów znajdujących sie w Lista i Lista 2, wynosi: " + df(getResultListCompareTheSame().size()) + "." + "\nZapisz wynik.";
        else
            text = "Liczba rekordów znajdujących sie w Lista i Lista 2, wynosi: " + df(getResultListCompareTheSame().size());
    }

    public void compareDiff() {
        Collections.sort(getList1());
        Collections.sort(getList2());
        getResultListCompareDiff().clear();

        for (String elem : getList2()) {
            int index = Collections.binarySearch(getList1(), elem);
            if (index < 0)
                setResultListCompareDiff(elem);
        }
        if (getResultListCompareDiff().size() != 0)
            text = "Liczba rekordow znajdujacych sie w Lista 2, a nie znajdujacych sie w \nLista, wynosi: " + df(getResultListCompareDiff().size()) + ".  Zapisz wynik.";
        else
            text = "Liczba rekordow znajdujacych sie w Lista 2, a nie znajdujacych sie w \nLista, wynosi: " + df(getResultListCompareDiff().size());
    }

    public void compareDiff2() {
        Collections.sort(getList1());
        Collections.sort(getList2());
        getResultListCompareDiff2().clear();

        for (String elem : getList1()) {
            int index = Collections.binarySearch(getList2(), elem);
            if (index < 0)
                setResultListCompareDiff2(elem);
        }
        if (getResultListCompareDiff2().size() != 0)
            text = "Liczba rekordow znajdujacych sie w Lista, a nie znajdujacych sie w Lista 2, \nwynosi: " + df(getResultListCompareDiff2().size()) + ".  Zapisz wynik.";
        else
            text = "Liczba rekordow znajdujacych sie w Lista, a nie znajdujacych sie w Lista 2, \nwynosi: " + df(getResultListCompareDiff2().size());
    }

    String df(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        return decimalFormat.format(number);
    }

    public void saveToFile(List<String> list, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki txt", "*.txt"));
        File savedFile = fileChooser.showSaveDialog(null);
        int counter = 0;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(savedFile))) {
            for (String elem : list) {
                bufferedWriter.write(elem + "\n");
                counter++;
            }
        } catch (Exception e) {
            dialogAlert(stage, "Rezygnujesz z zapisu danych do pliku?");
        }
        if (counter > 0)
            stage.close();
    }

    public void alerts(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CompyFX");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void infoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CompyFX");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void dialogAlert(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CompyFX");
        alert.setHeaderText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            stage.close();
    }
}
