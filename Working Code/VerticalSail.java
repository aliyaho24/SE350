import java.awt.Point;

public class VerticalSail implements SailStrategy{
	 
	boolean up = true;
	
	public void sail(Pirate pirate) {
		
		//set upper and lower bounds for movement
		final int topBounds = pirate.pirateLocation.y - 2;
		final int lowBounds = pirate.pirateLocation.y + 2;
		
	
		//ifs for oscillating movement
		if (up && pirate.pirateLocation.y > topBounds) {
			pirate.pirateLocation.y--;
		}
		else if (!up && pirate.pirateLocation.y < lowBounds) {
			pirate.pirateLocation.y++;
		} 
		else if (pirate.pirateLocation.y == topBounds) {
			pirate.pirateLocation.y++;
			up = false;
		} 
		else if (pirate.pirateLocation.y == lowBounds) {
			pirate.pirateLocation.y--;
			up = true;
		} 	
		
		System.out.println(up);
		System.out.println(pirate.pirateLocation.y);
	}

}
