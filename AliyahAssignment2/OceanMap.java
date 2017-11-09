import java.awt.Point;
import java.util.Random;

public class OceanMap {
	
	int[][] blueOcean;
	int mapSize;
	Point shipLocation;
	Random random = new Random();
	
	
	public OceanMap(int islands, int size) {
		mapSize = size;
	//	shipLocation = new Point();
		initialize();
		addIslands(islands);
		Point temp = new Point(random.nextInt(10),random.nextInt(10));
		if (isOcean(temp.x, temp.y) ) {
			shipLocation = temp;
			updateOcean(shipLocation.x,shipLocation.y, 2);	
		}
		
	}
	
	public int[][] getMap(){
		return blueOcean;
	}
	
	public Point getShipLocation() {
		return shipLocation;
	}
	
	public void initialize() {
		
		blueOcean = new int[mapSize][mapSize];
		//set map to 0 for ocean	
		for (int i=0; i < mapSize; i++) {
			for(int j=0; j < mapSize; j++) {
				blueOcean[i][j] = 0;
			}
		}
	}
	
	public void addIslands(int n) {
		
		while (n > 0) {
			
			Random randomX = new Random();
			Random randomY = new Random();
			int x = randomX.nextInt(10);
			int y = randomY.nextInt(10);
			
			//if the random coordinate is ocean, place an island
			if (blueOcean[x][y] == 0) {
				blueOcean[x][y] = 1;
				n--;
			}		
		}	
	}
	
	public void updateOcean(int x, int y, int val) {
		blueOcean[x][y] = val;	
	}
	
	public boolean isOcean(int x, int y) {
		if (blueOcean[x][y] == 0) 
			return true;
		else
			return false;
	}
	
}
