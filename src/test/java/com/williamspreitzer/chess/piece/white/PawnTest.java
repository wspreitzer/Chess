package com.williamspreitzer.chess.piece.white;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
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
	protected Builder builder;
	
	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, 4,Color.BLACK, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING,60,Color.WHITE, false);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 8, 9, 10, 11, 12, 13, 14, 15, 48, 49, 50, 51, 52, 53, 54, 55 })
	void movePawnTest(int position) {
		Pawn pawn = null;
		if(position < 16) {
			pawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, position, Color.BLACK, true);
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, 8));
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, 16));
		} else {
			pawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, position, Color.WHITE, true);
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, -8));
			Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, null, -16));
		}
	}
	
	@ParameterizedTest
	@ValueSource( ints = { 12, 52 } )
	void movePawnAttackTest(int position) {
		Pawn pawn = null;
		Pawn attackedPawn = null;
		int[] offSets = {7, 9};
		
		if( position == 12 ) {
			for(int offSet : offSets) {
				pawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, position, Color.BLACK, false);
			    attackedPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, position + offSet, Color.WHITE,false);
				builder.setPiece(attackedPawn);
				Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, attackedPawn, offSet));
			}
		} else {
			for(int offSet : offSets) {
				pawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, position, Color.WHITE, false);
				attackedPawn = (Pawn) PieceFactory.createPiece(PieceType.PAWN, position - offSet, Color.BLACK, false);
				builder.setPiece(attackedPawn);
				Assertions.assertEquals(MoveStatus.DONE, this.doMove(builder, pawn, attackedPawn, offSet * -1));
			}
		}
	}
	
	@ParameterizedTest
	@ValueSource( ints = { 8, 48 })
	void pawnPromotionTest(int position) {
		
	}

	private MoveStatus doMove(Builder builder, Pawn pawn, Pawn attackedPawn, int offset) {
		builder.setPiece(pawn);
		builder.setMoveMaker(pawn.getColor());
		final Board board = builder.build();
		final Move move = MoveFactory.createMove(board, pawn.getPosition(), pawn.getPosition() + offset);
		final MoveTransition transition = board.getCurrentPlayer().makeMove(move);
		return transition.getStatus();
	}
}