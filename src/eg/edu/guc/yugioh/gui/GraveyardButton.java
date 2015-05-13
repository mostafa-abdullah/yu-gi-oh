package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

public class GraveyardButton extends JButton{
	Player player;
	Card lastCard;
	public Card getLastCard() {
		return lastCard;
	}
	public void setLastCard(Card lastCard) {
		this.lastCard = lastCard;
	}
	public GraveyardButton(Player player) throws IOException {
		super();
		this.player = player;
		this.setPreferredSize(new Dimension(68,100));
		ArrayList<Card> graveyard = player.getField().getGraveyard();
		if(graveyard.size()>0){
			if(graveyard.get(graveyard.size()-1) instanceof MonsterCard){
				MonsterCard lastCard = (MonsterCard)graveyard.get(graveyard.size()-1);
				BufferedImage graveyardImage = lastCard.getImage();
				Image cardImage = graveyardImage.getScaledInstance(120, 140,Image.SCALE_SMOOTH);
				this.setIcon(new ImageIcon(cardImage));
			}
			else{
				SpellCard lastCard = (SpellCard)graveyard.get(graveyard.size()-1);
				BufferedImage graveyardImage = lastCard.getImage();
				Image cardImage = graveyardImage.getScaledInstance(120, 140,Image.SCALE_SMOOTH);
				this.setIcon(new ImageIcon(cardImage));
			}
			
		}
		else{
			BufferedImage graveyardImage = ImageIO.read(new File("art/cards/graveyard.jpg"));
			Image cardImage = graveyardImage.getScaledInstance(120, 140,Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(cardImage));
			
		}
	}




}
