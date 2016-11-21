package lab_1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimplifyTest_E1 {

	@Before
	public void setUp() throws Exception {
		return;
	}

	@Test
	public void testSimplify() {
		Calculator calcInst = new Calculator();
		Calculator.initialize("-2*x*yz+3x^2y-4x5yz^3+123");
		
		String result = Calculator.simplify("!simplify 1=2");
		assertEquals(result,"illegal variable");
		//fail("Not yet implemented");
	}

}
