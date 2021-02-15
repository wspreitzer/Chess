package com.williamspreitzer.chess.player.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.primitives.Ints;

public enum Ordering {
	ASC {
		@Override
		List<MoveScoreRecord> order(final List<MoveScoreRecord> moveScoreRecords) {
			Collections.sort(moveScoreRecords, new Comparator<MoveScoreRecord>() {
				@Override
				public int compare(final MoveScoreRecord o1, MoveScoreRecord o2) {
					return Ints.compare(o2.getScore(), o1.getScore());
				}
			});
			return moveScoreRecords;
		}
	},
	DESC {
		@Override
		List<MoveScoreRecord> order(final List<MoveScoreRecord> moveScoreRecords) {
			Collections.sort(moveScoreRecords, new Comparator<MoveScoreRecord> () {
				@Override
				public int compare(final MoveScoreRecord o1, MoveScoreRecord o2) {
					return Ints.compare(o2.getScore(), o1.getScore());
				}
			});
			return moveScoreRecords;
		}
	};

	abstract List<MoveScoreRecord> order(final List<MoveScoreRecord> moveScoreRecords);
}
