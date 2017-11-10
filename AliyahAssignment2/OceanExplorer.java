import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class OceanExplorer extends Application{

	boolean[][] islandMap;  
	Pane root;
	final int dimensions = 20;
	final int islandCount = 10;
	final int scalingFactor = 50;
	Image shipImage;
	Image pirateImage;
	ImageView shipImageView;
	ImageView pirateImageView;
	ImageView pirate2ImageView;
	OceanMap oceanMap;
	Scene scene;
	Ship ship;
	Pirate pirateShip;
	Pirate pirateShip2;
	
	@Override
	public void start(Stage mapStage) throws Exception {
		
		oceanMap = new OceanMap(dimensions, islandCount);
		islandMap = oceanMap.getMap(); 
		
		root = new AnchorPane();	
		drawMap();

		pirateShip = new Pirate(oceanMap);
		pirateShip2 = new Pirate(oceanMap);
		ship = new Ship(oceanMap);
		ship.addObserver(pirateShip);
		ship.addObserver(pirateShip2);
		loadShipImage();
		loadPirateImage(pirateShip, pirateShip2);

		scene = new Scene(root,1000,1000);
		mapStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		mapStage.setScene(scene);
		mapStage.show();
		startSailing();
	}
		 
    private void loadShipImage(){
		Image shipImage = new Image("ship.png",50,50,true,true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(oceanMap.getShipLocation().x*scalingFactor);
		shipImageView.setY(oceanMap.getShipLocation().y*scalingFactor);
		root.getChildren().add(shipImageView);
	}
    
    private void loadPirateImage(Pirate Pirate1, Pirate Pirate2) {
    	Image pirateImage = new Image("pirateShip.png",50,50,true,true);
		pirateImageView = new ImageView(pirateImage);
		pirateImageView.setX(Pirate1.getShipLocation().x*scalingFactor);
		pirateImageView.setY(Pirate1.getShipLocation().y*scalingFactor);
		root.getChildren().add(pirateImageView);
		
		pirate2ImageView = new ImageView(pirateImage);
		pirate2ImageView.setX(Pirate2.getShipLocation().x*scalingFactor);
		pirate2ImageView.setY(Pirate2.getShipLocation().y*scalingFactor);
		root.getChildren().add(pirate2ImageView);
    }
    
    
	private void startSailing(){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()){
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				}
				shipImageView.setX(ship.getShipLocation().x*scalingFactor);
				shipImageView.setY(ship.getShipLocation().y*scalingFactor);
				pirateImageView.setX(pirateShip.getShipLocation().x*scalingFactor);
				pirateImageView.setY(pirateShip.getShipLocation().y*scalingFactor);
				pirate2ImageView.setX(pirateShip2.getShipLocation().x*scalingFactor);
				pirate2ImageView.setY(pirateShip2.getShipLocation().y*scalingFactor);
			}
		});
	}
	
	// Draw ocean and islands
	public void drawMap(){
		for(int x = 0; x < dimensions; x++){
			for(int y = 0; y < dimensions; y++){
				Rectangle rect = new Rectangle(x*scalingFactor,y*scalingFactor,scalingFactor,scalingFactor);
				rect.setStroke(Color.BLACK);
				if(islandMap[x][y])
				    rect.setFill(Color.GREEN);
				else
					rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
			}
		}
	}
	
	public static void main(String[] args) {
     	launch(args);
     	
    }

}
