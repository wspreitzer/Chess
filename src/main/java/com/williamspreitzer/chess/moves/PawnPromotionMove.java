package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.utils.GameUtils;

public class PawnPromotionMove extends PawnMove{

	final Move decoratedMove;
	final Pawn promotedPawn;
	final Piece promotionPiece;
	
	protected PawnPromotionMove(final PawnMove decoratedMove, final Piece promotionPiece) {
		super(decoratedMove.getBoard(), decoratedMove.getMovedPiece(), decoratedMove.getDestinationCoordinate());
		this.decoratedMove = decoratedMove;
		this.promotedPawn = (Pawn) decoratedMove.getMovedPiece();
		this.promotionPiece = promotionPiece;
	}

	@Override
	public MoveType getType() {
		return MoveType.PAWN_PROMOTION_MOVE;
	}

	@Override
	public Board getBoard() {
		return this.board;
	}

	@Override
	public int getDestinationCoordinate() {
		return this.destinationCoordinate;
	}

	@Override
	public Piece getMovedPiece() {
		return this.movedPiece;
	}
	
	@Override
	public int hashCode() {
		return decoratedMove.hashCode() * ( promotedPawn.hashCode());
	}
	
	@Override
	public boolean equals(final Object other) {
		return this == other || other instanceof PawnPromotionMove && (super.equals(other));
	}
	
	@Override
	public Board execute() {
		final Board pawnMovedBoard = this.decoratedMove.execute();
		final Builder builder = new Builder();
		//builder.setPromotionPiece(promotionPiece);
		pawnMovedBoard.getCurrentPlayer().getActivePieces().stream().filter(piece -> !this.promotedPawn.equals(piece)).forEach(builder :: setPiece);
		pawnMovedBoard.getCurrentPlayer().getOpponent().getActivePieces().forEach(builder :: setPiece);
		builder.setPiece(this.promotionPiece);
		builder.setMoveMaker(pawnMovedBoard.getCurrentPlayer().getColor());
		//builder.setMoveTransition(this);
		return builder.build();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(GameUtils.getPositionAtCoordinate(this.movedPiece.getPosition()));
		sb.append("-");
		sb.append(GameUtils.getPositionAtCoordinate(this.destinationCoordinate));
		sb.append("=");
		sb.append(this.promotionPiece.getType());
		return sb.toString();
	}
}