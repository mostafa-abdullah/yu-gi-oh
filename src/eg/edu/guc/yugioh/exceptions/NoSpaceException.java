package eg.edu.guc.yugioh.exceptions;

public class NoSpaceException extends RuntimeException{
	public NoSpaceException(){
		super("No available space!");
	}

}
