package com.williamspreitzer.chess.board;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.piece.Piece;

public abstract class Tile {

	protected int tileCoordinate;
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	protected Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	
	public abstract boolean isTileOccupied();
	public abstract Piece getPiece();
	
	public int getTileCoordinate() {
		return this.tileCoordinate;
	}
	
	public static Tile createTile(int tileCoordinate, Piece piece) {
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		Map<Integer, EmptyTile> emptyTileMap = new HashMap<Integer, EmptyTile>();
		for(int i=0; i < GameUtils.NUM_TILES; i++ ) {
			emptyTileMap.put(i, new EmptyTile(i));
		}
		return ImmutableMap.copyOf(emptyTileMap);
	}
	
}