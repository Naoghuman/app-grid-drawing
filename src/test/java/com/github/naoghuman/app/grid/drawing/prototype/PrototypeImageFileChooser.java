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

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

/**
 * Prototype to import an image and save it automatically into:
 *  - /application/resources/images
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */
public class PrototypeImageFileChooser extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("gif",  "*.gif"),
                new FileChooser.ExtensionFilter("jpeg", "*.jpeg"),
                new FileChooser.ExtensionFilter("jpg",  "*.jpg"),
                new FileChooser.ExtensionFilter("png",  "*.png"));
        
        
        final Button openButton = new Button("Import");
        openButton.setOnAction((event) -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                openNewImageWindow(file);
            }
        });
 
         
        StackPane root = new StackPane();
        root.getChildren().add(openButton);
         
        Scene scene = new Scene(root, 400, 150);
         
        primaryStage.setTitle("Image Import");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void openNewImageWindow(final File file) {
        Stage secondStage = new Stage();
         
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuItem_Save = new MenuItem("Save Image");
        menuFile.getItems().addAll(menuItem_Save);
        menuBar.getMenus().addAll(menuFile);
         
        Label name = new Label(file.getAbsolutePath());
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView();
         
        menuItem_Save.setOnAction((event) -> {
            File file2 = new File(System.getProperty("user.dir") + File.separator + "resources" + File.separator + "images");
            if (!file2.exists()) {
                file2.mkdirs();
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("gif",  "*.gif"),
                    new FileChooser.ExtensionFilter("jpeg", "*.jpeg"),
                    new FileChooser.ExtensionFilter("jpg",  "*.jpg"),
                    new FileChooser.ExtensionFilter("png",  "*.png"));
            fileChooser.setInitialDirectory(file2);
            fileChooser.setInitialFileName(file.getName());
            fileChooser.setTitle("Save Image");
            File file1 = fileChooser.showSaveDialog(secondStage);
            if (file1 != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), "png", file1);
                }catch (IOException ex) {
                    Logger.getLogger(
                            PrototypeImageFileChooser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
 
        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));
        vbox.getChildren().addAll(name, imageView);
         
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setSmooth(true);
        imageView.setCache(true);
         
        Scene scene = new Scene(new VBox(), 400, 350);
        ((VBox)scene.getRoot()).getChildren().addAll(menuBar, vbox);
         
        secondStage.setTitle(file.getName());
        secondStage.setScene(scene);
        secondStage.show();
    }
}
