package com.williamspreitzer.chess.player.ai;

import com.williamspreitzer.chess.board.Board;

public class StandardBoardEvaluator {
	private static BoardEvaluator evaluator;
	
	public StandardBoardEvaluator(final Board board, final int depth) {
		evaluator = (b,d) -> {
			int score = BoardEvaluator.scorePlayer(b, b.getWhitePlayer(), d) - BoardEvaluator.scorePlayer(b, b.getBlackPlayer(), d);
			return score;
		};
	}
	
	public BoardEvaluator getEvaluator() {
		return evaluator;
	}
}