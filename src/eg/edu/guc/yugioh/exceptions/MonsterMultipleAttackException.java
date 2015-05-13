package eg.edu.guc.yugioh.exceptions;

public class MonsterMultipleAttackException extends RuntimeException{
	public MonsterMultipleAttackException(){
		super("You cannot attack with this monster again!");
	}

}
