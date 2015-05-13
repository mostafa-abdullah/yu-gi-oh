package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;

public class ContainerBoard extends JPanel {
	
	public Player getPlayer() {
		return player;
	}
	public FieldPanel getFieldPanel() {
		return fieldPanel;
	}
	public GraveyardAndDeckPanel getGraveAndDeck() {
		return graveAndDeck;
	}
	 Player player;
	 FieldPanel fieldPanel;
	 GraveyardAndDeckPanel graveAndDeck;
	public ContainerBoard(Player p){
		super();
		this.player = p;
		this.setPreferredSize(new Dimension(800,260));
		this.setLayout(null);
		this.setOpaque(false);
	}
	
	
}
