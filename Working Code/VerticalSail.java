
public class VerticalSail implements SailStrategy{
	 
	int count = 0;
	
	public void sail(Pirate pirate) {
		
		//modulo of count to allow back and forth movement
		if (count%6 <= 2 && pirate.map.isOcean(pirate.pirateLocation.x, pirate.pirateLocation.y-1)) {
			pirate.pirateLocation.y--;	
		}
		else if (count%6 >= 3 && pirate.map.isOcean(pirate.pirateLocation.x, pirate.pirateLocation.y+1)){
				pirate.pirateLocation.y++;
			}
		count++;
	}

}
