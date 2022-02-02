package com.example.filefx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {
    public CategoryAxis wordNumber;
    public NumberAxis wordFrequency;
    public StackedBarChart wordGraph;
    @FXML
    private Label textLabel;
    int numberWords = 0;
    public Map<String, Integer> words = new HashMap<>();
    public Map<Character, Integer> characters = new HashMap<>();

    @FXML
    protected void openFileReader() {
        textLabel.setText("Choose file from directories");
        fileTry();
    }

    public void fileTry() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file");
        //fileChooser.showOpenDialog(new Stage());
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            System.out.println("File name: " + file.getName());
            System.out.println("Path to file: " + file.getPath());
            textLabel.setText("You have chosen: " + file.getName());


            Scanner sc = null;
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] word = data.split(" ");
                numberWords = word.length;

                char[] characters = data.toCharArray();
                System.out.println("Number of characters: " + characters.length);

                for (int j = 0; j < characters.length; j++) {
                    putCharacter(characters[j], 1);
                }

                for (int i = 0; i < word.length; i++) {
                    putWord(word[i], 1);
                }

                System.out.println("Used words + number: " + words);

                System.out.println("File content: " + data);
                System.out.println("Text without space: " + Arrays.toString(word) + "\nTotal number of words: " + numberWords);
                System.out.println("Characters: " + Arrays.toString(characters));
            }
            sc.close();
            calculateFrequency();
        } else {
            textLabel.setText("No file");
        }
    }

    public void putWord(String name, int number) {
        if (!words.containsKey(name)) {
            words.put(name, number);
        } else {
            Integer count = words.get(name);
            words.put(name, ++count);
        }
    }

    public void putCharacter(char character, int number) {
        if (!characters.containsKey(character)) {
            characters.put(character, number);
        } else {
            number = characters.get(character);
            characters.put(character, ++number);
        }
    }

    public void calculateFrequency() {
        for (int i = 0; i < words.size(); i++) {
            //System.out.println(words.get(i).toString());
        }
    }

    public void checkCharacters() {

    }

    public void setGraph() {
        wordGraph.setTitle("Word frequency");
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data<>(words.get("100"), 1));
        series.getData().add(new XYChart.Data<>("10", 5));

        wordGraph.getData().add(series);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGraph();
    }
}