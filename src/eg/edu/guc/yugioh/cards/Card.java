package eg.edu.guc.yugioh.cards;

import java.io.IOException;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.Raigeki;

public abstract class Card {
	private final String name ; 
	private final String description; 
	private boolean isHidden;

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	private Location location;
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	private static Board board;


	public static void setBoard(Board board) {
		Card.board = board;
	}

	public Card (String name , String description) { 
		this.name = name ; 
		this.description = description ;
		isHidden = true;
		location = Location.DECK;
		//board = new Board();
	}

	abstract public void action(MonsterCard monster) throws IOException;

	public String getName() { 
		return this.name;
	}

	public String getDescription() { 
		return this.description;
	}

	public static Board getBoard() {
		return board;
	}







}


