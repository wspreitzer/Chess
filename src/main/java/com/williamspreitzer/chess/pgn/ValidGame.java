package com.williamspreitzer.chess.pgn;

import java.util.List;

public class ValidGame extends Game {
	
	public ValidGame(final PGNGameTags tags,
			         final List<String> moves,
			         final String outcome) {
		super(tags, moves, outcome);
	}
	
	@Override
	public boolean isValid() {
		return true;
	}
}