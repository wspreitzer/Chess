package com.williamspreitzer.chess.player.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.AttackMove;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.pgn.FenUtilities;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestMiniMax {

	Board board;
	Board testBoard;

	@BeforeEach
	private void setup() {
		board = Board.createStandardBoard();
		testBoard = FenUtilities.createGameFromFEN("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w - 0 1");
	}
	
	@Test
	public void testFoolsMate() {
		final Board board = Board.createStandardBoard();
		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("f2")).getPiece(),
						GameUtils.getCoordinateAtPosition("f3")));
		assertTrue(t1.getStatus().isDone());

		final MoveTransition t2 = t1.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(),
						t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(),
						GameUtils.getCoordinateAtPosition("e5")));
		assertTrue(t2.getStatus().isDone());

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t2.getBoard(),
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece(),
						GameUtils.getCoordinateAtPosition("g4")));
		assertTrue(t3.getStatus().isDone());

		final MiniMax strategy = new MiniMax(new StandardBoardEvaluator(t3.getBoard(), 4), 4);

		final Move aiMove = strategy.execute(t3.getBoard());

		final Move bestMove = MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t3.getBoard(),
				t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(),
				GameUtils.getCoordinateAtPosition("h4"));

		assertEquals(bestMove, aiMove);
	}
	
	@Test
	public void testFoolsMateWhite() {
		final Board board = Board.createStandardBoard();
		final MoveTransition t1 = board.getCurrentPlayer().makeMove(MoveFactory
				.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board, 
						board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(), 
						GameUtils.getCoordinateAtPosition("e4")));
		assertTrue(t1.getStatus().isDone());
		
		final MoveTransition t2 = t1.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(), 
						t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("a7")).getPiece(), GameUtils.getCoordinateAtPosition("a5")));
		assertTrue(t2.getStatus().isDone());
		
		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(), 
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("f1")).getPiece(), GameUtils.getCoordinateAtPosition("c4")));
		assertTrue(t3.getStatus().isDone());
		
		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t3.getBoard(), 
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("a5")).getPiece(), GameUtils.getCoordinateAtPosition("a4")));
		assertTrue(t4.getStatus().isDone());
		
		final MoveTransition t5 = t4.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(), 
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("d1")).getPiece(), GameUtils.getCoordinateAtPosition("h5")));
		assertTrue(t5.getStatus().isDone());
		
		final MoveTransition t6 = t5.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t5.getBoard(),
						t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("a4")).getPiece(), GameUtils.getCoordinateAtPosition("a3")));
		
		final MiniMax strategy = new MiniMax(new StandardBoardEvaluator(t6.getBoard(), 4), 4);
		final Move aiMove = strategy.execute(t6.getBoard());
		final AttackMove bestMove = MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t6.getBoard(), 
				t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("h4")).getPiece(), GameUtils.getCoordinateAtPosition("f7"), t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("f7")).getPiece());
		assertEquals(bestMove, aiMove);
	}
	
	@Test
	public void testOpeningDepth1() {
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 1), 1);
		minMax.execute(board);
		final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
		assertEquals(numBoardsEvaluated, 20L);
	}

	@Test
	public void testOpeningDepth2() {
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 2), 2);
		minMax.execute(board);
		final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
		assertEquals(numBoardsEvaluated, 400L);
	}

	@Test
	public void testOpeningDepth3() {
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 3), 3);
		minMax.execute(board);
		final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
		assertEquals(numBoardsEvaluated, 8902L);
	}

	@Test
	public void testOpeningDepth4() {
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 4), 4);
		minMax.execute(board);
		final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
		assertEquals(numBoardsEvaluated, 197281L);
	}

	@Test
	public void testOpeningDepth5() {
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 5), 5);
		minMax.execute(board);
		final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
		assertEquals(numBoardsEvaluated, 4865609L);
	}

	@Test
	public void testOpeningDepth6() {
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 6), 6);
		minMax.execute(board);
		final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
		assertEquals(numBoardsEvaluated, 119060324L);
	}

	@Test
	public void testKiwiPeteDepth1() {
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 1), 1);
		minMax.execute(testBoard);
		final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
		assertEquals(numBoardsEvaluated, 46);
	}

	@Test
	public void testKiwiPeteDepth2() {
		System.out.println(FenUtilities.createFENFromGame(testBoard));
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 2), 2);
		minMax.execute(testBoard);
		assertEquals(minMax.getNumBoardsEvaluated(), 1866L);
	}

	@Test
	public void engineIntegrity1() {
		testBoard = FenUtilities.createGameFromFEN("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - -\n");
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 6), 6);
		minMax.execute(board);
		assertEquals(minMax.getNumBoardsEvaluated(), 11030083);
	}

	@Test
	public void testKiwiPeteDepth2Bug2() {
		testBoard = FenUtilities
				.createGameFromFEN("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq -");
		final MoveTransition t1 = board.getCurrentPlayer().makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, board,
				board.getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("d7"), board.getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece()));
		
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 1), 1);
		minMax.execute(t1.getBoard());
		assertEquals(minMax.getNumBoardsEvaluated(), 45);
	}

	@Test
	public void testChessDotComGame() {
		testBoard = FenUtilities
				.createGameFromFEN("rnbk1bnr/1pN2ppp/p7/3P2q1/3Pp3/8/PPP1QPPP/RN2KB1R w KQ - 18 10");
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 4), 4);
		minMax.execute(board);
	}

	@Test
	public void testPosition3Depth1() {
		board = FenUtilities.createGameFromFEN("8/2p5/3p4/KP5p/1P3p1k/8/4P1P1/8 w - 0 1");
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 1), 1);
		minMax.execute(board);
		final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
		assertEquals(numBoardsEvaluated, 14);
	}

	@Test
	public void testPosition3Depth2() {
		board = FenUtilities.createGameFromFEN("8/2p5/3p4/KP5p/1P3p1k/8/4P1P1/8 w - 0 1");
		final MiniMax minMax = new MiniMax(new StandardBoardEvaluator(board, 2), 2);
		minMax.execute(board);
		final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
		assertEquals(numBoardsEvaluated, 191);
	}
}