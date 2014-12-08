package view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainWindow extends JFrame{
	
	
	private JButton jBExit = new JButton("Beenden");
	//...
	
	public MainWindow() {
		super("ZigBee");
		
		initForm();
	}
	
	private void initForm() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setBounds(200, 200, 500, 500);

		this.add(jBExit);
	}
	
	
	public void setExitButtonListener(ActionListener al) {
		this.jBExit.addActionListener(al);
	}
	
	public void setComEventListener(EventListener el) {
		
		
	}
}
