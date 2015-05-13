package eg.edu.guc.yugioh.exceptions;

public class DefenseMonsterAttackException extends RuntimeException {
	public DefenseMonsterAttackException(){
		super("You cannot attack with a monster in defense mode!");
	}
}


