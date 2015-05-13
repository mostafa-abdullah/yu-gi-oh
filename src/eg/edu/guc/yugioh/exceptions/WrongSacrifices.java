package eg.edu.guc.yugioh.exceptions;

public class WrongSacrifices extends RuntimeException{

	public WrongSacrifices() {
		super("INVALID! You can only sacrifice with monsters from your monster area!");
	}

}
