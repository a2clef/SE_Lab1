package lab_1;

import static org.junit.Assert.*;

import org.junit.Test;

public class DerivateTest_1 {

	@Test
	public void testDerivate() {
		Expression expr = new Expression();
		ExprInitialization.init(expr,"-2*x*yz+3x^2y-4x5yz^3+123");
		
		String result = ExprDerivate.derivate(expr, "!d/d x");
		
		assertEquals(result,"-2.0yz+6.0xy-20.0yz^3");
	}

}
