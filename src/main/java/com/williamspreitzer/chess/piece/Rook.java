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

public class Rook implements Piece{
	
	private final static int[] MOVE_COORDINATES = { -8, -1, 1, 8 };
	private int position;
	private Color color;
	private boolean isFirstMove;
	private int cachedHashCode;
	
	Rook(int position, Color color, boolean isFirstMove) {
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
		return this.isFirstMove;
	}
	
	public void setFirstMove(boolean isFirstMove) {
		this.isFirstMove = isFirstMove;
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
				if( GameUtils.isValidTileCoordinate(destinationCoordinate)) {
					Tile destinationTile = board.getTile(destinationCoordinate);
					if(!destinationTile.isTileOccupied()) {
						legalMoves.add(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board, this, destinationCoordinate));
					} else {
						Piece destinationPiece = destinationTile.getPiece();
						if(this.color != destinationPiece.getColor()) {
							legalMoves.add(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, board, this, destinationCoordinate, destinationPiece));
							break;
						} else {
							break;
						}
					}
				} else {
					break;
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	public PieceType getType() {
		return PieceType.ROOK;
	}
	
	public Piece movePiece(Move move) {
		return PieceFactory.createPiece(move.getMovedPiece().getType(), move.getDestinationCoordinate(), move.getMovedPiece().getColor(), false, null);
	}
	
	@Override
	public String toString() {
		return PieceType.ROOK.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		Boolean retVal = null;
		if(this == other) {
			retVal = true;
		} else if( ! ( other instanceof Rook ) ) {
			retVal = false;
		} else {
			Rook otherRook = (Rook) other;
			if (this.position == otherRook.position && this.color == otherRook.getColor()) {
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
		result = 31 * result + (isFirstMove ? 1 : 0 );
		return result;
	}
	
	private boolean isFirstColumnExclusion(int currentPosition, int candidateOffset) {
		return GameUtils.FIRST_COLUMN.get(currentPosition) && (candidateOffset == -1);
	}
	
	private boolean isEighthColumnExclusion(int currentPosition, int candidateOffset) {
		return GameUtils.EIGHTH_COLUMN.get(currentPosition) && (candidateOffset == 1);
	}

	@Override
	public Integer getPieceValue() {
		// TODO Auto-generated method stub
		return PieceType.ROOK.getPieceValue();
	}
}