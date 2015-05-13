package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import eg.edu.guc.yugioh.board.player.Player;

public class GraveAndDeck1 extends GraveyardAndDeckPanel {
	public GraveAndDeck1(Player p) throws IOException {
		super(p);
		
		this.add(grave,BorderLayout.NORTH);
		this.add(deck,BorderLayout.SOUTH);
		
		
		
	}

}
