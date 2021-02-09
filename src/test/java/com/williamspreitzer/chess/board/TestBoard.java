package com.williamspreitzer.chess.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Iterables;
import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.player.ai.BoardEvaluator;
import com.williamspreitzer.chess.player.ai.StandardBoardEvaluator;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestBoard {

	Board board;
	Builder builder;

	@BeforeEach
	private void setup() {
		this.board = Board.createStandardBoard();
		this.builder = new Builder();
		builder.setPiece(PieceFactory.createPiece(PieceType.KING, 4, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.KING, 60, Color.WHITE, false));
		builder.setMoveMaker(Color.WHITE);
	}

	@Test
	public void initialBoard() {
		assertEquals(board.getCurrentPlayer().getPlayerLegalMoves().size(), 20);
		assertEquals(board.getCurrentPlayer().getOpponent().getPlayerLegalMoves().size(), 20);
		assertFalse(board.getCurrentPlayer().isInCheck());
		assertFalse(board.getCurrentPlayer().isInCheckMate());
		assertFalse(board.getCurrentPlayer().isCastled());
		assertTrue(board.getCurrentPlayer().isKingSideCastleCapable());
		assertTrue(board.getCurrentPlayer().isQueenSideCastleCapable());
		assertEquals(board.getCurrentPlayer(), board.getWhitePlayer());
		assertEquals(board.getCurrentPlayer().getOpponent(), board.getBlackPlayer());
		assertFalse(board.getCurrentPlayer().getOpponent().isInCheck());
		assertFalse(board.getCurrentPlayer().getOpponent().isInCheckMate());
		assertFalse(board.getCurrentPlayer().getOpponent().isCastled());
		assertTrue(board.getCurrentPlayer().getOpponent().isKingSideCastleCapable());
		assertTrue(board.getCurrentPlayer().getOpponent().isQueenSideCastleCapable());
		assertTrue(board.getWhitePlayer().toString().equals("WHITE"));
		assertTrue(board.getBlackPlayer().toString().equals("BLACK"));

		final Iterable<Piece> allPieces = board.getAllPieces();
		final Iterable<Move> allMoves = Iterables.concat(board.getWhitePlayer().getPlayerLegalMoves(),
				board.getBlackPlayer().getPlayerLegalMoves());
		for (final Move move : allMoves) {
			assertFalse(move.isAttack());
			assertFalse(move.isCastle());
			// assertEquals(1, MoveUtils.exchangeScore(move));
		}

		assertEquals(Iterables.size(allMoves), 40);
		assertEquals(Iterables.size(allPieces), 32);
		assertFalse(GameUtils.isEndGame(board));
		assertFalse(GameUtils.isThreatenedBoardImmediate(board));
		assertEquals(0, new StandardBoardEvaluator(board, 0).getEvaluator().evaluate(board, 0));
		assertNull(board.getTile(35).getPiece());
	}

	@Test
	public void testPlainKingMove() {
		board = builder.build();
		System.out.println(board);

		assertEquals(5, board.getWhitePlayer().getPlayerLegalMoves().size());
		assertEquals(5, board.getBlackPlayer().getPlayerLegalMoves().size());
		assertFalse(board.getCurrentPlayer().isInCheck());
		assertFalse(board.getCurrentPlayer().isInCheckMate());
		assertFalse(board.getCurrentPlayer().getOpponent().isInCheck());
		assertFalse(board.getCurrentPlayer().getOpponent().isInCheckMate());
		assertEquals(board.getCurrentPlayer(), board.getWhitePlayer());
		assertEquals(board.getCurrentPlayer().getOpponent(), board.getBlackPlayer());

		StandardBoardEvaluator sbe = new StandardBoardEvaluator(board, 0);
		BoardEvaluator evaluator = sbe.getEvaluator();
		System.out.println(evaluator.evaluate(board, 0));
		assertEquals(0, new StandardBoardEvaluator(board, 0).getEvaluator().evaluate(board, 0));

		final Move move = MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board,
				board.getTile(GameUtils.getCoordinateAtPosition("e1")).getPiece(),
				GameUtils.getCoordinateAtPosition("f1"));

		final MoveTransition moveTransition = board.getCurrentPlayer().makeMove(move);

		assertEquals(moveTransition.getMove(), move);
		assertEquals(board, moveTransition.getFromBoard());
		assertEquals(moveTransition.getBoard().getCurrentPlayer(), moveTransition.getBoard().getBlackPlayer());
		assertTrue(moveTransition.getStatus().isDone());
		assertEquals(61, moveTransition.getBoard().getWhitePlayer().getPlayerKing().getPosition());
		System.out.println(moveTransition.getBoard());

	}

	@Test
	public void testBoardConsistency() {
		
		assertEquals(board.getWhitePlayer(), board.getCurrentPlayer());

		final MoveTransition t1 = board
				.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board,
							board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(),
							GameUtils.getCoordinateAtPosition("e4")));
		assertTrue(t1.getStatus().isDone());
		
		final MoveTransition t2 = t1.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(
							MoveType.PAWN_JUMP_MOVE, t1.getBoard(),
							t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(),
							GameUtils.getCoordinateAtPosition("e5")));
		assertTrue(t2.getStatus().isDone());
		
		final MoveTransition t3 = t2.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(
							MoveType.MAJOR_MOVE, t2.getBoard(),
							t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("g1")).getPiece(),
							GameUtils.getCoordinateAtPosition("f3")));
		assertTrue(t3.getStatus().isDone());
		
		final MoveTransition t4 = t3.getBoard().
				getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(
							MoveType.PAWN_JUMP_MOVE, t3.getBoard(),
							t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece(),
							GameUtils.getCoordinateAtPosition("d5")));
		assertTrue(t4.getStatus().isDone());
		
		final MoveTransition t5 = t4.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(
							MoveType.PAWN_ATTACK_MOVE, t4.getBoard(),
							t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(),
							GameUtils.getCoordinateAtPosition("d5"),
							t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("d5")).getPiece()));
		assertTrue(t5.getStatus().isDone());
		
		final MoveTransition t6 = t5.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(
							MoveType.MAJOR_ATTACK_MOVE, t5.getBoard(),
							t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(),
							GameUtils.getCoordinateAtPosition("d5"),
							t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("d5")).getPiece()));
		assertTrue(t6.getStatus().isDone());
		
		final MoveTransition t7 = t6.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(
							MoveType.MAJOR_ATTACK_MOVE, t6.getBoard(),
							t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("f3")).getPiece(),
							GameUtils.getCoordinateAtPosition("g5"),
							t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("g5")).getPiece()));
		assertTrue(t7.getStatus().isDone());
		
		final MoveTransition t8 = t7.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(
							MoveType.PAWN_MOVE, t7.getBoard(),
							t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("f7")).getPiece(),
							GameUtils.getCoordinateAtPosition("f6")));
		assertTrue(t8.getStatus().isDone());
		
		final MoveTransition t9 = t8.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(
							MoveType.MAJOR_MOVE, t8.getBoard(),
							t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("d1")).getPiece(),
							GameUtils.getCoordinateAtPosition("h5")));
		assertTrue(t9.getStatus().isDone());
		
		final MoveTransition t10 = t9.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(
							MoveType.PAWN_MOVE, t9.getBoard(),
							t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("g7")).getPiece(),
							GameUtils.getCoordinateAtPosition("g6")));
		assertTrue(t10.getStatus().isDone());
		
		final MoveTransition t11 = t10.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(
							MoveType.MAJOR_MOVE, t10.getBoard(),
							t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("h5")).getPiece(),
							GameUtils.getCoordinateAtPosition("h4")));
		assertTrue(t11.getStatus().isDone());
		
		final MoveTransition t12 = t11.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(
							MoveType.PAWN_ATTACK_MOVE, 
							t11.getBoard(),
							t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("f6")).getPiece(),
							GameUtils.getCoordinateAtPosition("g5"), 
							t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("g5")).getPiece()));
		assertTrue(t12.getStatus().isDone());
		
		final MoveTransition t13 = t12.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(
							MoveType.MAJOR_ATTACK_MOVE, 
							t12.getBoard(),
							t12.getBoard().getTile(GameUtils.getCoordinateAtPosition("h4")).getPiece(),
							GameUtils.getCoordinateAtPosition("g5"), 
							t12.getBoard().getTile(GameUtils.getCoordinateAtPosition("g5")).getPiece()));
		assertTrue(t13.getStatus().isDone());
		final MoveTransition t14 = t13.getBoard()
				.getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(
							MoveType.MAJOR_ATTACK_MOVE, 
							t13.getBoard(),
							t13.getBoard().getTile(GameUtils.getCoordinateAtPosition("d5")).getPiece(),
							GameUtils.getCoordinateAtPosition("e4"),
							t13.getBoard().getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece()));
		assertTrue(t14.getStatus().isDone());
		assertTrue(t14.getBoard().getWhitePlayer().getActivePieces().size() == calculatedActivesFor(t14.getBoard(),
				Color.WHITE));
		assertTrue(t14.getBoard().getBlackPlayer().getActivePieces().size() == calculatedActivesFor(t14.getBoard(),
				Color.BLACK));

	}

	@Test
	public void testInvalidBoardMissingBlackKing() {
		builder = new Builder();
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 0, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 1, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 2, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.QUEEN, 3, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 5, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 6, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 7, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 8, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 9, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 10, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 11, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 12, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 13, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 14, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 15, Color.BLACK, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 48, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 49, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 50, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 51, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 52, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 53, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 54, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 55, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 56, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 57, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 58, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.QUEEN, 59, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 61, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 62, Color.WHITE, true));
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 63, Color.WHITE, true));
		assertThrows(RuntimeException.class, () -> {
			builder.build();
		});
	}

	@Test
	public void testAlgebreicNotation() {
		assertEquals(GameUtils.getPositionAtCoordinate(0), "a8");
		assertEquals(GameUtils.getPositionAtCoordinate(1), "b8");
		assertEquals(GameUtils.getPositionAtCoordinate(2), "c8");
		assertEquals(GameUtils.getPositionAtCoordinate(3), "d8");
		assertEquals(GameUtils.getPositionAtCoordinate(4), "e8");
		assertEquals(GameUtils.getPositionAtCoordinate(5), "f8");
		assertEquals(GameUtils.getPositionAtCoordinate(6), "g8");
		assertEquals(GameUtils.getPositionAtCoordinate(7), "h8");
	}

	/*
	 * @Test public void mem() { final Runtime runtime = Runtime.getRuntime(); long
	 * start, end; runtime.gc(); start = runtime.freeMemory(); Board board =
	 * Board.createStandardBoard(); end = runtime.freeMemory();
	 * System.out.println("That took " + (start-end) + " bytes.");
	 * 
	 * }
	 */

	private static int calculatedActivesFor(final Board board, final Color Color) {
		int count = 0;
		for (final Piece p : board.getAllPieces()) {
			if (p.getColor().equals(Color)) {
				count++;
			}
		}
		return count;
	}

}
