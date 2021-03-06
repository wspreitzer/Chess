package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.Rook;

public class KingSideCastleMove extends CastleMove {


	public KingSideCastleMove(Board board, Piece movedPiece, 
			                  int kingDestinationCoordinate,
			                  Rook rook,
			                  int rookDestinationCoordinate) {
		super(board, movedPiece, kingDestinationCoordinate, rook, rookDestinationCoordinate);
	}
	
	public MoveType getType() {
		return MoveType.KING_SIDE_CASTLE_MOVE;
	}

	public Board getBoard() {
		return this.board;
	}

	public int getDestinationCoordinate() {
		return this.kingDestinationCoordinate;
	}

	public Piece getMovedPiece() {
		return this.movedPiece;
	}

	@Override
	public int getRookDestinationCoordinate() {
		// TODO Auto-generated method stub
		return this.rookDestinationCoordinate;
	}

	@Override
	public Piece getRook() {
		return this.rook;
	}
	
	@Override
	public String toString() {
		return "O-O";
	}
}