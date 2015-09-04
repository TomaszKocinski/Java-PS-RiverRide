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
public class FuelMeter extends ImageView {

    Image image;

    public FuelMeter() {
        image = new Image(RiverRideProjektSem.class.getResourceAsStream("fuelcontrol.png"));
        setImage(image);
        setScaleY(2);
        setScaleX(2);
    }

}
