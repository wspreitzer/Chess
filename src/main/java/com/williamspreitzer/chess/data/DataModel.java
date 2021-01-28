package com.williamspreitzer.chess.data;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.williamspreitzer.chess.moves.Move;


public class DataModel extends DefaultTableModel {

	private final List<Row> values;
	private static final String[] HEADERS = {"White", "Black"};
	
	private static final long serialVersionUID = 1L;

	public DataModel() {
		this.values = new ArrayList<Row>();
	}
	
	public void clear() {
		this.values.clear();
		setRowCount(0);
	}
	
	@Override
	public int getRowCount() {
		int retVal;
		if(this.values == null) {
			retVal = 0;
		} else {
			retVal = this.values.size();
		}
		return retVal;
	}
	
	@Override
	public int getColumnCount() {
		return HEADERS.length;
	}
	
	@Override
	public Object getValueAt(final int row, final int column) {
		Object retVal;
		final Row currentRow = this.values.get(row);
		switch(column) {
		case 0:
			retVal = currentRow.getWhiteMoves();
			break;
		case 1:
			retVal = currentRow.getBlackMoves();
			break;
		default:
			retVal = null;
		}
		return retVal;
	}
	
	@Override
	public void setValueAt(final Object aValue, 
			               final int row, 
			               final int column) {
		final Row currentRow;
		if(this.values.size() <= row) {
			currentRow = new Row();
			this.values.add(currentRow);
		} else {
			currentRow = this.values.get(row);
		}
		
		switch(column) {
		case 0:
			currentRow.setWhiteMove((String) aValue);
			fireTableRowsInserted(row, row);
			break;
		case 1:
			currentRow.setBlackMove((String) aValue);
			fireTableCellUpdated(row,column);
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public Class<?> getColumnClass(final int column) {
		return Move.class;
	}
	
	@Override
	public String getColumnName(final int column) {
		return HEADERS[column];
	}
	
	private static class Row {
		private String whiteMove;
		private String blackMove;
		
		Row() {
			
		}
		
		public String getWhiteMoves() {
			return this.whiteMove;
		}
		
		public String getBlackMoves() {
			return this.blackMove;
		}
		
		public void setWhiteMove(final String whiteMove) {
			this.whiteMove = whiteMove;
		}
		
		public void setBlackMove(final String blackMove) {
			this.blackMove = blackMove;
		}
	}
}
