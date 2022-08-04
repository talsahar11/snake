package com.ts.snake.gui;

import com.ts.snake.data.Constants;
import com.ts.snake.data.Option;
import com.ts.snake.logic.ButtonFactory;
import com.ts.snake.res.Resources;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameOverPanel {
    private JPanel panel ;
    private JLabel gameOverLbl ;
    private Button restartBtn, menuBtn, exitBtn, currentFocusedBtn ;
    private List<Button> buttons ;
    private ButtonFactory buttonFactory = new ButtonFactory() ;
    public GameOverPanel(){
        initComponents() ;
        initPanel() ;
    }
    private void initComponents(){
        setGameOverLbl();
        setButtons() ;

    }
    private void initPanel(){
        panel = new JPanel() ;
        panel.setBackground(Color.black);
        panel.setSize(Constants.frameWidth, Constants.frameHeight+37);
        panel.setLayout(null);
        panel.add(gameOverLbl) ;
        for(JLabel button: buttons){
            panel.add(button) ;
        }
        panel.requestFocus();
        panel.setFocusable(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public int getCenterX(int currentItemWidth){
        return (Constants.frameWidth - currentItemWidth)/2 ;
    }
    private void setGameOverLbl(){
        BufferedImage image = Resources.getGameOverIcon() ;
        ImageIcon icon = new ImageIcon(image) ;
        gameOverLbl = new JLabel() ;
        gameOverLbl.setIcon(icon);
        gameOverLbl.setSize(image.getWidth(), image.getHeight());
        gameOverLbl.setLocation(getCenterX(image.getWidth()),Constants.frameHeight/10);
    }
    private void setButtons(){
        buttons = new ArrayList<>() ;
        restartBtn = buttonFactory.createButton("Restart") ;
        exitBtn = buttonFactory.createButton("Exit") ;
        currentFocusedBtn = restartBtn ;
        currentFocusedBtn.focus();
        buttons.add(restartBtn) ;
        buttons.add(exitBtn) ;
        for(int i = 0 ; i < buttons.size() ; i++){
            JLabel button = buttons.get(i) ;
            button.setSize(Constants.labelWidth, Constants.labelHeight);
            button.setLocation(getCenterX(Constants.labelWidth), Constants.frameHeight/2 + (Constants.labelHeight + 10)*i);
        }
    }
    public Option keyPressed(int keyCode){
        int i = buttons.indexOf(currentFocusedBtn) ;
        System.out.println(keyCode);
        switch (keyCode){
            case 38:
                i--;
                if(i == -1){
                    i = buttons.size()-1 ;
                }
                break;
            case 40:
                i++;
                if(i == buttons.size()){
                    i = 0 ;
                }
                break;
            case 10:
                return Option.values()[i] ;
        }
        changeFocus(buttons.get(i));
        return null ;
    }
    private boolean isMouseOnComponent(Component c, int x, int y){
        int startX = c.getLocation().x, startY = c.getLocation().y, endX = startX + c.getWidth(), endY = startY + c.getHeight() ;
        if(x > startX && x < endX){
            if(y > startY && y < endY){
                return true ;
            }
        }
        return false ;
    }
    private void changeFocus(Button b){
        currentFocusedBtn.unFocus();
        currentFocusedBtn.repaint();
        currentFocusedBtn = b ;
        currentFocusedBtn.focus();
        currentFocusedBtn.repaint();

    }
}
