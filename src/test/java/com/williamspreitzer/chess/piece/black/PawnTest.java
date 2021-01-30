package com.williamspreitzer.chess.piece.black;

import org.junit.jupiter.api.Assertions;
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
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;

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
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, 4, Color.BLACK, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, 60, Color.WHITE, false);
		pawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e7"), Color.BLACK,
				true);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setPiece(pawn);
		builder.setMoveMaker(Color.BLACK);
	}

	@Test
	void movePawnTest() {
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, 8));
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, 16));
	}

	@Test
	public void movePawnAttackTest() {
		Pawn attackedPawn = null;
		int[] offsets = { 7, 9 };
		for (int offset : offsets) {
			attackedPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN,
					GameUtils.getCoordinateAtPosition("e7") + offset, Color.WHITE, false);
			builder.setPiece(attackedPawn);
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, attackedPawn, offset));
		}
	}

	@Test
	void pawnPromotionTest() {

	}

	@Test
	void enPassantAttackTest7() {
		enPassantPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("a4"),
				Color.WHITE, false);
		blackPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("b4"),
				Color.BLACK, false);
		builder.setPiece(enPassantPawn);
		builder.setEnPassantPawn(enPassantPawn);
		builder.setPiece(blackPawn);
		board = builder.build();
		Assertions.assertEquals(8, board.getBlackPlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, blackPawn, enPassantPawn, 7));
	}

	@Test
	void enPassantAttackTest9() {
		enPassantPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e4"),
				Color.WHITE, false);
		blackPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("d4"),
				Color.BLACK, false);
		builder.setPiece(enPassantPawn);
		builder.setEnPassantPawn(enPassantPawn);
		builder.setPiece(blackPawn);
		board = builder.build();
		Assertions.assertEquals(8, board.getBlackPlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, blackPawn, enPassantPawn, 9));
	}

	private MoveStatus doMove(Builder builder, Pawn pawn, Pawn attackedPawn, int offset) {
		final Board board = builder.build();
		final Move move = MoveFactory.getMove(board, pawn.getPosition(), pawn.getPosition() + offset);
		final MoveTransition transition = board.getCurrentPlayer().makeMove(move);
		return transition.getStatus();
	}
}