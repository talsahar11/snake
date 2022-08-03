package com.ts.snake.gui;

import com.ts.snake.data.Constants;

import javax.swing.*;

public class GameOverPanel {
    private JPanel panel ;
    private JLabel label ;
    public GameOverPanel(){
        initComponents() ;
        initPanel() ;
    }
    private void initComponents(){
        label = new JLabel("Game Is Over") ;
        label.setSize(100,40);
        label.setLocation((Constants.frameWidth -label.getWidth())/2, ((Constants.frameHeight+37-label.getHeight())/2));
    }
    private void initPanel(){
        panel = new JPanel() ;
        panel.setSize(Constants.frameWidth, Constants.frameHeight+37);
        panel.add(label) ;
    }

    public JPanel getPanel() {
        return panel;
    }
}
