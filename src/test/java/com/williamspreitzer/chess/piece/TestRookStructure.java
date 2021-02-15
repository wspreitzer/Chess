package com.williamspreitzer.chess.piece;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.player.ai.RookStructureAnalyzer;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestRookStructure {

    @Test
    public void test1() {
        final Board board = Board.createStandardBoard();
        assertEquals(0, RookStructureAnalyzer.getRookAnalyzer().rookStructureScore(board, board.getWhitePlayer()));
        assertEquals(0, RookStructureAnalyzer.getRookAnalyzer().rookStructureScore(board, board.getWhitePlayer()));
    }

    @Test
    public void test2() {
    	
        final Builder builder = new Builder();
        
        // Black Layout
        builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("a8"), Color.BLACK, false, null));
        builder.setPiece(PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e8"), Color.BLACK, false, false));
        
        // White Layout
        builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, GameUtils.getCoordinateAtPosition("h1"), Color.WHITE, false, null));
        builder.setPiece(PieceFactory.createPiece(PieceType.KING, GameUtils.getCoordinateAtPosition("e1"), Color.WHITE, false, false));
        builder.setMoveMaker(Color.WHITE);
        
        // Set the current player
        final Board board = builder.build();
        assertEquals(25, RookStructureAnalyzer.getRookAnalyzer().rookStructureScore(board, board.getWhitePlayer()));
        assertEquals(25, RookStructureAnalyzer.getRookAnalyzer().rookStructureScore(board, board.getWhitePlayer()));
    }
}