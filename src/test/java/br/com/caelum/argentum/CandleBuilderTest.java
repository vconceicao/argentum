package br.com.caelum.argentum;

import org.junit.Test;

public class CandleBuilderTest {

	@Test(expected=IllegalStateException.class)
	public void testMustBuildAValidCandle() {
		
		
		
		Candle candle = new CandleBuilder().buildCandle();
		
		
	}

}
