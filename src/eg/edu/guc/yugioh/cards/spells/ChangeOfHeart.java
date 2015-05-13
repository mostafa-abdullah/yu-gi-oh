package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;

public class ChangeOfHeart extends SpellCard {

	public ChangeOfHeart(String name, String description) throws IOException {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster){
		if (Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
			throw new NoMonsterSpaceException();
		if (Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()<5){
			Card.getBoard().getOpponentPlayer().getField().getMonstersArea().remove(monster);
			Card.getBoard().getActivePlayer().getField().getMonstersArea().add(monster);
		}
		
	}

}
