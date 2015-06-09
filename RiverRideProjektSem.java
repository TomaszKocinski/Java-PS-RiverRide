package riverrideprojektsem;


 import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
 import javafx.scene.Scene; 
 import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
 import javafx.scene.paint.Color;
 import javafx.stage.Stage; 



 public class RiverRideProjektSem extends Application {
    Player player=new Player();
 
    public void start(Stage stage) {
         Group FP = new Group();
         Scene scene = new Scene(FP,500,600);
         scene.setFill(Color.BLACK);
         AnchorPane CP = new AnchorPane();
         AnchorPane.setTopAnchor(FP,0.0);
         AnchorPane APL= new AnchorPane();
         
         APL.setRightAnchor(CP, 200.0);
         APL.setMinSize(200,0);
         APL.setBackground(Background.EMPTY);
         APL.setStyle("-fx-background-color: #42FFFF;");
         AnchorPane APR= new AnchorPane();
         APR.setStyle("-fx-background-color: #4242FF;");
         APR.setMinSize(200, 200);
         
        FP.getChildren().addAll(CP);
         
         CP.getChildren().add(player);
         player.setTranslateX(player.getX1());
         player.setTranslateY(player.getY1());
         
         
         scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

             public void handle(KeyEvent e) {
                 KeyCode KC=e.getCode();
                 System.out.println(KC);
                 if(KC.equals(KeyCode.LEFT)){
                     player.setX1(-10);
                 }
                 if(KC.equals(KeyCode.RIGHT)){
                     player.setX1(10);
                     
                 }
                 if(KC.equals(KeyCode.UP)){
                     player.setY1(-10);
                 }
                 if(KC.equals(KeyCode.DOWN)){
                     player.setY1(10);
                 }
                 player.setTranslateX(player.getX1());
                 player.setTranslateY(player.getY1());
             }
         });
        
         stage.setTitle("ImageView");
         
         stage.setScene(scene); 
         //stage.sizeToScene(); 
         stage.show(); 
        
     }

     public static void main(String[] args) {
        launch(args);
     }
 }