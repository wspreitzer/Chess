package com.williamspreitzer.chess.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Tile;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveType;

public class Knight implements Piece {

	private final static int[] MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
	
	private int position;
	private Color color;
	private boolean isFirstMove;
	private int cachedHashCode;
	
	Knight(int position, Color color, boolean isFirstMove) {
		this.position = position;
		this.color = color;
		this.isFirstMove = isFirstMove;
		this.cachedHashCode = computeHashCode();
	}

	public boolean isFirstMove() {
		return false;
	}
	public Collection<Move> calculateLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<Move>();
		for(int currentCandidateOffset : MOVE_COORDINATES) {
			int destinationCoordinate = this.position;
			if(true) {
				if(isFirstColumnExclusion(this.position, currentCandidateOffset ) ||
				   isSecondColumnExclusion(this.position, currentCandidateOffset) ||
				   isSeventhColumnExclusion(this.position,currentCandidateOffset) ||
				   isEighthColumnExclusion(this.position, currentCandidateOffset)) {
					continue;
				}
				destinationCoordinate += currentCandidateOffset;
				if(GameUtils.isValidTileCoordinate(destinationCoordinate)) {
					Tile destinationTile = board.getTile(destinationCoordinate);
					if(!destinationTile.isTileOccupied()) {
						legalMoves.add(MoveFactory.createNonAttackingMove(MoveType.MAJOR_MOVE, board, this, destinationCoordinate));
					} else {
						Piece pieceAtDestination = destinationTile.getPiece();
						Color pieceColor = pieceAtDestination.getColor();
						if(this.getColor() != pieceColor ) {
							legalMoves.add(MoveFactory.createAttackMove(MoveType.MAJOR_ATTACK_MOVE, board, this, destinationCoordinate, pieceAtDestination));
						} 
					}
				} 
			}
		}
		return legalMoves;
	}
	
	public PieceType getType() {
		return PieceType.KNIGHT;
	}
	
	public Piece movePiece(Move move) {
		return PieceFactory.createPiece(move.getMovedPiece().getType(), move.getDestinationCoordinate(), move.getMovedPiece().getColor(), false);
	}
	
	@Override
	public String toString() {
		return PieceType.KNIGHT.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		Boolean retVal = null;
		if(this == other) {
			retVal = true;
		} else if( ! ( other instanceof Knight ) ) {
			retVal = false;
		} else {
			Knight otherKnight = (Knight) other;
			if (this.position == otherKnight.position && this.color == otherKnight.getColor()) {
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
		return GameUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
		candidateOffset == 6 || candidateOffset == 15);
	}
	
	private boolean isSecondColumnExclusion(int currentPosition, int candidateOffset) {
		return GameUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
	}
	
	private boolean isSeventhColumnExclusion(int currentPosition, int candidateOffset) {
		return GameUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
	}
	
	private boolean isEighthColumnExclusion(int currentPosition, int candidateOffset) {
		return GameUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 || candidateOffset == 10 || candidateOffset == 17);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getPosition() {
		return this.position;
	}

	@Override
	public Integer getPieceValue() {
		// TODO Auto-generated method stub
		return 0;
	}
}