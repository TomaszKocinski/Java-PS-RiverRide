package riverrideprojektsem;

import java.io.File;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RiverRideProjektSem extends Application {
    
    Player player = new Player("player.png");
    Vector<Enemy> VecOfEnemy = new Vector<Enemy>();
    Vector<Shot> VecOfShots = new Vector<Shot>();
    Vector<Fuel> VecOfFuel = new Vector<>();
    int CooldownOfShots = 0;
    FuelPointer FP = new FuelPointer();
    Integer HighScore = 0;
    Double Score = 0.0;
    
    public void start(Stage stage) {
        try {
            Scanner scanner = new Scanner(new File("highscore.txt"));
            String tempStr = scanner.nextLine();
            HighScore = Integer.parseInt(tempStr);
            scanner.close();
        } catch (Exception e) {
            System.out.println("something with file");
            HighScore = -5;
        }
        
        BorderPane MainBorderPane = new BorderPane();
        Scene scene = new Scene(MainBorderPane, 400, 600, Color.BURLYWOOD);
        Text lScore = new Text(Score.toString());
        
        MainBorderPane.getChildren().add(player.Rec);
        MainBorderPane.getChildren().add(lScore);
        MainBorderPane.getChildren().add(FP.FM);
        MainBorderPane.getChildren().add(FP);
        MainBorderPane.getChildren().add(player);
        lScore.setTranslateX(20);
        lScore.setTranslateY(20);
        lScore.setVisible(true);
        FP.setTranslateX(260);
        FP.setTranslateY(580);
        FP.FM.setTranslateX(150);
        FP.FM.setTranslateY(580);
        
        player.setTranslateX(200);
        player.Rec.setTranslateX(200);
        player.setTranslateY(525);
        player.Rec.setTranslateY(525);
        
        for (int i = 0; i < 16; i++) {
            Integer tempInt = Math.abs(new Random().nextInt() % 3) + 1;
            Enemy tempBasicEnemy = new Enemy("enemy" + tempInt.toString() + ".png");
            tempInt = Math.abs(new Random().nextInt() % 600) + 1;
            tempBasicEnemy.setTranslateY(-tempInt);
            tempBasicEnemy.setTranslateX(tempInt * 10 % 360);
            
            MainBorderPane.getChildren().add(tempBasicEnemy);
            MainBorderPane.getChildren().add(tempBasicEnemy.Rec);
            tempBasicEnemy.AnimationOfImage.start();
            VecOfEnemy.add(tempBasicEnemy);
            
        }
        for (int i = 0; i < 2; i++) {
            Integer tempInt;
            Fuel Fuel = new Fuel();
            tempInt = Math.abs(new Random().nextInt() % 600) + 1;
            Fuel.setTranslateY(-tempInt);
            Fuel.setTranslateX(tempInt * 10 % 360);
            
            MainBorderPane.getChildren().add(Fuel);
            MainBorderPane.getChildren().add(Fuel.Rec);
            Fuel.AnimationOfImage.start();
            VecOfFuel.add(Fuel);
        }
        stage.setTitle("Copy of RiverRaid");
        
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        
        final Duration oneFrameAmt = Duration.millis(1000 / 60);
        final KeyFrame oneFrame;
        player.AnimationOfImage.start();
        FP.AnimationOfImage.start();
        oneFrame = new KeyFrame(oneFrameAmt, new EventHandler<ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                if (CooldownOfShots != 0) {
                    CooldownOfShots--;
                }
                double MullitiveOfSpeed = (-player.getTranslateY() + 525) / 100 + 1;
                for (Enemy BE : VecOfEnemy) {
                    BE.rectangleVelocityY.setValue(250 * MullitiveOfSpeed);
                    if (player.Rec.getBoundsInParent().intersects(BE.Rec.getBoundsInParent()) || FP.getTranslateX() < 110) {
                        System.out.println("jest kolizja lub koniec paliwa");
                        FP.setTranslateX(260);
                        if (HighScore < Score) {
                            HighScore = Score.intValue();
                        }
                        Score = 0.0;
                        try {
                            PrintStream ps = new PrintStream("highscore.txt");
                            ps.printf(HighScore.toString());
                            ps.close();
                        } catch (Exception e) {
                            System.out.println("cos jest nie tak z zapisem");
                        }
                        for (Shot Shots : VecOfShots) {
                            Shots.setVisible(false);
                        }
                        VecOfShots.clear();
                        for (Enemy E : VecOfEnemy) {
                            E.setTranslateY(-Math.abs(new Random().nextInt() % 300));
                            E.setTranslateX(Math.abs(new Random().nextInt() % 400));
                        }
                        for (Fuel Fuel : VecOfFuel) {
                            Fuel.setTranslateY(-Math.abs(new Random().nextInt() % 300));
                            Fuel.setTranslateX(Math.abs(new Random().nextInt() % 400));
                        }
                        player.setTranslateX(200);
                        player.Rec.setTranslateX(200);
                        player.setTranslateY(525);
                        player.Rec.setTranslateY(525);
                        
                    }
                    if (BE.getTranslateY() > 700) {
                        BE.setTranslateY(-Math.abs(new Random().nextInt() % 300));
                        BE.setTranslateX(Math.abs(new Random().nextInt() % 400));
                    }
                }
                for (Shot Shots : VecOfShots) {
                    boolean OneShotRemoved = false;
                    //System.out.println("siema");
                    Shots.rectangleVelocityY.set(-Shots.rectangleSpeed);
                    for (Enemy BE : VecOfEnemy) {
                        if (Shots.Rec.getBoundsInParent().intersects(BE.Rec.getBoundsInParent())) {
                            //System.out.println("jest kolizja");
                            BE.setTranslateY(-Math.abs(new Random().nextInt() % 300));
                            BE.setTranslateX(Math.abs(new Random().nextInt() % 400));
                            Shots.setVisible(false);
                            VecOfShots.remove(VecOfShots.indexOf(Shots));
                            Score += 5;
                            OneShotRemoved = true;
                            break;
                        }
                        
                        if (OneShotRemoved) {
                            break;
                        }                        
                    }
                    for (Fuel Fuel : VecOfFuel) {
                        if (Shots.Rec.getBoundsInParent().intersects(Fuel.Rec.getBoundsInParent())) {
                            Fuel.setTranslateY(-Math.abs(new Random().nextInt() % 300));
                            Fuel.setTranslateX(Math.abs(new Random().nextInt() % 400));
                            Shots.setVisible(false);
                            VecOfShots.remove(VecOfShots.indexOf(Shots));
                            Score += 5;
                            OneShotRemoved = true;
                            break;
                        }
                    }
                    if (OneShotRemoved) {
                        break;
                    }
                }
                for (Fuel Fuel : VecOfFuel) {
                    Fuel.rectangleVelocityY.setValue(250 * MullitiveOfSpeed);
                    if (player.Rec.getBoundsInParent().intersects(Fuel.Rec.getBoundsInParent())) {
                        System.out.println("jest kolizja z paliwem");
                        FP.setTranslateX(260);
                        Fuel.setTranslateY(-Math.abs(new Random().nextInt() % 300));
                        Fuel.setTranslateX(Math.abs(new Random().nextInt() % 400));
                    }
                    if (Fuel.getTranslateY() > 700) {
                        Fuel.setTranslateY(-Math.abs(new Random().nextInt() % 300));
                        Fuel.setTranslateX(Math.abs(new Random().nextInt() % 400));
                    }
                }
                Score += 0.05 * MullitiveOfSpeed;
                Integer intScore = Score.intValue();
                lScore.setText("Score: " + intScore.toString() + "  Highscore: " + HighScore.toString());
                FP.rectangleVelocityX.set(-player.rectangleSpeed / 25 * MullitiveOfSpeed);
            }
        });
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode KC = e.getCode();
                //System.out.println(KC);

                if (KC == KeyCode.RIGHT) {
                    player.rectangleVelocityX.set(player.rectangleSpeed);
                } else if (KC == KeyCode.LEFT) {
                    player.rectangleVelocityX.set(-player.rectangleSpeed);
                } else if (KC == KeyCode.UP) {
                    player.rectangleVelocityY.set(-player.rectangleSpeed / Math.sqrt(2.0));
                } else if (KC == KeyCode.DOWN) {
                    player.rectangleVelocityY.set(player.rectangleSpeed / Math.sqrt(2.0));
                } else if (KC == KeyCode.SPACE && CooldownOfShots == 0) {
                    Shot temp = new Shot(player);
                    temp.AnimationOfImage.start();
                    
                    temp.rectangleVelocityY.setValue(-250);
                    MainBorderPane.getChildren().add(temp);
                    MainBorderPane.getChildren().add(temp.Rec);
                    VecOfShots.add(temp);
                    CooldownOfShots = 30;
                    if (Score > 5.0) {
                        Score -= 5.0;
                    } else {
                        Score = 0.0;
                    }
                }
                
            }
            
        });
        scene.setOnKeyReleased((KeyEvent ke) -> {
            KeyCode KC = ke.getCode();
            System.out.println(KC);
            if (KC == KeyCode.RIGHT) {
                if (player.rectangleVelocityX.get() > 0) {
                    player.rectangleVelocityX.set(0);
                }
            } else if (KC == KeyCode.LEFT) {
                if (player.rectangleVelocityX.get() < 0) {
                    player.rectangleVelocityX.set(0);
                }
            } else if (KC == KeyCode.DOWN) {
                if (player.rectangleVelocityY.get() > 0) {
                    player.rectangleVelocityY.set(0);
                }
            } else if (KC == KeyCode.UP) {
                if (player.rectangleVelocityY.get() < 0) {
                    player.rectangleVelocityY.set(0);
                }
            }
        });
        
        TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build()
                .play();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
