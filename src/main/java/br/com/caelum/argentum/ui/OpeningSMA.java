package br.com.caelum.argentum.ui;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

import br.com.caelum.argentum.indicators.Indicator;
import br.com.caelum.argentum.indicators.Opening;
import br.com.caelum.argentum.indicators.SimpleMovingAverage;

public class OpeningSMA implements IndicatorOption {

	
	private JMenuItem item;
	private Indicator indicator;
	
	public OpeningSMA() {
		this.item = new JCheckBoxMenuItem("Opening Simple Moving Average");
		this.indicator = new SimpleMovingAverage(3, new Opening());
	}



	@Override
	public Indicator getIndicator() {
		return this.indicator;
	}

	@Override
	public JMenuItem getItem() {
		// TODO Auto-generated method stub
		return item;
	}

}
