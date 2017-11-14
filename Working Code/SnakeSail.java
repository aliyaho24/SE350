
public class SnakeSail implements SailStrategy{
	
	int count = 0;
	
	public void sail(Pirate pirate) {
		
		//modulo of count to allow back and forth movement
		if (count%6 <= 2 && pirate.map.isOcean(pirate.pirateLocation.x -1, pirate.pirateLocation.y)) {
			pirate.pirateLocation.x--;	
		}
		else if (count%6 >= 3 && pirate.map.isOcean(pirate.pirateLocation.x + 1, pirate.pirateLocation.y)){
				pirate.pirateLocation.x++;
			}
		count++;
	}	
}
