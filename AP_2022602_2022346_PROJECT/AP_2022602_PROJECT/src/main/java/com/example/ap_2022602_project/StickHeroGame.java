package com.example.ap_2022602_project;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;

public class StickHeroGame extends Application {
    private GameEngine gameEngine;
    private GameUI gameUi;
    private ScoreSystem scoreSystem;
    private Pane root;
    private Scene scene;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {

        root = new Pane();
        scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Stick Hero Game");
        gameUi=new GameUI(scene);
        scoreSystem=new ScoreSystem();
        gameEngine = new GameEngine(gameUi,scoreSystem);gameUi.setGameEngine(gameEngine);gameEngine.getGameUI().setGameEngine(gameEngine);
        Double[] pillarsArray = {220.0, 100.0, 160.0, 190.0, 300.0, 95.0, 35.0};
        Double[] widthArray = {150.0, 280.0, 220.0, 170.0, 145.0, 100.0, 160.0, 390.0, 410.0, 125.0, 350.0, 200.0, 250.0};

        gameEngine.getPlatform().getPillars().addAll(Arrays.asList(pillarsArray));
        gameEngine.getPlatform().getWidthList().addAll(Arrays.asList(widthArray));

        boolean gameEnded = true;
        if (gameEnded) {
            Stop[] stops = new Stop[]{new Stop(0, Color.WHEAT), new Stop(1, Color.LIGHTBLUE)};
            RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, stops);
            BackgroundFill backgroundFill = new BackgroundFill(radialGradient, CornerRadii.EMPTY, Insets.EMPTY);
            Background b = new Background(backgroundFill);
            root.setBackground(b);
            gameUi.setY(scene.getHeight() - 300);
        }
        gameUi.createPreliminaryUI(scene);
        root.getChildren().add(gameUi.getPane());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}






