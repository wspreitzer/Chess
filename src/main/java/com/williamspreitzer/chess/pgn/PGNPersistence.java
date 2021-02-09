package com.williamspreitzer.chess.pgn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.utils.GameConstants;
import com.williamspreitzer.chess.utils.GameUtils;

public interface PGNPersistence {

	abstract void persistGame(Game game);

	static Move queryBestMove(final Board board, final Player player, final String gameText, Connection conn) {
		String bestMove = "";
		String count = "0";
		Statement stmt = null;
		Move retVal = null;
		try {
			final int offset = gameText.isEmpty() ? 1 : 3;
			final String sqlString = String.format(GameConstants.NEXT_BEST_MOVE_QUERY, gameText, offset, gameText,
					offset, gameText, player.getColor().name(), gameText, offset, gameText, offset);
			System.out.println(sqlString);
			stmt = conn.createStatement();
			stmt.execute(sqlString);
			final ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				bestMove = rs.getString(1);
				count = rs.getString(2);
				StringBuffer sb = new StringBuffer();
				sb.append("Selected book move = ");
				sb.append(bestMove);
				sb.append(" with ");
				sb.append(count);
				sb.append(" hits");
				retVal = PGNUtilities.createMove(board, bestMove);
			} 
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
		return retVal;
	}
	
	static void createGameTable(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(GameUtils.props.getProperty("db.createTable"));
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch(SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}
	
	static void createIndex(final String columnName, final String indexName, Connection conn) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_CATALOG = 'def' AND ");
		sb.append("TABLE_SCHEMA = DATABASE() AND TABLE_NAME = \"game\" AND INDEX_NAME = \"");
		sb.append(indexName);
		sb.append("\"");
		Statement stmt = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(sb.toString());
			rs = stmt.getResultSet();
			if(!rs.isBeforeFirst()) {
				stmt2 = conn.createStatement();
				sb.setLength(0);
				sb.append("CREATE INDEX ");
				sb.append(indexName);
				sb.append("on Game(");
				sb.append(columnName);
		        stmt2.execute(sb.toString());
			}
		} catch (SQLException sqle) {
			if(stmt != null) {
				try {
					stmt.close();
					if(stmt2 != null) {
						stmt2.close();
					}
				} catch(SQLException sqle2) {
					sqle2.printStackTrace();
				}
			}
		}
	}

	static int getMaxGameRow(Connection conn) {
		final String sqlString = "SELECT MAX(ID) FROM Game";
		int maxId = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(sqlString);
			rs = stmt.getResultSet();
			if(rs.next()) {
				maxId = rs.getInt(1);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if(stmt != null) {
				try 
				{
					rs.close();
					stmt.close();
					conn.close();
				} catch(SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
		return maxId;
	}
}