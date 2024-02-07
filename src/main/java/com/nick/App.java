package com.nick;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;


public class App extends Application {

    private static Scene scene;
    private static String word;  
    private static char[] letters;
    private static Stage stage2;

    private static int hitsToWin;

    private static int hits;
    private static int errors;
    
    private static Circle cabeca;
    private static Line corpo;
    private static Line braco1;
    private static Line braco2;
    private static Line perna1;
    private static Line perna2;



    public static void addHits() throws IOException{
        hits++;

        if (hits == hitsToWin){
            Scene winnerScene = new Scene(loadFXML("ganhou"), 385, 280);
            stage2.setScene(winnerScene);
        }
    }

    public static void addErrors(){
        errors++;
    }

    private void setWord(){
        word = "olaola";
        letters = word.toCharArray();

        HashSet<Character> set = new HashSet<>();
        for (char char_ : letters) {
            set.add(char_);
        }

        char[] uniqueChars = new char[set.size()];
        int index = 0;
        for (char char__ : set) {
            uniqueChars[index++] = char__;
        }

        letters = uniqueChars;
        hitsToWin = letters.length;
    }

    public static void showHits(char charCorrect){
        for (int i = 0; i < word.toCharArray().length; i++){
            if (word.toCharArray()[i] == charCorrect){
                turnVisibleLettersOfLines(i + 1, charCorrect);
            }
        }
    }

    public static void updateErrors() throws IOException{
        switch (errors) {
            case 0:
                cabeca.setOpacity(0);
                corpo.setOpacity(0);
                braco1.setOpacity(0);
                braco2.setOpacity(0);
                perna1.setOpacity(0);
                perna2.setOpacity(0);

                break;

            case 1:
                cabeca.setOpacity(1);
                corpo.setOpacity(0);
                braco1.setOpacity(0);
                braco2.setOpacity(0);
                perna1.setOpacity(0);
                perna2.setOpacity(0);
                break;
            
            case 2:
                cabeca.setOpacity(1);
                corpo.setOpacity(1);
                braco1.setOpacity(0);
                braco2.setOpacity(0);
                perna1.setOpacity(0);
                perna2.setOpacity(0);
                break;
            
            case 3:
                cabeca.setOpacity(1);
                corpo.setOpacity(1);
                braco1.setOpacity(1);
                braco2.setOpacity(0);
                perna1.setOpacity(0);
                perna2.setOpacity(0);
                break;
            
            case 4:
                cabeca.setOpacity(1);
                corpo.setOpacity(1);
                braco1.setOpacity(1);
                braco2.setOpacity(1);
                perna1.setOpacity(0);
                perna2.setOpacity(0);
                break;

            case 5:
                cabeca.setOpacity(1);
                corpo.setOpacity(1);
                braco1.setOpacity(1);
                braco2.setOpacity(1);
                perna1.setOpacity(1);
                perna2.setOpacity(0);
                break;
            
            case 6:
                cabeca.setOpacity(1);
                corpo.setOpacity(1);
                braco1.setOpacity(1);
                braco2.setOpacity(1);
                perna1.setOpacity(1);
                perna2.setOpacity(1);

                Scene loserScene = new Scene(loadFXML("perdeu"), 385, 280);
                stage2.setScene(loserScene);

                for (javafx.scene.Node node : loserScene.getRoot().getChildrenUnmodifiable()) {
                    if (node instanceof Text) {
                        if (node.getId() == null) {continue;}
                        if (node.getId().equalsIgnoreCase("era")){
                            Text texto = (Text) node;
                            texto.setText("A palavra era " + word + "!");
                        }
                    }
                }

                break;
        }
    }

    private Node collectPartOfDummy(String id){
        for (javafx.scene.Node node : scene.getRoot().getChildrenUnmodifiable()) {
            if (node instanceof Line || node instanceof Circle) {
                if (node.getId().equalsIgnoreCase(id)){
                    return node;
                }
            }
        }
        return null;
    }

    private void turnVisibleLinesOfLetters(int lineNumber){
        for (javafx.scene.Node node : scene.getRoot().getChildrenUnmodifiable()) {
            if (node instanceof Line) {
                if (node.getId() == null) {continue;}
                if (node.getId().equalsIgnoreCase("l" + lineNumber)){
                    node.setOpacity(1);
                }
            }
        }
    }

    private static void turnVisibleLettersOfLines(int letterNumber, char letter){
        for (javafx.scene.Node node : scene.getRoot().getChildrenUnmodifiable()) {
            if (node instanceof Text) {
                Text text = (Text) node;
                if (text.getId() == null) {continue;}
                if (text.getId().equalsIgnoreCase("a" + letterNumber)){
                    text.setText(String.valueOf(letter).toUpperCase());
                    text.setOpacity(1);
                }
            }
        }
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        setWord(); 

        hitsToWin = letters.length;
        scene = new Scene(loadFXML("jogo"), 640, 480);
        stage.setScene(scene);
        stage.show();
        stage2 = stage;

        cabeca = (Circle) collectPartOfDummy("cabeca");
        corpo = (Line) collectPartOfDummy("corpo");
        perna1 = (Line) collectPartOfDummy("perna1");
        perna2 = (Line) collectPartOfDummy("perna2");
        braco1 = (Line) collectPartOfDummy("braco1");
        braco2 = (Line) collectPartOfDummy("braco2");

        for (int i = 0; i < word.toCharArray().length; i++){
            turnVisibleLinesOfLetters(i + 1);
        }

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static String getWord(){
        return word;
    }

    public static char[] getLetters(){
        return letters;
    }
    public static void main(String[] args) {
        launch();
    }

}