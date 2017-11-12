import java.awt.Point;

public class ChaseSail implements SailStrategy {
		
	Point pirateLocation, shipLocation;
	OceanMap oceanMap;
	
	public void sail(Pirate pirate) {
		
		Point pirateLocation = pirate.getPirateLocation();
		Point shipLocation = pirate.getShipLocation();
		OceanMap oceanMap = pirate.map;

		if(pirateLocation.x - shipLocation.x < 0) 
			goEast();
		else 
			goWest(); 
		
    	 	if(pirateLocation.y - shipLocation.y < 0)
    	 		goSouth();
    	 	else
    	 		goNorth();
    	 	
    	 	//update pirate coordinates
    	 	pirate.setX(pirateLocation.x);
    	 	pirate.setY(pirateLocation.y);
	}
	
	public void goEast(){
		if(pirateLocation.x<oceanMap.getDimensions()-1 && 
			oceanMap.isOcean(pirateLocation.x+1, pirateLocation.y)){
    				pirateLocation.x++;
    		} 
    }
	
	public void goWest(){
	    	if(pirateLocation.x >0 && 
	    		oceanMap.isOcean(pirateLocation.x-1, pirateLocation.y)){
	    			pirateLocation.x--;
	    	}  
	}
	    
	public void goNorth(){
	    	if(pirateLocation.y>0 && 
	    		oceanMap.isOcean(pirateLocation.x, pirateLocation.y-1)){
	    			pirateLocation.y--;
	    	}  	
	  }
	    
	public void goSouth(){
	    	if(pirateLocation.y<oceanMap.getDimensions()-1 && 
	    		oceanMap.isOcean(pirateLocation.x, pirateLocation.y+1)){
	    			pirateLocation.y++;
	    		}  
	}
}
