package com.williamspreitzer.chess.gui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseExited extends MouseAdapter {
	private AdapterEventHandler<MouseEvent> m_handler = null;
	public static MouseExited handle(AdapterEventHandler<MouseEvent> handler) {
		MouseExited adapter = new MouseExited();
		adapter.m_handler = handler;
		return adapter;
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		m_handler.handleWithRuntimeException(e);
	}
}