package lab_1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimplifyTest_C3 {

	@Before
	public void setUp() throws Exception {
		return;
	}

	@Test
	public void testSimplify() {
		Calculator calcInst = new Calculator();
		Calculator.initialize("-2*x*yz+3x^2y-4x5yz^3+123");
		
		String result = Calculator.simplify("!simplify x=1 y=2 z=3");
		assertEquals(result,"-963.0");
		//fail("Not yet implemented");
	}

}
