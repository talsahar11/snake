package com.ts.snake.gui;

import com.ts.snake.data.Constants;
import com.ts.snake.data.Direction;
import com.ts.snake.data.DirectionSingleton;
import com.ts.snake.data.ImageSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel {
    private JPanel panel;
    private ImageSingleton currentImage ;
    private JLabel imageLbl ;
    private ImageIcon imageIcon ;

    Integer currentDirection ;
    public GamePanel() {
        initComponents();
        initPanel();
    }
    private void initComponents() {
        ImageSingleton.getInstance().setImage(new BufferedImage(Constants.frameWidth, Constants.frameHeight, 1)) ;
        fillBackground(Color.white.getRGB());
        imageIcon = new ImageIcon(ImageSingleton.getInstance().getImage()) ;
        imageLbl = new JLabel() ;
        imageLbl.setIcon(imageIcon);
        imageLbl.setFocusable(true);
    }
    private void fillBackground(int color){
        for(int i = 0 ; i < ImageSingleton.getInstance().getImage().getWidth() ; i++){
            for(int j = 0 ; j < ImageSingleton.getInstance().getImage().getHeight() ; j++){
                ImageSingleton.getInstance().getImage().setRGB(i,j, color);
            }
        }
    }
    private void initPanel() {
        panel = new JPanel() ;
        panel.setSize(Constants.frameWidth, Constants.frameHeight+37);
        panel.setLayout(new BorderLayout());
        panel.add(imageLbl, BorderLayout.NORTH) ;
        panel.setVisible(true);
    }

    public BufferedImage getCurrentImage() {
        return ImageSingleton.getInstance().getImage();
    }

    public void keyPressed(int keyCode) {
        if(keyCode > 36 && keyCode < 41){
            switch (keyCode){
                case(37):
                    DirectionSingleton.getInstance().setCurrentDirection(Direction.LEFT); ;
                    break;
                case(38):
                    DirectionSingleton.getInstance().setCurrentDirection(Direction.UP); ;
                    break;
                case(39):
                    DirectionSingleton.getInstance().setCurrentDirection(Direction.RIGHT); ;
                    break;
                case(40):
                    DirectionSingleton.getInstance().setCurrentDirection(Direction.DOWN); ;
                    break;

            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getImageLabel() {
        return imageLbl ;
    }
}
