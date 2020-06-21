package br.com.caelum.argentum.indicators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.MockSerie;
import br.com.caelum.argentum.Serie;
import br.com.caelum.argentum.TimeSerie;

public class SimpleMovingAverageTest {

	@Test
	public void testSimpleExampleForSMA() {

	
		Serie serie = new MockSerie(1,2,3,4,3,4,5,4,3);
		
		Indicator sma = new SimpleMovingAverage(3, new Closing());
		

		assertEquals(2.0, sma.calculate(2, serie), 0.00001);
		assertEquals(3.0, sma.calculate(3, serie), 0.00001);
		assertEquals(10.0 / 3, sma.calculate(4, serie), 0.00001);
		assertEquals(11.0 / 3, sma.calculate(5, serie), 0.00001);
		assertEquals(4.0, sma.calculate(6, serie), 0.00001);
		assertEquals(13.0 / 3, sma.calculate(7, serie), 0.00001);
		assertEquals(4.0, sma.calculate(8, serie), 0.00001);
		
		
	
	}
	
	
	@Test
	public void testSimpleExampleForWithOpeningIndicator() {
		
		
		Serie serie = new MockSerie(1,2,3,4,3,4,5,4,3);
		
		Indicator sma = new SimpleMovingAverage(3, new Opening());
		
		
		assertEquals(2.0, sma.calculate(2, serie), 0.00001);
		assertEquals(3.0, sma.calculate(3, serie), 0.00001);
		assertEquals(10.0 / 3, sma.calculate(4, serie), 0.00001);
		assertEquals(11.0 / 3, sma.calculate(5, serie), 0.00001);
		assertEquals(4.0, sma.calculate(6, serie), 0.00001);
		assertEquals(13.0 / 3, sma.calculate(7, serie), 0.00001);
		assertEquals(4.0, sma.calculate(8, serie), 0.00001);
		
		
		
	}
	
	
	@Test
	public void testSimpleExampleForWithTotalIndicator() {
		
		
		Serie serie = new MockSerie(1,2,3,4,3,4,5,4,3);
		
		Indicator sma = new SimpleMovingAverage(3, new Total());
		
		
		assertEquals(1000.0, sma.calculate(2, serie), 0.00001);
		
		
		
	}
	
	
	@Test
	public void testSimpleMediaAverageWithDayParameter() {

	
		

		Serie serie = new MockSerie(1,2,3,4,3,4,5,4,3);
		
	
		Indicator sma = new SimpleMovingAverage(2, new Closing());
		

		assertEquals(5.0/2, sma.calculate(2, serie), 0.00001);
		assertEquals(7.0/2, sma.calculate(3, serie), 0.00001);
		assertEquals(7.0/2, sma.calculate(4, serie), 0.00001);
		assertEquals(9.0/2, sma.calculate(6, serie), 0.00001);
		
		
	
	}
	
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSimpleMediaAverageWithPositionMinorThanDay() {

	
		Serie serie = new MockSerie(1,2,3,4,3,4,5,4,3);
		Indicator sma = new SimpleMovingAverage(2, new Closing());
		

		sma.calculate(0, serie);
		
		
	
	}


}
