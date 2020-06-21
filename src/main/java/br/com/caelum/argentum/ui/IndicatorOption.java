package br.com.caelum.argentum.ui;

import javax.swing.JMenuItem;

import br.com.caelum.argentum.indicators.Indicator;

public interface IndicatorOption {

	JMenuItem getItem();

	Indicator getIndicator();

}