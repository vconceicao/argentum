package br.com.caelum.argentum.indicators;

import br.com.caelum.argentum.Serie;

public class Total implements Indicator {

	@Override
	public double calculate(int position, Serie series) {
		return series.getCandle(position).getTotal();
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Total indicator";
	}
}
