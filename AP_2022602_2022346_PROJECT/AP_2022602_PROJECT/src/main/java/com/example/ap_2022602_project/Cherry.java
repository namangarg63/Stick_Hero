package com.example.ap_2022602_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

class Cherry extends Platform {
    private ImageView image;

    public Cherry(){
        Image i = new Image("C://Users//Naman//Desktop//AP_2022602_PROJECT//src//main//resources//com//example//ap_2022602_project//ch_pixel.jpeg");
        image= new ImageView(i);
    }

    public ImageView getImage() {
        return image;
    }
}

