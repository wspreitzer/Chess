package com.williamspreitzer.chess.piece.black;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.Knight;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;

public class KnightTest {

	protected Builder builder;
	protected King blackKing;
	protected King whiteKing;
	protected Board board;
	
	@BeforeEach
	private void setup() {
		blackKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e8"), Color.BLACK, false);
		whiteKing = (King) PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e1"), Color.WHITE, false);
		builder = new Builder();
		builder.setPiece(blackKing);
		builder.setPiece(whiteKing);
		builder.setMoveMaker(Color.BLACK);
		
	}
	
	@Test
	public void testKnightsInHomeCorners() {
		Knight queensKnight = (Knight) PieceFactory.createPiece(PieceType.KNIGHT, GameUtils.getCoordinateAtPosition("a8"), Color.BLACK, false);
		Knight kingsKnight = (Knight) PieceFactory.createPiece(PieceType.KNIGHT, GameUtils.getCoordinateAtPosition("h8"), Color.BLACK, false);
		builder.setPiece(kingsKnight);
		builder.setPiece(queensKnight);
		board = builder.build();
		final Move[] moves = new Move[] { this.createMove(kingsKnight, kingsKnight.getPosition(), kingsKnight.getPosition() + Color.BLACK.getDirection() * 15),
				  this.createMove(kingsKnight, kingsKnight.getPosition(), kingsKnight.getPosition() + Color.BLACK.getDirection() * 6),
				  this.createMove(queensKnight, queensKnight.getPosition(), queensKnight.getPosition() + Color.BLACK.getDirection() * 17),
				  this.createMove(queensKnight, queensKnight.getPosition(), queensKnight.getPosition() + Color.BLACK.getDirection() * 10)};
		int totalMoves = board.getBlackPlayer().getPlayerLegalMoves().size() + board.getWhitePlayer().getPlayerLegalMoves().size();
		assertEquals(14, totalMoves);
		assertTrue(board.getBlackPlayer().getPlayerLegalMoves().containsAll(Arrays.asList(moves)));
	}
	
	@Test
	public void testKnightsInOpponentCorners() {
		Knight queensKnight = (Knight) PieceFactory.createPiece(PieceType.KNIGHT, GameUtils.getCoordinateAtPosition("a1"), Color.BLACK, false);
		Knight kingsKnight = (Knight) PieceFactory.createPiece(PieceType.KNIGHT, GameUtils.getCoordinateAtPosition("h1"), Color.BLACK, false);
		builder.setPiece(kingsKnight);
		builder.setPiece(queensKnight);
		board = builder.build();
		final Move[] moves = new Move[] { this.createMove(kingsKnight, kingsKnight.getPosition(), kingsKnight.getPosition() + Color.WHITE.getDirection() * 17),
										  this.createMove(kingsKnight, kingsKnight.getPosition(), kingsKnight.getPosition() + Color.WHITE.getDirection() * 10),
										  this.createMove(queensKnight, queensKnight.getPosition(), queensKnight.getPosition() + Color.WHITE.getDirection() * 15),
										  this.createMove(queensKnight, queensKnight.getPosition(), queensKnight.getPosition() + Color.WHITE.getDirection() * 6) };
		int totalMoves = board.getBlackPlayer().getPlayerLegalMoves().size() + board.getWhitePlayer().getPlayerLegalMoves().size();
		assertEquals(14, totalMoves);
		assertTrue(board.getBlackPlayer().getPlayerLegalMoves().containsAll(Arrays.asList(moves)));
	}
	
	@Test
	public void testKnightInMiddleOfBoard() {
		Knight knight = (Knight) PieceFactory.createPiece(PieceType.KNIGHT, GameUtils.getCoordinateAtPosition("d4"), Color.BLACK, false);
		builder.setPiece(knight);
		board = builder.build();
		final Move[] moves = new Move[] { this.createMove(knight, knight.getPosition(), knight.getPosition() + Color.WHITE.getDirection() * 17),
										  this.createMove(knight, knight.getPosition(), knight.getPosition() + Color.WHITE.getDirection() * 15),
										  this.createMove(knight, knight.getPosition(), knight.getPosition() + Color.WHITE.getDirection() * 10),
										  this.createMove(knight, knight.getPosition(), knight.getPosition() + Color.WHITE.getDirection() * 6),
										  this.createMove(knight, knight.getPosition(), knight.getPosition() + Color.BLACK.getDirection() * 6),
										  this.createMove(knight, knight.getPosition(), knight.getPosition() + Color.BLACK.getDirection() * 10),
										  this.createMove(knight, knight.getPosition(), knight.getPosition() + Color.BLACK.getDirection() * 15),
										  this.createMove(knight, knight.getPosition(), knight.getPosition() + Color.BLACK.getDirection() * 17) };
		int totalMoves = board.getBlackPlayer().getPlayerLegalMoves().size() + board.getWhitePlayer().getPlayerLegalMoves().size();
		assertEquals(18, totalMoves);
		assertTrue(board.getBlackPlayer().getPlayerLegalMoves().containsAll(Arrays.asList(moves)));
	}
	
	private Move createMove(Piece piece, int position, int destination) {
		return MoveFactory.createMove(board, position, destination);
	}
}