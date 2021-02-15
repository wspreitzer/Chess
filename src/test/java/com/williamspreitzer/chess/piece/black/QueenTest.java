package com.williamspreitzer.chess.piece.black;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveStatus;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.piece.Queen;
import com.williamspreitzer.chess.utils.GameUtils;

public class QueenTest {
	protected Builder builder;
	protected King blackKing;
	protected King whiteKing;
	protected Queen queen;
	protected Board board;
	
	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e8"), Color.BLACK, false, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e1"), Color.WHITE, false, false);
		queen = (Queen) PieceFactory.createPiece(PieceType.QUEEN, GameUtils.getCoordinateAtPosition("d4"), Color.BLACK, false, null);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setPiece(queen);
		builder.setMoveMaker(Color.BLACK);
		board = builder.build();
	}
	
	@Test
	public void allLegalMovesTest() {
		Assertions.assertEquals(32, board.getBlackPlayer().getPlayerLegalMoves().size());
		Assertions.assertEquals(5, board.getWhitePlayer().getPlayerLegalMoves().size());
	}
	
	
	@Test
	public void moveQueenForwardTest() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(queen.getPosition(), queen.getPosition() + Color.BLACK.getDirection() * 24));
	}
	
	@Test
	public void moveQueenBackwardTest() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(queen.getPosition(), queen.getPosition() + Color.WHITE.getDirection() * 24 ));
	}

	@Test
	public void moveQueenRight() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(queen.getPosition(), queen.getPosition() + Color.WHITE.getDirection() * 3));
	}
	
	@Test
	public void moveQueenLeft() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(queen.getPosition(), queen.getPosition() + Color.BLACK.getDirection() * 4));
	}
	
	@Test
	public void moveQueenForwardRight() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(queen.getPosition(), queen.getPosition() + Color.BLACK.getDirection() * 7));
	}
	
	@Test
	public void moveQueenForwardLeft() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(queen.getPosition(), queen.getPosition() + Color.BLACK.getDirection() * 9));
	}
	
	@Test
	public void moveQueenBackwardRight() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(queen.getPosition(), queen.getPosition() + Color.WHITE.getDirection() * 9));
	}
	
	@Test
	public void moveQueenBackwardLeft() {
		Assertions.assertEquals(MoveStatus.DONE, doMove(queen.getPosition(), queen.getPosition() + Color.WHITE.getDirection() * 7));
	}
	
	@Test
	public void testTopQueenWithPieceInWay() {
		queen = (Queen) PieceFactory.createPiece(PieceType.QUEEN, GameUtils.getCoordinateAtPosition("h8"), Color.BLACK, false, null);
		builder.setPiece(queen);
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("d4"), Color.BLACK, false, null));
		board = builder.build();
		Assertions.assertNull(MoveFactory.getMove(board, GameUtils.getCoordinateAtPosition("h8"), GameUtils.getCoordinateAtPosition("c3")).getBoard());
	}

	@Test
	public void testIsEighthColumnExclusion() {
		
	}
	
	@Test
	public void testIsFirstColumnExclusion()  {
		
	}
	
	private MoveStatus doMove(int position, int destination) {
		return board
				.getCurrentPlayer()
				.makeMove(MoveFactory
						.getMove(board, position, destination))
				.getStatus();
	}
}