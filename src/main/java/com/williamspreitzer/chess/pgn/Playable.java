package com.williamspreitzer.chess.pgn;

import java.util.List;

public interface Playable {

	abstract boolean isValid();
	abstract List<String> getMoves();
	abstract String getWinner();
}