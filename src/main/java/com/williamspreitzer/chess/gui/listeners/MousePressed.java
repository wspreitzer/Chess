package com.williamspreitzer.chess.gui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MousePressed extends MouseAdapter {

	private AdapterEventHandler<MouseEvent> m_handler = null;
	public static MousePressed handle(AdapterEventHandler<MouseEvent> handler) {
		MousePressed adapter = new MousePressed();
		adapter.m_handler = handler; 
		return adapter;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		m_handler.handleWithRuntimeException(e);
	}
}
