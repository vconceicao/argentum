package br.com.caelum.argentum.indicators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.argentum.MockSerie;

public class LowTest {

	@Test
	public void testLowIndicator() {

		MockSerie series = new MockSerie(1,2,3,4,5,6);
		
		Low low = new Low();
		assertEquals(6.0, low.calculate(5, series),0.0001);
		assertEquals(5.0, low.calculate(4, series),0.0001);
		assertEquals(4.0, low.calculate(3, series),0.0001);
		assertEquals(3.0, low.calculate(2, series),0.0001);
		assertEquals(2.0, low.calculate(1, series),0.0001);
		assertEquals(1.0, low.calculate(0, series),0.0001);
		
	
	
	}

}
