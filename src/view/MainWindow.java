package view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.*;

public class MainWindow extends JFrame{
	
	
	
	private JButton jBExit = new JButton("Beenden");
	private JLabel jLMesswert = new JLabel("test");
	//...
	
	public MainWindow() {
		super("ZigBee");
		
		initForm();
	}
	
	private void initForm() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setBounds(200, 200, 500, 500);
		
		this.add(jLMesswert);
		this.add(jBExit);
		
	}
	

	public void setjLMesswert(String Messwert) {
		this.jLMesswert.setText(Messwert);
	}
	
	
	public void setExitButtonListener(ActionListener al) {
		this.jBExit.addActionListener(al);
	}
	

}
