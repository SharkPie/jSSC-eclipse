/*
* Datei: LabelTest.java
*
* Die Klasse stellt das Hauptfenster zur Verfuegung, in welchem die Rohrleitungen visualisiert werden
*
*
*
*/

package view;

import controller.ViewControl;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

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
public class Hauptfenster extends JFrame{
	
	int anzahlSensor; //Anzahl der zu ueberwachenden Sensoren
	int zaehlerSensorBild;
	
	ViewControl actionListener;
	
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
	
	public Hauptfenster(int anzahlSensor, ViewControl actionListener){
		// TODO Auto-generated constructor stub
		this.anzahlSensor = anzahlSensor;
		this.zaehlerSensorBild = 0;
		
		this.actionListener = actionListener;
		
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
		
		setSensorLabels(1);
		
		initRadioButtons();
		
		initAusgewaehltBild();
		
		initSensorErh�henButton();
		
		initZeichenflaeche();
		
		initWindow();
	}

	private void initSensorLabels() {
		sensorLabel = new JLabel[5];
		sensorTextField = new JTextField[5];
		for(int i=0;i<5;i++){
			sensorLabel[i] = new JLabel();
			sensorTextField[i] = new JTextField("0.000");
			sensorTextField[i].setEditable(false);
		}
	}

	private void initSensorErh�henButton() { //Buttons zur Sensor Manipulation erstellen
		sensorPlus = new JButton("Sensor +");
		sensorMinus = new JButton("Sensor -");
		
		/*
		 * 
		 */
		sensorPlus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
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

	public void setSensorLabels(int anzahl) { //Visualisierung der Sensor Daten
		anzahlSensor = anzahl;
		gridSouth.removeAll();
		
		for(int i=0;i<anzahlSensor;i++){
			StringBuilder sb = new StringBuilder();
			sb.append("Sensor ");
			sb.append(i+1);
			sensorLabel[i].setText(sb.toString());
			gridSouth.add(sensorLabel[i]);
			this.getContentPane().validate();
			this.getContentPane().repaint();
			}
		
		for(int i=0;i<anzahlSensor;i++){
			gridSouth.add(sensorTextField[i]);
			this.getContentPane().validate();
			this.getContentPane().repaint();
		}
		
	}
	
	private void initMenuBar() { //Menu Bar zum steuern der Anwendung
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");				//File Bereich
		JMenuItem[] menuItemFile = new JMenuItem[4];
		menuItemFile[0] = new JMenuItem("Speichern");
		menuItemFile[1] = new JMenuItem("Laden");
		menuItemFile[2] = new JMenuItem("About");
		menuItemFile[3] = new JMenuItem("Exit");
		
		for(int i=0;i<menuItemFile.length;i++){
			fileMenu.add(menuItemFile[i]);
			menuItemFile[i].addActionListener(actionListener);
		}
		menuBar.add(fileMenu);
		
		JMenu comPortMenu = new JMenu("ComPort");		//ComPort Bereich
		JMenuItem[] menuItemComPort = new JMenuItem[3];
		menuItemComPort[0] = new JMenuItem("ComPort ausw�hlen");
		menuItemComPort[1] = new JMenuItem("ComPort starten");
		menuItemComPort[2] = new JMenuItem("ComPort stoppen");
		
		for(int i=0;i<menuItemComPort.length;i++){
			comPortMenu.add(menuItemComPort[i]);
			menuItemComPort[i].addActionListener(actionListener);
		}
		menuBar.add(comPortMenu);
		
		JMenu sensorMenu = new JMenu("Sensor");				//Sensor Bereich
		JMenuItem menuItemSensor = new JMenuItem("Anzahl Sensoren");
		menuItemSensor.addActionListener(actionListener);
		sensorMenu.add(menuItemSensor);
		menuBar.add(sensorMenu);
		
		setJMenuBar(menuBar);
		this.setJMenuBar(menuBar);
	}

	private void initAusgewaehltBild() { //das derzeitig ausgew�hlte Piktogramm wird dargestellt
		gewaehltesBildText = new JLabel("Ausgewaehltes Teil:");
		gewaehltesBild = new JLabel(kreuz);
		
		gridLinks.add(gewaehltesBildText);
		gridLinks.add(gewaehltesBild);
	}

	private void initZeichenflaeche() { //erstellen der Zeichenfl�che, 2D Feld aus einzelnen JLabels, Hinzufuegen des Mouseevents
		labelGitter = new JLabel[10][10];
		for(int i=0;i<labelGitter.length;i++){
			for(int j=0;j<labelGitter.length;j++){
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
		
		radioButton[2] = new JRadioButton(vertical); //gerade St�cke
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
		radioButton[12].setToolTipText("Sensor");
		radioButton[13] = new JRadioButton(sensorVertikal[0]);
		radioButton[13].setToolTipText("Sensor");
		
		radioButton[14] = new JRadioButton(ventilHorizontal); //Ventil
		radioButton[14].setToolTipText("Ventil");
		radioButton[15] = new JRadioButton(pumpe); //Pumpe
		radioButton[15].setToolTipText("Pumpe");
		
		for(int i=0;i<radioButton.length;i++){	//radioButtons in eine Gruppe zusammenf�hren
			buttonGroup.add(radioButton[i]);
			gridLinks.add(radioButton[i]);
			radioButton[i].addActionListener(new RadioButtonActionListener(i));
		
		}
		
	}
	
	public void setSensorTextFieldInhalt(String sensorTextField, int nummerTextField) {
		this.sensorTextField[nummerTextField-1].setText(sensorTextField);
	}
	
	public int[] getSensorFields(int sensorNummer){	//sucht die Position eines Sensores mit einer bestimmten Nummer
		int[] postion = new int[2];					
		for(int i=0;i<labelGitter.length;i++){
			for(int j=0;j<labelGitter.length;j++){
				if(labelGitter[i][j].getIcon().equals(sensorHorizontal[sensorNummer-1])
						||labelGitter[i][j].getIcon().equals(sensorVertikal[sensorNummer-1])){
					postion[0] = i;
					postion[1] = j;
				}
			}
		}
		
		return postion;	
	}
	
	public void setLabelGitterTooltip(String sensorWert, int[] position){//setzt ein Tooltip im LabelGitter 
		labelGitter[position[0]][position[1]].setToolTipText(sensorWert);//auf eine �bergegebene position
																		//->Sensoren
	}
	public int getAnzahlSensor() {
		return anzahlSensor;
	}
	
	public String speichern(){
		StringBuilder speichernString = new StringBuilder();
		for(int i=0;i<labelGitter.length;i++){
			for(int j=0;j<labelGitter.length;j++){
				speichernString.append(labelGitter[i][j].getIcon().toString());
				speichernString.append(":");
			}
		}
		return speichernString.toString();
	}
	
	public void laden(String ladenString){
		String[] splitString = ladenString.split(Pattern.quote(":"));
		for(int i=0;i<labelGitter.length;i++){
			for(int j=0;j<labelGitter.length;j++){
				int image = i * labelGitter.length + j;
				labelGitter[i][j].setIcon(new ImageIcon(splitString[image]));
			}
		}
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
