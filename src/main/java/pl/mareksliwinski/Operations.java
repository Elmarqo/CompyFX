package pl.mareksliwinski;

import controllers.Toggle1ScreenController;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    Toggle1ScreenController toggle1ScreenController = new Toggle1ScreenController();
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();

    public List<String> getList1() {
        return list1;
    }

    public List<String> getList2() {
        return list2;
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
        Collections.sort(list1);
        Collections.sort(list2);
        List<String> resultList = new ArrayList<>();

        for (String elem : list2) {
            int index = Collections.binarySearch(list1, elem);
            if (index >= 0)
                resultList.add(elem);
        }
        System.out.println(resultList.size());
        toggle1ScreenController.setLabel(String.valueOf(resultList.size()));
    }

    public void alerts(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CompyFX");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
