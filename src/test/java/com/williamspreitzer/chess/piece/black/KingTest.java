package com.williamspreitzer.chess.piece.black;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveStatus;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;

public class KingTest {

	protected King blackKing;
	protected King whiteKing;
	protected Builder builder;
	
	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, 4, Color.BLACK, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, 60, Color.WHITE, false);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
	}
	
	@Test
	void allLegalMovesTest() {
		builder.setMoveMaker(Color.WHITE);
		final Board board = builder.build();
		Assertions.assertEquals(5, board.getWhitePlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(5, board.getBlackPlayer().getPlayerLegalMoves().size());
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 4, 60 })
	void moveKingTest(int position) {
		int[] offSets = { 1, 7, 8, 9 };
		if( position == 4 ) {
			for( int offSet : offSets ) {
				builder.setMoveMaker(Color.BLACK);
				Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, blackKing, offSet));
			}
		} else {
			for(int offSet : offSets ) {
				builder.setMoveMaker(Color.WHITE);
				Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, whiteKing, offSet * -1 ));
			}
		}
	}
	
	private MoveStatus doMove(Builder builder, King king, int offSet) {
		Board board = builder.build();
		final Move move = MoveFactory.createMove(board, king.getPosition(), king.getPosition() + offSet);
		final MoveTransition transition = board.getCurrentPlayer().makeMove(move);
		return transition.getStatus();
	}
}