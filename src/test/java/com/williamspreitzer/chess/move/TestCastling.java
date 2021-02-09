package com.williamspreitzer.chess.move;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

//import com.chess.engine.classic.player.ai.StockAlphaBeta;
//import com.chess.pgn.FenUtilities;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.piece.Rook;
import com.williamspreitzer.chess.player.ai.MoveStrategy;
import com.williamspreitzer.chess.utils.GameUtils;

public class TestCastling {

    @Test
    public void testWhiteKingSideCastle() {
        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e4")));
        assertTrue(t1.getStatus().isDone());
        
        final MoveTransition t2 = t1.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e5")));
        assertTrue(t2.getStatus().isDone());
        
        final MoveTransition t3 = t2.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t2.getBoard(), t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("g1")).getPiece(),
                        GameUtils.getCoordinateAtPosition("f3")));
        assertTrue(t3.getStatus().isDone());
        
        final MoveTransition t4 = t3.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t3.getBoard(), t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d6")));
        assertTrue(t4.getStatus().isDone());
        
        final MoveTransition t5 = t4.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(), t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("f1")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e2")));
        assertTrue(t5.getStatus().isDone());
        
        final MoveTransition t6 = t5.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t5.getBoard(), t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("d6")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d5")));
        assertTrue(t6.getStatus().isDone());
        
        final Move wm1 = MoveFactory
                .createCastleMove(MoveType.KING_SIDE_CASTLE_MOVE, t6.getBoard(), t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("e1")).getPiece(), 
                		GameUtils.getCoordinateAtPosition("g1"), (Rook) t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(), GameUtils.getCoordinateAtPosition("f1"));
        assertTrue(t6.getBoard().getCurrentPlayer().getPlayerLegalMoves().contains(wm1));
        
        final MoveTransition t7 = t6.getBoard().getCurrentPlayer().makeMove(wm1);
        assertTrue(t7.getStatus().isDone());
        assertTrue(t7.getBoard().getWhitePlayer().isCastled());
        assertFalse(t7.getBoard().getWhitePlayer().isKingSideCastleCapable());
        assertFalse(t7.getBoard().getWhitePlayer().isQueenSideCastleCapable());
    }

    @Test
    public void testWhiteQueenSideCastle() {
        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e4")));
        assertTrue(t1.getStatus().isDone());
        
        final MoveTransition t2 = t1.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e5")));
        assertTrue(t2.getStatus().isDone());
        
        final MoveTransition t3 = t2.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t2.getBoard(), t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("d2")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d3")));
        assertTrue(t3.getStatus().isDone());
        
        final MoveTransition t4 = t3.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t3.getBoard(), t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d6")));
        assertTrue(t4.getStatus().isDone());
        
        final MoveTransition t5 = t4.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(), t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("c1")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d2")));
        assertTrue(t5.getStatus().isDone());
        
        final MoveTransition t6 = t5.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t5.getBoard(), t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("d6")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d5")));
        assertTrue(t6.getStatus().isDone());
        
        final MoveTransition t7 = t6.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t6.getBoard(), t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("d1")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e2")));
        assertTrue(t7.getStatus().isDone());
        
        final MoveTransition t8 = t7.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t7.getBoard(), t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("h7")).getPiece(),
                        GameUtils.getCoordinateAtPosition("h6")));
        assertTrue(t8.getStatus().isDone());
        
        final MoveTransition t9 = t8.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t8.getBoard(), t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("b1")).getPiece(),
                        GameUtils.getCoordinateAtPosition("c3")));
        assertTrue(t9.getStatus().isDone());
        
        final MoveTransition t10 = t9.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t9.getBoard(), t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("h6")).getPiece(),
                        GameUtils.getCoordinateAtPosition("h5")));
        assertTrue(t10.getStatus().isDone());
        
        final Move wm1 = MoveFactory
                .createCastleMove(MoveType.QUEEN_SIDE_CASTLE_MOVE, t10.getBoard(), t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("e1")).getPiece(), 
                		GameUtils.getCoordinateAtPosition("e1"), (Rook) t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece(), GameUtils.getCoordinateAtPosition("d1"));
        assertTrue(t10.getBoard().getCurrentPlayer().getPlayerLegalMoves().contains(wm1));
        
        final MoveTransition t11 = t10.getBoard().getCurrentPlayer().makeMove(wm1);
        assertTrue(t11.getStatus().isDone());
        assertTrue(t11.getBoard().getWhitePlayer().isCastled());
        assertFalse(t11.getBoard().getWhitePlayer().isKingSideCastleCapable());
        assertFalse(t11.getBoard().getWhitePlayer().isQueenSideCastleCapable());
    }

    @Test
    public void testBlackKingSideCastle() {
        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board, board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e4")));
        assertTrue(t1.getStatus().isDone());
        
        final MoveTransition t2 = t1.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e5")));
        assertTrue(t2.getStatus().isDone());
        
        final MoveTransition t3 = t2.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t2.getBoard(), t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("d2")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d3")));
        assertTrue(t3.getStatus().isDone());
        
        final MoveTransition t4 = t3.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t3.getBoard(), t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("g8")).getPiece(),
                        GameUtils.getCoordinateAtPosition("f6")));
        assertTrue(t4.getStatus().isDone());
        
        final MoveTransition t5 = t4.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t4.getBoard(), t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("d3")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d4")));
        assertTrue(t5.getStatus().isDone());
        
        final MoveTransition t6 = t5.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t5.getBoard(), t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("f8")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e7")));
        assertTrue(t6.getStatus().isDone());
        
        final MoveTransition t7 = t6.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t6.getBoard(), t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("d4")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d5")));
        assertTrue(t7.getStatus().isDone());
        
        final Move wm1 = MoveFactory
                .createCastleMove(MoveType.KING_SIDE_CASTLE_MOVE, t7.getBoard(), t7.getBoard().getTile(GameUtils.getCoordinateAtPosition(
                        "e8")).getPiece(), GameUtils.getCoordinateAtPosition("g8"), (Rook) t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece(), GameUtils.getCoordinateAtPosition("f8"));
        assertTrue(t7.getBoard().getCurrentPlayer().getPlayerLegalMoves().contains(wm1));
        
        final MoveTransition t8 = t7.getBoard().getCurrentPlayer().makeMove(wm1);
        assertTrue(t8.getStatus().isDone());
        assertTrue(t8.getBoard().getBlackPlayer().isCastled());
        assertFalse(t8.getBoard().getBlackPlayer().isKingSideCastleCapable());
        assertFalse(t8.getBoard().getBlackPlayer().isQueenSideCastleCapable());
    }

    @Test
    public void testBlackQueenSideCastle() {
        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board, 
                		board.getTile(GameUtils.getCoordinateAtPosition("e2")).getPiece(), GameUtils.getCoordinateAtPosition("e4")));
        assertTrue(t1.getStatus().isDone());
        
        final MoveTransition t2 = t1.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, t1.getBoard(), t1.getBoard().getTile(GameUtils.getCoordinateAtPosition("e7")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e5")));
        assertTrue(t2.getStatus().isDone());
        
        final MoveTransition t3 = t2.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t2.getBoard(), t2.getBoard().getTile(GameUtils.getCoordinateAtPosition("d2")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d3")));
        assertTrue(t3.getStatus().isDone());
        
        final MoveTransition t4 = t3.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t3.getBoard(), t3.getBoard().getTile(GameUtils.getCoordinateAtPosition("d8")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e7")));
        assertTrue(t4.getStatus().isDone());
        
        final MoveTransition t5 = t4.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t4.getBoard(), t4.getBoard().getTile(GameUtils.getCoordinateAtPosition("b1")).getPiece(),
                        GameUtils.getCoordinateAtPosition("c3")));
        assertTrue(t5.getStatus().isDone());
        
        final MoveTransition t6 = t5.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t5.getBoard(), t5.getBoard().getTile(GameUtils.getCoordinateAtPosition("b8")).getPiece(),
                        GameUtils.getCoordinateAtPosition("c6")));
        assertTrue(t6.getStatus().isDone());
        
        final MoveTransition t7 = t6.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t6.getBoard(), t6.getBoard().getTile(GameUtils.getCoordinateAtPosition("c1")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d2")));
        assertTrue(t7.getStatus().isDone());
        
        final MoveTransition t8 = t7.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, t7.getBoard(), t7.getBoard().getTile(GameUtils.getCoordinateAtPosition("d7")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d6")));
        assertTrue(t8.getStatus().isDone());
        
        final MoveTransition t9 = t8.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t8.getBoard(), t8.getBoard().getTile(GameUtils.getCoordinateAtPosition("f1")).getPiece(),
                        GameUtils.getCoordinateAtPosition("e2")));
        assertTrue(t9.getStatus().isDone());
        
        final MoveTransition t10 = t9.getBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t9.getBoard(), t9.getBoard().getTile(GameUtils.getCoordinateAtPosition("c8")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d7")));
        assertTrue(t10.getStatus().isDone());
        
        final MoveTransition t11 = t10.getBoard()
                .getCurrentPlayer()
                .makeMove(
                        MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, t10.getBoard(), t10.getBoard().getTile(GameUtils.getCoordinateAtPosition("g1")).getPiece(),
                                GameUtils.getCoordinateAtPosition("f3")));
        assertTrue(t11.getStatus().isDone());
        
        final Move wm1 = MoveFactory
                .createCastleMove(MoveType.QUEEN_SIDE_CASTLE_MOVE, t11.getBoard(), 
                		t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("e8")).getPiece(), 
                		GameUtils.getCoordinateAtPosition("c8"),
                		(Rook) t11.getBoard().getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece(),
                        GameUtils.getCoordinateAtPosition("d8"));
                        
        assertTrue(t11.getBoard().getCurrentPlayer().getPlayerLegalMoves().contains(wm1));
        
        final MoveTransition t12 = t11.getBoard().getCurrentPlayer().makeMove(wm1);
        assertTrue(t12.getStatus().isDone());
        assertTrue(t12.getBoard().getBlackPlayer().isCastled());
        assertFalse(t12.getBoard().getBlackPlayer().isKingSideCastleCapable());
        assertFalse(t12.getBoard().getBlackPlayer().isQueenSideCastleCapable());
    }

	/*
	 * @Test public void testCastleBugOne() { final Board board =
	 * Board.createStandardBoard(); final MoveTransition t1 =
	 * board.getCurrentPlayer() .makeMove(MoveFactory.createNonAttackingMove(board,
	 * GameUtils.getCoordinateAtPosition("e2"),
	 * GameUtils.getCoordinateAtPosition("e4")));
	 * assertTrue(t1.getStatus().isDone()); final MoveTransition t2 = t1.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t1.getBoard(),
	 * GameUtils.getCoordinateAtPosition("d7"),
	 * GameUtils.getCoordinateAtPosition("d5")));
	 * assertTrue(t2.getStatus().isDone()); final MoveTransition t3 = t2.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t2.getBoard(),
	 * GameUtils.getCoordinateAtPosition("e4"),
	 * GameUtils.getCoordinateAtPosition("e5")));
	 * assertTrue(t3.getStatus().isDone()); final MoveTransition t4 = t3.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t3.getBoard(),
	 * GameUtils.getCoordinateAtPosition("c8"),
	 * GameUtils.getCoordinateAtPosition("f5")));
	 * assertTrue(t4.getStatus().isDone()); final MoveTransition t5 = t4.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t4.getBoard(),
	 * GameUtils.getCoordinateAtPosition("f1"),
	 * GameUtils.getCoordinateAtPosition("d3")));
	 * assertTrue(t5.getStatus().isDone()); final MoveTransition t6 = t5.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t5.getBoard(),
	 * GameUtils.getCoordinateAtPosition("f5"),
	 * GameUtils.getCoordinateAtPosition("d3")));
	 * assertTrue(t6.getStatus().isDone()); final MoveTransition t7 = t6.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t6.getBoard(),
	 * GameUtils.getCoordinateAtPosition("c2"),
	 * GameUtils.getCoordinateAtPosition("d3")));
	 * assertTrue(t7.getStatus().isDone()); final MoveTransition t8 = t7.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t7.getBoard(),
	 * GameUtils.getCoordinateAtPosition("e7"),
	 * GameUtils.getCoordinateAtPosition("e6")));
	 * assertTrue(t8.getStatus().isDone()); final MoveTransition t9 = t8.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t8.getBoard(),
	 * GameUtils.getCoordinateAtPosition("d1"),
	 * GameUtils.getCoordinateAtPosition("a4")));
	 * assertTrue(t9.getStatus().isDone()); final MoveTransition t10 = t9.getBoard()
	 * .getCurrentPlayer()
	 * .makeMove(MoveFactory.createNonAttackingMove(t9.getBoard(),
	 * GameUtils.getCoordinateAtPosition("d8"),
	 * GameUtils.getCoordinateAtPosition("d7")));
	 * assertTrue(t10.getStatus().isDone()); final MoveTransition t11 =
	 * t10.getBoard() .getCurrentPlayer() .makeMove(
	 * MoveFactory.createNonAttackingMove(t10.getBoard(),
	 * GameUtils.getCoordinateAtPosition("b1"),
	 * GameUtils.getCoordinateAtPosition("c3")));
	 * assertTrue(t11.getStatus().isDone());
	 * 
	 * 
	 * final MoveStrategy moveStrategy = new StockAlphaBeta(6);
	 * 
	 * moveStrategy.execute(t11.getBoard());
	 * 
	 * }
	 */
	/*
	 * @Test public void testNoCastlingOutOfCheck() { final Board board =
	 * FenUtilities.
	 * createGameFromFEN("r3k2r/1pN1nppp/p3p3/3p4/8/8/PPPK1PPP/R6R b kq - 1 18");
	 * final Move illegalCastleMove = MoveFactory .createMove(board,
	 * GameUtils.getCoordinateAtPosition("e8"),
	 * GameUtils.getCoordinateAtPosition("c8")); final MoveTransition t1 =
	 * board.getCurrentPlayer() .makeMove(illegalCastleMove);
	 * assertFalse(t1.getStatus().isDone()); }
	 */

}
