package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import eg.edu.guc.yugioh.board.player.Player;

public class OpponentBoard extends ContainerBoard {

	public OpponentBoard(Player p) throws IOException {
		super(p);
		fieldPanel = new FieldPanel2(p);
		graveAndDeck = new GraveAndDeck2(p);
		fieldPanel.setSize(600, 220);
		fieldPanel.setLocation(200,2);
		
		graveAndDeck.setSize(80,210);
		graveAndDeck.setLocation(800,5);
		
		this.add(fieldPanel);
		this.add(graveAndDeck);
	}

}
