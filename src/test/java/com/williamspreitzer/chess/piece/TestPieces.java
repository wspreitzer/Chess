package com.williamspreitzer.chess.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;
import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestPieces {
	
	Builder builder;
	
	@BeforeEach
	private void setup() {
		builder = new Builder();
		builder.setPiece(PieceFactory.createPiece(PieceType.KING, 3, Color.BLACK, false, false));
		builder.setPiece(PieceFactory.createPiece(PieceType.KING, 60, Color.WHITE, false, false));
		builder.setMoveMaker(Color.WHITE);
	}

    @Test
    public void testMiddleQueenOnEmptyBoard() {
    	
        builder.setPiece(PieceFactory.createPiece(PieceType.QUEEN, 36, Color.WHITE, false, null));
        final Board board = builder.build();

        final Collection<Move> whiteLegals = board.getWhitePlayer().getPlayerLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getPlayerLegalMoves();
        
        assertEquals(whiteLegals.size(), 31);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e8"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("a4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("b4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("c4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("f4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("g4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("h4"))));
    }

    @Test
    public void testLegalMoveAllAvailable() {

        builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 28, Color.BLACK, false, null));
        builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 36, Color.WHITE, false, null));
        final Board board = builder.build();
        
        final Collection<Move> whiteLegals = board.getWhitePlayer().getPlayerLegalMoves();
        assertEquals(whiteLegals.size(), 13);
        final Move wm1 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("d6"));
        final Move wm2 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("f6"));
        final Move wm3 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("c5"));
        final Move wm4 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("g5"));
        final Move wm5 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("c3"));
        final Move wm6 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("g3"));
        final Move wm7 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("d2"));
        final Move wm8 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("f2"));

        assertTrue(whiteLegals.contains(wm1));
        assertTrue(whiteLegals.contains(wm2));
        assertTrue(whiteLegals.contains(wm3));
        assertTrue(whiteLegals.contains(wm4));
        assertTrue(whiteLegals.contains(wm5));
        assertTrue(whiteLegals.contains(wm6));
        assertTrue(whiteLegals.contains(wm7));
        assertTrue(whiteLegals.contains(wm8));

        final Builder boardBuilder2 = new Builder();
        boardBuilder2.setMoveMaker(Color.BLACK);
        builder.build();

        final Collection<Move> blackLegals = board.getBlackPlayer().getPlayerLegalMoves();

        final Move bm1 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("d7"));
        final Move bm2 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("f7"));
        final Move bm3 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("c6"));
        final Move bm4 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("g6"));
        final Move bm5 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("c4"));
        final Move bm6 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("g4"));
        final Move bm7 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("d3"));
        final Move bm8 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("f3"));

        assertEquals(blackLegals.size(), 13);

        assertTrue(blackLegals.contains(bm1));
        assertTrue(blackLegals.contains(bm2));
        assertTrue(blackLegals.contains(bm3));
        assertTrue(blackLegals.contains(bm4));
        assertTrue(blackLegals.contains(bm5));
        assertTrue(blackLegals.contains(bm6));
        assertTrue(blackLegals.contains(bm7));
        assertTrue(blackLegals.contains(bm8));
    }

    @Test
    public void testKnightInCorners() {
        
        builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 0, Color.BLACK, false, null));
        builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 56, Color.WHITE, false, null));
        
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getPlayerLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getPlayerLegalMoves();
        assertEquals(whiteLegals.size(), 7);
        assertEquals(blackLegals.size(), 7);
        
        final Move wm1 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("b3"));
        final Move wm2 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("c2"));
        
        assertTrue(whiteLegals.contains(wm1));
        assertTrue(whiteLegals.contains(wm2));
        
        final Move bm1 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("b6"));
        final Move bm2 = MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("c7"));
        
        assertTrue(blackLegals.contains(bm1));
        assertTrue(blackLegals.contains(bm2));

    }

    @Test
    public void testMiddleBishopOnEmptyBoard() {
        builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 35, Color.WHITE, false, null));
        builder.setMoveMaker(Color.WHITE);
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getPlayerLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getPlayerLegalMoves();
        assertEquals(whiteLegals.size(), 18);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(), GameUtils.getCoordinateAtPosition("a7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(), GameUtils.getCoordinateAtPosition("b6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(), GameUtils.getCoordinateAtPosition("c5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(), GameUtils.getCoordinateAtPosition("e3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(), GameUtils.getCoordinateAtPosition("f2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(), GameUtils.getCoordinateAtPosition("g1"))));
    }

    @Test
    public void testTopLeftBishopOnEmptyBoard() {
        builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 0, Color.WHITE, false, null));
        
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getPlayerLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getPlayerLegalMoves();
        
        assertEquals(board.getTile(0), board.getTile(0));
        assertNotNull(board.getTile(0));
        assertEquals(whiteLegals.size(), 12);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("b7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("c6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("d5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("e4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("f3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("g2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece(), GameUtils.getCoordinateAtPosition("h1"))));
    }

    @Test
    public void testTopRightBishopOnEmptyBoard() {
        builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 7, Color.WHITE, false, null));
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getPlayerLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getPlayerLegalMoves();
        assertEquals(whiteLegals.size(), 12);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece(), GameUtils.getCoordinateAtPosition("g7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece(), GameUtils.getCoordinateAtPosition("f6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece(), GameUtils.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece(), GameUtils.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece(), GameUtils.getCoordinateAtPosition("c3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece(), GameUtils.getCoordinateAtPosition("b2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece(), GameUtils.getCoordinateAtPosition("a1"))));
    }

    @Test
    public void testBottomLeftBishopOnEmptyBoard() {
        builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 56, Color.WHITE, false, null));
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getPlayerLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getPlayerLegalMoves();
        assertEquals(whiteLegals.size(), 12);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("b2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("c3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("f6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("g7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("h8"))));
    }

    @Test
    public void testBottomRightBishopOnEmptyBoard() {
        builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 63, Color.WHITE, false, null));
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getPlayerLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getPlayerLegalMoves();
        assertEquals(whiteLegals.size(), 12);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(), GameUtils.getCoordinateAtPosition("g2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(), GameUtils.getCoordinateAtPosition("f3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(), GameUtils.getCoordinateAtPosition("e4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(), GameUtils.getCoordinateAtPosition("d5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(), GameUtils.getCoordinateAtPosition("c6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(), GameUtils.getCoordinateAtPosition("b7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(), GameUtils.getCoordinateAtPosition("a8"))));
    }

    @Test
    public void testMiddleRookOnEmptyBoard() {
        builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 36, Color.WHITE, false, null));
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getPlayerLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getPlayerLegalMoves();
        assertEquals(whiteLegals.size(), 18);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e8"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("a4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("b4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("c4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("f4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("g4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("h4"))));
    }

    @Test
    public void testPawnPromotion() {
        builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 5, Color.BLACK, false, null));
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 15, Color.WHITE, false, null));
        final Board board = builder.build();
        final Move m1 = MoveFactory.createNonAttackingMove(MoveType.PAWN_PROMOTION_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("h7")).getPiece(), GameUtils.getCoordinateAtPosition("h8"));
        final MoveTransition t1 = board.getCurrentPlayer().makeMove(m1);
        assertTrue(t1.getStatus().isDone());
        
        final Move m2 = MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, t1.getBoard(), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(), GameUtils.getCoordinateAtPosition("h8"), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece());
        final MoveTransition t2 = t1.getBoard().getCurrentPlayer().makeMove(m2);
        assertTrue(t2.getStatus().isDone());
        assertTrue(t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece().getType().equals(PieceType.QUEEN));
    }

    @Test
    public void testSimpleWhiteEnPassant() {
        
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 11, Color.BLACK, true, null));
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 52, Color.WHITE, true, null));

        final Board board = builder.build();
        final Move m1 = MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(), GameUtils.getCoordinateAtPosition("e4"));
        final MoveTransition t1 = board.getCurrentPlayer().makeMove(m1);
        assertTrue(t1.getStatus().isDone());
        
        final Move m2 = MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t1.getBoard(), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(), GameUtils.getCoordinateAtPosition("c8"));
        final MoveTransition t2 = t1.getBoard().getCurrentPlayer().makeMove(m2);
        assertTrue(t2.getStatus().isDone());
       
        final Move m3 = MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t2.getBoard(), t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece(), GameUtils.getCoordinateAtPosition("e5"));
        final MoveTransition t3 = t2.getBoard().getCurrentPlayer().makeMove(m3);
        assertTrue(t3.getStatus().isDone());
        
        final Move m4 = MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t3.getBoard(), t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece(), GameUtils.getCoordinateAtPosition("d5"));
        final MoveTransition t4 = t3.getBoard().getCurrentPlayer().makeMove(m4);
        assertTrue(t4.getStatus().isDone());
        
        final Move m5 = MoveFactory.createAttackMove(MoveType.PAWN_EN_PASSANT_ATTACK_MOVE, t4.getBoard(), t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("e5")).getPiece(), GameUtils.getCoordinateAtPosition("d6"), t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("d5")).getPiece());
        final MoveTransition t5 = t4.getBoard().getCurrentPlayer().makeMove(m5);
        assertTrue(t5.getStatus().isDone());
        assertNull(t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("d5")).getPiece());
    }

    @Test
    public void testSimpleBlackEnPassant() {
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 11, Color.BLACK, true, null));
        builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 52, Color.WHITE, true, null));
        final Board board = builder.build();
        
        final Move m1 = MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e1")).getPiece(), GameUtils.getCoordinateAtPosition("d1"));
        final MoveTransition t1 = board.getCurrentPlayer().makeMove(m1);
        assertTrue(t1.getStatus().isDone());
        
        final Move m2 = MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece(), GameUtils.getCoordinateAtPosition("d5"));
        final MoveTransition t2 = t1.getBoard().getCurrentPlayer().makeMove(m2);
        assertTrue(t2.getStatus().isDone());
        
        final Move m3 = MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(), t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("d1")).getPiece(), GameUtils.getCoordinateAtPosition("c1"));
        final MoveTransition t3 = t2.getBoard().getCurrentPlayer().makeMove(m3);
        assertTrue(t3.getStatus().isDone()); 
        
        final Move m4 = MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t3.getBoard(), t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d5")).getPiece(), GameUtils.getCoordinateAtPosition("d4"));
        final MoveTransition t4 = t3.getBoard().getCurrentPlayer().makeMove(m4);
        assertTrue(t4.getStatus().isDone());
        
        final Move m5 = MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t4.getBoard(), t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(), GameUtils.getCoordinateAtPosition("e4"));
        final MoveTransition t5 = t4.getBoard().getCurrentPlayer().makeMove(m5);
        assertTrue(t5.getStatus().isDone());
        
        final Move m6 = MoveFactory.createAttackMove(MoveType.PAWN_EN_PASSANT_ATTACK_MOVE, t5.getBoard(), t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(), GameUtils.getCoordinateAtPosition("e3"), t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece());
        final MoveTransition t6 = t5.getBoard().getCurrentPlayer().makeMove(m6);
        assertTrue(t6.getStatus().isDone());
        assertNull(t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("e4")).getPiece());
    }

    @Test
    public void testEnPassant2() {
        
    	final Board board = Board.createStandardBoard();
        final Move m1 = MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(), GameUtils.getCoordinateAtPosition("e3"));
        final MoveTransition t1 = board.getCurrentPlayer().makeMove(m1);
        assertTrue(t1.getStatus().isDone());
        
        final Move m2 = MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("h7")).getPiece(), GameUtils.getCoordinateAtPosition("h5"));
        final MoveTransition t2 = t1.getBoard().getCurrentPlayer().makeMove(m2);
        assertTrue(t2.getStatus().isDone());
        
        final Move m3 = MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t2.getBoard(), t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("e3")).getPiece(), GameUtils.getCoordinateAtPosition("e4"));
        final MoveTransition t3 = t2.getBoard().getCurrentPlayer().makeMove(m3);
        assertTrue(t3.getStatus().isDone());
        
        final Move m4 = MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t3.getBoard(), t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("h5")).getPiece(), GameUtils.getCoordinateAtPosition("h4"));
        final MoveTransition t4 = t3.getBoard().getCurrentPlayer().makeMove(m4);
        assertTrue(t4.getStatus().isDone());
        
        final Move m5 = MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t4.getBoard(), t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("g2")).getPiece(), GameUtils.getCoordinateAtPosition("g4"));
        final MoveTransition t5 = t4.getBoard().getCurrentPlayer().makeMove(m5);
        assertTrue(t5.getStatus().isDone());
        
        final Move m6 = MoveFactory.createAttackMove(MoveType.PAWN_EN_PASSANT_ATTACK_MOVE, t5.getBoard(), t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("h4")).getPiece(), GameUtils.getCoordinateAtPosition("g3"), t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("g4")).getPiece());
        final MoveTransition t6 = t5.getBoard().getCurrentPlayer().makeMove(m6);
        assertTrue(t6.getStatus().isDone());
        assertNull(t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("g4")).getPiece());
    }

    @Test
    public void testKingEquality() {
        final Board board = Board.createStandardBoard();
        final Board board2 = Board.createStandardBoard();
        assertEquals(board.getTile(60).getPiece(), board2.getTile(60).getPiece());
        assertFalse(board.getTile(60).getPiece().equals(null));
    }

    @Test
    public void testHashCode() {
        final Board board = Board.createStandardBoard();
        final Set<Piece> pieceSet = Sets.newHashSet(board.getAllPieces());
        final Set<Piece> whitePieceSet = Sets.newHashSet(board.getWhitePieces());
        final Set<Piece> blackPieceSet = Sets.newHashSet(board.getBlackPieces());
        assertTrue(pieceSet.size() == 32);
        assertTrue(whitePieceSet.size() == 16);
        assertTrue(blackPieceSet.size() == 16);
    }

}