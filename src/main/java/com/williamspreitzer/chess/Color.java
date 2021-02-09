package com.williamspreitzer.chess;

import com.williamspreitzer.chess.player.BlackPlayer;
import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.player.WhitePlayer;
import com.williamspreitzer.chess.utils.GameUtils;

public enum Color {
	BLACK {
		@Override
		public int getDirection() {
			return 1;
		}
		
		@Override
		public int getOppositeDirection() {
			return -1;
		}
		
		@Override
		public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
			return blackPlayer;
		}
		
		public boolean isBlack() {
			return true;
		}
		
		public boolean isWhite() {
			return false;
		}
		
		@Override
		public boolean isPawnPromotionTile(int position) {
			return GameUtils.FIRST_RANK.get(position);
		}
		
		
	},
	WHITE {
		@Override
		public int getDirection() {
			return -1;
		}
		
		@Override
		public int getOppositeDirection() {
			return 1;
		}
		
		@Override
		public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
			return whitePlayer;
		}
		
		public boolean isWhite() {
			return true;
		}
		
		public boolean isBlack() {
			return false;
		}

		@Override
		public boolean isPawnPromotionTile(int position) {
			return GameUtils.EIGHTH_RANK.get(position);
		}
	};
	
	public abstract int getOppositeDirection();
	public abstract int getDirection();
	public abstract boolean isBlack();
	public abstract boolean isWhite();
	public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
	public abstract boolean isPawnPromotionTile(int position);
}