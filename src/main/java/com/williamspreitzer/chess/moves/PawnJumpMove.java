package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.Piece;

public class PawnJumpMove extends NonAttackingMoves {
	
	public PawnJumpMove(Board board, Piece movedPiece, int destinationCoordinate) {
		super(board, movedPiece, destinationCoordinate);
	}
	
	public MoveType getType() {
		return MoveType.PAWN_JUMP_MOVE;
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
	public Board execute() {
		final Builder builder = new Builder();
		for(final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
			if(!this.movedPiece.equals(piece)) {
				builder.setPiece(piece);
			}
		}
		for(final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
			builder.setPiece(piece);
		}
		final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
		builder.setPiece(movedPawn);
		builder.setEnPassantPawn(movedPawn);
		builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getColor());
		return builder.build();
	}
}