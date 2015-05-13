package eg.edu.guc.yugioh.exceptions;

public class WrongPhaseException extends RuntimeException{
	public WrongPhaseException(){
		super("You cannot perform this action during the current phase!");
	}
}
