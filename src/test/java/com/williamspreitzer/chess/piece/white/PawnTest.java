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
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.Knight;
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.utils.GameUtils;

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
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e8"), Color.BLACK, false, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e1"), Color.WHITE, false, false);
		pawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e2"), Color.WHITE, true, null);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setPiece(pawn);
		builder.setMoveMaker(Color.WHITE);
	}
	@Test
	void movePawnTest() {
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, 8));
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, 16));
	}
	
	@Test
	void movePawnAttackTest() {
		Pawn attackedPawn = null;
		int[] offsets = {7, 9};
		
		for (int offset : offsets) {
			attackedPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e2") - offset, Color.BLACK, false, null);
			builder.setPiece(attackedPawn);
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, offset));
		}
	}
	
	@Test
	void pawnPromotionTest() {
		Knight attackedKnight = null;
		Pawn attackingPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("b7"), Color.WHITE, false, null);
		int [] offsets = {7,9};
		for (int offset : offsets) {
			attackedKnight = (Knight) PieceFactory.createPiece(PieceType.KNIGHT, GameUtils.getCoordinateAtPosition("b7") - offset, Color.BLACK, false, null);
			builder.setPiece(attackedKnight);
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, attackingPawn, offset));
		}
	}
	
	
	@Test
	void enPassantAttackTest7() {
		enPassantPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e5"), Color.BLACK, false, null);
		whitePawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("d5"), Color.WHITE, false, null);
		builder.setPiece(enPassantPawn);
		builder.setEnPassantPawn(enPassantPawn);
		builder.setPiece(whitePawn);
		board = builder.build();
		Assertions.assertEquals(8, board.getWhitePlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, whitePawn, 7));
	}
	
	@Test
	void enPassantAttackTest9() {
		enPassantPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e5"), Color.BLACK, false, null);
		whitePawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("f5"), Color.WHITE, false, null);
		builder.setPiece(enPassantPawn);
		builder.setEnPassantPawn(enPassantPawn);
		builder.setPiece(whitePawn);
		board = builder.build();
		Assertions.assertEquals(8, board.getWhitePlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, whitePawn, 9));
	}
	
	private MoveStatus doMove(Builder builder, Pawn pawn, int offset) {
		builder.setPiece(pawn);
		board = builder.build();
		final Move move = MoveFactory.getMove(board, pawn.getPosition(), pawn.getPosition() - offset);
		final MoveTransition transition = board.getCurrentPlayer().makeMove(move);
		return transition.getStatus();
	}
}