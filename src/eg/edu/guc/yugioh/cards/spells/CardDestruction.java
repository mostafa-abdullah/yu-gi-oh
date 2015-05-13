package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class CardDestruction extends SpellCard{

	public CardDestruction(String name, String description) throws IOException {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster){
		
		
		//player 1
		//set cards number
		int num1 = Card.getBoard().getActivePlayer().getField().getHand().size();
		//discard cards to graveyard
		for (Card c:Card.getBoard().getActivePlayer().getField().getHand()){
			Card.getBoard().getActivePlayer().getField().getGraveyard().add(c);
			c.setLocation(Location.GRAVEYARD);
		}
		//empty hand of player 1
		Card.getBoard().getActivePlayer().getField().setHand(new ArrayList<Card>());
		//draw new cards
		if(Card.getBoard().getActivePlayer().getField().getDeck().getDeck().size()<num1)
		{
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			return;
		}
		else{
			Card.getBoard().getActivePlayer().getField().addNCardsToHand(num1);
		}
		
		
		//player 2
		//set cards number
		int num2 = Card.getBoard().getOpponentPlayer().getField().getHand().size();
		//discard cards to graveyard
		for (Card c:Card.getBoard().getOpponentPlayer().getField().getHand()){
			Card.getBoard().getOpponentPlayer().getField().getGraveyard().add(c);
			c.setLocation(Location.GRAVEYARD);
		}
		//empty hand of player 2
		Card.getBoard().getOpponentPlayer().getField().setHand(new ArrayList<Card>());
		//draw new cards
		if(Card.getBoard().getOpponentPlayer().getField().getDeck().getDeck().size()<num2)
		{
			Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
			return;
		}
		else
			Card.getBoard().getOpponentPlayer().getField().addNCardsToHand(num2);
		
		
		
		
	}

}
