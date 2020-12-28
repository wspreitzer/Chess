package com.williamspreitzer.chess.piece;

public enum PieceType {
	ROOK("R"),
	KNIGHT("N"),
	BISHOP("B"),
	QUEEN("Q"),
	KING("K"),
	PAWN("P"),
	EnPassantPawn("E");
	
	private String pieceName;
	
	PieceType(String pieceName) {
		this.pieceName = pieceName;
	}
	
	@Override
	public String toString() {
		return this.pieceName;
	}
	
}
