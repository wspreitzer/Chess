package com.williamspreitzer.chess.utils;

import java.awt.Color;

public class GameConstants {

	public static final String HUMAN_TEXT = "Human";
	public static final String COMPUTER_TEXT = "Computer";
	public static final Color LIGHT_TILE_COLOR = Color.decode(GameUtils.props.getProperty("color.lightTile"));
	public static final Color DARK_TILE_COLOR = Color.decode(GameUtils.props.getProperty("color.darkTile"));
	public static final String NEXT_BEST_MOVE_QUERY = createSqlQuery();

	private static String createSqlQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT SUBSTR(g1.moves, LENGTH('%s') + %d, INSTR(SUBSTR(g1.moves, LENGTH('%s') + %d, LENGTH(g1.moves)), ',') - 1), ");
		sb.append("COUNT(*) FROM game g1 WHERE g1.move LIKE '%s%%' AND (outcome = '%S') GROUP BY substr(g1.moves, LENGTH('%s') + %d, ");
		sb.append("INSTR(substr(g1.moves, LENGTH('%s') + %d, LENGTH(g1.moves)), ',') -1) ORDER BY 2 DESC");
		return sb.toString();
	}
}
