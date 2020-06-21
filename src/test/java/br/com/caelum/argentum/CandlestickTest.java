package br.com.caelum.argentum;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;


public class CandlestickTest {


	@Test(expected=IllegalArgumentException.class)
	public void testMaxPriceMustNotBeLowerThanMin(){
		
		Candle candlestick = new Candle(6, 6, 5, 4, 30, Calendar.getInstance());
		
		
		
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCandlestickDateMustNotBeNull(){
		
		Candle candlestick = new Candle(6, 5,5, 5, 30, null);
		
		
		
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCandlestickValuesMustNotBeNegative(){
		
		Candle candlestick = new Candle(4,4,-4,4,4,Calendar.getInstance());
		
		
		
		
	}
	
	@Test
	public void testCandlestickIsHighWhenOpeningEqualClosing(){
		
		Candle candlestick = new Candle(4,4,4,4,4,Calendar.getInstance());
		
		assertTrue("Is High?", candlestick.isHigh());
	}
	
	

}
