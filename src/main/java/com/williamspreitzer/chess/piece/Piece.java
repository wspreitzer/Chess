package com.williamspreitzer.chess.piece;

import java.util.Collection;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;

public interface Piece {
	abstract Color getColor();
	abstract int getPosition();
	abstract Collection<Move> calculateLegalMoves(Board board);
	abstract PieceType getType();
	abstract boolean isFirstMove();
	abstract void setFirstMove(boolean firstMove);
	public abstract Piece movePiece(Move move);
}