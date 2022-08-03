package com.ts.snake.logic;

import com.ts.snake.data.DirectionSingleton;
import com.ts.snake.gui.MainFrame;

public class Engine {
    GameController controller ;
    GuiController guiController ;
    MainFrame mainFrame ;
    boolean gameOver, isAppleEaten;
    int cannibalisedIndex ;
    public Engine() {
        mainFrame = new MainFrame() ;
        controller = new GameController(mainFrame.getGamePanel()) ;
        guiController = new GuiController(mainFrame.getGamePanel().getCurrentImage(), controller.getGroupsGrid()) ;
        restart();

    }
    public void run() {
        while(!gameOver) {
            evaluate();

            write();

            draw();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
            System.out.println("gameOVered");
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
        cannibalisedIndex = -1 ;
        gameOver = false ;
        isAppleEaten = false ;
        controller.initGridGroups();
        controller.createSnake();
        controller.plantApple() ;
        guiController.paintApple();
        guiController.paintSnake();
    }
}
