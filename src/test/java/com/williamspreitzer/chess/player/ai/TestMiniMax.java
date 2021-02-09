package com.williamspreitzer.chess.player.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.AttackMove;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestMiniMax {

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
}