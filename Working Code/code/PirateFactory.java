package code;

public class PirateFactory {
	OceanMap map;
	
	public PirateFactory(OceanMap oceanmap) {
		this.map = oceanmap;
	}
	
	public Pirate createPirate() {
		Pirate pirate = new Pirate(map);
		pirate.draw();
		return pirate;
	}
	
}
