package com.williamspreitzer.chess.pgn;

import java.util.List;

public abstract class Game implements Playable {

	protected final PGNGameTags tags;
	protected final List<String> moves;
	protected final String winner;
	
	Game(final PGNGameTags tags,
	     final List<String> moves,
	     final String outcome) {
		this.tags = tags;
		this.moves = moves;
		this.winner = calculateWinner(outcome);
	}
	
	@Override
	public String toString() {
		return this.tags.toString();
	}
	
	public List<String> getMoves() {
		return this.moves;
	}
	
	public String getWinner() {
		return this.winner;
	}
	
	private static String calculateWinner(String outcome) {
		String retVal;
		if(outcome.equals("1-0")) {
			retVal = "White";
		} else if(outcome.equals("0-1")) {
			retVal = "Black";
		} else if(outcome.equals("1/2-1/2")){
			retVal = "Tie";
		} else {
			retVal = "None";
		}
		return retVal;
	}
}
