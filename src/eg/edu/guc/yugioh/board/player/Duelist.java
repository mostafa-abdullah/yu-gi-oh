package eg.edu.guc.yugioh.board.player;


import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.board.Board;

public interface Duelist {

	public boolean summonMonster(MonsterCard monster);
	public  boolean summonMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices);
	public boolean setMonster(MonsterCard monster);
	public boolean setMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices);
	public boolean setSpell(SpellCard spell) throws IOException;
	public boolean activateSpell(SpellCard spell, MonsterCard monster) throws IOException, Exception;
	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster) throws Exception;
	public boolean declareAttack(MonsterCard activeMonster) throws LineUnavailableException, Exception;
	public void addCardToHand();
	public void addNCardsToHand(int n);
	public boolean endTurn() throws Exception;
	public void endPhase() throws Exception;
	public boolean switchMonsterMode(MonsterCard monster);
	
}
