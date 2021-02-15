package com.williamspreitzer.chess.player.ai;

import java.util.Observable;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.utils.GameUtils;

public class StockAlphaBeta extends Observable {

	private final BoardEvaluator evaluator;
	private final int searchDepth;
	private long boardsEvaluated;
	private int quiescenceCount;
	private static final int MAX_QUIESCENCE = 5000 * 5;
	
	public StockAlphaBeta(Board board, int searchDepth) {
		this.evaluator = new StandardBoardEvaluator(board, searchDepth).getEvaluator();
		this.searchDepth = searchDepth;
		this.boardsEvaluated = 0;
		this.quiescenceCount = 0;
	}
	
	@Override
	public String toString() {
		return "StockAB";
	}
	
	public long getNumBoardsEvaluated() {
		return this.boardsEvaluated;
	}
	
	public Move execute(final Board board) {
		final long startTime = System.currentTimeMillis();
		final Player currentPlayer = board.getCurrentPlayer();
		Move bestMove = MoveFactory.getNullMove();
		int highestSeenValue = Integer.MIN_VALUE;
		int lowestSeenValue = Integer.MAX_VALUE;
		int currentValue;
		System.out.println(currentPlayer + " THINKING with depth = " + this.searchDepth);
		int moveCounter = 1;
		int numMoves = currentPlayer.getPlayerLegalMoves().size();
		for (final Move move : MoveSorter.EXPENSIVE.sort((currentPlayer.getPlayerLegalMoves()))) {
			final MoveTransition trans = currentPlayer.makeMove(move);
			this.quiescenceCount = 0;
			final String s;
			StringBuffer sb = new StringBuffer();
			if(trans.getStatus().isDone()) {
				final long candidateMoveStartTime = System.nanoTime();
				currentValue = currentPlayer.getColor().isWhite() ? 
						min(trans.getBoard(), this.searchDepth - 1, highestSeenValue, lowestSeenValue) :
						max(trans.getBoard(), this.searchDepth - 1, highestSeenValue, lowestSeenValue);
				if(currentPlayer.getColor().isWhite() && currentValue > highestSeenValue) {
					highestSeenValue = currentValue;
					bestMove = move;
					if(trans.getBoard().getBlackPlayer().isInCheckMate()) {
						break;
					}
				} else if(currentPlayer.getColor().isBlack() && currentValue < lowestSeenValue) {
					lowestSeenValue = currentValue;
					bestMove = move;
					if(trans.getBoard().getWhitePlayer().isInCheckMate()) {
						break;
					}
				}
				sb.append(" ");
				sb.append(score(currentPlayer, highestSeenValue, lowestSeenValue));
				sb.append(" q: ");
				sb.append(this.quiescenceCount);
				String quiescenceInfo = sb.toString();
				sb.setLength(0);
				sb.append("\t");
				sb.append(toString());
				sb.append("(");
				sb.append(this.searchDepth);
				sb.append("), m: (" );
				sb.append(moveCounter);
				sb.append("/");
				sb.append(numMoves);
				sb.append(") ");
				sb.append(move);
				sb.append(", best: ");
				sb.append(bestMove);
				sb.append(quiescenceInfo);
				sb.append(", t: ");
				sb.append(calculateTimeTaken(candidateMoveStartTime, System.nanoTime()));
				
			} else {
				sb.append("\t");
				sb.append(toString());
				sb.append("(");
				sb.append(this.searchDepth);
				sb.append("), m: (");
				sb.append(moveCounter);
				sb.append("/");
				sb.append(numMoves);
				sb.append(") ");
				sb.append(move);
				sb.append(" is illegal! best: " + bestMove);
			}
			System.out.println(sb.toString());
			setChanged();
			notifyObservers(sb.toString());
			moveCounter++;
		}
		
		final long executionTime = System.currentTimeMillis() - startTime;
		StringBuffer sb = new StringBuffer();
		sb.append(currentPlayer);
		sb.append(" SELECTS ");
		sb.append(bestMove);
		sb.append("[#boards evaluated = ");
		sb.append(this.boardsEvaluated);
		sb.append(" time take = ");
		sb.append(executionTime / 1000);
		sb.append(" rate = ");
		sb.append(1000 * ((double) this.boardsEvaluated /executionTime));
		System.out.printf("%s SELECTS %s [#boards evaluated = %d, time taken = %d ms, rate = %.1f\n", currentPlayer,
				bestMove, this.boardsEvaluated, executionTime, (1000 * ((double) this.boardsEvaluated/executionTime)));
		setChanged();
		notifyObservers(sb.toString());
		return bestMove;
		
	}
	
	private static String score(final Player currentPlayer, final int highestSeenValue, int lowestSeenValue) {
		StringBuffer sb = new StringBuffer();
		if(currentPlayer.getColor().isWhite()) {
			sb.append("[score: ");
			sb.append(highestSeenValue);
			sb.append("]");
		} else {
			sb.append("[score: ");
			sb.append(lowestSeenValue);
			sb.append("]");
		}
		return sb.toString();
	}
	
	private int max(final Board board, final int depth, final int highestSeenValue, final int lowestSeenValue) {
		int retVal = -1;
		if(depth == 0 || GameUtils.isEndGame(board)) {
			this.boardsEvaluated++;
			retVal = this.evaluator.evaluate(board, depth);
		}
		int currentHighest = highestSeenValue;
		for(final Move move : MoveSorter.STANDARD.sort((board.getCurrentPlayer().getPlayerLegalMoves()))) {
			final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
			if(trans.getStatus().isDone()) {
				currentHighest = Math.max(currentHighest, min(trans.getBoard(), calculateQuiescenceDepth(trans.getBoard(),depth), currentHighest, lowestSeenValue));
				if(currentHighest >= lowestSeenValue) {
					retVal = lowestSeenValue;
				}
			}
		}
		return retVal;
	}
	
	private int min(final Board board, final int depth, final int highestSeenValue, final int lowestSeenValue) {
		int retVal = -1;
		if(depth == 0 || GameUtils.isEndGame(board)) {
			this.boardsEvaluated++;
			retVal = this.evaluator.evaluate(board, depth);
		}
		int currentLowest = lowestSeenValue;
		for(final Move move : MoveSorter.STANDARD.sort((board.getCurrentPlayer().getPlayerLegalMoves()))) {
			final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
			if(trans.getStatus().isDone()) {
				currentLowest = Math.min(currentLowest, max(trans.getBoard(), calculateQuiescenceDepth(trans.getBoard(), depth), highestSeenValue, currentLowest));
				if(currentLowest <= highestSeenValue) {
					retVal = highestSeenValue;
				}
			}
		}
		return currentLowest;
	}
	
	private int calculateQuiescenceDepth(Board board, final int depth) {
		int retVal = -1;
		if(depth == 1 && this.quiescenceCount < MAX_QUIESCENCE) {
			int activityMeasure = 0;
			if(board.getCurrentPlayer().isInCheck()) {
				activityMeasure += 1;
			}
			for(final Move move : GameUtils.lastNMoves(board, 2)) {
				if(move.isAttack()) {
					activityMeasure += 1;
				}
				if(activityMeasure >= 2) {
					this.quiescenceCount++;
					retVal = 2;
				}
			}
		}
		return retVal;
	}
	
	private static String calculateTimeTaken(final long start, final long end) {
		return (end - start) / 1000000 + " ms";
	}
}
