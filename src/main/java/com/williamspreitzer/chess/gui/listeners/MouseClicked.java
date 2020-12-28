package com.williamspreitzer.chess.gui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MouseClicked extends MouseAdapter {

	private AdapterEventHandler<MouseEvent> m_handler = null;

	public static MouseClicked handle(AdapterEventHandler<MouseEvent> handler) {
		MouseClicked adapter = new MouseClicked();
		adapter.m_handler = handler; 
		return adapter;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { 
		m_handler.handleWithRuntimeException(e);
	}
	
}