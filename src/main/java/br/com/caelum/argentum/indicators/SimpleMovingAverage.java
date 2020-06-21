package br.com.caelum.argentum.indicators;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.argentum.Serie;

public class SimpleMovingAverage implements Indicator{

	private final int days;
	private final Indicator anotherIndicator;
	private static final Logger logger = LoggerFactory.getLogger(SimpleMovingAverage.class);

	public SimpleMovingAverage(int days, Indicator anotherIndicator) {
		this.days = days;
		this.anotherIndicator = anotherIndicator;
		
	}

	public double calculate(int position, Serie series) {
		logger.info("Calculating simple moving average for position: " + position);
		
		double sum =0.0;
		
		for (int i = daysBehind(position) ; i <= position; i++) {
			sum+=anotherIndicator.calculate(i, series);
		}
		
		return sum/days;
	}

	private int daysBehind(int position) {
		return position - (days-1);
	}

	@Override
	public String toString() {
		return "Simple Moving Average of " + anotherIndicator.toString();
	}

}
