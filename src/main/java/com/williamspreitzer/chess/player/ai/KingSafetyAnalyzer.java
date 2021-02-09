package com.williamspreitzer.chess.player.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.utils.GameUtils;

public class KingSafetyAnalyzer {
	private static KingSafetyAnalyzer kingSafetyAnalyzer;
	private static final List<List<Boolean>> COLUMNS = initColumns();
	
	private KingSafetyAnalyzer() {
	}
	
	public static KingSafetyAnalyzer getKingSafetyAnalyzer() {
		if (kingSafetyAnalyzer == null) {
			kingSafetyAnalyzer = new KingSafetyAnalyzer();
		}
		
		return kingSafetyAnalyzer;
	}
	private static List<List<Boolean>> initColumns() {
		final List<List<Boolean>> columns = new ArrayList<List<Boolean>>();
		columns.add(GameUtils.FIRST_COLUMN);
		columns.add(GameUtils.SECOND_COLUMN);
		columns.add(GameUtils.THIRD_COLUMN);
		columns.add(GameUtils.FOURTH_COLUMN);
		columns.add(GameUtils.FIFTH_COLUMN);
		columns.add(GameUtils.SIXTH_COLUMN);
		columns.add(GameUtils.SEVENTH_COLUMN);
		columns.add(GameUtils.EIGHTH_COLUMN);
		return ImmutableList.copyOf(columns);
	}
	
	public KingDistance calculateKingTropism(final Player player) {
		final int playerKingSquare = player.getPlayerKing().getPosition();
		final Collection<Move> enemyMoves = player.getOpponent().getPlayerLegalMoves();
		Piece closestPiece = null;
		int closestDistance = Integer.MAX_VALUE;
		for(final Move move : enemyMoves) {
			final int currentDistance = calculateChebyshevDistance(playerKingSquare, move.getDestinationCoordinate());
			if(currentDistance < closestDistance) {
				closestDistance = currentDistance;
				closestPiece = move.getMovedPiece();
			}
		}
		return new KingDistance(closestPiece, closestDistance);
	}

	private int calculateChebyshevDistance(int kingTileId, int enemyAttackTileId) {
		final int squareOneRank = getRank(kingTileId);
		final int squareTwoRank = getRank(enemyAttackTileId);

		final int squareOneFile = getFile(kingTileId);
		final int squareTwoFile = getFile(enemyAttackTileId);
		
		final int rankDistance = Math.abs(squareTwoRank - squareOneRank);
		final int fileDistance = Math.abs(squareTwoFile - squareOneFile);
		
		return Math.max(rankDistance, fileDistance);
	}
	
	private static int getFile(final int coordinate) {
		int retVal = 0;
		switch (coordinate) {
		case 0: case 8: case 16: case 24: case 32: case 40: case 48: case 56:
			retVal = 1;
			break;
		case 1: case 9: case 17: case 25: case 33: case 41: case 49: case 57:
			retVal = 2;
			break;
		case 2: case 10: case 18: case 26: case 34: case 42: case 50: case 58:
			retVal = 3;
			break;
		case 3: case 11: case 19: case 27: case 35: case 43: case 51: case 59:
			retVal = 4;
			break;
		case 4: case 12: case 20: case 28: case 36: case 44: case 52: case 60:
			retVal = 5;
			break;
		case 5: case 13: case 21: case 29: case 37: case 45: case 53: case 61:
			retVal = 6;
			break;
		case 6: case 14: case 22: case 30: case 38: case 46: case 54: case 62:
			retVal= 7;
			break;
		case 7: case 15: case 23: case 31: case 39: case 47: case 55: case 63:
			retVal = 8;
			break;
		default:
			throw new RuntimeException("Should not reach here!");
		}
		return retVal;
	}
	
	private static int getRank(final int coordinate) {
		int retVal = 0;
		switch (coordinate) {
		case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: 
			retVal = 8;
			break;
		case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15:
			retVal = 7;
			break;
		case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23:
			retVal = 6;
			break;
		case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 31:
			retVal = 5;
			break;
		case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39:
			retVal = 4;
			break;
		case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47:
			retVal = 3;
			break;
		case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55:
			retVal = 2;
			break;
		case 56: case 57: case 58: case 59: case 60: case 61: case 62: case 63:
			retVal = 1;
			break;
		default:
			throw new RuntimeException("Should not reach here!");
		}
		return retVal;
	}
}
