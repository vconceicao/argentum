package br.com.caelum.argentum.graphic;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartTheme;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Serie;
import br.com.caelum.argentum.indicators.Indicator;

public final class GraphicBuilder {

	
	private final Serie serie;
	private final int begin;
	private final int end;
	
	private DefaultCategoryDataset dataset;
	private DefaultOHLCDataset cDataset;
	private JFreeChart graph;
	private static final Logger logger =  LoggerFactory.getLogger("GraphicBuilder");
	

	
	
	public GraphicBuilder(Serie serie, int begin, int end) {
		this.serie = serie;
		this.begin = begin;
		this.end = end;
	}
	
	
	public void createGraphic(String title){
		
		
		logger.info("Creating graph with title " + title);
		this.dataset = new DefaultCategoryDataset();
		
		
		this.graph = ChartFactory.createLineChart(title, "Days", "Values", this.dataset, PlotOrientation.VERTICAL, true, true, false);
		
		ChartTheme darkTheme = StandardChartTheme.createDarknessTheme();
		darkTheme.apply(this.graph);
	}
	
	public void createCandleStick(String title){
		
		OHLCDataItem[] data = new OHLCDataItem[this.end]; 
		
		for(int i = this.begin; i<this.end; i++){

			Candle candle = this.serie.getCandle(i);
			
			OHLCDataItem serie = new OHLCDataItem(candle.getDate().getTime(),candle.getOpening(),candle.getHigh(), candle.getLow(), candle.getClosing(), candle.getTotal());
			data[i] = serie;
		}
		
		this.cDataset = new DefaultOHLCDataset("Time series 1", data);
		this.graph = ChartFactory.createCandlestickChart(title, "Days", "Values", this.cDataset,  true);
		
	}
	
	
	public void plotIndicator(Indicator indicator){
		for(int i = this.begin; i<this.end; i++){
			double value = indicator.calculate(i, serie);
			this.dataset.addValue(value, indicator.toString(), "" +i);
		}
	}
	
	

	

	public void save(OutputStream out) throws IOException{
		ChartUtilities.writeChartAsPNG(out, graph, 500, 300);
	}
	
	
	public JPanel getPanel(){
		return new ChartPanel(graph);
	}
	
	
}
