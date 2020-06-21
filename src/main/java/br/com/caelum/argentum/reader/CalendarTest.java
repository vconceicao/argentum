package br.com.caelum.argentum.reader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(555454646);
		System.out.println(c.getTimeInMillis());
		
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
	}
}
