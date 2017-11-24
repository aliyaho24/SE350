package tc;
import static org.junit.Assert.*;
import org.junit.Test;
import code.Pirate;
import code.SailStrategy;
import code.VerticalSail;
import code.HorizontalSail;
import code.OceanMap;

public class PirateTest {

	OceanMap oceanMap;
	
	@Test
	public void test() {
		fail("Not yet implemented");
		Pirate jack = new Pirate(oceanMap);
		Pirate krabs = new Pirate(oceanMap);
		SailStrategy horizontal = new HorizontalSail();
		SailStrategy vertical = new VerticalSail();
		jack.setSailStrategy(horizontal);
		krabs.setSailStrategy(vertical);
		jack.setX(3);
		jack.setY(3);
		krabs.setX(5);
		krabs.setY(3);
		
	}

}
