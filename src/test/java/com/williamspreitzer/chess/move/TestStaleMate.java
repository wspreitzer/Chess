package com.williamspreitzer.chess.move;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.piece.Bishop;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestStaleMate {
	Builder builder;
	King blackKing;
	King whiteKing;
	
	@BeforeEach
	private void setup() { 
		builder = new Builder();
	}
	
    @Test
    public void testAnandKramnikStaleMate() {

        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("g7"), Color.BLACK, false));
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("f6"), Color.BLACK, false));
        builder.setPiece(PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e4"), Color.BLACK, false));
        
        // White Layout
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("f5"), Color.WHITE, false));
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("h4"), Color.WHITE, false));
        builder.setPiece(PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("h5"), Color.WHITE, false));
        // Set the current player
        builder.setMoveMaker(Color.BLACK);
        final Board board = builder.build();
        
        assertFalse(board.getCurrentPlayer().isInStaleMate());
        
        final MoveTransition t1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_ATTACK_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(),
                        GameUtils.getCoordinateAtPosition("f5")));
        
        assertTrue(t1.getStatus().isDone());
        assertTrue(t1.getBoard().getCurrentPlayer().isInStaleMate());
        assertFalse(t1.getBoard().getCurrentPlayer().isInCheck());
        assertFalse(t1.getBoard().getCurrentPlayer().isInCheckMate());
    }

    @Test
    public void testAnonymousStaleMate() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("c8"), Color.BLACK, false));

        // White Layout
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("c7"), Color.WHITE, false));
        builder.setPiece(PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("c5"), Color.WHITE, false));
 
        // Set the current player
        builder.setMoveMaker(Color.WHITE);
        
        final Board board = builder.build();
        assertFalse(board.getCurrentPlayer().isInStaleMate());
        
        final MoveTransition t1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("c5")).getPiece(),
                        GameUtils.getCoordinateAtPosition("c6")));
        
        assertTrue(t1.getStatus().isDone());
        assertTrue(t1.getBoard().getCurrentPlayer().isInStaleMate());
        assertFalse(t1.getBoard().getCurrentPlayer().isInCheck());
        assertFalse(t1.getBoard().getCurrentPlayer().isInCheckMate());
    }

    @Test
    public void testAnonymousStaleMate2() {
        final Builder builder = new Builder();
        
        // Black Layout
        builder.setPiece(PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("a8"), Color.BLACK, false));
        
        // White Layout
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, GameUtils.getCoordinateAtPosition("a6"), Color.WHITE, false));
        builder.setPiece(PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("b6"), Color.WHITE, false));
        builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, GameUtils.getCoordinateAtPosition("d6"), Color.WHITE, false));
        
        // Set the current player
        builder.setMoveMaker(Color.WHITE);
        
        final Board board = builder.build();
        assertFalse(board.getCurrentPlayer().isInStaleMate());
        
        final MoveTransition t1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a6")).getPiece(),
                        GameUtils.getCoordinateAtPosition("a7")));
        assertTrue(t1.getStatus().isDone());
        assertTrue(t1.getBoard().getCurrentPlayer().isInStaleMate());
        assertFalse(t1.getBoard().getCurrentPlayer().isInCheck());
        assertFalse(t1.getBoard().getCurrentPlayer().isInCheckMate());
    }
}