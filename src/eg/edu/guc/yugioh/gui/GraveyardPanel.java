package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

public class GraveyardPanel extends JPanel{
   private GraveyardButton graveyardButton;
   	public GraveyardButton getGraveyardButton() {
	return graveyardButton;
}

	Player player;
   	JLabel graveText;
	public GraveyardPanel(Player player) throws IOException {
		super(); 
		this.player = player;
		this.setLayout(new GridLayout(2,1));
		this.setPreferredSize(new Dimension(100, 130));
		
		graveText = new JLabel("Graveyard ("+player.getField().getGraveyard().size()+")");
		graveyardButton = new GraveyardButton(player);
		
		this.add(graveText);
		this.add(graveyardButton);
	}
	
	public void updatePanel(Player player){
		ArrayList<Card> graveyard = player.getField().getGraveyard();
		
		if(graveyard.size()>0){

			if(graveyard.get(graveyard.size()-1) instanceof MonsterCard){
				MonsterCard c = (MonsterCard)graveyard.get(graveyard.size()-1);
				this.getGraveyardButton().setText(c.getName() + "\n" + c.getAttackPoints() + "\n" + c.getDefensePoints() + "\n" + c.getLevel());

			}
			else{
				SpellCard c = (SpellCard)graveyard.get(graveyard.size()-1);
				this.getGraveyardButton().setText(c.getName());
			}
		}
		else
			this.getGraveyardButton().setText("Empty");
		graveText.setText("Graveyard ("+player.getField().getGraveyard().size()+")");
	}

}
