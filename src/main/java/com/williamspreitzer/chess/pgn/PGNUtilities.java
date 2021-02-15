package com.williamspreitzer.chess.pgn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.utils.GameUtils;

public class PGNUtilities {

	private static final Pattern PGN_PATTERN = Pattern.compile("\\[(\\w+)\\s+\"(.*?)\"\\]$");
    private static final Pattern KING_SIDE_CASTLE = Pattern.compile("O-O#?\\+?");
    private static final Pattern QUEEN_SIDE_CASTLE = Pattern.compile("O-O-O#?\\+?");
    private static final Pattern PAWN_MOVE = Pattern.compile("^([a-h][0-8])(\\+)?(#)?$");
    private static final Pattern PAWN_ATTACK_MOVE = Pattern.compile("(^[a-h])(x)([a-h][0-8])(\\+)?(#)?$");
    private static final Pattern MAJOR_MOVE = Pattern.compile("^(B|N|R|Q|K)([a-h]|[1-8])?([a-h][0-8])(\\+)?(#)?$");
    private static final Pattern MAJOR_ATTACK_MOVE = Pattern.compile("^(B|N|R|Q|K)([a-h]|[1-8])?(x)([a-h][0-8])(\\+)?(#)?$");
    private static final Pattern PAWN_PROMOTION_MOVE = Pattern.compile("(.*?)=(.*?)");
    private static final Pattern PAWN_ATTACK_PROMOTION_MOVE = Pattern.compile("(.*?)x(.*?)=(.*?)");

	private PGNUtilities() {
	}

	public static void persistPGNFile(final File pgnFile) {
		int count = 0;
		int validCount = 0;
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(pgnFile));
			PGNGameTags.TagsBuilder tagsBuilder = new PGNGameTags.TagsBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					if (isTag(line)) {
						final Matcher matcher = PGN_PATTERN.matcher(line);
						if (matcher.find()) {
							tagsBuilder.addTag(matcher.group(1), matcher.group(2));
						}
					} else if (isEndOfGame(line)) {
						final String[] ending = line.split(" ");
						final String outcome = ending[ending.length - 1];
						sb.append(line.replace(outcome, "")).append(" ");
						final String gameText = sb.toString().trim();
						if (!gameText.isEmpty() && gameText.length() > 80) {
							final Game game = GameFactory.createGame(tagsBuilder.build(), gameText, outcome);
							System.out
									.println("(" + (++count) + ") Finished parsing " + game + " count = " + (++count));
							sb.setLength(0);
							sb.append("(");
							sb.append(++count);
							sb.append(") Finished parsing ");
							sb.append(game);
							sb.append(" count = ");
							sb.append(++count);
							System.out.println(sb.toString());

							if (game.isValid()) {
								MySqlGamePersistence.getPersistence().persistGame(game);
								validCount++;
							}
						}
						sb.setLength(0);
						tagsBuilder = new PGNGameTags.TagsBuilder();
					} else {
						sb.append(line).append(" ");
					}
				}
			}
			br.readLine();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		sb.setLength(0);
		sb.append("Finished building book from PGN File: ");
		sb.append(pgnFile);
		sb.append(" Parsed ");
		sb.append(count);
		sb.append(" games, valid = ");
		sb.append(validCount);
		System.out.println(sb.toString());
	}

	private static boolean isEndOfGame(String gameText) {
		return gameText.endsWith("1-0") || gameText.endsWith("0-1") || gameText.endsWith("1/2-1/2")
				|| gameText.endsWith("*");
	}

	private static boolean isTag(String gameText) {
		return gameText.startsWith("[") && gameText.endsWith("]");
	}

	public static List<String> processMoveText(final String gameText) throws ParsePGNException {
		return gameText.isEmpty() ? Collections.emptyList() : createMovesFromPGN(gameText);
	}

	private static List<String> createMovesFromPGN(final String pgnText) {
		ImmutableList.Builder<String> moves = null;

		if (!pgnText.startsWith("1.")) {
			return Collections.emptyList();
		} else {
			final List<String> sanitizedMoves = new LinkedList<String>(
					Arrays.asList(removeParenthesis(pgnText).replaceAll(Pattern.quote("$") + "[0-9]+", "")
							.replaceAll("[0-9]+\\s*\\.\\.\\.", "").split("\\s*[0-9]+" + Pattern.quote("."))));
			final List<String> processedData = removeEmptyText(sanitizedMoves);
			final String[] moveRows = processedData.toArray(new String[processedData.size()]);
			moves = new Builder<String>();
			for (final String row : moveRows) {
				final String[] moveContent = removeWhiteSpace(row).split(" ");
				switch (moveContent.length) {
				case 1:
					moves.add(moveContent[0]);
					break;
				case 2:
					moves.add(moveContent[0]);
					moves.add(moveContent[1]);
					break;
				default:
					return Collections.emptyList();
				}
			}
		}
		return moves.build();
	}

	private static String removeParenthesis(String gameText) {
		int parenthesisCounter = 0;
		final StringBuffer sb = new StringBuffer();
		for (final char c : gameText.toCharArray()) {
			if (c == '(' || c == '{') {
				parenthesisCounter++;
			}
			if (c == ')' || c == '}') {
				parenthesisCounter--;
			}
			if (!(c == '(' || c == '{' || c == ')' || c == '}') && parenthesisCounter == 0) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	private static String removeWhiteSpace(String row) {
		return row.trim().replaceAll("\\s+", " ");
	}

	private static List<String> removeEmptyText(List<String> moves) {
		final List<String> retVal = new ArrayList<String>();
		for (final String moveText : moves) {
			if (!moveText.isEmpty()) {
				retVal.add(moveText);
			}
		}
		return retVal;
	}

	public static Move createMove(final Board board, final String pgnText) {
		final Matcher kingSideCastleMatcher = KING_SIDE_CASTLE.matcher(pgnText);
		final Matcher queenSideCastleMatcher = QUEEN_SIDE_CASTLE.matcher(pgnText);
		final Matcher pawnMoveMatcher = PAWN_MOVE.matcher(pgnText);
		final Matcher pawnAttackMoveMatcher = PAWN_ATTACK_MOVE.matcher(pgnText);
		final Matcher pawnPromotionMoveMatcher = PAWN_PROMOTION_MOVE.matcher(pgnText);
		final Matcher pawnAttackPromotionMatcher = PAWN_ATTACK_PROMOTION_MOVE.matcher(pgnText);
		final Matcher majorMoveMatcher = MAJOR_MOVE.matcher(pgnText);
		final Matcher majorAttackMoveMatcher = MAJOR_ATTACK_MOVE.matcher(pgnText);
		Move retVal = null;

		int currentCoordinate;
		int destinationCoordinate;

		if (kingSideCastleMatcher.matches()) {
			retVal = extractCastleMove(board, "O-O");
		} else if (queenSideCastleMatcher.matches()) {
			retVal = extractCastleMove(board, "O-O-O");
		} else if (pawnMoveMatcher.matches()) {
			final String destinationSquare = pawnMoveMatcher.group(1);
			destinationCoordinate = GameUtils.getCoordinateAtPosition(destinationSquare);
			currentCoordinate = deriveCurrentCoordinate(board, "P", destinationSquare, "");

			if (Math.abs(destinationCoordinate - currentCoordinate) == 8) {
				retVal = MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, board,
						board.getTile(currentCoordinate).getPiece(), destinationCoordinate);
			} else {
				retVal = MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board,
						board.getTile(currentCoordinate).getPiece(), destinationCoordinate);
			}
		} else if (pawnAttackMoveMatcher.matches()) {
			final String destinationSquare = pawnAttackMoveMatcher.group(3);
			destinationCoordinate = GameUtils.getCoordinateAtPosition(destinationSquare);
			final String disambiguationFile = pawnAttackMoveMatcher.group(1) != null ? pawnAttackMoveMatcher.group(1)
					: "";
			currentCoordinate = deriveCurrentCoordinate(board, "P", destinationSquare, disambiguationFile);
			retVal = MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, board,
					board.getTile(currentCoordinate).getPiece(), destinationCoordinate,
					board.getTile(destinationCoordinate).getPiece());

		} else if (pawnAttackPromotionMatcher.matches()) {
			final String destinationSquare = pawnAttackPromotionMatcher.group(2);
			final String disambiguationFile = pawnAttackPromotionMatcher.group(1) != null
					? pawnAttackPromotionMatcher.group(1)
					: "";
			destinationCoordinate = GameUtils.getCoordinateAtPosition(destinationSquare);
			currentCoordinate = deriveCurrentCoordinate(board, "P", destinationSquare, disambiguationFile);
			retVal = MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_PROMOTION_MOVE, board,
					board.getTile(currentCoordinate).getPiece(), destinationCoordinate,
					board.getTile(destinationCoordinate).getPiece());

		} else if (pawnPromotionMoveMatcher.find()) {
			final String destinationSquare = pawnPromotionMoveMatcher.group(1);
			destinationCoordinate = GameUtils.getCoordinateAtPosition(destinationSquare);
			currentCoordinate = deriveCurrentCoordinate(board, "P", destinationSquare, "");
			return MoveFactory.createNonAttackingMove(MoveType.PAWN_PROMOTION_MOVE, board,
					board.getTile(currentCoordinate).getPiece(), destinationCoordinate);

		} else if (majorMoveMatcher.find()) {
			final String destinationSquare = majorMoveMatcher.group(3);
			destinationCoordinate = GameUtils.getCoordinateAtPosition(destinationSquare);
			final String disambiguationFile = majorMoveMatcher.group(2) != null ? majorMoveMatcher.group(2) : "";
			currentCoordinate = deriveCurrentCoordinate(board, majorMoveMatcher.group(1), destinationSquare,
					disambiguationFile);
			retVal = MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board,
					board.getTile(currentCoordinate).getPiece(), destinationCoordinate);

		} else if (majorAttackMoveMatcher.find()) {
			final String destinationSquare = majorAttackMoveMatcher.group(4);
			destinationCoordinate = GameUtils.getCoordinateAtPosition(destinationSquare);
			final String disambiguationFile = majorAttackMoveMatcher.group(2) != null ? majorAttackMoveMatcher.group(2)
					: "";
			currentCoordinate = deriveCurrentCoordinate(board, majorAttackMoveMatcher.group(1), destinationSquare,
					disambiguationFile);
			retVal = MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, board,
					board.getTile(currentCoordinate).getPiece(), destinationCoordinate,
					board.getTile(destinationCoordinate).getPiece());

		} else {
			retVal = MoveFactory.createNonAttackingMove(MoveType.NULL_MOVE, board, null, 0);
		}

		return retVal;
	}

	private static Move extractCastleMove(final Board board, final String castleMove) {
		Move retVal = null;
		for (final Move move : board.getCurrentPlayer().getPlayerLegalMoves()) {
			if (move.isCastle() && move.toString().equals(castleMove)) {
				retVal = move;
			} else {
				retVal = MoveFactory.createNonAttackingMove(MoveType.NULL_MOVE, board, null, 0);
			}
		}
		return retVal;
	}

	private static int deriveCurrentCoordinate(final Board board, final String movedPiece,
			final String destinationSquare, final String disambiguationFile) throws RuntimeException {
		final List<Move> currentCandidates = new ArrayList<Move>();
		final int destinationCoordinate = GameUtils.getCoordinateAtPosition(destinationSquare);
		int retVal;
		for (final Move move : board.getCurrentPlayer().getPlayerLegalMoves()) {
			if (move.getDestinationCoordinate() == destinationCoordinate
					&& move.getMovedPiece().toString().equals(movedPiece)) {
				currentCandidates.add(move);
			}
		}
		switch (currentCandidates.size()) {
		case 0:
			retVal = -1;
			break;
		case 1:
			retVal = currentCandidates.iterator().next().getCurrentCoordinate();
			break;
		default:
			retVal = extractFurther(currentCandidates, movedPiece, disambiguationFile);
		}
		return retVal;
	}

	private static int extractFurther(final List<Move> candidateMoves, final String movedPiece,
			final String disambiguationFile) {
		final List<Move> currentCandidates = new ArrayList<Move>();
		int retVal;
		for (final Move move : candidateMoves) {
			if (move.getMovedPiece().getType().toString().equals(movedPiece)) {
				currentCandidates.add(move);
			}
		}

		switch (currentCandidates.size()) {
		case 1:
			retVal = currentCandidates.iterator().next().getCurrentCoordinate();
			break;
		default:
			final List<Move> candidatesRefined = new ArrayList<Move>();
			for (final Move move : currentCandidates) {
				final String pos = GameUtils.getPositionAtCoordinate(move.getCurrentCoordinate());
				if (pos.contains(disambiguationFile)) {
					candidatesRefined.add(move);
				}
			}
			if (candidatesRefined.size() == 1) {
				retVal = candidatesRefined.iterator().next().getCurrentCoordinate();
			} else {
				retVal = -1;
			}
		}
		return retVal;
	}
}