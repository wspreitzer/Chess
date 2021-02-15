package com.williamspreitzer.chess.player.ai;

import java.util.Collection;
import java.util.stream.Collectors;

import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.utils.GameConstants;

public class PawnStructureAnalyzer {
	
	private static PawnStructureAnalyzer pawnAnalyzer;
	
	public static PawnStructureAnalyzer getPawnAnalyzer() {
		if(pawnAnalyzer == null) {
			pawnAnalyzer = new PawnStructureAnalyzer();
		}
		return pawnAnalyzer;
	}
	
	public int isolatedPawnPenalty(final Player player) {
		return calculateIsolatedPawnPenalty(createPawnColumnTable(calculatePlayerPawns(player)));
	}
	
	public int doubledPawnPenalty(final Player player) {
		return calculatePawnColumnStack(createPawnColumnTable(calculatePlayerPawns(player)));
	}
	
	public Collection<Piece> calculatePlayerPawns(final Player player) {
		return player.getActivePieces().stream().filter(piece -> piece.getType() == PieceType.PAWN).collect(Collectors.toList());
	}
	
	public int pawnStructureScore(final Player player) {
		final int[] pawnsOnColumnTable = createPawnColumnTable(calculatePlayerPawns(player));
		return calculatePawnColumnStack(pawnsOnColumnTable) + calculateIsolatedPawnPenalty(pawnsOnColumnTable);
	}
	
	private int calculatePawnColumnStack(final int[] pawnsOnColumnTable) {
		int pawnStackPenalty = 0;
		for(final int pawnStack : pawnsOnColumnTable) {
			if(pawnStack > 1) {
				pawnStackPenalty += pawnStack;
			}
		}
		return pawnStackPenalty * GameConstants.DOUBLED_PAWN_PENALTY;
	}
	
	private int calculateIsolatedPawnPenalty(final int[] pawnsOnColumnTable) {
		int numIsolatedPawns = 0;
		if(pawnsOnColumnTable[0] > 0 && pawnsOnColumnTable[1] == 0) {
			numIsolatedPawns += pawnsOnColumnTable[0];
		}
		if(pawnsOnColumnTable[7] > 0 && pawnsOnColumnTable[6] == 0) {
			numIsolatedPawns += pawnsOnColumnTable[7];
		}
		for(int i = 1; i < pawnsOnColumnTable.length - 1; i++) {
            if((pawnsOnColumnTable[i-1] == 0 && pawnsOnColumnTable[i+1] == 0)) {
                numIsolatedPawns += pawnsOnColumnTable[i];
            }
        }
		return numIsolatedPawns * GameConstants.ISOLATED_PAWN_PENALTY;
	}
	
	private int[] createPawnColumnTable(final Collection<Piece> playerPawns) {
		final int[] table = new int[8];
		for(final Piece playerPawn : playerPawns) {
			table[playerPawn.getPosition() % 8]++;
		}
		return table;
	}
}