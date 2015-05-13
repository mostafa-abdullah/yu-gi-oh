package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;


public class MonsterReborn extends SpellCard {

	public MonsterReborn(String name, String description) throws IOException {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster) throws IOException{
		
		ArrayList<Card> grave1 = Card.getBoard().getActivePlayer().getField().getGraveyard();
		ArrayList<Card> grave2 = Card.getBoard().getOpponentPlayer().getField().getGraveyard();
		
		MonsterCard max = new MonsterCard("", "", 0, 0, 0);
		int place = -1;
		for(Card c: grave1){
			if (c.getClass().equals(MonsterCard.class)){
				MonsterCard graveMonster = (MonsterCard) c;
				if (graveMonster.getAttackPoints()>max.getAttackPoints()){
					max=graveMonster;
					place = 0;
				}
			}
		}
		for(Card c: grave2){
			if (c.getClass().equals(MonsterCard.class)){
				MonsterCard graveMonster = (MonsterCard) c;
				if (graveMonster.getAttackPoints()>max.getAttackPoints()){
					max=graveMonster;
					place = 1;
				}
			}
		}
		if(max.getName().equals(""))
			return;
		
		max.setMode(Mode.ATTACK);
		max.setHidden(false);
		max.setLocation(Location.FIELD);
		Card.getBoard().getActivePlayer().getField().getMonstersArea().add(max);
		if (place==0){
			grave1.remove(max);
		}
		else
			grave2.remove(max);
		
	}

}
