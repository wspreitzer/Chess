package com.williamspreitzer.chess.pgn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.williamspreitzer.chess.utils.GameUtils;

public class MySqlGamePersistence implements PGNPersistence {

	private static MySqlGamePersistence persistence;
	
	
	public static MySqlGamePersistence getPersistence() {
		if(persistence == null) {
			persistence = new MySqlGamePersistence();
		}
		return persistence;
	}
	
	private MySqlGamePersistence() {
		PGNPersistence.createGameTable(this.createDBConnection());
		PGNPersistence.createIndex("outcome", "OutcomeIndex", this.createDBConnection());
		PGNPersistence.createIndex("moves", "MoveIndex", this.createDBConnection());
	}
	
	private Connection createDBConnection() {
		Connection conn = null;
		try {
			Class.forName(GameUtils.props.getProperty("db.driver"));
			conn =  DriverManager.getConnection(GameUtils.props.getProperty("db.url"),
											   GameUtils.props.getProperty("db.user"), 
											   GameUtils.props.getProperty("db.pass"));
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return conn;
	}

	@Override
	public void persistGame(Game game) {
		final String gameSQLString = "INSERT INTO Game(id, outcome, moves) VALUES(?,?,?,);";
		PreparedStatement stmt = null;
		try {
			stmt =  createDBConnection().prepareStatement(gameSQLString);
			stmt.setInt(1,  PGNPersistence.getMaxGameRow(createDBConnection()) +1);
			stmt.setString(2,  game.getWinner());
			stmt.setString(3, game.getMoves().toString().replaceAll("\\[","").replaceAll("\\]",""));
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
				
			}
		}
	}
}