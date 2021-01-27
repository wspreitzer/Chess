package com.williamspreitzer.chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.moves.AttackMove;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveLog;
import com.williamspreitzer.chess.piece.Piece;

public class TakenPiecesPanel extends JPanel {

	private final JPanel northPanel;
	private final JPanel southPanel;
	private final static EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);
	private final static Color PANEL_COLOR = Color.decode("0xFDFE6");
	private final static Dimension TAKEN_PIECES_DIMENSION = new Dimension(40, 80);

	public TakenPiecesPanel() {
		super(new BorderLayout());
		setBackground(Color.decode("0xFDF5E6"));
		setBorder(PANEL_BORDER);
		this.northPanel = new JPanel(new GridLayout(8, 2));
		this.southPanel = new JPanel(new GridLayout(8, 2));
		this.northPanel.setBackground(PANEL_COLOR);
		this.southPanel.setBackground(PANEL_COLOR);
		this.add(this.northPanel, BorderLayout.NORTH);
		this.add(this.southPanel, BorderLayout.SOUTH);
		this.setPreferredSize(TAKEN_PIECES_DIMENSION);
	}

	public void redo(final MoveLog moveLog) {
		this.southPanel.removeAll();
		this.northPanel.removeAll();
		final List<Piece> whiteTakenPieces = new ArrayList<Piece>();
		final List<Piece> blackTakenPieces = new ArrayList<Piece>();
		for (final Move move : moveLog.getMoves()) {
			if (move.isAttack()) {
				AttackMove attackMove = (AttackMove) move;
				final Piece takenPiece = attackMove.getAttackedPiece();
				if (takenPiece.getColor().isWhite()) {
					whiteTakenPieces.add(takenPiece);
				} else {
					blackTakenPieces.add(takenPiece);
				}
			}
		}

		this.sort(whiteTakenPieces);
		this.sort(blackTakenPieces);

		this.addToPanel(whiteTakenPieces, this.southPanel);
		this.addToPanel(blackTakenPieces, this.northPanel);
	}

	private void addToPanel(List<Piece> takenPieces, JPanel panel) {
		for (final Piece takenPiece : takenPieces) {
			BufferedImage image;
			try {
				image = ImageIO.read(new File(GameUtils.iconName(null, takenPiece)));
				final ImageIcon imageIcon = new ImageIcon(image);
				final JLabel imageLabel = new JLabel(imageIcon);
				panel.add(imageLabel);
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private void sort(List<Piece> takenPieces) {
		takenPieces.sort((p1, p2) -> p1.getPieceValue().compareTo(p2.getPieceValue()));
	}
}
