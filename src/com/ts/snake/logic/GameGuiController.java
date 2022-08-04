package com.ts.snake.logic;

import com.ts.snake.data.AppleSingleton;
import com.ts.snake.data.SnakeSingleton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameGuiController {
    private BufferedImage image ;
    private List<Point>[][] groupsGrid ;
    private List<Point> cannibalisedLocations ;
    private Point lastAppleCell ;
    private Color brushColor ;

    public GameGuiController(BufferedImage image, List<Point>[][] groupsGrid) {
        this.image = image ;
        this.groupsGrid = groupsGrid ;


    }

    public void paintApple(){
       brushColor = Color.red ;
       paintCell(AppleSingleton.getInstance().getX(), AppleSingleton.getInstance().getY());
    }

    public void paintSnake(){
        brushColor = Color.BLACK ;
        for(Point p : SnakeSingleton.getInstance().getSnakeLocations()){
            paintCell((int)p.getX(), (int)p.getY());
        }
    }
    public void promoteSnake(){
       brushColor = Color.black;
       paintCell(SnakeSingleton.getInstance().getHead()) ;
       brushColor = Color.white ;
       paintCell(SnakeSingleton.getInstance().getLastTrace());
    }

    public void snakeCannibalised(){
        brushColor = Color.white ;
        for(Point p: cannibalisedLocations){
            paintCell(p);
        }
    }
    private void paintCell(int x, int y){
        for(Point p: groupsGrid[x][y]){
            image.setRGB(p.x, p.y, Color.red.getRGB());
        }
    }
    private void paintCell(Point pp){
        for(Point p: groupsGrid[pp.x][pp.y]){
            image.setRGB(p.x, p.y, brushColor.getRGB());
        }
    }

    public void deleteApple(){
        brushColor = Color.white ;
        paintCell(lastAppleCell.x, lastAppleCell.y);
    }


    public void gameOver() {
        brushColor = Color.red ;
        for(int i = 0 ; i < groupsGrid.length ; i++){
            for(int j = 0 ; j < groupsGrid[0].length ; j++){
                paintCell(i, j);
            }
        }
    }
    public void enlargeSnake(){
        brushColor = Color.black ;
        paintCell(SnakeSingleton.getInstance().getLastTrace()) ;
    }
    public void rememberApple(){
        lastAppleCell = new Point(AppleSingleton.getInstance().getX(), AppleSingleton.getInstance().getY()) ;
    }
    public void rememberSnakeEatenPart(int i) {
        cannibalisedLocations = new ArrayList() ;
        Point p ;
        for(int j = i ; j < SnakeSingleton.getInstance().getSnakeLocations().size() ; j++){
            p = SnakeSingleton.getInstance().getSnakeLocations().get(j) ;
            cannibalisedLocations.add(new Point(p.x, p.y)) ;
        }
    }
}
