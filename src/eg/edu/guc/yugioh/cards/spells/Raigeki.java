package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;



public class Raigeki extends SpellCard{

	public Raigeki(String name, String description) throws IOException {
		super(name, description);
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster) { 
		
		Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(Card.getBoard().getOpponentPlayer().getField().getMonstersArea());
		//Card.getBoard().getOpponentPlayer().getField().getMonstersArea().clear();

		
	}
	

}
