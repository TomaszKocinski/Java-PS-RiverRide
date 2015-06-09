/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riverrideprojektsem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Kot
 */
public class Player extends ImageView{
    Image image;
    private int X1;
    private int Y1;
    public Player() {
        image = new Image(RiverRideProjektSem.class.getResourceAsStream("player.png"));
        setImage(image);
        X1=4;
        Y1=5;
        
    }

    /**
     * @return the X1
     */
    public int getX1() {
        return X1;
    }

    /**
     * @param X1 the X1 to set
     */
    public void setX1(int X1) {
        this.X1 = this.X1 + X1;
    }

    /**
     * @return the Y1
     */
    public int getY1() {
        return Y1;
    }

    /**
     * @param Y1 the Y1 to set
     */
    public void setY1(int Y1) {
        this.Y1 = this.Y1 + Y1;
        
    }
    
    
}
