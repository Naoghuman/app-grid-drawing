/*
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
package com.github.naoghuman.app.grid.drawing.prototype;

import com.github.naoghuman.app.grid.drawing.resources.DefaultImage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Prototype to import an image and save it automatically into:
 *  - /application/resources/images
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */

public class PrototypeDefaultImage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        ImageView imageView = new ImageView();
        DefaultImage defaultImage = new DefaultImage();
        imageView.setImage(defaultImage.getImage());
        
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color:LIGHTGREEN;");
        root.getChildren().add(imageView);
         
        Scene scene = new Scene(root, 1280, 720);
         
        primaryStage.setTitle("DefaultImage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
