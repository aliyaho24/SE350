import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Pirate implements Observer{

	Point pirateLocation;
	Point shipLocation;
    OceanMap oceanMap;
    Random rand = new Random();
    int x = 0;
    
    public Pirate(OceanMap oceanMap){    	
    	this.oceanMap = oceanMap;
    	while(x != -1)
    	{
    		pirateLocation= new Point(rand.nextInt(10),rand.nextInt(10));
    		if(oceanMap.islands[(int) pirateLocation.getX()][(int) pirateLocation.getY()])
    		{
    			x = 0;
    		}
    		else x = x -1;
    	}
    }
    
    public Point getShipLocation(){
    	return pirateLocation;
    }
    
    public void goEast(){
    	if(pirateLocation.x<oceanMap.getDimensions()-1 && oceanMap.isOcean(pirateLocation.x+1, pirateLocation.y)){
    		pirateLocation.x++;
    	} 
    }
    
    public void goWest(){
    	if(pirateLocation.x >0 && oceanMap.isOcean(pirateLocation.x-1, pirateLocation.y)){
    		pirateLocation.x--;
    	}  
    	
    }
    
    public void goNorth(){
    	if(pirateLocation.y>0 && oceanMap.isOcean(pirateLocation.x, pirateLocation.y-1)){
    		pirateLocation.y--;
    	}  
    	
    }
    
    public void goSouth(){
    	if(pirateLocation.y<oceanMap.getDimensions()-1 && oceanMap.isOcean(pirateLocation.x, pirateLocation.y+1)){
    		pirateLocation.y++;
    		
    	}  	
    }
    
    public void movePirate() {
    	if(pirateLocation.x - shipLocation.x < 0)
    		goEast();
    	else 
    		goWest(); 
    	
    	if(pirateLocation.y - shipLocation.y < 0)
    		goSouth();
    	else
    		goNorth();
    	
    } 
    
    @Override
	public void update(Observable ship, Object obj) {
		if(ship instanceof Ship) {
			shipLocation = ((Ship)ship).getShipLocation();
			movePirate();
		}
    }
}


