package com.ts.snake.logic;

import com.ts.snake.data.AppleSingleton;
import com.ts.snake.data.Constants;
import com.ts.snake.data.Direction;
import com.ts.snake.gui.GamePanel;
import com.ts.snake.data.SnakeSingleton;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    private GamePanel gameFrame ;
    private GuiController guiController ;
    private List<Point>[][] groupsGrid;
    private Random rand ;
    private boolean isGameOver ;

    public GameController(GamePanel gameFrame){
        this.gameFrame = gameFrame ;
        rand = new Random() ;
        isGameOver = false ;
        initGridGroups();
        guiController = new GuiController(gameFrame.getCurrentImage(), groupsGrid) ;
        SnakeSingleton.getInstance().setHead(getRandomCell()); ;


    }
    public void createSnake(){
        SnakeSingleton.getInstance().reset();
        SnakeSingleton.getInstance().setHead(getRandomCell()) ;
    }
    public void plantApple() { AppleSingleton.getInstance().setCell(getRandomCellForApple()) ;}
    public void initGridGroups(){
        groupsGrid = new ArrayList[Constants.frameWidth/Constants.pixelsToCol][Constants.frameHeight/Constants.pixelsToRow] ;
        int row,col, blockX, blockY ;
        for(int x = 0 ; x < Constants.frameWidth ; x++){
            for(int y = 0 ; y < Constants.frameHeight ; y++){
                Point p = new Point(x,y) ;
                row = (int)Math.floor(x/Constants.pixelsToRow) ;
                col = (int)Math.floor(y/Constants.pixelsToCol) ;
                if(groupsGrid[row][col] == null){
                    groupsGrid[row][col] = new ArrayList<>() ;
                }
                groupsGrid[row][col].add(p) ;
            }
        }
    }

    public void promoteSnake(Direction direction) {
        //0 - left, 1 - down, 2 - right, 3 - up
        if(direction == Direction.LEFT){
                    SnakeSingleton.getInstance().goLeft();
        }

        if(direction == Direction.DOWN){
                    SnakeSingleton.getInstance().goDown();
        }
        if(direction == Direction.RIGHT){
                    SnakeSingleton.getInstance().goRight();
        }
        if(direction == Direction.UP){
                    SnakeSingleton.getInstance().goUp();
        }
    }
    private Point getRandomCell() {
        int x, y ;
        do {
            x = rand.nextInt(groupsGrid.length);
            y = rand.nextInt(groupsGrid[0].length);
        }while(SnakeSingleton.getInstance().contains(x,y)) ;
        return new Point(x,y) ;
    }

    public Point getRandomCellForApple(){
        int x, y ;
        do {
            x = rand.nextInt(groupsGrid.length) ;
            y = rand.nextInt(groupsGrid[0].length) ;
        }while(SnakeSingleton.getInstance().contains(x,y)) ;
        return new Point(x,y) ;
    }

    public boolean isSnakeCrashed(){
        if(SnakeSingleton.getInstance().getHead().x < 0 || SnakeSingleton.getInstance().getHead().y < 0 ||
           SnakeSingleton.getInstance().getHead().x > groupsGrid.length-1 || SnakeSingleton.getInstance().getHead().y > groupsGrid[0].length -1){
            return true ;
        }
        return false ;
    }
    public int isCannibalised(){
        return SnakeSingleton.getInstance().isCannibalised();
    }
    public void cutSnake(int i){
        SnakeSingleton.getInstance().cutSnake(i) ;
    }

    public boolean isAppleEaten(){
        if(SnakeSingleton.getInstance().getHead().x == AppleSingleton.getInstance().getX() && SnakeSingleton.getInstance().getHead().y == AppleSingleton.getInstance().getY()){
            return true ;
        }
        return false ;
    }

    public List<Point>[][] getGroupsGrid() {
        return groupsGrid;
    }

    public void enlargeSnake(){
        SnakeSingleton.getInstance().enlargeSnake(SnakeSingleton.getInstance().getLastTrace().x, SnakeSingleton.getInstance().getLastTrace().y) ;
    }
}
