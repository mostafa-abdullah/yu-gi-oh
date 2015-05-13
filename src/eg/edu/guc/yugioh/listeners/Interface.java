package eg.edu.guc.yugioh.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Interface extends JFrame{
	JLabel background;
	


	public Interface() throws IOException, FontFormatException {
		
		
		
		
		background = new JLabel(new ImageIcon(ImageIO.read(new File("art/backgrounds/main5Updated.jpg"))));
		
		background.setLayout(new BorderLayout());
		
		JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
		header.setPreferredSize(new Dimension(300,150));
		
		InputStream is = new BufferedInputStream(new FileInputStream("fonts/youmurdererbb_reg.ttf"));
		Font font1 = Font.createFont(Font.TRUETYPE_FONT, is);
		font1 = font1.deriveFont(Font.PLAIN, 120);
		
		JLabel yugioh = new JLabel("Yu-Gi-Oh!");
		yugioh.setForeground(new Color(255, 123, 13));
		yugioh.setFont(font1);
		header.add(yugioh);
		header.setOpaque(false);
		
		
		JPanel menu = new JPanel(new FlowLayout());
		menu.setPreferredSize(new Dimension(200,500));
		
		
		
		JButton startGame = new JButton("Start Game");
		JButton exit = new JButton("Exit");
		JButton cardList = new JButton("Card List");
		
		
		is = new BufferedInputStream(new FileInputStream("fonts/hotpizza.ttf"));
		Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		font = font.deriveFont(Font.PLAIN, 30);
		
		cardList.setPreferredSize(new Dimension(400,100));
		cardList.setFont(font);
		cardList.setForeground(Color.GRAY);
		cardList.setContentAreaFilled(false);
		cardList.setFocusPainted(false);
		cardList.setBorderPainted(false);
		
		
		
		
		
		
		
		//exit.setBackground(Color.BLACK);
		exit.setFont(font);
		exit.setPreferredSize(new Dimension(400,100));
		exit.setForeground(Color.GRAY);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.setBorderPainted(false);
		
		
		
		startGame.setFont(font);
		startGame.setPreferredSize(new Dimension(400,100));
		startGame.setForeground(Color.GRAY);
		startGame.setContentAreaFilled(false);
		startGame.setFocusPainted(false);
		startGame.setBorderPainted(false);
		
		
		
		JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonsContainer.setOpaque(false);
		menu.add(startGame);
		menu.add(cardList);
		menu.add(exit);
		menu.setOpaque(false);
		
		buttonsContainer.add(menu);
		background.add(header, BorderLayout.NORTH);
		background.add(buttonsContainer,BorderLayout.CENTER);
		this.add(background);
		addTheActions(startGame,exit,cardList,this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		this.validate();
		String soundName = "sounds/Yu-Gi-Oh! Power of Chaos Yugi The Destiny - New Card!.wav";
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch(Exception e1){

		}
	}
	
	
	public void addTheActions(JButton b1, JButton b2,JButton cardList,Interface current){
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Main();
					//System.exit(0);
					current.dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}	
			}
		});
		
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
		});
		
		cardList.addActionListener(new ActionListener(){

			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new CardList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current.dispose();
			}
			
		});
		addMouseListeners(b1);
		addMouseListeners(b2);
		addMouseListeners(cardList);
	}
	
	
	
	
	private void addMouseListeners(JButton b) {
		b.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				String soundName = "sounds/Draw Card (2).wav";
				try{
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
				}
				catch(Exception e1){

				}
				b.setForeground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				b.setForeground(Color.GRAY);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}


	public static void main(String[] args) throws IOException, FontFormatException{
		new Interface();
	}
	
	

}
