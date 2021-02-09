package com.williamspreitzer.chess.piece.black;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.williamspreitzer.chess.piece.Queen;
import com.williamspreitzer.chess.player.ai.KingSafetyAnalyzer;
import com.williamspreitzer.chess.utils.GameUtils;

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
	
	@Test
	public void moveKingTest() {
		String[] directions = new String[] {"Forward", "Revers"};
		int[] offsets = { 1, 7, 8, 9 };
		for (String direction : directions) {
			if(direction.equals("Forward")) {
				for(int offset : offsets) {
					assertEquals(MoveStatus.DONE, this.doMove(builder, blackKing, offset * Color.BLACK.getDirection()));
				}
			} else {
				for(int offset : offsets) {
					assertEquals(MoveStatus.DONE, this.doMove(builder, blackKing, offset * Color.BLACK.getOppositeDirection()));
				}
			}
		}
		for( int offSet : offsets ) {
		}
	}
	
	@Test
	public void leavesPlayerInCheck() {
		Queen queen = (Queen) PieceFactory.createPiece(PieceType.QUEEN, GameUtils.getCoordinateAtPosition("c3"), Color.WHITE, false);
		builder.setPiece(queen);
		assertEquals(MoveStatus.LEAVES_PLAYER_IN_CHECK, this.doMove(builder, blackKing, 8));
	}
	
	@Test
	public void testKingSafteyAnalyzer() {
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 12, Color.BLACK, false));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 52, Color.WHITE, false));
		final Board board = builder.build();
		assertEquals(10, KingSafetyAnalyzer.getKingSafetyAnalyzer().calculateKingTropism(board.getBlackPlayer()).tropismScore());
	}
	
	private MoveStatus doMove(Builder builder, King king, int offSet) {
		Board board = builder.build();
		final Move move = MoveFactory.getMove(board, king.getPosition(), king.getPosition() + offSet);
		final MoveTransition transition = board.getCurrentPlayer().makeMove(move);
		return transition.getStatus();
	}
}