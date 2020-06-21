package br.com.caelum.argentum.ui;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

import br.com.caelum.argentum.indicators.Closing;
import br.com.caelum.argentum.indicators.Indicator;
import br.com.caelum.argentum.indicators.SimpleMovingAverage;

public class ClosingSMA implements IndicatorOption {
	
	
	private JMenuItem item;
	private Indicator indicator;
	
	
	public ClosingSMA() {

		this.item = new JCheckBoxMenuItem("Closing Simple Moving Average");
		this.indicator = new SimpleMovingAverage(3, new Closing());
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.caelum.argentum.ui.IndicatorOption#getIndicator()
	 */
	@Override
	public Indicator getIndicator(){
		return indicator;
	}

	@Override
	public JMenuItem getItem() {
		// TODO Auto-generated method stub
		return item;
	}

}
