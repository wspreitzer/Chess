package com.williamspreitzer.chess.gui;

public class DialogFactory {
	public static DialogBox retVal = null;
	
	public static DialogBox createDialogBox(DialogType type, String message) {
		switch(type) {
		case ILLEGAL_MOVE:
			retVal = new DialogBox(message, type.getIconName());
			break;
		case LEAVES_PLAYER_IN_CHECK:
			retVal = new DialogBox(message, type.getIconName());
			break;
		case NO_INTERNET_CONNECTION:
			retVal = new DialogBox(message, type.getIconName());
			break;
		case VERSION_NEEDS_UPDATE:
			retVal = new DialogBox(message, type.getIconName());
			break;
		case VERSION_UP_TO_DATE:
			retVal = new DialogBox(message, type.getIconName());
			break;
		default:
			retVal = new DialogBox(message, type.getIconName());
		}
		return retVal;
	}
}
