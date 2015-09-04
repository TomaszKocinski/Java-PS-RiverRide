/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riverrideprojektsem;

import javafx.scene.paint.Color;

/**
 *
 * @author Kot
 */
public class Shot extends Player {

    public Shot(Player pla) {
        super("shot.png");
        Rec.setFill(Color.PURPLE);
        setTranslateX(pla.getTranslateX() + 5);
        setTranslateY(pla.getTranslateY() - 5);
        minX = 0;
        minY = -20;
    }

}
