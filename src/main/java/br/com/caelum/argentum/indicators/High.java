package br.com.caelum.argentum.indicators;

import br.com.caelum.argentum.Serie;

public class High implements Indicator {

	@Override
	public double calculate(int position, Serie series) {
		return series.getCandle(position).getHigh();
	}

	@Override
	public String toString() {
		return "High Indicator";
	}
	
	
	

}
