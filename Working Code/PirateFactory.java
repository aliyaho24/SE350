
public class PirateFactory {
	OceanMap map;
	
	public PirateFactory(OceanMap oceanmap) {
		this.map = oceanmap;
	}
	
	public Pirate createPirate() {
		Pirate pirate = new Pirate(map);
		return pirate;
	}
	
}
