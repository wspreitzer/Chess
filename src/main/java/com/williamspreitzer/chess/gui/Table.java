package com.williamspreitzer.chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.google.common.collect.Lists;
import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Tile;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.gui.listeners.MouseClicked;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveLog;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.piece.Piece;

public class Table {

	private final JFrame gameFrame;
	private final JPanel boardPanel;
	private final MoveLog moveLog;
	private Board chessBoard;

	private Tile sourceTile;
	private BoardDirection boardDirection;
	private boolean highlightLegalMoves;
	private final GameHistoryPanel historyPanel;
	private final TakenPiecesPanel takePiecesPanel;
	private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
	private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
	private static final Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);
	private static final Color LIGHT_TILE_COLOR = Color.decode("#FFFACD");
	private static final Color DARK_TILE_COLOR = Color.decode("#593E1A");

	public Table() {
		this.gameFrame = new JFrame("Chess");
		this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
		this.gameFrame.setLayout(new BorderLayout());
		this.gameFrame.setJMenuBar(this.createJMenuBar());
		this.chessBoard = Board.createStandardBoard();
		this.historyPanel = new GameHistoryPanel();
		this.takePiecesPanel = new TakenPiecesPanel();
		this.boardPanel = new BoardPanel();
		this.moveLog = new MoveLog();
		this.boardDirection = BoardDirection.NORMAL;
		this.gameFrame.add(this.takePiecesPanel, BorderLayout.WEST);
		this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
		this.gameFrame.add(this.historyPanel, BorderLayout.EAST);
		this.highlightLegalMoves = false;
		this.gameFrame.setVisible(true);
		center(gameFrame);
	}

	public boolean getHighlightLegalMoves() {
		return this.highlightLegalMoves;
	}
	private JMenuBar createJMenuBar() {
		final JMenuBar tableMenuBar = new JMenuBar();
		tableMenuBar.setBackground(LIGHT_TILE_COLOR);
		tableMenuBar.add(createFileMenu());
		tableMenuBar.add(createPreferenceMenu());
		tableMenuBar.add(createHelpMenu());
		tableMenuBar.add(createAboutMenu());
		tableMenuBar.add(createExitMenu());
		return tableMenuBar;
	}

	private JMenu createFileMenu() {
		final JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		final JMenuItem openPGN = new JMenuItem("Load PGN File");
		openPGN.addActionListener(a -> System.out.println("open up that pgn file!"));
		fileMenu.add(openPGN);
		return fileMenu;
	}

	private JMenu createHelpMenu() {
		final JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		final JMenuItem chess = new JMenuItem("About Chess");
		chess.addActionListener(a -> System.out.println("How to play chess"));
		final JMenuItem jchess = new JMenuItem("How to Play JChess");
		jchess.addActionListener(a -> System.out.println("How to Play JChess"));
		helpMenu.add(chess);
		helpMenu.add(jchess);
		return helpMenu;
	}

	private JMenu createPreferenceMenu() {
		final JMenu preferenceMenu = new JMenu("Preferences");
		preferenceMenu.setMnemonic(KeyEvent.VK_P);

		final JMenuItem FlipBoardMenuItem = new JMenuItem("Flip Board");
		final JMenu colorMenu = new JMenu("Color");
		final JRadioButtonMenuItem black = new JRadioButtonMenuItem("Black");
		final JRadioButtonMenuItem white = new JRadioButtonMenuItem("White", true);
		final JCheckBoxMenuItem cbHighlightLegalMoves = new JCheckBoxMenuItem("Highlight Moves", false);
		
		FlipBoardMenuItem.addActionListener(a -> {
			boardDirection = boardDirection.opposite();
			((BoardPanel) boardPanel).drawBoard(chessBoard, null);
		});

		black.addActionListener(a -> {
			System.out.println("Black was clicked");
		});

		white.addActionListener(a -> {
			System.out.println("White was clicked");
		});

		cbHighlightLegalMoves.addActionListener(a -> {
			highlightLegalMoves = cbHighlightLegalMoves.isSelected();
		});
		colorMenu.add(white);
		colorMenu.add(black);
		preferenceMenu.add(FlipBoardMenuItem);
		preferenceMenu.add(cbHighlightLegalMoves);
		preferenceMenu.add(colorMenu);
		return preferenceMenu;
	}

	private JMenu createAboutMenu() {
		final JMenu aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		final JMenuItem about = new JMenuItem("About JChess");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));

		final JMenuItem updates = new JMenuItem("Check for Updates");
		updates.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));

		about.addActionListener(a -> {
			System.out.println("this is about");
		});
		updates.addActionListener(a -> {
			System.out.println("this is updates");
		});

		aboutMenu.add(about);
		aboutMenu.add(updates);
		return aboutMenu;
	}

	private JMenu createExitMenu() {
		JMenu exitMenu = new JMenu("Exit");
		exitMenu.setMnemonic(KeyEvent.VK_X);
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem saveAndExit = new JMenuItem("Save and Exit");

		exit.addActionListener(e -> System.exit(0));
		saveAndExit.addActionListener(e -> System.out.println("Save and Exit was clicked"));
		exitMenu.add(exit);
		exitMenu.add(saveAndExit);
		return exitMenu;
	}

	private static void center(final JFrame frame) {
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		final int w = frame.getSize().width;
		final int h = frame.getSize().height;
		final int x = (dim.width - w) / 2;
		final int y = (dim.height - h) / 2;
		frame.setLocation(x, y);
	}

	private class BoardPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		final List<TilePanel> boardTiles;

		public BoardPanel() {
			super(new GridLayout(8, 8));
			this.boardTiles = new ArrayList<TilePanel>();
			for (int i = 0; i < GameUtils.NUM_TILES; i++) {
				final TilePanel tilePanel = new TilePanel(this, i);
				this.boardTiles.add(tilePanel);
				add(tilePanel);
			}
			setPreferredSize(BOARD_PANEL_DIMENSION);
			setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			setBackground(Color.decode("#8B4726"));
			validate();
		}

		public void drawBoard(final Board board, Piece humanMovedPiece) {
			removeAll();
			for (final TilePanel boardTile : boardDirection.traverse(boardTiles)) {
				boardTile.drawTile(board, humanMovedPiece);
				add(boardTile);
			}
			validate();
			repaint();
		}
	}

	public class TilePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private final int tileId;
		private Piece humanMovedPiece;
		private Tile destinationTile;

		TilePanel(final BoardPanel boardPanel, final int tileId) {
			super(new GridBagLayout());
			this.tileId = tileId;
			setPreferredSize(TILE_PANEL_DIMENSION);
			assignTileColor();
			assignTilePieceIcon(chessBoard);
			addMouseListener(MouseClicked.handle(e -> {
				if (SwingUtilities.isRightMouseButton(e)) {
					sourceTile = null;
				} else {
					if (sourceTile == null) {
						sourceTile = chessBoard.getTile(tileId);
						humanMovedPiece = sourceTile.getPiece();
						if (humanMovedPiece == null) {
							sourceTile = null;
						}
					} else {
						destinationTile = chessBoard.getTile(tileId);
						final Move move = MoveFactory.getMove(chessBoard, sourceTile.getTileCoordinate(),
								destinationTile.getTileCoordinate());
						final MoveTransition transition = chessBoard.getCurrentPlayer().makeMove(move);
						switch (transition.getStatus()) {
						case DONE:
							chessBoard = transition.getBoard();
							moveLog.addMove(move);
							break;
						case ILLEGAL_MOVE:
							DialogFactory.createDialogBox(DialogType.ILLEGAL_MOVE,
									"This is an illegal move.  Try again.");
							break;
						case LEAVES_PLAYER_IN_CHECK:
							DialogFactory.createDialogBox(DialogType.LEAVES_PLAYER_IN_CHECK,
									"You cannot make this move because it will leave you in check");
							break;
						}
						sourceTile = null;
						destinationTile = null;
						humanMovedPiece = null;
					}
				}

				SwingUtilities.invokeLater(() -> {
					historyPanel.redo(chessBoard, moveLog);
					takePiecesPanel.redo(moveLog);
					boardPanel.drawBoard(chessBoard, humanMovedPiece);
				});
			}));

			validate();
		}

		private void assignTileColor() {
			if (GameUtils.FIRST_ROW[this.tileId] || GameUtils.THIRD_ROW[this.tileId] || GameUtils.FIFTH_ROW[this.tileId]
					|| GameUtils.SEVENTH_ROW[this.tileId]) {
				setBackground(this.tileId % 2 == 0 ? LIGHT_TILE_COLOR : DARK_TILE_COLOR);
			} else {
				setBackground(this.tileId % 2 == 0 ? DARK_TILE_COLOR : LIGHT_TILE_COLOR);
			}
		}

		private void drawTile(final Board board, Piece humanMovedPiece) {
			assignTileColor();
			assignTilePieceIcon(board);
			highlightLegals(board, humanMovedPiece);
			validate();
			repaint();
		}

		private void assignTilePieceIcon(final Board board) {
			this.removeAll();
			if (board.getTile(this.tileId).isTileOccupied()) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File(GameUtils.iconName(board, (Integer) this.tileId)));
					add(new JLabel(new ImageIcon(image)));
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}

		private void highlightLegals(Board board, Piece selectedPiece) {
			if (getHighlightLegalMoves()) {
				for (final Move move : pieceLegalMoves(board, selectedPiece)) {
					if (move.getDestinationCoordinate() == this.tileId) {
						try {
							add(new JLabel(new ImageIcon(ImageIO.read(new File(
									TilePanel.class.getResource("../../../../art/misc/green_dot.png").getPath())))));
						} catch (FileNotFoundException fnfe) {
							fnfe.printStackTrace();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					}
				}
			}
		}

		private Collection<Move> pieceLegalMoves(final Board board, Piece selectedPiece) {
			if (selectedPiece != null && selectedPiece.getColor() == board.getCurrentPlayer().getColor()) {
				return selectedPiece.calculateLegalMoves(board);
			}
			return Collections.emptyList();
		}

	}

	public enum BoardDirection {
		NORMAL {
			@Override
			List<TilePanel> traverse(List<TilePanel> boardTiles) {
				// TODO Auto-generated method stub
				return boardTiles;
			}

			@Override
			BoardDirection opposite() {
				// TODO Auto-generated method stub
				return FLIPPED;
			}

		},
		FLIPPED {

			@Override
			List<TilePanel> traverse(List<TilePanel> boardTiles) {
				// TODO Auto-generated method stub
				return Lists.reverse(boardTiles);
			}

			@Override
			BoardDirection opposite() {
				// TODO Auto-generated method stub
				return NORMAL;
			}

		};

		abstract List<TilePanel> traverse(final List<TilePanel> boardTiles);

		abstract BoardDirection opposite();
	}
}