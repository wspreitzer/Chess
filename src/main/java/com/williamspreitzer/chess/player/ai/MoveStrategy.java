package com.williamspreitzer.chess.player.ai;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;

@FunctionalInterface
public interface MoveStrategy {
	Move execute(Board board);
}