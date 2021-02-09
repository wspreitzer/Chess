package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;

public class MoveTransition {
	
	private final Board board;
	private Move move;
	private MoveStatus status;
	private final Board fromBoard; 

	public MoveTransition(Board fromBoard, Board board, Move move, MoveStatus status) {
		this.board = board;
		this.move = move;
		this.status = status;
		this.fromBoard = fromBoard;
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
	
	public Board getFromBoard() {
		return this.fromBoard;
	}
}