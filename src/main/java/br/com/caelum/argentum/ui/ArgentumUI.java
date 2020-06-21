package br.com.caelum.argentum.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Serie;
import br.com.caelum.argentum.TimeSerie;
import br.com.caelum.argentum.Transaction;
import br.com.caelum.argentum.graphic.GraphicBuilder;
import br.com.caelum.argentum.indicators.Closing;
import br.com.caelum.argentum.indicators.High;
import br.com.caelum.argentum.indicators.Indicator;
import br.com.caelum.argentum.indicators.Low;
import br.com.caelum.argentum.indicators.Opening;
import br.com.caelum.argentum.indicators.SimpleMovingAverage;
import br.com.caelum.argentum.indicators.Total;
import br.com.caelum.argentum.indicators.WeightedMovingAverage;
import br.com.caelum.argentum.reader.CandlestickFactory;

public class ArgentumUI {

	private JFrame frame;
	private JPanel panel;
	private JTable table;
	private JPanel buttonPanel;
	private JTabbedPane tabs;
	private JFormattedTextField beginDateField;
	private IndicatorOption closingSMA;
	private List<IndicatorOption> indicatorOptionList;
	private OpeningSMA openingSMA;

	public static void main(String[] args) {
		new ArgentumUI().buildMainScreen();
	}

	public void buildMainScreen() {

		buildWindow();
		buildMenu();
		buildMainPanel();
		buildTabs();
		buildTitle();
		buildTableWithScroll();
		buildButtonPanel();
		buildDateField();
		buildLoadButton();
		buildExitButton();
		showWindow();
	}

	private void buildMenu() {

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu indicatorsMenu = new JMenu("Indicators");
		menuBar.add(indicatorsMenu);
		
		this.indicatorOptionList = new ArrayList<>();
		
		closingSMA = new ClosingSMA();
		openingSMA = new OpeningSMA();
		
		this.indicatorOptionList.add(closingSMA);
		this.indicatorOptionList.add(openingSMA);
		
		
		for (IndicatorOption indicator : indicatorOptionList) {
			indicatorsMenu.add(indicator.getItem());
		}
		
		

		JMenu optionsMenu = new JMenu("Options");
		menuBar.add(optionsMenu);

		JMenuItem loadXMLMenuItem = new JMenuItem("Load XML");
		JMenuItem exitMenuItem = new JMenuItem("Exit");

		loadXMLMenuItem.addActionListener(

				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new ProccessXMLWorker(table, tabs, indicatorOptionList).execute();
					}
				});

		exitMenuItem.addActionListener(
				
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.exit(0);
					}
				}
				
				);
		
		optionsMenu.add(loadXMLMenuItem);
		optionsMenu.add(exitMenuItem);
	}



	private void buildDateField() {

		try {
			MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
			maskFormatter.setPlaceholderCharacter('_');

			beginDateField = new JFormattedTextField(maskFormatter);
			buttonPanel.add(beginDateField);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buildTabs() {

		tabs = new JTabbedPane();
		tabs.add("Transactions table", null);
		tabs.add("Graph", null);
		panel.add(tabs);
	}

	private void buildButtonPanel() {

		buttonPanel = new JPanel(new GridLayout());
		panel.add(buttonPanel, BorderLayout.SOUTH);

	}

	private void filterByDate(List<Transaction> transactions) {

		try {
			String value = (String) this.beginDateField.getValue();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			;

			sdf.setLenient(false);
			Date date = sdf.parse(value);

			Iterator<Transaction> iterator = transactions.iterator();

			while (iterator.hasNext()) {

				if (iterator.next().getDate().getTime().before(date)) {
					iterator.remove();
				}
			}

		} catch (Exception e) {
			this.beginDateField.setValue(null);
		}

	}

	private void buildTitle() {

		JLabel title = new JLabel("Transactions List");
		title.setFont(new Font("Verdana", Font.BOLD, 25));
		title.setForeground(new Color(50, 50, 100));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title, BorderLayout.NORTH);

	}

	private void buildWindow() {
		frame = new JFrame("Argentum");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void buildMainPanel() {

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		frame.add(panel);
	}

	private void buildLoadButton() {

		JButton loadButton = new JButton("Load XML");
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ProccessXMLWorker(table, tabs, indicatorOptionList).execute();

			}
		});

		buttonPanel.add(loadButton);
	}

	private void loadData(List<Transaction> list) {

//		List<Transaction> list = new SelectXML().select();
//		filterByDate(list);


		
		ReflectionTableModel transactionTableModel = new ReflectionTableModel(list);
		
		table.setModel(transactionTableModel);

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

		JPanel graph = gBuilder.getPanel();
		this.tabs.setComponentAt(1, graph);

	}

	private void buildExitButton() {

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		buttonPanel.add(exitButton);
	}

	private void buildTableWithScroll() {

		table = new JTable();
		table.setBorder(new LineBorder(Color.black));
		table.setGridColor(Color.black);
		table.setShowGrid(true);

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(table);
		scroll.setSize(450, 450);

		tabs.setComponentAt(0, scroll);
	}

	private void showWindow() {
		frame.pack();
		frame.setSize(540, 540);
		frame.setVisible(true);
	}

	
	private class LoadXMLWorker extends SwingWorker<List<Transaction>, Void>{

		@Override
		protected List<Transaction> doInBackground() throws Exception {
			List<Transaction> list = new SelectXML().select();
			filterByDate(list);
			
			return list;
		}
		
		
		@Override
		protected void done() {
			try {
				loadData(get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
