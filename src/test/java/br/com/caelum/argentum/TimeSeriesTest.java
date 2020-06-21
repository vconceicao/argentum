package br.com.caelum.argentum;

import java.util.Collections;

import org.junit.Test;

public class TimeSeriesTest {

	@Test(expected=IndexOutOfBoundsException.class)
	public void testCantGetACandleOutBoundsOfList() {
		
		new TimeSerie(Collections.emptyList()).getCandle(1);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCantPassANullListToTimeSeries() {
		
		Serie timeSeries = new TimeSerie(null);
	}

}
