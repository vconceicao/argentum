package br.com.caelum.argentum.ui;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.caelum.argentum.Transaction;

public class TransactionTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Transaction> transactions;
	private final DateFormat dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
	private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
	
	public TransactionTableModel(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return transactions.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
	Transaction t = this.transactions.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return numberFormat.format(t.getPrice());
		case 1:
			return t.getQuantity();
		case 2:
			return dateFormat.format(t.getDate().getTime());
		}
		
		
		
		return "";
	}
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	@Override
	public String getColumnName(int column) {

		switch (column) {
		case 0:
			return "Price";
		case 1:
			return "Quantity";
		case 2: 
			return "Date";
			
		}
		
		return "";
	}

}
