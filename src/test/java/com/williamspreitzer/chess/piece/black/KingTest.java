package com.williamspreitzer.chess.piece.black;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveStatus;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.piece.Queen;

public class KingTest {

	protected King blackKing;
	protected King whiteKing;
	protected Builder builder;
	
	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("d4"), Color.BLACK, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e1"), Color.WHITE, false);
		builder = new Builder();
		builder.setMoveMaker(Color.BLACK);
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
	}
	
	@Test
	public void allLegalMovesTest() {
		final Board board = builder.build();
		assertEquals(8, board.getBlackPlayer().getPlayerLegalMoves().size());
		assertEquals(5, board.getWhitePlayer().getPlayerLegalMoves().size());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Forward", "Backward"})
	public void moveKingTest(String direction) {
		
		int[] offsets = { 1, 7, 8, 9 };
		if(direction.equals("Forward")) {
			for( int offSet : offsets ) {
				assertEquals(MoveStatus.DONE, this.doMove(builder, blackKing, offSet));
			}
		} else {
			for(int offset : offsets) {
				assertEquals(MoveStatus.DONE, this.doMove(builder, blackKing, offset * Color.WHITE.getDirection()));
			}
		}
	}
	
	@Test
	public void leavesPlayerInCheck() {
		Queen queen = (Queen) PieceFactory.createPiece(PieceType.QUEEN, GameUtils.getCoordinateAtPosition("c3"), Color.WHITE, false);
		builder.setPiece(queen);
		assertEquals(MoveStatus.LEAVES_PLAYER_IN_CHECK, this.doMove(builder, blackKing, 8));
	}
	
	private MoveStatus doMove(Builder builder, King king, int offSet) {
		Board board = builder.build();
		final Move move = MoveFactory.getMove(board, king.getPosition(), king.getPosition() + offSet);
		final MoveTransition transition = board.getCurrentPlayer().makeMove(move);
		return transition.getStatus();
	}
}