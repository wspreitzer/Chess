package com.williamspreitzer.chess.piece.white;

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
import com.williamspreitzer.chess.piece.EnPassantPawn;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;

public class PawnTest {

	protected King blackKing;
	protected King whiteKing;
	protected Pawn pawn;
	protected Pawn enPassantPawn;
	protected Pawn whitePawn;
	protected Builder builder;
	protected Board board;
	
	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e8"), Color.BLACK, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e1"), Color.WHITE, false);
		pawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e2"), Color.WHITE, true);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setPiece(pawn);
		builder.setMoveMaker(Color.WHITE);
	}
	@Test
	void movePawnTest() {
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, 8));
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, 16));
	}
	
	@Test
	void movePawnAttackTest() {
		Pawn attackedPawn = null;
		int[] offsets = {7, 9};
		
		for (int offset : offsets) {
			attackedPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e2") - offset, Color.BLACK, false);
			builder.setPiece(attackedPawn);
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, attackedPawn, offset));
		}
	}
	
	@Test
	void pawnPromotionTest() {
		
	}
	
	
	@Test
	void enPassantAttackTest7() {
		enPassantPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e5"), Color.BLACK, false);
		whitePawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("d5"), Color.WHITE, false);
		builder.setPiece(enPassantPawn);
		builder.setEnPassantPawn(enPassantPawn);
		builder.setPiece(whitePawn);
		board = builder.build();
		Assertions.assertEquals(8, board.getWhitePlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, whitePawn, enPassantPawn, 7));
	}
	
	@Test
	void enPassantAttackTest9() {
		enPassantPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e5"), Color.BLACK, false);
		whitePawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("f5"), Color.WHITE, false);
		builder.setPiece(enPassantPawn);
		builder.setEnPassantPawn(enPassantPawn);
		builder.setPiece(whitePawn);
		board = builder.build();
		Assertions.assertEquals(8, board.getWhitePlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, whitePawn, enPassantPawn, 9));
	}
	
	private MoveStatus doMove(Builder builder, Pawn pawn, Pawn attackedPawn, int offset) {
		builder.setPiece(pawn);
		builder.setMoveMaker(pawn.getColor());
		board = builder.build();
		final Move move = MoveFactory.getMove(board, pawn.getPosition(), pawn.getPosition() - offset);
		final MoveTransition transition = board.getCurrentPlayer().makeMove(move);
		return transition.getStatus();
	}
}