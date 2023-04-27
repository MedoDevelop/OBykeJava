package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MenuConsultation extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel principal;
	private JPanel leftCentral;
	private JPanel leftTop;
	private static JPanel leftBottom;
	private JPanel rightCentral;
	private JPanel rightTop;
	private JPanel rightBottom;
	private JLabel labelLeft;
	private JLabel labelRight;
	private MenuConsultationDroit menu;
	
	
	public MenuConsultation(String leftTitle, String rightTitle) {
		
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
		
		//Stratégie de placement
		this.principal=new JPanel(new GridLayout(1,2));
		this.leftCentral=new JPanel(new BorderLayout());
		this.leftTop=new JPanel(new BorderLayout());
		MenuConsultation.leftBottom=new JPanel(new BorderLayout());
		this.rightCentral=new JPanel(new BorderLayout());
		this.rightTop=new JPanel(new BorderLayout());
		this.rightBottom=new JPanel(new BorderLayout());
		
		//Rendre transparent
		this.principal.setOpaque(false);
		this.leftCentral.setOpaque(false);
		this.leftTop.setOpaque(false);
		MenuConsultation.leftBottom.setOpaque(false);
		this.rightCentral.setOpaque(false);
		this.rightTop.setOpaque(false);
		this.rightBottom.setOpaque(false);
		
		//Titre partie gauche
		this.labelLeft=new JLabel(leftTitle,JLabel.CENTER);
		this.labelLeft.setFont(MenuConsultation.getFontSize(24));
		
		//Titre du titre de la partie droite
		this.labelRight=new JLabel(rightTitle,JLabel.CENTER);
		this.labelRight.setFont(MenuConsultation.getFontSize(24));

		
		//Ajout du panneau de gauche
		this.principal.add(this.leftCentral,BorderLayout.WEST);
		
		//Ajout des sous-panneaux gauches
		this.leftCentral.add(this.leftTop,BorderLayout.NORTH);
		this.leftCentral.add(MenuConsultation.leftBottom,BorderLayout.CENTER);
		
		//Ajout du titre
		this.leftTop.add(this.labelLeft);
		
		//Ajout du panneau de droite
		this.principal.add(this.rightCentral,BorderLayout.EAST);
		
		//Ajout des sous-panneaux droits
		this.rightCentral.add(this.rightTop,BorderLayout.NORTH);
		this.rightCentral.add(this.rightBottom,BorderLayout.CENTER);
				
		//Ajout du titre
		this.rightTop.add(this.labelRight);
				
		//Création de la bordure
		this.leftCentral.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
		this.rightCentral.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
		 
		
		this.add(this.principal,BorderLayout.CENTER);

		
	}
	
		//Fonction de changement de la police
		public static Font getFontSize(int size) {
			return new Font("Helvetica",Font.PLAIN,size);
		}
	
		
		public static void addJTable(JTable table) {
			leftBottom.removeAll();
			leftBottom.add(new JScrollPane(table), BorderLayout.CENTER);
		}
		
		//Ajout du formulaire correspondant dans la partie droite du panneau
		public void addMenuDroit(MenuConsultationDroit unMenu) {
			this.rightBottom.add(this.menu=unMenu);
		}
		
		public MenuConsultationDroit getMenuDroit() {
			return this.menu;
		}
		
	

}
