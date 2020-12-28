package com.williamspreitzer.chess.board.utils;

import java.util.Collection;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;

public class GameUtils {

	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLUMN = initColumn(6);
	public static final boolean[] EIGHTH_COLUMN = initColumn(7);
	
	public static final boolean[] FIRST_ROW = initRow(0);
	public static final boolean[] SECOND_ROW = initRow(8);
	public static final boolean[] THIRD_ROW = initRow(16);
	public static final boolean[] FOURTH_ROW = initRow(24);
	public static final boolean[] FIFTH_ROW = initRow(32);
	public static final boolean[] SIXTH_ROW = initRow(40);
	public static final boolean[] SEVENTH_ROW = initRow(48);
	public static final boolean[] EIGHTH_ROW = initRow(56);
	
	public static final int NUM_TILES = 64;
	public static final int NUM_TILES_PER_ROW = 8;
	
	private GameUtils() {
		throw new RuntimeException("You cannot instatiate me!");
	}
	
	public static boolean isValidTileCoordinate(int coordinate) {
		return coordinate >= 0 && coordinate < NUM_TILES;
	}
	
	private static boolean[] initColumn(int colNumber) {
		boolean[] column = new boolean[NUM_TILES];
		do {
			column[colNumber] = true;
			colNumber += NUM_TILES_PER_ROW;
		} while (colNumber < NUM_TILES );
		return column;
	}
	
	private static boolean[] initRow(int rowNumber) {
		boolean[] row = new boolean[NUM_TILES];
		do {
			row[rowNumber] = true;
			rowNumber++;
		} while (rowNumber % NUM_TILES_PER_ROW != 0);
		
		return row;
	}
	
	public static boolean isTilesEmpty(Board board, int ...coordinates) {
		boolean retVal = true;
		for(int aCoordinate : coordinates) {
			if(board.getTile(aCoordinate).isTileOccupied()) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
	
	public static boolean areTilesBeingAttacked(Collection<Move> opponentMoves, int ...coordinates) {
		boolean retVal = false;
		for(int aCoordinate : coordinates) {
			for(Move aMove : opponentMoves) {
				if(aMove.getDestinationCoordinate() == aCoordinate) {
					retVal = true;
					break;
				} else {
					continue;
				}
			}
		}
		return retVal;
	}
}