package com.williamspreitzer.chess.board.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.piece.Piece;

public class GameUtils {

	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] THIRD_COLUMN = initColumn(2);
	public static final boolean[] FOURTH_COLUMN = initColumn(3);
	public static final boolean[] FIFTH_COLUMN = initColumn(4);
	public static final boolean[] SIXTH_COLUMN = initColumn(5);
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
	public final static List<String> ALGEBRAIC_NOTATION = initializeAlgebraicNotation();
	public static final Map<String, Integer> POSITION_TO_COORDINATE = initializePositionToCoordinateMap();
	public static final int NUM_TILES = 64;
	public static final int NUM_TILES_PER_ROW = 8;
	public static final int START_TILE_INDEX = 0;
	private GameUtils() {
		throw new RuntimeException("You cannot instatiate me!");
	}
	
	public static boolean isValidTileCoordinate(int coordinate) {
		return coordinate >= START_TILE_INDEX && coordinate < NUM_TILES;
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
	
	public static int getCoordinateAtPosition(final String position) {
		return POSITION_TO_COORDINATE.get(position);
	}
	
	private static Map<String, Integer> initializePositionToCoordinateMap() {
		final Map<String, Integer> positionToCoordinate = new HashMap<String, Integer>();
		for (int i = START_TILE_INDEX; i < NUM_TILES; i++ ) {
			positionToCoordinate.put(ALGEBRAIC_NOTATION.get(i), i);
		}
		return positionToCoordinate;
	}
	
	private static List<String> initializeAlgebraicNotation() {
		return Collections.unmodifiableList(Arrays.asList(
				"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
				"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
				"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
				"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
				"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
				"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
				"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
				"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"));
	}
	
	public static String iconName(Board board, Object obj) {
		StringBuffer sb = new StringBuffer();
		sb.append(GameUtils.class.getResource("../../../../../art/simple").getPath());
		sb.append("/");
		if(obj instanceof Integer) {
			sb.append(board.getTile(((Integer) obj).intValue()).getPiece().getColor().toString().substring(0, 1));
			sb.append(board.getTile(((Integer) obj).intValue()).getPiece().toString());
		} else if (obj instanceof Piece ){
			Piece piece = (Piece) obj;
			sb.append(piece.getColor().toString().substring(0,1));
			sb.append(piece.toString());
		}
		sb.append(".gif");
		return sb.toString();
	}
	
	public static String calculateCheckAndCheckmateHash(final Board board) {
		String retVal;
		if(board.getCurrentPlayer().isInCheckMate()) {
			retVal = "#";
		} else if (board.getCurrentPlayer().isInCheck()) {
			retVal = "+";
		} else {
			retVal = "";
		}
		return retVal;
	}
}