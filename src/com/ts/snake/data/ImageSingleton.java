package com.ts.snake.data;

import java.awt.image.BufferedImage;

public class ImageSingleton {
    private BufferedImage image ;
    private static ImageSingleton instance = new ImageSingleton() ;

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public static ImageSingleton getInstance() {
        return instance;
    }
}
