package com.williamspreitzer.chess.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.piece.Piece;

public class GameUtils {

	public static final List<Boolean> FIRST_COLUMN = initColumn(0);
	public static final List<Boolean> SECOND_COLUMN = initColumn(1);
	public static final List<Boolean> THIRD_COLUMN = initColumn(2);
	public static final List<Boolean> FOURTH_COLUMN = initColumn(3);
	public static final List<Boolean> FIFTH_COLUMN = initColumn(4);
	public static final List<Boolean> SIXTH_COLUMN = initColumn(5);
	public static final List<Boolean> SEVENTH_COLUMN = initColumn(6);
	public static final List<Boolean> EIGHTH_COLUMN = initColumn(7);

	public static final List<Boolean> EIGHTH_RANK = initRank(0);
	public static final List<Boolean> SEVENTH_RANK = initRank(8);
	public static final List<Boolean> SIXTH_RANK = initRank(16);
	public static final List<Boolean> FIFTH_RANK = initRank(24);
	public static final List<Boolean> FOURTH_RANK = initRank(32);
	public static final List<Boolean> THIRD_RANK = initRank(40);
	public static final List<Boolean> SECOND_RANK = initRank(48);
	public static final List<Boolean> FIRST_RANK = initRank(56);
	public final static List<String> ALGEBRAIC_NOTATION = initializeAlgebraicNotation();
	public static final Map<String, Integer> POSITION_TO_COORDINATE = initializePositionToCoordinateMap();
	public static String PIECE_IMAGE_PATH;

	public static final int NUM_TILES = 64;
	public static final int NUM_TILES_PER_ROW = 8;
	public static final int START_TILE_INDEX = 0;
	
	public static final Properties props = GameUtils.getProperties();

	private GameUtils() {
		throw new RuntimeException("You cannot instatiate me!");
	}

	public static boolean isValidTileCoordinate(int coordinate) {
		return coordinate >= START_TILE_INDEX && coordinate < NUM_TILES;
	}

	private static List<Boolean> initColumn(int colNumber) {
		final Boolean[] column = new Boolean[NUM_TILES];
		for (int i = 0; i < column.length; i++) {
			column[i] = false;
		}
		do {
			column[colNumber] = true;
			colNumber += NUM_TILES_PER_ROW;
		} while (colNumber < NUM_TILES);
		return Collections.unmodifiableList(Arrays.asList(column));
	}

	private static List<Boolean> initRank(int rankNumber) {
		Boolean[] row = new Boolean[NUM_TILES];
		for (int i = 0; i < row.length; i++) {
			row[i] = false;
		}
		do {
			row[rankNumber] = true;
			rankNumber++;
		} while (rankNumber % NUM_TILES_PER_ROW != 0);

		return Collections.unmodifiableList(Arrays.asList(row));
	}

	public static boolean isTilesEmpty(Board board, int... coordinates) {
		boolean retVal = true;
		for (int aCoordinate : coordinates) {
			if (board.getTile(aCoordinate).isTileOccupied()) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}

	public static boolean areTilesBeingAttacked(Collection<Move> opponentMoves, int... coordinates) {
		boolean retVal = false;
		for (int aCoordinate : coordinates) {
			for (Move aMove : opponentMoves) {
				if (aMove.getDestinationCoordinate() == aCoordinate) {
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

	public static String getPositionAtCoordinate(final int coordinate) {
		return ALGEBRAIC_NOTATION.get(coordinate);
	}
	
	private static Map<String, Integer> initializePositionToCoordinateMap() {
		final Map<String, Integer> positionToCoordinate = new HashMap<String, Integer>();
		for (int i = START_TILE_INDEX; i < NUM_TILES; i++) {
			positionToCoordinate.put(ALGEBRAIC_NOTATION.get(i), i);
		}
		return positionToCoordinate;
	}

	private static List<String> initializeAlgebraicNotation() {
		return Collections.unmodifiableList(Arrays.asList("a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8", "a7", "b7",
				"c7", "d7", "e7", "f7", "g7", "h7", "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6", "a5", "b5", "c5",
				"d5", "e5", "f5", "g5", "h5", "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4", "a3", "b3", "c3", "d3",
				"e3", "f3", "g3", "h3", "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2", "a1", "b1", "c1", "d1", "e1",
				"f1", "g1", "h1"));
	}

	public static String iconName(Board board, String iconPath, Object obj) {
		StringBuffer sb = new StringBuffer();
		sb.append(GameUtils.class.getResource(iconPath).getPath());
		sb.append("/");
		if (obj instanceof Integer) {
			sb.append(board.getTile(((Integer) obj).intValue()).getPiece().getColor().toString().substring(0, 1));
			sb.append(board.getTile(((Integer) obj).intValue()).getPiece().toString());
		} else if (obj instanceof Piece) {
			Piece piece = (Piece) obj;
			sb.append(piece.getColor().toString().substring(0, 1));
			sb.append(piece.toString());
		}
		sb.append(".gif");

		return sb.toString();
	}

	public static String calculateCheckAndCheckmateHash(final Board board) {
		String retVal;
		if (board.getCurrentPlayer().isInCheckMate()) {
			retVal = "#";
		} else if (board.getCurrentPlayer().isInCheck()) {
			retVal = "+";
		} else {
			retVal = "";
		}
		return retVal;
	}

	public static boolean isEndGame(final Board board) {
		return board.getCurrentPlayer().isInCheckMate() ||
			   board.getCurrentPlayer().isInStaleMate();
	}
	
	public static boolean isThreatenedBoardImmediate(final Board board) { 
		return board.getWhitePlayer().isInCheck() || board.getBlackPlayer().isInCheck();
	}
	
	public static Properties getProperties() {
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(GameUtils.class.getResource("/chess.properties").toURI()));
			props.load(fis);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return props;
	}
}