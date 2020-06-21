package br.com.caelum.argentum;

import java.util.Calendar;

public class CandleBuilder {

	private double opening;
	private double closing;
	private double low;
	private double high;
	private double total;
	private Calendar date;
	
	private boolean isDateMethodInvoked;


	public CandleBuilder opening(double opening) {

		this.opening = opening;
		return this;
	}

	public CandleBuilder closing(double closing) {

		this.closing = closing;
		return this;
	}

	public CandleBuilder low(double low) {

		this.low = low;
		return this;
	}

	public CandleBuilder high(double high) {

		this.high = high;
		return this;
	}

	public CandleBuilder total(double total) {

		this.total = total;
		return this;
	}

	public CandleBuilder date(Calendar date) {

		isDateMethodInvoked = true;
		this.date = date;
		return this;
	}
	
	public Candle buildCandle(){
		
		
		
		if (!isDateMethodInvoked) {
			throw new IllegalStateException("The method date build must be called before buildCandle");
		}
		
		return new Candle(opening, closing, low, high, total, date);
	}
}
