package com.cgvsu.rasterizationfxapp;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

import com.cgvsu.rasterization.*;
import javafx.scene.paint.Color;

public class RasterizationController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        Rasterization.drawEllipse(canvas.getGraphicsContext2D(), 700, 500, 5, 2, Color.BLACK);
        Rasterization.drawEllipse(canvas.getGraphicsContext2D(), 600, 200, 300, 50, Color.RED);
        Rasterization.drawEllipse(canvas.getGraphicsContext2D(), 500, 100, 30, 50, Color.BLUE);
        Rasterization.drawEllipse(canvas.getGraphicsContext2D(), 0, 0, 200, 200, Color.GRAY);
        Rasterization.drawEllipse(canvas.getGraphicsContext2D(), 400, 300, -100, -50, Color.GREEN);

    }

}