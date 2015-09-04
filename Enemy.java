/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riverrideprojektsem;

import javafx.scene.paint.Color;
import javafx.scene.image.Image;

/**
 *
 * @author Kot
 */
public class Enemy extends Player {

    public Enemy(String imageName) {
        super(imageName);

        Rec.setFill(Color.RED);
        minX = 0;
        minY = -1200;
        maxX = 1000;
        maxY = 1000;
    }

}
