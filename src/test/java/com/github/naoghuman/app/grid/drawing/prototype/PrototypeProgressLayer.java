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

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * idea
 *  - A transparent layer with infinite progress component.
 *  - show() shows the layer.
 *  - hide() hides the layer.
 *  - Mouse click won't trigger anything...
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */
public class PrototypeProgressLayer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final StackPane sp = new StackPane();
        sp.setStyle("-fx-background-color:AQUAMARINE; -fx-padding:14;");
        
        final VBox vb = new VBox(7.0d);
        Button btn = new Button("Show");
        btn.setOnAction((event1) -> {
            final ProgressLayer pl = new ProgressLayer();
            sp.getChildren().add(pl);
            
            final PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(5.0d));
            pt.setOnFinished((event2) -> {
                sp.getChildren().remove(pl);
            });
            pt.playFromStart();
        });
        vb.getChildren().add(btn);
        
        Button btn2 = new Button("Print");
        btn2.setOnAction((event) -> {
            System.out.println("--> print!");
        });
        vb.getChildren().add(btn2);
        
        sp.getChildren().add(vb);
        
        final Scene scene = new Scene(sp, 640, 360);
        
        primaryStage.setTitle("PrototypeProgressLayer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public final class ProgressLayer extends StackPane {
        
        public ProgressLayer() {
            this.initialize();
        }
        
        private void initialize() {
            super.setMouseTransparent(false);
            super.setStyle("-fx-background-color:rgb(0,0,0,0.33);");
            
            final ProgressIndicator pi = new ProgressIndicator(-1.0d);
            pi.setMaxSize(96.0d, 96.0d);
            pi.setStyle("-fx-progress-color:red;");
            super.getChildren().add(pi);
        }
    }

}
