package com.williamspreitzer.chess.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.williamspreitzer.chess.board.Board;
import com.williamspreitzer.chess.board.Tile;
import com.williamspreitzer.chess.board.utils.GameUtils;
import com.williamspreitzer.chess.moves.Move;
import com.williamspreitzer.chess.moves.MoveFactory;
import com.williamspreitzer.chess.moves.MoveTransition;
import com.williamspreitzer.chess.piece.Piece;

public class TilePanel extends JPanel {
	
	private final int tileId;
	private static final Color LIGHT_TILE_COLOR = Color.decode("#FFFACD");
	private static final Color DARK_TILE_COLOR = Color.decode("#593E1A");
	private static final long serialVersionUID = 1L;
	private static final Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
	private Tile sourceTile;
	private Tile destinationTile;
	private Board chessBoard;
	private Piece humanMovedPiece;
	
	public TilePanel(final BoardPanel boardPanel, final int tileId) {
		super(new GridBagLayout());
		this.tileId = tileId;
		setPreferredSize(TILE_PANEL_DIMENSION);
		this.assignTileColor();
		this.chessBoard = Board.createStandardBoard();
		assignTilePieceIcon(chessBoard);
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)) {
					sourceTile = null;
				} else if(SwingUtilities.isLeftMouseButton(e)) {
System.out.println("BH");
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
							System.out.println("Hello");
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
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		validate();
	}
	
	private void assignTileColor() {
		if(GameUtils.FIRST_ROW[this.tileId] ||
				   GameUtils.THIRD_ROW[this.tileId] ||
				   GameUtils.FIFTH_ROW[this.tileId] ||
				   GameUtils.SEVENTH_ROW[this.tileId]) {
					setBackground( this.tileId % 2 == 0 ? LIGHT_TILE_COLOR : DARK_TILE_COLOR);
				} else {
					setBackground( this.tileId % 2 == 0 ? DARK_TILE_COLOR : LIGHT_TILE_COLOR );
				}
	}
	
	public void drawTile(final Board board) {
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
				image = ImageIO.read(new File( this.iconName(board, TilePanel.class.getResource("../../../../art/simple"))));
				add(new JLabel(new ImageIcon(image)));
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} 
		}
	}
	
	private void clearState() {
		sourceTile = null;
		destinationTile = null;
		humanMovedPiece = null;
	}
}