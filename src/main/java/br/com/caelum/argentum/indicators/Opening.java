package br.com.caelum.argentum.indicators;

import br.com.caelum.argentum.Serie;

public class Opening implements Indicator {

	@Override
	public double calculate(int position, Serie series) {
		// TODO Auto-generated method stub
		return series.getCandle(position).getOpening();
	}

	@Override
	public String toString() {
		return "Opening Indicator";
	}
	
	

}
