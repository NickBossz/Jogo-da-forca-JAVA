package com.nick;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

public class BotaoALF {

    private char[] letters = App.getLetters();

    @FXML
    public void buttonClick(@SuppressWarnings("exports") ActionEvent event) throws IOException{
        Button buttonClicked = (Button) event.getSource();
        char letterClicked = buttonClicked.getId().toCharArray()[0];
        boolean found = false;

        for (int i = 0; i < letters.length; i++){
            if (letterClicked == letters[i]){
                found = true;
                break;
            }
        }

        if (found) {
            App.addHits();
            buttonClicked.setStyle("-fx-background-color: #00FF00;");
            App.showHits(letterClicked);
        } else {
            buttonClicked.setStyle("-fx-background-color: #FF0000;");
            App.addErrors();
            App.updateErrors();
        }

        buttonClicked.setDisable(true);

    }

}