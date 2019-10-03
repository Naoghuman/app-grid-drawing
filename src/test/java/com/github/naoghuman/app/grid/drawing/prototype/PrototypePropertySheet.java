/*
 * Copyright (C) 2019 Naoghuman's dream
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


import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.*;

/*
 * Example ListViewElementsShowsDifferentPropertySheets
 *  - pojo PaletteElement (abstract?)
 *  - Listview shows different PaletteElements
 *  - Selection from a PaletteElement in ListView shows corresponding PropertySheet
 *
 * PaletteElement
 *  - Haben eine einfach Darstellung.
 *  - Name, Beschreibung, moegliche Parents, moegliche Childrens
 * ProjectPaletteElement
 *  - Haben eine erweiterte Anzeige.
 *  - Manche sind editierbar, andere nicht.
 *  - Fuer 'Image' z.Bsp. der Pfad zum Bild.
 *
 * PaletteElement
 *  - Darstellung in Palette
 *     - ListViewCell
 *     - Selection in ListView shows PropertySheet
 *  - Darstellung in Project
 *     - ListViewCell
 *     - Selection in ListView shows PropertySheet
 */
public class PrototypePropertySheet extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    private static Map<String, Object> customDataMap = new LinkedHashMap<>();
    static {
        customDataMap.put("Group 1#My Text",        "Same text");                          // Creates a TextField in property sheet
        customDataMap.put("Group 1#My Date",        LocalDate.of(2000, Month.JANUARY, 1)); // Creates a DatePicker
        customDataMap.put("Group 2#My Enum Choice", SomeEnum.ONE);                         // Creates a ChoiceBox
        customDataMap.put("Group 2#My Boolean",     false);                                // Creates a CheckBox
        customDataMap.put("Group 2#My Number",      500);                                  // Creates a NumericField
        customDataMap.put("Group 3#My Color",       Color.ALICEBLUE);                      // Creates a ColorPicker
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<PropertySheet.Item> list = FXCollections.observableArrayList();
        for (String key : customDataMap.keySet())
            list.add(new CustomPropertyItem(key));

        PropertySheet propertySheet = new PropertySheet(list);
        VBox.setVgrow(propertySheet, Priority.ALWAYS);
        
        final VBox vbRoot = new VBox(7.0d);
        vbRoot.getChildren().add(propertySheet);

        final Scene scene = new Scene(vbRoot, 360, 640);
        
        primaryStage.setTitle("PrototypePropertySheet: Nr.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    enum SomeEnum {
        ONE,
        TWO,
        THREE;
    }
    
    class CustomPropertyItem implements PropertySheet.Item {
        private String key;
        private String category, name;

        public CustomPropertyItem(String key) {
            this.key = key;
            
            String[] skey = key.split("#");
            category = skey[0];
            name     = skey[1];
        }

        @Override
        public Class<?> getType() {
            return customDataMap.get(key).getClass();
        }

        @Override
        public String getCategory() {
            return category;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Optional<ObservableValue<? extends Object>> getObservableValue() {
            return Optional.empty();
        }

        @Override
        public Object getValue() {
            return customDataMap.get(key);
        }

        @Override
        public void setValue(Object value) {
            customDataMap.put(key, value);
        }
    }

}
