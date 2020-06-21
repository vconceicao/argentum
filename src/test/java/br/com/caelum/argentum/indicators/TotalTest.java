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

public class TotalTest {

	@Test
	public void testCalculateTotalIndicator() {
		Serie series = new MockSerie(1,2,3,4,5,6);
		
		
		Total total = new Total();
		
		
		assertEquals(1000.0, total.calculate(0, series),0.0001);
	
	
	}
	
	

}
