package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.piece.Piece;

public abstract class AttackMove implements Move {

	Board board;
	Piece movedPiece;
	int destinationCoordinate;
	Piece attackedPiece;
	
	AttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
		this.attackedPiece = attackedPiece;
	}
	
	public abstract MoveType getType();

	public abstract Board getBoard();

	public abstract int getDestinationCoordinate();

	public abstract Piece getMovedPiece();

	public abstract Piece getAttackedPiece();
	
	public int getCurrentCoordinate() {
		return this.movedPiece.getPosition();
	}
	
	public boolean isAttack() {
		return true;
	}
	
	public boolean isCastle() {
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * this.board.hashCode();
		result = prime * this.movedPiece.hashCode();
		result = prime * this.attackedPiece.hashCode();
		result = prime * this.destinationCoordinate;
		return result;
	}
	
	@Override
	public boolean equals(Object other) {
		Boolean retVal = null;
		if(this == other) {
			retVal = true;
		} else if( !( other instanceof AttackMove )) {
			retVal = false;
		} else {
			final AttackMove otherMove = ( AttackMove ) other;
			if(this.getCurrentCoordinate() == otherMove.getCurrentCoordinate() && 
			   this.board == otherMove.getBoard() &&
			   this.movedPiece == otherMove.getMovedPiece() &&
			   this.destinationCoordinate == otherMove.getDestinationCoordinate() &&
			   this.attackedPiece == otherMove.attackedPiece) {
				retVal = true;
			} else {
				retVal = false;
			}
		}
		return retVal.booleanValue();
	}
	public Board execute() {
		return null;
	}
}
