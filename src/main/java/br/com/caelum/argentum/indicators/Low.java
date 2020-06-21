package br.com.caelum.argentum.indicators;

import br.com.caelum.argentum.Serie;

public class Low implements Indicator {

	@Override
	public double calculate(int position, Serie series) {
		return series.getCandle(position).getLow();
	}

}
