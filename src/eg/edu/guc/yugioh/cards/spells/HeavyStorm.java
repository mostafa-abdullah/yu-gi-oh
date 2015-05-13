package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster{

	public HeavyStorm(String name, String description) throws IOException {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster) {
		super.action(monster);
		
		Card.getBoard().getActivePlayer().getField().removeSpellToGraveyard(Card.getBoard().getActivePlayer().getField().getSpellArea());
		Card.getBoard().getActivePlayer().getField().getSpellArea().clear();
		
	}

}
