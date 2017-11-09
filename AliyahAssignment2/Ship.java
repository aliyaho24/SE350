import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Ship implements Subject {

	Point location;
	OceanMap oceanMap;
	List<Observer> observers = new LinkedList<Observer>();
	
	public Ship(OceanMap map) {
	
		oceanMap = map;
		location = map.getShipLocation();
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		if(observers.contains(o))
			observers.remove(o);
	}
	
	public void notifyObservers() {
		for (Observer shipObserver: observers)
			shipObserver.update(this);
	}
	
	public Point getShipLocation() {
		return location;
	}
	
	public void setLocation(int x, int y) {
		Point temp = new Point(x,y);
		location.setLocation(temp);
	}
	public void goEast() {
		
		if (location.getX() + 1 < 10 && oceanMap.isOcean(location.x + 1, location.y)) {
			int x = (int) location.getX() + 1;
			int y = (int) location.getY();
			setLocation(x,y);
		}
		
		notifyObservers();
		
	}
	public void goWest() {
		
		if (location.getX() - 1  >= 0 && oceanMap.isOcean(location.x - 1, location.y)) {
			int x = (int) location.getX() - 1;
			int y = (int) location.getY();
			setLocation(x,y);	
		}
		notifyObservers();
		
	}
	public void goNorth() {
		
		if (location.getY() - 1 >= 0 && oceanMap.isOcean(location.x, location.y - 1)) {
			int x = (int) location.getX();
			int y = (int) location.getY() - 1;
			setLocation(x,y);	
		}
		notifyObservers();
	}
	public void goSouth() {
			
		if (location.getY() + 1 <10 && oceanMap.isOcean(location.x, location.y + 1)) {
			int x = (int) location.getX();
			int y = (int) location.getY() + 1;
			setLocation(x,y);
		}
		notifyObservers();
	}
	
}
