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
                // Делаем проверку по формуле эллипса (лежит ли точка в эллипсе)
                float x2 = (pixelX - x) * (pixelX - x);
                float y2 = (pixelY - y) * (pixelY - y);
                float a2 = a * a;
                float b2 = b * b;
                float xa = x2 / a2;
                float by = y2 / b2;
                if (xa + by <= 1) {
                    // закрашиваю
                    pixelWriter.setColor(pixelX, pixelY, interpolation(color, pixelX, pixelY, x, y));
                }
            }
        }
    }

    public static Color interpolation(final Color color,
                                      final float pixelX, final float pixelY,
                                      final float x, final float y) {
        // Перевожу цвета в RGB для удобства работы
        int redRGB = (int) (color.getRed()*255);
        int greenRGB = (int) (color.getGreen()*255);
        int blueRGB = (int) (color.getBlue()*255);
        // Нахожу путь от центра эллипса до текущей точки
        int way = (int) (Math.pow(Math.pow(pixelX - x, 2) + Math.pow(pixelY - y, 2), 0.5));
        // Высчитываю цвет исходя из расстояния
        return Gradient(way, redRGB, greenRGB, blueRGB);
    }

    private static Color Gradient(final int way, final int redRGB, final int greenRGB, final int blueRGB ) {
        double minRGB = Math.min(Math.min(redRGB, greenRGB), blueRGB);
        double maxRGB = Math.max(Math.max(redRGB, greenRGB), blueRGB);
        double myRedRGB = redRGB;
        double myGreenRGB = greenRGB;
        double myBlueRGB = blueRGB;
        // Проверяем, в каком канале цвета мы находимся
        for (int i = 0; i <= way; i++) {
            if (equals(maxRGB, myRedRGB) && !equals(maxRGB, myGreenRGB)) {
                if (!equals(minRGB, myBlueRGB)) {
                    myBlueRGB--;
                } else {
                    myGreenRGB++;
                }
            } else if (equals(maxRGB, myGreenRGB) && !equals(maxRGB, myBlueRGB)) {
                if (!equals(minRGB, myRedRGB)) {
                    myRedRGB--;
                } else {
                    myBlueRGB++;
                }
            } else if (equals(maxRGB, myBlueRGB) && !equals(maxRGB, myRedRGB)) {
                if (!equals(minRGB, myGreenRGB)) {
                    myGreenRGB--;
                } else {
                    myRedRGB++;
                }
            }
        }
        return Color.rgb((int) myRedRGB, (int) myGreenRGB, (int) myBlueRGB);
    }

    // Метод для проверки вещественных чисел
    private static boolean equals(Double a, Double b) {
        double EPS = 1/(10e3);
        if (Math.abs(a - b) < EPS) {
            return true;
        }
        return false;
    }
}
