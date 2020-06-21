package br.com.caelum.argentum.indicators;

import br.com.caelum.argentum.Serie;

public class Closing implements Indicator {

	@Override
	public double calculate(int position, Serie series) {
		return series.getCandle(position).getClosing();
	}

	@Override
	public String toString() {
		return "Closing Indicator";
	}

}
