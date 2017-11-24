import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application{

	boolean[][] islandMap;
	boolean win = false;
	AnchorPane root;
	final int scalingFactor = 50;
	OceanMap oceanMap;
	Image shipImage;
	Image winImage;
	Image treasureImage;
	ImageView shipImageView;
	ImageView treasureImageView;
	ImageView winImageView;
	Scene scene;
	Treasure treasure;
	Ship ship;
	Serpent serpent;
	Thread serpentThread;
	Leviathan levi;
	Thread leviThread;
	Monster[] component = new Monster[10];
	
	
	@Override
	public void start(Stage mapStage) throws Exception {
		
		//generate the ocean
		oceanMap = OceanMap.getGrid();
		islandMap = oceanMap.getMap();
		
		root = new AnchorPane();
		drawMap();
		
		ship = new Ship(oceanMap);
		
		treasure = new Treasure(oceanMap);

		//spawning a ship doing vertical and horizontal sail just for test purposes 
		//so you guys can see it work :)
//		PirateFactory factory = new PirateFactory(oceanMap);
//		Pirate pirate = factory.createPirate();
//		Pirate pirate2 = factory.createPirate();
//		ship.addObserver(pirate);
//		ship.addObserver(pirate2);
//		pirate2.addToPane(root);
//		pirate.addToPane(root);
//		SailStrategy vertical = new VerticalSail();
//		SailStrategy chase = new ChaseSail();
//		pirate.setSailStrategy(chase);
//		pirate2.setSailStrategy(chase);
		
//		pirate.start();
//		spawnPirates(1);
		loadShipImage();
		loadTreasureImage();
		
		// Adding monsters (test)
		serpent = new Serpent(scalingFactor, oceanMap,false);
		serpent.addToPane(root.getChildren());
		serpentThread = new Thread(serpent);
		serpentThread.start();
		
		levi = new Leviathan(scalingFactor, oceanMap, false);
		levi.addToPane(root.getChildren());
		leviThread = new Thread(levi);
		leviThread.start();
			
		scene = new Scene(root,1000,1000);
		mapStage.setScene(scene);
		mapStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		mapStage.show();
		startSailing();	
	}
	
	//draw ocean and islands
	public void drawMap(){
		for(int x = 0; x < oceanMap.getDimensions(); x++){
			for(int y = 0; y < OceanMap.getGrid().getDimensions(); y++){
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
	
	
//	public void spawnPirates(int n) {
//		
//		PirateFactory factory = new PirateFactory(oceanMap);
//		while(n>0) {
//			Pirate pirate = factory.createPirate();
//			ship.addObserver(pirate);
//			pirate.addToPane(root);
//			SailStrategy snake = new SnakeSail(); 
//			pirate.setSailStrategy(snake);
//			pirate.start();
//			n--;
//		}
//	}
	
	private void loadShipImage(){	
		Image shipImage = new Image("ship.png",50,50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(oceanMap.getShipLocation().x*scalingFactor);
		shipImageView.setY(oceanMap.getShipLocation().y*scalingFactor);
		root.getChildren().add(shipImageView);
		
	}
	
	private void loadTreasureImage(){
		Image treasureImage = new Image("Treasure.png",50,50,true,true);
		treasureImageView = new ImageView(treasureImage);
		treasureImageView.setX(oceanMap.getTreasureLocation().x*scalingFactor);
		treasureImageView.setY(oceanMap.getTreasureLocation().y*scalingFactor);
		root.getChildren().add(treasureImageView);
		
		Image winImage = new Image("win.gif",50, 50, false, false);
		winImageView = new ImageView(winImage);
		winImageView.setX(oceanMap.getTreasureLocation().x*scalingFactor);
		winImageView.setY(oceanMap.getTreasureLocation().y*scalingFactor);
	}
	private void startSailing() {
		 scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			public void handle(KeyEvent ke) {
				if (!win)
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
			
			if(ship.getShipLocation().equals(treasure.getTreasureLocation())){	
	    		  setFoundTargetImage();
	    		win = true;
	    		}	
			
			}
			
		});	
	}
	
	protected void setFoundTargetImage(){
    	if(root.getChildren().contains(shipImageView)){
			root.getChildren().remove(shipImageView);	
			root.getChildren().add(winImageView);		
		}
    }
	
	public static void main (String[] args) {
		launch(args);
	}
}
