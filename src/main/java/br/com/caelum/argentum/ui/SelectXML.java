package br.com.caelum.argentum.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.MalformedInputException;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.caelum.argentum.Transaction;
import br.com.caelum.argentum.reader.XMLReader;

public class SelectXML {

	public List<Transaction> select() {

		try {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("Only XML ", "xml"));
			int ret = fileChooser.showOpenDialog(null);

			if (ret == JFileChooser.APPROVE_OPTION) {

				File file = fileChooser.getSelectedFile();

				XMLReader xmlReader = new XMLReader(Transaction.class);

				FileReader fileReader;
				fileReader = new FileReader(file);
				List<Transaction> transactions = (List<Transaction>) xmlReader.load(fileReader);
				
				return transactions;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();

	}

	public static void main(String[] args) {
		SelectXML selectXML = new SelectXML();

		selectXML.select();
	}

}
