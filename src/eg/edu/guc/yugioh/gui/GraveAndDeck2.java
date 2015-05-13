package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import eg.edu.guc.yugioh.board.player.Player;

public class GraveAndDeck2 extends GraveyardAndDeckPanel {
	public GraveAndDeck2(Player p) throws IOException {
		super(p);
		
		this.add(grave,BorderLayout.SOUTH);
		this.add(deck,BorderLayout.NORTH);
		
		
		
	}

}
