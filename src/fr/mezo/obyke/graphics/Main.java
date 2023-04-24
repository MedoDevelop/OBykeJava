package fr.mezo.obyke.graphics;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import fr.mezo.obyke.data.BD;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		try {
			Font font = Main.getFontSize(14);
	        UIManager.put("OptionPane.messageFont", font);
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.init();
		Default d =new Default();
		System.out.println(d);

	}
	
	public static Font getFontSize(int size) {
		return new Font("Helvetica",Font.PLAIN,size);
	}
	
	public static boolean AllFieldFilled(JTextField[] tab) {
		boolean res = true;
		for(JTextField jtf : tab) {
			if(jtf.getText().length() == 0) {
				res = false;
				break;
			}
		}
		if(!res) {
			new Alert(2,"Un ou plusieurs champ(s) n'ont pas été complété.");
			//Message d'alerte qui s'affiche pendant 2 secondes.
		}
		return res;
	}
}
