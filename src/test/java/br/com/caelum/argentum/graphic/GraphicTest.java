package br.com.caelum.argentum.graphic;

import java.io.IOException;

import javax.swing.JFrame;

import br.com.caelum.argentum.MockSerie;
import br.com.caelum.argentum.Serie;
import br.com.caelum.argentum.indicators.Closing;
import br.com.caelum.argentum.indicators.SimpleMovingAverage;
import br.com.caelum.argentum.indicators.WeightedMovingAverage;

public class GraphicTest {


	public static void main (String[] args) throws IOException{
		
		
		Serie serie = new MockSerie(1,2,3,4,5,6,7,8,8,9,9,4,3,2,1,2,2,4,5,6,7,8,9,10,11,10,6,3,2,6,7,8,9);
		
		GraphicBuilder graphicBuilder = new GraphicBuilder(serie, 3, 32);
		graphicBuilder.createGraphic("Simple Moving Average ");
		graphicBuilder.plotIndicator(new SimpleMovingAverage(3, new Closing()));
		graphicBuilder.plotIndicator(new WeightedMovingAverage(3, new Closing()));
		graphicBuilder.plotIndicator(new Closing());
//		graphicBuilder.save(new FileOutputStream("graphic.png"));
	
		
		JFrame frame = new JFrame("My frame");
		frame.add(graphicBuilder.getPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
}
