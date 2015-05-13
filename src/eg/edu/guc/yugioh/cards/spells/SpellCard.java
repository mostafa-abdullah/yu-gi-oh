package eg.edu.guc.yugioh.cards.spells;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

abstract public class SpellCard extends Card {
	private BufferedImage image;
	public BufferedImage getImage() {
		return image;
	}

	public SpellCard(String name, String description) throws IOException{
		super(name , description);
		try{
			this.image = ImageIO.read(new File("art/cards/spells/"+this.getName()+".jpg"));
		}
			catch(Exception e){
				this.image = ImageIO.read(new File("art/cards/spells/blank.jpg"));
			}
	}

	@Override
	abstract public void action(MonsterCard monster) throws IOException;
	
	
}