package eg.edu.guc.yugioh.exceptions;

public class MultipleMonsterAdditionException  extends RuntimeException{
	public MultipleMonsterAdditionException(){
		super("You cannot add another monster!");
	}

}
