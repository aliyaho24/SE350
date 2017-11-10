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

	final int dimension = 20;
	final int scale = 50;
	int[][] loadedOcean;
	OceanMap oceanGrid;
	AnchorPane root;
	Scene scene;
	ImageView shipImageView, pirateImageView, pirateImageView2; 
	Ship ship;
	Pirate pirate1, pirate2;
	
	@Override
	public void start(Stage oceanStage) throws Exception {
		
		//generate the ocean grid
		oceanGrid = new OceanMap(10,20);
		loadedOcean = oceanGrid.getMap();
		
		root = new AnchorPane();
		drawMap();
		
		//create ship
		ship = new Ship(oceanGrid);
		pirate1 = new Pirate(oceanGrid);
		pirate2 = new Pirate(oceanGrid);
		ship.registerObserver(pirate1);
		ship.registerObserver(pirate2);
		loadShipImage();
			
		//setup
		scene = new Scene(root,1000,1000);
		oceanStage.setScene(scene);
		oceanStage.setTitle("Columbus Game");
		oceanStage.show();
		
		startSailing();	

	}
	
	public void drawMap() {
		for(int x = 0; x < dimension; x++){
			for(int y = 0; y < dimension; y++){
				Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
				rect.setStroke(Color.BLACK); // We want the black outline
				if (loadedOcean[x][y] != 1)
					rect.setFill(Color.PALETURQUOISE); // I like this color better than BLUE
				if (oceanGrid.blueOcean[x][y] == 1)
					rect.setFill(Color.GREEN); 
				root.getChildren().add(rect); // Add to the node tree in the pane
			}
		}
	}

	private void loadShipImage(){
		
		//load ship
		Image shipImage = new Image("ship.png",50,50, true, true);
		shipImageView = new ImageView(shipImage);
		int x = (int) oceanGrid.getShipLocation().getX();
		int y = (int) oceanGrid.getShipLocation().getY();
		shipImageView.setX(x * scale);
		shipImageView.setY(y * scale);
		root.getChildren().add(shipImageView);
		//load pirate 1
		Image pirateImage = new Image("pirateship.png",50,50, true, true);
		pirateImageView = new ImageView(pirateImage);
		int a = (int) pirate1.getShipLocation().getX();
		int b = (int) pirate1.getShipLocation().getY();
		pirateImageView.setX(a * scale);
		pirateImageView.setY(b * scale);
		root.getChildren().add(pirateImageView);
		//load pirate 2
		Image pirateImage2 = new Image("pirateship.png",50,50, true, true);
		pirateImageView2 = new ImageView(pirateImage2);
		int c = (int) pirate2.getShipLocation().getX();
		int d = (int) pirate2.getShipLocation().getY();
		pirateImageView2.setX(c * scale);
		pirateImageView2.setY(d * scale);
		root.getChildren().add(pirateImageView2);
		
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
				
				
			shipImageView.setX(ship.getShipLocation().x*scale);
			shipImageView.setY(ship.getShipLocation().y*scale);
			
			pirateImageView.setX(pirate1.getShipLocation().x*scale);
			pirateImageView.setY(pirate1.getShipLocation().y*scale);
			
			pirateImageView2.setX(pirate2.getShipLocation().x*scale);
			pirateImageView2.setY(pirate2.getShipLocation().y*scale);
			}
			 });
		
		
	}
	
	public static void main (String[] args) {
		launch(args);
	}
}
