package com.williamspreitzer.chess.pgn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.pgn.FenUtilities;
import com.williamspreitzer.chess.utils.GameUtils;


public class TestFENParser {

    @Test
    public void testWriteFEN1() throws IOException {
        final Board board = Board.createStandardBoard();
        final String fenString = FenUtilities.createFENFromGame(board);
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", fenString);
    }

    @Test
    public void testWriteFEN2() throws IOException {
        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e4")));
        assertTrue(t1.getStatus().isDone());
        final String fenString = FenUtilities.createFENFromGame(t1.getBoard());
        assertEquals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", fenString);
        final MoveTransition t2 = t1.getBoard().getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("c7")).getPiece(),
                        GameUtils.getCoordinateAtPosition("c5")));
        assertTrue(t2.getStatus().isDone());
        final String fenString2 = FenUtilities.createFENFromGame(t2.getBoard());
        assertEquals("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 1",fenString2);
    }
}