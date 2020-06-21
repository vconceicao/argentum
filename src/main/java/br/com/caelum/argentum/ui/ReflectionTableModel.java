package br.com.caelum.argentum.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<?> list;
	private Class<?> clazz;
	private static final Logger logger = LoggerFactory.getLogger(ReflectionTableModel.class);
	
	public ReflectionTableModel(List<?> list) {
		
		if (list==null) {
			logger.error("The list can't be null");
			throw new IllegalArgumentException("The List can't be null");
		}
		
		this.list = list;
		this.clazz= list.get(0).getClass();
	}

	@Override
	public int getColumnCount() {
		
		int count = 0;
		for (Method m  : clazz.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Column.class)) {
				count++;
			}
		}
		return count;
		
	}

	@Override
	public int getRowCount() {

		return list.size();
			
		
	}

	
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object object = this.list.get(rowIndex);
		
		for (Method m : clazz.getDeclaredMethods()) {
			if(m.getAnnotation(Column.class)!=null && m.getAnnotation(Column.class).position()==columnIndex){
				try {
					return String.format(m.getAnnotation(Column.class).format(), m.invoke(object));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	
	
	}
	
	
	@Override
	public String getColumnName(int columnIndex) {

		Method[] declaredMethods = clazz.getDeclaredMethods();
		
		for (Method method : declaredMethods) {
			
			boolean annotationPresent = method.isAnnotationPresent(Column.class);
			
			if(annotationPresent && columnIndex == method.getAnnotation(Column.class).position()){
				return method.getAnnotation(Column.class).name();
			}
			
		}
		
		return "";
	}
	
	
	
	
	

}
