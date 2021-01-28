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

public class Bishop implements Piece {

	private final static int[] MOVE_COORDINATES = { -9, -7, 7, 9 };
	private int position;
	private Color color;
	private int cachedHashCode;
	private boolean isFirstMove;
	
	Bishop(int position, Color color, boolean isFirstMove) {
		this.position = position;
		this.color = color;
		this.isFirstMove = isFirstMove;
		this.cachedHashCode = computeHashCode();
	}
	
	public Color getColor() {
		return this.color;
	}

	public int getPosition() {
		return this.position;
	}

	public boolean isFirstMove() {
		return false;
	}
	
	public Collection<Move> calculateLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<Move>();
		for(int currentCandidateOffset : MOVE_COORDINATES) {
			int destinationCoordinate = this.position;
			while( GameUtils.isValidTileCoordinate(destinationCoordinate)) {
				if(isFirstColumnExclusion(destinationCoordinate, currentCandidateOffset) || 
						isEighthColumnExclusion(destinationCoordinate, currentCandidateOffset)) {
					break;
				}
				destinationCoordinate += currentCandidateOffset;
				if(GameUtils.isValidTileCoordinate(destinationCoordinate)) {
					Tile destinationTile = board.getTile(destinationCoordinate);
					if(!destinationTile.isTileOccupied()) {
						legalMoves.add(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board, this, destinationCoordinate));
					} else {
						Piece destinationPiece = destinationTile.getPiece();
						if(this.color != destinationPiece.getColor()) {
							legalMoves.add(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, board, this, destinationCoordinate, destinationPiece));
						} else {
							break;
						}
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	public PieceType getType() {
		return PieceType.BISHOP;
	}
	
	public Piece movePiece(Move move) {
		return PieceFactory.createPiece(move.getMovedPiece().getType(), move.getDestinationCoordinate(), move.getMovedPiece().getColor(), false);
	}

	@Override
	public String toString() {
		return PieceType.BISHOP.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		Boolean retVal = null;
		if(this == other) {
			retVal = true;
		} else if( ! ( other instanceof Bishop ) ) {
			retVal = false;
		} else {
			Bishop otherBishop = (Bishop) other;
			if (this.position == otherBishop.position && this.color == otherBishop.getColor()) {
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
	
	public void setFirstMove(boolean isFirstMove) {
		this.isFirstMove = isFirstMove;
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

	@Override
	public Integer getPieceValue() {
		// TODO Auto-generated method stub
		return PieceType.BISHOP.getPieceValue();
	}
}