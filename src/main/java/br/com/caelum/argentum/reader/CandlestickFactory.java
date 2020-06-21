package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Transaction;

public class CandlestickFactory {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CandlestickFactory.class);


	public Candle buildCandleForDate(Calendar date, List<Transaction> transactions) {

		double high = transactions.isEmpty() ? 0 : Double.MIN_VALUE;
		double low = transactions.isEmpty() ? 0 : Double.MAX_VALUE;
		double total = 0;

		for (Transaction transaction : transactions) {
			total += transaction.getTotal();

			if (transaction.getPrice() > high) {
				high = transaction.getPrice();
			}

			if (transaction.getPrice() < low) {
				low = transaction.getPrice();
			}
		}

		double opening = transactions.isEmpty() ? 0 : transactions.get(0).getPrice();
		double closing = transactions.isEmpty() ? 0 : transactions.get(transactions.size() - 1).getPrice();

		return new Candle(opening, closing, low, high, total, date);
	}


	public List<Candle> buildCandles(List<Transaction> transactions) {
		
		logger.info("Building candles");

		List<Candle> candlesticks = new ArrayList<>();
		
		Map<Calendar, List<Transaction>> collect = transactions.stream().sorted().collect(Collectors.groupingBy(Transaction::getDate) );
		
		List<Calendar> dates = collect.keySet().stream().sorted().collect(Collectors.toList());
		
		dates.forEach(date -> candlesticks.add(buildCandleForDate(date, collect.get(date))));
		
		
		return candlesticks;
	}
	
}
