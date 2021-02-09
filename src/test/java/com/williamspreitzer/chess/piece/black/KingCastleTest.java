package com.williamspreitzer.chess.piece.black;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveStatus;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.utils.GameUtils;

public class KingCastleTest {

	Board board = null;
	@BeforeEach
	private void setup() {
		board = Board.createStandardBoard();
	}
	
	@Test
	public void testKingSideCastle() {
		//White's first move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("a2"), GameUtils.getCoordinateAtPosition("a4")).isDone());
		
		//Black's first move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("e7"), GameUtils.getCoordinateAtPosition("e5")).isDone());
		
		//White's second move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("b2"), GameUtils.getCoordinateAtPosition("b4")).isDone());
		
		//Black's second move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("g8"), GameUtils.getCoordinateAtPosition("h6")).isDone());
		
		//White's third move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("c2"), GameUtils.getCoordinateAtPosition("c4")).isDone());
		
		//Black's third move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("f8"), GameUtils.getCoordinateAtPosition("e7")).isDone());
		
		//White's fourth move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("d2"), GameUtils.getCoordinateAtPosition("d4")).isDone());
		
		//Start of castle move
		final Move castleMove = MoveFactory.getMove(board, GameUtils.getCoordinateAtPosition("e8"), GameUtils.getCoordinateAtPosition("g8"));
		Assertions.assertTrue(board.getCurrentPlayer().getPlayerLegalMoves().contains(castleMove)); 
		final MoveTransition mt = board.getCurrentPlayer().makeMove(castleMove);
		Assertions.assertTrue(mt.getStatus().isDone());
	}
	
	@Test
	public void testQueenSideCastle() {
		//White's first move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("a2"), GameUtils.getCoordinateAtPosition("a4")).isDone());
		
		//Black's first move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("d7"), GameUtils.getCoordinateAtPosition("d5")).isDone());
		
		//White's second move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("b2"), GameUtils.getCoordinateAtPosition("b4")).isDone());
		
		//Black's second move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("c8"), GameUtils.getCoordinateAtPosition("e6")).isDone());
		
		//White's third move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("c2"), GameUtils.getCoordinateAtPosition("c4")).isDone());
		
		//Black's third move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("d8"), GameUtils.getCoordinateAtPosition("d7")).isDone());
		
		//White's fourth move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("d2"), GameUtils.getCoordinateAtPosition("d4")).isDone());
		
		//Black's fourth move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("b8"), GameUtils.getCoordinateAtPosition("a6")).isDone());
		
		//White's fifth move
		Assertions.assertTrue(makeMove(GameUtils.getCoordinateAtPosition("e2"), GameUtils.getCoordinateAtPosition("e4")).isDone());
		
		//Start of castle move
		final Move castleMove = MoveFactory.getMove(board, GameUtils.getCoordinateAtPosition("e8"), GameUtils.getCoordinateAtPosition("c8"));
		Assertions.assertTrue(board.getCurrentPlayer().getPlayerLegalMoves().contains(castleMove)); 
		final MoveTransition mt = board.getCurrentPlayer().makeMove(castleMove);
		Assertions.assertTrue(mt.getStatus().isDone());
	} 
	
	private MoveStatus makeMove(int position, int destination) {
		final MoveTransition transition = board.getCurrentPlayer()
				.makeMove(MoveFactory.getMove(board, position, destination));
		board = transition.getBoard();
		return transition.getStatus();
	}
}
