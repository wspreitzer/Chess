package com.williamspreitzer.chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import com.williamspreitzer.chess.gui.listeners.MouseClicked;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.piece.Piece;

public class Table {
	
	private final JFrame gameFrame;
	private JPanel boardPanel;
	private Board chessBoard;
	private Tile sourceTile;
	
	private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
	private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
	private static final Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
	private static final Color LIGHT_TILE_COLOR = Color.decode("#FFFACD");
	private static final Color DARK_TILE_COLOR = Color.decode("#593E1A");
	
	public Table() {
		this.gameFrame = new JFrame("Chess");
		this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
		this.gameFrame.setLayout(new BorderLayout());
		this.gameFrame.setJMenuBar(this.createJMenuBar());
		this.chessBoard = Board.createStandardBoard();
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
	
	private class BoardPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		final List<TilePanel> boardTiles;
		public BoardPanel() {
			super(new GridLayout(8,8));
			this.boardTiles = new ArrayList<TilePanel>();
			for(int i = 0; i < GameUtils.NUM_TILES; i++ ) {
				final TilePanel tilePanel = new TilePanel(this, i);
				this.boardTiles.add(tilePanel);
				add(tilePanel);
			}
			setPreferredSize(BOARD_PANEL_DIMENSION);
			setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			setBackground(Color.decode("#8B4726"));
			validate();
		}
	
		public void drawBoard(final Board board) {
			removeAll();
			for(final TilePanel boardTile : boardTiles) {
				boardTile.drawTile(board);
				add(boardTile);
			}
			validate();
			repaint();
		}
	}
	
	private class TilePanel extends JPanel {
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
				if(SwingUtilities.isRightMouseButton(e)) {
					sourceTile = null;
				} else {
					if(sourceTile == null ) {
						sourceTile = chessBoard.getTile(tileId);
						humanMovedPiece = sourceTile.getPiece();
						if(humanMovedPiece == null ) { 
							sourceTile = null;
						}
					} else {
						destinationTile = chessBoard.getTile(tileId);
						final Move move = MoveFactory.createMove(chessBoard, sourceTile.getTileCoordinate(), destinationTile.getTileCoordinate());
						final MoveTransition transition = chessBoard.getCurrentPlayer().makeMove(move);
						switch(transition.getStatus()) {
						case DONE:
							chessBoard = transition.getBoard();
							break;
						case ILLEGAL_MOVE:
							break;
						case LEAVES_PLAYER_IN_CHECK:
							break;
						}
						sourceTile = null;
						destinationTile = null;
						humanMovedPiece = null;
						
					}
				}
				SwingUtilities.invokeLater(() -> {
					boardPanel.drawBoard(chessBoard);
				});
			 }));
			validate();
		}
		
		private void assignTileColor() {
			if(GameUtils.FIRST_ROW[this.tileId] ||
					GameUtils.THIRD_ROW[this.tileId] ||
					GameUtils.FIFTH_ROW[this.tileId] ||
					GameUtils.SEVENTH_ROW[this.tileId]) {
				setBackground( this.tileId % 2 == 0 ? LIGHT_TILE_COLOR : DARK_TILE_COLOR);
			} else {
				setBackground( this.tileId % 2 == 0 ? DARK_TILE_COLOR : LIGHT_TILE_COLOR);
			}
		}
		
		private void drawTile(final Board board) {
			assignTileColor();
			assignTilePieceIcon(board);
			validate();
			repaint();
		}
		
		private String iconName(Board board, URL path) {
			StringBuffer sb = new StringBuffer();
			sb.append(path.getPath());
			sb.append("/");
			sb.append(board.getTile(this.tileId).getPiece().getColor().toString().substring(0,1));
			sb.append(board.getTile(this.tileId).getPiece().toString());
			sb.append(".gif");
			return sb.toString();
		}
		
		private void assignTilePieceIcon(final Board board) {
			this.removeAll();
			if(board.getTile(this.tileId).isTileOccupied()) {
				BufferedImage image = null;
				URL path = TilePanel.class.getResource("../../../../art/simple");
				try {
					image = ImageIO.read(new File(this.iconName(board, path)));
					add(new JLabel(new ImageIcon(image)));
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}