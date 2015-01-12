package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.*;
import javax.swing.*;

public class DrawBoard extends JFrame{
	private JPanel zeichenflaeche = new JPanel();

	public DrawBoard(){
		super("DruchflussBrett");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 520;
		int frameHeight = 543;
		setSize(frameWidth, frameHeight);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dimension.width - getSize().width) / 2;
		int y = (dimension.height - getSize().height) / 2;
		setLocation(x, y);
		Container contenPane = getContentPane();
		getContentPane().setLayout(null);
		
		//Anfang der Komponenten
		zeichenflaeche.setBounds(7, 2, 500, 500);
		zeichenflaeche.setBackground(Color.WHITE);
		contenPane.add(zeichenflaeche);
		//Anfang des Menues
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItemSelect = new JMenuItem("Select ComPort");
		JMenuItem menuItemClose = new JMenuItem("Close ComPort");
		
		menu.add(menuItemSelect);
		menu.add(menuItemClose);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		//Ende des Menues
		//Ende der Komponenten
		
		setResizable(false);
		setVisible(true);

	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D graphics2D = (Graphics2D)g;
		graphics2D.setBackground(Color.WHITE);
		graphics2D.clearRect(0, 0, 400, 200);
		graphics2D.setColor(Color.BLUE);
		graphics2D.draw3DRect(40, 105, 100, 60, true);
	}
}
