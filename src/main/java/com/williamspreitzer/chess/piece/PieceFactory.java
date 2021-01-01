package com.williamspreitzer.chess.piece;

import com.williamspreitzer.chess.Color;

public class PieceFactory {

	public static Piece createPiece(PieceType type, int position, Color color, boolean isFirstMove) {
		Piece retPiece = null;
		switch(type) {
		case ROOK:
			retPiece = new Rook(position, color, isFirstMove);
			break;
		case KNIGHT:
			retPiece = new Knight(position, color, isFirstMove);
			break;
		case BISHOP:
			retPiece = new Bishop(position, color, isFirstMove);
			break;
		case QUEEN:
			retPiece = new Queen(position, color, isFirstMove);
			break;
		case KING:
			retPiece = new King(position, color, isFirstMove);
			break;
		case PAWN:
			retPiece = new Pawn(position, color, isFirstMove);
			break;
		case EnPassantPawn:
			retPiece = new EnPassantPawn(position, color);
			break;
		default:
			break;
		}
		
		return retPiece;
	}
}
