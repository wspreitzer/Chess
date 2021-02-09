package com.williamspreitzer.chess.sync;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import com.williamspreitzer.autoupdate.AutoUpdate;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.gui.DialogBox;
import com.williamspreitzer.chess.gui.DialogFactory;
import com.williamspreitzer.chess.gui.DialogType;
import com.williamspreitzer.chess.gui.Table;
import com.williamspreitzer.chess.utils.GameUtils;
public class Worker {

	static DialogBox box;

	public synchronized void checkForUpdate() {
		int returnCode = AutoUpdate.getUpdateReturnCode("https://api.github.com", "chess", getVersion());
		switch (returnCode) {
		case 0:
			break;
		case 1:
			box = DialogFactory.createDialogBox(DialogType.VERSION_NEEDS_UPDATE,
					GameUtils.props.getProperty("message.versionNeedsUpdate"));
			break;
		case 408:
			box = DialogFactory.createDialogBox(DialogType.NO_INTERNET_CONNECTION,
					GameUtils.props.getProperty("message.noInternetConnection"));
			break;
		default:
			box = DialogFactory.createDialogBox(DialogType.CATOSTRAPHIC_ERROR_OCCURED,
					GameUtils.props.getProperty("message.catostraphicError"));
		}
		
		while (DialogBox.isOpen) {
			try {
				Thread.sleep(5);
				if(DialogBox.isCatostraphic) {
					System.exit(returnCode);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unused")
	public synchronized void runGui() {
		Table table = Table.getTable();
	}

	private static String getVersion() {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = null;
		try {
			model = reader.read(new FileReader("pom.xml"));
		} catch (IOException | XmlPullParserException e) {
			e.printStackTrace();
		}
		return model.getVersion();
	}
	
	
}
