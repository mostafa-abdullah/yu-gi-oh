package eg.edu.guc.yugioh.cards;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MonsterCard extends Card {

	public BufferedImage getImage() {
		return image;
	}


	private BufferedImage image;
	


	private int level; 
	private int defensePoints;
	private int attackPoints;
	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}


	private boolean attacked;
	public boolean isSwitched() {
		return switched;
	}

	public void setSwitched(boolean switched) {
		this.switched = switched;
	}


	private boolean switched;
	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}


	private Mode mode = Mode.DEFENSE;

	public MonsterCard(String name, String description, int level, int attack, int defence) throws IOException{
		super(name,description);
		this.level = level ;
		this.attackPoints = attack ;
		this.defensePoints = defence;
		try{
			
			this.image = ImageIO.read(new File("art/cards/monsters/"+this.getName()+".jpg"));
		}
		catch(Exception e){
			System.out.println(this.getName());
			
			this.image = ImageIO.read(new File("art/cards/monsters/blank.jpg"));
		}
		attacked = false;
		switched = false;
	}

	public int getLevel(){
		return this.level;
	}

	public int getDefensePoints(){
		return this.defensePoints;
	}

	public int getAttackPoints(){
		return this.attackPoints;
	}



	public void setDefensePoints(int defense){
		this.defensePoints = defense; 
	}

	public void setAttackPoints(int attack){
		this.attackPoints = attack;
	}


	public void action() {
		int pLifePoints = Card.getBoard().getOpponentPlayer().getLifePoints();

		Card.getBoard().getOpponentPlayer().setLifePoints(pLifePoints-this.getAttackPoints());
		if (Card.getBoard().getOpponentPlayer().getLifePoints()<=0)
			Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
	}

	public void action(MonsterCard monster) {
		int pLifePoints = Card.getBoard().getOpponentPlayer().getLifePoints();
		int aLifePoints = Card.getBoard().getActivePlayer().getLifePoints();

		int pAttack = monster.getAttackPoints();
		int pDef = monster.getDefensePoints();
		int aAttack = this.getAttackPoints();
		int aDef = this.getDefensePoints();

		boolean lifePointsAffected = true;
		
		if(monster.getMode()==Mode.ATTACK){ 
			if(aAttack > pAttack) {
				pLifePoints -= aAttack-pAttack;
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
			}
			else{
				if(aAttack < pAttack) { 
					aLifePoints -= pAttack - aAttack;
					Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
				}else{
					Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
					Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
					lifePointsAffected=false;
				}

			}
		}else { 
			lifePointsAffected=false;
			if(aAttack>pDef){
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
				
			}else if (aAttack < pDef){
				aLifePoints -= pDef-aAttack;
				lifePointsAffected=true;
			}
		}

		if(lifePointsAffected){
			try{
				String soundName = "sounds/Life Point Cycle.wav";
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			}
			catch(Exception e1){

			}
		}

		if(pLifePoints <= 0 ) Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
		else if(aLifePoints<=0)  Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());

		Card.getBoard().getActivePlayer().setLifePoints(aLifePoints);
		Card.getBoard().getOpponentPlayer().setLifePoints(pLifePoints);

	}











}


