package com.williamspreitzer.chess.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.data.DataModel;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveLog;
import com.williamspreitzer.chess.utils.GameUtils;

public class GameHistoryPanel extends JPanel {

	private DataModel model;
	private final JScrollPane scrollPane;
	private static final Dimension HISTORY_PANEL_DIMENSION = new Dimension(100,400);
	private static DataModel globalModel;
	private static final long serialVersionUID = 1L;
	
	GameHistoryPanel() {
		this.setLayout(new BorderLayout());
		this.model = new DataModel();
		final JTable table = new JTable(model);
		table.setRowHeight(15);
		this.scrollPane = new JScrollPane(table);
		scrollPane.setColumnHeaderView(table.getTableHeader());
		scrollPane.setPreferredSize(HISTORY_PANEL_DIMENSION);
		this.add(scrollPane, BorderLayout.CENTER);
		GameHistoryPanel.globalModel = model;
		this.setVisible(true);
	}
	
	void redo(final Board board, final MoveLog moveHistory) {
		int currentRow = 0;
		this.model.clear();
		for(final Move move : moveHistory.getMoves()) {
			final String moveText = move.toString();
			if(move.getMovedPiece().getColor().isWhite()) {
				this.model.setValueAt(moveText, currentRow, 0);
			} else {
				this.model.setValueAt(moveText, currentRow, 1);
				currentRow++;
			}
		}
		
		if(moveHistory.getMoves().size() > 0) {
			final Move lastMove = moveHistory.getMoves().get(moveHistory.size() - 1);
			final String moveText = lastMove.toString();
			if(lastMove.getMovedPiece().getColor().isWhite()) {
				this.model.setValueAt(moveText + GameUtils.calculateCheckAndCheckmateHash(board), currentRow, 0);
			} else {
				this.model.setValueAt(moveText + GameUtils.calculateCheckAndCheckmateHash(board), currentRow -1, 1);
			}
		}
		
		final JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		
	}
	
	public static DataModel getDataModel() {
		return globalModel;
	}
}
