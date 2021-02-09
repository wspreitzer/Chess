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
				final Rook rook = (Rook) this.board.getTile(0).getPiece();
				if(rook != null) {
					if(this.board.getTile(rook.getPosition()).isTileOccupied() && rook.isFirstMove()) {
						kingCastles.add(MoveFactory.createCastleMove(MoveType.QUEEN_SIDE_CASTLE_MOVE, board, this.playerKing, 2, (Rook) rook, 3));
					}
				}
			} else if(GameUtils.isTilesEmpty(this.board, 5,6) && !GameUtils.areTilesBeingAttacked(opponentLegalMoves, 5, 6)) {
				final Rook rook = (Rook) this.board.getTile(7).getPiece();
				if(rook != null) {
					if(this.board.getTile(rook.getPosition()).isTileOccupied() && rook.isFirstMove()) {
						kingCastles.add(MoveFactory.createCastleMove(MoveType.KING_SIDE_CASTLE_MOVE, board, this.playerKing, 6, rook, 5));
					}
				}
			}
		}
		return ImmutableList.copyOf(kingCastles);
	}
}