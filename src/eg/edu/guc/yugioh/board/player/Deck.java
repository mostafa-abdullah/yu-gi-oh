package eg.edu.guc.yugioh.board.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
//import guc.edu.eg.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

public class Deck {
	private static ArrayList<Card> monsters = new ArrayList<Card>();
	private static String monstersPath = "Database-Monsters.csv";
	public static String getMonstersPath() {
		return monstersPath;
	}

	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}

	public static String getSpellsPath() {
		return spellsPath;
	}

	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}

	private static String spellsPath = "Database-Spells.csv";
	
	int count = 0;
	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public static void setMonsters(ArrayList<Card> monsters) {
		Deck.monsters = monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public static void setSpells(ArrayList<Card> spells) {
		Deck.spells = spells;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	private static ArrayList<Card> spells = new ArrayList<Card>();
	private ArrayList<Card> deck;




	public ArrayList<Card> getDeck() {
		return deck;
	}

	public Deck() throws IOException, Exception{
		
		

		if (monsters==null || monsters.size()==0){
			monsters = loadCardsFromFile(monstersPath);
		}
		if (spells==null || spells.size()==0){
			spells = loadCardsFromFile(spellsPath);
		}
		deck = new ArrayList<Card>();
		buildDeck(monsters, spells);
		this.shuffleDeck();

	}
	Scanner sc = new Scanner(System.in);
	public ArrayList<Card> loadCardsFromFile(String path) throws Exception{
		ArrayList<Card> cards = new ArrayList<Card>();
		File f = new File(path);
		if(!f.exists()){
			if (count==3)
				throw new FileNotFoundException(path);
			else{
				count++;
				path = sc.nextLine();
				return loadCardsFromFile(path);
			}
		}
		BufferedReader br = new BufferedReader(new FileReader(f));
		int line=1;
		while(br.ready()){
			String s[] = br.readLine().split(",");
			if (!s[0].equals("Monster") && !s[0].equals("Spell") && !s[0].trim().equals("")){
				if (count==3)
					throw new UnknownCardTypeException(path, line, s[0]);
				else
				{
					count++;
					path = sc.nextLine();
					return loadCardsFromFile(path);
				}

			}
			for (int i=0; i<s.length; i++){
				if (s[i].trim().equals("")){
					if (count==3)
						throw new EmptyFieldException(path, line, i+1);
					else
					{
						count++;
						path = sc.nextLine();
						return loadCardsFromFile(path);
					}
				}
			}
			if (s[0].equals("Monster")){
				if(s.length<6){
					if (count==3)
						throw new MissingFieldException(path, line);
					else
					{
						count++;
						path = sc.nextLine();
						return loadCardsFromFile(path);
					}
				}
				cards.add(new MonsterCard(s[1],s[2],Integer.parseInt(s[5]),Integer.parseInt(s[3]),Integer.parseInt(s[4])));
			}
			else{
				if(s.length<3){
					if (count==3)
						throw new MissingFieldException(path, line);
					else
					{
						count++;
						path = sc.nextLine();
						return loadCardsFromFile(path);
					}
				}
				switch(s[1]){
				case "Card Destruction": cards.add(new CardDestruction(s[1],s[2]));break;
				case "Change Of Heart": cards.add(new ChangeOfHeart(s[1],s[2]));break;
				case "Dark Hole": cards.add(new DarkHole(s[1],s[2]));break;
				case "Graceful Dice": cards.add(new GracefulDice(s[1],s[2]));break;
				case "Harpie's Feather Duster": cards.add(new HarpieFeatherDuster(s[1],s[2]));break;
				case "Heavy Storm": cards.add(new HeavyStorm(s[1],s[2]));break;
				case "Mage Power": cards.add(new MagePower(s[1],s[2]));break;
				case "Monster Reborn": cards.add(new MonsterReborn(s[1],s[2]));break;
				case "Pot of Greed": cards.add(new PotOfGreed(s[1],s[2]));break;
				case "Raigeki": cards.add(new Raigeki(s[1],s[2]));break;
				default:
					if (count==3)
						throw new UnknownSpellCardException(path, line, s[1]);
					else
					{
						count++;
						path = sc.nextLine();
						return loadCardsFromFile(path);
					}

				}
			}
			line++;
		}

		return cards;
	}

	public void buildDeck(ArrayList<Card> monsters, ArrayList<Card>spells) throws IOException{
		for (int i=0; i<15; i++){
			MonsterCard m1 = (MonsterCard) monsters.get((int)(Math.random()*monsters.size()));
			MonsterCard addedMonster = new MonsterCard(m1.getName(),m1.getDescription(),m1.getLevel(),m1.getAttackPoints(),m1.getDefensePoints());
			deck.add(addedMonster);
			addedMonster.setLocation(Location.DECK);
		}
		for (int i=0; i<5; i++){
			SpellCard s1 = (SpellCard) spells.get((int)(Math.random()*spells.size()));
			SpellCard s2;
			switch(s1.getName()){
			case "Card Destruction": s2 = new CardDestruction(s1.getName(),s1.getDescription());break;
			case "Change Of Heart": s2 = new ChangeOfHeart(s1.getName(),s1.getDescription());break;
			case "Dark Hole": s2 = new DarkHole(s1.getName(),s1.getDescription());break;
			case "Graceful Dice": s2 = new GracefulDice(s1.getName(),s1.getDescription());break;
			case "Harpie's Feather Duster": s2 = new HarpieFeatherDuster(s1.getName(),s1.getDescription());break;
			case "Heavy Storm": s2 = new HeavyStorm(s1.getName(),s1.getDescription());break;
			case "Mage Power": s2 = new MagePower(s1.getName(),s1.getDescription());break;
			case "Monster Reborn": s2 = new MonsterReborn(s1.getName(),s1.getDescription());break;
			case "Pot of Greed": s2 = new PotOfGreed(s1.getName(),s1.getDescription());break;
			case "Raigeki": s2 = new Raigeki(s1.getName(),s1.getDescription());break;
			default: s2 = null;

			}
			deck.add(s2);
			s2.setLocation(Location.DECK);
		}
	}

	public void shuffleDeck(){
		Collections.shuffle(this.deck);
	}

	public ArrayList<Card> drawNCards(int n){
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i=0; i<n; i++){
			cards.add(this.deck.remove(0));
		}
		return cards;
	}

	public Card drawOneCard(){
		return this.deck.remove(0);
	}
	
	public static void main(String[] args) throws IOException, Exception {
		
		Scanner sc= new Scanner(System.in);
		Deck d = new Deck();
		
		for (int i=0; i<d.deck.size(); i++)
			if(d.deck.get(i) instanceof SpellCard)
			System.out.println(d.deck.get(i).getClass());
	}









}
