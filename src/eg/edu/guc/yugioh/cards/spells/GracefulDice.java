package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

import java.io.IOException;
import java.util.ArrayList;

public class GracefulDice extends SpellCard {

	public GracefulDice(String name, String description) throws IOException {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster){
		int randomNum = (int)(Math.random()*10)+1;
		int gain = randomNum * 100;
		ArrayList<MonsterCard> myMonsters = Card.getBoard().getActivePlayer().getField().getMonstersArea();
		for (MonsterCard c : myMonsters){
			c.setAttackPoints(c.getAttackPoints()+gain);
			c.setDefensePoints(c.getDefensePoints()+gain);
		}
	}

}
