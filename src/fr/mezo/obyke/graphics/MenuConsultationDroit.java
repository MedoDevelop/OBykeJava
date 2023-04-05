package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class MenuConsultationDroit extends JPanel{
	
	private JPanel principal;
	private JPanel left;
	private JPanel right;
	private JPanel bottom;
	
	public MenuConsultationDroit(int lineLeft, int lineRight) {
		super(new BorderLayout(60,60));
		
		this.principal=new JPanel(new GridLayout(1,2));
		this.bottom=new JPanel(new GridLayout(1,5));
		
		this.left=new JPanel(new GridLayout(lineLeft,1));
		this.right=new JPanel(new GridLayout(lineRight,1));
		
		this.setOpaque(false);
		this.principal.setOpaque(false);
		this.left.setOpaque(false);
		this.right.setOpaque(false);
		this.bottom.setOpaque(false);
		
		this.principal.add(left);
		this.principal.add(right);
		
		this.add(this.principal,BorderLayout.CENTER);
		this.add(this.bottom,BorderLayout.SOUTH);
		
	}
	
	//Ajout d'une ligne de formulaire à gauche
		public void addLeft(Component component) {
			this.left.add(component);
		}
		
	//Ajout d'une ligne de formulaire à droite
	public void addRight(Component component) {
		this.right.add(component);
	}
	//Ajout d'une ligne de formulaire à droite
	public void addBottom(Component component) {
		this.bottom.add(component);
	}
	
	public void DeleteConfirmation() {
		int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir supprimer cette ligne?");
	    
	    if(res == JOptionPane.YES_OPTION)
	    {
	      
	    }
	}
	
	public void EditConfirmation() {
		int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir modifier cette ligne?");
	    
	    if(res == JOptionPane.YES_OPTION)
	    {
	      
	    }
	}
	

}
