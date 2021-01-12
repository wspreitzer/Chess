package com.williamspreitzer.chess.piece.black;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
	
	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, 4,Color.BLACK, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, 60,Color.WHITE, false);
		pawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e7"), Color.BLACK, true);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setPiece(pawn);
		builder.setMoveMaker(Color.BLACK);
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 8, 9, 10, 11, 12, 13, 14, 15 })
	void movePawnTest(int position) {
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, 8));
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, 16));
	}
	
	public void movePawnAttackTest() {
		Pawn attackedPawn = null;
		int[] offSets = {7, 9};
		for(int offSet : offSets) {
		    attackedPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("e7") + offSet, Color.WHITE, false);
			builder.setPiece(attackedPawn);
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, attackedPawn, offSet));
		}
	}
	
	@ParameterizedTest
	@ValueSource( ints = { 8, 48 })
	void pawnPromotionTest(int position) {
		
	}

	private MoveStatus doMove(Builder builder, Pawn pawn, Pawn attackedPawn, int offset) {
		final Board board = builder.build();
		final Move move = MoveFactory.getMove(board, pawn.getPosition(), pawn.getPosition() + offset);
		final MoveTransition transition = board.getCurrentPlayer().makeMove(move);
		return transition.getStatus();
	}
}