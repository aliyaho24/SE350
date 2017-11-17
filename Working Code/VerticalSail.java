
public class VerticalSail implements SailStrategy{
	 
	int count = 0;
	
	public void sail(Pirate pirate) {
		
		if(count%4 < 2 && pirate.map.isOcean(pirate.pirateLocation.x, pirate.pirateLocation.y-1)) {
			pirate.pirateLocation.y--;
		}
		
		else if (count%4 >= 2 && pirate.map.isOcean(pirate.pirateLocation.x, pirate.pirateLocation.y+1)) {
			pirate.pirateLocation.y++;
		}	
		count++;		
	}

}
