package br.com.caelum.argentum;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.caelum.argentum.ui.Column;

@XStreamAlias("transaction")
public final class Transaction implements Serializable, Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final double price;
	private final int quantity;
	private final Calendar date;

	public Transaction(double price, int quantity, Calendar date) {

		if (date == null) {
			throw new IllegalArgumentException("Date must not be null!");
		}

		this.price = price;
		this.quantity = quantity;
		this.date = date;
	}

	@Column(name="Price", position=0, format="R$ %.2f")
	public double getPrice() {
		return price;
	}

	@Column(name="Quantity", position=1 )
	public int getQuantity() {
		return quantity;
	}

	@Column(name="Date", position=2, format="%1$Td/%1$Tm/%1$TY")
	public Calendar getDate() {
		return (Calendar) date.clone();
	}

	@Column(name="Total", position=3, format="R$ %,#.2f")
	public double getTotal() {
		return price * quantity;
	}

	@Override
	public String toString() {

		return "Transaction [Price=" + price + ", Quantity=" + quantity + ", Date="
				+ new SimpleDateFormat("yyyy-MM-dd").format(this.date.getTime()) + "]";
	}

	@Override
	public int compareTo(Object o) {
		Transaction t = (Transaction) o;

		return this.date.compareTo(t.getDate());
	}

}
