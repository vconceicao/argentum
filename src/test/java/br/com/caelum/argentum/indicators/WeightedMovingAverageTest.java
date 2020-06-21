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

public class WeightedMovingAverageTest {

	@Test
	public void testWeightedMovingAverage() {
		Serie series = new MockSerie(1,2,3,4,5,6);
		
		
		Indicator wma = new WeightedMovingAverage(3, new Closing());
		
		assertEquals(14d /6, wma.calculate(2, series), 0.0001);
		assertEquals(20d /6, wma.calculate(3, series), 0.0001);
		assertEquals(26d /6, wma.calculate(4, series), 0.0001);
		assertEquals(32d /6, wma.calculate(5, series), 0.0001);
		
	
	}
	
	@Test
	public void testWeightedMovingAverageWithOpeningIndicator() {
		Serie series = new MockSerie(1,2,3,4,5,6);
		
		
		Indicator wma = new WeightedMovingAverage(3, new Opening());
		
		assertEquals(14d /6, wma.calculate(2, series), 0.0001);
		assertEquals(20d /6, wma.calculate(3, series), 0.0001);
		assertEquals(26d /6, wma.calculate(4, series), 0.0001);
		assertEquals(32d /6, wma.calculate(5, series), 0.0001);
		
		
	}
	
	@Test
	public void testWeightedMovingAverageWithTotalIndicator() {
		Serie series = new MockSerie(1,2,3,4,5,6);
		
		
		Indicator wma = new WeightedMovingAverage(3, new Total());
		
		assertEquals(6000.0d /6, wma.calculate(2, series), 0.0001);
		assertEquals(6000.0d /6, wma.calculate(3, series), 0.0001);
		assertEquals(6000.0d /6, wma.calculate(4, series), 0.0001);
		assertEquals(6000.0d /6, wma.calculate(5, series), 0.0001);
		
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSimpleMediaAverageWithPositionMinorThanDay() {

	
		Serie series = new MockSerie(1,2,3,4,5,6);
		
		WeightedMovingAverage wma = new WeightedMovingAverage(2, new Closing());
		

		wma.calculate(0, series);
		
		
	
	}
	
}
