package com.williamspreitzer.chess.gui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEntered extends MouseAdapter {
	private AdapterEventHandler<MouseEvent> m_handler = null;
	public static MouseEntered handle(AdapterEventHandler<MouseEvent> handler) {
		MouseEntered adapter = new MouseEntered();
		adapter.m_handler = handler;
		return adapter;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		m_handler.handleWithRuntimeException(e);
	}
}