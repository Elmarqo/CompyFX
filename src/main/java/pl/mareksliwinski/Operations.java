package pl.mareksliwinski;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private static List<String> resultListCompareTheSame = new ArrayList<>();
    private static List<String> resultListCompareDiff = new ArrayList<>();
    private static List<String> resultListCompareDiff2 = new ArrayList<>();
    public static String text;

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

    public void loadList(File file, int listNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (listNumber == 0)
                    list1.add(line);
                else
                    list2.add(line);
            }
        } catch (Exception e) {
            alerts(e.getMessage());
            Platform.exit();
            System.exit(0);
        }
    }

    public void compareTheSame() {
        text = "";
        Collections.sort(list1);
        Collections.sort(list2);

        for (String elem : list2) {
            int index = Collections.binarySearch(list1, elem);
            if (index >= 0)
                setResultListCompareTheSame(elem);
        }

        if (getResultListCompareTheSame().size() != 0)
            text = "Liczba rekordów znajdujących sie w Lista i Lista 2, wynosi: " + df(getResultListCompareTheSame().size()) + "." + "\nZapisz wynik.";
        else
            text = "Liczba rekordów znajdujących sie w Lista i Lista 2, wynosi: " + df(getResultListCompareTheSame().size());
    }

    public void compareDiff() {
        text = "";
        Collections.sort(list1);
        Collections.sort(list2);

        for (String elem : list2) {
            int index = Collections.binarySearch(list1, elem);
            if (index < 0)
                setResultListCompareDiff(elem);
        }
        if (getResultListCompareDiff().size() != 0)
            text = "Liczba rekordow znajdujacych sie w Lista 2, a nie znajdujacych sie w \nLista, wynosi: " + df(getResultListCompareDiff().size()) + ".  Zapisz wynik.";
        else
            text = "Liczba rekordow znajdujacych sie w Lista 2, a nie znajdujacych sie w \nLista, wynosi: " + df(getResultListCompareDiff().size());
    }

    public void compareDiff2() {
        text = "";
        Collections.sort(list2);
        Collections.sort(list1);

        for (String elem : list1) {
            int index = Collections.binarySearch(list2, elem);
            if (index < 0)
                setResultListCompareDiff2(elem);
        }
        if (getResultListCompareDiff2().size() != 0)
            text = "Liczba rekordow znajdujacych sie w Lista, a nie znajdujacych sie w \nLista 2, wynosi: " + df(getResultListCompareDiff2().size()) + ".  Zapisz wynik.";
        else
            text = "Liczba rekordow znajdujacych sie w Lista, a nie znajdujacych sie w \nLista 2, wynosi: " + df(getResultListCompareDiff2().size());
    }

    public String tekst() {
        return text;
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

    String df(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        return decimalFormat.format(number);
    }

    public void saveToFile(List<String> list) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki txt", "*.txt"));
        File savedFile = fileChooser.showSaveDialog(null);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(savedFile))) {
            for (String elem : list) {
                bufferedWriter.write(elem + "\n");
            }
        } catch (Exception e) {
            infoAlert(e.getMessage());
        }
    }
}
