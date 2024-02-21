package com.example.nine.tutorials;

import com.example.nine.controller.Display;
import com.example.nine.controller.QuitGameAction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TutorialPopup {
    public static void display(String text)
    {
        Stage popUpWindow=new Stage();

        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Game ended");


        Label label1= new Label(text);
        label1.setStyle("-fx-font-size: 45; -fx-font-weight: bold; ");
        label1.setTextFill(Color.WHITE);


        Button button1= new Button("Back to tutorials");
        button1.setStyle("-fx-font-weight: bold; -fx-background-color: #aac6de;");

        Button button2= new Button("Exit");
        button2.setStyle("-fx-font-weight: bold; -fx-background-color: #aac6de;");

        // Go back to menu when back to menu button pressed
        button1.setOnAction(e -> {
            popUpWindow.close();
            try {
                Display.switchTo("tutorialMenu", 940, 590);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Close game when quit button pressed
        button2.setOnAction(e -> {
            popUpWindow.close();
            QuitGameAction action = new QuitGameAction();
            action.perform();
        });

        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, button1, button2);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 900, 300);

        layout.setBackground(new Background(new BackgroundFill(Color.web("#303030"), CornerRadii.EMPTY, Insets.EMPTY)));

        popUpWindow.setScene(scene1);

        // Show popup
        popUpWindow.showAndWait();

        }

}

