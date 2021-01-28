package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.piece.Piece;

public class PawnAttackMove extends AttackMove {

	PawnAttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
		super(board, movedPiece, destinationCoordinate, attackedPiece);
	}

	private Board board;
	
	@Override
	public MoveType getType() {
		return MoveType.PAWN_ATTACK_MOVE;
	}

	@Override
	public Board getBoard() {
		return this.board;
	}


	@Override
	public Piece getMovedPiece() {
		return this.movedPiece;
	}

	@Override
	public Piece getAttackedPiece() {
		return this.attackedPiece;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(GameUtils.ALGEBRAIC_NOTATION.get(movedPiece.getPosition()));
		sb.append("x");
		sb.append(GameUtils.ALGEBRAIC_NOTATION.get(destinationCoordinate));
		return sb.toString();
	}
}
