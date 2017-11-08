import java.awt.Point;
import java.util.Random;

public class Pirate implements Observer {
	Point shipPosition;
	Point piratePosition;
	OceanMap oceanMap;
	Random random = new Random();
	
	public Pirate(OceanMap map) {
		oceanMap = map;
		Point temp = new Point(random.nextInt(10),random.nextInt(10));
		if (oceanMap.isOcean(temp.x, temp.y))
			piratePosition = temp;
			oceanMap.updateOcean(temp.x, temp.y, 2);
	}
	
	public void update(Ship ship) {
		
		if (ship instanceof Ship) {
			shipPosition = ((Ship)ship).getShipLocation();
			movePirate();	
		}	
	}
	
	public Point getShipLocation() {
		return piratePosition;
	}
	
	public void movePirate() {
		
		int x = shipPosition.x;
		int y = shipPosition.y;
		
		oceanMap.updateOcean(x, y, 0);
		
		if (shipPosition != piratePosition) {
			
			
			if (shipPosition.x < piratePosition.x && shipPosition.y < piratePosition.y) { //ship is northwest
				if (piratePosition.x - 1 > 0 &&
					piratePosition.y - 1 > 0 &&
					oceanMap.isOcean(piratePosition.x - 1, piratePosition.y - 1)) {
						piratePosition.x--;
						piratePosition.y--;
				}
			}
			if (shipPosition.x == piratePosition.x && shipPosition.y < piratePosition.y) { //ship is north
				if (oceanMap.isOcean(piratePosition.x, piratePosition.y - 1)) {
							piratePosition.y--;
				}
				
			}
			if (shipPosition.x > piratePosition.x && shipPosition.y < piratePosition.y) { //ship is northeast
				if (piratePosition.x + 1 < 10 &&
					piratePosition.y - 1 > 0 &&
					oceanMap.isOcean(piratePosition.x + 1, piratePosition.y - 1)) {
						piratePosition.x++;
						piratePosition.y--;
				}
			}
			
			
			if (shipPosition.x < piratePosition.x && shipPosition.y == piratePosition.y) { //ship is west
				if (oceanMap.isOcean(piratePosition.x - 1, piratePosition.y)) {
					piratePosition.x--;
				}
			}
			if (shipPosition.x > piratePosition.x && shipPosition.y == piratePosition.y) { //ship is east
				if (oceanMap.isOcean(piratePosition.x + 1, piratePosition.y)) {
					piratePosition.x++;
				}
			}
			
			
			if (shipPosition.x < piratePosition.x && shipPosition.y > piratePosition.y) { //ship is south west
				if (piratePosition.x - 1 > 0 &&
					piratePosition.y + 1 < 10 &&
					oceanMap.isOcean(piratePosition.x - 1, piratePosition.y + 1)) {
						piratePosition.x--;
						piratePosition.y++;
				}
			}
			if (shipPosition.x == piratePosition.x && shipPosition.y > piratePosition.y) { //ship is south
				if (oceanMap.isOcean(piratePosition.x, piratePosition.y + 1)) {
					piratePosition.y++;
			}
			}
			if (shipPosition.x > piratePosition.x && shipPosition.y > piratePosition.y) {	//ship is south east
				if (piratePosition.x + 1 < 10 &&
					piratePosition.y + 1 < 10 &&
					oceanMap.isOcean(piratePosition.x + 1, piratePosition.y + 1)) {
						piratePosition.x++;
						piratePosition.y++;
				}
			}
				
		}
		
		
		
	}
	
}
