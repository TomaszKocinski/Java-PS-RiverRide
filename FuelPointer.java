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
public class FuelPointer extends Player {

    Image image;
    FuelMeter FM;

    public FuelPointer() {
        super("FuelPointer.png");
        FM = new FuelMeter();
        setScaleY(2.0);
    }

}
