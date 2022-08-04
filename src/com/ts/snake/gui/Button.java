package com.ts.snake.gui;

import com.ts.snake.data.Constants;

import javax.swing.*;
import java.awt.*;

public class Button extends JLabel{
    private final String value ;
    private boolean isFocused ;
    public Button(String value){
        super();
        isFocused = false ;
        this.value = value ;
    }
    public boolean isFocused() {
        return isFocused;
    }

    public void focus() {
        isFocused = true;
    }
    public void unFocus(){
        isFocused = false ;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g ;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, Constants.labelHeight / 2));
        g2d.setColor(isFocused ? Color.red : Color.black);
        g2d.fillRoundRect(0, 0, Constants.labelWidth-1, Constants.labelHeight-1, 15, 15);
        g2d.setColor(isFocused ? Color.orange : Color.cyan);
        g2d.drawRoundRect(0, 0, Constants.labelWidth-1, Constants.labelHeight-1, 15, 15);
        g.setColor(isFocused ? Color.black : Color.red);
        int stringWidth = g2d.getFontMetrics().stringWidth(value) ;
        int stringHeight = g2d.getFontMetrics().getHeight() ;
        g2d.drawString(value, (Constants.labelWidth-stringWidth)/2, (Constants.labelHeight+stringHeight)/3);
    }
}
