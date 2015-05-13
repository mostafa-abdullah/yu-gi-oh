package eg.edu.guc.yugioh.listeners;






import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Computer;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.gui.GamePanel;

@SuppressWarnings("serial")
public class Main extends JFrame {

	public static int gameMode;
	Clip clip;
	public Clip getClip() {
		return clip;
	}
	public static Controller controller;
	GamePanel game;
	public Main() throws Exception{
		Board board = new Board();
		Object[] options = {"Two Players",
		"Play vs. Computer"};
		int n = JOptionPane.showOptionDialog(null,
				"Select game mode:",
				"Start Game",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				null); //default button title

		String player1;
		


		Player p1;
		Player p2;
		if(n==0){
			gameMode = 0;
			player1 = (String) JOptionPane.showInputDialog(null,"Please enter the first player's name","Player 1",JOptionPane.QUESTION_MESSAGE);
			if(player1==null || player1.length()==0){
				this.dispose();
				new Interface();
				return;
			}
			
			String player2 = (String) JOptionPane.showInputDialog(null,"Please enter the second player's name","Player 2",JOptionPane.QUESTION_MESSAGE);
			if(player2==null || player2.length()==0){
				this.dispose();
				new Interface();
				return;
			}
			p1 = new Player(player1);
			p2 = new Player(player2);
		}
		else{
			gameMode = 1;
			player1 = (String) JOptionPane.showInputDialog(null,"Please enter the first player's name","Player 1",JOptionPane.QUESTION_MESSAGE);
			if(player1==null || player1.length()==0){
				this.dispose();
				new Interface();
				return;
			}
			p1 = new Player(player1);
			p2 = new Computer();
		}



		//DarkHole darkHole = new DarkHole("Dark Hole","");
		//darkHole.setLocation(Location.HAND);
		//p2.getField().getHand().add(darkHole);

		board.startGame(p1, p2);





		game = new GamePanel(p1,p2);

		controller = new Controller(game,board,this);
		if(Card.getBoard().getActivePlayer() instanceof Computer){
			((Computer)Card.getBoard().getActivePlayer()).computerTurn();
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(game);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setSize(1366,768);
		this.setVisible(true);
		this.validate();
		String soundName = "sounds/Yu-Gi-Oh! Power of Chaos Yugi The Destiny - Duel Theme.wav";
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(10);

		}
		catch(Exception e1){

		}

	}



}
