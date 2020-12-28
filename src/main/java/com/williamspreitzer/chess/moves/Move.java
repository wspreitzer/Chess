package com.williamspreitzer.chess.moves;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.piece.Piece;

public interface Move {
	abstract MoveType getType();
	abstract Board getBoard();
	abstract int getDestinationCoordinate();
	abstract int getCurrentCoordinate();
	abstract Piece getMovedPiece();
	abstract Board execute();
	abstract boolean isAttack();
	abstract boolean isCastle();
}
