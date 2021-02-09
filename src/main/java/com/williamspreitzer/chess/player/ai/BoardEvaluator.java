package com.williamspreitzer.chess.player.ai;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.utils.GameUtils;

@FunctionalInterface
public interface BoardEvaluator {

	abstract int evaluate(Board board, int depth);
	
	static int scorePlayer(final Board board, final Player player, final int depth) {
		int value = pieceValue(player);
		int mobility = mobility(player);
		int check = check(player);
		int checkMate = checkMate(player, depth);
		int castled = castled(player);
		return value + mobility + check + checkMate + castled;
		
		/*
		 * return pieceValue(player) + mobility(player) + check(player) +
		 * checkMate(player, depth) + castled(player);
		 */
	}
	
	static int pieceValue(final Player player) {
		int pieceValueScore = 0;
		for(final Piece piece : player.getActivePieces()) {
			pieceValueScore += piece.getPieceValue();
		}
		return pieceValueScore;
	}
	
	static int mobility(final Player player) {
		return player.getPlayerLegalMoves().size();
	}
	
	static int check(final Player player) {
		return player.getOpponent().isInCheck() ? Integer.valueOf(GameUtils.props.getProperty("bonus.check")) : 0;
	}
	
	static int checkMate(Player player, int depth) {
		return player.getOpponent().isInCheckMate() ? Integer.valueOf(GameUtils.props.getProperty("bonus.mate")) * depthBonus(depth) : 0;
	}
	
	static int depthBonus(int depth) {
		return depth == 0 ? 1 : Integer.valueOf(GameUtils.props.getProperty("bonus.depth")) * depth;
	}
	
	static int castled(Player player) {
		return player.isCastled() ? Integer.valueOf(GameUtils.props.getProperty("bonus.castle")) : 0;
	}
	
	static boolean isGameOver(final Board board) {
		return board.getCurrentPlayer().isInStaleMate() || board.getCurrentPlayer().isInCheckMate();
	}
}