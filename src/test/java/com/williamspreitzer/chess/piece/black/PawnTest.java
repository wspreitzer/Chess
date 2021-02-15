package com.williamspreitzer.chess.piece.black;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveStatus;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.Knight;
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.utils.GameUtils;

public class PawnTest {

	protected King blackKing;
	protected King whiteKing;
	protected Pawn pawn;
	protected Builder builder;
	protected Board board;
	protected Pawn enPassantPawn;
	protected Pawn blackPawn;

	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, 4, Color.BLACK, false, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, 60, Color.WHITE, false, false);
		pawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e7"), Color.BLACK,
				true, null);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setPiece(pawn);
		builder.setMoveMaker(Color.BLACK);
	}

	@Test
	void movePawnTest() {
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, 8));
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, 16));
	}

	@Test
	public void movePawnAttackTest() {
		Pawn attackedPawn = null;
		int[] offsets = { 7, 9 };
		for (int offset : offsets) {
			attackedPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN,
					GameUtils.getCoordinateAtPosition("e7") + offset, Color.WHITE, false, null);
			builder.setPiece(attackedPawn);
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, offset));
		}
	}

	@Test
	void pawnPromotionTest() {
		Knight attackedKnight = null;
		Pawn attackingPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("b2"), Color.BLACK, false, null);
		int [] offsets = {7,9};
		for (int offset : offsets) {
			attackedKnight = (Knight) PieceFactory.createPiece(PieceType.KNIGHT, GameUtils.getCoordinateAtPosition("b2") + offset, Color.WHITE, false, null);
			builder.setPiece(attackedKnight);
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, attackingPawn, offset));
		}
	}

	@Test
	void enPassantAttackTest7() {
		enPassantPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("a4"),
				Color.WHITE, false, null);
		blackPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("b4"),
				Color.BLACK, false, null);
		builder.setPiece(enPassantPawn);
		builder.setEnPassantPawn(enPassantPawn);
		builder.setPiece(blackPawn);
		board = builder.build();
		Assertions.assertEquals(8, board.getBlackPlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, blackPawn, 7));
	}

	@Test
	void enPassantAttackTest9() {
		enPassantPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e4"),
				Color.WHITE, false, null);
		blackPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("d4"),
				Color.BLACK, false, null);
		builder.setPiece(enPassantPawn);
		builder.setEnPassantPawn(enPassantPawn);
		builder.setPiece(blackPawn);
		board = builder.build();
		Assertions.assertEquals(8, board.getBlackPlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, blackPawn, 9));
	}

	private MoveStatus doMove(Builder builder, Pawn pawn, int offset) {
		builder.setPiece(pawn);
		final Board board = builder.build();
		final Move move = MoveFactory.getMove(board, pawn.getPosition(), pawn.getPosition() + offset);
		final MoveTransition transition = board.getCurrentPlayer().makeMove(move);
		return transition.getStatus();
	}
}