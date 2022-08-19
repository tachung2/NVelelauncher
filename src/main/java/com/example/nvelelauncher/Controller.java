package com.example.nvelelauncher;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private HBox top;
    @FXML
    private ImageView imageView;

    int count;

    private double xOffSet = 0;
    private double yOffSet = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slideshow();
        makeStageDragable();
    }

    private void makeStageDragable() {
        parent.setOnMousePressed(event -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        parent.setOnMouseDragged(event -> {
            Main.stage.setX(event.getScreenX() - xOffSet);
            Main.stage.setY(event.getScreenY() - yOffSet);
            Main.stage.setOpacity(0.8f);
        });
        parent.setOnDragDone(event -> {
            Main.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased(event -> {
            Main.stage.setOpacity(1.0f);
        });
    }


    @FXML
    public void close_app(Event event) {
        System.exit(0);
    }

    @FXML
    public void minimize_stage(Event event) {
        Main.stage.setIconified(true);
    }


    public void slideshow() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("image/b2.jpg"));
        images.add(new Image("image/b3.jpg"));
        images.add(new Image("image/b4.jpg"));
        images.add(new Image("image/b5.jpg"));
        images.add(new Image("image/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            imageView.setImage(images.get(count));
            count++;
            if (count == 5)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}