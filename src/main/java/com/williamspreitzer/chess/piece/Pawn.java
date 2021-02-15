package com.williamspreitzer.chess.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Tile;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.utils.GameUtils;

public class Pawn implements Piece {

	private final static int[] MOVE_COORDINATES = { 7, 8, 9, 16 };
	int position;
	Color color;
	boolean isFirstMove;
	private int cachedHashCode;

	Pawn(int position, Color color, boolean isFirstMove) {
		this.position = position;
		this.color = color;
		this.isFirstMove = isFirstMove;
		this.cachedHashCode = computeHashCode();
	}

	public boolean isFirstMove() {
		return isFirstMove;
	}

	public void setFirstMove(boolean isFirstMove) {
		this.isFirstMove = isFirstMove;
	}

	public Color getColor() {
		return this.color;
	}

	public int getPosition() {
		return this.position;
	}

	public Collection<Move> calculateLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<Move>();
		for (int currentCandidateOffset : MOVE_COORDINATES) {
			int destinationCoordinate = this.position + (this.getColor().getDirection() * currentCandidateOffset);
			if (!GameUtils.isValidTileCoordinate(destinationCoordinate)) {
				continue;
			}
			Tile destinationTile = board.getTile(destinationCoordinate);
			if (!destinationTile.isTileOccupied()) {
				switch (currentCandidateOffset) {
				case 7:
					if (board.getEnPassantPawn() != null) {
						addEnPassantAttackMove(board, legalMoves, destinationCoordinate, currentCandidateOffset);
					}
					break;
				case 8:
					if (this.color.isPawnPromotionTile(destinationCoordinate)) {
						legalMoves.add(MoveFactory.createNonAttackingMove(MoveType.PAWN_PROMOTION_MOVE, board, this,
								destinationCoordinate));
					} else {
						legalMoves.add(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, board, this,
								destinationCoordinate));
					}
					break;
				case 9:
					if (board.getEnPassantPawn() != null) {
						addEnPassantAttackMove(board, legalMoves, destinationCoordinate, currentCandidateOffset);
					}
					break;
				case 16:
					Tile jumpedTile = board.getTile(this.position + (this.color.getDirection() * 8));
					if ((this.isFirstMove && !jumpedTile.isTileOccupied()) && 
							(GameUtils.SEVENTH_RANK.get(this.position) && this.color.isBlack() || GameUtils.SECOND_RANK.get(this.position) && this.color.isWhite())) {
						legalMoves.add(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board, this,
								destinationCoordinate));
					}
					break;
				default:
					continue;
				}
			} else {
				if (this.color != destinationTile.getPiece().getColor()) {
					switch (currentCandidateOffset) {
					case 7:
						switch (this.color) {
						case BLACK:
							if (!GameUtils.FIRST_COLUMN.get(this.position)) {
								if (Color.BLACK.isPawnPromotionTile(destinationCoordinate)) {
									legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_PROMOTION_MOVE,
											board, this, destinationCoordinate, destinationTile.getPiece()));
								} else {
									legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, board, this,
											destinationCoordinate, destinationTile.getPiece()));
								}
								break;
							}
							break;
						case WHITE:
							if (!GameUtils.EIGHTH_COLUMN.get(this.position)) {
								if (Color.WHITE.isPawnPromotionTile(destinationCoordinate)) {
									legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_PROMOTION_MOVE,
											board, this, destinationCoordinate, destinationTile.getPiece()));
								} else {
									legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, board, this,
											destinationCoordinate, destinationTile.getPiece()));
								}
							}
							break;
						default:
							break;
						}
						break;
					case 9:
						switch (this.color) {
						case BLACK:
							if (!GameUtils.EIGHTH_COLUMN.get(this.position)) {
								if (Color.BLACK.isPawnPromotionTile(destinationCoordinate)) {
									legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_PROMOTION_MOVE,
											board, this, destinationCoordinate, destinationTile.getPiece()));
								} else {
									legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, board, this,
											destinationCoordinate, destinationTile.getPiece()));
								}
								break;
							}
							break;
						case WHITE:
							if (!GameUtils.FIRST_COLUMN.get(this.position)) {
								if (Color.WHITE.isPawnPromotionTile(destinationCoordinate)) {
									legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_PROMOTION_MOVE,
											board, this, destinationCoordinate, destinationTile.getPiece()));
								} else {
									legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, board, this,
											destinationCoordinate, destinationTile.getPiece()));
								}
							}
							break;
						default:
							break;
						}
						break;
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}

	public PieceType getType() {
		return PieceType.PAWN;
	}

	public Piece movePiece(Move move) {
		return PieceFactory.createPiece(move.getMovedPiece().getType(), move.getDestinationCoordinate(),
				move.getMovedPiece().getColor(), false, null);
	}

	@Override
	public String toString() {
		return PieceType.PAWN.toString();
	}

	@Override
	public boolean equals(Object other) {
		Boolean retVal = null;
		if (this == other) {
			retVal = true;
		} else if (!(other instanceof Pawn)) {
			retVal = false;
		} else {
			Pawn otherPawn = (Pawn) other;
			if (this.position == otherPawn.position && this.color == otherPawn.getColor()) {
				retVal = true;
			} else {
				retVal = false;
			}
		}
		return retVal.booleanValue();
	}

	@Override
	public int hashCode() {
		return this.cachedHashCode;
	}

	private int computeHashCode() {
		int result = getType().hashCode();
		result = 31 * result + this.color.hashCode();
		result = 31 * result + this.position;
		result = 31 * result + (isFirstMove ? 1 : 0);
		return result;
	}

	@Override
	public Integer getPieceValue() {
		// TODO Auto-generated method stub
		return PieceType.PAWN.getPieceValue();
	}

	private void addEnPassantAttackMove(Board board, List<Move> legalMoves, int destinationCoordinate, int offset) {
		switch (offset) {
		case 7:
			if (board.getEnPassantPawn().getPosition() == this.position + (this.color.getOppositeDirection())) {
				final Piece pieceOnCandidate = board.getEnPassantPawn();
				if (this.color != pieceOnCandidate.getColor()) {
					legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_EN_PASSANT_ATTACK_MOVE, board, this,
							destinationCoordinate, pieceOnCandidate));
				}
			}
			break;
		case 9:
			if (board.getEnPassantPawn().getPosition() == this.position + (this.color.getDirection())) {
				final Piece pieceOnCandidate = board.getEnPassantPawn();
				if (this.color != pieceOnCandidate.getColor()) {
					legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_EN_PASSANT_ATTACK_MOVE, board, this,
							destinationCoordinate, pieceOnCandidate));
				}
			}
			break;
		}
	}

}