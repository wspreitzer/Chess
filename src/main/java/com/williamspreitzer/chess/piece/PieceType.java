package com.williamspreitzer.chess.piece;

public enum PieceType {
	ROOK("R", 500) {
		@Override
		public boolean isRook() {
			return true;
		}

	},
	KNIGHT("N", 300) {
		@Override
		public boolean isRook() {
			return false;
		}
	},

	BISHOP("B", 300) {
		@Override
		public boolean isRook() {
			return false;
		}
	},

	QUEEN("Q", 900) {
		@Override
		public boolean isRook() {
			return false;
		}
	},

	KING("K", 10000) {
		@Override
		public boolean isRook() {
			return false;
		}
	},

	PAWN("P", 100) {
		@Override
		public boolean isRook() {
			return false;
		}
	},

	EnPassantPawn("E", 100) {
		@Override
		public boolean isRook() {
			return false;
		}
	};

	private String pieceName;
	private int pieceValue;

	public abstract boolean isRook();

	PieceType(String pieceName, int pieceValue) {
		this.pieceName = pieceName;
		this.pieceValue = pieceValue;
	}

	@Override
	public String toString() {
		return this.pieceName;
	}

	public int getPieceValue() {
		return this.pieceValue;
	}

}
