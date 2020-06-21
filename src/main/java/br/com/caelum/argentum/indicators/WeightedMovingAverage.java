package br.com.caelum.argentum.indicators;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import br.com.caelum.argentum.Serie;

public class WeightedMovingAverage implements Indicator {

	
	
	private int days;
	private Indicator anotherIndicator;
	private static final Logger logger = LoggerFactory.getLogger(WeightedMovingAverage.class);

	

	public WeightedMovingAverage(int days, Indicator anotherIndicator) {
		this.days = days;
		this.anotherIndicator = anotherIndicator;
	}


	/* (non-Javadoc)
	 * @see br.com.caelum.argentum.indicadores.Indicator#calculate(int, br.com.caelum.argentum.TimeSeries)
	 */
	@Override
	public double calculate(int position, Serie series){
		
		logger.info("Calculating weighted moving average for position " + position);
		double sum = 0.0;
		int weight = 0;
		
		
		for(int i = numbersOfDaysBehind(position); i<=position; i++){
			sum += anotherIndicator.calculate(i, series) * ++weight;
		}
		
		
		return sum/(weight+days);
	}


	private int numbersOfDaysBehind(int position) {
		return position -(days-1);
	}
	
	@Override
	public String toString() {
		return "Weighted Moving Average of " + anotherIndicator.toString();
	}
}
