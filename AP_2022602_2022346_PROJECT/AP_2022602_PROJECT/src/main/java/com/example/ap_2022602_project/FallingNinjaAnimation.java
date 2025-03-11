package com.example.ap_2022602_project;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
public class FallingNinjaAnimation {
    public static void playFallingAnimation(ImageView ninja) {

        TranslateTransition fallAnimation = new TranslateTransition(Duration.seconds(7), ninja);
        double finalY = 600;
        fallAnimation.setToY(finalY);
        fallAnimation.play();
    }
}

