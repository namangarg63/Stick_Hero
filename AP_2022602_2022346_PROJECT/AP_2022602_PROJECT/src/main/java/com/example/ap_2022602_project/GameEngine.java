package com.example.ap_2022602_project;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
class GameEngine implements Runnable  {
    private StickHeroCharacter character;
    private GameUI gameUI;
    private ScoreSystem scoreSystem;
    private Platform platform;
    private  boolean pressed;
    private GameEngine gameEngine;

    void handleStartButtonClick() {
        int ind1=(int)(Math.random()*7);
        int ind2=(int)(Math.random()*13);
        gameUI.gameLoop(ind1,ind2);


    }
    public GameEngine(GameUI gameUI,ScoreSystem s) {
        this.gameUI = gameUI;
        this.character = new StickHeroCharacter();
        this.scoreSystem=s;
        this.platform=new Platform();
        this.pressed=false;
    }
    public void handleKeyPress(KeyCode keyCode , double len) {
        AtomicBoolean isover= new AtomicBoolean(false);
        gameUI.getScene().setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("SPACE")) {
                gameUI.startMovingLine();
            }
        });

        gameUI.getScene().setOnKeyReleased(event -> {
            if (event.getCode().toString().equals("SPACE")) {
                gameUI.stopMovingLine();
                double stickLen=gameUI.getVerticalLine().getStartY()-gameUI.getVerticalLine().getEndY();
                if ((gameUI.getVerticalLine().getStartX()+stickLen<len)||(gameUI.getVerticalLine().getStartX()+stickLen> len+getPlatform().getP2())){
                    gameUI.moveChar();
                    gameUI.isOverUi();

                }else {
                    ImageView i=character.getImage();
                    final int[] j={1,(int)(len+platform.getP2()-50-i.getLayoutX())};
                    Timeline t1 =new Timeline();
                    KeyFrame k1=new KeyFrame(Duration.millis(5), event1 ->{
                        i.setLayoutX(i.getLayoutX()+1);
                        j[0]=j[0]+1;
                        if (j[0]==j[1]){
                            scoreSystem.setCurrentScore(scoreSystem.getCurrentScore()+1);
                            pressed=true;
                            t1.stop();
                        }
                    });
                    t1.getKeyFrames().add(k1);
                    t1.setCycleCount(Timeline.INDEFINITE);
                    t1.play();
                }
            }
        });
    }
    public void onClickContinue(){
        gameUI.getPane().getChildren().clear();
        if (scoreSystem.getNoOfCherry()>=10){
            scoreSystem.setNoOfCherry(scoreSystem.getNoOfCherry()-10);
            gameEngine.getPlatform().setCherry(null);
            handleStartButtonClick();
        }else {
            Rectangle r1 = new Rectangle(500, 200, Color.HONEYDEW);
            r1.setLayoutX(400);
            r1.setLayoutY(150);
            gameUI.getPane().getChildren().add(r1);
            Label title = new Label("SOME MORE CHERRIES REQUIRED!!");
            title.setLayoutX((500));
            title.setLayoutY((170));
            title.setStyle("-fx-font-size: 60px;" + "-fx-font-family: 'Lobster';" + "-fx-font-weight: bold;" + "-fx-text-fill: #346688;");
            gameUI.getPane().getChildren().add(title);
            Button restart = new Button("Restart");
            restart.setLayoutX(550);
            restart.setLayoutY(500);
            restart.setScaleX(3);
            restart.setScaleY(2.85);
            restart.setOnAction(event -> this.onClickRestart());
            gameUI.getPane().getChildren().add(restart);
            Button home = new Button("Home");
            home.setLayoutX(400);
            home.setLayoutY(300);
            home.setScaleX(3);
            home.setScaleY(2.85);
            home.setOnAction(event -> this.onClickHome());
            gameUI.getPane().getChildren().add(home);
        }
    }
    public void onClickRestart(){
        scoreSystem.update();
        handleStartButtonClick();
        gameUI.getPane().getChildren().clear();
        gameUI.createPreliminaryUI(gameUI.getScene());
    }
    public void onClickHome(){
        gameUI.getPane().getChildren().clear();
        gameUI.createPreliminaryUI(gameUI.getScene());
    }

    public void onClickPause(){
        gameUI.scoresystemUi();
    }

    @Override
    public void run() {
    }

    public Platform getPlatform() {
        return platform;
    }
    public ScoreSystem getScoreSystem() {
        return scoreSystem;
    }
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public StickHeroCharacter getCharacter() {
        return character;
    }
    public GameUI getGameUI() {
        return gameUI;
    }
    public boolean isPressed() {
        return pressed;
    }

}