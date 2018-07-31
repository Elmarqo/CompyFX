package pl.mareksliwinski;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    public static String text;

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

    public List<String> compareTheSame() {
       text = "";
        Collections.sort(list1);
        Collections.sort(list2);
        List<String> resultList = new ArrayList<>();

        for (String elem : list2) {
            int index = Collections.binarySearch(list1, elem);
            if (index >= 0)
                resultList.add(elem);
        }

        if (resultList.size() != 0)
            text = "Liczba rekordów znajdujących sie w Lista i Lista 2 wynosi: " + df(resultList.size()) + "." + "\nZapisz wynik.";
        else
            text = "Liczba rekordów znajdujących sie w Lista i Lista 2 wynosi: " + df(resultList.size());
        return resultList;
    }

    public List<String> compareDiff(){
        text = "";
        Collections.sort(list1);
        Collections.sort(list2);
        List<String> resultList = new ArrayList<>();

        for (String elem : list2) {
            int index = Collections.binarySearch(list1, elem);
            if (index < 0)
                resultList.add(elem);
        }
            text = "Liczba rekordow znajdujacych sie w Lista 2, a nie znajdujacych sie w \nLista wynosi: " + df(resultList.size()) + ".  Zapisz wynik.";
        return resultList;
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
}
