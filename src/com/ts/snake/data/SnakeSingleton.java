package com.ts.snake.data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnakeSingleton {
    private static SnakeSingleton instance = new SnakeSingleton() ;
    private List<Point> snakeLocations ;
    private Point head, lastTrace ;
    private Color color ;
    private SnakeSingleton() {
        snakeLocations = new ArrayList<>() ;
        color = Color.black ;
        lastTrace = null ;
    }
    public void setHead(Point p){
        if(snakeLocations.size() == 0){
            head = p ;
        }
        snakeLocations.add(p) ;

    }
    public boolean cutSnake(int i){
        if(snakeLocations.size() <= i){
            return false ;
        }else{
            while(i < snakeLocations.size()){
                snakeLocations.remove(i) ;
                i++ ;
            }
            return true ;
        }
    }
    public boolean enlargeSnake(int x, int y) {
        for(Point p: snakeLocations){
            if(p.x == x && p.y == y){
                return false ;
            }
        }
        snakeLocations.add(new Point(x,y)) ;
        return true ;
    }

    public void goLeft() {
        lastTrace = new Point(snakeLocations.get(snakeLocations.size()-1)) ;
        promoteCells();
        snakeLocations.get(0).x -= 1 ;
    }
    public void goDown() {
        lastTrace = new Point(snakeLocations.get(snakeLocations.size()-1)) ;
        promoteCells();
        snakeLocations.get(0).y += 1 ;
    }
    public void goRight() {
        lastTrace = new Point(snakeLocations.get(snakeLocations.size()-1)) ;
        promoteCells();
        snakeLocations.get(0).x += 1 ;
    }
    public void goUp() {
        lastTrace = new Point(snakeLocations.get(snakeLocations.size()-1)) ;
        promoteCells();
        snakeLocations.get(0).y -= 1 ;
    }
    private void promoteCells() {
        int currentX, currentY ;
        for(int i = snakeLocations.size()-1 ; i > 0 ; i--){
            currentX = snakeLocations.get(i-1).x ;
            currentY = snakeLocations.get(i-1).y ;
            snakeLocations.get(i).x = currentX ;
            snakeLocations.get(i).y = currentY ;
        }
    }

    public Point getHead() {
        return head;
    }

    public int isCannibalised() {
        for(int i = 1 ; i < snakeLocations.size() ; i++){
            if(head.x == snakeLocations.get(i).x && head.y == snakeLocations.get(i).y){
                return i ;
            }
        }
        return -1 ;
    }

    public List<Point> getSnakeLocations() {
        return snakeLocations;
    }

    public Point getLastTrace() {
        return lastTrace;
    }

    public static SnakeSingleton getInstance() {
        return instance;
    }
    public void reset(){
        snakeLocations = new ArrayList<>( );
        head = null ;
    }
    public boolean contains(int x, int y){
        for(Point p: snakeLocations){
            if(p.x == x && p.y == y){
                return true ;
            }
        }
        return false ;
    }
}
