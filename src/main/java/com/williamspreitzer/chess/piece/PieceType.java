package com.williamspreitzer.chess.piece;

public enum PieceType {
	ROOK("R", 500),
	KNIGHT("N", 300),
	BISHOP("B", 300),
	QUEEN("Q",900),
	KING("K", 10000),
	PAWN("P", 100),
	EnPassantPawn("E", 100);
	
	private String pieceName;
	private int pieceValue;
	
	PieceType(String pieceName, int pieceValue) {
		this.pieceName = pieceName;
		this.pieceValue = pieceValue;
	}
	
	@Override
	public String toString() {
		return this.pieceName;
	}
	
	public int getPieceValue() {
		return this.pieceValue;
	}
	
}
