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
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.piece.Rook;
import com.williamspreitzer.chess.utils.GameUtils;

public class WhitePlayer extends Player {
	public WhitePlayer(Board board, Collection<Move> playerLegalMoves,
			Collection<Move> opponentLegalMoves) {
		super(board, playerLegalMoves, opponentLegalMoves);
	}

	@Override
	public Collection<Piece> getActivePieces() {
		return this.board.getWhitePieces();
	}

	@Override
	public Color getColor() {
		return Color.WHITE;
	}

	@Override
	public Player getOpponent() {
		return this.board.getBlackPlayer();
	}
	
	@Override
	public Collection<Move> calculateKingCastles(Collection<Move> playerLegalMoves, Collection<Move> opponentLegalMoves) {
		final List<Move> kingCastles = new ArrayList<Move>();
		if(this.playerKing.isFirstMove() && !this.isInCheck()) {
			if(GameUtils.isTilesEmpty(this.board, 61,62) && !GameUtils.areTilesBeingAttacked(opponentLegalMoves, 61,62)) {
				Piece kingRook = board.getTile(GameUtils.getCoordinateAtPosition("h1")).getPiece();
				if( kingRook instanceof Rook && kingRook.isFirstMove() ) {
						kingCastles.add(MoveFactory.createCastleMove(MoveType.KING_SIDE_CASTLE_MOVE, board, this.playerKing, 62, (Rook) kingRook, 61));
				}
			} 
			if(GameUtils.isTilesEmpty(this.board, 57,58,59) && !GameUtils.areTilesBeingAttacked(opponentLegalMoves, 57,58,59)) {
				Piece queenRook = board.getTile(GameUtils.getCoordinateAtPosition("a1")).getPiece();
				if( queenRook instanceof Rook && queenRook.isFirstMove()) {
					kingCastles.add(MoveFactory.createCastleMove(MoveType.QUEEN_SIDE_CASTLE_MOVE, board, this.playerKing, GameUtils.getCoordinateAtPosition("c1"), (Rook) queenRook, GameUtils.getCoordinateAtPosition("d1")));
				}
			}
		}
		return ImmutableList.copyOf(kingCastles);
	}
}