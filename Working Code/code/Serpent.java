package code;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.awt.Point;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.scene.Node;


class SerpentSprite {
	int x;
	int y;
	Circle circle;
	int scalingFactor;
	int radius = 20;
	Point serpentLocation;
	SerpentSprite(int x, int y, int scalingFactor){
		this.x = x;
		this.y = y;
		serpentLocation = new Point (x,y);
		circle= new Circle();
		setPositionX(x);
		setPositionY(y);
		circle.setRadius(radius);
		this.scalingFactor = scalingFactor;
	}
	
	Circle getCircle(){
		return circle;
	}
	
	void setX(int x){
		this.x = x;
		setPositionX(x);
	}
	
	void setY(int y){
		this.y = y;
		setPositionY(y);
	}
	
	int getX(){
		return x;
	}
	
	int getY(){
		return y;
	}
	
	public void setLineColor(Circle circle, Color color){
		circle.setStroke(color);
		circle.setFill(color);
	}
	
	public void setPositionX(int x){
		circle.setCenterX(x*scalingFactor + (scalingFactor/2));
	}
	
	public void setPositionY(int y){
		circle.setCenterY(y*scalingFactor + (scalingFactor/2));
	}
	
	public boolean ContainsPoint(Point point) {
		return false;
	}

	public Point getCurrentLocation() {
		return serpentLocation;
	}

	public void setLocation(Point pos) {
		this.serpentLocation = pos;
	}
}

public class Serpent implements Monster, Runnable{
	
	OceanMap map;
	Boolean running = true;
	int radius;
	Random rand = new Random();
	int scalingFactor;
	SerpentSprite[] serpentSprite = new SerpentSprite[10];
	
	public Serpent(int scalingFactor, OceanMap map) {
		this.radius = 20;
		this.scalingFactor = scalingFactor;
		this.map = map;
		map.getMap();
		for (int j=0; j<10; j++) {
			int x=rand.nextInt(15);
			int y=rand.nextInt(15);
			serpentSprite[j] = new SerpentSprite(x, y, scalingFactor);
		}
	}
	
	public int getSerpentSize() {
		return serpentSprite.length;
	}
	
	// checks if two points collide with each other
	// (if 2 points have same location on map)
	public boolean collisionMatch() {
		for (int i=0; i<20; i++) {
			for (int j=1; j<20; j++) {
				if (serpentSprite[i].getCurrentLocation() == serpentSprite[j].getCurrentLocation()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addToPane(ObservableList<Node> scene) {
		for (SerpentSprite sprite: serpentSprite) {
			Circle circle = sprite.getCircle();
			circle.setFill(Color.LIMEGREEN);
			scene.add(circle);
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (SerpentSprite sprite : serpentSprite) {
				int xMove = sprite.getX() + rand.nextInt(3)-1;
				if (xMove >=0 && xMove<=20) {
					sprite.setX(xMove);
				}
				int yMove = sprite.getY() + rand.nextInt(3)-1;
				if (yMove>=0 && yMove<=20) {
					sprite.setY(yMove);
				}
			}
		}
	}

	@Override
	public void add(Monster monster) {
		// doesn't apply to leaf node
	}

	@Override
	public void remove(Monster monster) {
		// doesn't apply to leaf node
	}
}
