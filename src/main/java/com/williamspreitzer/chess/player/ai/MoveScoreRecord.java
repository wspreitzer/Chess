package com.williamspreitzer.chess.player.ai;

import com.williamspreitzer.chess.moves.Move;

public class MoveScoreRecord implements Comparable<MoveScoreRecord>{

	 final Move move;
     final int score;

     MoveScoreRecord(final Move move, final int score) {
         this.move = move;
         this.score = score;
     }

     Move getMove() {
         return this.move;
     }

     int getScore() {
         return this.score;
     }

     @Override
     public int compareTo(MoveScoreRecord o) {
         return Integer.compare(this.score, o.score);
     }

     @Override
     public String toString() {
         return this.move + " : " +this.score;
     }
}