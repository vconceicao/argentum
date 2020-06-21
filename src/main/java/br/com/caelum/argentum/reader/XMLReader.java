package br.com.caelum.argentum.reader;

import java.io.Reader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.Transaction;

public class XMLReader {

	
	private XStream stream;

	public XMLReader(Class clazz) {
		
		stream = new XStream(new DomDriver());
		stream.autodetectAnnotations(true);
		stream.processAnnotations(clazz);

	}
	
	public List<?> load(Reader file)  {
		
		try {
			List<?> list=  (List<?>) stream.fromXML(file);
			
			return list;
		} catch (ConversionException e) {

			throw new IllegalStateException("Conversion error reading values from XML");
		}
		
	}

	public String convertToXML(List<Transaction> list) {


		String xml = stream.toXML(list);
		
		
		return xml;
		
		
		
	}
	
}
