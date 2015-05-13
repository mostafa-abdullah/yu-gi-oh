package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HarpieFeatherDuster extends SpellCard {

	public HarpieFeatherDuster(String name, String description) throws IOException {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster) {
		Card.getBoard().getOpponentPlayer().getField().removeSpellToGraveyard(Card.getBoard().getOpponentPlayer().getField().getSpellArea());
		Card.getBoard().getOpponentPlayer().getField().getSpellArea().clear();
	}

}
