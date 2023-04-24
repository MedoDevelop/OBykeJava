package Controller;

import java.awt.event.KeyListener;

import javax.swing.JTextField;

public abstract class MainKeyListener implements KeyListener{
	private JTextField jtf;
	public MainKeyListener(JTextField j) {
		this.jtf = j;
	}
	
	protected String getText() {
		return this.jtf.getText();
	}
	
	protected int getTextSize() {
		return this.jtf.getText().length();
	}
	
	protected boolean contains(String txt) {
		return this.jtf.getText().contains(txt);
	}
}
