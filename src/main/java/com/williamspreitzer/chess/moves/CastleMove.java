package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.Rook;

public abstract class CastleMove implements Move {

	protected Board board;
	protected Piece movedPiece;
	protected Rook rook;
	protected int kingDestinationCoordinate;
	protected int rookDestinationCoordinate;
	
	
	public CastleMove(Board board, Piece movedPiece, int kingDestinationCoordinate, Rook rook, int rookDestinatiionCoordinate) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.kingDestinationCoordinate = kingDestinationCoordinate;
		this.rook = rook;
		this.rookDestinationCoordinate = rookDestinatiionCoordinate;
	}
	
	public abstract MoveType getType();
	public abstract Board getBoard();
	public abstract int getDestinationCoordinate();
	public abstract int getRookDestinationCoordinate();
	public abstract Piece getRook();
	public abstract Piece getMovedPiece();
	
	public int getCurrentCoordinate() {
		return this.movedPiece.getPosition();
	}
	
	public boolean isAttack() {
		return false;
	}
	
	public boolean isCastle() {
		return true;
	}
	public Board execute() {
		Builder builder = new Builder();
		for(Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
			if(!this.movedPiece.equals(piece) && !this.rook.equals(piece)) {
				builder.setPiece(piece);
			}
		}
		
		for(Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
			builder.setPiece(piece);
		}
		
		this.movedPiece.setFirstMove(false);
		this.rook.setFirstMove(false);
		builder.setPiece(this.movedPiece.movePiece(this));
		builder.setPiece(this.rook.movePiece(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board, this.rook, rookDestinationCoordinate)));
		builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getColor());
		return builder.build();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.board.hashCode();
		result = prime * result + this.movedPiece.hashCode();
		result = prime * result + this.rook.hashCode();
		result = prime * result + this.kingDestinationCoordinate;
		result = prime * result + this.rookDestinationCoordinate;
		return result;
	}
	
	@Override
	public boolean equals(final Object other) {
		Boolean retVal = null;
		if(this == other) {
			retVal = true;
		} else if( ! ( other instanceof CastleMove )) {
			retVal = false;
		} else {
			final CastleMove otherMove = (CastleMove) other;
			if (this.getCurrentCoordinate() == otherMove.getCurrentCoordinate() &&
				this.kingDestinationCoordinate == otherMove.kingDestinationCoordinate &&
				this.rookDestinationCoordinate == otherMove.rookDestinationCoordinate &&
				this.movedPiece == otherMove.getMovedPiece() &&
				this.rook == otherMove.rook) {
				retVal = true;
			} else {
				retVal = false;
			}
		}
		return retVal.booleanValue();
	}
}