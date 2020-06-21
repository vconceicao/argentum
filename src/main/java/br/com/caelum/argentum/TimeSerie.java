package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeSerie implements Serie {

	private List<Candle> candles = new ArrayList<>();
	private static final Logger logger = LoggerFactory.getLogger(TimeSerie.class);

	public TimeSerie(List<Candle> candles) {
		
		if (candles == null) {
			logger.error("I can't create a Serie with a nul candle list");
			throw new IllegalArgumentException("Time Series cant accept a null list");
		}
		this.candles = candles;
	}

	public Candle getCandle(int index) {
		return candles.get(index);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return this.candles.size();
	}

}
