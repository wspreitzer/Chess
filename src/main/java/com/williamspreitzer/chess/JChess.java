package com.williamspreitzer.chess;

import com.williamspreitzer.chess.gui.DialogBox;
import com.williamspreitzer.chess.sync.Worker;

public class JChess {

	static DialogBox box;
	static boolean isReady;

	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.checkForUpdate();
		worker.runGui();
	}
}