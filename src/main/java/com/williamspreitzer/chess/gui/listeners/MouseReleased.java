package com.williamspreitzer.chess.gui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseReleased extends MouseAdapter {
	private AdapterEventHandler<MouseEvent> m_handler = null;
	public static MouseReleased handle(AdapterEventHandler<MouseEvent> handler ) {
		MouseReleased adapter = new MouseReleased();
		adapter.m_handler = handler;
		return adapter;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		m_handler.handleWithRuntimeException(e);
	}
}