/*
* Datei: LabelTest.java
*
* Die Klasse stellt das Hauptfenster zur Verfuegung, in welchem die Rohrleitungen visualisiert werden
*
*
*
*/

package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	int anzahlSensor; //Anzahl der zu ueberwachenden Sensoren
	int zaehlerSensorBild;
	
	JPanel borderLayout; //Hauptfenster
	JPanel gridSouth; //im JPanel "borderLayout" eingebettet, Sensordaten
	JPanel gridHaupt; //im JPanel "borderLayout" eingebettet, nimmt "gridRechts" und "gridLinks" auf
	JPanel gridRechts; //im JPanel "gridHaupt" eingebettet, Darstellung des Rohrsystems
	JPanel gridLinks; //im JPanel "gridHaupt" eingebettet, Darstellung der Rohrstuecke
	
	JLabel[] sensorLabel;
	JTextField[] sensorTextField;
	
	JLabel gewaehltesBildText; //Darstellung der derzeitigen Auswahl
	JLabel gewaehltesBild; //Darstellung der derzeitigen Auswahl
	
	JButton sensorPlus; // Button zur Auswahl ob Sensor 1, 2, etc. dargestellt werden soll
	JButton sensorMinus;
	
	final ImageIcon kreuz;
	final ImageIcon leer;
	final ImageIcon horizontal;
	final ImageIcon vertical;
	final ImageIcon lStueckObenLinks;
	final ImageIcon lStueckObenRechts;
	final ImageIcon lStueckUntenLinks;
	final ImageIcon lStueckUntenRechts;
	final ImageIcon tStueckOben;
	final ImageIcon tStueckUnten;
	final ImageIcon tStueckLinks;
	final ImageIcon tStueckRechts;
	final ImageIcon ventilHorizontal;
	final ImageIcon[] sensorHorizontal;
	final ImageIcon[] sensorVertikal;
	final ImageIcon pumpe;
	
	JRadioButton[] radioButton;
	ButtonGroup buttonGroup;
	
	JLabel[][] labelGitter; //2-D Array zum anordnen der Rohrstuecke
	
	public LabelTest(int anzahlSensor){
		// TODO Auto-generated constructor stub
		this.anzahlSensor = anzahlSensor;
		this.zaehlerSensorBild = 0;
		
		kreuz = new ImageIcon("pics/Kreuz.png");							//Laden aller Bilder
		leer = new ImageIcon("pics/Leer.png");
		horizontal = new ImageIcon("pics/Horizontal.png");
		vertical = new ImageIcon("pics/Vertikal.png");
		lStueckObenLinks = new ImageIcon("pics/LStueckObenLinks.png");
		lStueckObenRechts = new ImageIcon("pics/LStueckObenRechts.png");
		lStueckUntenLinks = new ImageIcon("pics/LStueckUntenLinks.png");
		lStueckUntenRechts = new ImageIcon("pics/LStueckUntenRechts.png");
		tStueckOben = new ImageIcon("pics/TStueckOben.png");
		tStueckUnten = new ImageIcon("pics/TStueckUnten.png");
		tStueckLinks = new ImageIcon("pics/TStueckLinks.png");
		tStueckRechts = new ImageIcon("pics/TStueckRechts.png");
		ventilHorizontal = new ImageIcon("pics/ventilHorizontal.png");
		sensorHorizontal = new ImageIcon[5];
		sensorVertikal = new ImageIcon[5];
		for(int i=0;i<5;i++){
			StringBuilder sbHorizontal = new StringBuilder();
			sbHorizontal.append("pics/SensorHorizontal");
			sbHorizontal.append(i+1);
			sbHorizontal.append(".png");
			sensorHorizontal[i] = new ImageIcon(sbHorizontal.toString());
			
			StringBuilder sbVertikal = new StringBuilder();
			sbVertikal.append("pics/SensorVertikal");
			sbVertikal.append(i+1);
			sbVertikal.append(".png");
			sensorVertikal[i] = new ImageIcon(sbVertikal.toString());
			
		}
		
		pumpe = new ImageIcon("pics/Pumpe.png");

		initLayout(); //initialisieren der einzelen Teile, die das Hauptfenster darstellen
		
		initMenuBar();
		
		initSensorLabels();
		
		initRadioButtons();
		
		initAusgewaehltBild();
		
		initSensorErhöhenButton();
		
		initZeichenflaeche();
		
		initWindow();
	}

	private void initSensorErhöhenButton() { //Buttons zur Sensor Manipulation erstellen
		sensorPlus = new JButton("Sensor +");
		sensorMinus = new JButton("Sensor -");
		
		sensorPlus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(zaehlerSensorBild);
				if(zaehlerSensorBild==anzahlSensor-1)
					zaehlerSensorBild = -1;
				
				zaehlerSensorBild++;
				
				radioButton[12].setIcon(sensorHorizontal[zaehlerSensorBild]); 	//button Sensor Horizontal
				radioButton[13].setIcon(sensorVertikal[zaehlerSensorBild]);		//button Sensor Vertikal
				
				if(radioButton[12].isSelected())
					gewaehltesBild.setIcon(radioButton[12].getIcon());
				if(radioButton[13].isSelected())
					gewaehltesBild.setIcon(radioButton[13].getIcon());
			}
			
		});
		
		sensorMinus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(zaehlerSensorBild==0)
					zaehlerSensorBild=anzahlSensor;
				
				zaehlerSensorBild--;
				radioButton[12].setIcon(sensorHorizontal[zaehlerSensorBild]);	//button Sensor Horizontal
				radioButton[13].setIcon(sensorVertikal[zaehlerSensorBild]);		//button Sensor Vertikal
				
				if(radioButton[12].isSelected())
					gewaehltesBild.setIcon(radioButton[12].getIcon());
				if(radioButton[13].isSelected())
					gewaehltesBild.setIcon(radioButton[13].getIcon());
				
			}
			
		});
		
		gridLinks.add(sensorPlus);
		gridLinks.add(sensorMinus);
	}

	private void initSensorLabels() { //Visualisierung der Sensor Daten
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

	private void initMenuBar() { //Menu Bar zum steuern der Anwendung
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

	private void initAusgewaehltBild() { //das derzeitig ausgewählte Piktogramm wird dargestellt
		gewaehltesBildText = new JLabel("Ausgewaehltes Teil:");
		gewaehltesBild = new JLabel(kreuz);
		
		gridLinks.add(gewaehltesBildText);
		gridLinks.add(gewaehltesBild);
	}

	private void initZeichenflaeche() { //erstellen der Zeichenfläche, 2D Feld aus einzelnen JLabels, Hinzufuegen des Mouseevents
		labelGitter = new JLabel[10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				labelGitter[i][j] = new JLabel(leer);
				gridRechts.add(labelGitter[i][j]);
				
				labelGitter[i][j].addMouseListener(new IconMouseListener(i,j));
				
			}
		}
	}

	private void initWindow() { //erstellen des Hauptfenster
		setSize(800,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void initLayout() { //Zusammenfuegen der einzelnen Teile fuer das Hauptfenster
		
		borderLayout = new JPanel();
		borderLayout.setLayout(new BorderLayout());
		add(borderLayout);
		
		gridSouth = new JPanel(); //Sensordaten
		gridSouth.setLayout(new GridLayout(2,3));
		borderLayout.add(gridSouth,BorderLayout.SOUTH);
		
		gridHaupt = new JPanel(); //nimmt "gridLinks" und "gridRechts" auf
		gridHaupt.setLayout(new GridLayout(1,2));
		borderLayout.add(gridHaupt,BorderLayout.CENTER);
		
		gridLinks = new JPanel(); //Rohrstuecke
		gridLinks.setLayout(new GridLayout(5,4));
		gridHaupt.add(gridLinks);
		
		gridRechts = new JPanel(); //Rohrsystem
		gridRechts.setLayout(new GridLayout(10,10));
		gridHaupt.add(gridRechts);
	}

	private void initRadioButtons() {

		buttonGroup = new ButtonGroup();
		radioButton = new JRadioButton[16];
		
		radioButton[0] = new JRadioButton(kreuz); //Kreuzrohrstueck
		radioButton[0].setSelected(true);
		radioButton[1] = new JRadioButton(leer);
		
		radioButton[2] = new JRadioButton(vertical); //gerade Stücke
		radioButton[3] = new JRadioButton(horizontal);
		
		radioButton[4] = new JRadioButton(lStueckObenLinks); //L-Rohrstuecke
		radioButton[5] = new JRadioButton(lStueckObenRechts);
		radioButton[6] = new JRadioButton(lStueckUntenLinks);
		radioButton[7] = new JRadioButton(lStueckUntenRechts);
		
		radioButton[8] = new JRadioButton(tStueckLinks); //T-Rohrstuecke
		radioButton[9] = new JRadioButton(tStueckOben);
		radioButton[10] = new JRadioButton(tStueckRechts);
		radioButton[11] = new JRadioButton(tStueckUnten);
		
		radioButton[12] = new JRadioButton(sensorHorizontal[0]); //Sensoren
		radioButton[13] = new JRadioButton(sensorVertikal[0]);
		
		radioButton[14] = new JRadioButton(ventilHorizontal); //Ventil
		radioButton[15] = new JRadioButton(pumpe); //Pumpe
		
		for(int i=0;i<radioButton.length;i++){	//radioButtons in eine Gruppe zusammenführen
			buttonGroup.add(radioButton[i]);
			gridLinks.add(radioButton[i]);
			radioButton[i].addActionListener(new RadioButtonActionListener(i));
		
		}
		
	}
	
	public void setSensorTextFieldInhalt(String sensorTextField, int nummerTextField) {
		this.sensorTextField[nummerTextField].setText(sensorTextField);
		//sensorTextField[0]
	}
	
	class RadioButtonActionListener implements ActionListener{

		int nummerDesRadioButtons;
		
		public RadioButtonActionListener(int nummerDesRadioButtons) {
			// TODO Auto-generated constructor stub
			this.nummerDesRadioButtons = nummerDesRadioButtons;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gewaehltesBild.setIcon(radioButton[nummerDesRadioButtons].getIcon());
		}	
	}
	
	class IconMouseListener extends MouseAdapter{
		
		private int xKoordinate;
		private int yKoordinate;
		
		public IconMouseListener(int xKoordinate, int yKoordinate) {
			// TODO Auto-generated constructor stub
			this.xKoordinate = xKoordinate;
			this.yKoordinate = yKoordinate;
		}

		public void mouseClicked(MouseEvent e) {
			
			if(radioButton[0].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(kreuz);
			else if(radioButton[1].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(leer);
			else if(radioButton[2].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(vertical);
			else if(radioButton[3].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(horizontal);
			else if(radioButton[4].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(lStueckObenLinks);
			else if(radioButton[5].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(lStueckObenRechts);
			else if(radioButton[6].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(lStueckUntenLinks);
			else if(radioButton[7].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(lStueckUntenRechts);
			else if(radioButton[8].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(tStueckLinks);
			else if(radioButton[9].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(tStueckOben);
			else if(radioButton[10].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(tStueckRechts);
			else if(radioButton[11].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(tStueckUnten);
			else if(radioButton[12].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(sensorHorizontal[zaehlerSensorBild]);
			else if(radioButton[13].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(sensorVertikal[zaehlerSensorBild]);
			else if(radioButton[14].isSelected())
				labelGitter[xKoordinate][yKoordinate].setIcon(ventilHorizontal);
			else
				labelGitter[xKoordinate][yKoordinate].setIcon(pumpe);
		}
	}

}
