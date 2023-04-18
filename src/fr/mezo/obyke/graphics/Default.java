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
        
        hotels.add(enregH);
        hotels.add(consulH);
        
        instituts.add(enregI);
        instituts.add(consulI);
        
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
        
        JMenu venteMatOccas = new JMenu("Matériel d'occasion");
        JMenu venteMatNeuf = new JMenu("Matériel neuf");
        
        JMenuItem enregVenteMatOccas = new JMenuItem("Enregistrement");
        JMenuItem consulVenteMatOccas= new JMenuItem("Consultation");
        
        JMenuItem enregVenteMatNeuf = new JMenuItem("Enregistrement");
        JMenuItem consulVenteMatNeuf= new JMenuItem("Consultation");
        
        venteMatOccas.add(enregVenteMatOccas);
        venteMatOccas.add(consulVenteMatOccas);
        
        venteMatNeuf.add(enregVenteMatNeuf);
        venteMatNeuf.add(consulVenteMatNeuf);
        
        vente.add(venteMatOccas);
        vente.add(venteMatNeuf);
        
        mb.add(centre);
        mb.add(rdv);
        mb.add(materiel);
        mb.add(vente);
        
        Object [][] j= {
				{"element1","test"},{"element2","test"}
		};
		String []entetes= {"TEST1","test2"};
		
		//Ajout des évènements
		enregL.addActionListener((e) -> this.addForm(new FormCentre(3,3,"Enregistrement de Licencié")));
		enregH.addActionListener((e) -> this.addForm(new FormCentre(3,3,"Enregistrement d'Hôtel")));
		enregI.addActionListener((e) -> this.addForm(new FormCentre(3,3,"Enregistrement d'Institut")));
		
		enregRdv.addActionListener((e) -> this.addForm(new FormRdv(5,5,"Enregistrement d'un RDV")));
		
		enregMatOccas.addActionListener((e) -> this.addForm(new FormMaterielOccasion(4,4,"Enregistrement Matériel d'Occasion")));
		enregMatNeufs.addActionListener((e) -> this.addForm(new FormMaterielNeuf(4,4,"Enregistrement Matériel Neuf")));
		enregGaranties.addActionListener((e) -> this.addForm(new FormGarantie(3,3,"Enregistrement Garantie")));
		
		enregServ.addActionListener((e) -> this.addForm(new FormServices(4,5,"Service")));
		
		MenuConsultation menuVenteMatOccas = new MenuConsultation("Liste du matériel d'occasion possédé","Matériel d'Occasion");
		menuVenteMatOccas.addMenuDroit(new MenuVenteMatOccas(7,0));
		enregVenteMatOccas.addActionListener((e) -> this.addMenuConsultation(menuVenteMatOccas));
		
		MenuConsultation menuVenteMatNeuf = new MenuConsultation("Liste du matériel neuf possédé","Matériel Neuf");
		menuVenteMatNeuf.addMenuDroit(new MenuVenteMatNeuf(6,0));
		enregVenteMatNeuf.addActionListener((e) -> this.addMenuConsultation(menuVenteMatNeuf));
		
		MenuConsultation menuConsulVenteMatOccas = new MenuConsultation("Liste du matériel d'occasion en vente","Matériel d'Occasion");
		menuConsulVenteMatOccas.addMenuDroit(new MenuConsultationVenteMatOccas(7,0));
		consulVenteMatOccas.addActionListener((e) -> this.addMenuConsultation(menuConsulVenteMatOccas));
		
		MenuConsultation menuConsulVenteMatNeuf = new MenuConsultation("Liste du matériel neuf en vente","Matériel Neuf");
		menuConsulVenteMatNeuf.addMenuDroit(new MenuConsultationVenteMatNeuf(6,0));
		consulVenteMatNeuf.addActionListener((e) -> this.addMenuConsultation(menuConsulVenteMatNeuf));
		
		MenuConsultation menuLic = new MenuConsultation("Liste des Licenciés","Licencié");
		menuLic.addMenuDroit(new MenuConsultationCentre(3,3));
		consulL.addActionListener((e) -> this.addMenuConsultation(menuLic));
		
		MenuConsultation menuInst = new MenuConsultation("Liste des Instituts","Institut");
		menuInst.addMenuDroit(new MenuConsultationCentre(3,3));
		consulI.addActionListener((e) -> this.addMenuConsultation(menuInst));
		
		MenuConsultation menuHot = new MenuConsultation("Liste des Hôtels","Hôtel");
		menuHot.addMenuDroit(new MenuConsultationCentre(3,3));
		consulH.addActionListener((e) -> this.addMenuConsultation(menuHot));
		
		MenuConsultation menuRdv = new MenuConsultation("Liste des RDV","Rendez-vous");
		menuRdv.addMenuDroit(new MenuConsultationRdv(5,5));
		consulRdv.addActionListener((e) -> this.addMenuConsultation(menuRdv));
		
		MenuConsultation menuMatOccas = new MenuConsultation("Liste du matériel D'occasion","Matériel d'Occasion");
		menuMatOccas.addMenuDroit(new MenuConsultationMaterielOccasion(5,5));
		consulMatOccas.addActionListener((e) -> this.addMenuConsultation(menuMatOccas));
		
		MenuConsultation menuMatNeuf = new MenuConsultation("Liste du matériel Neuf","Matériel Neuf");
		menuMatNeuf.addMenuDroit(new MenuConsultationMaterielNeuf(5,5));
		consulMatNeufs.addActionListener((e) -> this.addMenuConsultation(menuMatNeuf));
		
		MenuConsultation menuServ = new MenuConsultation("Liste des Services","Service");
		menuServ.addMenuDroit(new MenuConsultationServices(5,5));
		consulServ.addActionListener((e) -> this.addMenuConsultation(menuServ));
		
		MenuConsultation menuGarantie = new MenuConsultation("Liste des Garanties","Garantie");
		menuGarantie.addMenuDroit(new MenuConsultationGarantie(3,3));
		consulGaranties.addActionListener((e) -> this.addMenuConsultation(menuGarantie));

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
		this.add(unForm,BorderLayout.CENTER);
		this.invalidate();
		this.validate();
		this.repaint();
	}
	
	//Méthode d'ajout de formulaire à la fenêtre
	public void addMenuConsultation(MenuConsultation menuConsultation) {
		this.getContentPane().removeAll();
		this.add(menuConsultation,BorderLayout.CENTER);
		this.invalidate();
		this.validate();
		this.repaint();
		
	}
	
	//Méthode d'ajout de formulaire à la fenêtre
	public void clear() {
		this.getContentPane().removeAll();
		this.repaint();
	}
	

}
