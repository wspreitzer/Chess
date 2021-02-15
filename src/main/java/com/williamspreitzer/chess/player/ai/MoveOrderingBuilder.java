package com.williamspreitzer.chess.player.ai;

import java.util.ArrayList;
import java.util.List;

import com.williamspreitzer.chess.moves.Move;

public class MoveOrderingBuilder {

	List<MoveScoreRecord> moveScoreRecords;
	Ordering ordering;
	
	MoveOrderingBuilder() {
		this.moveScoreRecords = new ArrayList<MoveScoreRecord>();
	}
	
	void addMoveOrderingRecord(final Move move, final int score) {
		this.moveScoreRecords.add(new MoveScoreRecord(move, score));
	}
	
	void setOrder(final Ordering order) {
		this.ordering = order;
	}
	
	List<MoveScoreRecord> build() {
		return this.ordering.order(moveScoreRecords);
	}
}