package com.williamspreitzer.chess.player.ai;

import java.util.Collection;
import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.utils.GameUtils;

import static com.williamspreitzer.chess.utils.GameUtils.mvvlva;
public enum MoveSorter {

	SORT {
		@Override
		Collection<Move> sort(Collection<Move> moves) {
			return Ordering.from(SMART_SORT).immutableSortedCopy(moves);
		}
	},
	STANDARD {
		@Override
		Collection<Move> sort(Collection<Move> moves) {
			return Ordering.from((Comparator<Move>) (move1, move2) -> ComparisonChain.start()
					.compare(move1.isCastle(), move2.isCastle())
					.compare(mvvlva(move2), mvvlva(move1))
					.result()).immutableSortedCopy(moves);
		}
	},
	EXPENSIVE {
		@Override
		Collection<Move> sort(Collection<Move> moves) {
			return Ordering.from((Comparator<Move>) (move1, move2) -> ComparisonChain.start()
					.compare(GameUtils.kingThreat(move1), GameUtils.kingThreat(move2))
					.compare(mvvlva(move2), mvvlva(move1))
					.result()).immutableSortedCopy(moves);
		}
	};
	
	Comparator<Move> SMART_SORT = (Move m, Move m2) -> {
		return ComparisonChain.start()
				.compare(m.isAttack(), m2.isAttack())
				.compare(m.isCastle(), m2.isCastle())
				.compare(m2.getMovedPiece().getPieceValue(), m.getMovedPiece().getPieceValue()).result();
	};

	abstract Collection<Move> sort(Collection<Move> moves);
}