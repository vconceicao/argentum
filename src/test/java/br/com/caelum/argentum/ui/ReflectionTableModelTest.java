package br.com.caelum.argentum.ui;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Transaction;

public class ReflectionTableModelTest {

	@Test
	public void testGetColumnCount() {
		
		
		//criar uma lista de transactions
		Calendar today = Calendar.getInstance();
		
		Transaction transaction = new Transaction(40.5, 100, today);
		Transaction transaction2 = new Transaction(45.0, 100, today);
		Transaction transaction3 = new Transaction(39.8, 100, today);
		Transaction transaction4 = new Transaction(42.3, 100, today);
			
		List<Transaction> transactions = Arrays.asList(transaction, transaction2, transaction3, transaction4);
		
		//instanciar uma reflectiontablemodel
			
		
		ReflectionTableModel tableModel = new ReflectionTableModel(transactions);
		
		//fazer asserssões
		
		assertEquals("Same number of columns?", 4, tableModel.getColumnCount());
		
		
	}
	
	
	@Test
	public void testGetValueAt(){
		
		//criar uma lista de transactions
		Calendar calendar = Calendar.getInstance();
		
		String date = "17/06/2020";
		try {
			Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			calendar.setTime(parsedDate);
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		Transaction transaction = new Transaction(40.5, 100, calendar);
		Transaction transaction2 = new Transaction(45.0, 100, calendar);
		Transaction transaction3 = new Transaction(39.8, 100, calendar);
		Transaction transaction4 = new Transaction(42.3, 100, calendar);
		
		
		
		
		
		List<Transaction> transactions = Arrays.asList(transaction, transaction2, transaction3, transaction4);
		
		//instanciar uma reflectiontablemodel
			
		
		ReflectionTableModel tableModel = new ReflectionTableModel(transactions);
		
		//fazer asserssões
		
		assertEquals( "R$ 40,50", tableModel.getValueAt(0, 0));
		assertEquals( "100", tableModel.getValueAt(0, 1));
		assertEquals( "17/06/2020", tableModel.getValueAt(0, 2));
		assertEquals( "R$ 4.050,00", tableModel.getValueAt(0, 3));
		
		assertEquals( "R$ 45,00", tableModel.getValueAt(1, 0));
		assertEquals( "100", tableModel.getValueAt(1, 1));
		assertEquals( "17/06/2020", tableModel.getValueAt(1, 2));
		assertEquals( "R$ 4.500,00", tableModel.getValueAt(1, 3));
		
		assertEquals( "R$ 39,80", tableModel.getValueAt(2, 0));
		assertEquals( "100", tableModel.getValueAt(2, 1));
		assertEquals( "17/06/2020", tableModel.getValueAt(2, 2));
		assertEquals( "R$ 3.980,00", tableModel.getValueAt(2, 3));
		
		assertEquals( "R$ 42,30", tableModel.getValueAt(3, 0));
		assertEquals( "100", tableModel.getValueAt(3, 1));
		assertEquals( "17/06/2020", tableModel.getValueAt(3, 2));
		assertEquals( "R$ 4.230,00", tableModel.getValueAt(3, 3));
		
		
		
	}
	
	
	@Test
	public void testGetColumnName(){
		
		
		//criar uma lista de transactions
		Calendar today = Calendar.getInstance();
		
		Transaction transaction = new Transaction(40.5, 100, today);
		Transaction transaction2 = new Transaction(45.0, 100, today);
		Transaction transaction3 = new Transaction(39.8, 100, today);
		Transaction transaction4 = new Transaction(42.3, 100, today);
			
		List<Transaction> transactions = Arrays.asList(transaction, transaction2, transaction3, transaction4);
		
		//instanciar uma reflectiontablemodel
			
		
		ReflectionTableModel tableModel = new ReflectionTableModel(transactions);
		
		assertEquals( "Price", tableModel.getColumnName(0));
		assertEquals( "Quantity", tableModel.getColumnName(1));
		assertEquals( "Date", tableModel.getColumnName(2));
		assertEquals( "Total", tableModel.getColumnName(3));
		
		
		
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testReceiveANullListAsParameter(){
		
		
				
		
		ReflectionTableModel tableModel = new ReflectionTableModel(null);
		
		assertEquals( "Price", tableModel.getColumnName(0));
		
		
		
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testReceiveAEmptyListAsParameter(){
		
		
				
		
		ReflectionTableModel tableModel = new ReflectionTableModel(Collections.emptyList());
		
		assertEquals( "Price", tableModel.getColumnName(0));
		
		
		
		
	}

}
