package eg.edu.guc.yugioh.exceptions;

public class WrongAttackedMonsterException extends RuntimeException{

	public WrongAttackedMonsterException() {
		super("You can only attack monsters on your opponent's field!");
	}

}
