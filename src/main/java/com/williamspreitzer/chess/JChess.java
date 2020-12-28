package com.williamspreitzer.chess;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.gui.Table;

public class JChess {

	public static void main(String[] args) {
		Board board = Board.createStandardBoard();
		Table table = new Table();
	}
}
