package com.example.ap_2022602_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.util.Random;

class GameUI implements Runnable{
    private Pane pane;
    private Scene scene;
    private GameEngine gameEngine;
    private double y;
    private Timeline timeline;
    private Line verticalLine;
    @Override
    public void run() {

    }
    public void createPreliminaryUI(Scene s) {
        gameEngine.getScoreSystem().update();
        Button startButton = new Button("Play");
        startButton.setLayoutX((s.getWidth()-startButton.getWidth())/2);
        startButton.setLayoutY((s.getHeight()-startButton.getHeight())/2 +100);
        startButton.setScaleX(3);
        startButton.setScaleY(2.85);
        startButton.setOnAction(event -> gameEngine.handleStartButtonClick());
        pane.getChildren().add(startButton);
        Button pause = new Button("RECORDED DATA");
        pause.setLayoutX(scene.getWidth()-100);
        pause.setLayoutY(50);
        pause.setScaleX(3);
        pause.setScaleY(2.85);
        pause.setOnAction(event -> gameEngine.onClickPause());
        pane.getChildren().add(pause);
        pane.setPrefSize(s.getWidth(), s.getHeight());
        Label title = new Label("NINJA STICK GAME");
        title.setFont(new Font(150));
        title.setLayoutX((s.getWidth()-startButton.getWidth())/2 -200);
        title.setLayoutY((s.getHeight()-startButton.getHeight())/2-200);
        title.setStyle(
                "-fx-font-size: 54px;" +
                        "-fx-font-family: 'Lobster';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #346688;"
        );

        pane.getChildren().add(title);
        Rectangle st=new Rectangle(350,400, Color.BLACK);
        st.setLayoutY(y);
        st.setLayoutX(0);
        pane.getChildren().add(st);
        Image image = new Image("file:C://Users//Naman//Desktop//AP_2022602_PROJECT//src//main//resources//com//example//ap_2022602_project//final_sh.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(75);
        gameEngine.getCharacter().setImage(imageView);
        gameEngine.getCharacter().getImage().setLayoutX(300);
        gameEngine.getCharacter().getImage().setLayoutY(y-75);
        pane.getChildren().add(gameEngine.getCharacter().getImage());
        gameEngine.getPlatform().setP1(350);
        gameEngine.getPlatform().setY(y);

    }
    public GameUI(Scene root) {
        this.pane = new Pane();
        scene=root;
    }
    public void gameLoop(int ind1,int ind2) {
        pane.getChildren().clear();
        Label title1 = new Label("SCORE-BOARD");
        title1.setLayoutX((scene.getWidth()/2 -100));
        title1.setLayoutY((scene.getHeight()/2 -250));
        title1.setStyle(
                "-fx-font-size: 54px;" +
                        "-fx-font-family: 'Lobster';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #346688;"
        );
        this.getPane().getChildren().add(title1);
        Label title2 = new Label("Cherries");
        title2.setLayoutX((50));
        title2.setLayoutY((20));
        title2.setStyle("-fx-font-size: 30px;" + "-fx-font-family: 'Lobster';" + "-fx-font-weight: bold;" + "-fx-text-fill: #346688;"
        );
        this.getPane().getChildren().add(title2);
        Label title3 = new Label(gameEngine.getScoreSystem().getCurrentScore()+"");
        title3.setLayoutX((title1.getLayoutX()+65));
        title3.setLayoutY((title1.getLayoutY()+65));
        title3.setStyle(
                "-fx-font-size: 40px;" + "-fx-font-family: 'Lobster';" + "-fx-font-weight: bold;" + "-fx-text-fill: #346688;"
        );
        this.getPane().getChildren().add(title3);
        Label title4 = new Label( ""+ gameEngine.getScoreSystem().getNoOfCherry());
        title4.setLayoutX((100));
        title4.setLayoutY((50));
        title4.setStyle("-fx-font-size: 30px;" + "-fx-font-family: 'Lobster';" + "-fx-font-weight: bold;" + "-fx-text-fill: #346688;");
        this.getPane().getChildren().add(title4);
        Platform p=gameEngine.getPlatform();
        ImageView i=gameEngine.getCharacter().getImage();
        i.setLayoutX(p.getP1()-50);i.setLayoutY(y-75);
        this.getPane().getChildren().add(i);
        p.setP2(p.getPillars().get(ind1));
        double len=p.getP1()+p.getWidthList().get(ind2);
        Rectangle rec1=new Rectangle(p.getP1(),400,Color.BLACK);rec1.setLayoutX(0);rec1.setLayoutY(this.y);
        Rectangle rec2=new Rectangle(p.getP2(),400,Color.BLACK);rec2.setLayoutX(len);rec2.setLayoutY(this.y);
        this.getPane().getChildren().add(rec1);
        this.getPane().getChildren().add(rec2);
        double b= Math.random()*(p.getWidthList().get(ind2));
        Cherry cherry=new Cherry();
        Random r=new Random();
        int a=r.nextInt(0,2);
        if (a==1){
            cherry.getImage().setLayoutX(gameEngine.getCharacter().getImage().getLayoutX()+60+b);cherry.getImage().setFitWidth(20);
            cherry.getImage().setLayoutY(this.y-30);cherry.getImage().setFitHeight(20);
            gameEngine.getPlatform().setCherry(cherry);
            pane.getChildren().add(gameEngine.getPlatform().getCherry().getImage());
            gameEngine.getScoreSystem().setNoOfCherry(gameEngine.getScoreSystem().getNoOfCherry()+1);
        }
        timeline = new Timeline();
        verticalLine = new Line(gameEngine.getPlatform().getP1(), gameEngine.getPlatform().getY(), gameEngine.getPlatform().getP1(), gameEngine.getPlatform().getY());
        this.getPane().getChildren().add(verticalLine);
        gameEngine.handleKeyPress(KeyCode.ENTER, len);
        p.setP1(p.getP2());
        ind1=(int)(Math.random()*7);
        ind2=(int)(Math.random()*13);
        final int[] indices = new int[]{ind1, ind2};
        Timeline t2=new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            if (gameEngine.isPressed()){
                t2.stop();
                gameLoop(indices[0],indices[1]);
                gameEngine.setPressed(false);
            }
        });

        t2.getKeyFrames().add(keyFrame);
        t2.setCycleCount(Timeline.INDEFINITE);
        t2.play();
    }
    public void startMovingLine() {
        verticalLine.setStrokeWidth(5);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            verticalLine.setEndY(verticalLine.getEndY() - 1);
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void stopMovingLine() {
        timeline.stop();
        KeyFrame k1=new KeyFrame(Duration.millis(10),event1 ->{verticalLine.getTransforms().add(new Rotate(9,verticalLine.getStartX(), verticalLine.getStartY()));;});
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(k1);
        timeline.setCycleCount(10);
        timeline.play();
    }
    public void moveChar(){
        int len=(int)(-verticalLine.getEndY()+verticalLine.getStartY()+50);
        ImageView i=gameEngine.getCharacter().getImage();
        KeyFrame k1=new KeyFrame(Duration.millis(5),event1 ->{i.setLayoutX(i.getLayoutX()+1);});
        Timeline t1 =new Timeline(k1);
        t1.setCycleCount(len-50);
        t1.play();
    }
    public void isOverUi() {
        ImageView i=gameEngine.getCharacter().getImage();
        FallingNinjaAnimation.playFallingAnimation(i);
        Rectangle r1 = new Rectangle(500, 200, Color.HONEYDEW);
        r1.setLayoutX(250);
        r1.setLayoutY(200);
        pane.getChildren().add(r1);

        Label title = new Label("GAME OVER");
        title.setLayoutX(350);
        title.setLayoutY(250);
        title.setStyle("-fx-font-size: 60px;" + "-fx-font-family: 'Lobster';" + "-fx-font-weight: bold;" + "-fx-text-fill: #346688;");
        this.getPane().getChildren().add(title);

        Button cont = new Button("10 CHERRIES REQUIRED TO CONTINUE:");
        cont.setLayoutX(400);
        cont.setLayoutY(350);
        cont.setScaleX(3);
        cont.setScaleY(2.85);
        cont.setOnAction(event -> gameEngine.onClickContinue());
        pane.getChildren().add(cont);

        Button restart = new Button("RESTART");
        restart.setLayoutX(350);
        restart.setLayoutY(450);
        restart.setScaleX(3);
        restart.setScaleY(2.85);
        restart.setOnAction(event -> gameEngine.onClickRestart());
        pane.getChildren().add(restart);

        Button home = new Button("HOME");
        home.setLayoutX(500);
        home.setLayoutY(450);
        home.setScaleX(3);
        home.setScaleY(2.85);
        home.setOnAction(event -> gameEngine.onClickHome());
        pane.getChildren().add(home);
    }

    public void scoresystemUi() {
        pane.getChildren().clear();

        Rectangle background = new Rectangle(700, 500, Color.LIGHTGOLDENRODYELLOW);
        background.setLayoutX(800);
        background.setLayoutY(700);
        pane.getChildren().add(background);

        Label title = new Label("Score Board");
        title.setLayoutX(600);
        title.setLayoutY(170);
        title.setStyle("-fx-font-size: 60px;" + "-fx-font-family: 'Lobster';" + "-fx-font-weight: bold;" + "-fx-text-fill: #346688;");
        pane.getChildren().add(title);

        Label highestScoreLabel = new Label("Highest Score: " + gameEngine.getScoreSystem().getBestScore());
        highestScoreLabel.setLayoutX(400);
        highestScoreLabel.setLayoutY(300);
        highestScoreLabel.setStyle("-fx-font-size: 30px;" + "-fx-font-family: 'Lobster';" + "-fx-font-weight: bold;" + "-fx-text-fill: #346688;");
        pane.getChildren().add(highestScoreLabel);
        Label cherriesLabel = new Label("No Of Cherries: " + gameEngine.getScoreSystem().getNoOfCherry());
        cherriesLabel.setLayoutX(400);
        cherriesLabel.setLayoutY(400);
        cherriesLabel.setStyle("-fx-font-size: 30px;" + "-fx-font-family: 'Lobster';" + "-fx-font-weight: bold;" + "-fx-text-fill: #346688;");
        pane.getChildren().add(cherriesLabel);
        Button home = new Button("Home");
        home.setLayoutX(700);
        home.setLayoutY(550);
        home.setScaleX(3);
        home.setScaleY(2.85);
        home.setOnAction(event -> gameEngine.onClickHome());
        pane.getChildren().add(home);
    }
    public Pane getPane() {
        return pane;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    public Scene getScene() {
        return scene;
    }
    public Line getVerticalLine() {
        return verticalLine;
    }
}