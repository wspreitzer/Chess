package com.williamspreitzer.chess.player.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.utils.GameUtils;

public final class RookStructureAnalyzer {

	private static final RookStructureAnalyzer rookAnalyzer = new RookStructureAnalyzer();
	private static final List<List<Boolean>> BOARD_COLUMNS = initColumns();
	private static final int OPEN_COLUMN_ROOK_BONUS = 25;
	private static final int NO_BONUS = 0;
	
	private static List<List<Boolean>> initColumns() {
		List<List<Boolean>> cols = new ArrayList<List<Boolean>>();
		cols.add(GameUtils.FIRST_COLUMN);
		cols.add(GameUtils.SECOND_COLUMN);
		cols.add(GameUtils.THIRD_COLUMN);
		cols.add(GameUtils.FOURTH_COLUMN);
		cols.add(GameUtils.FIFTH_COLUMN);
		cols.add(GameUtils.SIXTH_COLUMN);
		cols.add(GameUtils.SEVENTH_COLUMN);
		cols.add(GameUtils.EIGHTH_COLUMN);
		return ImmutableList.copyOf(cols);
	}

	private RookStructureAnalyzer() {}
	
	public static RookStructureAnalyzer getRookAnalyzer() {
		return rookAnalyzer;
	}
	
	public int rookStructureScore(final Board board, final Player player) {
		final int[] rookOnColumnTable = createRookColumnTable(calculatePlayerRooks(player));
		return calculateOpenFileRookBonus(rookOnColumnTable, board);
	}
	
	private static int[] createRookColumnTable(final Collection<Piece> playerRooks) {
		final int[] table = new int[8];
		for(final Piece playerRook : playerRooks) {
			table[playerRook.getPosition() % 8]++;
		}
		return table;
	}
	
	private static Collection<Piece> calculatePlayerRooks(final Player player) {
		final List<Piece> playerRooks = new ArrayList<>();
		for(final Piece piece : player.getActivePieces()) {
			if(piece.getType().isRook()) {
				playerRooks.add(piece);
			}
		}
		return ImmutableList.copyOf(playerRooks);
	}
	
	private static int calculateOpenFileRookBonus(final int[] rookOnColumnTable, Board board) {
		int bonus = NO_BONUS;
		for(final int rookLocation : rookOnColumnTable) {
			final int[] piecesOnColumn = createPiecesOnColumnTable(board);
			final int rookColumn = rookLocation/8;
			for(int i = 0; i < piecesOnColumn.length; i++) {
				if(piecesOnColumn[i] == 1 && i == rookColumn) {
					bonus += OPEN_COLUMN_ROOK_BONUS;
				}
			}
		}
		return bonus;
	}
	
	private static int[] createPiecesOnColumnTable(final Board board) {
		final int[] piecesOnColumnTable = new int[BOARD_COLUMNS.size()];
		for(final Piece piece : board.getAllPieces()) {
			for(int i = 0; i < BOARD_COLUMNS.size(); i++ ) {
				if(BOARD_COLUMNS.get(i).get(piece.getPosition())) {
					piecesOnColumnTable[i]++;
				}
			}
		}
		return piecesOnColumnTable;
	}
}