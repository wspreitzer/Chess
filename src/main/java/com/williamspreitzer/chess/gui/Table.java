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
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
import com.williamspreitzer.chess.gui.listeners.MouseClicked;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveLog;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.piece.Piece;
import com.williamspreitzer.chess.piece.PieceFactory;
import com.williamspreitzer.chess.piece.PieceType;
import com.williamspreitzer.chess.utils.GameConstants;
import com.williamspreitzer.chess.utils.GameUtils;

public class Table extends Observable {
	private final JFrame gameFrame;
	private final JPanel boardPanel;
	private final MoveLog moveLog;
	private Board chessBoard;

	private Tile sourceTile;
	private BoardDirection boardDirection;
	private boolean highlightLegalMoves;
	private final GameHistoryPanel historyPanel;
	private final TakenPiecesPanel takePiecesPanel;
	private static Table table;

	static String iconPath;

	private Table() {
		this.gameFrame = new JFrame("Chess");
		this.gameFrame.setSize(new Dimension(Integer.valueOf(GameUtils.props.getProperty("dimension.outerFrame.x")),
				Integer.valueOf(GameUtils.props.getProperty("dimension.outerFrame.y"))));
		this.gameFrame.setLayout(new BorderLayout());
		this.gameFrame.setJMenuBar(this.createJMenuBar());
		this.chessBoard = Board.createStandardBoard();

		/*
		 * this.chessBoard = Board.createTestBoard(
		 * PieceFactory.createPiece(PieceType.ROOK, 0,
		 * com.williamspreitzer.chess.Color.BLACK, false),
		 * PieceFactory.createPiece(PieceType.ROOK, 5,
		 * com.williamspreitzer.chess.Color.BLACK, false),
		 * PieceFactory.createPiece(PieceType.PAWN, 8,
		 * com.williamspreitzer.chess.Color.BLACK, true),
		 * PieceFactory.createPiece(PieceType.PAWN, 9,
		 * com.williamspreitzer.chess.Color.BLACK, true),
		 * PieceFactory.createPiece(PieceType.PAWN, 10,
		 * com.williamspreitzer.chess.Color.BLACK, true),
		 * PieceFactory.createPiece(PieceType.PAWN, 13,
		 * com.williamspreitzer.chess.Color.BLACK, true),
		 * PieceFactory.createPiece(PieceType.PAWN, 14,
		 * com.williamspreitzer.chess.Color.BLACK, true),
		 * PieceFactory.createPiece(PieceType.KING, 15,
		 * com.williamspreitzer.chess.Color.BLACK, false),
		 * PieceFactory.createPiece(PieceType.KNIGHT, 12,
		 * com.williamspreitzer.chess.Color.WHITE, false),
		 * PieceFactory.createPiece(PieceType.ROOK, 27,
		 * com.williamspreitzer.chess.Color.WHITE, false),
		 * PieceFactory.createPiece(PieceType.PAWN, 41,
		 * com.williamspreitzer.chess.Color.WHITE, false),
		 * PieceFactory.createPiece(PieceType.PAWN, 48,
		 * com.williamspreitzer.chess.Color.WHITE, true),
		 * PieceFactory.createPiece(PieceType.PAWN, 53,
		 * com.williamspreitzer.chess.Color.WHITE, true),
		 * PieceFactory.createPiece(PieceType.PAWN, 54,
		 * com.williamspreitzer.chess.Color.WHITE, true),
		 * PieceFactory.createPiece(PieceType.PAWN, 55,
		 * com.williamspreitzer.chess.Color.WHITE, true),
		 * PieceFactory.createPiece(PieceType.KING, 62,
		 * com.williamspreitzer.chess.Color.WHITE, true));
		 */
		this.historyPanel = new GameHistoryPanel();
		this.takePiecesPanel = new TakenPiecesPanel();
		Table.iconPath = GameUtils.props.getProperty("piece.default");
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

	public static Table getTable() {
		Table retVal;
		if (table != null) {
			retVal = table;
		} else {
			retVal = new Table();
		}
		return retVal;
	}

	public boolean getHighlightLegalMoves() {
		return this.highlightLegalMoves;
	}

	private JMenuBar createJMenuBar() {
		final JMenuBar tableMenuBar = new JMenuBar();
		tableMenuBar.setBackground(GameConstants.LIGHT_TILE_COLOR);
		tableMenuBar.add(createFileMenu());
		tableMenuBar.add(createEditMenu());
		tableMenuBar.add(createPreferenceMenu());
		tableMenuBar.add(createHelpMenu());
		tableMenuBar.add(createAboutMenu());
		tableMenuBar.add(createExitMenu());
		return tableMenuBar;
	}

	private JMenu createFileMenu() {
		final JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		final JMenuItem openPGN = new JMenuItem("Open PGN File");
		final JMenuItem newGame = new JMenuItem("New Game");

		openPGN.addActionListener(a -> System.out.println("open up that pgn file!"));
		newGame.addActionListener(a -> {
			this.chessBoard = Board.createStandardBoard();
			GameHistoryPanel.getDataModel().clear();
			this.historyPanel.repaint();
			((BoardPanel) boardPanel).drawBoard(this.chessBoard, iconPath, null);
		});

		fileMenu.add(openPGN);
		fileMenu.add(newGame);
		return fileMenu;
	}

	private JMenu createEditMenu() {
		final JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_H);

		final JMenuItem undo = new JMenuItem("Undo Move");
		final JMenuItem redo = new JMenuItem("Redo Move");

		undo.addActionListener(a -> {
			System.out.println("Undo Clicked");
		});

		redo.addActionListener(a -> {
			System.out.println("Redo Clicked");
		});

		editMenu.add(undo);
		editMenu.add(redo);
		return editMenu;
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

		final JMenu pieceMenu = new JMenu("Piece Type");
		final JRadioButtonMenuItem simple = new JRadioButtonMenuItem("Simple", true);
		final JRadioButtonMenuItem fancy = new JRadioButtonMenuItem("Fancy");
		final JRadioButtonMenuItem holywarriors = new JRadioButtonMenuItem("Holy Warriors");
		final JRadioButtonMenuItem classic = new JRadioButtonMenuItem("Classic");

		final JCheckBoxMenuItem cbHighlightLegalMoves = new JCheckBoxMenuItem("Highlight Moves", false);

		final JMenuItem choosePlayerTypes = new JMenuItem("Choose Players");

		FlipBoardMenuItem.addActionListener(a -> {
			boardDirection = boardDirection.opposite();
			((BoardPanel) boardPanel).drawBoard(chessBoard, iconPath, null);
		});

		simple.addActionListener(a -> {
			((BoardPanel) boardPanel).drawBoard(chessBoard, GameUtils.props.getProperty("piece.default"), null);
		});

		fancy.addActionListener(a -> {
			((BoardPanel) boardPanel).drawBoard(chessBoard, GameUtils.props.getProperty("piece.fancy"), null);
		});

		holywarriors.addActionListener(a -> {
			((BoardPanel) boardPanel).drawBoard(chessBoard, GameUtils.props.getProperty("piece.holywarriors"), null);
		});

		classic.addActionListener(a -> {
			((BoardPanel) boardPanel).drawBoard(chessBoard, GameUtils.props.getProperty("piece.classic"), null);
		});

		cbHighlightLegalMoves.addActionListener(a -> {
			highlightLegalMoves = cbHighlightLegalMoves.isSelected();
		});

		choosePlayerTypes.addActionListener(a -> {
			GameSetup setUp = new GameSetup(new JFrame("Choose Players"), true);
			setUp.setVisible(true);
		});

		pieceMenu.add(simple);
		pieceMenu.add(fancy);
		pieceMenu.add(holywarriors);
		pieceMenu.add(classic);

		final ButtonGroup pieceGroup = new ButtonGroup();
		pieceGroup.add(simple);
		pieceGroup.add(fancy);
		pieceGroup.add(holywarriors);
		pieceGroup.add(classic);

		preferenceMenu.add(FlipBoardMenuItem);
		preferenceMenu.add(cbHighlightLegalMoves);
		preferenceMenu.add(pieceMenu);
		preferenceMenu.add(choosePlayerTypes);
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

	private void setUpdate(final GameSetup gameSetup) {
		setChanged();
		notifyObservers(gameSetup);
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
			setPreferredSize(new Dimension(Integer.valueOf(GameUtils.props.getProperty("dimension.boardPanel.x")),
					Integer.valueOf(GameUtils.props.getProperty("dimension.boardPanel.y"))));
			setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			setBackground(Color.decode("#8B4726"));
			validate();
		}

		public void drawBoard(final Board board, String iconPath, Piece humanMovedPiece) {
			removeAll();
			for (final TilePanel boardTile : boardDirection.traverse(boardTiles)) {
				boardTile.drawTile(board, iconPath, humanMovedPiece);
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
			setPreferredSize(new Dimension(Integer.valueOf(GameUtils.props.getProperty("dimension.tilePanel.x")),
					Integer.valueOf(GameUtils.props.getProperty("dimension.tilePanel.y"))));
			assignTileColor();
			assignTilePieceIcon(chessBoard, iconPath);
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
					boardPanel.drawBoard(chessBoard, iconPath, humanMovedPiece);
				});
			}));

			validate();
		}

		private void assignTileColor() {
			if (GameUtils.EIGHTH_RANK.get(tileId) || GameUtils.SIXTH_RANK.get(tileId)
					|| GameUtils.FOURTH_RANK.get(tileId) || GameUtils.SECOND_RANK.get(tileId)) {
				setBackground(this.tileId % 2 == 0 ? GameConstants.LIGHT_TILE_COLOR : GameConstants.DARK_TILE_COLOR);
			} else {
				setBackground(this.tileId % 2 == 0 ? GameConstants.DARK_TILE_COLOR : GameConstants.LIGHT_TILE_COLOR);
			}
		}

		private void drawTile(final Board board, String iconPath, Piece humanMovedPiece) {
			assignTileColor();
			assignTilePieceIcon(board, iconPath);
			highlightLegals(board, humanMovedPiece);
			validate();
			repaint();
		}

		private void assignTilePieceIcon(final Board board, String iconPath) {
			this.removeAll();
			if (board.getTile(this.tileId).isTileOccupied()) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File(GameUtils.iconName(board, iconPath, (Integer) this.tileId)));
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
							add(new JLabel(new ImageIcon(ImageIO.read(
									new File(TilePanel.class.getResource("/art/misc/green_dot.png").getPath())))));
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
				return Lists.reverse(boardTiles);
			}

			@Override
			BoardDirection opposite() {
				return NORMAL;
			}

		};

		abstract List<TilePanel> traverse(final List<TilePanel> boardTiles);

		abstract BoardDirection opposite();
	}
}