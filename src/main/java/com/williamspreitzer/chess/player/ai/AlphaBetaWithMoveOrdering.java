package com.williamspreitzer.chess.player.ai;

import java.util.Observable;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.utils.GameUtils;

public class AlphaBetaWithMoveOrdering extends Observable {

	private final BoardEvaluator evaluator;
	private final int searchDepth;
	private final MoveSorter sorter;
	private final int quiescenceFactor;
	private long boardsEvaluated;
	private long executionTime;
	private int quiescenceCount;
	private int cutOffsProduced;
	
	public AlphaBetaWithMoveOrdering(Board board, final int searchDepth, final int quiescenceFactor) {
		this.evaluator = new StandardBoardEvaluator(board, searchDepth).getEvaluator();
		this.searchDepth = searchDepth;
		this.quiescenceFactor = quiescenceFactor;
		this.sorter = MoveSorter.SORT;
		this.boardsEvaluated = 0;
		this.quiescenceCount = 0;
		this.cutOffsProduced = 0;
	}
	
	
	public long getNumBoardsEvaluated() {
		return this.boardsEvaluated;
	}
	
	public Move execute(final Board board) {
		final long startTime = System.currentTimeMillis();
		int highestSeenValue = Integer.MIN_VALUE;
		int lowestSeenValue = Integer.MAX_VALUE;
		int currentValue;
		int moveCounter = 1;
		Move bestMove = null;
		Color color = board.getCurrentPlayer().getColor();
		final int moves = this.sorter.sort(board.getCurrentPlayer().getPlayerLegalMoves()).size();
		System.out.println(board.getCurrentPlayer() +  " THINKING with depth = " + this.searchDepth);
		System.out.println("\tOrdered moves! : " + this.sorter.sort(board.getCurrentPlayer().getPlayerLegalMoves()));
		for(final Move move : this.sorter.sort(board.getCurrentPlayer().getPlayerLegalMoves())) {
			final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
			this.quiescenceCount = 1;
			StringBuffer sb = new StringBuffer();
			if(trans.getStatus().isDone()) {
				final long candidateMoveStartTime = System.nanoTime();
				currentValue = color.isWhite() ? 
						min(trans.getBoard(), this.searchDepth -1, highestSeenValue, lowestSeenValue) :
						max(trans.getBoard(), this.searchDepth -1, highestSeenValue, lowestSeenValue);
				if(color.isWhite() && currentValue > highestSeenValue) {
					highestSeenValue = currentValue;
					bestMove = move;
				} else if(color.isBlack() && currentValue < lowestSeenValue) {
					lowestSeenValue = currentValue;
					bestMove = move;
				}
				sb.append("[h: ");
				sb.append(highestSeenValue);
				sb.append(" 1: ");
				sb.append(lowestSeenValue);
				sb.append("] q: ");
				sb.append(this.quiescenceCount);
				final String quiescenceInfo = sb.toString();
				sb.setLength(0);
				sb.append("\t");
				sb.append(toString());
				sb.append("(");
				sb.append(this.searchDepth);
				sb.append("), m: (");
				sb.append(moveCounter);
				sb.append("/");
				sb.append(moves);
				sb.append(") ");
				sb.append(move);
				sb.append(", best: ");
				sb.append(bestMove);
				sb.append(quiescenceInfo);
				sb.append(" , t: ");
				sb.append(calculateTimeTaken(candidateMoveStartTime, System.nanoTime()));
			} else {
				sb.append("\t");
				sb.append(toString());
				sb.append(", m: (");
				sb.append(moveCounter);
				sb.append("/");
				sb.append(moves);
				sb.append(") ");
				sb.append(move);
				sb.append(" is illegal, best: ");
				sb.append(bestMove);
			}
			
			System.out.println(sb.toString());
			setChanged();
			//notifyObervers(s);
			moveCounter++;
		}
		
		this.executionTime = System.currentTimeMillis() - startTime;
		System.out.printf("%s SELECTS %s [#boards evaluated = %d, time taken = %d ms, eval rate = %.1f cutoffCount = %d prune percent = %.2f\n", board.getCurrentPlayer(),
				bestMove, this.boardsEvaluated, this.executionTime, (1000 * ((double) this.boardsEvaluated/this.executionTime)), this.cutOffsProduced, 100 * ((double)this.cutOffsProduced/this.boardsEvaluated));
		return bestMove;
	}
	
	public int max(final Board board, final int depth, final int highest, final int lowest) {
		int retVal;
		if(depth == 0 || GameUtils.isEndGame(board)) {
			this.boardsEvaluated++;
			retVal = this.evaluator.evaluate(board, depth);
		} else {
			int currentHighest = highest;
			for(final Move move : this.sorter.sort((board.getCurrentPlayer().getPlayerLegalMoves()))) {
				final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
				if(trans.getStatus().isDone()) {
					currentHighest = Math.max(currentHighest, min(trans.getBoard(), calculateQuiescenceDepth(board, move,depth), currentHighest, lowest));
					if (lowest <= currentHighest) {
						this.cutOffsProduced++;
						break;
					}
				}
			}
			retVal = currentHighest;
		}
		return retVal;
	}
	
	public int min(final Board board, final int depth, final int highest, final int lowest) {
		int retVal;
		if(depth == 0 || GameUtils.isEndGame(board)) {
			this.boardsEvaluated++;
			retVal = this.evaluator.evaluate(board, depth);
		} else {
			int currentLowest = lowest;
			for(final Move move : this.sorter.sort((board.getCurrentPlayer().getPlayerLegalMoves()))) {
				final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
				if(trans.getStatus().isDone()) {
					currentLowest = Math.min(currentLowest, max(trans.getBoard(), calculateQuiescenceDepth(board, move, depth), highest, lowest));
					if(currentLowest <= highest) {
						this.cutOffsProduced++;
						break;
					}
				}
			}
			retVal = currentLowest;
		}
		return retVal;
	}
	
	private String calculateTimeTaken(final long start, final long end) {
		final long timeTaken = (end - start / 1000000);
		return timeTaken + " ms";
	}

	private int calculateQuiescenceDepth(final Board board, final Move move, final int depth) {
		return depth - 1;
	}
	@Override
	public String toString() {
		return "AB+MO";
	}
}
