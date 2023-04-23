package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class MenuConsultationDroit extends JPanel{
	
	private JPanel principal;
	private JPanel top;
	private JPanel left;
	private JPanel right;
	private JPanel centralBottom;
	private JPanel firstBottom;
	private JPanel secondBottom;
	
	public MenuConsultationDroit(int lineLeft, int lineRight) {
		super(new BorderLayout(60,60));
		
		//Stratégie de placement
		this.principal=new JPanel(new GridLayout(1,2));
		this.top=new JPanel(new GridLayout(1,1));
		this.centralBottom=new JPanel(new GridLayout(2,1));
		this.firstBottom=new JPanel(new GridLayout(1,3));
		this.secondBottom=new JPanel(new GridLayout(1,5));
		
		this.left=new JPanel(new GridLayout(lineLeft,1));
		this.right=new JPanel(new GridLayout(lineRight,1));
		
		this.setOpaque(false);
		this.principal.setOpaque(false);
		this.top.setOpaque(false);
		this.left.setOpaque(false);
		this.right.setOpaque(false);
		this.centralBottom.setOpaque(false);
		this.firstBottom.setOpaque(false);
		this.secondBottom.setOpaque(false);
		
		this.principal.add(left);
		this.principal.add(right);
		
		this.add(this.top,BorderLayout.NORTH);
		this.add(this.principal,BorderLayout.CENTER);
		this.add(this.centralBottom,BorderLayout.SOUTH);
		centralBottom.add(this.firstBottom,BorderLayout.CENTER);
		centralBottom.add(this.secondBottom,BorderLayout.CENTER);
		
	}
	
	//Ajout d'une ligne de formulaire à gauche
		public void addLeft(Component component) {
			this.left.add(component);
		}
		
	//Ajout d'une ligne de formulaire à droite
	public void addRight(Component component) {
		this.right.add(component);
	}
	//Ajout d'une ligne de formulaire en bas
	public void addFirstBottom(Component component) {
		this.firstBottom.add(component);
	}
	
	//Ajout d'un espace en bas
	public void addFirstBottomSpace() {
		JPanel unPanel=new JPanel();
		unPanel.setOpaque(false);
		this.firstBottom.add(unPanel);
	}
	
	//Ajout d'un espace en bas
	public void addSecondBottomSpace() {
		JPanel unPanel=new JPanel();
		unPanel.setOpaque(false);
		this.secondBottom.add(unPanel);
	}
	
	//Ajout d'une ligne de formulaire en bas
	public void addSecondBottom(Component component) {
		this.secondBottom.add(component);
	}
	
	//Ajout d'un espace en haut
	public void addTopSpace() {
		JPanel unPanel=new JPanel();
		unPanel.setOpaque(false);
		this.top.add(unPanel);
	}
	
	//Ajout d'un espace en haut
		public void addTop(Component component) {
			this.top.add(component);
		}
	
	
	
	
	
	

}
