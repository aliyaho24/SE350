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
	AnchorPane root;
	final int dimensions = 20;
	final int islandCount = 10;
	final int scalingFactor = 50;
	OceanMap oceanMap;
	Image shipImage;
	ImageView shipImageView;
	Scene scene; 
	Ship ship;
	
	@Override
	public void start(Stage mapStage) throws Exception {
		
		//generate the ocean
		oceanMap = new OceanMap(dimensions,islandCount);
		islandMap = oceanMap.getMap();
		
		root = new AnchorPane();
		drawMap();
		
		PirateFactory factory = new PirateFactory(oceanMap);
		Pirate pirate = factory.createPirate();
		ship = new Ship(oceanMap);
		ship.addObserver(pirate);
		loadShipImage();
		pirate.addToPane(root);
		SailStrategy snake = new SnakeSail();
		pirate.setSailStrategy(snake);
		pirate.start();
			
		scene = new Scene(root,1000,1000);
		mapStage.setScene(scene);
		mapStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		mapStage.show();
		startSailing();	
	}
	
	//draw ocean and islands
	public void drawMap() {
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

	private void loadShipImage(){	
		Image shipImage = new Image("ship.png",50,50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(oceanMap.getShipLocation().x*scalingFactor);
		shipImageView.setY(oceanMap.getShipLocation().y*scalingFactor);
		root.getChildren().add(shipImageView);
	}
	
	private void startSailing() {
		 scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
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
			
			}
		});	
	}
	
	public static void main (String[] args) {
		launch(args);
	}
}
