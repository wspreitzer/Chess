package com.williamspreitzer.chess.piece;

import java.util.Collection;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;

public class EnPassantPawn implements Piece{

	private int position;
	private Color color;
	private int cachedHashCode;
	private boolean isFirstMove;
	
	EnPassantPawn(int position, Color color) {
		this.position = position;
		this.color = color;
		this.cachedHashCode = computeHashCode();
		this.isFirstMove = false;
	}
	
	public Color getColor() {
		return this.color;
	}

	public int getPosition() {
		return this.position;
	}
	
	public boolean isFirstMove() {
		return false;
	}
	
	public void setFirstMove(boolean isFirstMove) {
		this.isFirstMove = isFirstMove;
	}

	public Collection<Move> calculateLegalMoves(Board board) {
		return null;
	}
	
	public PieceType getType() {
		return PieceType.EnPassantPawn;
	}

	public Piece movePiece(Move move) {
		return PieceFactory.createPiece(move.getMovedPiece().getType(), move.getDestinationCoordinate(),  move.getMovedPiece().getColor(), false);
	}
	
	@Override
	public boolean equals(Object other) {
		Boolean retVal = null;
		if(this == other) {
			retVal = true;
		} else if( ! ( other instanceof EnPassantPawn ) ) {
			retVal = false;
		} else {
			EnPassantPawn ptherEnPassantPawn = (EnPassantPawn) other;
			if (this.position == ptherEnPassantPawn.position && this.color == ptherEnPassantPawn.getColor()) {
				retVal = true;
			} else {
				retVal = false;
			}
		}
		return retVal.booleanValue();
	}
	
	@Override
	public int hashCode() {
		return this.cachedHashCode;
	}
	
	private int computeHashCode() {
		int result = getType().hashCode();
		result = 31 * result + this.color.hashCode();
		result = 31 * result + this.position;
		result = 31 * result + (isFirstMove ? 1 : 0);
		return result;
	}
}