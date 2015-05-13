package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MagePower extends SpellCard {

	public MagePower(String name, String description) throws IOException {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster) {
		int SpellCards = Card.getBoard().getActivePlayer().getField().getSpellArea().size();
		monster.setAttackPoints(monster.getAttackPoints()+500*SpellCards);
		monster.setDefensePoints(monster.getDefensePoints()+500*SpellCards);
	}

}
