package code;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.collections.ObservableList;
import javafx.scene.Node;

//Composite: 

public class Leviathan implements Monster, Runnable {
	
	/*
	 Jobs:
	 	- create new instance of Leviathan when 2 serpents collide
	 	- increase size of Leviathan each time it collides with a serpent
	 	- Keep doing this until ALL serpents are devoured and turned
	 	  into a leviathan
	 	- DON'T allow leviathan to be devoured by another leviathan
	 	- Change color indicating that indicates difference between
	 	  serpent and leviathan
	 */
	
	OceanMap map;
	Boolean running = true;
	int radius;
	Random rand = new Random();
	int scalingFactor;
	int length = 0;
	Point location;
	// length of serpents that collided
	// should start with 2 serpents?
	
//	SerpentSprite[] serpentSprite;
	List<Monster> components = new ArrayList<Monster>();
	
	public Leviathan(int scale, OceanMap map) {
		
		this.radius = 20;
		this.scalingFactor = scale;
		this.map = map;
		map.getMap();
		int x = rand.nextInt(10);
		int y = rand.nextInt(10);
		
//		length = serpentSprite.length;
			
	}	
	
	
	
	public void addToPane(ObservableList<Node> scene) {
		Circle circle = new Circle();
		circle.setFill(Color.RED);
		scene.add(circle);
	}
	
	@Override
	public void add(Monster monster) {
		components.add(monster);
	}

	@Override
	public void remove(Monster monster) {
		components.remove(monster);
	}

//	@Override
//	public boolean ContainsPoint(Point point) {
//		return false;
//	}
//
//	@Override
//	public Point getCurrentLocation() {
//		return location;
//	}
//
//	@Override
//	public void setLocation(Point pos) {
//		this.location = pos;
//	}
	
	public void increaseSize(Monster monster) {
		// increase size of leviathan if the
		// leviathan collides with serpent(s)
		radius = radius+1;
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			for (SerpentSprite sprite : serpentSprite) {
//				int xMove = sprite.getX() + rand.nextInt(3)-1;
//				if (xMove >=0 && xMove<=20) {
//					sprite.setX(xMove);
//				}
//				int yMove = sprite.getY() + rand.nextInt(3)-1;
//				if (yMove>=0 && yMove<=20) {
//					sprite.setY(yMove);
//				}
//			}
		}
	}
	
}
