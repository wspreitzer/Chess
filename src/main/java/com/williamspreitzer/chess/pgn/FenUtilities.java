package com.williamspreitzer.chess.pgn;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Board.Builder;
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.utils.GameUtils;

public class FenUtilities {

	private FenUtilities() {}
	
	public static Board createGameFromFEN(final String fenString) {
		return parseFEN(fenString);
	}
	
	public static String createFENFromGame(final Board board) {
		StringBuffer sb = new StringBuffer();
		sb.append(calculateBoardText(board));
		sb.append(" ");
		sb.append(calculateCurrentPlayerText(board));
		sb.append(" ");
		sb.append(calculateCastleText(board));
		sb.append(" ");
		sb.append(calculateEnPassantSquare(board));
		sb.append(" ");
		sb.append("0 1");
		return sb.toString();
	}
	
	private static Board parseFEN(final String fenString) {
		final String[] fenPartitions = fenString.trim().split( " ");
		final Builder builder = new Builder();
		final boolean whiteKingSideCastle = whiteKingSideCastle(fenPartitions[2]);
		final boolean whiteQueenSideCastle = whiteQueenSideCastle(fenPartitions[2]);
		final boolean blackKingSideCastle = blackKingSideCastle(fenPartitions[2]);
		final boolean blackQueenSideCastle = blackQueenSideCastle(fenPartitions[2]);
		final String gameConfig = fenPartitions[0];
		final char[] boardTiles = gameConfig.replaceAll("/", "")
				.replaceAll("8", "--------")
				.replaceAll("7", "-------")
				.replaceAll("6", "------")
				.replaceAll("5", "-----")
				.replaceAll("4", "----")
				.replaceAll("3", "---")
				.replaceAll("2", "--")
				.replaceAll("1", "-")
				.toCharArray();
		int i = 0;
		while(i < boardTiles.length) {
			switch(boardTiles[i]) {
			case 'r':
				builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, i, Color.BLACK, true));
				i++;
				break;
			case 'n':
				builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, i, Color.BLACK, true));
				i++;
				break;
			case 'b':
				builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, i, Color.BLACK, true));
				i++;
				break;
			case 'k':
				final boolean isCastled = !blackKingSideCastle && !blackQueenSideCastle;
				builder.setPiece(PieceFactory.createPiece(PieceType.KING, i, Color.BLACK, true));
				i++;
				break;
			case 'q':
				builder.setPiece(PieceFactory.createPiece(PieceType.QUEEN, i, Color.BLACK, true));
				i++;
				break;
			case 'p':
				builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, i, Color.BLACK, true));
				i++;
				break;
			case 'R':
				builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, i, Color.WHITE, true));
				i++;
				break;
			case 'N':
				builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, i, Color.BLACK, true));
				i++;
				break;
			case 'B':
				builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, i, Color.BLACK, true));
				i++;
				break;
			case 'K':
				final boolean isCastledW = !whiteKingSideCastle && !whiteQueenSideCastle;
				builder.setPiece(PieceFactory.createPiece(PieceType.KING, i, Color.BLACK, true));
				i++;
				break;
			case 'Q':
				builder.setPiece(PieceFactory.createPiece(PieceType.QUEEN, i, Color.BLACK, true));
				i++;
				break;
			case 'P':
				builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, i, Color.BLACK, true));
				i++;
				break;
			case '-':
				i++;
				break;
			default: 
				throw new RuntimeException("Invalid FEN String" + gameConfig);
			}
		}
		builder.setMoveMaker(moveMaker(fenPartitions[1]));
		return builder.build();
	}
	
	private static Color moveMaker(final String moveMakerString) {
		Color retVal = null;
		if(moveMakerString.equals("w")) {
			retVal = Color.WHITE;
		} else {
			retVal = Color.BLACK;
		}
		return retVal;
	}
	
	private static boolean whiteKingSideCastle(final String fenCastleString) {
		return fenCastleString.contains("K");
	}
	
	private static boolean whiteQueenSideCastle(final String fenCastleString) {
		return fenCastleString.contains("Q");
	}
	
	private static boolean blackKingSideCastle(final String fenCastleString) {
		return fenCastleString.contains("k");
	}
	
	private static boolean blackQueenSideCastle(final String fenCastleString) {
		return fenCastleString.contains("q");
	}
	
	private static String calculateCastleText(final Board board) {
		final StringBuffer sb = new StringBuffer();
		if(board.getWhitePlayer().isKingSideCastleCapable()) {
			sb.append("K");
		}
		if(board.getWhitePlayer().isQueenSideCastleCapable()) {
			sb.append("Q");
		}
		if(board.getBlackPlayer().isKingSideCastleCapable()) {
			sb.append("k");
		}
		if(board.getBlackPlayer().isQueenSideCastleCapable()) {
			sb.append("q");
		}
		
		return sb.toString().isEmpty() ? "-" : sb.toString();
	}
	
	private static String calculateEnPassantSquare(final Board board) {
		final Pawn enPassantPawn = board.getEnPassantPawn();
		String retVal;
		if(enPassantPawn != null) {
			retVal = GameUtils.getPositionAtCoordinate(enPassantPawn.getPosition() + 8 * enPassantPawn.getColor().getOppositeDirection());
		} else {
			retVal = "-";
		}
		
		return retVal;
	}
	
	private static String calculateBoardText(final Board board) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i< GameUtils.NUM_TILES; i++ ) {
			final String tileText = board.getTile(i).getPiece() == null ? "-" :
				board.getTile(i).getPiece().getColor().isWhite() ? board.getTile(i).getPiece().toString() :
					board.getTile(i).getPiece().toString().toLowerCase();
			sb.append(tileText);
		}
		sb.insert(8, "/");
		sb.insert(17, "/");
		sb.insert(26, "/");
		sb.insert(35, "/");
		sb.insert(44, "/");
		sb.insert(53, "/");
		sb.insert(62, "/");
		return sb.toString()
				.replaceAll("--------", "8")
				.replaceAll("-------", "7")
				.replaceAll("------", "6")
		        .replaceAll("-----","5")
		        .replaceAll("----", "4")
		        .replaceAll("---", "3")
		        .replaceAll("--", "2")
		        .replaceAll("-", "1");
	}
	
	private static String calculateCurrentPlayerText(final Board board) {
		return board.getCurrentPlayer().toString().substring(0,1).toLowerCase();
	}
}