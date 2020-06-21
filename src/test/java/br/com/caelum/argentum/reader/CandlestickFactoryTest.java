package br.com.caelum.argentum.reader;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Transaction;

public class CandlestickFactoryTest {

	@Test
	public void testSimpleSequenceTransactions() {
	
	Calendar today = Calendar.getInstance();
	
	Transaction transaction = new Transaction(40.5, 100, today);
	Transaction transaction2 = new Transaction(45.0, 100, today);
	Transaction transaction3 = new Transaction(39.8, 100, today);
	Transaction transaction4 = new Transaction(42.3, 100, today);
		
	List<Transaction> transactions = Arrays.asList(transaction, transaction2, transaction3, transaction4);	
	CandlestickFactory factory = new CandlestickFactory();
	Candle candlestick = factory.buildCandleForDate(today, transactions);
	
	assertEquals(40.5,candlestick.getOpening(), 0.00001);
	assertEquals(42.3,candlestick.getClosing(), 0.00001);
	assertEquals(39.8,candlestick.getLow(), 0.00001);
	assertEquals(45.0,candlestick.getHigh(), 0.00001);
	assertEquals(16760.0,candlestick.getTotal(), 0.00001);
	
	}
	
	@Test
	public void testBuildCandleWithoutTransactions(){
		
		Calendar today = Calendar.getInstance();
		
		CandlestickFactory candlestickFactory = new CandlestickFactory();
		
		List<Transaction> transactions = Collections.emptyList();
		
		Candle candle= candlestickFactory.buildCandleForDate(today, transactions);
		
		assertEquals(0.0, candle.getTotal(), 0.00001);
		
	}
	
	@Test
	public void testOneTransactionOnly() {
	
	Calendar today = Calendar.getInstance();
	
	Transaction transaction = new Transaction(40.5, 100, today);
		
	List<Transaction> transactions = Arrays.asList(transaction);	
	CandlestickFactory factory = new CandlestickFactory();
	Candle candlestick = factory.buildCandleForDate(today, transactions);
	
	assertEquals(40.5,candlestick.getOpening(), 0.00001);
	assertEquals(40.5,candlestick.getClosing(), 0.00001);
	assertEquals(40.5,candlestick.getLow(), 0.00001);
	assertEquals(40.5,candlestick.getHigh(), 0.00001);
	assertEquals(4050.0,candlestick.getTotal(), 0.00001);
	
	}
	
	
	@Test
	public void testBuildCandleWithTransactionInCrescentOrder() {
	
	Calendar today = Calendar.getInstance();
	
	Transaction transaction = new Transaction(39.8, 100, today);
	Transaction transaction2 = new Transaction(40.5, 100, today);
	Transaction transaction3 = new Transaction(42.3, 100, today);
	Transaction transaction4 = new Transaction(45.0, 100, today);
		
	List<Transaction> transactions = Arrays.asList(transaction, transaction2, transaction3, transaction4);	
	CandlestickFactory factory = new CandlestickFactory();
	Candle candlestick = factory.buildCandleForDate(today, transactions);
	
	assertEquals(39.8,candlestick.getOpening(), 0.00001);
	assertEquals(45.0,candlestick.getClosing(), 0.00001);
	assertEquals(39.8,candlestick.getLow(), 0.00001);
	assertEquals(45.0,candlestick.getHigh(), 0.00001);
	assertEquals(16760.0,candlestick.getTotal(), 0.00001);
	
	}
	
	@Test
	public void testBuildCandleWithTransactionInDecrescentOrder() {
		
		Calendar today = Calendar.getInstance();
		
		Transaction transaction4 = new Transaction(45.0, 100, today);
		Transaction transaction3 = new Transaction(42.3, 100, today);
		Transaction transaction2 = new Transaction(40.5, 100, today);
		Transaction transaction = new Transaction(39.8, 100, today);
		
		List<Transaction> transactions = Arrays.asList(transaction4,transaction3, transaction2,transaction);	
		CandlestickFactory factory = new CandlestickFactory();
		Candle candlestick = factory.buildCandleForDate(today, transactions);
		
		assertEquals(45.0,candlestick.getOpening(), 0.00001);
		assertEquals(39.8,candlestick.getClosing(), 0.00001);
		assertEquals(39.8,candlestick.getLow(), 0.00001);
		assertEquals(45.0,candlestick.getHigh(), 0.00001);
		assertEquals(16760.0,candlestick.getTotal(), 0.00001);
		
	}
	

	
	@Test
	public void testBuildCandlesWithManyTransactions(){
		
		
		Calendar today = Calendar.getInstance();
		
		Transaction transaction1 = new Transaction(40.5, 100, today);
		Transaction transaction2 = new Transaction(45.0, 100, today);
		Transaction transaction3 = new Transaction(39.8, 100, today);
		Transaction transaction4 = new Transaction(42.3, 100, today);
		
		
		Calendar tomorrow = (Calendar)today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		
		Transaction transaction5 = new Transaction(48.8, 100, tomorrow);
		Transaction transaction6 = new Transaction(49.3, 100, tomorrow);
		
		Calendar after = (Calendar)tomorrow.clone();
		after.add(Calendar.DAY_OF_MONTH, 1);
		
		Transaction transaction7 = new Transaction(51.8, 100, after);
		Transaction transaction8 = new Transaction(52.3, 100, after);
		
		List<Transaction> transactions = Arrays.asList(transaction1,transaction2, transaction3, transaction4, transaction5, transaction6, transaction7, transaction8);
		
		
		List<Candle> candles = new CandlestickFactory().buildCandles(transactions);
		
		
		assertEquals(3, candles.size());
		assertEquals(40.5,candles.get(0).getOpening(), 0.00001);
		assertEquals(42.3,candles.get(0).getClosing(), 0.00001);
		
		assertEquals(48.8,candles.get(1).getOpening(), 0.00001);
		assertEquals(49.3,candles.get(1).getClosing(), 0.00001);
		
		assertEquals(51.8,candles.get(2).getOpening(), 0.00001);
		assertEquals(52.3,candles.get(2).getClosing(), 0.00001);
		
		
		
		
	}
	
	
	@Test
	public void testBuildCandlesWithUnorderedDate(){
		
		
		Calendar today = Calendar.getInstance();
		
		Transaction transaction1 = new Transaction(40.5, 100, today);
	
		
		Calendar tomorrow = (Calendar)today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
	
		Transaction transaction2 = new Transaction(52.3, 100, tomorrow);
		
		List<Transaction> transactions = Arrays.asList(transaction2, transaction1);
		
		
		List<Candle> candles = new CandlestickFactory().buildCandles(transactions);
		
		assertEquals(2, candles.size());
		assertEquals(40.5,candles.get(0).getOpening(), 0.00001);
		assertEquals(40.5,candles.get(0).getClosing(), 0.00001);
		assertEquals(40.5,candles.get(0).getHigh(), 0.00001);
		assertEquals(40.5,candles.get(0).getLow(), 0.00001);
		assertEquals(4050.0, candles.get(0).getTotal(),0.0001);
		
		assertEquals(52.3,candles.get(1).getOpening(), 0.00001);
		assertEquals(52.3,candles.get(1).getClosing(), 0.00001);
		assertEquals(52.3,candles.get(1).getHigh(), 0.00001);
		assertEquals(52.3,candles.get(1).getLow(), 0.00001);
		assertEquals(5230.0, candles.get(1).getTotal(),0.0001);
		
		
		
	}
	

}
