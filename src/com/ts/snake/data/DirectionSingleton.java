package com.ts.snake.data;

public class DirectionSingleton {
    private Direction currentDirection ;
    private static DirectionSingleton instance = new DirectionSingleton() ;
    private DirectionSingleton(){
        currentDirection = Direction.DOWN ;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public static DirectionSingleton getInstance() {
        return instance;
    }
}
