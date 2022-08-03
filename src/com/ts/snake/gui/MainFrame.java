package com.ts.snake.gui;
import com.ts.snake.data.Constants;

import javax.swing.* ;
public class MainFrame {
    private JFrame frame ;
    private GamePanel gamePanel ;
    private GameOverPanel gameOverPanel ;
    public MainFrame(){
        gamePanel = new GamePanel() ;
        gameOverPanel = new GameOverPanel() ;
        frame = new JFrame("Snake");
        frame.setSize(Constants.frameWidth, Constants.frameHeight+37);
        frame.setContentPane(gamePanel.getPanel());
        frame.setVisible(true);
    }
    public void gameOver(){
        frame.setContentPane(gameOverPanel.getPanel());
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
