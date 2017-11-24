package tc;
import static org.junit.Assert.*;
import org.junit.Test;
import code.Pirate;
import code.OceanMap;
import code.SailStrategy;
import code.HorizontalSail;
import code.VerticalSail;

public class PirateTest {

	OceanMap oceanMap;
	
	@Test
	public void test() {

		Pirate jack = new Pirate(oceanMap);
		Pirate krabs = new Pirate(oceanMap);
		SailStrategy horizontal = new HorizontalSail();
		SailStrategy vertical = new VerticalSail();
		jack.setSailStrategy(horizontal);
		krabs.setSailStrategy(vertical);
		jack.setX(3);
		jack.setY(3);
		krabs.setX(2);
		krabs.setY(4);
		krabs.sail();
		jack.sail();
		assertTrue(jack.getPirateLocation()!=krabs.getPirateLocation());	
	}

}
