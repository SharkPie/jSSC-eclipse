package view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.*;

public class MainWindow extends JFrame{
	
	
	
	private JButton jBExit = new JButton("Beenden");
	private JButton jBRefresh = new JButton("refresh");
	private JLabel jLMesswert = new JLabel("test");
	private JTextField jTMesswert = new JTextField("ausgabe234452");
	//...
	
	public MainWindow() {
		super("ZigBee");
		
		initForm();
	}
	
	private void initForm() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setBounds(200, 200, 500, 500);
		
		this.add(jTMesswert);
		this.add(jLMesswert);
		this.add(jBRefresh);
		this.add(jBExit);
		
	}
	

	public void setjTMesswert(String messwert) {
		this.jTMesswert.setText(messwert);
	}

	public void setjLMesswert(String messwert) {
		this.jLMesswert.setText(messwert);
	}
	
	
	public void setExitButtonListener(ActionListener al) {
		this.jBExit.addActionListener(al);
	}

	public void setRefreshButtonListener(ActionListener al) {
		this.jBRefresh.addActionListener(al);
		// TODO Auto-generated method stub
		
	}
	

}
