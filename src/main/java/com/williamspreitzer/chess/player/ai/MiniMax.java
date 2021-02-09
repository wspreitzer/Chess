package com.williamspreitzer.chess.player.ai;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveTransition;

public class MiniMax {

	private final BoardEvaluator evaluator;
	private final int depth;
	
	public MiniMax(StandardBoardEvaluator evaluator, final int depth) {
		this.evaluator = evaluator.getEvaluator();
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "MiniMax";
	}

	public Move execute(Board board) {
		MoveStrategy strategy = (b) -> {
			final long startTime = System.currentTimeMillis();
			Move bestMove = null;
			int highestSeenValue = Integer.MIN_VALUE;
			int lowestSeenValue = Integer.MAX_VALUE;
			int currentValue;
			int numMoves = board.getCurrentPlayer().getPlayerLegalMoves().size();
			int counter = 0;
			System.out.println(board.getCurrentPlayer() + " thinking with depth of " + depth + " and " + numMoves + " moves");
			for (final Move move : board.getCurrentPlayer().getPlayerLegalMoves()) {
				if(counter == 5) {
					System.out.println("this is the checkmate move");
				}
				System.out.println("Thinking about " + move);
				final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
				if (trans.getStatus().isDone()) {
					currentValue = board.getCurrentPlayer().getColor().isWhite() ? min(trans.getBoard(), depth - 1)
							: max(trans.getBoard(), depth - 1);
					if (board.getCurrentPlayer().getColor().isWhite() && currentValue >= highestSeenValue) {
						highestSeenValue = currentValue;
						bestMove = move;
					} else if (board.getCurrentPlayer().getColor().isBlack() && currentValue <= lowestSeenValue) {
						
						lowestSeenValue = currentValue;
						System.out.println("this is currentValue: " + currentValue);
						bestMove = move;
					}
				}
				counter++;
			}
			final long executionTime = System.currentTimeMillis() - startTime;
			System.out.println(executionTime);
			return bestMove;
		};
		return strategy.execute(board);
	}

	public int min(final Board board, final int depth) {
		int retVal;
		if (depth == 0 || BoardEvaluator.isGameOver(board)) {
			retVal = this.evaluator.evaluate(board, depth);
		} else {
			int lowestSeenValue = Integer.MAX_VALUE;
			for (final Move move : board.getCurrentPlayer().getPlayerLegalMoves()) {
				final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
				if (trans.getStatus().isDone()) {
					final int currentValue = max(trans.getBoard(), depth - 1);
					if (currentValue <= lowestSeenValue) {
						lowestSeenValue = currentValue;
					}
				}
			}
			retVal = lowestSeenValue;
		}
		return retVal;
	}

	public int max(final Board board, final int depth) {
		int retVal;
		if (depth == 0 || BoardEvaluator.isGameOver(board)) {
			retVal = this.evaluator.evaluate(board, depth);
		} else {
			int highestSeenValue = Integer.MIN_VALUE;
			for (final Move move : board.getCurrentPlayer().getPlayerLegalMoves()) {
				final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
				if (trans.getStatus().isDone()) {
					final int currentValue = min(trans.getBoard(), depth - 1);
					if (currentValue >= highestSeenValue) {
						highestSeenValue = currentValue;
					}
				}
			}
			retVal = highestSeenValue;
		}
		return retVal;
	}
}
