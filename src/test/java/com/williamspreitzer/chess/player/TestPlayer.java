package com.williamspreitzer.chess.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestPlayer {

	Board board;

	@BeforeEach()
	private void setup() {
		board = Board.createStandardBoard();
	}

	@Test
	public void testSimpleEvaluation() {
		final Board board = Board.createStandardBoard();
		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(),
						GameUtils.getCoordinateAtPosition("e4")));
		assertTrue(t1.getStatus().isDone());

		final MoveTransition t2 = t1.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(),
						t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(),
						GameUtils.getCoordinateAtPosition("e5")));
		assertTrue(t2.getStatus().isDone());
		//assertEquals(0, StandardBoardEvaluator.getEvaluator(t2.getBoard(), 0).evaluate(t2.getBoard(), 0));
	}

	@Test
	public void testBug() {
		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("c2")).getPiece(),
						GameUtils.getCoordinateAtPosition("c3")));
		assertTrue(t1.getStatus().isDone());

		final MoveTransition t2 = t1.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t1.getBoard(),
						t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("b8")).getPiece(),
						GameUtils.getCoordinateAtPosition("a6")));
		assertTrue(t2.getStatus().isDone());

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(),
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("d1")).getPiece(),
						GameUtils.getCoordinateAtPosition("a4")));
		assertTrue(t3.getStatus().isDone());

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t3.getBoard(),
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece(),
						GameUtils.getCoordinateAtPosition("d6")));
		assertFalse(t4.getStatus().isDone());
	}

	@Test
	public void testDiscoveredCheck() {
		final Builder builder = new Builder();

		// Black Layout
		builder.setPiece(
				PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e8"), Color.BLACK, false));
		builder.setPiece(
				PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("a5"), Color.BLACK, false));

		// White Layout
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, GameUtils.getCoordinateAtPosition("e3"),
				Color.WHITE, false));
		builder.setPiece(
				PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("e2"), Color.WHITE, false));
		builder.setPiece(
				PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("c1"), Color.WHITE, false));

		// Set the current player
		builder.setMoveMaker(Color.WHITE);

		final Board board = builder.build();

		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("e3")).getPiece(),
						GameUtils.getCoordinateAtPosition("b6")));
		assertTrue(t1.getStatus().isDone());
		assertTrue(t1.getBoard().getCurrentPlayer().isInCheck());

		final MoveTransition t2 = t1.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t1.getBoard(),
						t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("a5")).getPiece(),
						GameUtils.getCoordinateAtPosition("b5")));
		assertFalse(t2.getStatus().isDone());

		final MoveTransition t3 = t1.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t1.getBoard(),
						t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("a5")).getPiece(),
						GameUtils.getCoordinateAtPosition("e5")));
		assertTrue(t3.getStatus().isDone());
	}

	/*
	 * @Test public void testUnmakeMove() { final Move m1 =
	 * MoveFactory.createMove(board, GameUtils.getCoordinateAtPosition("e2"),
	 * GameUtils.getCoordinateAtPosition("e4")); final MoveTransition t1 =
	 * board.getCurrentPlayer().makeMove(m1); assertTrue(t1.getStatus().isDone());
	 * t1.getBoard().getCurrentPlayer().getOpponent().unMakeMove(m1); }
	 */

	@Test
	public void testIllegalMove() {
		final Move m1 = MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, board,
				board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(),
				GameUtils.getCoordinateAtPosition("e6"));
		final MoveTransition t1 = board.getCurrentPlayer().makeMove(m1);
		assertFalse(t1.getStatus().isDone());
	}

}
