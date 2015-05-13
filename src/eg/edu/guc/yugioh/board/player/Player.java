package eg.edu.guc.yugioh.board.player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.CannotAddChangeOfHeartException;
import eg.edu.guc.yugioh.exceptions.CannotAddMagePowerException;
import eg.edu.guc.yugioh.exceptions.CannotAddMonsterRebornException;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.WrongAttackedMonsterException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.exceptions.WrongSacrifices;

public class Player implements Duelist{
	private String name;
	public boolean isSummonedMonster() {
		return summonedMonster;
	}

	public void setSummonedMonster(boolean summonedMonster) {
		this.summonedMonster = summonedMonster;
	}

	private boolean summonedMonster;
	public String getName() {
		return name;
	}

	private int lifePoints;
	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	private Field field;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	public Player(String name) throws Exception{
		
		this.name = name; 
		lifePoints = 8000;
		field = new Field();
		summonedMonster = false;
	}



	public boolean summonMonster(MonsterCard monster) {
		
		if (this.isSummonedMonster())
			throw new MultipleMonsterAdditionException();
		if (!this.getField().getHand().contains(monster))
			return false;
		if (this.getField().getMonstersArea().size()>=5)
			throw new NoMonsterSpaceException();
		if (this == Card.getBoard().getOpponentPlayer())
			return false;
		if ( Card.getBoard().isGameEnds())
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		this.getField().addMonsterToField(monster, Mode.ATTACK,false);
		monster.setLocation(Location.FIELD);
		this.getField().getHand().remove(monster);
		this.setSummonedMonster(true);
		return true;
	}
	public boolean summonMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices) {

		
		if(this.isSummonedMonster())
			throw new MultipleMonsterAdditionException();
		if(!this.getField().getHand().contains(monster))
			return false;
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		if( Card.getBoard().isGameEnds() )
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		if(Card.getBoard().getActivePlayer().getField().sacrifices(monster)!=sacrifices.size())
			return false;
		for(int i=0; i<sacrifices.size(); i++){
			if(!Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(sacrifices.get(i)))
				throw new WrongSacrifices();
		}
		this.getField().addMonsterToField(monster, Mode.ATTACK,sacrifices);
		this.setSummonedMonster(true);
		monster.setLocation(Location.FIELD);
		this.getField().getHand().remove(monster);
		return true;
	}

	public boolean setMonster(MonsterCard monster){
		
		if(this.isSummonedMonster())
			throw new MultipleMonsterAdditionException();
		if(!this.getField().getHand().contains(monster))
			return false;
		if( Card.getBoard().isGameEnds())
			return false;
		if(this.getField().getMonstersArea().size()>=5)
			throw new NoMonsterSpaceException();
		if( this == Card.getBoard().getOpponentPlayer())
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		this.getField().addMonsterToField(monster, Mode.DEFENSE,true);
		monster.setLocation(Location.FIELD);
		this.getField().getHand().remove(monster);
		this.setSummonedMonster(true);
		return true;
	}
	public boolean setMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices){ 

		
		if(this.isSummonedMonster())
			throw new MultipleMonsterAdditionException();
		if(!this.getField().getHand().contains(monster))
			return false;
		if(Card.getBoard().isGameEnds() )
			return false;
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		if( Card.getBoard().getActivePlayer().getField().sacrifices(monster)!=sacrifices.size())
			return false;
		for(int i=0; i<sacrifices.size(); i++){
			if(!Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(sacrifices.get(i)))
				throw new WrongSacrifices();
		}
		this.getField().addMonsterToField(monster, Mode.DEFENSE,sacrifices);
		this.setSummonedMonster(true);
		monster.setLocation(Location.FIELD);
		this.getField().getHand().remove(monster);
		return true;
	}
	public boolean setSpell(SpellCard spell) throws IOException{ 
		
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		if(!this.getField().getHand().contains(spell))
			return false;
		if(Card.getBoard().isGameEnds())
			return false;
		if(this.getField().getSpellArea().size()>=5)
			throw new NoSpellSpaceException();
		this.getField().addSpellToField(spell, null, true);
		return true;
	}

	public boolean activateSpell(SpellCard spell, MonsterCard monster) throws Exception{ 
		
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		if(Card.getBoard().isGameEnds())
			return false;
		if(spell instanceof ChangeOfHeart){
			if (Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
				throw new NoMonsterSpaceException();
			if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()==0)
				throw new CannotAddChangeOfHeartException();
		}
		else if (spell instanceof MonsterReborn){
			if (Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
				throw new NoMonsterSpaceException();
			ArrayList<Card> grave1 = Card.getBoard().getActivePlayer().getField().getGraveyard();
			ArrayList<Card> grave2 = Card.getBoard().getOpponentPlayer().getField().getGraveyard();
			boolean foundMonster = false;
			for(int i=0; i<grave1.size() && !foundMonster; i++)
				if(grave1.get(i) instanceof MonsterCard)
					foundMonster = true;
			for(int i=0; i<grave2.size() && !foundMonster; i++)
				if(grave2.get(i) instanceof MonsterCard)
					foundMonster = true;
			if(!foundMonster)
				throw new CannotAddMonsterRebornException();	
		}
		else if (spell instanceof MagePower){
			if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()==0 && Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()==0)
				throw new CannotAddMagePowerException();
		}
		
		if(spell.getLocation()== Location.HAND){
			if(this.getField().getSpellArea().size()>=5)
				throw new NoSpellSpaceException();
			else
				this.getField().addSpellToField(spell, monster, false);
		}else{
			if(spell.getLocation()== Location.FIELD)
				this.getField().activateSetSpell(spell, monster);
		}
		return true;
	}

	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster) throws Exception {
		
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		if( Card.getBoard().isGameEnds())
			return false;
		if(this.getField().getPhase()!=Phase.BATTLE)
			throw new WrongPhaseException();
		if(activeMonster.isAttacked())
			throw new MonsterMultipleAttackException();
		if(activeMonster.getMode()==Mode.DEFENSE)
			throw new DefenseMonsterAttackException();
		if(!Card.getBoard().getOpponentPlayer().getField().getMonstersArea().contains(opponentMonster))
			throw new WrongAttackedMonsterException();
		
		activeMonster.action(opponentMonster);
		activeMonster.setAttacked(true);
		return true;
	}

	public boolean declareAttack(MonsterCard activeMonster)throws Exception {


		if(this == Card.getBoard().getOpponentPlayer()) 
			return false; 
		if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()>0)
			return false;
		if(Card.getBoard().isGameEnds()) 
			return false;
		if( this.getField().getPhase()!=Phase.BATTLE )
			throw new WrongPhaseException();
		if(activeMonster.isAttacked()) 
			throw new MonsterMultipleAttackException();
		if(activeMonster.getMode()==Mode.DEFENSE) 
			throw new DefenseMonsterAttackException();
		String soundName = "sounds/Monster Destroyed.wav";    
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
		activeMonster.action();
		activeMonster.setAttacked(true);
		return true;
	}

	@Override
	public void addCardToHand() {
		if (this.getField().getDeck().getDeck().size()==0)
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		else	
			this.getField().addCardToHand();

	}

	@Override
	public void addNCardsToHand(int n) {
		if(Card.getBoard().isGameEnds())
			return;
		if (this.getField().getDeck().getDeck().size()<n  || Card.getBoard().isGameEnds())
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		else
			this.getField().addNCardsToHand(n);

	}
	public void endPhase() throws Exception{
		if(Card.getBoard().isGameEnds())
			return;
		Phase current = this.getField().getPhase();
		if (current == Phase.MAIN1)
			current = Phase.BATTLE;
		else if (current == Phase.BATTLE)
			current = Phase.MAIN2;
		else if (current == Phase.MAIN2)
			this.endTurn();
		this.getField().setPhase(current);
	}

	@Override
	public boolean endTurn() throws Exception{


		if(this == Card.getBoard().getOpponentPlayer() )
			return false; 
		if(Card.getBoard().isGameEnds())
			return false; 
		reInitialize();
		Card.getBoard().nextPlayer();
		String soundName = "sounds/Draw Card (2).wav";    
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
		
		return true;
	}

	public void reInitialize(){
		if (this == Card.getBoard().getOpponentPlayer())
			return;
		for(MonsterCard c:this.getField().getMonstersArea()){
			c.setAttacked(false);
			c.setSwitched(false);
		}
	}

	@Override
	public boolean switchMonsterMode(MonsterCard monster) {

		if(this == Card.getBoard().getOpponentPlayer())
			return false; 
		if(monster.isSwitched())
			return false;
		if(!this.getField().getMonstersArea().contains(monster))
			return false; 
		if(this.getField().getPhase()==Phase.BATTLE) 
			throw new WrongPhaseException();



		if (monster.getMode()==Mode.ATTACK)
		{
			monster.setMode(Mode.DEFENSE);
			monster.setHidden(true);
		}
		else{
			monster.setMode(Mode.ATTACK);
			monster.setHidden(false);
		}
		monster.setSwitched(true);
		return true;
	}
	




}
