package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Formulaire extends JPanel {
	
	private JPanel left;
	private JPanel right;
	private JPanel center;
	private JPanel top;
	private JPanel bottom;
	private JPanel bottomCentral;
	private JPanel firstBottom;
	private JPanel middleBottom;
	private JPanel secondBottom;
	private JPanel principal;
	private JLabel label;
	
	
	
	public Formulaire(int lineLeft, int lineRight,String title) {
		super(new BorderLayout(60,60));
		//Stratégie de placement
		JPanel lesPanels[]= {new JPanel(),new JPanel(),new JPanel(),new JPanel()};
		for(JPanel unPanel : lesPanels) {
			unPanel.setOpaque(false);
		}
		
		this.add(lesPanels[0],BorderLayout.NORTH);
		this.add(lesPanels[1],BorderLayout.SOUTH);
		this.add(lesPanels[2],BorderLayout.WEST);
		this.add(lesPanels[3],BorderLayout.EAST);
		
		//Rendre transparent
		this.setOpaque(false);
		
		//Stratégie de placement avec les grille pour les lignes du formulaire
		this.left=new JPanel(new GridLayout(lineLeft,1));
		this.right=new JPanel(new GridLayout(lineRight,1));
		this.center=new JPanel(new GridLayout(1,2));
		this.top=new JPanel(new GridLayout(2,3));
		this.bottom=new JPanel(new BorderLayout());
		this.middleBottom=new JPanel(new GridLayout(1,1));
		this.bottomCentral=new JPanel(new GridLayout(3,1));
		this.firstBottom=new JPanel(new GridLayout(1,3));
		this.secondBottom=new JPanel(new GridLayout(1,5));
		this.principal=new JPanel(new BorderLayout());
		
		//Rendre transparent
		this.left.setOpaque(false);
		this.right.setOpaque(false);
		this.center.setOpaque(false);
		this.top.setOpaque(false);
		this.bottom.setOpaque(false);
		this.middleBottom.setOpaque(false);
		this.bottomCentral.setOpaque(false);
		this.firstBottom.setOpaque(false);
		this.secondBottom.setOpaque(false);
		this.principal.setOpaque(false);
		
		//Titre du formulaire
		this.label=new JLabel(title,JLabel.CENTER);
		this.label.setFont(Formulaire.getFontSize(24));
		
		//Ajout du panneau du haut
		this.principal.add(this.top,BorderLayout.NORTH);
		
		JPanel pan1=new JPanel();
		pan1.setOpaque(false);
		JPanel pan2=new JPanel();
		pan2.setOpaque(false);
		
		//Espace
		this.top.add(pan1);
		
		//Ajout du titre
		this.top.add(this.label);
		
		//Espace
		this.top.add(pan2);
		
		//Ajout du panneau central
		this.principal.add(this.center,BorderLayout.CENTER);
				
		
		//Création de la bordure
		this.principal.setBorder(BorderFactory.createLineBorder(Color.GRAY,4));
		
		//Ajout des panneaux à gauche et à droite
		this.center.add(this.left);
		this.center.add(this.right);
		
		//Ajout du panneau inférieur
		this.principal.add(this.bottom,BorderLayout.SOUTH);
		
		//Ajout du panneau inférieur central
		this.bottom.add(this.bottomCentral,BorderLayout.CENTER);
		
		//Ajout du panneau du premier panneau inférieur
		this.bottomCentral.add(this.firstBottom,BorderLayout.NORTH);
		
		//Ajout du panneau du panneau inférieur du milieu
		this.bottomCentral.add(this.middleBottom,BorderLayout.NORTH);
		
		//Ajout du panneau du second panneau inférieur
		this.bottomCentral.add(this.secondBottom,BorderLayout.SOUTH);
		
		this.add(this.principal,BorderLayout.CENTER);
		
	}
	
	//Ajout d'une ligne de formulaire à gauche
	public void addLeft(InputField unInput) {
		this.left.add(unInput);
	}
	
	//Ajout d'une ligne de formulaire à droite
	public void addRight(InputField unInput) {
		this.right.add(unInput);
	}
	
	//Ajout d'un composant dans le panneau du bas
	public void addFirstBottom(JComponent unComponent) {
			
		this.firstBottom.add(unComponent);
	}
		
	//Ajout d'un espace dans le panneau du bas
	public void addFirstBottomSpace(JPanel unPanel) {
		this.firstBottom.add(unPanel);
		unPanel.setOpaque(false);
	}
	
	//Ajout d'un espace dans le second panneau du bas
	public void addSecondBottomSpace(JPanel unPanel) {
		this.secondBottom.add(unPanel);
		unPanel.setOpaque(false);
	}
	
	//Ajout d'un composant dans le panneau du bas
	public void addSecondBottom(JComponent unComponent) {
		this.secondBottom.add(unComponent);
	}
	
	//Ajout d'un espace dans le panneau du bas
	public void addTopSpace(JPanel unPanel) {
		this.top.add(unPanel);
		unPanel.setOpaque(false);
	}
	
	//Ajout d'un composant dans le panneau du bas
	public void addTop(JComponent unComponent) {
		this.top.add(unComponent);
	}
	
	//Fonction de changement de la police
	public static Font getFontSize(int size) {
		return new Font("Helvetica",Font.PLAIN,size);
	}
	
	//Fonction d'ajout de cadre à gauche et à droite
	public  void setBorderToLeftAndRight() {
		this.left.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
		this.right.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
	}

}
