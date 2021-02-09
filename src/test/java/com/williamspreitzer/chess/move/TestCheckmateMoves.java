package com.williamspreitzer.chess.move;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.piece.Bishop;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestCheckmateMoves {
	Board board;
	Builder testBuilder;

	King blackKing;
	King whiteKing;

	@BeforeEach
	private void setup() {
		this.board = Board.createStandardBoard();
		this.testBuilder = new Builder();
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.KING, 4, Color.BLACK, true));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.KING, 60, Color.WHITE, true));
	}

	@Test
	public void testFoolsMate() {
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

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t3.getBoard(),
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(),
						GameUtils.getCoordinateAtPosition("h4")));
		assertTrue(t4.getStatus().isDone());
		assertTrue(t4.getBoard().getCurrentPlayer().isInCheckMate());
	}

	@Test
	public void testScholarsMate() {
		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(),
						GameUtils.getCoordinateAtPosition("e4")));
		assertTrue(t1.getStatus().isDone());

		final MoveTransition t2 = t1.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t1.getBoard(),
						t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("a7")).getPiece(),
						GameUtils.getCoordinateAtPosition("a6")));
		assertTrue(t2.getStatus().isDone());

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(),
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("d1")).getPiece(),
						GameUtils.getCoordinateAtPosition("f3")));
		assertTrue(t3.getStatus().isDone());

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t3.getBoard(),
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("a6")).getPiece(),
						GameUtils.getCoordinateAtPosition("a5")));
		assertTrue(t4.getStatus().isDone());

		final MoveTransition t5 = t4.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(),
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("f1")).getPiece(),
						GameUtils.getCoordinateAtPosition("c4")));
		assertTrue(t5.getStatus().isDone());

		final MoveTransition t6 = t5.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t5.getBoard(),
						t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("a5")).getPiece(),
						GameUtils.getCoordinateAtPosition("a4")));
		assertTrue(t6.getStatus().isDone());

		final MoveTransition t7 = t6.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t6.getBoard(),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("f3")).getPiece(),
						GameUtils.getCoordinateAtPosition("f7"),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("f7")).getPiece()));
		assertTrue(t7.getStatus().isDone());
		assertTrue(t7.getBoard().getCurrentPlayer().isInCheckMate());
	}

	@Test
	public void testLegalsMate() {
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

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(),
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("f1")).getPiece(),
						GameUtils.getCoordinateAtPosition("c4")));
		assertTrue(t3.getStatus().isDone());

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t3.getBoard(),
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece(),
						GameUtils.getCoordinateAtPosition("d6")));
		assertTrue(t4.getStatus().isDone());

		final MoveTransition t5 = t4.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(),
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("g1")).getPiece(),
						GameUtils.getCoordinateAtPosition("f3")));
		assertTrue(t5.getStatus().isDone());

		final MoveTransition t6 = t5.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t5.getBoard(),
						t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("c8")).getPiece(),
						GameUtils.getCoordinateAtPosition("g4")));
		assertTrue(t6.getStatus().isDone());

		final MoveTransition t7 = t6.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t6.getBoard(),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("b1")).getPiece(),
						GameUtils.getCoordinateAtPosition("c3")));
		assertTrue(t7.getStatus().isDone());

		final MoveTransition t8 = t7.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t7.getBoard(),
						t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("g7")).getPiece(),
						GameUtils.getCoordinateAtPosition("g6")));
		assertTrue(t8.getStatus().isDone());

		final MoveTransition t9 = t8.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t8.getBoard(),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("f3")).getPiece(),
						GameUtils.getCoordinateAtPosition("e5"),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece()));
		assertTrue(t9.getStatus().isDone());

		final MoveTransition t10 = t9.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t9.getBoard(),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("g4")).getPiece(),
						GameUtils.getCoordinateAtPosition("d1"),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("d1")).getPiece()));
		assertTrue(t10.getStatus().isDone());

		final MoveTransition t11 = t10.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t10.getBoard(),
						t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("c4")).getPiece(),
						GameUtils.getCoordinateAtPosition("f7"),
						t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("f7")).getPiece()));
		assertTrue(t11.getStatus().isDone());

		final MoveTransition t12 = t11.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t11.getBoard(),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("e8")).getPiece(),
						GameUtils.getCoordinateAtPosition("e7")));
		assertTrue(t12.getStatus().isDone());

		final MoveTransition t13 = t12.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t12.getBoard(),
						t12.getBoard().getTile(GameUtils.getCoordinateAtPosition("c3")).getPiece(),
						GameUtils.getCoordinateAtPosition("d5")));
		assertTrue(t13.getStatus().isDone());
		assertTrue(t13.getBoard().getCurrentPlayer().isInCheckMate());
	}

	@Test
	public void testSevenMoveMate() {
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

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t2.getBoard(),
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("d2")).getPiece(),
						GameUtils.getCoordinateAtPosition("d4")));
		assertTrue(t3.getStatus().isDone());

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t3.getBoard(),
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece(),
						GameUtils.getCoordinateAtPosition("d6")));
		assertTrue(t4.getStatus().isDone());

		final MoveTransition t5 = t4.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, t4.getBoard(),
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(),
						GameUtils.getCoordinateAtPosition("e5"),
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece()));
		assertTrue(t5.getStatus().isDone());

		final MoveTransition t6 = t5.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t5.getBoard(),
						t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(),
						GameUtils.getCoordinateAtPosition("e7")));
		assertTrue(t6.getStatus().isDone());

		final MoveTransition t7 = t6.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, t6.getBoard(),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(),
						GameUtils.getCoordinateAtPosition("d6"),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("d6")).getPiece()));
		assertTrue(t7.getStatus().isDone());

		final MoveTransition t8 = t7.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t7.getBoard(),
						t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(),
						GameUtils.getCoordinateAtPosition("e4"),
						t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece()));
		assertTrue(t8.getStatus().isDone());

		final MoveTransition t9 = t8.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t8.getBoard(),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("f1")).getPiece(),
						GameUtils.getCoordinateAtPosition("e2")));
		assertTrue(t9.getStatus().isDone());

		final MoveTransition t10 = t9.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t9.getBoard(),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(),
						GameUtils.getCoordinateAtPosition("g2"),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("g1")).getPiece()));
		assertTrue(t10.getStatus().isDone());

		final MoveTransition t11 = t10.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, t10.getBoard(),
						t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("d6")).getPiece(),
						GameUtils.getCoordinateAtPosition("c7"),
						t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("c7")).getPiece()));
		assertTrue(t11.getStatus().isDone());

		final MoveTransition t12 = t11.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t11.getBoard(),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece(),
						GameUtils.getCoordinateAtPosition("h1"),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece()));
		assertTrue(t12.getStatus().isDone());

		final MoveTransition t13 = t12.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t12.getBoard(),
						t12.getBoard().getTile(GameUtils.getCoordinateAtPosition("d1")).getPiece(),
						GameUtils.getCoordinateAtPosition("d8")));
		assertTrue(t13.getStatus().isDone());
		assertTrue(t13.getBoard().getCurrentPlayer().isInCheckMate());
	}

	@Test
	public void testGrecoGame() {

		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("d2")).getPiece(),
						GameUtils.getCoordinateAtPosition("d4")));
		assertTrue(t1.getStatus().isDone());

		final MoveTransition t2 = t1.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t1.getBoard(),
						t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("g8")).getPiece(),
						GameUtils.getCoordinateAtPosition("f6")));
		assertTrue(t2.getStatus().isDone());

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(),
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("b1")).getPiece(),
						GameUtils.getCoordinateAtPosition("d2")));
		assertTrue(t3.getStatus().isDone());

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t3.getBoard(),
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(),
						GameUtils.getCoordinateAtPosition("e5")));
		assertTrue(t4.getStatus().isDone());

		final MoveTransition t5 = t4.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, t4.getBoard(),
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(),
						GameUtils.getCoordinateAtPosition("e5"),
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece()));
		assertTrue(t5.getStatus().isDone());

		final MoveTransition t6 = t5.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t5.getBoard(),
						t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("f6")).getPiece(),
						GameUtils.getCoordinateAtPosition("g4")));
		assertTrue(t6.getStatus().isDone());

		final MoveTransition t7 = t6.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t6.getBoard(),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("h2")).getPiece(),
						GameUtils.getCoordinateAtPosition("h3")));
		assertTrue(t7.getStatus().isDone());

		final MoveTransition t8 = t7.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t7.getBoard(),
						t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("g4")).getPiece(),
						GameUtils.getCoordinateAtPosition("e3")));
		assertTrue(t8.getStatus().isDone());

		final MoveTransition t9 = t8.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, t8.getBoard(),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("f2")).getPiece(),
						GameUtils.getCoordinateAtPosition("e3"),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("e3")).getPiece()));
		assertTrue(t9.getStatus().isDone());

		final MoveTransition t10 = t9.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t9.getBoard(),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(),
						GameUtils.getCoordinateAtPosition("h4")));
		assertTrue(t10.getStatus().isDone());

		final MoveTransition t11 = t10.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t10.getBoard(),
						t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece(),
						GameUtils.getCoordinateAtPosition("g3")));
		assertTrue(t11.getStatus().isDone());

		final MoveTransition t12 = t11.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t11.getBoard(),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("h4")).getPiece(),
						GameUtils.getCoordinateAtPosition("g3"),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("g3")).getPiece()));
		assertTrue(t12.getStatus().isDone());
		assertTrue(t12.getBoard().getCurrentPlayer().isInCheckMate());

	}

	@Test
	public void testAnotherGame() {
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

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(),
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("g1")).getPiece(),
						GameUtils.getCoordinateAtPosition("f3")));
		assertTrue(t3.getStatus().isDone());

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t3.getBoard(),
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("b8")).getPiece(),
						GameUtils.getCoordinateAtPosition("c6")));
		assertTrue(t4.getStatus().isDone());

		final MoveTransition t5 = t4.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(),
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("f1")).getPiece(),
						GameUtils.getCoordinateAtPosition("c4")));
		assertTrue(t5.getStatus().isDone());

		final MoveTransition t6 = t5.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t5.getBoard(),
						t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("c6")).getPiece(),
						GameUtils.getCoordinateAtPosition("d4")));
		assertTrue(t6.getStatus().isDone());

		final MoveTransition t7 = t6.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t6.getBoard(),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("f3")).getPiece(),
						GameUtils.getCoordinateAtPosition("e5"),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece()));
		assertTrue(t7.getStatus().isDone());

		final MoveTransition t8 = t7.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t7.getBoard(),
						t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(),
						GameUtils.getCoordinateAtPosition("g5")));
		assertTrue(t8.getStatus().isDone());

		final MoveTransition t9 = t8.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t8.getBoard(),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(),
						GameUtils.getCoordinateAtPosition("f7"),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("f7")).getPiece()));
		assertTrue(t9.getStatus().isDone());

		final MoveTransition t10 = t9.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t9.getBoard(),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("g5")).getPiece(),
						GameUtils.getCoordinateAtPosition("g2"),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece()));
		assertTrue(t10.getStatus().isDone());

		final MoveTransition t11 = t10.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t10.getBoard(),
						t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(),
						GameUtils.getCoordinateAtPosition("f1")));
		assertTrue(t11.getStatus().isDone());

		final MoveTransition t12 = t11.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t11.getBoard(),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece(),
						GameUtils.getCoordinateAtPosition("e4"),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece()));
		assertTrue(t12.getStatus().isDone());

		final MoveTransition t13 = t12.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t12.getBoard(),
						t12.getBoard().getTile(GameUtils.getCoordinateAtPosition("c4")).getPiece(),
						GameUtils.getCoordinateAtPosition("e2")));
		assertTrue(t13.getStatus().isDone());

		final MoveTransition t14 = t13.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t13.getBoard(),
						t13.getBoard().getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(),
						GameUtils.getCoordinateAtPosition("f3"),
						t13.getBoard().getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece()));
		assertTrue(t14.getStatus().isDone());
		assertTrue(t14.getBoard().getCurrentPlayer().isInCheckMate());

	}

	@Test
	public void testSmotheredMate() {

		final Board board = Board.createStandardBoard();
		final MoveTransition t1 = board.getCurrentPlayer().makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board,
			board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(), GameUtils.getCoordinateAtPosition("e4")));
		assertTrue(t1.getStatus().isDone());

		final MoveTransition t2 = t1.getBoard().getCurrentPlayer().makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(),
			t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(), GameUtils.getCoordinateAtPosition("e5")));
		assertTrue(t2.getStatus().isDone());

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer().makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(),
			t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("g1")).getPiece(), GameUtils.getCoordinateAtPosition("e2")));
		assertTrue(t3.getStatus().isDone());

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer().makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t3.getBoard(),
			t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("b8")).getPiece(), GameUtils.getCoordinateAtPosition("c6")));
		assertTrue(t4.getStatus().isDone());

		final MoveTransition t5 = t4.getBoard().getCurrentPlayer().makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(),
			t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("b1")).getPiece(), GameUtils.getCoordinateAtPosition("c3")));
		assertTrue(t4.getStatus().isDone());

		final MoveTransition t6 = t5.getBoard().getCurrentPlayer().makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t5.getBoard(),
			t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("c6")).getPiece(), GameUtils.getCoordinateAtPosition("d4")));
		assertTrue(t6.getStatus().isDone());

		final MoveTransition t7 = t6.getBoard().getCurrentPlayer().makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t6.getBoard(),
			t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece(), GameUtils.getCoordinateAtPosition("g3")));
		assertTrue(t7.getStatus().isDone());
		
		final MoveTransition t8 = t7.getBoard().getCurrentPlayer().makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t7.getBoard(),
			t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(), GameUtils.getCoordinateAtPosition("f3")));
		assertTrue(t8.getStatus().isDone());
		assertTrue(t8.getBoard().getCurrentPlayer().isInCheckMate());

	}

	@Test
	public void testHippopotamusMate() {

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

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(),
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("g1")).getPiece(),
						GameUtils.getCoordinateAtPosition("e2")));
		assertTrue(t3.getStatus().isDone());

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t3.getBoard(),
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(),
						GameUtils.getCoordinateAtPosition("h4")));
		assertTrue(t4.getStatus().isDone());

		final MoveTransition t5 = t4.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(),
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("b1")).getPiece(),
						GameUtils.getCoordinateAtPosition("c3")));
		assertTrue(t5.getStatus().isDone());

		final MoveTransition t6 = t5.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t5.getBoard(),
						t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("b8")).getPiece(),
						GameUtils.getCoordinateAtPosition("c6")));
		assertTrue(t6.getStatus().isDone());

		final MoveTransition t7 = t6.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t6.getBoard(),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece(),
						GameUtils.getCoordinateAtPosition("g3")));
		assertTrue(t7.getStatus().isDone());

		final MoveTransition t8 = t7.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t7.getBoard(),
						t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("h4")).getPiece(),
						GameUtils.getCoordinateAtPosition("g5")));
		assertTrue(t8.getStatus().isDone());

		final MoveTransition t9 = t8.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t8.getBoard(),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("d2")).getPiece(),
						GameUtils.getCoordinateAtPosition("d4")));
		assertTrue(t9.getStatus().isDone());

		final MoveTransition t10 = t9.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t9.getBoard(),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("c6")).getPiece(),
						GameUtils.getCoordinateAtPosition("d4"),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece()));
		assertTrue(t10.getStatus().isDone());

		final MoveTransition t11 = t10.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t10.getBoard(),
						t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("c1")).getPiece(),
						GameUtils.getCoordinateAtPosition("g5"),
						t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("g5")).getPiece()));
		assertTrue(t11.getStatus().isDone());

		final MoveTransition t12 = t11.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t11.getBoard(),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(),
						GameUtils.getCoordinateAtPosition("f3")));
		assertTrue(t12.getStatus().isDone());
		assertTrue(t12.getBoard().getCurrentPlayer().isInCheckMate());

	}

	@Test
	public void testBlackburneShillingMate() {

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

		final MoveTransition t3 = t2.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(),
						t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("g1")).getPiece(),
						GameUtils.getCoordinateAtPosition("f3")));
		assertTrue(t3.getStatus().isDone());

		final MoveTransition t4 = t3.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t3.getBoard(),
						t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("b8")).getPiece(),
						GameUtils.getCoordinateAtPosition("c6")));
		assertTrue(t4.getStatus().isDone());

		final MoveTransition t5 = t4.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(),
						t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("f1")).getPiece(),
						GameUtils.getCoordinateAtPosition("c4")));
		assertTrue(t5.getStatus().isDone());

		final MoveTransition t6 = t5.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t5.getBoard(),
						t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("c6")).getPiece(),
						GameUtils.getCoordinateAtPosition("d4")));
		assertTrue(t6.getStatus().isDone());

		final MoveTransition t7 = t6.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t6.getBoard(),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("f3")).getPiece(),
						GameUtils.getCoordinateAtPosition("e5"),
						t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece()));
		assertTrue(t7.getStatus().isDone());

		final MoveTransition t8 = t7.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t7.getBoard(),
						t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(),
						GameUtils.getCoordinateAtPosition("g5")));
		assertTrue(t8.getStatus().isDone());

		final MoveTransition t9 = t8.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t8.getBoard(),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(),
						GameUtils.getCoordinateAtPosition("f7"),
						t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("f7")).getPiece()));
		assertTrue(t9.getStatus().isDone());

		final MoveTransition t10 = t9.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t9.getBoard(),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("g5")).getPiece(),
						GameUtils.getCoordinateAtPosition("g2"),
						t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece()));
		assertTrue(t10.getStatus().isDone());

		final MoveTransition t11 = t10.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t10.getBoard(),
						t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(),
						GameUtils.getCoordinateAtPosition("f1")));
		assertTrue(t11.getStatus().isDone());

		final MoveTransition t12 = t11.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t11.getBoard(),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece(),
						GameUtils.getCoordinateAtPosition("e4"),
						t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece()));
		assertTrue(t12.getStatus().isDone());

		final MoveTransition t13 = t12.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t12.getBoard(),
						t12.getBoard().getTile(GameUtils.getCoordinateAtPosition("c4")).getPiece(),
						GameUtils.getCoordinateAtPosition("e2")));
		assertTrue(t13.getStatus().isDone());

		final MoveTransition t14 = t13.getBoard().getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t13.getBoard(),
						t13.getBoard().getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(),
						GameUtils.getCoordinateAtPosition("f3")));
		assertTrue(t14.getStatus().isDone());
		assertTrue(t14.getBoard().getCurrentPlayer().isInCheckMate());
	}

	@Test
	public void testAnastasiaMate() {
		testBuilder = new Builder();
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.ROOK, 0, com.williamspreitzer.chess.Color.BLACK, false));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.ROOK, 5, com.williamspreitzer.chess.Color.BLACK, false));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 8, com.williamspreitzer.chess.Color.BLACK, true));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 9, com.williamspreitzer.chess.Color.BLACK, true));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.PAWN, 10, com.williamspreitzer.chess.Color.BLACK, true));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.PAWN, 13, com.williamspreitzer.chess.Color.BLACK, true));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.KING, 15, com.williamspreitzer.chess.Color.BLACK, false));

		testBuilder.setPiece(
				PieceFactory.createPiece(PieceType.KNIGHT, 12, com.williamspreitzer.chess.Color.WHITE, false));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.ROOK, 27, com.williamspreitzer.chess.Color.WHITE, false));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.PAWN, 41, com.williamspreitzer.chess.Color.WHITE, false));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.PAWN, 48, com.williamspreitzer.chess.Color.WHITE, true));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.PAWN, 53, com.williamspreitzer.chess.Color.WHITE, true));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.PAWN, 54, com.williamspreitzer.chess.Color.WHITE, true));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.PAWN, 55, com.williamspreitzer.chess.Color.WHITE, true));
		testBuilder
				.setPiece(PieceFactory.createPiece(PieceType.KING, 62, com.williamspreitzer.chess.Color.WHITE, true));
		testBuilder.setMoveMaker(Color.WHITE);

		final Board board = testBuilder.build();
		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("d5")).getPiece(),
						GameUtils.getCoordinateAtPosition("h5")));
		assertTrue(t1.getStatus().isDone());
		assertTrue(t1.getBoard().getCurrentPlayer().isInCheckMate());
	}

	@Test
	public void testTwoBishopMate() {
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 3, Color.BLACK, true));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 8, Color.BLACK, false));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 10, Color.BLACK, true));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 11, Color.BLACK, true));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 15, Color.BLACK, true));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 17, Color.BLACK, true));

		testBuilder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 38, Color.WHITE, false));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 40, Color.WHITE, false));
		testBuilder.setMoveMaker(Color.WHITE);

		final Board board = testBuilder.build();
		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("g4")).getPiece(),
						GameUtils.getCoordinateAtPosition("h5")));

		assertTrue(t1.getStatus().isDone());
		assertTrue(t1.getBoard().getCurrentPlayer().isInCheckMate());
	}

	@Test
	public void testQueenRookMate() {
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 9, Color.WHITE, false));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.QUEEN, 16, Color.WHITE, false));

		testBuilder.setMoveMaker(Color.WHITE);

		final Board board = testBuilder.build();
		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("a6")).getPiece(),
						GameUtils.getCoordinateAtPosition("a8")));
		assertTrue(t1.getStatus().isDone());
		assertTrue(t1.getBoard().getCurrentPlayer().isInCheckMate());
	}

	@Test
	public void testQueenKnightMate() {
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.QUEEN, 15, Color.WHITE, false));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 29, Color.WHITE, false));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 55, Color.WHITE, true));
		testBuilder.setMoveMaker(Color.WHITE);

		final Board board = testBuilder.build();
		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("h7")).getPiece(),
						GameUtils.getCoordinateAtPosition("e7")));
		assertTrue(t1.getStatus().isDone());
		assertTrue(t1.getBoard().getCurrentPlayer().isInCheckMate());
	}

	@Test
	public void testBackRankMate() {
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 18, Color.BLACK, false));

		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 51, Color.WHITE, true));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 52, Color.WHITE, true));
		testBuilder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 53, Color.WHITE, true));

		// Set the current player
		testBuilder.setMoveMaker(Color.BLACK);

		final Board board = testBuilder.build();

		final MoveTransition t1 = board.getCurrentPlayer()
				.makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board,
						board.getTile(GameUtils.getCoordinateAtPosition("c6")).getPiece(),
						GameUtils.getCoordinateAtPosition("c1")));
		assertTrue(t1.getStatus().isDone());
		assertTrue(t1.getBoard().getCurrentPlayer().isInCheckMate());
	}

	/*
	 * @Test public void testMateInTwoTest1() { final Board board = FenUtilities.
	 * createGameFromFEN("6k1/1b4pp/1B1Q4/4p1P1/p3q3/2P3r1/P1P2PP1/R5K1 w - - 1 0");
	 * final MoveStrategy alphaBeta = new StockAlphaBeta(6); final Move bestMove =
	 * alphaBeta.execute(board); assertEquals(bestMove,
	 * Move.MoveFactory.createMove(board, GameUtils.getCoordinateAtPosition("d6"),
	 * GameUtils.getCoordinateAtPosition("e6"))); }
	 */

	/*
	 * @Test public void testMateInTwoTest2() { final Board board = FenUtilities.
	 * createGameFromFEN("3r3r/1Q5p/p3q2k/3NBp1B/3p3n/5P2/PP4PP/4R2K w - - 1 0");
	 * final MoveStrategy alphaBeta = new StockAlphaBeta(6); final Move bestMove =
	 * alphaBeta.execute(board); assertEquals(bestMove,
	 * Move.MoveFactory.createMove(board, GameUtils.getCoordinateAtPosition("b7"),
	 * GameUtils.getCoordinateAtPosition("g7"))); }
	 */

	/*
	 * @Test public void testMateInTwoTest3() { final Board board = FenUtilities.
	 * createGameFromFEN("rn3rk1/1R3ppp/2p5/8/PQ2P3/1P5P/2P1qPP1/3R2K1 w - - 1 0");
	 * final MoveStrategy alphaBeta = new StockAlphaBeta(1); final Move bestMove =
	 * alphaBeta.execute(board); assertEquals(bestMove,
	 * Move.MoveFactory.createMove(board, GameUtils.getCoordinateAtPosition("b4"),
	 * GameUtils.getCoordinateAtPosition("f8"))); }
	 */

	/*
	 * @Test public void testMateInFourTest1() { final Board board = FenUtilities.
	 * createGameFromFEN("7k/4r2B/1pb5/2P5/4p2Q/2q5/2P2R2/1K6 w - - 1 0"); final
	 * MoveStrategy alphaBeta = new StockAlphaBeta(6); final Move bestMove =
	 * alphaBeta.execute(board); assertEquals(bestMove,
	 * Move.MoveFactory.createMove(board, GameUtils.getCoordinateAtPosition("f2"),
	 * GameUtils.getCoordinateAtPosition("f8"))); }
	 */

	/*
	 * @Test public void testMagnusBlackToMoveAndWinTest1() { final Board board =
	 * FenUtilities
	 * .createGameFromFEN("2rr2k1/pb3pp1/4q2p/2pn4/2Q1P3/P4P2/1P3BPP/2KR2NR b - - 0 1"
	 * ); final MoveStrategy alphaBeta = new StockAlphaBeta(6); final Move bestMove
	 * = alphaBeta.execute(board); assertEquals(bestMove,
	 * Move.MoveFactory.createMove(board, GameUtils.getCoordinateAtPosition("d5"),
	 * GameUtils.getCoordinateAtPosition("e3"))); }
	 */
}
