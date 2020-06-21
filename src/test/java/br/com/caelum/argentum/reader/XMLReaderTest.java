package br.com.caelum.argentum.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Transaction;

public class XMLReaderTest {

	@Test
	public void testLoadTransactionList() {

		String  testXML = new StringBuilder().append("<list>")
				.append("<transaction>")
				.append("<price>43.5</price>")
				.append("<quantity>1000</quantity>")
				.append("<date>")
				.append("<time>555454646</time>")
				.append("</date>")
				.append("</transaction>")
		.append("</list>").toString();
		
		XMLReader xmlReader = new XMLReader(Transaction.class);
		
		List<Transaction> transactions = (List<Transaction>) xmlReader.load(new StringReader(testXML));
		
		assertEquals(1, transactions.size());
		assertEquals(43.5, transactions.get(0).getPrice(), 0.0001);
		assertEquals(1000, transactions.get(0).getQuantity(), 0.0001);
		
	
	}
	
	@Test
	public void testLoadTransactionListWith2Transactions() {
		
		String  testXML = new StringBuilder().append("<list>")
				.append("<transaction>")
				.append("<price>43.5</price>")
				.append("<quantity>1000</quantity>")
				.append("<date>")
				.append("<time>555454646</time>")
				.append("</date>")
				.append("</transaction>")
				.append("<transaction>")
				.append("<price>39.5</price>")
				.append("<quantity>50</quantity>")
				.append("<date>")
				.append("<time>554454646</time>")
				.append("</date>")
				.append("</transaction>")
				.append("</list>").toString();
		
		XMLReader xmlReader = new XMLReader(Transaction.class);
		
		List<Transaction> transactions = (List<Transaction>) xmlReader.load(new StringReader(testXML));
		
		assertEquals(2, transactions.size());
		assertEquals(43.5, transactions.get(0).getPrice(), 0.0001);
		assertEquals(1000, transactions.get(0).getQuantity(), 0.0001);
		assertEquals(39.5, transactions.get(1).getPrice(), 0.0001);
		assertEquals(50, transactions.get(1).getQuantity(), 0.0001);
		
		
	}
	
	@Test
	public void testLoadTransactionListWithoutTransactions() {
		
		String  testXML = new StringBuilder().append("<list>")
				
				.append("</list>").toString();
		
		XMLReader xmlReader = new XMLReader(Transaction.class);
		
		List<Transaction> transactions = (List<Transaction>) xmlReader.load(new StringReader(testXML));
		
		assertEquals(0, transactions.size());
		
		
	}
	
	
	@Test(expected=IllegalStateException.class)
	public void testLoadTransactionListWithErrors() {
		
		String  testXML = new StringBuilder().append("<list>")
				.append("<transaction>")
				.append("<price>43,5</price>")
				.append("<quantity>1000</quantity>")
				.append("<date>")
				.append("<time>555454646</time>")
				.append("</date>")
				.append("</transaction>")
	
				.append("</list>").toString();
		
		XMLReader xmlReader = new XMLReader(Transaction.class);
		
		List<Transaction> transactions = (List<Transaction>) xmlReader.load(new StringReader(testXML));
		
		assertEquals(1, transactions.size());
		
		
	}
	
	
	@Test
	public void testReadXMLTransactionList() {
		
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(555454646l);
		
		
		Transaction transaction = new Transaction(43.5, 1000, date);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		XMLReader xmlReader = new XMLReader(Transaction.class);
		
		String xml = xmlReader.convertToXML(transactions);
		
		
		
		assertTrue("Does it has the price tag",xml.contains("<price>43.5</price>"));
		assertTrue("Does it has contains the quantity tag?",xml.contains("<quantity>1000</quantity>"));
		assertTrue("Does it has time tag?", xml.contains("<time>555454646</time>"));
		
		
	}

}
