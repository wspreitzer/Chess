package com.williamspreitzer.chess.gui.listeners;

@FunctionalInterface
public interface AdapterEventHandler<EVENT> {
	void handle(EVENT e) throws Exception;
	default void handleWithRuntimeException(EVENT e) {
		try {
			handle(e);
		} catch(Exception exception) {
			throw exception instanceof RuntimeException ? (RuntimeException) exception : new RuntimeException(exception);
		}
	}
}