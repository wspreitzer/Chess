package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.piece.Piece;

public class PawnEnPassantAttackMove extends PawnAttackMove {

	public PawnEnPassantAttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
		super(board, movedPiece, destinationCoordinate, attackedPiece);
	}
	
	@Override
	public Board execute() {
		Builder builder = new Builder();
		for(Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
			if(!this.movedPiece.equals(piece)) {
				builder.setPiece(piece);
			}
		}
		for(Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
			if(!this.attackedPiece.equals(piece)) {
				builder.setPiece(piece);
			}
		}

		builder.setPiece(this.movedPiece.movePiece(this));
		builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getColor());
		return builder.build();
	}
}