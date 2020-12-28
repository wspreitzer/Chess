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

public class King implements Piece{

	private static final int[] MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};
	private int position;
	private Color color;
	private boolean isFirstMove;
	private int cachedHashCode;
	
	King(int position, Color color) {
		this.position = position;
		this.color = color;
		this.isFirstMove = false;
		this.cachedHashCode = this.computeHashCode();
		
	}
	
	public Color getColor() {
		return color;
	}

	public int getPosition() {
		return position;
	}
	
	public boolean isFirstMove() {
		return this.isFirstMove;
	}

	public Collection<Move> calculateLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<Move>();
		for(int currentCoordinate : MOVE_COORDINATES) {
			int destinationCoordinate = this.position;
			if(isFirstColumnExclusion(destinationCoordinate, currentCoordinate) ||
			   isEighthColumnExclusion(destinationCoordinate, currentCoordinate)) {
				break;
			}
			
			destinationCoordinate += currentCoordinate;
			if (GameUtils.isValidTileCoordinate(destinationCoordinate)) {
				Tile destinationTile = board.getTile(destinationCoordinate);
				if(!destinationTile.isTileOccupied()) {
					legalMoves.add(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board, this, destinationCoordinate));
				} else {
					Piece attackedPiece = destinationTile.getPiece();
					if(this.color != attackedPiece.getColor()) {
						legalMoves.add(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, board, this, destinationCoordinate, attackedPiece));
					} else {
						break;
					}
				}
				break;
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	public PieceType getType() {
		return PieceType.KING;
	}
	
	@Override
	public String toString() {
		return PieceType.KING.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		Boolean retVal = null;
		if(this == other) {
			retVal = true;
		} else if( ! ( other instanceof King ) ) {
			retVal = false;
		} else {
			King otherKing = (King) other;
			if (this.position == otherKing.position && this.color == otherKing.getColor()) {
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
		result = 31 * result + (isFirstMove ? 1 : 0 );
		return result;
	}
	
	private boolean isFirstColumnExclusion(int currentPosition, int candidateOffset) {
		return GameUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
	}
	
	private boolean isEighthColumnExclusion(int currentPosition, int candidateOffset) {
		return GameUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
	}

	public Piece movePiece(Move move) {
		return PieceFactory.createPiece(move.getMovedPiece().getType(), move.getDestinationCoordinate(), move.getMovedPiece().getColor());
	}
}