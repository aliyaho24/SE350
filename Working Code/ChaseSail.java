
public class ChaseSail implements SailStrategy {

	OceanMap oceanMap;
	
	public void sail(Pirate pirate) {
		
		oceanMap = pirate.map;
		int shipX = pirate.shipLocation.x;
		int shipY = pirate.shipLocation.y;
		int pirateX = pirate.pirateLocation.x;
		int pirateY = pirate.pirateLocation.y;
		
		if (pirate.shipLocation != pirate.pirateLocation) {
			
				//CC is east
				if(pirateX<oceanMap.getDimensions()-1 && pirateX < shipX && pirateY == shipY &&
					oceanMap.isOcean(pirateX+1, pirateY)){	
					pirate.pirateLocation.x++;
		    		} 
				
				//CC is west
			    	if(pirateX >0 && pirateX > shipX && pirateY == shipY &&
			    		oceanMap.isOcean(pirateX-1, pirateY)){
			    			pirate.pirateLocation.x--;
			    	}  
			    	
			    //CC is north
			    	if(pirateY>0 && pirateY > shipY && pirateX == shipX &&
			    		oceanMap.isOcean(pirateX, pirateY-1)){
			    			pirate.pirateLocation.y--;
			    	}  	
			    	
			    //CC is south
			    	if(pirateY<oceanMap.getDimensions()-1 && pirateY < shipY && pirateX == shipX &&
			    		oceanMap.isOcean(pirateX, pirateY+1)){
			    			pirate.pirateLocation.y++;
			    		}  
			    	
			    	//CC is northwest
			    if (shipX < pirateX && shipY < pirateY) {
						if (pirateX - 1 > 0 && pirateY - 1 > 0 &&
							oceanMap.isOcean(pirateX - 1, pirateY - 1)) {
								pirate.pirateLocation.x--;
								pirate.pirateLocation.y--;
						}			
					}
				
			    //CC is northeast
				if (shipX > pirateX && shipY < pirateY) { 
						if (pirateX + 1 < oceanMap.getDimensions() &&
							pirateY - 1 > 0 &&
							oceanMap.isOcean(pirateX + 1, pirateY - 1)) {
								pirate.pirateLocation.x++;
								pirate.pirateLocation.y--;
						}
				}
				
				//CC is southwest	
				if (shipX < pirateX && shipY > pirateY) {
					if (pirateX - 1 > 0 &&
						pirateY + 1 < oceanMap.getDimensions() &&
						oceanMap.isOcean(pirateX - 1, pirateY + 1)) {
							pirate.pirateLocation.x--;
							pirate.pirateLocation.y++;
						}
				}
				
				//CC is south east
				if (shipX > pirateX && shipY > pirateY) {	
					if (pirateX + 1 < oceanMap.getDimensions() &&
						pirateY + 1 < oceanMap.getDimensions() &&
						oceanMap.isOcean(pirateX + 1, pirateY + 1)) {
							pirate.pirateLocation.x++;
							pirate.pirateLocation.y++;
						}
				} 	
		}
	}
}



