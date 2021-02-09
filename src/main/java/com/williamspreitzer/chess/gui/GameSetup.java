
package com.williamspreitzer.chess.gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.williamspreitzer.chess.player.Player;
import com.williamspreitzer.chess.player.PlayerType;
import com.williamspreitzer.chess.utils.GameConstants;

public class GameSetup extends JDialog {
	private PlayerType whitePlayerType;
	private PlayerType blackPlayerType;
	private JSpinner searchDepthSpinner;

	GameSetup(final JFrame frame, final boolean modal) {
		super(frame, modal);
		final JPanel panel = new JPanel(new GridLayout(0, 1));
		final JRadioButton whiteHumanRadioButton = new JRadioButton(GameConstants.HUMAN_TEXT, true);
		final JRadioButton whiteComputerButton = new JRadioButton(GameConstants.COMPUTER_TEXT);
		final JRadioButton blackHumanRadioButton = new JRadioButton(GameConstants.HUMAN_TEXT);
		final JRadioButton blackComputerRadioButton = new JRadioButton(GameConstants.COMPUTER_TEXT, true);

		whiteHumanRadioButton.setActionCommand(GameConstants.HUMAN_TEXT);
		
		final ButtonGroup whiteGroup = new ButtonGroup();
		whiteGroup.add(whiteHumanRadioButton);
		whiteGroup.add(whiteComputerButton);

		final ButtonGroup blackGroup = new ButtonGroup();
		blackGroup.add(blackHumanRadioButton);
		blackGroup.add(blackComputerRadioButton);

		panel.add(new JLabel("White"));
		panel.add(whiteHumanRadioButton);
		panel.add(whiteComputerButton);
		panel.add(new JLabel("Black"));
		panel.add(blackHumanRadioButton);
		panel.add(blackComputerRadioButton);

		panel.add(new JLabel("Search"));
		this.searchDepthSpinner = addLabeledSpinner(panel, "Search Depth",
				new SpinnerNumberModel(6, 0, Integer.MAX_VALUE, 1));

		final JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			frame.dispose();
		});
		final JButton okButton = new JButton("OK");
		okButton.addActionListener(e -> {
			whitePlayerType = whiteHumanRadioButton.isSelected() ? PlayerType.HUMAN : PlayerType.COMPUTER;
			blackPlayerType = blackComputerRadioButton.isSelected() ? PlayerType.COMPUTER : PlayerType.HUMAN;
			frame.dispose();
		});

		panel.add(cancelButton);
		panel.add(okButton);

		getContentPane().add(panel);

		setLocationRelativeTo(frame);
		pack();
		setVisible(false);
	}
	
	boolean isAIPlayer(final Player player) {
		boolean retVal;

		switch (player.getColor()) {
		case WHITE:
			retVal = getWhitePlayerType() == PlayerType.COMPUTER;
			break;
		case BLACK:
			retVal = getBlackPlayerType() == PlayerType.COMPUTER;
			break;
		default:
			retVal = false;
		}

		return retVal;
	}

	private PlayerType getWhitePlayerType() {
		return this.whitePlayerType;
	}

	private PlayerType getBlackPlayerType() {
		return this.blackPlayerType;
	}

	private static JSpinner addLabeledSpinner(final Container c, final String label, final SpinnerModel model) {
		final JLabel l = new JLabel(label);
		c.add(l);
		final JSpinner spinner = new JSpinner(model);
		l.setLabelFor(spinner);
		c.add(spinner);
		return spinner;
	}

	int getSearchDepth() {
		return (Integer) this.searchDepthSpinner.getValue();
	}
}
