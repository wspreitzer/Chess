package com.williamspreitzer.chess.player.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.utils.GameUtils;

public class MoveOrdering {

	private final BoardEvaluator evaluator;
	
	private static MoveOrdering moveOrder;
	private static final int ORDER_SEARCH_DEPTH = 2;
	
	private MoveOrdering(Board board) {
		this.evaluator = new StandardBoardEvaluator(board, ORDER_SEARCH_DEPTH).getEvaluator();
	}
	
	public static MoveOrdering getMoveOrder(Board board) {
		if(moveOrder == null) {
			moveOrder = new MoveOrdering(board);
		}
		return moveOrder;
	}
	
	public List<Move> orderMoves(final Board board) {
		return orderImpl(board, ORDER_SEARCH_DEPTH);
	}
	
	private List<Move> orderImpl(final Board board, final int depth) {
		final List<MoveOrderEntry> moveOrderEntries = new ArrayList<MoveOrderEntry>();
		for(final Move move : board.getCurrentPlayer().getPlayerLegalMoves()) {
			final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
			if(trans.getStatus().isDone()) {
				final int attackBonus = calculateAttackBonus(board.getCurrentPlayer(), move);
				final int currentValue = attackBonus + (board.getCurrentPlayer().getColor().isWhite() ? 
					min(trans.getBoard(), depth -1) :
					max(trans.getBoard(), depth -1));
				moveOrderEntries.add(new MoveOrderEntry(move, currentValue));
			}
		}
		Collections.sort(moveOrderEntries, (o1, o2) -> Ints.compare(o2.getScore(), o1.getScore()));
		final List<Move> orderedMoves = new ArrayList<Move>();
		for(final MoveOrderEntry entry : moveOrderEntries) {
			orderedMoves.add(entry.getMove());
		}
		return ImmutableList.copyOf(orderedMoves);
	}
	
	private int calculateAttackBonus(Player currentPlayer, Move move) {
		final int attackBonus = move.isAttack() ? 1000 : 0;
		return attackBonus * (currentPlayer.getColor().isWhite() ? 1 : -1);
	}
	
	private Collection<Move> calculateSimpleMoveOrder(final Collection<Move> moves) {
		final List<Move> sortedMoves = new ArrayList<Move>();
		sortedMoves.addAll(moves);
		Collections.sort(sortedMoves, (m1, m2) -> Booleans.compare(m2.isAttack(),  m1.isAttack()));
		return sortedMoves;
	}
	
	public int min(final Board board, final int depth) {
		int retVal = -1;
		if(depth == 0 || GameUtils.	isEndGame(board)) {
			retVal = this.evaluator.evaluate(board, depth);
		} else {
			int lowestSeenValue = Integer.MAX_VALUE;
			for (final Move move : calculateSimpleMoveOrder(board.getCurrentPlayer().getPlayerLegalMoves())) {
				final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
				if(trans.getStatus().isDone()) {
					final int currentValue = max(trans.getBoard(), depth -1);
					if(currentValue <= lowestSeenValue) {
						retVal = lowestSeenValue = currentValue;
					}
				}
			}
		}
		return retVal;
	}
	
	public int max(final Board board, final int depth) {
		int retVal = -1;
		if(depth == 0 || GameUtils.isEndGame(board)) {
			retVal = this.evaluator.evaluate(board, depth);
		} else {
			int highestSeenValue = Integer.MIN_VALUE;
			for (final Move move : calculateSimpleMoveOrder(board.getCurrentPlayer().getPlayerLegalMoves())) {
				final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
				if(trans.getStatus().isDone()) {
					final int currentValue = min(trans.getBoard(), depth -1);
					if(currentValue >= highestSeenValue) {
						retVal = highestSeenValue = currentValue;
					}
				}
			}
			
		}
		return retVal;
	}
}
