package br.com.caelum.argentum.indicators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.MockSerie;
import br.com.caelum.argentum.TimeSerie;

public class ClosingTest {

	@Test
	public void testClosingIndicatorWithSimpleExample() {
		
		MockSerie series = new MockSerie(1,2,3,4,5,6);
		Closing closing = new Closing();
		
		assertEquals(6.0, closing.calculate(5, series), 0.0001);
		assertEquals(5.0, closing.calculate(4, series), 0.0001);
		assertEquals(4.0, closing.calculate(3, series), 0.0001);
		assertEquals(3.0, closing.calculate(2, series), 0.0001);
		assertEquals(2.0, closing.calculate(1, series), 0.0001);
		
		
	}
	

	


}
