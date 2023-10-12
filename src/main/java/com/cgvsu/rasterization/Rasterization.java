package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Rasterization {
    public static void drawEllipse(
            final GraphicsContext graphicsContext,
            final int x, final int y, final int a, final int b,
            Color color) {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        for (int pixelX = x - Math.abs(a); pixelX <= x + Math.abs(a); pixelX++) {
            for (int pixelY = y - Math.abs(b); pixelY <= y + Math.abs(b); pixelY++) {
                float x2 = (pixelX - x) * (pixelX - x);
                float y2 = (pixelY - y) * (pixelY - y);
                float a2 = a * a;
                float b2 = b * b;
                float xa = x2 / a2;
                float by = y2 / b2;
                if (xa + by <= 1) {
                    pixelWriter.setColor(pixelX, pixelY, color);
                }
            }
        }
    }
}
