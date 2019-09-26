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


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Prototype for an example from master/details views. Selection from different 
 * masters update the details view.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */
public class PrototypeListViewDetailsSelection extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    CheckBox  cbOkay;
    TextArea  taDescription;
    TextField tfTitle;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Layout
        HBox hbDesktop = this.configureDesktop();
        
        VBox vbDetails = this.configureDetails();
        hbDesktop.getChildren().add(vbDetails);
        
        VBox vbProject = this.configureProject();
        hbDesktop.getChildren().add(vbProject);
        
        VBox vbPalette = this.configurePalette();
        hbDesktop.getChildren().add(vbPalette);
        
        // Scene
        Scene scene = new Scene(hbDesktop, 1280, 720);
         
        primaryStage.setTitle("PrototypeListViewDetailsSelection");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private HBox configureDesktop() {
        HBox hbDesktop = new HBox(7.0);
        hbDesktop.setStyle("-fx-background-color:AQUAMARINE; -fx-padding:14;");
        
        return hbDesktop;
    }
    
    private VBox configureDetails() {
        VBox vbDetails = new VBox(7.0d);
        vbDetails.setStyle("-fx-background-color:DARKTURQUOISE; -fx-padding:7;");
        HBox.setHgrow(vbDetails, Priority.ALWAYS);
        
        Label lDetails = new Label("Details");
        vbDetails.getChildren().add(lDetails);
        
        Label lTitle = new Label("Title");
        vbDetails.getChildren().add(lTitle);
        
        tfTitle = new TextField();
        tfTitle.setDisable(true);
        vbDetails.getChildren().add(tfTitle);
        
        Label lDescription = new Label("Description");
        vbDetails.getChildren().add(lDescription);
        
        taDescription = new TextArea();
        taDescription.setDisable(true);
        taDescription.setWrapText(true);
        vbDetails.getChildren().add(taDescription);
        
        cbOkay = new CheckBox("Okay");
        cbOkay.setManaged(false);
        cbOkay.setVisible(false);
        vbDetails.getChildren().add(cbOkay);
        
        return vbDetails;
    }
    
    private VBox configureProject() {
        VBox vbProject = new VBox(7.0d);
        vbProject.setStyle("-fx-background-color:DARKTURQUOISE; -fx-padding:7;");
        HBox.setHgrow(vbProject, Priority.ALWAYS);
        
        Label lProject = new Label("Project");
        vbProject.getChildren().add(lProject);
        
        ListView<Element> lvProject = new ListView<>();
        Callback<ListView<Element>, ListCell<Element>> callback = (Callback<ListView<Element>, ListCell<Element>>) (ListView<Element> listView) -> new ListCell<Element>() {
            @Override
            protected void updateItem(Element element, boolean empty) {
                super.updateItem(element, empty);
                
                this.setGraphic(null);
                
                if (element == null || empty) {
                    this.setText(null);
                } else {
                    this.setText(element.getTitle());
                }
            }
        };
        lvProject.setCellFactory(callback);
        lvProject.setOnMouseClicked(event -> {
            // Show the Tag
            if (
                    event.getClickCount() == 1
                    && !lvProject.getSelectionModel().isEmpty()
            ) {
                final Element element = lvProject.getSelectionModel().getSelectedItem();
                this.onActionShowDetailsElement(DetailsType.PROJECT, element);
            }
        });

        ObservableList<Element> elements = FXCollections.observableArrayList();
        elements.add(new Element("JavaFX2", "Description from JavaFX. Description from JavaFX. Description from JavaFX. Description from JavaFX. Description from JavaFX. ", true));
        elements.add(new Element("Java2",   "Description from Java. Description from Java. Description from Java. Description from Java. ", false));
        lvProject.setItems(elements);
        
        vbProject.getChildren().add(lvProject);
        
        return vbProject;
    }
    
    private VBox configurePalette() {
        VBox vbPalette = new VBox(7.0d);
        vbPalette.setStyle("-fx-background-color:DARKTURQUOISE; -fx-padding:7;");
        HBox.setHgrow(vbPalette, Priority.ALWAYS);
        
        Label lPalette = new Label("Palette");
        vbPalette.getChildren().add(lPalette);
        
        ListView<Element> lvPalette = new ListView<>();
        Callback<ListView<Element>, ListCell<Element>> callback = (Callback<ListView<Element>, ListCell<Element>>) (ListView<Element> listView) -> new ListCell<Element>() {
            @Override
            protected void updateItem(Element element, boolean empty) {
                super.updateItem(element, empty);
                
                this.setGraphic(null);
                
                if (element == null || empty) {
                    this.setText(null);
                } else {
                    this.setText(element.getTitle());
                }
            }
        };
        lvPalette.setCellFactory(callback);
        lvPalette.setOnMouseClicked(event -> {
            // Show the Tag
            if (
                    event.getClickCount() == 1
                    && !lvPalette.getSelectionModel().isEmpty()
            ) {
                final Element element = lvPalette.getSelectionModel().getSelectedItem();
                this.onActionShowDetailsElement(DetailsType.PALETTE, element);
            }
        });
        
        ObservableList<Element> elements = FXCollections.observableArrayList();
        elements.add(new Element("JavaFX", "Description from JavaFX. Description from JavaFX. Description from JavaFX. Description from JavaFX. Description from JavaFX. ", true));
        elements.add(new Element("Java",   "Description from Java. Description from Java. Description from Java. Description from Java. ", true));
        lvPalette.setItems(elements);
        
        vbPalette.getChildren().add(lvPalette);
        
        return vbPalette;
    }
    
    private void onActionShowDetailsElement(DetailsType detailsType, Element element) {
        System.out.println("#onActionShowDetailsElement(Element)");
        System.out.println(element.toString());
        
        tfTitle.setText(      element.getTitle());
        taDescription.setText(element.getDescription());

        cbOkay.setSelected(element.isOkay());
        cbOkay.setManaged(detailsType.equals(DetailsType.PROJECT));
        cbOkay.setVisible(detailsType.equals(DetailsType.PROJECT));
    }

    public class Element {
        boolean okay;
        String title;
        String description;
        
        public Element(String title, String description, boolean okay) {
            this.title = title;
            this.description = description;
            this.okay = okay;
        }
        
        public String getTitle() {
            return title;
        }
        
        public String getDescription() {
            return description;
        }
        
        public boolean isOkay() {
            return okay;
        }
        
        @Override
        public String toString() {
            return String.format("Element[title=%s, okay=%s, description=%s]", title, (okay?"true":"false"), description);
        }
    }
    
    public enum DetailsType {
        PALETTE,
        PROJECT;
    }
}
