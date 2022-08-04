package com.ts.snake.res;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {
    private static Resources instance;

    static {
        try {
            instance = new Resources();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage gameOverIcon;
    private Resources() throws IOException {
        gameOverIcon = ImageIO.read(new File("src/com/ts/snake/res/game-over.png")) ;
    }

    public static BufferedImage getGameOverIcon() {
        return gameOverIcon;
    }
}
