package com.williamspreitzer.chess.utils;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.piece.Bishop;
import com.williamspreitzer.chess.piece.Knight;
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.piece.Queen;
import com.williamspreitzer.chess.piece.Rook;


public class PieceUtils {

	private final Table<Color, Integer, Queen> ALL_POSSIBLE_QUEENS = createAllPossibleMovedQueens();
	private final Table<Color, Integer, Rook> ALL_POSSIBLE_ROOKS = createAllPossibleMovedRooks();
	private final Table<Color, Integer, Knight> ALL_POSSIBLE_KNIGHTS = createAllPossibleMovedKnights();
	private final Table<Color, Integer, Bishop> ALL_POSSIBLE_BISHOPS = createAllPossibleMovedBishops();
	private final Table<Color, Integer, Pawn> ALL_POSSIBLE_PAWNS = createAllPossibleMovedPawns();
	
	Pawn getMovedPawn(final Color color, final int destinationCoordinate) {
		return ALL_POSSIBLE_PAWNS.get(color, destinationCoordinate);
	}
	
	Knight getMovedKnight(final Color color, int destinationCoordinate) {
		return ALL_POSSIBLE_KNIGHTS.get(color, destinationCoordinate);
	}
	
	Bishop getMovedBishop(final Color color, int destinationCoordinate) {
		return ALL_POSSIBLE_BISHOPS.get(color, destinationCoordinate);
	}
	
	Rook getMovedRook(final Color color, int destinationCoordinagte) {
		return ALL_POSSIBLE_ROOKS.get(color, destinationCoordinagte);
	}
	
	Queen getMovedQueen(final Color color, int destinationCoordinate) {
		return ALL_POSSIBLE_QUEENS.get(color, destinationCoordinate);
	}
	
	private static Table<Color, Integer, Queen> createAllPossibleMovedQueens() {
		final ImmutableTable.Builder<Color, Integer, Queen> pieces = ImmutableTable.builder();
		for(final Color color : Color.values()) {
			for(int i=0; i < GameUtils.NUM_TILES; i++) {
				pieces.put(color, i, (Queen) PieceFactory.createPiece(PieceType.QUEEN, i, color, false));
			}
		}
		return pieces.build();
	}
	
	private static Table<Color, Integer, Rook> createAllPossibleMovedRooks() {
		final ImmutableTable.Builder<Color, Integer, Rook> pieces = ImmutableTable.builder();
		for(final Color color : Color.values()) {
			for(int i = 0; i < GameUtils.NUM_TILES; i++) {
				pieces.put(color, i, (Rook) PieceFactory.createPiece(PieceType.ROOK, i, color, false));
			}
		}
		return pieces.build();
	}
	
	private static Table<Color, Integer, Knight> createAllPossibleMovedKnights() {
		final ImmutableTable.Builder<Color, Integer, Knight> pieces = ImmutableTable.builder();
		for(Color color : Color.values()) {
			for(int i = 0; i < GameUtils.NUM_TILES; i++) {
				pieces.put(color, i, (Knight) PieceFactory.createPiece(PieceType.KNIGHT, i, color, false));
			}
		}
		return pieces.build();
	}
	private static Table<Color, Integer, Bishop> createAllPossibleMovedBishops() {
		final ImmutableTable.Builder<Color, Integer, Bishop> pieces = ImmutableTable.builder();
		for(Color color : Color.values()) {
			for(int i = 0; i < GameUtils.NUM_TILES; i++) {
				pieces.put(color, i, (Bishop) PieceFactory.createPiece(PieceType.BISHOP, i, color, false));
			}
		}
		return pieces.build();
	}
	private static Table<Color, Integer, Pawn> createAllPossibleMovedPawns() {
		final ImmutableTable.Builder<Color, Integer, Pawn> pieces = ImmutableTable.builder();
		for(Color color : Color.values()) {
			for(int i = 0; i < GameUtils.NUM_TILES; i++) {
				pieces.put(color, i, (Pawn) PieceFactory.createPiece(PieceType.PAWN, i, color, false));
			}
		}
		return pieces.build();
	}
}