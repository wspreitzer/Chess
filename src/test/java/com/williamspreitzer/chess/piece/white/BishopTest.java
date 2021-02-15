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
import com.williamspreitzer.chess.piece.Bishop;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.utils.GameUtils;

public class BishopTest {
	protected Builder builder;
	protected King blackKing;
	protected King whiteKing;
	protected Bishop bishop;
	protected Board board;
	
	@BeforeEach
	private void setup() {
		
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("d8"), Color.BLACK, false, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("d1"), Color.WHITE, false, false);
		bishop = (Bishop) PieceFactory.createPiece(PieceType.BISHOP, GameUtils.getCoordinateAtPosition("d4"), Color.WHITE, false, null);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setPiece(bishop);
		builder.setMoveMaker(Color.WHITE);
		board = builder.build();
	}
	
	@Test
	public void allLegalMovesTest() {
		Assertions.assertEquals(5, board.getBlackPlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(18, board.getWhitePlayer().getPlayerLegalMoves().size());
	}
	@Test
	public void moveBishopBackward() {
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(bishop.getPosition(), bishop.getPosition() + (Color.WHITE.getDirection() * 7)));
	}
	
	@Test
	public void moveBishopForward() {
		Assertions.assertEquals(MoveStatus.DONE, this.doMove(bishop.getPosition(), bishop.getPosition() + (Color.WHITE.getDirection() * 7)));
	}
	
	@Test
	public void testTopRightBishopWithPieceInTheWay() {
		bishop = (Bishop) PieceFactory.createPiece(PieceType.BISHOP, GameUtils.getCoordinateAtPosition("h8"), Color.WHITE,false, null);
		builder.setPiece(bishop);
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, GameUtils.getCoordinateAtPosition("d4"), Color.WHITE, false, null));
		board = builder.build();
		Assertions.assertNull(MoveFactory.getMove(board, GameUtils.getCoordinateAtPosition("h8"), GameUtils.getCoordinateAtPosition("c3")).getBoard());
	}
	
	private MoveStatus doMove(int position, int destination) {
		final Move move = MoveFactory.getMove(board, position, destination);
		return board.getCurrentPlayer().makeMove(move).getStatus();
	}

}