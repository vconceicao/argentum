package br.com.caelum.argentum;

import java.util.Calendar;

public class Candle {

	private double opening;
	private double closing;
	private double low;
	private double high;
	private double total;
	private Calendar date;
	private static double ZERO = 0.0;

	public Candle(double opening, double closing, double low, double high, double total, Calendar date) {

		if (high < low) {
			throw new IllegalArgumentException("Highest price must not be minor than Lowest");
		}

		if (date == null) {

			throw new IllegalArgumentException("Date must not null!");
		}

		if (opening < ZERO || closing < ZERO || low < ZERO || high < ZERO || total < ZERO) {

			throw new IllegalArgumentException("Candlestick values must not be negative");
		}

		this.opening = opening;
		this.closing = closing;
		this.low = low;
		this.high = high;
		this.total = total;
		this.date = date;
	}

	public double getOpening() {
		return opening;
	}

	public double getClosing() {
		return closing;
	}

	public double getLow() {
		return low;
	}

	public double getHigh() {
		return high;
	}

	public double getTotal() {
		return total;
	}

	public Calendar getDate() {
		return date;
	}

	public boolean isHigh() {
		return this.closing >= this.opening;
	}

	public boolean isLow() {
		return this.closing < this.opening;
	}

	@Override
	public String toString() {
		return "Candlestick [Opening=" + opening + ", Closing=" + closing + ", Low=" + low + ", High=" + high
				+ ", Total=" + total + ", Date=" + date.get(Calendar.DATE) + "/" + date.get(Calendar.MONTH) + "/"
				+ date.get(Calendar.YEAR) + "]";
	}

}
