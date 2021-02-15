package com.williamspreitzer.chess.player.ai;

import com.williamspreitzer.chess.moves.Move;

public class MoveOrderEntry {

	final Move move;
	final int score;
	
	MoveOrderEntry(final Move move, final int score) {
		this.move = move;
		this.score = score;
	}
	
	final Move getMove() {
		return this.move;
	}
	
	final int getScore() {
		return this.score;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("move = ");
		sb.append(this.move);
		sb.append(" score = ");
		sb.append(this.score);
		return sb.toString();
	}
}
