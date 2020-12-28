package com.williamspreitzer.chess.board;

import com.williamspreitzer.chess.piece.Piece;

public class OccupiedTile extends Tile {

	Piece pieceOnTile;
	
	OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
		super(tileCoordinate);
		this.pieceOnTile = pieceOnTile;
	}
	
	@Override
	public boolean isTileOccupied() {
		return true;
	}
	
	@Override
	public Piece getPiece() {
		return this.pieceOnTile;
	}
	
	@Override
	public String toString() {
		return pieceOnTile.getColor().isBlack() ? this.pieceOnTile.toString()
				.toLowerCase() : this.pieceOnTile.toString();
	}
}