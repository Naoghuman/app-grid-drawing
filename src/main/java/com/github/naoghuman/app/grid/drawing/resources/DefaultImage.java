/**
 * Copyright (C) 2019 - 2019 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.app.grid.drawing.resources;

import com.github.naoghuman.app.grid.drawing.validator.DefaultGridDrawingValidator;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * TODO
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */
public final class DefaultImage {
    
    private static final int HEIGHT = 270;
    private static final int WIDTH  = 480;
    
    private WritableImage writableImage = null;
    
    private void initialize() {
        LoggerFacade.getDefault().info(ImageReader.class, "DefaultImage#initialize()"); // NOI18N
        
        final Canvas          canvas = new Canvas(WIDTH, HEIGHT);
        final GraphicsContext gc     = canvas.getGraphicsContext2D();
        
        // Background
//        gc.setFill(Color.BLANCHEDALMOND);
//        gc.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Checker
        gc.setFill(Color.color(
                Color.LIGHTGREY.getRed(),
                Color.LIGHTGREY.getGreen(),
                Color.LIGHTGREY.getBlue(),
                0.25d));
        
        int x2;
        for (int x = 32; x < WIDTH; x+=32) {
            for (int y = 16; y < HEIGHT - 16; y+=16) {
                x2 = (y / 16 % 2 == 0) ? x : x - 16;
                gc.fillRect(x2, y, 16, 16);
            }
        }
        
        gc.setFill(Color.color(
                Color.WHITE.getRed(),
                Color.WHITE.getGreen(),
                Color.WHITE.getBlue(),
                0.5d));
        for (int x = 32; x < WIDTH; x+=32) {
            for (int y = 16; y < HEIGHT - 16; y+=16) {
                x2 = (y / 16 % 2 == 0) ? x - 16 : x;
                gc.fillRect(x2, y, 16, 16);
            }
        }

        // Border
        gc.setStroke(Color.RED);
        gc.setLineWidth(32);
        gc.strokeRect(0, 0, WIDTH, HEIGHT);
        
        // Text
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 48.0d)); // NOI18N
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText("No Image", // NOI18N
                Math.round(canvas.getWidth() / 2),
                Math.round(canvas.getHeight() / 2) - 25);

        final SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        writableImage = canvas.snapshot(snapshotParameters, new WritableImage(WIDTH, HEIGHT));
    }

    public Image getImage() {
        LoggerFacade.getDefault().debug(ImageReader.class, "DefaultImage#getImage(): Image"); // NOI18N
        
        if (writableImage == null) {
            this.initialize();
        }
        
        return writableImage;
    }
    
    public void write(final String msg) {
        LoggerFacade.getDefault().debug(ImageReader.class, "DefaultImage#write(String)"); // NOI18N
        
        DefaultGridDrawingValidator.requireNonNull(msg);
        
        if (writableImage == null) {
            this.initialize();
        }
        
    }
    
}