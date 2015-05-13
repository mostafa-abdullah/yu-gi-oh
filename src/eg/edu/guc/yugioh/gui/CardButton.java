package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

import eg.edu.guc.yugioh.listeners.Controller;

public class CardButton extends JButton{
	public CardButton(){
		super();
		 this.setPreferredSize(new Dimension(68,100));
		 
		 //this.setOpaque(false);
		 //this.setContentAreaFilled(false);
		// this.setBorderPainted(false);
		 
	}
}
