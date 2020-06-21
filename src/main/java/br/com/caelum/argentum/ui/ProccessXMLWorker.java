package br.com.caelum.argentum.ui;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Serie;
import br.com.caelum.argentum.TimeSerie;
import br.com.caelum.argentum.Transaction;
import br.com.caelum.argentum.graphic.GraphicBuilder;
import br.com.caelum.argentum.reader.CandlestickFactory;

public class ProccessXMLWorker extends SwingWorker<Object[], Void> {

	private final  JTable table;
	private final JTabbedPane tabs;
	private final List<IndicatorOption> indicatorOptionList;
	

	
	

	public ProccessXMLWorker(JTable table, JTabbedPane tabs, List<IndicatorOption> indicatorOptionList) {
		this.table = table;
		this.tabs = tabs;
		this.indicatorOptionList = indicatorOptionList;
	}




	@Override
	protected Object[] doInBackground() throws Exception {

		
		List<Transaction> list = new SelectXML().select();
//		filterByDate(list);
		
		CandlestickFactory factory = new CandlestickFactory();
		List<Candle> candles = factory.buildCandles(list);

		Serie serie = new TimeSerie(candles);

		GraphicBuilder gBuilder = new GraphicBuilder(serie, 2, serie.getTotal() - 1);

		gBuilder.createGraphic("Simple Moving Average");
		
		for (IndicatorOption indicatorOption : this.indicatorOptionList) {
			if (indicatorOption.getItem().isSelected()) {
				gBuilder.plotIndicator(indicatorOption.getIndicator());
			}
		}
		
		
		JPanel panel = gBuilder.getPanel();
		return new Object[] {list,panel};
	}
	
	
	@Override
	protected void done() {
	
		try {
			Object[] returns = get();
			
			
		List<Transaction> transactions = (List<Transaction>) returns[0];
		JPanel graph = (JPanel) returns[1];
		
		ReflectionTableModel transactionTableModel = new ReflectionTableModel(transactions);
		
		table.setModel(transactionTableModel);

		tabs.setComponentAt(1, graph);
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
}
