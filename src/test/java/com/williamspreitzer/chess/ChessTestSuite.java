package com.williamspreitzer.chess;

import org.junit.platform.suite.api.SelectClasses;

import com.williamspreitzer.chess.board.TestBoard;
import com.williamspreitzer.chess.gui.GUITest;
import com.williamspreitzer.chess.pgn.PGNTestSuite;
import com.williamspreitzer.chess.player.TestPlayer;
import com.williamspreitzer.chess.player.ai.AITestSuite;

/*@Suite.SuiteClasses({TestPieces.class,
                     TestBoard.class,
                     TestStaleMate.class,
                     TestPlayer.class,
                     TestCheckmate.class,
                     TestMiniMax.class,
                     TestAlphaBeta.class,
                     TestCastling.class,
                     TestPawnStructure.class,
                     TestFENParser.class,
                     TestEngine.class
                     TestPGNParser.class})*/

@SelectClasses({TestBoard.class, 
				GUITest.class, 
				MoveTestSuite.class, 
				PGNTestSuite.class, 
				TestPlayer.class, 
				AITestSuite.class})

public class ChessTestSuite {
}
