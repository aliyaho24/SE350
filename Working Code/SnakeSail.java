
public class SnakeSail implements SailStrategy{
	
	boolean back = false;
	
	public void sail(Pirate pirate) {
		
		System.out.println(pirate.pirateLocation);
		if (back) {
			pirate.pirateLocation.x--;
			back = false;
		}
		else {
			pirate.pirateLocation.x++;
			back = true;
		}	
		
	}

}
