package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.piece.Piece;

public class MajorMove extends NonAttackingMoves {
	
	MajorMove(Board board, Piece movedPiece, int destinationCoordinate) {
		super(board, movedPiece, destinationCoordinate);
	}
	
	public MoveType getType() {
		return MoveType.MAJOR_MOVE;
	}

	public Board getBoard() {
		return this.board;
	}

	public int getDestinationCoordinate() {
		return this.destinationCoordinate;
	}

	public Piece getMovedPiece() {
		return this.movedPiece;
	}
	
	@Override
	public boolean equals(final Object other) {
		return this == other || other instanceof MajorMove && super.equals(other);
	}
}