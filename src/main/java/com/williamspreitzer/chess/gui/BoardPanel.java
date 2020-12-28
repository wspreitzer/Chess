package com.williamspreitzer.chess.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.utils.GameUtils;

public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final List<TilePanel> boardTiles;
	
	private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
	public BoardPanel() {
		super(new GridLayout(8,8));
		this.boardTiles = new ArrayList<TilePanel>();
		for(int i = 0; i < GameUtils.NUM_TILES; i++ ) {
			final TilePanel tilePanel = new TilePanel(this, i);
			this.boardTiles.add(tilePanel);
			add(tilePanel);
		}
		setPreferredSize(BOARD_PANEL_DIMENSION);
		validate();
	}
	
	public void drawBoard(final Board board) {
		removeAll();
		for(final TilePanel tilePanel : boardTiles) {
			tilePanel.drawTile(board);
			add(tilePanel);
		}
		validate();
		repaint();
	}
}
