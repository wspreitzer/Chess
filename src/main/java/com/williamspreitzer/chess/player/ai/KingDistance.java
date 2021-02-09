package com.williamspreitzer.chess.player.ai;

import com.williamspreitzer.chess.piece.Piece;

public class KingDistance {

	final Piece enemyPiece;
	final int distance;
	
	public KingDistance(Piece enemyPiece, int distance) {
		this.enemyPiece = enemyPiece;
		this.distance = distance;
	}
	
	public Piece getEnemyPiece() {
		return this.enemyPiece;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public int tropismScore() {
		return (enemyPiece.getPieceValue()/10) * distance;
	}
}