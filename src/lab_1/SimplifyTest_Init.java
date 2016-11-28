package lab_1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimplifyTest_Init {

	@Before
	public void setUp() throws Exception {
		return;
	}

	@Test
	public void testSimplify() {
		Expression expr = new Expression();
		ExprInitialization.init(expr,"-2*x*yz+3x^2y-4x5yz^3+123");
		String result = ExprSimplify.simplify(expr, "!simplify");
		assertEquals(result,"-2.0xyz+3.0x^2y-20.0xyz^3+123.0");
		//fail("Not yet implemented");
	}

}
