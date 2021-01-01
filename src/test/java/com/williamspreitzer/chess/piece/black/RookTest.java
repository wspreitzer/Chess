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
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.piece.Rook;

public class RookTest {
	
	protected Builder builder;
	protected King blackKing;
	protected King whiteKing;
	protected Rook rook;
	protected Board board;
	
	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e8"), Color.BLACK, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e1"), Color.WHITE, false);
		rook = (Rook) PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("d4"), Color.BLACK, false);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setPiece(rook);
		builder.setMoveMaker(Color.BLACK);
		board = builder.build();
	}

	@Test
	public void allLegalMovesTest() {
		Assertions.assertEquals(19, board.getBlackPlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(5, board.getWhitePlayer().getPlayerLegalMoves().size());
	}
	
	@Test
	public void moveRookBackwardTest() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(rook.getPosition(), rook.getPosition() + Color.WHITE.getDirection() * 24));
	}
	
	@Test 
	public void moveRookForwardwardTest() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(rook.getPosition(), rook.getPosition() + Color.BLACK.getDirection() * 24));
	}
	
	
	@Test
	public void moveRookRightTest() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(rook.getPosition(), rook.getPosition() + Color.BLACK.getDirection() * 4));
	}
	
	@Test 
	public void moveRookLeftTest() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(rook.getPosition(), rook.getPosition() + Color.BLACK.getDirection() * 3)); 
	}

	@Test
	public void testTopMiddleRookWithPieceInWay() {
		rook = (Rook) PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("d8"), Color.BLACK, false);
		builder.setPiece(rook);
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("d4"), Color.BLACK, false));
		board = builder.build();
		Assertions.assertNull(MoveFactory.createMove(board, GameUtils.getCoordinateAtPosition("d8"), GameUtils.getCoordinateAtPosition("d3")).getBoard());
	}
	
	private MoveStatus doMove(int position, int destination) {
		final Move move = MoveFactory.createMove(board, position, destination);
		return board.getCurrentPlayer().makeMove(move).getStatus();
	}
}