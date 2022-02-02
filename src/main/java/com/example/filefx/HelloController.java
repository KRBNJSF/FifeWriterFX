package com.example.filefx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label textLabel;
    int numberWords = 0;
    public Map<String, Integer> words = new HashMap<>();

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
            System.out.println(file.getName());
            System.out.println(file.getPath());
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

                for (int i = 0; i < word.length; i++) {
                    if (words.containsKey(word[i])) {
                        words.get(word[i]);
                    } else {
                        putWord(word[i], 0);
                    }

                }

                System.out.println(words + " - ");

                System.out.println(data);
                System.out.println("----" + Arrays.toString(word) + "   " + numberWords);
            }
            sc.close();
        } else {
            textLabel.setText("No file");
        }
    }

    public void putWord(String name, int number) {
        if (words.containsKey(name)) {
            words.put(name, number);
        } else {
            //words.get(name).
        }
    }

}