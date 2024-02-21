package com.example.nine.controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class EndGamePopup {
    public static String winningColor;
    public static void display()
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Game ended");

        Label label1;

        if (winningColor != null){
            label1= new Label(winningColor.toUpperCase() + " " + "WINS");
        } else {
            label1= new Label("Game Is A Draw From Repetition!");
        }

        label1.setStyle("-fx-font-size: 45; -fx-font-weight: bold; ");
        label1.setTextFill(Color.WHITE);


        Button button1= new Button("Back to Menu");
        button1.setStyle("-fx-font-weight: bold; -fx-background-color: #aac6de;");

        Button button2= new Button("Exit");
        button2.setStyle("-fx-font-weight: bold; -fx-background-color: #aac6de;");

        // Go back to menu when back to menu button pressed
        button1.setOnAction(e -> {
                popupwindow.close();
            try {
                Display.switchTo("menu", 940, 590);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Close game when quit button pressed
        button2.setOnAction(e -> {
            popupwindow.close();
            QuitGameAction action = new QuitGameAction();
            action.perform();
        });

        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, button1, button2);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 900, 300);

        layout.setBackground(new Background(new BackgroundFill(Color.web("#303030"), CornerRadii.EMPTY, Insets.EMPTY)));

        popupwindow.setScene(scene1);

        // Show popup
        popupwindow.showAndWait();

    }

    public static void setWinningColor(String color){
        winningColor = color;
    }

}
