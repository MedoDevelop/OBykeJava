package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Default extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JPanel p = new JPanel();
	
	public Default() {
		
		//Taille fenêtre par défaut
		super("Gestion O'Byke");
		Dimension dim= new Dimension(1080,720);
        this.setSize(dim);
        
        //Center la fenêtre
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(new Background("./ressources/aquabike2.png"));
        
        //Création du Menu
        createMenu();
        this.setLayout(new BorderLayout());  
	}
	
	public void createMenu() {
		
		//Création du menu et de ses différents sous-menu
        JMenuBar mb = new JMenuBar();
        
        JMenu centre = new JMenu("CENTRE");
        
        JMenu licencies = new JMenu("Licenciés");
        JMenu hotels = new JMenu("Hôtels");
        JMenu instituts = new JMenu("Instituts");
        
        JMenuItem enregL = new JMenuItem("Enregistrement");
        JMenuItem enregH= new JMenuItem("Enregistrement");
        JMenuItem enregI= new JMenuItem("Enregistrement");
        
        JMenuItem consulL = new JMenuItem("Consultation");
        JMenuItem consulH= new JMenuItem("Consultation");
        JMenuItem consulI= new JMenuItem("Consultation");
        
        licencies.add(enregL);
        licencies.add(consulL);
        
        hotels.add(enregL);
        hotels.add(consulL);
        
        instituts.add(enregL);
        instituts.add(consulL);
        
        centre.add(licencies);
        centre.add(hotels);
        centre.add(instituts);
        
        JMenu rdv = new JMenu("RDV");
        
        JMenuItem enregRdv = new JMenuItem("Enregistrement");
        JMenuItem consulRdv= new JMenuItem("Consultation");
        
        rdv.add(enregRdv);
        rdv.add(consulRdv);
        
        
        JMenu materiel = new JMenu("MATERIEL");
        
        JMenu occas = new JMenu("Occasions");
        JMenu neufs = new JMenu("Neufs");
        JMenu garanties = new JMenu("Garanties");
        JMenu services = new JMenu("Services");
        
        JMenuItem enregMatOccas = new JMenuItem("Enregistrement");
        JMenuItem consulMatOccas= new JMenuItem("Consultation");
        
        JMenuItem enregMatNeufs = new JMenuItem("Enregistrement");
        JMenuItem consulMatNeufs= new JMenuItem("Consultation");
        
        JMenuItem enregGaranties = new JMenuItem("Enregistrement");
        JMenuItem consulGaranties= new JMenuItem("Consultation");
        
        JMenuItem enregServ = new JMenuItem("Enregistrement");
        JMenuItem consulServ= new JMenuItem("Consultation");
        
        occas.add(enregMatOccas);
        occas.add(consulMatOccas);
        
        neufs.add(enregMatNeufs);
        neufs.add(consulMatNeufs);
        
        garanties.add(enregGaranties);
        garanties.add(consulGaranties);
        
        services.add(enregServ);
        services.add(consulServ);
        
        materiel.add(occas);
        materiel.add(neufs);
        materiel.add(garanties);
        materiel.add(services);
        
        JMenu vente = new JMenu("VENTE");
        
        JMenuItem enregVente = new JMenuItem("Enregistrement");
        JMenuItem consulVente= new JMenuItem("Consultation");
        
        vente.add(enregVente);
        vente.add(consulVente);
        
        mb.add(centre);
        mb.add(rdv);
        mb.add(materiel);
        mb.add(vente);
        
        Object [][] j= {
				{"element1","test"},{"element2","test"}
		};
		String []entetes= {"TEST1","test2"};
		
		//Ajout des évènements
		enregMatOccas.addActionListener((e) -> this.addForm(new FormMaterielOccasion(4,4,"Enregistrement Matériel d'Occasion")));
		
		enregServ.addActionListener((e) -> this.addForm(new FormServices(4,5,"Service")));
		
		MenuConsultation m = new MenuConsultation(j,entetes,"Liste du matériel D'occasion","Matériel d'Occasion");
		m.addMenuDroit(new MenuConsultationMaterielOccasion(4,4));
		consulMatOccas.addActionListener((e) -> this.addMenuConsultation(m));

        this.setJMenuBar(mb);
	}
	
	public void Cadre() {
		//Stratégie de placement de l'espacement entre chaque élément
        this.setLayout(new BorderLayout(20,60));
        
        //Transparence
        this.p.setOpaque(false);
        
        //Création des bordures du panneau
        this.p.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
        
        //Centre par rapport à l'espace disponible
        this.add(this.p,BorderLayout.CENTER);
        
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel[] ps= {p1,p2,p3,p4};
       
        for(JPanel element : ps ) {
        	element.setOpaque(false);
        }
        
        this.add(p1,BorderLayout.NORTH);
        this.add(p2,BorderLayout.SOUTH);
        this.add(p3,BorderLayout.WEST);
        this.add(p4,BorderLayout.EAST);
	}
	
	//Méthode d'ajout de formulaire à la fenêtre
	public void addForm(Formulaire unForm) {
		this.getContentPane().removeAll();
		this.repaint();
		this.add(unForm,BorderLayout.CENTER);
	}
	
	//Méthode d'ajout de formulaire à la fenêtre
	public void addMenuConsultation(MenuConsultation menuConsultation) {
		this.getContentPane().removeAll();
		this.repaint();
		this.add(menuConsultation,BorderLayout.CENTER);
		
	}

}
