package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.utils.GameUtils;

public abstract class NonAttackingMoves implements Move {

	Board board;
	Piece movedPiece;
	int destinationCoordinate;
	
	public NonAttackingMoves(Board board, Piece movedPiece, int destinationCoordinate) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate =  destinationCoordinate;
	}

	public abstract MoveType getType();
	public abstract Board getBoard();
	public abstract int getDestinationCoordinate();
	public abstract Piece getMovedPiece();
	
	public int getCurrentCoordinate() {
		return this.movedPiece.getPosition();
	}
	
	public boolean isAttack() {
		return false;
	}
	
	public boolean isCastle() {
		return false;
	}
	
	public Board execute() {
		Builder builder = new Builder();
		for(Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
			if(!this.movedPiece.equals(piece)) {
				builder.setPiece(piece);
			}
		}
		
		for(Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
			builder.setPiece(piece);
		}
		
		builder.setPiece(this.movedPiece.movePiece(this));
		builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getColor());
		return builder.build();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.board.hashCode();
		result = prime * result + this.movedPiece.hashCode();
		result = prime * result + this.destinationCoordinate;
		return result;
	}
	
	@Override
	public boolean equals(final Object other) {
		Boolean retVal = null;
		if(this == other ) {
			retVal = true;
		} else if( ! ( other instanceof NonAttackingMoves) || ( other instanceof NullMove)) {
			retVal = false;
		} else {
			final NonAttackingMoves otherMove = (NonAttackingMoves ) other;
			if(this.getCurrentCoordinate() == otherMove.getCurrentCoordinate() &&
			   this.destinationCoordinate == otherMove.getDestinationCoordinate() &&
			   this.movedPiece == otherMove.getMovedPiece() ) {
				retVal = true;
			} else {
				retVal = false;
			}
		}
		return retVal.booleanValue();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(GameUtils.ALGEBRAIC_NOTATION.get(movedPiece.getPosition()));
		sb.append("-");
		sb.append(GameUtils.ALGEBRAIC_NOTATION.get(destinationCoordinate));
		return sb.toString();
	}
}