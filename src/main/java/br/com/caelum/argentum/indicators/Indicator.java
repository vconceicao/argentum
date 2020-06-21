package br.com.caelum.argentum.indicators;

import br.com.caelum.argentum.Serie;

public interface Indicator {

	double calculate(int position, Serie series);

}