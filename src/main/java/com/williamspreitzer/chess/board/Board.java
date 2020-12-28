package com.williamspreitzer.chess.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.williamspreitzer.chess.Color;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.piece.Pawn;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.player.BlackPlayer;
import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.player.WhitePlayer;

public class Board {

	private List<Tile> gameBoard;
	private Collection<Piece> whitePieces;
	private Collection<Piece> blackPieces;
	private WhitePlayer whitePlayer;
	private BlackPlayer blackPlayer;
	private Player currentPlayer;
	
	private Board(Builder builder) {
		this.gameBoard = createGameBoard(builder);
		this.whitePieces = calculateActivePieces(this.gameBoard, Color.WHITE);
		this.blackPieces = calculateActivePieces(this.gameBoard, Color.BLACK);
		
		Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(whitePieces);
		Collection<Move> blackStandardLegalMoves = calculateLegalMoves(blackPieces);
		this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
		this.blackPlayer = new BlackPlayer(this, blackStandardLegalMoves, whiteStandardLegalMoves);
		this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
	}
	
	private static Collection<Piece> calculateActivePieces(List<Tile> gameBoard, Color color) {
		List<Piece> activePieces = new ArrayList<Piece>();
		for(Tile tile : gameBoard) {
			if(tile.isTileOccupied()) {
				Piece piece = tile.getPiece();
				if(piece.getColor() == color) {
					activePieces.add(piece);
				}
			}
		}
		return ImmutableList.copyOf(activePieces);
	}
	
	public Tile getTile(int tileCoordinate) {
		return gameBoard.get(tileCoordinate);
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	private static List<Tile> createGameBoard(Builder builder) {
		Tile[] tiles = new Tile[GameUtils.NUM_TILES];
		for (int i = 0; i < GameUtils.NUM_TILES; i++ ) {
			tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
		}
		return ImmutableList.copyOf(tiles);
	}
	
	private Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {
		List<Move> legalMoves = new ArrayList<Move>();
		for(Piece piece : pieces) {
			legalMoves.addAll(piece.calculateLegalMoves(this));
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < GameUtils.NUM_TILES; i++ ) {
			String tileText = this.gameBoard.get(i).toString();
			builder.append(String.format("%3s", tileText));
			if( ( i + 1 ) % GameUtils.NUM_TILES_PER_ROW == 0 ) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}
	
	public Collection<Piece> getWhitePieces() {
		return this.whitePieces;
	}
	
	public Collection<Piece> getBlackPieces() {
		return this.blackPieces;
	}
	
	public Player getWhitePlayer() {
		return this.whitePlayer;
	}
	
	public Player getBlackPlayer() {
		return this.blackPlayer;
	}
	
	public static Board createStandardBoard() {
		Builder builder = new Builder();
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 0, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 1, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 2, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.QUEEN, 3, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.KING, 4, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 5, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 6, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 7, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 8, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 9, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 10, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 11, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 12, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 13, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 14, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 15, Color.BLACK));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 48, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 49, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 50, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 51, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 52, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 53, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 54, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.PAWN, 55, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 56, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 57, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 58, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.QUEEN, 59, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.KING, 60, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.BISHOP, 61, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.KNIGHT, 62, Color.WHITE));
		builder.setPiece(PieceFactory.createPiece(PieceType.ROOK, 63, Color.WHITE));
		builder.setMoveMaker(Color.WHITE);
		return builder.build();
	}
	
	public Iterable<Move> getAllLegalMoves() {
		return Iterables.unmodifiableIterable(Iterables.concat(this.whitePlayer.getPlayerLegalMoves(), this.blackPlayer.getPlayerLegalMoves()));
	}
	
	public static class Builder {
		Map<Integer, Piece> boardConfig;
		Color nextMoveMaker;
		Pawn enPassantPawn;
		
		public Builder() {
			this.boardConfig = new HashMap<Integer, Piece>();
		}
		
		public Builder setPiece(Piece piece) {
			this.boardConfig.put(piece.getPosition(), piece);
			return this;
		}
		
		public Builder setMoveMaker(Color nextMoveMaker) {
			this.nextMoveMaker = nextMoveMaker;
			return this;
		}
		public void setEnPassantPawn(Pawn enPassantPawn) {
			this.enPassantPawn = enPassantPawn;
		}
		public Board build() {
			return new Board(this);
		}
	}	
}
