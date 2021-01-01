package com.williamspreitzer.chess.piece.black;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveStatus;
import com.williamspreitzer.chess.piece.Bishop;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;

public class BishopTest {
	protected Builder builder;
	protected King blackKing;
	protected King whiteKing;
	protected Bishop bishop;
	protected Board board;
	
	@BeforeEach
	private void setup() {
		
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("d8"), Color.BLACK, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("d1"), Color.WHITE, false);
		bishop = (Bishop) PieceFactory.createPiece(PieceType.BISHOP, GameUtils.getCoordinateAtPosition("d4"), Color.WHITE, false);
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
		bishop = (Bishop) PieceFactory.createPiece(PieceType.BISHOP, GameUtils.getCoordinateAtPosition("h8"), Color.WHITE,false);
		builder.setPiece(bishop);
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, GameUtils.getCoordinateAtPosition("d4"), Color.WHITE, false));
		board = builder.build();
		Assertions.assertNull(MoveFactory.createMove(board, GameUtils.getCoordinateAtPosition("h8"), GameUtils.getCoordinateAtPosition("c3")).getBoard());
	}
	
	private MoveStatus doMove(int position, int destination) {
		final Move move = MoveFactory.createMove(board, position, destination);
		return board.getCurrentPlayer().makeMove(move).getStatus();
	}

}