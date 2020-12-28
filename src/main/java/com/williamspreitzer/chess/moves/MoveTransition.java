package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;

public class MoveTransition {
	
	private final Board board;
	private Move move;
	private MoveStatus status;

	public MoveTransition(Board board, Move move, MoveStatus status) {
		this.board = board;
		this.move = move;
		this.status = status;
	}
	
	public MoveStatus getStatus() {
		return this.status;
	}
	
	public Move getMove() {
		return this.move;
	}
	
	public Board getBoard() {
		return this.board;
	}
}