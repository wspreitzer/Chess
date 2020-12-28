package com.williamspreitzer.chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Tile;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.piece.Piece;

public class Table {
	
	private final JFrame gameFrame;
	private JPanel boardPanel;
	
	private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
	
	public Table() {
		this.gameFrame = new JFrame("Chess");
		this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
		this.gameFrame.setLayout(new BorderLayout());
		this.gameFrame.setJMenuBar(this.createJMenuBar());
		this.boardPanel = new BoardPanel();
		this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
		this.gameFrame.setVisible(true);
	}

	private JMenuBar createJMenuBar() {
		final JMenuBar tableMenuBar = new JMenuBar();
		tableMenuBar.add(createFileMenu());
		tableMenuBar.add(createHelpMenu());
		tableMenuBar.add(createAboutMenu());
		tableMenuBar.add(createExitMenu());
		return tableMenuBar;
	}

	private JMenu createFileMenu() {
		final JMenu fileMenu = new JMenu("File");
		
		final JMenuItem openPGN = new JMenuItem("Load PGN File");
		openPGN.addActionListener(a -> System.out.println("open up that pgn file!"));
		fileMenu.add(openPGN);
		return fileMenu;
	}
	
	private JMenu createHelpMenu() {
		final JMenu helpMenu = new JMenu("Help");
		
		final JMenuItem chess = new JMenuItem("About Chess");
		chess.addActionListener(a -> System.out.println("How to play chess"));
		final JMenuItem jchess = new JMenuItem("How to Play JChess");
		jchess.addActionListener(a -> System.out.println("How to Play JChess"));
		helpMenu.add(chess);
		helpMenu.add(jchess);
		return helpMenu;
	}
	
	private JMenu createAboutMenu() {
		final JMenu aboutMenu = new JMenu("About");
		final JMenuItem about = new JMenuItem("About JChess");
		final JMenuItem updates = new JMenuItem("Check for Updates");
		
		about.addActionListener(a -> System.out.println("this is about"));
		updates.addActionListener(a -> System.out.println("this is updates"));
		aboutMenu.add(about);
		aboutMenu.add(updates);
		return aboutMenu;
	}
	
	private JMenu createExitMenu() {
		JMenu exitMenu = new JMenu("Exit");
		JMenuItem exit = new JMenuItem("Save and Exit");
		exit.addActionListener(a -> System.exit(0));
		exitMenu.add(exit);
		return exitMenu;
	}
}