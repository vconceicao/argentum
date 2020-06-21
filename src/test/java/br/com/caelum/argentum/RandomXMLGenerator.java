package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class RandomXMLGenerator {

	public static void main(String[] args) {
		
		
		
		Calendar date = Calendar.getInstance();
		
		Random random = new Random(123);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		
		double value = 40;
		int quantity = 1000;
		
		//30 days
		for(int i=0; i<30; i++){
			
			
			for(int x = 0 ; x<random.nextInt(20); x++){
				
				value +=(random.nextInt(200) -100)/ 100.0;
				
				
				quantity+= (random.nextInt(200)-100);
				
				
				Transaction transaction = new Transaction(value, quantity, date);
				
				transactions.add(transaction);
			}
			
			date = (Calendar) date.clone();
			date.add(Calendar.DAY_OF_YEAR, 1);
			
			
		}
		
		XStream stream = new XStream(new DomDriver());
		stream.alias("transaction", Transaction.class);
		stream.setMode(XStream.NO_REFERENCES);
		System.out.println(stream.toXML(transactions));
		
	}
	
	
}
