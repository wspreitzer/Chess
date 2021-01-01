package com.williamspreitzer.chess.moves;

import java.util.ArrayList;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.Rook;

public class MoveFactory {

	private static Move retMove = null;
	
	public static NonAttackingMoves createNonAttackingMove(MoveType type, Board board, Piece movedPiece, int destinationCoordinate) {
		switch(type) {
		case MAJOR_MOVE:
			retMove = new MajorMove(board, movedPiece, destinationCoordinate);
			break;
		case PAWN_JUMP_MOVE:
			retMove = new PawnJumpMove(board, movedPiece, destinationCoordinate);
			break;
		case PAWN_MOVE:
			retMove = new PawnMove(board, movedPiece, destinationCoordinate);
			break;
		case NULL_MOVE:
			retMove = new NullMove(board, movedPiece, destinationCoordinate);
			break;
		default:
			break;
		}
		return (NonAttackingMoves) retMove;
	}
	
	public static AttackMove createAttackMove(MoveType type, Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
		switch(type) {
		case PAWN_ATTACK_MOVE:
			retMove = new PawnAttackMove(board, movedPiece, destinationCoordinate, attackedPiece);
			break;
		case MAJOR_ATTACK_MOVE:
			retMove = new MajorAttackMove(board, movedPiece, destinationCoordinate, attackedPiece);
			break;
		case PAWN_EN_PASSANT_ATTACK_MOVE:
			retMove = new PawnEnPassantAttackMove(board, movedPiece, destinationCoordinate, attackedPiece);
			break;
		default:
			break;
		}
		return (AttackMove) retMove;		
	}
	
	public static CastleMove createCastleMove(MoveType type, Board board, Piece movedPiece,
			                                  int kingDestinationCoordinate, Rook rook, 
			                                  int rookDestinationCoordinate) {
		switch(type) {
		case KING_SIDE_CASTLE_MOVE:
			retMove = new KingSideCastleMove(board, movedPiece, kingDestinationCoordinate, rook, rookDestinationCoordinate);
			break;
		case QUEEN_SIDE_CASTLE_MOVE:
			retMove = new QueenSideCastleMove(board, movedPiece, kingDestinationCoordinate, rook, rookDestinationCoordinate);
			break;
		default:
			break;
		}
		return (CastleMove) retMove;	
	}
	
	public static Move createMove(final Board board, 
			                       final int currentCooridnate,
			                       final int destinationCooridnate) {
		Move retVal = null;
		for(final Move move : board.getAllLegalMoves()) {
			
			if(move.getCurrentCoordinate() == currentCooridnate && 
					move.getDestinationCoordinate() == destinationCooridnate) {
				retVal = move;
				break;
			}
		}
		
		if ( retVal == null ) {
			retVal = MoveFactory.createNonAttackingMove(MoveType.NULL_MOVE, null, null, -1);
		}
		
		return retVal;
	}
}