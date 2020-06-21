package br.com.caelum.argentum.indicators;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.MockSerie;
import br.com.caelum.argentum.TimeSerie;

public class OpeningTest {

	@Test
	public void testOpeningIndicatorWithSimpleExample() {
		
		MockSerie series = new MockSerie(1,2,3,4,5,6);

		Opening opening = new Opening();
		
		
		assertEquals(6.0, opening.calculate(5, series),0.0001);
		assertEquals(5.0, opening.calculate(4, series),0.0001);
		assertEquals(4.0, opening.calculate(3, series),0.0001);
		assertEquals(3.0, opening.calculate(2, series),0.0001);
		assertEquals(2.0, opening.calculate(1, series),0.0001);
		assertEquals(1.0, opening.calculate(0, series),0.0001);
	}
	
	

	

}
