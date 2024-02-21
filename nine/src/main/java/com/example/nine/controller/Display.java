package com.example.nine.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.File;
import java.net.URL;

public class Display {
    private static Scene scene;

    public static void setScene(Scene scene){
        Display.scene = scene;
    }
    public static void switchTo(String fileName, int width, int height) throws Exception {
        URL url = new File("src/main/java/com/example/nine/pages/" + fileName + ".fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        scene.setRoot(root);
        Application.window.setWidth(width);
        Application.window.setHeight(height);
    }

    public static Scene getScene() {
        return scene;
    }
}
