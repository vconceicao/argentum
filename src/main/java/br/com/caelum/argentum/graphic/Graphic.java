package br.com.caelum.argentum.graphic;

import java.io.IOException;
import java.io.OutputStream;

import br.com.caelum.argentum.Serie;
import br.com.caelum.argentum.indicators.Indicator;

public final class Graphic {

	private String title;
	private int begin;
	private int end;
	private Indicator indicator;
	private OutputStream stream;
	private Serie serie;

	public Graphic called(String title) {
		
		
		this.title = title;
		return this;
		
	}

	public Graphic withTemporalSerie(Serie serie) {
		
		this.serie = serie;
		return this;
	}

	public Graphic from(int begin) {
		// TODO Auto-generated method stub
		return this;
	}

	public Graphic to(int end) {
		this.end = end;
		return this;
	}

	public Graphic withIndicator(Indicator indicator) {
		this.indicator = indicator;
		return this;
		
	}

	public void save(OutputStream stream) throws IOException {
		
		this.stream = stream;
	
		GraphicBuilder graphicBuilder = new GraphicBuilder(serie, begin, end);
		graphicBuilder.createGraphic(title);
		graphicBuilder.plotIndicator(indicator);
		graphicBuilder.save(this.stream);
	
	
	}
	
	
	

	

	
}
