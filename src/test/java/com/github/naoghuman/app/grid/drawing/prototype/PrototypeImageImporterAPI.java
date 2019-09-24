package com.github.naoghuman.app.grid.drawing.prototype;

import java.io.File;

import com.github.naoghuman.app.grid.drawing.resources.ImageImporter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PrototypeImageImporterAPI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final Button openButton = new Button("Import");
        openButton.setOnAction((event) -> {
            final File  file  = ImageImporter.getDefault().load(primaryStage);
            final Image image = ImageImporter.getDefault().read(file);
            ImageImporter.getDefault().write(primaryStage, file.getName(), image);
        });
 
        StackPane root = new StackPane();
        root.getChildren().add(openButton);
         
        Scene scene = new Scene(root, 400, 150);
         
        primaryStage.setTitle("Image Import");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
