package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.piece.Piece;

public class MajorAttackMove extends AttackMove {

	MajorAttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
		super(board, movedPiece, destinationCoordinate, attackedPiece);
	}

	@Override
	public MoveType getType() {
		return MoveType.MAJOR_ATTACK_MOVE;
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
	public Piece getAttackedPiece() {
		return this.attackedPiece;
	}
}