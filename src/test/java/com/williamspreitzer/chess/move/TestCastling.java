package com.williamspreitzer.chess.move;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

//import com.chess.engine.classic.player.ai.StockAlphaBeta;
//import com.chess.pgn.FenUtilities;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.CastleMove;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.pgn.FenUtilities;
import com.williamspreitzer.chess.piece.Rook;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestCastling {

	@Test
	public void testWhiteKingSideCastle() {
		final Board board = FenUtilities.createGameFromFEN("4k3/8/8/8/8/8/8/4K2R w K - 1 0");
		final Move move = MoveFactory.createCastleMove(MoveType.KING_SIDE_CASTLE_MOVE, board,
				board.getTile(GameUtils.getCoordinateAtPosition("e1")).getPiece(),
				GameUtils.getCoordinateAtPosition("g1"),
				(Rook) board.getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(),
				GameUtils.getCoordinateAtPosition("f1"));
		long count = board.getWhitePlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move))
				.count();
		assertEquals(1, count);

		final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
		assertTrue(trans.getStatus().isDone());
		assertTrue(trans.getBoard().getWhitePlayer().isCastled());
		assertFalse(trans.getBoard().getWhitePlayer().isKingSideCastleCapable());
		assertFalse(trans.getBoard().getWhitePlayer().isQueenSideCastleCapable());
	}

	@Test
	public void testWhiteQueenSideCastle() {
		final Board board = FenUtilities.createGameFromFEN("4k3/8/8/8/8/8/8/R3K3 w Q - 1 0");
		final Move move = MoveFactory.createCastleMove(MoveType.QUEEN_SIDE_CASTLE_MOVE, board,
				board.getTile(GameUtils.getCoordinateAtPosition("e1")).getPiece(),
				GameUtils.getCoordinateAtPosition("c1"),
				(Rook) board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(),
				GameUtils.getCoordinateAtPosition("d1"));
		long count = board.getWhitePlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move))
				.count();
		assertEquals(1, count);

		// assertEquals(expected, actual);

		final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
		assertTrue(trans.getStatus().isDone());
		assertTrue(trans.getBoard().getWhitePlayer().isCastled());
		assertFalse(trans.getBoard().getWhitePlayer().isKingSideCastleCapable());
		assertFalse(trans.getBoard().getWhitePlayer().isQueenSideCastleCapable());
	}

	@Test
	public void testBlackKingSideCastle() {
		final Board board = FenUtilities.createGameFromFEN("4k2r/8/8/8/8/8/8/4K3 b k - 1 0");

		final Move move = MoveFactory.createCastleMove(MoveType.KING_SIDE_CASTLE_MOVE, board,
				board.getTile(GameUtils.getCoordinateAtPosition("e8")).getPiece(),
				GameUtils.getCoordinateAtPosition("g8"),
				(Rook) board.getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece(),
				GameUtils.getCoordinateAtPosition("f8"));
		long count = board.getBlackPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		assertEquals(1, count);

		final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
		assertTrue(trans.getStatus().isDone());
		assertTrue(trans.getBoard().getBlackPlayer().isCastled());
		assertFalse(trans.getBoard().getBlackPlayer().isKingSideCastleCapable());
		assertFalse(trans.getBoard().getBlackPlayer().isQueenSideCastleCapable());
	}

	@Test
	public void testBlackQueenSideCastle() {
		final Board board = FenUtilities.createGameFromFEN("r3k3/8/8/8/8/8/8/4K3 -b q - 1 0");
		final Move move = MoveFactory.createCastleMove(MoveType.QUEEN_SIDE_CASTLE_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e8")).getPiece(), GameUtils.getCoordinateAtPosition("c8") , (Rook) board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("d8"));
		long count = board.getBlackPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		assertEquals(1, count);
		
		final MoveTransition trans = board.getCurrentPlayer().makeMove(move);
		assertTrue(trans.getStatus().isDone());
		assertTrue(trans.getBoard().getBlackPlayer().isCastled());
		assertFalse(trans.getBoard().getBlackPlayer().isKingSideCastleCapable());
		assertFalse(trans.getBoard().getBlackPlayer().isQueenSideCastleCapable());
	}

	/*
	 * @Test public void testCastleBugOne() { final Board board =
	 * Board.createStandardBoard(); final MoveTransition t1 =
	 * board.getCurrentPlayer() .makeMove(MoveFactory.createNonAttackingMove(board,
	 * GameUtils.getCoordinateAtPosition("e2"),
	 * GameUtils.getCoordinateAtPosition("e4")));
	 * assertTrue(t1.getStatus().isDone()); final MoveTransition t2 = t1.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t1.getBoard(),
	 * GameUtils.getCoordinateAtPosition("d7"),
	 * GameUtils.getCoordinateAtPosition("d5")));
	 * assertTrue(t2.getStatus().isDone()); final MoveTransition t3 = t2.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t2.getBoard(),
	 * GameUtils.getCoordinateAtPosition("e4"),
	 * GameUtils.getCoordinateAtPosition("e5")));
	 * assertTrue(t3.getStatus().isDone()); final MoveTransition t4 = t3.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t3.getBoard(),
	 * GameUtils.getCoordinateAtPosition("c8"),
	 * GameUtils.getCoordinateAtPosition("f5")));
	 * assertTrue(t4.getStatus().isDone()); final MoveTransition t5 = t4.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t4.getBoard(),
	 * GameUtils.getCoordinateAtPosition("f1"),
	 * GameUtils.getCoordinateAtPosition("d3")));
	 * assertTrue(t5.getStatus().isDone()); final MoveTransition t6 = t5.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t5.getBoard(),
	 * GameUtils.getCoordinateAtPosition("f5"),
	 * GameUtils.getCoordinateAtPosition("d3")));
	 * assertTrue(t6.getStatus().isDone()); final MoveTransition t7 = t6.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t6.getBoard(),
	 * GameUtils.getCoordinateAtPosition("c2"),
	 * GameUtils.getCoordinateAtPosition("d3")));
	 * assertTrue(t7.getStatus().isDone()); final MoveTransition t8 = t7.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t7.getBoard(),
	 * GameUtils.getCoordinateAtPosition("e7"),
	 * GameUtils.getCoordinateAtPosition("e6")));
	 * assertTrue(t8.getStatus().isDone()); final MoveTransition t9 = t8.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t8.getBoard(),
	 * GameUtils.getCoordinateAtPosition("d1"),
	 * GameUtils.getCoordinateAtPosition("a4")));
	 * assertTrue(t9.getStatus().isDone()); final MoveTransition t10 = t9.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t9.getBoard(),
	 * GameUtils.getCoordinateAtPosition("d8"),
	 * GameUtils.getCoordinateAtPosition("d7")));
	 * assertTrue(t10.getStatus().isDone()); final MoveTransition t11 =
	 * t10.getBoard() .getCurrentPlayer() .makeMove(
	 * MoveFactory.createNonAttackingMove(t10.getBoard(),
	 * GameUtils.getCoordinateAtPosition("b1"),
	 * GameUtils.getCoordinateAtPosition("c3")));
	 * assertTrue(t11.getStatus().isDone());
	 * 
	 * 
	 * final MoveStrategy moveStrategy = new StockAlphaBeta(6);
	 * 
	 * moveStrategy.execute(t11.getBoard());
	 * 
	 * }
	 */
	/*
	 * @Test public void testNoCastlingOutOfCheck() { final Board board =
	 * FenUtilities.
	 * createGameFromFEN("r3k2r/1pN1nppp/p3p3/3p4/8/8/PPPK1PPP/R6R b kq - 1 18");
	 * final Move illegalCastleMove = MoveFactory .createMove(board,
	 * GameUtils.getCoordinateAtPosition("e8"),
	 * GameUtils.getCoordinateAtPosition("c8")); final MoveTransition t1 =
	 * board.getCurrentPlayer() .makeMove(illegalCastleMove);
	 * assertFalse(t1.getStatus().isDone()); }
	 */

}
