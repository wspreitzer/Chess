package com.williamspreitzer.chess.gui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JLabel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.williamspreitzer.chess.TestUtils;

public class GUITest {

	DialogBox box;
	JLabel input;
	String testString;
	@BeforeEach
	private void setup() {
	}
	
	@ParameterizedTest()
	@EnumSource(value = DialogType.class)
	public void dialogBoxTest(DialogType type) {
		switch(type) {
		case ILLEGAL_MOVE:
			box = DialogFactory.createDialogBox(DialogType.ILLEGAL_MOVE, 
					"This is an illegal move.  Try again.");
			testString = "This is an illegal move.  Try again.";
			assertNotNull(box);
			input = (JLabel) TestUtils.getChildNamed(box, "frame");
			//assertNotNull(input);
			//assertEquals(testString, input.getText());
			break;
		case LEAVES_PLAYER_IN_CHECK:
			box = DialogFactory.createDialogBox(type, "You cannot make this move because it will leave you in check");
			testString = "You cannot make this move because it will leave you in check";
			assertNotNull(box);
			input = (JLabel) TestUtils.getChildNamed(box, "frame");
			//assertNotNull(input);
			//assertEquals(testString, input.getText());
			break;
		}
	}
}
