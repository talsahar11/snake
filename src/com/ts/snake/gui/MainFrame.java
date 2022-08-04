package com.ts.snake.gui;
import com.ts.snake.data.Constants;
import com.ts.snake.data.Option;

import javax.swing.* ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame {
    private JFrame frame ;
    private GamePanel gamePanel ;
    private GameOverPanel gameOverPanel ;
    private boolean gameOver, isExit, isRestart ;
    public MainFrame(){
        initPanels();
        initFrame();
        setKeyListener();
    }
    private void initPanels(){
        gamePanel = new GamePanel() ;
    }
    private void initFrame(){
        frame = new JFrame("Snake");
        frame.setSize(Constants.frameWidth, Constants.frameHeight+37);
        frame.setContentPane(gamePanel.getPanel());

        frame.setFocusable(true);
        frame.setVisible(true);
    }
    private void setKeyListener(){
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                Option option = null ;
                if(gameOver){
                    option = gameOverPanel.keyPressed(e.getKeyCode());
                    if(option != null){
                        System.out.println(option);
                        if(option == Option.EXIT) {
                            isExit = true;
                        }
                        if(option == Option.RESTART){
                            isRestart = true ;
                        }
                    }
                }else{
                    gamePanel.keyPressed(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public void gameOver(){
        gameOver = true ;
        gameOverPanel = new GameOverPanel() ;
        frame.setContentPane(gameOverPanel.getPanel());
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public boolean isExit() {
        return isExit;
    }

    public JFrame getFrame() {
        return frame;
    }
    public boolean isRestart(){
        return isRestart ;
    }
    public void restart(){
        initPanels();
        frame.setContentPane(gamePanel.getPanel());
        gameOver = false ;
        isRestart = false ;

    }
}
