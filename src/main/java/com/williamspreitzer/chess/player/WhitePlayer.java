package com.williamspreitzer.chess.player;

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
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.Rook;

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
			if(GameUtils.isTilesEmpty(this.board, 61,62) && GameUtils.areTilesBeingAttacked(this.opponentLegalMoves, 61,62)) {
				final Rook rook = (Rook) this.board.getTile(63).getPiece();
				if(this.board.getTile(rook.getPosition()).isTileOccupied() && rook.isFirstMove()) {
					kingCastles.add(MoveFactory.createCastleMove(MoveType.KING_SIDE_CASTLE_MOVE, board, this.playerKing, 62, (Rook) rook, 61));
				}
			} else if(GameUtils.isTilesEmpty(this.board, 57,58,59) && GameUtils.areTilesBeingAttacked(this.opponentLegalMoves, 57,58,59)) {
				final Rook rook = (Rook) this.board.getTile(56).getPiece();
				if(this.board.getTile(rook.getPosition()).isTileOccupied() && rook.isFirstMove()) {
					kingCastles.add(MoveFactory.createCastleMove(MoveType.QUEEN_SIDE_CASTLE_MOVE, board, this.playerKing, 58, (Rook) rook, 59));
				}
			}
		}
		return ImmutableList.copyOf(kingCastles);
	}
}