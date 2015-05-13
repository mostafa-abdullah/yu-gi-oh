package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class AbsorbPower extends SpellCard implements Absorbing{

	public AbsorbPower(String name, String description) throws IOException {
		super(name,description);
		
	}



	@Override
	public int absorbPower() {
		int AttackPoints = 0;
		ArrayList<MonsterCard> Oppmonsters = Card.getBoard().getOpponentPlayer().getField().getMonstersArea();
		for(int i=0; i<Oppmonsters.size(); i++) { 
			MonsterCard OppMonster = Oppmonsters.get(i);
			if( OppMonster.getMode()==Mode.ATTACK){
				AttackPoints += OppMonster.getAttackPoints();
			}
				
		}
		return AttackPoints;
	}

	@Override
	public void action(MonsterCard monster) {
     int  AttackPoints 	= absorbPower();
 

    int num =0 ;
    
     ArrayList<MonsterCard> ActiveMonsters = Card.getBoard().getActivePlayer().getField().getMonstersArea();
     for(int j=0; j<ActiveMonsters.size(); j++) { 
    	 MonsterCard MyMonster = ActiveMonsters.get(j);
    	 if(MyMonster.getMode()==Mode.ATTACK){
    		 num++;
    	 }
     }
     int Attack = AttackPoints / num;
		for(int i=0; i<ActiveMonsters.size(); i++) {
			MonsterCard MyMonster = ActiveMonsters.get(i);
			if(MyMonster.getMode()==Mode.ATTACK){
				MyMonster.setAttackPoints(MyMonster.getAttackPoints()+Attack);
			}
			
	}
	

}
}
