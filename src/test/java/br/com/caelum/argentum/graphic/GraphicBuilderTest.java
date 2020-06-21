package br.com.caelum.argentum.graphic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import br.com.caelum.argentum.MockSerie;
import br.com.caelum.argentum.Serie;
import br.com.caelum.argentum.indicators.Closing;
import br.com.caelum.argentum.indicators.SimpleMovingAverage;

public class GraphicBuilderTest {

	@Test
	public void testGraphicBuilderFluentInterface() throws FileNotFoundException, IOException {

		
		
		Serie serie = new MockSerie(1,2,3,4,3,4,5,4,3);
		new Graphic()
		.called("CandleSticks")
		.withTemporalSerie(serie)
		.from(1).to(5)
		.withIndicator(new SimpleMovingAverage(0, new Closing()))
		.save(new FileOutputStream("graphic.png"));
	}

}
