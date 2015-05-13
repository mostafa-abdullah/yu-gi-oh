package eg.edu.guc.yugioh.board.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.listeners.Main;

public class Computer extends Player {

	public Computer() throws Exception {
		super("Computer");
	}
	Timer t;

	public void computerTurn() throws Exception{

		Computer p = this;
		 t = new Timer(350, new ActionListener() {
			int phase=1;
			public void actionPerformed(ActionEvent e) {
				if(phase == 1){
					try{
						p.mainPhase1();
					}
					catch(Exception e1){
						//do nothing
					}
					p.updateGUI();
				}
				else if (phase==2){
					try{
						p.endPhase();
						p.battlePhase();
						p.updateGUI();
					}
					catch(Exception e2){

					}
				}
				else if (phase==3){
					try{
						p.endPhase();
						p.mainPhase2();
						p.updateGUI();
					}
					catch(Exception e2){

					}
				}
				else{
					try{endPhase();
					updateGUI();
					}
					catch(Exception e2){
						
					}
					((Timer)e.getSource()).stop();
				}
				phase++;
			}
		});
		t.setRepeats(true);
		t.start();
		


		
	}

	public void mainPhase1() throws Exception{
		for (Card c:this.getField().getHand())
			System.out.println(c.getName());
		System.out.println("---");
		SpellCard containsCardDestruction = containsCardDestruction();
		SpellCard containsChangeOfHeart = containsChangeOfHeart();
		SpellCard containsDarkHole = containsDarkHole();
		SpellCard containsGracefulDice = containsGracefulDice();
		SpellCard containsHarpieFeather = containsHarpieFeather();
		SpellCard containsHeavyStorm = containsHeavyStorm();
		SpellCard containsMagePower = containsMagePower();
		SpellCard containsPotOfGreed= containsPotOfGreed();
		SpellCard containsRaigeki= containsRaigeki();
		SpellCard containsMonsterReborn  = containsMonsterReborn();

		MonsterCard maxAttackMonsterInHand = maxAttackMonsterInHand();
		MonsterCard maxAttackMonsterInHandOneSacrifice = maxAttackMonsterInHandOneSacrifice();
		MonsterCard maxAttackMonsterInHandTwoSacrifice = maxAttackMonsterInHandTwoSacrifice();
		MonsterCard maxDefenseMonsterInHand = maxDefenseMonsterInHand();
		MonsterCard minAttackMonsterInHand = minAttackMonsterInHand();

		MonsterCard maxAttackMonsterInMyField = maxAttackMonsterInMyField();
		MonsterCard maxAttackMonsterInOpponentField = maxAttackMonsterInOpponentField();

		MonsterCard maxAttackMonsterInMyGraveyard = maxAttackMonsterInMyGraveyard();
		MonsterCard maxAttackMonsterInOpponentGraveyard = maxAttackMonsterInOpponentGraveyard();


		if(containsPotOfGreed!=null){
			if(this.getField().getDeck().getDeck().size()>3){
				this.activateSpell(containsPotOfGreed, null);
				
				containsCardDestruction = containsCardDestruction();
				containsChangeOfHeart = containsChangeOfHeart();
				containsDarkHole = containsDarkHole();
				containsGracefulDice = containsGracefulDice();
				containsHarpieFeather = containsHarpieFeather();
				containsHeavyStorm = containsHeavyStorm();
				containsMagePower = containsMagePower();
				containsPotOfGreed= containsPotOfGreed();
				containsRaigeki= containsRaigeki();

				maxAttackMonsterInHand = maxAttackMonsterInHand();
				maxDefenseMonsterInHand = maxDefenseMonsterInHand();
				minAttackMonsterInHand = minAttackMonsterInHand();
				maxAttackMonsterInHandOneSacrifice = maxAttackMonsterInHandOneSacrifice();
				maxAttackMonsterInHandTwoSacrifice = maxAttackMonsterInHandTwoSacrifice();
			}
		}
		if(containsHarpieFeather!=null){
			if(Card.getBoard().getActivePlayer().getField().getSpellArea().size()>1){
				this.activateSpell(containsHarpieFeather, null);
				
			}
			containsHarpieFeather = containsHarpieFeather();
		}
		if(containsHeavyStorm!=null){
			if(Card.getBoard().getActivePlayer().getField().getSpellArea().size()>=2 && this.getField().getSpellArea().size()<=1){
				this.activateSpell(containsHeavyStorm, null);
				
			}
			containsHeavyStorm = containsHeavyStorm();
		}
		if(containsRaigeki!=null){
			boolean activated = false;
			if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()>2)
				activated = true;
			else if(maxAttackMonsterInMyField == null && maxAttackMonsterInOpponentField!=null)
				activated = true;
			else if(maxAttackMonsterInMyField !=null && maxAttackMonsterInOpponentField !=null && maxAttackMonsterInMyField.getAttackPoints()<maxAttackMonsterInOpponentField.getAttackPoints())
				activated = true;
			if(activated){
				this.activateSpell(containsRaigeki, null);
				
				maxAttackMonsterInOpponentField = maxAttackMonsterInOpponentField();
				containsRaigeki = containsRaigeki();
			}
			
		}

		if(containsDarkHole != null){
			boolean activated = false;
			if(maxAttackMonsterInMyField == null && maxAttackMonsterInOpponentField!=null){
				activated = true;
			}
			else if(maxAttackMonsterInMyField !=null && maxAttackMonsterInOpponentField !=null && maxAttackMonsterInMyField.getAttackPoints()<maxAttackMonsterInOpponentField.getAttackPoints() && maxAttackMonsterInHand==null){
				activated = true;
			}
			else if(maxAttackMonsterInMyField !=null && maxAttackMonsterInOpponentField !=null  && maxAttackMonsterInHand!=null && maxAttackMonsterInMyField.getAttackPoints()<maxAttackMonsterInOpponentField.getAttackPoints() && maxAttackMonsterInOpponentField.getAttackPoints()>maxAttackMonsterInHand.getAttackPoints()){
				activated = true;
				
			}
			if(activated){
				this.activateSpell(containsDarkHole, null);
				System.out.println("ATTENTION: DARK HOLE!");
				maxAttackMonsterInMyField = maxAttackMonsterInMyField();
				maxAttackMonsterInOpponentField = maxAttackMonsterInOpponentField();
				containsDarkHole = containsDarkHole();
				
			}
			
		}

		if(containsChangeOfHeart!=null){
			if(maxAttackMonsterInOpponentField!=null && maxAttackMonsterInOpponentField.getAttackPoints()>1500)
			{
				this.activateSpell(containsChangeOfHeart, maxAttackMonsterInOpponentField);
				containsChangeOfHeart = containsChangeOfHeart();
				maxAttackMonsterInOpponentField = maxAttackMonsterInOpponentField();
				maxAttackMonsterInMyField = maxAttackMonsterInMyField();
				
			}
		}
		//summon monster
		if(maxAttackMonsterInOpponentField==null){
			//check two sacrifices
			if(this.getField().getMonstersArea().size()>=2 && maxAttackMonsterInHandTwoSacrifice!=null){
				MonsterCard min1=null;
				MonsterCard min2=null;
				int attack1 = 100000;
				int attack2 = 100000;
				for (MonsterCard c:this.getField().getMonstersArea()){
					if(c.getAttackPoints()<attack1){
						attack2=attack1;
						min2=min1;
						min1=c;
						attack1=c.getAttackPoints();
					}
					else if(c.getAttackPoints()<attack2){
						attack2=c.getAttackPoints();
						min2=c;
					}
				}
				if(maxAttackMonsterInHandTwoSacrifice.getAttackPoints()>attack2){
					ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
					sacrifices.add(min1);
					sacrifices.add(min2);
					this.summonMonster(maxAttackMonsterInHandTwoSacrifice, sacrifices);
					maxAttackMonsterInHandTwoSacrifice = maxAttackMonsterInHandTwoSacrifice();
					maxAttackMonsterInMyField = maxAttackMonsterInMyField();
					minAttackMonsterInHand = minAttackMonsterInHand();
				}
			}
			//check one sacrifice
			else if(this.getField().getMonstersArea().size()>=1 && maxAttackMonsterInHandOneSacrifice!=null){
				MonsterCard min1=null;
				int attack1 = 100000;

				for (MonsterCard c:this.getField().getMonstersArea()){
					if(c.getAttackPoints()<attack1){
						min1=c;
						attack1=c.getAttackPoints();
					}
				}
				if(maxAttackMonsterInHandOneSacrifice.getAttackPoints()>attack1){
					ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
					sacrifices.add(min1);
					this.summonMonster(maxAttackMonsterInHandOneSacrifice, sacrifices);
					maxAttackMonsterInHandOneSacrifice = maxAttackMonsterInHandTwoSacrifice();
					maxAttackMonsterInMyField = maxAttackMonsterInMyField();
					minAttackMonsterInHand = minAttackMonsterInHand();
				}
			}
			if(maxAttackMonsterInHand!=null){
				this.summonMonster(maxAttackMonsterInHand);
				

			}

		}
		else{
			//opponent monster area has monsters
			if(maxAttackMonsterInMyField==null){
				if(maxAttackMonsterInHand!=null){
					if(maxAttackMonsterInHand.getAttackPoints()>=maxAttackMonsterInOpponentField.getAttackPoints() || (containsMagePower!=null && maxAttackMonsterInHand.getAttackPoints()*(500+countSpells())>=maxAttackMonsterInOpponentField.getAttackPoints()) || (containsGracefulDice!=null && maxAttackMonsterInHand.getAttackPoints()+400>=maxAttackMonsterInOpponentField.getAttackPoints()) )
						this.summonMonster(maxAttackMonsterInHand);
					else if(maxAttackMonsterInHand.getAttackPoints()<maxAttackMonsterInOpponentField.getAttackPoints()){
						if(maxDefenseMonsterInHand.getDefensePoints()>maxAttackMonsterInOpponentField.getAttackPoints())
							setMonster(maxDefenseMonsterInHand);
						else
							setMonster(minAttackMonsterInHand);
					}
				}
			}
			else{
				//check two sacrifices
				if(this.getField().getMonstersArea().size()>=2 && maxAttackMonsterInHandTwoSacrifice !=null && (maxAttackMonsterInHandTwoSacrifice.getAttackPoints()>maxAttackMonsterInOpponentField.getAttackPoints()  || (containsMagePower!=null && maxAttackMonsterInHandTwoSacrifice.getAttackPoints()*(500+countSpells())>=maxAttackMonsterInOpponentField.getAttackPoints()) || (containsGracefulDice!=null && maxAttackMonsterInHandTwoSacrifice.getAttackPoints()+400>=maxAttackMonsterInOpponentField.getAttackPoints()) )){

					MonsterCard min1=null;
					MonsterCard min2=null;
					int attack1 = 100000;
					int attack2 = 100000;
					for (MonsterCard c:this.getField().getMonstersArea()){
						if(c.getAttackPoints()<=attack1 && c!=min1){
							attack2=attack1;
							min2=min1;
							min1=c;
							attack1=c.getAttackPoints();
						}
						else if(c.getAttackPoints()<attack2){
							attack2=c.getAttackPoints();
							min2 = c;
						}
					}
					if(maxAttackMonsterInHandTwoSacrifice.getAttackPoints()>attack2){
						ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
						sacrifices.add(min1);
						sacrifices.add(min2);
						this.summonMonster(maxAttackMonsterInHandTwoSacrifice, sacrifices);
						maxAttackMonsterInHandTwoSacrifice = maxAttackMonsterInHandTwoSacrifice();
						maxAttackMonsterInMyField = maxAttackMonsterInMyField();
						minAttackMonsterInHand = minAttackMonsterInHand();
					}

				}
				//check one sacrifice
				else if(this.getField().getMonstersArea().size()>=1 && maxAttackMonsterInHandOneSacrifice!=null && (maxAttackMonsterInHandOneSacrifice.getAttackPoints()>maxAttackMonsterInOpponentField.getAttackPoints() || maxAttackMonsterInMyField.getAttackPoints()>maxAttackMonsterInOpponentField.getAttackPoints()  || (containsMagePower!=null && maxAttackMonsterInHandOneSacrifice.getAttackPoints()*(500+countSpells())>=maxAttackMonsterInOpponentField.getAttackPoints()) || (containsGracefulDice!=null && maxAttackMonsterInHandOneSacrifice.getAttackPoints()+400>=maxAttackMonsterInOpponentField.getAttackPoints()) )){
					MonsterCard min1=null;
					int attack1 = 100000;

					for (MonsterCard c:this.getField().getMonstersArea()){
						if(c.getAttackPoints()<attack1){
							min1=c;
							attack1=c.getAttackPoints();
						}
					}
					if(maxAttackMonsterInHandOneSacrifice.getAttackPoints()>attack1){
						ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
						sacrifices.add(min1);
						this.summonMonster(maxAttackMonsterInHandOneSacrifice, sacrifices);
						maxAttackMonsterInHandOneSacrifice = maxAttackMonsterInHandTwoSacrifice();
						maxAttackMonsterInMyField = maxAttackMonsterInMyField();
						minAttackMonsterInHand = minAttackMonsterInHand();
					}
				}
				if(maxAttackMonsterInHand!=null && (maxAttackMonsterInHand.getAttackPoints()>maxAttackMonsterInOpponentField.getAttackPoints() || maxAttackMonsterInMyField.getAttackPoints()>maxAttackMonsterInOpponentField.getAttackPoints()  || (containsMagePower!=null && maxAttackMonsterInHand.getAttackPoints()*(500+countSpells())>=maxAttackMonsterInOpponentField.getAttackPoints()) || (maxAttackMonsterInHand!=null && maxAttackMonsterInHandOneSacrifice.getAttackPoints()+400>=maxAttackMonsterInOpponentField.getAttackPoints()))){
					this.summonMonster(maxAttackMonsterInHand);
					maxAttackMonsterInHand = maxAttackMonsterInHand();
					maxAttackMonsterInMyField = maxAttackMonsterInMyField();
					minAttackMonsterInHand = minAttackMonsterInHand();
				}
				else if(maxDefenseMonsterInHand!=null && maxDefenseMonsterInHand.getDefensePoints()>=maxAttackMonsterInOpponentField.getAttackPoints())
					setMonster(maxDefenseMonsterInHand);
				else if(maxDefenseMonsterInHand!=null && maxDefenseMonsterInHand.getDefensePoints()<maxAttackMonsterInOpponentField.getAttackPoints())
					setMonster(minAttackMonsterInHand());
			}
		}
		maxAttackMonsterInHand = maxAttackMonsterInHand();
		maxAttackMonsterInMyField = maxAttackMonsterInMyField();
		minAttackMonsterInHand = minAttackMonsterInHand();
		

		if(containsMonsterReborn!=null){
			if(maxAttackMonsterInMyGraveyard!=null || maxAttackMonsterInOpponentGraveyard!=null){
				MonsterCard max;
				if(maxAttackMonsterInMyGraveyard==null)
					max = maxAttackMonsterInOpponentGraveyard;
				else if(maxAttackMonsterInOpponentGraveyard==null)
					max = maxAttackMonsterInMyGraveyard;
				else{
					if(maxAttackMonsterInMyGraveyard.getAttackPoints()>maxAttackMonsterInOpponentGraveyard.getAttackPoints())
						max = maxAttackMonsterInMyGraveyard;
					else
						max = maxAttackMonsterInOpponentGraveyard;
				}
				boolean activated = false;
				if(maxAttackMonsterInOpponentField==null)
					activated =true;
				else if(maxAttackMonsterInMyField==null && maxAttackMonsterInOpponentField!=null && max.getAttackPoints()>maxAttackMonsterInOpponentField.getAttackPoints())
					activated = true;
				else if(maxAttackMonsterInMyField!=null && maxAttackMonsterInOpponentField!=null && (maxAttackMonsterInMyField.getAttackPoints()>maxAttackMonsterInOpponentField.getAttackPoints() || max.getAttackPoints()>=maxAttackMonsterInOpponentField.getAttackPoints()))
					activated = true;
				if(activated){
					this.activateSpell(containsMonsterReborn, null);
					
				}
				maxAttackMonsterInMyGraveyard = maxAttackMonsterInMyGraveyard();
				maxAttackMonsterInOpponentGraveyard = maxAttackMonsterInOpponentGraveyard();
				maxAttackMonsterInMyField = maxAttackMonsterInMyField();
				containsMonsterReborn = containsMonsterReborn();


			}
		}
		if(containsGracefulDice!=null){
			if(maxAttackMonsterInOpponentField==null && maxAttackMonsterInMyField!=null && maxAttackMonsterInMyField.getAttackPoints()>1500){
				this.activateSpell(containsGracefulDice, null);
				
			}
			else if(maxAttackMonsterInOpponentField!=null && maxAttackMonsterInMyField!=null && maxAttackMonsterInOpponentField.getAttackPoints()<=maxAttackMonsterInMyField.getAttackPoints()+500){
				this.activateSpell(containsGracefulDice, null);
				
			}
			containsGracefulDice = containsGracefulDice();
		}


		if(containsMagePower!=null){
			if(maxAttackMonsterInMyField!=null){
				ArrayList<SpellCard> spells = new ArrayList<SpellCard>();
				for(Card c:this.getField().getHand()){
					if(c instanceof SpellCard && c!=containsMagePower){
						spells.add((SpellCard)c);
					}
				}
				while(!spells.isEmpty()){
					this.setSpell(spells.remove(0));
				}
				this.activateSpell(containsMagePower, maxAttackMonsterInMyField);
				
				containsMagePower = containsMagePower();
			}
		}
		if(containsCardDestruction!=null){
			if(this.getField().getHand().size()>1 && this.getField().getDeck().getDeck().size()>this.getField().getHand().size()+2 && (maxAttackMonsterInHand==null || maxAttackMonsterInHand.getAttackPoints()<2000) && maxAttackMonsterInHandOneSacrifice==null && maxAttackMonsterInHandTwoSacrifice==null){
				if(containsRaigeki!=null)
					this.setSpell(containsRaigeki);
				if(containsDarkHole!=null)
					this.setSpell(containsDarkHole);
				if(containsMagePower!=null)
					this.setSpell(containsMagePower);
				if(containsMonsterReborn!=null)
					this.setSpell(containsMonsterReborn);
				this.activateSpell(containsCardDestruction, null);
				

				minAttackMonsterInHand = minAttackMonsterInHand();
				maxAttackMonsterInHand = maxAttackMonsterInHand();
				maxDefenseMonsterInHand = maxDefenseMonsterInHand();
				containsCardDestruction = containsCardDestruction();
			}

		}

		//switch monsters' mode

		if(maxAttackMonsterInMyField!=null && maxAttackMonsterInOpponentField!=null && maxAttackMonsterInMyField.getAttackPoints()<maxAttackMonsterInOpponentField.getAttackPoints()){
			for (MonsterCard c:this.getField().getMonstersArea())
				if(c.getMode()==Mode.ATTACK){
					this.switchMonsterMode(c);
					
				}
		}
		else if(maxAttackMonsterInOpponentField==null || maxAttackMonsterInMyField!=null && maxAttackMonsterInOpponentField!=null && maxAttackMonsterInMyField.getAttackPoints()>maxAttackMonsterInOpponentField.getAttackPoints()){
			for (MonsterCard c:this.getField().getMonstersArea())
				if(c.getMode()==Mode.DEFENSE){
					this.switchMonsterMode(c);
					
				}
		}


	}



	private int countSpells() {
		int count = 0;
		for(Card c: this.getField().getHand())
			if(c instanceof SpellCard && !(c instanceof MagePower))
				count++;
		return count;
	}

	private MonsterCard minAttackMonsterInHand() {
		int minAttack = 100000;
		MonsterCard min = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getAttackPoints()<minAttack && ((MonsterCard)c).getLevel()<=4){
				min = (MonsterCard) c;
				minAttack = min.getAttackPoints();
			}
		}
		return min;
	}



	private MonsterCard maxAttackMonsterInHandTwoSacrifice() {
		int maxAttack = 0;
		MonsterCard max = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getAttackPoints()>maxAttack && ((MonsterCard)c).getLevel()>6){
				max = (MonsterCard) c;
				maxAttack = max.getAttackPoints();
			}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInHandOneSacrifice() {
		int maxAttack = 0;
		MonsterCard max = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getAttackPoints()>maxAttack && (((MonsterCard)c).getLevel()==5 || ((MonsterCard)c).getLevel()==6)){
				max = (MonsterCard) c;
				maxAttack = max.getAttackPoints();
			}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInMyGraveyard() {
		MonsterCard max = null;
		int maxAttack = 0;
		for (Card c:this.getField().getGraveyard()){
			if(c instanceof MonsterCard)
				if(((MonsterCard)c).getAttackPoints()>maxAttack){
					max = (MonsterCard)c;
					maxAttack = max.getAttackPoints();
				}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInOpponentGraveyard() {
		MonsterCard max = null;
		int maxAttack = 0;
		for (Card c:Card.getBoard().getOpponentPlayer().getField().getGraveyard()){
			if(c instanceof MonsterCard)
				if(((MonsterCard)c).getAttackPoints()>maxAttack){
					max = (MonsterCard)c;
					maxAttack = max.getAttackPoints();
				}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInOpponentField() {
		MonsterCard max = null;
		int maxAttack = 0;

		for (MonsterCard c:Card.getBoard().getOpponentPlayer().getField().getMonstersArea())
			if(c.getAttackPoints()>maxAttack){
				maxAttack = c.getAttackPoints();
				max = c;
			}
		return max;
	}

	private MonsterCard maxAttackMonsterInMyField() {
		MonsterCard max = null;
		int maxAttack = 0;

		for (MonsterCard c:this.getField().getMonstersArea())
			if(c.getAttackPoints()>maxAttack){
				maxAttack = c.getAttackPoints();
				max = c;
			}
		return max;
	}

	private MonsterCard maxDefenseMonsterInHand() {

		int maxDefense = 0;
		MonsterCard max = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getDefensePoints()>maxDefense && ((MonsterCard)c).getLevel()<=4){
				max = (MonsterCard) c;
				maxDefense = max.getDefensePoints();
			}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInHand() {
		int maxAttack = 0;
		MonsterCard max = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getAttackPoints()>maxAttack && ((MonsterCard)c).getLevel()<=4){
				max = (MonsterCard) c;
				maxAttack = max.getAttackPoints();
			}
		}
		return max;
	}

	private SpellCard containsRaigeki() {

		for (Card c:this.getField().getHand()){
			if (c.getName().equals("Raigeki"))
				return (Raigeki)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c.getName().equals("Raigeki"))
				return c;
		}
		return null;
	}

	private SpellCard containsPotOfGreed() {
		// TODO Auto-generated method stub
		for (Card c:this.getField().getHand()){
			if (c instanceof PotOfGreed)
				return (PotOfGreed)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c instanceof PotOfGreed)
				return c;
		}
		return null;
	}

	private SpellCard containsMagePower() {
		// TODO Auto-generated method stub
		for (Card c:this.getField().getHand()){
			if (c instanceof MagePower)
				return (MagePower)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c instanceof MagePower)
				return c;
		}
		return null;
	}
	private SpellCard containsMonsterReborn() {
		// TODO Auto-generated method stub
		for (Card c:this.getField().getHand()){
			if (c instanceof MonsterReborn)
				return (MonsterReborn)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c instanceof MonsterReborn)
				return c;
		}
		return null;
	}

	private SpellCard containsHeavyStorm() {
		// TODO Auto-generated method stub
		for (Card c:this.getField().getHand()){
			if (c instanceof HeavyStorm)
				return (HeavyStorm)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c instanceof HeavyStorm)
				return c;
		}
		return null;
	}

	private SpellCard containsHarpieFeather() {
		// TODO Auto-generated method stub
		for (Card c:this.getField().getHand()){
			if (c instanceof HarpieFeatherDuster)
				return (HarpieFeatherDuster)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c instanceof HarpieFeatherDuster)
				return c;
		}
		return null;
	}

	private SpellCard containsGracefulDice() {
		// TODO Auto-generated method stub
		for (Card c:this.getField().getHand()){
			if (c instanceof GracefulDice)
				return (GracefulDice)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c instanceof GracefulDice)
				return c;
		}
		return null;
	}

	private SpellCard containsDarkHole() {
		// TODO Auto-generated method stub
		for (Card c:this.getField().getHand()){
			if (c.getName().equals("Dark Hole"))
				return (DarkHole)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c.getName().equals("Dark Hole"))
				return c;
		}
		return null;
	}

	private SpellCard containsChangeOfHeart() {
		// TODO Auto-generated method stub
		for (Card c:this.getField().getHand()){
			if (c instanceof ChangeOfHeart)
				return (ChangeOfHeart)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c instanceof ChangeOfHeart)
				return c;
		}
		return null;
	}

	private SpellCard containsCardDestruction() {
		// TODO Auto-generated method stub
		for (Card c:this.getField().getHand()){
			if (c instanceof CardDestruction)
				return (CardDestruction)c;
		}
		for(SpellCard c:this.getField().getSpellArea()){
			if(c instanceof CardDestruction)
				return c;
		}
		return null;
	}

	public void battlePhase() throws Exception{
		ArrayList<MonsterCard> myMonsters = new ArrayList<MonsterCard>();
		for(MonsterCard c:this.getField().getMonstersArea())
			if(c.getMode()==Mode.ATTACK)
				myMonsters.add(c);

		if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()>0){
			sortByAttack(myMonsters);

			ArrayList<MonsterCard> opponentMonsters = Card.getBoard().getOpponentPlayer().getField().getMonstersArea();

			for(MonsterCard monster:myMonsters){
				if(opponentMonsters.size()==0)
					this.declareAttack(monster);
				else
					attackWithMonster(monster,opponentMonsters);
				opponentMonsters = Card.getBoard().getOpponentPlayer().getField().getMonstersArea();
			}
		}
		else{
			for(MonsterCard monster:myMonsters){
				if(monster.getMode().equals(Mode.ATTACK)){
					this.declareAttack(monster);
					
				}
			}
		}


	}

	private void attackWithMonster(MonsterCard monster,
			ArrayList<MonsterCard> opponentMonsters) throws Exception {
		int maxNumber = -1;
		MonsterCard max = null;
		for(MonsterCard oppMonster:opponentMonsters){
			if(oppMonster.getMode()==Mode.ATTACK && oppMonster.getAttackPoints()>maxNumber && monster.getAttackPoints()>oppMonster.getAttackPoints()){
				maxNumber = oppMonster.getAttackPoints();
				max = oppMonster;
			}
		}
		for(MonsterCard oppMonster:opponentMonsters){
			if(oppMonster.getMode()==Mode.DEFENSE && oppMonster.getDefensePoints()>maxNumber && monster.getAttackPoints()>oppMonster.getDefensePoints()){
				maxNumber = oppMonster.getDefensePoints();
				max = oppMonster;
			}
		}
		if(max!=null){
			this.declareAttack(monster, max);
			
		}
		

	}

	private void sortByAttack(ArrayList<MonsterCard> monsters) {
		for(int i=0; i<monsters.size()-1; i++){
			for (int j=0;j<monsters.size()-1-i; j++){
				if(monsters.get(j).getAttackPoints()<monsters.get(j+1).getAttackPoints()){
					MonsterCard temp = monsters.get(j);
					monsters.set(j, monsters.get(j+1));
					monsters.set(j+1, temp);
				}
			}
		}

	}

	public void mainPhase2(){
		//no need
	}


	public void updateGUI() {
		try{

			Main.controller.getActiveGraveAndDeck().updatePanel();
			Main.controller.getOpponentGraveAndDeck().updatePanel();
			Main.controller.getActiveHandPanel().updatePanel(Main.controller.getActiveHandPanel().getPlayer());
			Main.controller.getOpponentHandPanel().updatePanel(Main.controller.getOpponentHandPanel().getPlayer());
			Main.controller.getActiveInfoPanel().updatePanel(Main.controller.getActiveInfoPanel().getPlayer());
			Main.controller.getOpponentInfoPanel().updatePanel(Main.controller.getOpponentInfoPanel().getPlayer());
			Main.controller.getActiveMonstersPanel().updatePanel(Main.controller.getActiveMonstersPanel().getPlayer());
			Main.controller.getOpponentMonstersPanel().updatePanel(Main.controller.getOpponentMonstersPanel().getPlayer());
			Main.controller.getActiveSpellsPanel().updatePanel(Main.controller.getActiveSpellsPanel().getPlayer());
			Main.controller.getOpponentSpellsPanel().updatePanel(Main.controller.getOpponentSpellsPanel().getPlayer());
			Main.controller.getPhasePanel().updatePanel();
			Main.controller.addActionListenersToButtons();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
