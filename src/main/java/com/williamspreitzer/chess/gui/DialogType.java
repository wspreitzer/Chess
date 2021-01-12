package com.williamspreitzer.chess.gui;

public enum DialogType {
	ILLEGAL_MOVE {
		@Override
		public String getIconName() {
			return "illegal";
		}
	},
	LEAVES_PLAYER_IN_CHECK {
		@Override
		public String getIconName() {
			return "warning";
		}
	},
	
	NO_INTERNET_CONNECTION {
		@Override
		public String getIconName() {
			return "warning";
		}
	},
	
	VERSION_UP_TO_DATE {
		@Override
		public String getIconName() {
			return "warning";
		}
	},
	
	VERSION_NEEDS_UPDATE {
		@Override
		public String getIconName() {
			return "warning";
		}
	},
	
	CATOSTRAPHIC_ERROR_OCCURED {
		@Override
		public String getIconName() {
			return "warning";
		}
	};
	
	public abstract String getIconName();
}
