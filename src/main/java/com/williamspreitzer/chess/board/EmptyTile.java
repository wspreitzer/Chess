package com.williamspreitzer.chess.board;

import com.williamspreitzer.chess.piece.Piece;

public class EmptyTile extends Tile {

	EmptyTile(int tileCoordinate) {
		super(tileCoordinate);
	}

	@Override
	public boolean isTileOccupied() {
		return false;
	}

	@Override
	public Piece getPiece() {
		return null;
	}
	
	@Override
	public String toString() {
		return "-";
	}
}