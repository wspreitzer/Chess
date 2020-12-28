package com.williamspreitzer.chess.piece;

import com.williamspreitzer.chess.Color;

public class PieceFactory {

	public static Piece createPiece(PieceType type, int position, Color color) {
		Piece retPiece = null;
		switch(type) {
		case ROOK:
			retPiece = new Rook(position, color);
			break;
		case KNIGHT:
			retPiece = new Knight(position, color);
			break;
		case BISHOP:
			retPiece = new Bishop(position, color);
			break;
		case QUEEN:
			retPiece = new Queen(position, color);
			break;
		case KING:
			retPiece = new King(position, color);
			break;
		case PAWN:
			retPiece = new Pawn(position, color, true);
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
