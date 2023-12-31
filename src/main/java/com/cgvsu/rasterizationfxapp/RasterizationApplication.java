package com.cgvsu.rasterizationfxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class RasterizationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RasterizationApplication.class.getResource("mainwindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Rasterization App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Color clr = Color.rgb(2, 2, 2);

        //launch();
        System.out.println(0.1 + 0.2 == 0.3);
    }
}