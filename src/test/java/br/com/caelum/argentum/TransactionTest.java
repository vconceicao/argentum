package br.com.caelum.argentum;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class TransactionTest {

	@Test
	public void testTransactionDateIsImmutable() {

	
	Calendar calendar = Calendar.getInstance();
	calendar.set(Calendar.DAY_OF_MONTH, 15);
	
	Transaction transaction = new Transaction(10, 5, calendar);
	
	transaction.getDate().set(Calendar.DAY_OF_MONTH, 20);
	
	assertEquals(15, transaction.getDate().get(Calendar.DAY_OF_MONTH));
	
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testTransactionDateMustNotBeNull(){
		

		
		Transaction transaction = new Transaction(10, 5, null);
		
		
			
		
		
	}

}
