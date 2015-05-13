package eg.edu.guc.yugioh.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.MonsterButton;
import eg.edu.guc.yugioh.gui.SpellButton;
import eg.edu.guc.yugioh.gui.WrapLayout;

/**
 *  FlowLayout subclass that fully supports wrapping of components.
 */


public class CardList extends JFrame{
	JLabel cardImage;
	public CardList() throws Exception {
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("art/backgrounds/main5Updated.jpg"))));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1344,768));
		background.setLayout(new BorderLayout());
		JPanel leftSide = new JPanel(new BorderLayout());
		leftSide.setPreferredSize(new Dimension(344,768));
		leftSide.setOpaque(false);
		cardImage = new JLabel(new ImageIcon(ImageIO.read(new File("art/cards/back.png")).getScaledInstance(272, 400, Image.SCALE_SMOOTH)));
		
		JButton back = new JButton("Back");
		CardList current = this;
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Interface();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current.dispose();
				
			}
			
		});
		
		back.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
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
				((JComponent) e.getSource()).setForeground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				((JComponent) e.getSource()).setForeground(Color.GRAY);

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		InputStream is = new BufferedInputStream(new FileInputStream("fonts/hotpizza.ttf"));
		Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		font = font.deriveFont(Font.PLAIN, 30);
		
		
		back.setPreferredSize(new Dimension(400,100));
		back.setFont(font);
		back.setForeground(Color.GRAY);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		
		leftSide.add(cardImage, BorderLayout.CENTER);
		leftSide.add(back,BorderLayout.SOUTH);
		
		
		JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.CENTER));
		rightSide.setPreferredSize(new Dimension(1000,768));
		rightSide.setOpaque(false);
		JPanel cardsContainer = new JPanel(new WrapLayout());
		cardsContainer.setOpaque(false);
		
		
		
		//cardsContainer.setPreferredSize(new Dimension(700,768));
		//rightSide.add(cardsContainer);
		cardsContainer.setAutoscrolls(true);
		BufferedReader br = new BufferedReader(new FileReader(new File("Database-Monsters.csv")));
		ArrayList<MonsterButton> monsters = new ArrayList<MonsterButton>();
		MouseListener ml = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if(e.getSource() instanceof MonsterButton){
					MonsterCard monster = ((MonsterButton)e.getSource()).getMonster();
					updateImage(monster);
				}
				else if(e.getSource() instanceof SpellButton){
					SpellCard spell = ((SpellButton)e.getSource()).getSpell();
					updateImage(spell);
				}
					
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		while(br.ready()){
			String s = br.readLine();
			String monsterProp [] = s.split(",");
			MonsterCard monster = new MonsterCard(monsterProp[1],monsterProp[2],Integer.parseInt(monsterProp[5]),Integer.parseInt(monsterProp[3]),Integer.parseInt(monsterProp[4]));
			//System.out.println(monsterProp[1]);
			MonsterButton monsterButton = new MonsterButton(monster);
			monsterButton.setPreferredSize(new Dimension(136,200));
			monsterButton.setIcon(new ImageIcon(monsterButton.getMonster().getImage().getScaledInstance(136, 200, Image.SCALE_SMOOTH)));
			monsterButton.addMouseListener(ml);
			cardsContainer.add(monsterButton);
			monsters.add(monsterButton);
		}
		br = new BufferedReader(new FileReader(new File("Database-Spells.csv")));
		ArrayList<SpellButton> spells = new ArrayList<SpellButton>();
		while(br.ready()){
			String s = br.readLine();
			String spellProp [] = s.split(",");
			SpellCard spell = new CardDestruction(spellProp[1],spellProp[2]);
			//System.out.println(monsterProp[1]);
			SpellButton spellButton = new SpellButton(spell);
			spellButton.setPreferredSize(new Dimension(136,200));
			spellButton.setIcon(new ImageIcon(spellButton.getSpell().getImage().getScaledInstance(136, 200, Image.SCALE_SMOOTH)));
			spellButton.addMouseListener(ml);
			cardsContainer.add(spellButton);
			spells.add(spellButton);
		}
		JScrollPane scrollList = new JScrollPane(cardsContainer);
		scrollList.setPreferredSize(new Dimension(700,760));
		scrollList.setOpaque(false);
		scrollList.getViewport().setBackground(Color.BLACK);
		rightSide.add(scrollList);
		background.add(leftSide,BorderLayout.WEST);
		background.add(rightSide,BorderLayout.EAST);
		this.add(background);
		this.setVisible(true);
	}
	
	public void updateImage(Card c){
		
		Image image;
		if (c instanceof SpellCard)
			image = ((SpellCard)c).getImage();
		else
			image = ((MonsterCard)c).getImage();
		cardImage.setIcon(new ImageIcon(image.getScaledInstance(272, 400, Image.SCALE_SMOOTH)));
		
	}
	
	

}


