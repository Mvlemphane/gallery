package com.example.gallery;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelloApplication extends Application {
    private static final String[] IMAGE_PATHS = {
            "image.jpg","image1.jpg","image2.jpg","image3.jpg","image4.jpg","image5.jpg","image6.jpg","image7.jpg","image8.jpg","image9.jpg"
    };
    private static final double IMAGE_HEIGHT = 140;
    private static final double IMAGE_WIDTH = 120;

    private int currentImg= 0;

    @Override
    public void start(Stage stage) {


        Label chelsea = new Label("We bleed blue");
        chelsea.setId("Chelsea");

        HBox root = new HBox();
        VBox displaya = new VBox();
        VBox displayb = new VBox();
        HBox button = new HBox();
        button.setId("Button");
        HBox container1 = new HBox();
        HBox container2 = new HBox();
        HBox container3 = new HBox();
        VBox left = new VBox();
        VBox right = new VBox();

        Button Close = new Button("Close");
        Button Next = new Button("Next");
        Button Previous = new Button("Previous");
        Previous.setId("Previous");
        Next.setId("Next");
        Close.setId("Close");

        List<ImageView> imageViews1 = createImageViews(3, 0);
        List<ImageView> imageViews2 = createImageViews(3, 4);
        List<ImageView> imageViews3 = createImageViews(3, 7);
        ImageView mainDisplay = new ImageView();
        mainDisplay.setFitWidth(600);
        mainDisplay.setPreserveRatio(true);
        mainDisplay.setId("mainDisplay");

        container1.getChildren().addAll(imageViews1);
        container2.getChildren().addAll(imageViews2);
        container3.getChildren().addAll(imageViews3);
        container1.setId("container");
        container2.setId("container");
        container3.setId("container");
        button.getChildren().addAll(Next, Close, Previous);
        displaya.getChildren().addAll(container3, container1);
        displayb.getChildren().addAll(container2);
        right.getChildren().addAll(button, mainDisplay);
        left.getChildren().addAll(chelsea, displaya, displayb);
        root.getChildren().addAll( left, right);

        for (ImageView imageView : imageViews1) {
            imageView.setOnMouseClicked(event -> updateMainDisplay(mainDisplay, imageView));
        }
        for (ImageView imageView : imageViews2) {
            imageView.setOnMouseClicked(event -> updateMainDisplay(mainDisplay, imageView));
        }
        for (ImageView imageView : imageViews3) {
            imageView.setOnMouseClicked(event -> updateMainDisplay(mainDisplay, imageView));
        }
        Previous.setOnAction(event -> handlePrevious(mainDisplay));
        Next.setOnAction(event -> handleNext(mainDisplay));

        Scene scene = new Scene(root, 1000, 640);
        String css = "/com/example/gallery/style.css";
        String cssExternal = Objects.requireNonNull(getClass().getResource(css)).toExternalForm();
        scene.getStylesheets().add(cssExternal);
        stage.setScene(scene);
        stage.show();
    }
    private List<ImageView> createImageViews(int count, int startIndex) {
        List<ImageView> imageViews = new ArrayList<>();

        int columns = 4;
        for (int i = 0; i < count; i++) {
            int rowIndex = i / columns;
            int colIndex = i % columns;

            Image image = new Image(getClass().getResource(IMAGE_PATHS[startIndex + i]).toExternalForm());
            ImageView imageView = new ImageView(image);

            imageView.setFitHeight(IMAGE_HEIGHT);
            imageView.setFitWidth(IMAGE_WIDTH);
            imageView.setPreserveRatio(true);
            imageViews.add(imageView);
        }
        return imageViews;
    }
        private void updateMainDisplay(ImageView mainDisplay, ImageView clickedImage) {
        mainDisplay.setImage(clickedImage.getImage());
    }
        private void handlePrevious(ImageView mainDisplay) {
        currentImg = (currentImg - 1 + IMAGE_PATHS.length) % IMAGE_PATHS.length;
        Image image = new Image(getClass().getResource(IMAGE_PATHS[currentImg]).toExternalForm());
        mainDisplay.setImage(image);
    }
        private void handleNext(ImageView mainDisplay) {
        currentImg = (currentImg + 1) % IMAGE_PATHS.length;
        Image image = new Image(getClass().getResource(IMAGE_PATHS[currentImg]).toExternalForm());
        mainDisplay.setImage(image);
    }
    public static void main(String[] args) {
        launch();
    }
}