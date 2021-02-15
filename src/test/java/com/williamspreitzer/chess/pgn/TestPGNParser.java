package com.williamspreitzer.chess.pgn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveTransition;

public class TestPGNParser {
	Board board;
	
	@BeforeEach
	private void setup() {
		board = Board.createStandardBoard();
	}
	/*
	 * Connection conn;
	 * 
	 * @BeforeEach private void setup() { try {
	 * Class.forName(GameUtils.props.getProperty("db.driver")); conn =
	 * DriverManager.getConnection(GameUtils.props.getProperty("db.url"),
	 * GameUtils.props.getProperty("db.user"),
	 * GameUtils.props.getProperty("db.pass")); } catch(SQLException sqle) {
	 * sqle.printStackTrace(); } catch(ClassNotFoundException cnfe) {
	 * cnfe.printStackTrace(); } }
	 * 
	 * @Test public void test1() throws IOException {
	 * doTest("com/chess/tests/pgn/t1.pgn"); }
	 * 
	 * @Test public void test2() throws IOException {
	 * doTest("com/chess/tests/pgn/t2.pgn"); }
	 * 
	 * @Test public void test3() throws IOException {
	 * doTest("com/chess/tests/pgn/t3.pgn"); }
	 * 
	 * @Test public void test4() throws IOException {
	 * doTest("com/chess/tests/pgn/t4.pgn"); }
	 * 
	 * @Test public void test5() throws IOException {
	 * doTest("com/chess/tests/pgn/smallerTest.pgn"); }
	 * 
	 * @Test public void test6() throws IOException {
	 * doTest("com/chess/tests/pgn/t6.pgn"); }
	 * 
	 * @Test public void test8() throws IOException {
	 * doTest("com/chess/tests/pgn/t8.pgn"); }
	 * 
	 * @Test public void test9() throws IOException {
	 * doTest("com/chess/tests/pgn/t9.pgn"); }
	 * 
	 * @Test public void testPawnPromotion() throws IOException {
	 * doTest("com/chess/tests/pgn/queenPromotion.pgn"); }
	 * 
	 * @Test public void test10() throws IOException {
	 * doTest("com/chess/tests/pgn/t10.pgn"); }
	 * 
	 * @Test public void test11() throws IOException {
	 * doTest("com/chess/tests/pgn/bigTest.pgn"); }
	 * 
	 * @Test public void test12() throws IOException {
	 * doTest("com/chess/tests/pgn/twic1047.pgn"); }
	 * 
	 * @Test public void test13() throws IOException {
	 * doTest("com/chess/tests/pgn/twic1046.pgn"); }
	 * 
	 * @Test public void test14() throws IOException {
	 * doTest("com/chess/tests/pgn/combined.pgn"); }
	 * 
	 * @Test public void test15() throws IOException {
	 * doTest("com/chess/tests/pgn/c2012.pgn"); }
	 * 
	 * @Test public void testMax() throws IOException { int maxId =
	 * PGNPersistence.getMaxGameRow(conn); System.out.println("max id = " +maxId); }
	 * 
	 * @Test public void testParens() throws ParsePGNException {
	 * 
	 * final String gameText = "(+)-(-) (+)-(-) 1. e4 e6"; final List<String> moves
	 * = PGNUtilities.processMoveText(gameText); assert(moves.size() == 2);
	 * 
	 * }
	 * 
	 * @Test public void testWithErol() throws IOException { final Board board =
	 * Board.createStandardBoard(); final Move move =
	 * PGNPersistence.queryBestMove(board, board.getCurrentPlayer(), "", conn);
	 * final MoveTransition moveTransition =
	 * board.getCurrentPlayer().makeMove(move); final Move move2 =
	 * PGNPersistence.queryBestMove(moveTransition.getBoard(),
	 * moveTransition.getBoard().getCurrentPlayer(), "e4", conn);
	 * System.out.println("move 2 = " +move2); }
	 * 
	 * private static void doTest(final String testFilePath) throws IOException {
	 * final URL url = Resources.getResource(testFilePath); final File testPGNFile =
	 * new File(url.getFile()); PGNUtilities.persistPGNFile(testPGNFile); }
	 */
    
	
	@Test
	public void testKING_SIDE_CASTLE_MOVE() {
		board = FenUtilities.createGameFromFEN("4k3/8/8/8/8/8/8/4K2R w K - 0 1");
		Move move = PGNUtilities.createMove(board, "O-O");
		long count = board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		assertEquals(1, count);
	}
	
	@Test
	public void testQUEEN_SIDE_CASTLE_MOVE() {
		board = FenUtilities.createGameFromFEN("4k3/8/8/8/8/8/8/R3K w Q - 0 1");
		Move move = PGNUtilities.createMove(board, "O-O-O");
		long count = board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		assertEquals(1, count);
	}
	
	@Test
	public void testPAWN_MOVE() {
		Move move = PGNUtilities.createMove(board, "e3");
		long count = board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		assertEquals(1, count);
	}
	
	@Test
	public void testPAWN_MOVE2() {
		Move move = PGNUtilities.createMove(board, "e4");
		long count = board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		assertEquals(1, count);
	}
	
	@Test
	public void testPAWN_ATTACK_MOVE_One_Move() {
		board = FenUtilities.createGameFromFEN("4k3/8/8/8/8/3q4/4P3/4K3 w - - 0 1");
		Move move = PGNUtilities.createMove(board, "exd3");
		long count = board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		assertEquals(1, count);
	}
	
	@Test
	public void testPAWN_ATTACK_MOVE_Two_Moves() {
		board = FenUtilities.createGameFromFEN("4k3/8/8/8/8/3q4/2P1P3/4K3 w - - 0 1");
		Move move = PGNUtilities.createMove(board, "exd3");
		Move move2 = PGNUtilities.createMove(board, "cxd3");
		long count = board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move2)).count();
		count += board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		assertEquals(2, count);
	}
	
	@Test
	public void testMAJOR_MOVE_One_Move() {
		board = Board.createStandardBoard();
		Move move = PGNUtilities.createMove(board, "Nf3");
		long count = board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		assertEquals(1, count);
	}
	
	@Test
	public void testMAJOR_MOVE_Two_Moves() {
		board = FenUtilities.createGameFromFEN("rnbqkb1r/pp2p2p/2p2ppP/3pP1N1/1n1P2P1/1BP5/PP3P2/RNBQK2R b KQkq - 0 1");
		Move move = PGNUtilities.createMove(board, "N4a6");
		Move move2= PGNUtilities.createMove(board, "N8a6");
		long count = board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move)).count();
		count += board.getCurrentPlayer().getPlayerLegalMoves().stream().filter(aMove -> aMove.equals(move2)).count();
		assertEquals(2, count);
	}
	
    @Test
    public void testCreateMovesFromPGN() {
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append("1. e4 Nf6 2. e5 Nd5 3. d4 c6 4. Nf3 g6 5. h4 d6 6. h5 Bg7 7. h6 Bf8 8. Be2 Bg4 9. Ng5 Bf5 10. g4 Bc8 "
    			+ "11. Bd3 Nb4 12. Bc4 d5 13. Bb3 f6 14. c3 N4a6 {Diagram [#]} 15. Nxh7 Rxh7 16. Bc2 Rh8 17. Bxg6+ Kd7 18. Qf3 fxe5 19. Qf5+ e6 ");
    	sb.append("20. Qxe5 Be7 21. g5 c5 22. Bf7 Bd6 23. Qg7 Kc6 24. Be3 cxd4 25. cxd4 Bf8 26. Qg6 Kb6 27. Nc3 Nc6 28. Na4+ Ka5 29. Qc2 Ncb4 30. Bd2 Qxg5 31. a3 Kb5 32. Be8+ 1-0");
    	try {
			List<String> pgnString = PGNUtilities.processMoveText(sb.toString());
			for(String aString : pgnString) {
				if(aString.equals("1-0")) {
					break;
				}
				if(aString.equals("N4a6")) {
					System.out.println(FenUtilities.createFENFromGame(board));
				}
				MoveTransition trans = board.getCurrentPlayer().makeMove(PGNUtilities.createMove(board, aString));
				assertTrue(trans.getStatus().isDone());
				board = trans.getBoard();
			}
		} catch (ParsePGNException e) {
			e.printStackTrace();
		}
    	String expected = "r1b1Bb1r/pp6/n3p2P/1k1p2q1/Nn1P4/P7/1PQB1P2/R3K2R b KQ - 0 1";
    	assertEquals(expected, FenUtilities.createFENFromGame(board));
    }
}
