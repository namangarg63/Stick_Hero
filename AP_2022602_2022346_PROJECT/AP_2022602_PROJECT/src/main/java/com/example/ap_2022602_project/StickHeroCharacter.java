package com.example.ap_2022602_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class StickHeroCharacter {
    private int x,y;
    private boolean isInverted;
    private ImageView image;

    public StickHeroCharacter() {
        Image i=new Image("C:/Users/Naman/Downloads/StickHero/Stick_Hero_jar/images/stand.png");
        image=new ImageView(i);
        isInverted=false;
        x=0;
    }
    public ImageView getImage() {
        return image;
    }
    public void setImage(ImageView image) {
        this.image = image;
    }

}