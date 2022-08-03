package com.ts.snake.data;

import java.awt.*;

public class AppleSingleton {
    private static AppleSingleton instance = new AppleSingleton(0,0);
    private int x, y;
    private Color color ;
    private AppleSingleton(int x, int y){
        this.x = x ;
        this.y = y ;
        color = Color.red ;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCell(Point p){
        x = p.x ;
        y = p.y ;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public static AppleSingleton getInstance() {
        return instance;
    }


}
