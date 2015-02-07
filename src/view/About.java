package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class About extends JDialog {
	private JTextArea creditials;
	private JPanel panel;
	private JLabel label;
	private JButton ok;
	
	public About(){
		//Layout des Fensters
		panel = new JPanel();					
		panel.setLayout(new BorderLayout());
		add(panel);
		setTitle("About");
		setSize(300,300);
		label = new JLabel("Programmiert von:");
		panel.add(label, BorderLayout.NORTH);
		creditials = new JTextArea("\n\nDüll, Vincent\n\nGrüber, "
				+ "Dominik\n\nSommer, Fabian\n\nToptan, Bekir Emre\n");
		creditials.setEditable(false);
		creditials.setBackground(Color.lightGray);
		panel.add(creditials,BorderLayout.CENTER);
		ok = new JButton("OK");
		panel.add(ok, BorderLayout.SOUTH);
		
		
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		setModal(true);
		setVisible(true);
		dispose();
	}
	
}
