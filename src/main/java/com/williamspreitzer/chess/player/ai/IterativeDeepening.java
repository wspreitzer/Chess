package com.williamspreitzer.chess.player.ai;

import java.util.List;
import java.util.Observable;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.utils.GameUtils;

public class IterativeDeepening extends Observable {

	private final BoardEvaluator evaluator;
	private final int searchDepth;
	private final MoveSorter moveSorter;
	private long  boardsEvaluated;
	private long executionTime;
	private int cutOffsProduced;
	
	public IterativeDeepening(final Board board, final int searchDepth) {
		this.evaluator = new StandardBoardEvaluator(board, searchDepth).getEvaluator();
		this.searchDepth = searchDepth;
		this.moveSorter = MoveSorter.SORT;
		this.boardsEvaluated = 0;
		this.cutOffsProduced = 0;
	}
	
	@Override
	public String toString() {
		return "ID";
	}
	
	public long getNumBoardsEvaluated() {
		return this.boardsEvaluated;
	}
	
	public Move execute(final Board board) {
		final long startTime = System.currentTimeMillis();
		System.out.println(board.getCurrentPlayer() + " THINKING WITH depth = " + this.searchDepth);
		MoveOrderingBuilder builder = new MoveOrderingBuilder(); 
		for(final Move move : board.getCurrentPlayer().getPlayerLegalMoves()) {
			builder.addMoveOrderingRecord(move, 0);
		}
		
		Move bestMove = MoveFactory.getNullMove();
		int currentDepth = 1;
		
		int highestSeenValue = Integer.MIN_VALUE;
		int lowestSeenValue = Integer.MAX_VALUE;
		
		while (currentDepth <= this.searchDepth) {
			final long subTimeStart = System.currentTimeMillis();
			int currentValue;
			final List<MoveScoreRecord> records = builder.build();
			builder = new MoveOrderingBuilder();
			builder.setOrder(board.getCurrentPlayer().getColor().isWhite() ? Ordering.DESC : Ordering.ASC);
			for(final MoveScoreRecord record : records) {
				final MoveTransition trans = board.getCurrentPlayer().makeMove(record.getMove());
				if(trans.getStatus().isDone()) {
					currentValue = board.getCurrentPlayer().getColor().isWhite() ? 
							min(trans.getBoard(), currentDepth - 1, highestSeenValue, lowestSeenValue) :
							max(trans.getBoard(), currentDepth - 1, highestSeenValue, lowestSeenValue);
					builder.addMoveOrderingRecord(record.getMove(),  currentValue);
					if(board.getCurrentPlayer().getColor().isWhite() && currentValue > highestSeenValue) {
						highestSeenValue = currentValue;
						bestMove = record.getMove(); 
					} else if(board.getCurrentPlayer().getColor().isBlack() && currentValue < lowestSeenValue) {
						lowestSeenValue = currentValue;
						bestMove = record.getMove();
					}
				}
			}
			final long subtime = System.currentTimeMillis() - subTimeStart;
			StringBuffer sb = new StringBuffer();
			sb.append("\t");
			sb.append(toString());
			sb.append(" bestMove = ");
			sb.append(bestMove);
			sb.append(" Depth = ");
			sb.append(currentDepth);
			sb.append(" took ");
			sb.append(subtime);
			sb.append(" ms, ordered moves : ");
			sb.append(records);
			setChanged();
			notifyObservers(bestMove);
			currentDepth++;
		}
		this.executionTime = System.currentTimeMillis() - startTime;
		System.out.printf("%s SELECTS %s [#boards evaluated = %d, time taken = %d ms, eval rate = %.1f cutoffCount = %d, prune percent = %.2f\n", board.getCurrentPlayer(),bestMove, this.boardsEvaluated, this.executionTime, (1000 * ((double) this.boardsEvaluated / this.executionTime)), this.cutOffsProduced, 100 * ((double)this.cutOffsProduced/this.boardsEvaluated));
		return bestMove;
	}
	
	public int max(final Board board, final int depth, final int highest, final int lowest) {
		int retVal = -1;
		if(depth == 0 || GameUtils.isEndGame(board)) {
			this.boardsEvaluated++;
			retVal = this.evaluator.evaluate(board, depth);
		}
		int currentHighest = highest;
		for (final Move move : this.moveSorter.sort((board.getCurrentPlayer().getPlayerLegalMoves()))) {
			final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
			if(trans.getStatus().isDone()) {
				currentHighest = Math.max(currentHighest, min(trans.getBoard(), depth -1, currentHighest, lowest));
				if(lowest <= currentHighest) {
					this.cutOffsProduced++;
					retVal = currentHighest;
					break;
				}
			}
		}
		return retVal;
	}
	
	public int min (final Board board, final int depth, final int highest, final int lowest ) {
		int retVal = - 1;
		if(depth == 0 || GameUtils.isEndGame(board)) {
			this.boardsEvaluated++;
			retVal = this.evaluator.evaluate(board, depth);
		}
		int currentLowest = lowest;
		for (final Move move : this.moveSorter.sort((board.getCurrentPlayer().getPlayerLegalMoves()))) {
			final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
			if (trans.getStatus().isDone()) {
				currentLowest = Math.min(currentLowest, max(trans.getBoard(), depth -1, highest, currentLowest));
				if (currentLowest <= highest) {
					retVal = currentLowest;
					this.cutOffsProduced++;
					break;
				}
			}
		}
		return retVal;
	}
}