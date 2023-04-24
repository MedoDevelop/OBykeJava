package fr.mezo.obyke.graphics;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Alert {
	
	//Permet d'ouvrir une alerte qui va se fermer toute seulement au bout de quelques secondes
	
	public Alert(float s,String msg) {
		int delay =  (int) (s * 1000); // delay in milliseconds (conver second in msilliseconds)
		Timer timer = new Timer(delay, e -> {
            JOptionPane.getRootFrame().dispose(); // close the dialog box after the delay
        });
		timer.setRepeats(false); // run the task only once
        timer.start(); // start the timer
        JOptionPane.showMessageDialog(null, msg, "ALERT", JOptionPane.WARNING_MESSAGE);
	}
	
    public static void main(String[] args) {
    	new Alert(1.5f,"Ceci est une alert temporaire de 1.5s"); 
    	System.out.println("fin 1.5");
    	new Alert(3, "Ceci est une alert temporaire de 3s");
    	System.out.println("fin 3");
    }
}