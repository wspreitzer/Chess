package com.williamspreitzer.chess.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveType;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.Rook;
import com.williamspreitzer.chess.utils.GameUtils;

public class BlackPlayer extends Player {

	public BlackPlayer(Board gameBoard, Collection<Move> playerLegalMoves,
			Collection<Move> opponentLegalMoves) {
		super(gameBoard, playerLegalMoves, opponentLegalMoves);
	}

	@Override
	public Collection<Piece> getActivePieces() {
		return this.board.getBlackPieces();
	}

	@Override
	public Color getColor() {
		return Color.BLACK;
	}

	@Override
	public Player getOpponent() {
		return this.board.getWhitePlayer();
	}

	@Override
	public Collection<Move> calculateKingCastles(Collection<Move> playerLegalMoves,
			Collection<Move> opponentLegalMoves) {
		List<Move> kingCastles = new ArrayList<Move>();
		if(this.playerKing.isFirstMove() && !this.isInCheck()) {
			if(GameUtils.isTilesEmpty(this.board, 1,2,3) && !GameUtils.areTilesBeingAttacked(opponentLegalMoves, 1, 2, 3)) {
				final Piece queenRook = this.board.getTile(GameUtils.getCoordinateAtPosition("a8")).getPiece();
				if(queenRook instanceof Rook && queenRook.isFirstMove()) {
						kingCastles.add(MoveFactory.createCastleMove(MoveType.QUEEN_SIDE_CASTLE_MOVE, board, this.playerKing, 2, (Rook) queenRook, 3));
				}
			} 
			if(GameUtils.isTilesEmpty(this.board, 5,6) && !GameUtils.areTilesBeingAttacked(opponentLegalMoves, 5, 6)) {
				final Piece kingRook = this.board.getTile(GameUtils.getCoordinateAtPosition("h8")).getPiece();
				if(kingRook instanceof Rook && kingRook.isFirstMove()) {
						kingCastles.add(MoveFactory.createCastleMove(MoveType.KING_SIDE_CASTLE_MOVE, board, this.playerKing, 6, (Rook) kingRook, 5));
				}
			}
		}
		return ImmutableList.copyOf(kingCastles);
	}
}