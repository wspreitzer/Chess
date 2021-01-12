package com.williamspreitzer.chess.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveStatus;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.moves.NullMove;
import com.williamspreitzer.chess.piece.King;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.PieceType;

public abstract class Player {

	protected Board board;
	protected King playerKing;
	protected Collection<Move> playerLegalMoves;
	protected Collection<Move> opponentLegalMoves;
	private boolean isInCheck;
	
	public abstract Collection<Piece> getActivePieces();
	public abstract Color getColor();
	public abstract Player getOpponent();
	public abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegalMoves, Collection<Move> opponentLegalMoves);
	
	protected Player(Board board, Collection<Move> playerLegalMoves,
			Collection<Move> opponentLegalMoves ) {
		this.board = board;
		final Optional<King> kingOpt = Optional.ofNullable(this.establishKing());
		if (kingOpt.isPresent()) {
			this.playerKing = (King) kingOpt.get();
		} else {
			throw new RuntimeException("Not a valid board");
		}
		this.playerLegalMoves = ImmutableList.copyOf(Iterables.concat(playerLegalMoves, calculateKingCastles(playerLegalMoves,opponentLegalMoves)));
		this.opponentLegalMoves = opponentLegalMoves;
		this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPosition(), opponentLegalMoves).isEmpty();
	}
	
	public Collection<Move>  getPlayerLegalMoves() {
		return this.playerLegalMoves;
	}
	
	public boolean isMoveLegal(Move move) {
		return this.playerLegalMoves.contains(move);
	}
	
	public boolean isInCheck() {
		return this.isInCheck;
	}
	
	public boolean isInCheckMate() {
		return this.isInCheck && !hasEscapeMoves();
	}
	
	public boolean isInStaleMate() {
		return !this.isInCheck && !hasEscapeMoves();
	}
	
	public boolean isCastled() {
		return false;
	}
	
	public MoveTransition makeMoves(Move move) {
		MoveTransition retVal = null;
		if(!(move instanceof NullMove) ) {
			if(Player.calculateAttacksOnTile(move.getMovedPiece().getPosition(), move.getBoard().getCurrentPlayer().getOpponent().getPlayerLegalMoves()).isEmpty()) {
				Board transitionBoard = move.execute();
				retVal = new MoveTransition(transitionBoard, move, MoveStatus.DONE);
			} else {
				retVal = new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
			}
		} else {
			retVal = new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
		}
		return retVal;
	}
	
	public MoveTransition makeMove(Move move) {
		MoveTransition retVal = null;
		if(!isMoveLegal(move)) {
			retVal = new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
		} else {
			Board transitionBoard = move.execute();
			Collection<Move> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.getCurrentPlayer().
					getOpponent().getPlayerKing().getPosition(), transitionBoard.getCurrentPlayer().playerLegalMoves);
			if(!kingAttacks.isEmpty()) {
				retVal = new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
			} else {
				retVal = new MoveTransition(transitionBoard, move, MoveStatus.DONE);
			}
		}
		return retVal;
	}
	
	public Piece getPlayerKing() {
		return this.playerKing;
	}
	private King establishKing() {
		King retVal = null;
		for(Piece piece : getActivePieces() ) {
			if(piece.getType().equals(PieceType.KING)) {
				retVal = (King) piece;
				break;
			} else {
				continue;
			}
		}
		return retVal;
	}
	
	private  static Collection<Move> calculateAttacksOnTile(int position, Collection<Move> opponentMoves) {
		List<Move> attackMoves = new ArrayList<Move>();
		for(Move move : opponentMoves) {
			if(position == move.getDestinationCoordinate()) {
				attackMoves.add(move);
			}
		}
		return ImmutableList.copyOf(attackMoves);
	}
	
	private boolean hasEscapeMoves() {
		boolean retVal = false;
		for(Move move : this.playerLegalMoves) {
			MoveTransition transition = makeMove(move);
			if(transition.getStatus().isDone()) {
				retVal = true;
			}
		}
		return retVal;
	}
}