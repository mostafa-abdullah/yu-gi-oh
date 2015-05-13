package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole  extends Raigeki {

	public DarkHole(String name, String description) throws IOException {
		super(name,description);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster) { 
		super.action(monster);
		
		Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(Card.getBoard().getActivePlayer().getField().getMonstersArea());
		Card.getBoard().getActivePlayer().getField().getMonstersArea().clear();
	}

}
