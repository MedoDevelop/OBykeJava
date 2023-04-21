package fr.mezo.obyke.graphics;

import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import fr.mezo.obyke.data.BD;

public class test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.init();
		Default d =new Default();
		System.out.println(d);

	}

}
