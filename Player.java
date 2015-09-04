/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//http://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
package riverrideprojektsem;

import java.util.Vector;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Kot
 */
public class Player extends ImageView {

    Image image;
    Rectangle Rec;
    double SizeOfImageAndCollisionRectangle = 2.0;
    //http://stackoverflow.com/questions/21331519/how-to-get-smooth-animation-with-keypress-event-in-javafx
    final double rectangleSpeed = 250;
    double minX = 0, minY = 450;
    double maxX = 388, maxY = 585;
    final DoubleProperty rectangleVelocityX = new SimpleDoubleProperty();
    final DoubleProperty rectangleVelocityY = new SimpleDoubleProperty();
    final LongProperty lastUpdateTime = new SimpleLongProperty();

    public Player(String imageName) {

        image = new Image(RiverRideProjektSem.class.getResourceAsStream(imageName));

        setImage(image);
        setScaleX(SizeOfImageAndCollisionRectangle);
        setScaleY(SizeOfImageAndCollisionRectangle);
        Rec = new Rectangle(image.getWidth() * SizeOfImageAndCollisionRectangle, image.getHeight() * SizeOfImageAndCollisionRectangle);
        Rec.setVisible(false);
        Rec.setFill(Color.BLUE);
    }
    final AnimationTimer AnimationOfImage = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (lastUpdateTime.get() > 0) {
                final double elapsedSeconds = (timestamp - lastUpdateTime.get()) / 1_000_000_000.0;
                final double deltaX = elapsedSeconds * rectangleVelocityX.get();
                final double deltaY = elapsedSeconds * rectangleVelocityY.get();
                final double oldX = getTranslateX();
                final double newX = Math.max(minX, Math.min(maxX, oldX + deltaX));
                final double oldY = getTranslateY();
                final double newY = Math.max(minY, Math.min(maxY, oldY + deltaY));
                setTranslateX(newX);
                Rec.setTranslateX(newX - 1.5 * SizeOfImageAndCollisionRectangle * SizeOfImageAndCollisionRectangle);
                setTranslateY(newY);
                Rec.setTranslateY(newY - 1.5 * SizeOfImageAndCollisionRectangle * SizeOfImageAndCollisionRectangle);
            }
            lastUpdateTime.set(timestamp);
        }
    };

}
