package br.com.caelum.argentum.indicators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.argentum.MockSerie;

public class HighTest {

	@Test
	public void testHighIndicator() {

	MockSerie series = new MockSerie(1,2,3,4,5,6);
	
	High high = new High();
	assertEquals(6.0, high.calculate(5, series),0.0001);
	assertEquals(5.0, high.calculate(4, series),0.0001);
	assertEquals(4.0, high.calculate(3, series),0.0001);
	assertEquals(3.0, high.calculate(2, series),0.0001);
	assertEquals(2.0, high.calculate(1, series),0.0001);
	assertEquals(1.0, high.calculate(0, series),0.0001);
	
	
	}

}
