package view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class DrawBoard extends JFrame{
	
	private JPanel jPanelBorder;
	private JPanel zeichenflaeche;
	private JPanel datenPanel;
	private JLabel comportTextFieldLabel1 = new JLabel("Comport 1");
	private JLabel comportTextFieldLabel2 = new JLabel("Comport 2");
	private JLabel comportTextFieldLabel3 = new JLabel("Comport 3");
	private JTextField comportTextField1 = new JTextField("Comport TextField1");
	private JTextField comportTextField2 = new JTextField("Comport TextField2");;
	private JTextField comportTextField3 = new JTextField("Comport TextField3");;
	
	
	public DrawBoard(){
		super("DruchflussBrett");
		setSize(710,600);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(true);
		//setVisible(true);
		setLocation(330, 80);
		initLayout();
		

	}
	
	public void showView(){
		setVisible(true);
		validate();
	}
	
	private void initLayout(){
		jPanelBorder = new JPanel();
		jPanelBorder.setLayout(new BorderLayout());
		add(jPanelBorder);
		//Anfang des Menues
	    JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItemSelect = new JMenuItem("Select ComPort");
		JMenuItem menuItemClose = new JMenuItem("Close ComPort");
		
		menu.add(menuItemSelect);
		menu.add(menuItemClose);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		jPanelBorder.add(menuBar, BorderLayout.NORTH);
		
		//Ende des Menues
		

		zeichenflaeche = new JPanel(){
//		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D graphics2D = (Graphics2D)g;
			graphics2D.setBackground(Color.WHITE);
			graphics2D.setColor(Color.blue);
			graphics2D.drawRect(50, 50, 100, 50);
		}
		};
		zeichenflaeche.setBackground(Color.WHITE);
		jPanelBorder.add(zeichenflaeche, BorderLayout.CENTER);
		
		datenPanel = new JPanel();
		datenPanel.setLayout(new GridLayout(2,6));
		comportTextField1.setEditable(false);
		comportTextField2.setEditable(false);
		comportTextField3.setEditable(false);
		datenPanel.add(comportTextFieldLabel1);
		datenPanel.add(comportTextFieldLabel2);
		datenPanel.add(comportTextFieldLabel3);
		datenPanel.add(comportTextField1);
		datenPanel.add(comportTextField2);
		datenPanel.add(comportTextField3);
		jPanelBorder.add(datenPanel, BorderLayout.SOUTH);
	}

	
	public void setComportTextField1(String textFieldContent1) {
		comportTextField1.setText(textFieldContent1);
	}
}
