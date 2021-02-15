package com.williamspreitzer.chess.player.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.pgn.FenUtilities;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.utils.GameConstants;

public class TestPawnStructure {

	Board board;
	Builder builder;
	
	@BeforeEach
	private void setup() {
		this.board = Board.createStandardBoard();
		builder = new Builder();
		// Black Layout
		builder.setPiece(PieceFactory.createPiece(PieceType.KING, 4, Color.BLACK, false, null));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 8, Color.BLACK, true, null));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 12, Color.BLACK, true, null));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 16, Color.BLACK, false, null));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 20, Color.BLACK, false, null));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 28, Color.BLACK, false, null));

		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 52, Color.WHITE, true, null));
		builder.setPiece(PieceFactory.createPiece(PieceType.KING, 60, Color.WHITE, true, null));
		builder.setMoveMaker(Color.WHITE);
		
		System.out.println(FenUtilities.createFENFromGame(board));
	}
    @Test
    public void testIsolatedPawnsOnStandardBoard() {
        assertEquals(PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getWhitePlayer()), 0);
        assertEquals(PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getBlackPlayer()), 0);
    }

    @Test
    public void testIsolatedPawnByExample1() {
        board = FenUtilities.createGameFromFEN("r1bq1rk1/pp2bppp/1np2n2/6B1/3P4/1BNQ4/PP2NPPP/R3R1K1 b - - 0 13");
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getWhitePlayer()));
        assertEquals(0, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getBlackPlayer()));
    }

    @Test
    public void testIsolatedPawnByExample2() {
        board = FenUtilities.createGameFromFEN("r1bq1rk1/p3bppp/1np2n2/6B1/3P4/1BNQ4/PP2NPPP/R3R1K1 b - - 0 1");
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getWhitePlayer()));
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 2, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getBlackPlayer()));
    }

    @Test
    public void testIsolatedPawnByExample3() {
    	this.board = this.builder.build();
    	assertEquals(GameConstants.ISOLATED_PAWN_PENALTY, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getWhitePlayer()));
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 5, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getBlackPlayer()));
    }

    @Test
    public void testIsolatedPawnByExample4() {
        final Board board = FenUtilities.createGameFromFEN("4k3/2p1p1p1/8/8/8/8/2P1P1P1/4K3 w KQkq -");
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 3, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getWhitePlayer()));
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 3, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getBlackPlayer()));
        
        assertEquals(0, new StandardBoardEvaluator(board, 1).getEvaluator().evaluate(board, 1));
    }

    @Test
    public void testIsolatedPawnByExample5() {
        final Board board = FenUtilities.createGameFromFEN("6k1/p6p/8/8/8/8/P6P/6K1 b - - 0 1");
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 2, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getWhitePlayer()));
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 2, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getBlackPlayer()));
        assertEquals(0, new StandardBoardEvaluator(board, 1).getEvaluator().evaluate(board, 1));
    }

    @Test
    public void testIsolatedPawnByExample6() {
        final Board board = FenUtilities.createGameFromFEN("6k1/4p3/4p3/8/8/4P3/4P3/6K1 b - - 0 1");
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 2, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getWhitePlayer()));
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 2, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getBlackPlayer()));
        assertEquals(0, new StandardBoardEvaluator(board, 1).getEvaluator().evaluate(board, 1));
    }

    @Test
    public void testDoubledPawnByExample1() {
        assertEquals(0, PawnStructureAnalyzer.getPawnAnalyzer().doubledPawnPenalty(board.getWhitePlayer()));
        assertEquals(0, PawnStructureAnalyzer.getPawnAnalyzer().doubledPawnPenalty(board.getBlackPlayer()));
        assertEquals(0, new StandardBoardEvaluator(board, 1).getEvaluator().evaluate(board, 1));
    }

    @Test
    public void testDoubledPawnByExample2() {
        final Board board = FenUtilities.createGameFromFEN("6k1/4p3/4p3/8/8/4P3/4P3/6K1 b - - 0 1");
        assertEquals(GameConstants.DOUBLED_PAWN_PENALTY * 2, PawnStructureAnalyzer.getPawnAnalyzer().doubledPawnPenalty(board.getWhitePlayer()));
        assertEquals(GameConstants.DOUBLED_PAWN_PENALTY * 2, PawnStructureAnalyzer.getPawnAnalyzer().doubledPawnPenalty(board.getBlackPlayer()));
        assertEquals(0, new StandardBoardEvaluator(board, 1).getEvaluator().evaluate(board, 1));
    }

    @Test
    public void testDoubledPawnByExample3() {
        final Board board = FenUtilities.createGameFromFEN("6k1/8/8/P7/P7/P7/8/6K1 b - - 0 1");
        assertEquals(GameConstants.DOUBLED_PAWN_PENALTY * 3, PawnStructureAnalyzer.getPawnAnalyzer().doubledPawnPenalty(board.getWhitePlayer()));
        assertEquals(0, PawnStructureAnalyzer.getPawnAnalyzer().doubledPawnPenalty(board.getBlackPlayer()));
    }

    @Test
    public void testDoubledPawnByExample4() {
        final Board board = FenUtilities.createGameFromFEN("6k1/8/8/P6p/P6p/P6p/8/6K1 b - - 0 1");
        assertEquals(GameConstants.DOUBLED_PAWN_PENALTY * 3, PawnStructureAnalyzer.getPawnAnalyzer().doubledPawnPenalty(board.getWhitePlayer()));
        assertEquals(GameConstants.DOUBLED_PAWN_PENALTY * 3, PawnStructureAnalyzer.getPawnAnalyzer().doubledPawnPenalty(board.getBlackPlayer()));
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 3, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getWhitePlayer()));
        assertEquals(GameConstants.ISOLATED_PAWN_PENALTY * 3, PawnStructureAnalyzer.getPawnAnalyzer().isolatedPawnPenalty(board.getBlackPlayer()));
    }
}