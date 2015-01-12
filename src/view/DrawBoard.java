package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.*;
import javax.swing.*;

public class DrawBoard extends JFrame{
	private JPanel zeichenflaeche = new JPanel();
	//private GridBagLayout gridBagLayout = new GridBagLayout();
	//private JPanel canvasPane = new JPanel();
	//private JPanel menuPane = new JPanel();

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
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Select ComPort");
		
		menu.add(menuItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		//Ende der Komponenten
		
		setResizable(false);
		setVisible(true);
//		this.getContentPane().setLayout(gridBagLayout);
//		GridBagConstraints constraints = new GridBagConstraints();
//		constraints.fill = constraints.BOTH;
//		constraints.weightx = 2;
//		constraints.gridx = 0;
//		constraints.gridy = 0;
//		constraints.anchor = constraints.PAGE_START;
//		gridBagLayout.addLayoutComponent(canvasPane, constraints);
//		constraints.weightx = 1;
//		constraints.gridx = 1;
//		gridBagLayout.addLayoutComponent(menuPane, constraints);
//		menuPane.setLayout(new GridLayout(5, 2));
//		
//		
//		pack();
//		setLocationRelativeTo(null);
//		setVisible(true);
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
