package com.ts.snake.logic;

import com.ts.snake.data.DirectionSingleton;
import com.ts.snake.gui.MainFrame;

public class Engine {
    GameController controller ;
    GameGuiController guiController ;
    MainFrame mainFrame ;
    boolean gameOver, isAppleEaten;
    Boolean isExit = false, isRestart = false ;
    int cannibalisedIndex ;
    public Engine() {
        mainFrame = new MainFrame() ;
        controller = new GameController(mainFrame.getGamePanel()) ;
        guiController = new GameGuiController(mainFrame.getGamePanel().getCurrentImage(), controller.getGroupsGrid()) ;
        cannibalisedIndex = -1 ;
        gameOver = false ;
        isAppleEaten = false ;
        controller.initGridGroups();
        controller.createSnake();
        controller.plantApple() ;
        guiController.paintApple();
        guiController.paintSnake();
    }
    public void run() {
        while(!isExit) {
            while (!gameOver) {
                evaluate();

                write();

                draw();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isRestart = mainFrame.isRestart() ;
            if(isRestart){
                restart();
            }
            isExit = mainFrame.isExit();
        }
        shutDown() ;
    }
    public void evaluate() {
        controller.promoteSnake(DirectionSingleton.getInstance().getCurrentDirection());
        gameOver = controller.isSnakeCrashed() ;
        cannibalisedIndex = controller.isCannibalised();
        isAppleEaten = controller.isAppleEaten() ;
    }
    public void write() {
        if(gameOver){
            mainFrame.gameOver();
            guiController.gameOver() ;
        }else
        if(cannibalisedIndex != -1){
            guiController.rememberSnakeEatenPart(cannibalisedIndex);
            controller.cutSnake(cannibalisedIndex);
        }else
        if(isAppleEaten){
            guiController.rememberApple();
            controller.enlargeSnake();
            controller.plantApple();
        }


    }
    private void draw(){
        if(gameOver){
            guiController.gameOver();
        }else {
            guiController.promoteSnake();
            if (cannibalisedIndex != -1) {
                guiController.snakeCannibalised();
                cannibalisedIndex = -1;
            }
            if (isAppleEaten) {
                isAppleEaten = false;
                guiController.deleteApple();
                guiController.enlargeSnake();
                guiController.paintApple();
            }
        }
        mainFrame.getGamePanel().getImageLabel().repaint();

    }
    public void restart(){
        mainFrame.restart();
        controller = new GameController(mainFrame.getGamePanel()) ;
        guiController = new GameGuiController(mainFrame.getGamePanel().getCurrentImage(), controller.getGroupsGrid()) ;
        cannibalisedIndex = -1 ;
        gameOver = false ;
        isAppleEaten = false ;
        controller.initGridGroups();
        controller.createSnake();
        controller.plantApple() ;
        guiController.paintApple();
        guiController.paintSnake();
    }
    public void shutDown(){
        mainFrame.getFrame().dispose();
    }
}
