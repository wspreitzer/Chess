package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.piece.Piece;

public class NullMove extends NonAttackingMoves {

	public NullMove(Board board, Piece movedPiece, int destinationCoordinate) {
		super(board, movedPiece, destinationCoordinate);
	}
	
	@Override
	public Board execute() {
		throw new RuntimeException("Cannot execute the null move!");
	}

	@Override
	public MoveType getType() {
		return null;
	}

	@Override
	public Board getBoard() {
		return null;
	}

	@Override
	public int getDestinationCoordinate() {
		return 0;
	}

	@Override
	public Piece getMovedPiece() {
		return null;
	}
}