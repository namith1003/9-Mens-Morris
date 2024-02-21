package com.example.nine.controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {

    static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // create the window for the application
        window = primaryStage;
        Scene scene = new Scene(new Pane());
        Display.setScene(scene);

        // application creates the menu
        Menu menu = new Menu();
        menu.showMenu();

        // set applications title and settings
        window.setTitle("9 Man's Morris");
        window.setScene(scene);
        window.setResizable(false);
        window.getIcons().add(new Image("file:src/main/java/com/example/nine/images/icon.png"));
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}