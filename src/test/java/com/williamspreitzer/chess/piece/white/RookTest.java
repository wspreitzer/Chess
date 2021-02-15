package com.williamspreitzer.chess.piece.white;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveStatus;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.piece.Rook;
import com.williamspreitzer.chess.utils.GameUtils;

public class RookTest {
	
	protected Builder builder;
	protected King blackKing;
	protected King whiteKing;
	protected Rook rook;
	protected Board board;
	
	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e8"), Color.BLACK, false, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e1"), Color.WHITE, false, false);
		rook = (Rook) PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("d4"), Color.WHITE, false, null);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setPiece(rook);
		builder.setMoveMaker(Color.WHITE);
		board = builder.build();
	}

	@Test
	public void allLegalMovesTest() {
		Assertions.assertEquals(5, board.getBlackPlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(19, board.getWhitePlayer().getPlayerLegalMoves().size());
	}
	
	@Test
	public void moveRookBackwardTest() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(rook.getPosition(), rook.getPosition() + 24));
	}
	
	@Test 
	public void moveRookForwardwardTest() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(rook.getPosition(), rook.getPosition() - 24));
	}
	
	
	@Test
	public void moveRookRightTest() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(rook.getPosition(), rook.getPosition() + 4));
	}
	
	/*
	 * @Test public void moveRookLeftTest() {
	 * Assertions.assertEquals(MoveStatus.DONE, doMove(rook.getPosition(),
	 * rook.getPosition() - 3)); }
	 */

	@Test
	public void testTopMiddleRookWithPieceInWay() {
		rook = (Rook) PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("d8"), Color.WHITE, false, null);
		builder.setPiece(rook);
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("d4"), Color.WHITE, false, null));
		board = builder.build();
		Assertions.assertNull(MoveFactory.getMove(board, GameUtils.getCoordinateAtPosition("d8"), GameUtils.getCoordinateAtPosition("d3")).getBoard());
	}
	private MoveStatus doMove(int position, int destination) {
		final Move move = MoveFactory.getMove(board, position, destination);
		return board.getCurrentPlayer().makeMove(move).getStatus();
	}
}
