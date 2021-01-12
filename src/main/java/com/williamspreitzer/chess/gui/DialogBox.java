package com.williamspreitzer.chess.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class DialogBox extends JDialog{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton closeButton;
	private JPanel panel;
	private JLabel iconLabel;
	private JLabel messageLabel;
	public static boolean isOpen = false;
	public static boolean isCatostraphic = false;
	
	protected DialogBox(String message, String name) {
		isOpen = true;
		this.frame = new JFrame("Chess - Info");
		this.frame.setName("frame");
		this.frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.frame.setSize(new Dimension(370, 125));
		this.panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
			this.iconLabel = new JLabel(new ImageIcon(this.getIcon(name, c)));
		panel.add(iconLabel,c);
		
		GridBagConstraints messageConstraints = new GridBagConstraints();
		messageConstraints.gridx = 0;
		messageConstraints.gridy = 1;
		messageConstraints.gridwidth = 2;
		this.messageLabel = new JLabel(message);
		this.messageLabel.setName("messageLabel");
		panel.add(messageLabel, messageConstraints);
		
		GridBagConstraints closeConstraints = new GridBagConstraints();
		closeConstraints.gridx = 1;
		closeConstraints.gridy = 2;
		this.closeButton = new JButton("Close");
		this.closeButton.addActionListener(e -> { 
			this.frame.dispose();
			isOpen = false;
			if(this.messageLabel.getText().contains("Catostraphic")) {
				isCatostraphic = true;
			}
		});
		panel.add(closeButton, closeConstraints);
		frame.add(panel, BorderLayout.LINE_START);
		frame.setVisible(true);
	}
	
	  private BufferedImage getIcon(String iconName, GridBagConstraints c) {
		  BufferedImage image = null; 
		  StringBuffer sb = new StringBuffer();
		  sb.append(DialogBox.class.getResource("../../../../art").getPath());
		  sb.append("/");
		  sb.append(iconName);
		  sb.append(".png"); 
		  try {
			  image = ImageIO.read(new File(sb.toString()));
		  } catch (FileNotFoundException fnfe) { 
			  fnfe.printStackTrace();
		  } catch (IOException ioe) {
			  ioe.printStackTrace();
		  }
		  return image;
	}
	 
}