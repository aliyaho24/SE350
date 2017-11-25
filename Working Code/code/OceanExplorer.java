package code;

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
	
	@Override
	public void start(Stage mapStage) throws Exception {
		
		//generate the ocean
		oceanMap = OceanMap.getGrid();
		islandMap = oceanMap.getMap();
		
		root = new AnchorPane();
		drawMap();
		
		ship = new Ship();	
		treasure = new Treasure(oceanMap);

		EnemyFactory enemyFactory = new EnemyFactory(oceanMap);
		Enemy enemy1 = enemyFactory.createEnemy("submarine");
		Enemy enemy2 = enemyFactory.createEnemy("pirate");
		Enemy enemy3 = enemyFactory.createEnemy("submarine");
		enemy1.addToPane(root);
		enemy2.addToPane(root);
		enemy3.addToPane(root);
		spawnPirateFleet(2,2,2);
		
		// Adding monsters onto the map
//		spawnSerpents(3);
//		spawnLeviathans(1);
		
		serpent = new Serpent(scalingFactor, oceanMap,false);
		serpent.addToPane(root.getChildren());
		serpentThread = new Thread(serpent);
		serpentThread.start();
		
		levi = new Leviathan(scalingFactor, oceanMap, false);
		levi.addToPane(root.getChildren());
		leviThread = new Thread(levi);
		leviThread.start();
		
		ship.addMultipleObservers(oceanMap.enemyShips);
		loadShipImage();
		loadTreasureImage();
		
		scene = new Scene(root,1000,1000);
		mapStage.setScene(scene);
		mapStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		mapStage.show();
		startSailing();	
	}
	
	public void spawnSerpents(int i) {
		while (i > 0) {
			serpent = new Serpent(scalingFactor, oceanMap, false);
			serpent.addToPane(root.getChildren());
			serpentThread = new Thread(serpent);
			serpentThread.start();
			i--;
		}
	}
	
	public void spawnLeviathans(int j) {
		while (j>0) {
			levi = new Leviathan(scalingFactor, oceanMap, false);
			levi.addToPane(root.getChildren());
			leviThread =new Thread(levi);
			leviThread.start();
		}
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

	//method to spawn specific amounts of each pirate ship type
	public void spawnPirateFleet(int vertical, int horizontal, int chase) {
		
		EnemyFactory pirateFleetFactory = new EnemyFactory(oceanMap);
		SailStrategy v = new VerticalSail();
		SailStrategy h = new HorizontalSail();
		SailStrategy c = new ChaseSail();
		
		for (int i=vertical; i>0; i--) {
			pirateFleetFactory.createPirate(v).addToPane(root);}
		
		for (int i=horizontal; i>0; i--) {
			pirateFleetFactory.createPirate(h).addToPane(root);}
		
		for (int i=chase; i>0; i--) {
			pirateFleetFactory.createPirate(c).addToPane(root);}
	}
	
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
