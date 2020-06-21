package br.com.caelum.argentum;

import java.util.Calendar;

public class MockSerie implements Serie {

	private final double[] values;

	public MockSerie(double... values) {
		this.values = values;

	}

	@Override
	public Candle getCandle(int index) {
		Calendar date = (Calendar)Calendar.getInstance().clone();
		return new Candle(values[index], values[index], values[index], values[index], 1000, date);
	}

	public int getTotal() {
		return values.length;
	}

}
