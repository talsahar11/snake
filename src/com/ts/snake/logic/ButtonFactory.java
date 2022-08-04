package com.ts.snake.logic;

import com.ts.snake.data.Constants;
import com.ts.snake.gui.Button;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {
    public Button createButton(String value){
        return new Button(value) ;
    }

}
