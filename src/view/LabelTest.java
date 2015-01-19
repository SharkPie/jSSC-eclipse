package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class LabelTest extends JFrame{
	
	int anzahlSensor;
	
	JPanel borderLayout;
	JPanel gridSouth;
	JPanel gridHaupt;
	JPanel gridRechts;
	JPanel gridLinks;
	
	JLabel[] sensorLabel;
	JTextField[] sensorTextField;
	
	JLabel gew�hltesBildText;
	JLabel gew�hltesBild;
	
	JButton sensorPlus;
	JButton sensorMinus;
	
	final ImageIcon kreuz;
	final ImageIcon leer;
	final ImageIcon horizontal;
	final ImageIcon vertical;
	final ImageIcon lSt�ckObenLinks;
	final ImageIcon lSt�ckObenRechts;
	final ImageIcon lSt�ckUntenLinks;
	final ImageIcon lSt�ckUntenRechts;
	final ImageIcon tSt�ckOben;
	final ImageIcon tSt�ckUnten;
	final ImageIcon tSt�ckLinks;
	final ImageIcon tSt�ckRechts;
	final ImageIcon ventilHorizontal;
	final ImageIcon sensorHorizontal;
	final ImageIcon sensorVertikal;
	final ImageIcon pumpe;
	
	JRadioButton kreuzButton;
	JRadioButton leerButton;
	JRadioButton horizontalButton;
	JRadioButton vertikalButton;
	JRadioButton lSt�ckObenLinksButton;
	JRadioButton lSt�ckObenRechtsButton;
	JRadioButton lSt�ckUntenLinksButton;
	JRadioButton lSt�ckUntenRechtsButton;
	JRadioButton tSt�ckObenButton;
	JRadioButton tSt�ckUntenButton;
	JRadioButton tSt�ckLinksButton;
	JRadioButton tSt�ckRechtsButton;
	JRadioButton sensorVertikalButton;
	JRadioButton sensorHorizontalButton;
	JRadioButton ventilHorizontalButton;
	JRadioButton pumpeButton;
	ButtonGroup buttonGroup;
	
	JLabel[][] labelGitter;
	
	public LabelTest(int anzahlSensor){
		// TODO Auto-generated constructor stub
		this.anzahlSensor = anzahlSensor;
		
		kreuz = new ImageIcon("pics/Kreuz.png");
		leer = new ImageIcon("pics/Leer.png");
		horizontal = new ImageIcon("pics/Horizontal.png");
		vertical = new ImageIcon("pics/Vertikal.png");
		lSt�ckObenLinks = new ImageIcon("pics/LSt�ckObenLinks.png");
		lSt�ckObenRechts = new ImageIcon("pics/LSt�ckObenRechts.png");
		lSt�ckUntenLinks = new ImageIcon("pics/LSt�ckUntenLinks.png");
		lSt�ckUntenRechts = new ImageIcon("pics/LSt�ckUntenRechts.png");
		tSt�ckOben = new ImageIcon("pics/TSt�ckOben.png");
		tSt�ckUnten = new ImageIcon("pics/TSt�ckUnten.png");
		tSt�ckLinks = new ImageIcon("pics/TSt�ckLinks.png");
		tSt�ckRechts = new ImageIcon("pics/TSt�ckRechts.png");
		ventilHorizontal = new ImageIcon("pics/VentilHorizontal.png");
		sensorHorizontal = new ImageIcon("pics/SensorHorizontal.png");
		sensorVertikal = new ImageIcon("pics/SensorVertikal.png");
		pumpe = new ImageIcon("pics/Pumpe.png");

		initLayout();
		
		initMenuBar();
		
		initSensorLabels();
		
		initRadioButtons();
		
		initAusgew�hltBild();
		
		initSensorErh�henButton();
		
		initZeichenfl�che();
		
		initWindow();
	}

	private void initSensorErh�henButton() {
		sensorPlus = new JButton("Sensor +");
		sensorMinus = new JButton("Sensor -");
		gridLinks.add(sensorPlus);
		gridLinks.add(sensorMinus);
	}

	private void initSensorLabels() {
		sensorLabel = new JLabel[anzahlSensor];
		sensorTextField = new JTextField[anzahlSensor];
		
		for(int i=0;i<anzahlSensor;i++){
			StringBuilder sb = new StringBuilder();
			sb.append("Sensor ");
			sb.append(i+1);
			sensorLabel[i] = new JLabel(sb.toString());
			gridSouth.add(sensorLabel[i]);
			}
		for(int i=0;i<anzahlSensor;i++){
			sensorTextField[i] = new JTextField("000,000,000");
			sensorTextField[i].setEditable(false);
			gridSouth.add(sensorTextField[i]);
		}
	}

	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItemSelect = new JMenuItem("Select ComPort");
		JMenuItem menuItemClose = new JMenuItem("Close ComPort");
		
		menu.add(menuItemSelect);
		menu.add(menuItemClose);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		this.setJMenuBar(menuBar);
	}

	private void initAusgew�hltBild() {
		gew�hltesBildText = new JLabel("Ausgew�hltes Teil:");
		gew�hltesBild = new JLabel(kreuz);
		
		gridLinks.add(gew�hltesBildText);
		gridLinks.add(gew�hltesBild);
	}

	private void initZeichenfl�che() {
		labelGitter = new JLabel[10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				labelGitter[i][j] = new JLabel(leer);
				gridRechts.add(labelGitter[i][j]);
				
				class IconMouseListener extends MouseAdapter{
					
					private int xKoordinate;
					private int yKoordinate;
					
					public IconMouseListener(int xKoordinate, int yKoordinate) {
						// TODO Auto-generated constructor stub
						this.xKoordinate = xKoordinate;
						this.yKoordinate = yKoordinate;
					}

					public void mouseClicked(MouseEvent e) {
						
						if(kreuzButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(kreuz);
						else if(leerButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(leer);
						else if(vertikalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(vertical);
						else if(horizontalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(horizontal);
						else if(lSt�ckObenLinksButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lSt�ckObenLinks);
						else if(lSt�ckObenRechtsButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lSt�ckObenRechts);
						else if(lSt�ckUntenLinksButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lSt�ckUntenLinks);
						else if(lSt�ckUntenRechtsButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lSt�ckUntenRechts);
						else if(tSt�ckLinksButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tSt�ckLinks);
						else if(tSt�ckObenButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tSt�ckOben);
						else if(tSt�ckRechtsButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tSt�ckRechts);
						else if(tSt�ckUntenButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tSt�ckUnten);
						else if(sensorHorizontalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(sensorHorizontal);
						else if(sensorVertikalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(sensorVertikal);
						else if(ventilHorizontalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(ventilHorizontal);
						else
							labelGitter[xKoordinate][yKoordinate].setIcon(pumpe);
					}
				}
				labelGitter[i][j].addMouseListener(new IconMouseListener(i,j));
				
			}
		}
	}

	private void initWindow() {
		setSize(800,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void initLayout() {
		
		borderLayout = new JPanel();
		borderLayout.setLayout(new BorderLayout());
		add(borderLayout);
		
		gridSouth = new JPanel();
		gridSouth.setLayout(new GridLayout(2,3));
		borderLayout.add(gridSouth,BorderLayout.SOUTH);
		
		gridHaupt = new JPanel();
		gridHaupt.setLayout(new GridLayout(1,2));
		borderLayout.add(gridHaupt,BorderLayout.CENTER);
		
		gridLinks = new JPanel();
		gridLinks.setLayout(new GridLayout(5,4));
		gridHaupt.add(gridLinks);
		
		gridRechts = new JPanel();
		gridRechts.setLayout(new GridLayout(10,10));
		gridHaupt.add(gridRechts);
	}

	private void initRadioButtons() {
		
		kreuzButton =new JRadioButton(kreuz);
		kreuzButton.setSelected(true);
		leerButton = new JRadioButton(leer);
		
		horizontalButton = new JRadioButton(horizontal);
		vertikalButton = new JRadioButton(vertical);
		
		lSt�ckObenLinksButton = new JRadioButton(lSt�ckObenLinks);
		lSt�ckObenRechtsButton = new JRadioButton(lSt�ckObenRechts);
		lSt�ckUntenLinksButton = new JRadioButton(lSt�ckUntenLinks);
		lSt�ckUntenRechtsButton = new JRadioButton(lSt�ckUntenRechts);
		
		tSt�ckLinksButton = new JRadioButton(tSt�ckLinks);
		tSt�ckObenButton = new JRadioButton(tSt�ckOben);
		tSt�ckRechtsButton = new JRadioButton(tSt�ckRechts);
		tSt�ckUntenButton = new JRadioButton(tSt�ckUnten);
		
		sensorHorizontalButton = new JRadioButton(sensorHorizontal);
		sensorVertikalButton = new JRadioButton(sensorVertikal);
		
		ventilHorizontalButton = new JRadioButton(ventilHorizontal);
		pumpeButton = new JRadioButton(pumpe);
		
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(kreuzButton);
		buttonGroup.add(leerButton);
		
		buttonGroup.add(horizontalButton);
		buttonGroup.add(vertikalButton);
		
		buttonGroup.add(lSt�ckObenLinksButton);
		buttonGroup.add(lSt�ckObenRechtsButton);
		buttonGroup.add(lSt�ckUntenLinksButton);
		buttonGroup.add(lSt�ckUntenRechtsButton);
		
		buttonGroup.add(tSt�ckLinksButton);
		buttonGroup.add(tSt�ckObenButton);
		buttonGroup.add(tSt�ckRechtsButton);
		buttonGroup.add(tSt�ckUntenButton);
		
		buttonGroup.add(sensorHorizontalButton);
		buttonGroup.add(sensorVertikalButton);
		
		buttonGroup.add(ventilHorizontalButton);
		buttonGroup.add(pumpeButton);
		
		gridLinks.add(kreuzButton);
		gridLinks.add(leerButton);
		gridLinks.add(horizontalButton);
		gridLinks.add(vertikalButton);
		gridLinks.add(lSt�ckObenLinksButton);
		gridLinks.add(lSt�ckObenRechtsButton);
		gridLinks.add(lSt�ckUntenLinksButton);
		gridLinks.add(lSt�ckUntenRechtsButton);
		gridLinks.add(tSt�ckLinksButton);
		gridLinks.add(tSt�ckObenButton);
		gridLinks.add(tSt�ckRechtsButton);
		gridLinks.add(tSt�ckUntenButton);
		gridLinks.add(sensorHorizontalButton);
		gridLinks.add(sensorVertikalButton);
		gridLinks.add(ventilHorizontalButton);
		gridLinks.add(pumpeButton);
	}
	
	public void setSensorTextFieldInhalt(String sensorTextField) {
		this.sensorTextField[0].setText(sensorTextField);
		//sensorTextField[0]
	}

}
