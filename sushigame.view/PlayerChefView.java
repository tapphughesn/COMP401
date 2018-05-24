package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import comp401.sushi.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comp401.sushi.AvocadoPortion;
import comp401.sushi.EelPortion;
import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri;
import comp401.sushi.Nigiri.NigiriType;
import comp401.sushi.Plate;
import comp401.sushi.RedPlate;
import comp401.sushi.Roll;
import comp401.sushi.Sashimi;
import comp401.sushi.Sashimi.SashimiType;
import comp401.sushi.SeaweedPortion;
import comp401.sushi.Sushi;

public class PlayerChefView extends JPanel implements ActionListener, ChangeListener {

	private List<ChefViewListener> listeners;
	private int belt_size;
	private JComboBox<String> selectSushiType;
	private JLabel sushiTypeLabel = new JLabel("Select Sushi Type:      ", SwingConstants.TRAILING);
	private JComboBox<String> selectPlateColor;
	private JLabel plateColorLabel = new JLabel("Select Plate Color:      ", SwingConstants.TRAILING);
	private JComboBox<String> selectSashimiType;
	private JLabel sashimiTypeLabel = new JLabel("Select Sashimi Type:      ", SwingConstants.TRAILING);
	private JComboBox<String> selectNigiriType;
	private JLabel nigiriTypeLabel = new JLabel("Select Nigiri Type:      ", SwingConstants.TRAILING);
	private JComboBox<String> selectPosition;
	private JLabel positionLabel = new JLabel("Select Position:      ", SwingConstants.TRAILING);
	private JSlider[] sliders;
	private JSlider avocadoAmount = new JSlider(0, 150, 0);
	private JSlider crabAmount = new JSlider(0, 150, 0);
	private JSlider eelAmount = new JSlider(0, 150, 0);
	private JSlider riceAmount = new JSlider(0, 150, 0);
	private JSlider salmonAmount = new JSlider(0, 150, 0);
	private JSlider seaweedAmount = new JSlider(0, 150, 0);
	private JSlider shrimpAmount = new JSlider(0, 150, 0);
	private JSlider tunaAmount = new JSlider(0, 150, 0);

	private JLabel[] labels;
	private JLabel avocadoLabel = new JLabel("Avocado Amount: 0.0 oz    ", SwingConstants.TRAILING);
	private JLabel crabLabel = new JLabel("Crab Amount: 0.0 oz    ", SwingConstants.TRAILING);
	private JLabel eelLabel = new JLabel("Eel Amount: 0.0 oz    ", SwingConstants.TRAILING);
	private JLabel riceLabel = new JLabel("Rice Amount: 0.0 oz    ", SwingConstants.TRAILING);
	private JLabel salmonLabel = new JLabel("Salmon Amount: 0.0 oz    ", SwingConstants.TRAILING);
	private JLabel seaweedLabel = new JLabel("Seaweed Amount: 0.0 oz    ", SwingConstants.TRAILING);
	private JLabel shrimpLabel = new JLabel("Shrimp Amount: 0.0 oz    ", SwingConstants.TRAILING);
	private JLabel tunaLabel = new JLabel("Tuna Amount: 0.0 oz    ", SwingConstants.TRAILING);
	
	private JLabel rollNameLabel = new JLabel("Roll Name:      ", SwingConstants.TRAILING);
	private JTextField rollName = new JTextField("", 15);
	private JPanel rollNamePanel = new JPanel(new BorderLayout());
	
	private JLabel priceLabel = new JLabel("Price of Gold Plate:      ", SwingConstants.TRAILING);
	private JSlider priceSlider = new JSlider(500, 1000, 500);

	private JButton makePlate;
	private JPanel ings;
	private JPanel selections;

	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(700, 700));
		setMaximumSize(new Dimension(700, 700));
		
		JPanel header = new JPanel(new GridLayout(1,2));
		JLabel playerUITitle = new JLabel("Create Your Plate Here", SwingConstants.CENTER);
		playerUITitle.setFont(new Font("Serif", Font.BOLD, 25));
		playerUITitle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		header.add(playerUITitle);
		header.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		add(header);
		
		selectSushiType = new JComboBox<String>(new String[]{"Roll", "Sashimi", "Nigiri"});
		selectSushiType.setPreferredSize(new Dimension(100, 50));
		selectSushiType.setMaximumSize(new Dimension(100, 50));
		selectSushiType.addActionListener(this);

		selectPlateColor = new JComboBox<String>(new String[] {"Red", "Green", "Blue", "Gold"});
		selectPlateColor.setPreferredSize(new Dimension(100, 50));
		selectPlateColor.setMaximumSize(new Dimension(100, 50));
		selectPlateColor.addActionListener(this);

		selectSashimiType = new JComboBox<String>(new String[] {"Crab", "Eel", "Salmon", "Shrimp", "Tuna"});
		selectSashimiType.setPreferredSize(new Dimension(100, 50));
		selectSashimiType.setMaximumSize(new Dimension(100, 50));
		selectSashimiType.addActionListener(this);
		selectSashimiType.setVisible(false);
		sashimiTypeLabel.setVisible(false);

		selectNigiriType = new JComboBox<String>(new String[] {"Crab", "Eel", "Salmon", "Shrimp", "Tuna"});
		selectNigiriType.setPreferredSize(new Dimension(100, 50));
		selectNigiriType.setMaximumSize(new Dimension(100, 50));
		selectNigiriType.addActionListener(this);
		selectNigiriType.setVisible(false);
		nigiriTypeLabel.setVisible(false);

		selectPosition = new JComboBox<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"});
		selectPosition.setPreferredSize(new Dimension(100, 50));
		selectPosition.setMaximumSize(new Dimension(100, 50));
		selectPosition.addActionListener(this);
		
		selections = new JPanel(new GridLayout(0,2));
		JLabel[] selectionLabels = new JLabel[] {sushiTypeLabel, plateColorLabel, sashimiTypeLabel, nigiriTypeLabel, positionLabel};
		JComboBox[] selectionBoxes = new JComboBox[] {selectSushiType, selectPlateColor, selectSashimiType, selectNigiriType, selectPosition};
		for (int i = 0; i<5; i++) {
			selections.add(selectionLabels[i]);
			selections.add(selectionBoxes[i]);
		}
		add(selections);
		
		
		sliders = new JSlider[]{avocadoAmount, crabAmount, eelAmount, riceAmount, salmonAmount, seaweedAmount, shrimpAmount, tunaAmount};
		labels = new JLabel[] {avocadoLabel, crabLabel, eelLabel, riceLabel, salmonLabel, seaweedLabel, shrimpLabel, tunaLabel};
		
		Hashtable<Integer, JLabel> ingLabelTable = new Hashtable<Integer, JLabel>();
		ingLabelTable.put(new Integer(0), new JLabel("0"));
		ingLabelTable.put(new Integer(150), new JLabel("1.5"));
		
		ings = new JPanel(new GridLayout(5,4));
		for (int i = 0; i<8; i++) {
			sliders[i].setLabelTable(ingLabelTable);
			sliders[i].setPaintLabels(true);
			sliders[i].setMinorTickSpacing(50);
			sliders[i].setPaintTicks(true);
			sliders[i].addChangeListener(this);
			ings.add(labels[i]);
			ings.add(sliders[i]);
		}
		
		ings.add(rollNameLabel);
		rollName.addActionListener(this);
		rollNamePanel.add(rollName, BorderLayout.CENTER);
		rollNamePanel.add(new JLabel("(press enter)"), BorderLayout.NORTH);
		rollNamePanel.add(new JLabel(" "), BorderLayout.SOUTH);
		ings.add(rollNamePanel);
		
		Hashtable<Integer, JLabel> priceLabelTable = new Hashtable<Integer, JLabel>();
		priceLabelTable.put(new Integer(500), new JLabel("$5.00"));
		priceLabelTable.put(new Integer(750), new JLabel("$7.50"));
		priceLabelTable.put(new Integer(1000), new JLabel("$10.00"));
		priceSlider.setLabelTable(priceLabelTable);
		priceSlider.setPaintLabels(true);
		priceSlider.setMinorTickSpacing(100);
		priceSlider.setPaintTicks(true);
		priceSlider.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		priceLabel.setVisible(false);
		priceSlider.setVisible(false);
		priceSlider.addChangeListener(this);
		ings.add(priceLabel);
		ings.add(priceSlider);
		add(ings);

		makePlate = new JButton("Place Plate!");
		makePlate.setActionCommand("make_plate");
		makePlate.addActionListener(this);
		add(makePlate);

	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}


	private void makeSushiTypeRequest(Plate.SushiType t) {
		for (ChefViewListener l : listeners) {
			l.handleTypeChangeRequest(t);
		}
	}
	private void makeColorChangeRequest(Plate.Color c) {
		for (ChefViewListener l : listeners) {
			l.handleColorChangeRequest(c);
		}
	}
	private void makeIngredientChangeRequest(IngredientPortion ip) {
		for (ChefViewListener l : listeners) {
			l.handleIngredientChangeRequest(ip);
		}
	}
	private void makePlateRequest() {
		for (ChefViewListener l : listeners) {
			l.handleMakePlateRequest();
		}
	}
	private void makePriceChangeRequest(double price) {
		for (ChefViewListener l : listeners) {
			l.handlePriceChangeRequest(price);
		}
	}
	private void makePositionChangeRequest(int pos) {
		for (ChefViewListener l : listeners) {
			l.handlePositionChangeRequest(pos);
		}
	}
	private void makeNameRequest(String name) {
		for (ChefViewListener l : listeners) {
			l.handleNameRequest(name);
		}
	}
	private void makeSashimiTypeRequest(SashimiType sashimiTypeRequested) {
		for (ChefViewListener l : listeners) {
			l.handleSashimiTypeRequest(sashimiTypeRequested);
		}
	}
	private void makeNigiriTypeRequest(NigiriType nigiriTypeRequested) {
		for (ChefViewListener l : listeners) {
			l.handleNigiriTypeRequest(nigiriTypeRequested);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == selectSushiType) {
			Plate.SushiType typeRequested = null;
			String type = (String)selectSushiType.getSelectedItem();
			switch (type) {
			case "Roll":
				typeRequested = Plate.SushiType.ROLL;
				setIngsVisible(true);
				selectSashimiType.setVisible(false);
				sashimiTypeLabel.setVisible(false);
				selectNigiriType.setVisible(false);
				nigiriTypeLabel.setVisible(false);
				break;
			case "Sashimi":
				typeRequested = Plate.SushiType.SASHIMI;
				setIngsVisible(false);
				selectSashimiType.setVisible(true);
				sashimiTypeLabel.setVisible(true);
				selectNigiriType.setVisible(false);
				nigiriTypeLabel.setVisible(false);
				break;
			case "Nigiri":
				typeRequested = Plate.SushiType.NIGIRI;
				setIngsVisible(false);
				selectSashimiType.setVisible(false);
				sashimiTypeLabel.setVisible(false);
				selectNigiriType.setVisible(true);
				nigiriTypeLabel.setVisible(true);
				break;
			default:
				throw new RuntimeException("Selected JComboBox selectSushiType item which I didn't put on there");
			}
			makeSushiTypeRequest(typeRequested);
		}
		else if (e.getSource() == selectPlateColor) {
			priceLabel.setVisible(false);
			priceSlider.setVisible(false);
			Plate.Color colorRequested = null;
			String type = (String)selectPlateColor.getSelectedItem();
			switch (type) {
			case "Red":
				colorRequested = Plate.Color.RED;
				break;
			case "Green":
				colorRequested = Plate.Color.GREEN;
				break;
			case "Blue":
				colorRequested = Plate.Color.BLUE;
				break;
			case "Gold":
				colorRequested = Plate.Color.GOLD;
				makePriceChangeRequest(5.0);
				priceLabel.setVisible(true);
				priceSlider.setVisible(true);
				break;
			default:
				throw new RuntimeException("Selected JComboBox selectPlateColor item which I didn't put on there");
			}
			makeColorChangeRequest(colorRequested);
		}
		else if (e.getSource() == selectSashimiType) {
			Sashimi.SashimiType sashimiTypeRequested = null;
			switch ((String)selectSashimiType.getSelectedItem()) {
			case "Crab":
				sashimiTypeRequested = Sashimi.SashimiType.CRAB;
				break;
			case "Eel":
				sashimiTypeRequested = Sashimi.SashimiType.EEL;
				break;
			case "Salmon":
				sashimiTypeRequested = Sashimi.SashimiType.SALMON;
				break;
			case "Shrimp":
				sashimiTypeRequested = Sashimi.SashimiType.SHRIMP;
				break;
			case "Tuna":
				sashimiTypeRequested = Sashimi.SashimiType.TUNA;
				break;
			default:
				throw new RuntimeException("Sashimi type bad");
			}
			makeSashimiTypeRequest(sashimiTypeRequested);
		}
		else if (e.getSource() == selectNigiriType) {
			Nigiri.NigiriType nigiriTypeRequested = null;
			switch ((String)selectNigiriType.getSelectedItem()) {
			case "Crab":
				nigiriTypeRequested = Nigiri.NigiriType.CRAB;
				break;
			case "Eel":
				nigiriTypeRequested = Nigiri.NigiriType.EEL;
				break;
			case "Salmon":
				nigiriTypeRequested = Nigiri.NigiriType.SALMON;
				break;
			case "Shrimp":
				nigiriTypeRequested = Nigiri.NigiriType.SHRIMP;
				break;
			case "Tuna":
				nigiriTypeRequested = Nigiri.NigiriType.TUNA;
				break;
			default:
				throw new RuntimeException("Nigiri type bad");
			}
			makeNigiriTypeRequest(nigiriTypeRequested);
		}
		else if (e.getSource() == rollName) {
			String name = rollName.getText();
			makeNameRequest(name);
		}
		else if (e.getSource() == makePlate) {
			makePlateRequest();
		}
		else if (e.getSource() == selectPosition) {
			int pos = 0;
			pos = selectPosition.getSelectedIndex() + 1;
			makePositionChangeRequest(pos);
		}
	}

	private void setIngsVisible(boolean vis) {
		for (JLabel l : labels) {
			l.setVisible(vis);
		}
		for (JSlider s : sliders) {
			s.setVisible(vis);
		}
		rollNameLabel.setVisible(vis);
		rollNamePanel.setVisible(vis);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		double ingAmount = 0.0;
		boolean ingChange = false;
		int ingIndex = 10;
		for (int i = 0; i<8; i++) {
			if(e.getSource() == sliders[i]) {
				ingAmount = ((double) sliders[i].getValue()) / 100.0;
				ingChange = true;
				ingIndex = i;
			}
		}
		if (ingChange) {
			switch (ingIndex) {
			case 0:
				makeIngredientChangeRequest(new AvocadoPortion(ingAmount));
				avocadoLabel.setText("Avocado Amount: " + ingAmount + " oz");
				break;
			case 1:
				makeIngredientChangeRequest(new CrabPortion(ingAmount));
				crabLabel.setText("Crab Amount: " + ingAmount + " oz");
				break;
			case 2:
				makeIngredientChangeRequest(new EelPortion(ingAmount));
				eelLabel.setText("Eel Amount: " + ingAmount + " oz");
				break;
			case 3:
				makeIngredientChangeRequest(new RicePortion(ingAmount));
				riceLabel.setText("Rice Amount: " + ingAmount + " oz");
				break;
			case 4:
				makeIngredientChangeRequest(new SalmonPortion(ingAmount));
				salmonLabel.setText("Salmon Amount: " + ingAmount + " oz");
				break;
			case 5:
				makeIngredientChangeRequest(new SeaweedPortion(ingAmount));
				seaweedLabel.setText("Seaweed Amount: " + ingAmount + " oz");
				break;
			case 6:
				makeIngredientChangeRequest(new ShrimpPortion(ingAmount));
				shrimpLabel.setText("Shrimp Amount: " + ingAmount + " oz");
				break;
			case 7:
				makeIngredientChangeRequest(new TunaPortion(ingAmount));
				tunaLabel.setText("Tuna Amount: " + ingAmount + " oz");
				break;
			}
			
		}
		if (e.getSource() == priceSlider) {
			double price = ((double) priceSlider.getValue()) / 100.0;
			priceLabel.setText("Price of Gold Plate: $" + price + "   ");
			makePriceChangeRequest(price);
		}
	}
}

