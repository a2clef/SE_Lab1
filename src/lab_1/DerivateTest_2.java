package lab_1;

import static org.junit.Assert.*;

import org.junit.Test;

public class DerivateTest_2 {

	@Test
	public void testDerivate() {
		Calculator calcInst = new Calculator();
		Calculator.initialize("-2*x*yz+3x^2y-4x5yz^3+123");
		
		String result = Calculator.derivate('k');
		assertEquals(result,"variable does not exist");
	}

}
