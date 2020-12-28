package com.williamspreitzer.chess.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Tile;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveType;

public class Pawn implements Piece{

	private final static int[] MOVE_COORDINATES = {7, 8, 9, 16};
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

	public Color getColor() {
		return this.color;
	}

	public int getPosition() {
		return this.position;
	}

	public Collection<Move> calculateLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<Move>();
		for(int currentCandidateOffset : MOVE_COORDINATES) {
			int destinationCoordinate = this.position + (this.getColor().getDirection() * currentCandidateOffset);
			if( !GameUtils.isValidTileCoordinate(destinationCoordinate)) {
				continue;
			}
			Tile destinationTile = board.getTile(destinationCoordinate);
			if(!destinationTile.isTileOccupied()) {
				switch(currentCandidateOffset) {
				case 8:
					legalMoves.add(MoveFactory.createNonAttackingMove(MoveType.PAWN_MOVE, board, this, destinationCoordinate));
					break;
				case 16:
					Tile jumpedTile = board.getTile(this.position + (this.color.getDirection() * 8));
					if( this.isFirstMove && (!jumpedTile.isTileOccupied()) &&
					  ( GameUtils.SECOND_ROW[this.position] && 
							  this.getColor().isBlack()) || 
					  ( GameUtils.SEVENTH_ROW[this.position] && 
							  this.getColor().isWhite())) {
						legalMoves.add(MoveFactory.createNonAttackingMove(MoveType.PAWN_JUMP_MOVE, board, this, destinationCoordinate));
					}
					break;
				default:
				}
			} else {
				if( this.color != destinationTile.getPiece().getColor()) {
					switch(currentCandidateOffset) {
					case 7:
						switch(this.color) {
						case BLACK:
							if( !GameUtils.FIRST_COLUMN[this.position] ) {
								legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, board, this, destinationCoordinate, destinationTile.getPiece()));
							}
							break;
						case WHITE:
							if ( !GameUtils.EIGHTH_COLUMN[this.position] ) {
								legalMoves.add(MoveFactory.createAttackMove(
										MoveType.PAWN_ATTACK_MOVE, board, this, destinationCoordinate, destinationTile.getPiece()));
							}
							break;
						}
						break;
					case 9:
						switch(this.color) {
						case BLACK:
							if( !GameUtils.EIGHTH_COLUMN[this.position] ) {
								legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, board, this, destinationCoordinate, destinationTile.getPiece()));
							}
							break;
						case WHITE:
							if( !GameUtils.FIRST_COLUMN[this.position] ) {
								legalMoves.add(MoveFactory.createAttackMove(MoveType.PAWN_ATTACK_MOVE, board, this, destinationCoordinate, destinationTile.getPiece()));
							}
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
		return PieceFactory.createPiece(move.getMovedPiece().getType(), move.getDestinationCoordinate(), move.getMovedPiece().getColor());
	}
	
	@Override
	public String toString() {
		return PieceType.PAWN.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		Boolean retVal = null;
		if(this == other) {
			retVal = true;
		} else if( ! ( other instanceof Pawn ) ) {
			retVal = false;
		} else {
			Pawn otherPawn = (Pawn) other;
			if (this.position == otherPawn.position && this.color == otherPawn.getColor()) {
				retVal = true;
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
	
}