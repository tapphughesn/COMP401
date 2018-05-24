package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import comp401.sushi.Nigiri;
import comp401.sushi.Plate;
import comp401.sushi.Sashimi;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver, ActionListener {

	private Belt belt;
	private JPanel leftBeltSide = new JPanel();
	private JPanel rightPlateView = new JPanel();
	private JLabel defaultRightSide = new JLabel("Click a plate to display its information");
	private JButton[] belt_buttons;
	private JLabel chefName = new JLabel("Chef: ");
	private JLabel plateColor = new JLabel("Plate Color: ");
	private JLabel sushiType = new JLabel("Sushi Type: ");
	private JLabel sashimiType = new JLabel("Sashimi Type: ");
	private JLabel rollName = new JLabel("Name of Roll: ");
	private JLabel rollIngredients = new JLabel("");
	private JLabel plateAge = new JLabel("Age of Plate: ");
	private JLabel[] labels = new JLabel[] {chefName, plateColor, sushiType, sashimiType, rollName, plateAge};

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new BorderLayout());

		defaultRightSide.setFont(new Font("Serif", Font.BOLD, 16));
		defaultRightSide.setVerticalAlignment(SwingConstants.CENTER);
		defaultRightSide.setHorizontalAlignment(SwingConstants.CENTER);
		defaultRightSide.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		leftBeltSide.setLayout(new GridLayout(belt.getSize(), 1));
		rightPlateView.setLayout(new BoxLayout(rightPlateView, BoxLayout.Y_AXIS));
		belt_buttons = new JButton[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			JButton plabel = new JButton("");
			plabel.setMinimumSize(new Dimension(250, 20));
			plabel.setPreferredSize(new Dimension(250, 20));
			plabel.setOpaque(true);
			plabel.setBackground(Color.GRAY);
			plabel.addActionListener(this);
			plabel.setActionCommand("" + i);
			leftBeltSide.add(plabel);
			belt_buttons[i] = plabel;
		}
		rightPlateView.add(defaultRightSide);

		chefName.setBorder(BorderFactory.createEmptyBorder(15,1,30,0));
		plateColor.setBorder(BorderFactory.createEmptyBorder(15,10,30,0));
		sushiType.setBorder(BorderFactory.createEmptyBorder(15,10,30,0));
		sashimiType.setBorder(BorderFactory.createEmptyBorder(15,10,30,0));
		rollName.setBorder(BorderFactory.createEmptyBorder(15,10,30,0));
		rollIngredients.setBorder(BorderFactory.createEmptyBorder(15,10,30,0));
		plateAge.setBorder(BorderFactory.createEmptyBorder(15,10,30,0));

		chefName.setHorizontalAlignment(SwingConstants.CENTER);
		plateColor.setHorizontalAlignment(SwingConstants.CENTER);
		sushiType.setHorizontalAlignment(SwingConstants.CENTER);
		sashimiType.setHorizontalAlignment(SwingConstants.CENTER);
		rollName.setHorizontalAlignment(SwingConstants.CENTER);
		rollIngredients.setHorizontalAlignment(SwingConstants.CENTER);
		plateAge.setHorizontalAlignment(SwingConstants.CENTER);

		chefName.setFont(new Font("SansSerif", Font.PLAIN, 16));
		plateColor.setFont(new Font("SansSerif", Font.PLAIN, 16));
		sushiType.setFont(new Font("SansSerif", Font.PLAIN, 16));
		sashimiType.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rollName.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rollIngredients.setFont(new Font("SansSerif", Font.PLAIN, 16));
		plateAge.setFont(new Font("SansSerif", Font.PLAIN, 16));	

		chefName.setVisible(false);
		plateColor.setVisible(false);
		sushiType.setVisible(false);
		sashimiType.setVisible(false);
		rollName.setVisible(false);
		rollIngredients.setVisible(false);
		plateAge.setVisible(false);

		rightPlateView.add(chefName);
		rightPlateView.add(plateColor);
		rightPlateView.add(sushiType);
		rightPlateView.add(sashimiType);
		rightPlateView.add(rollName);
		rightPlateView.add(rollIngredients);
		rightPlateView.add(plateAge);

		leftBeltSide.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 1, Color.black));
		rightPlateView.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 2, Color.black));

		add(leftBeltSide, BorderLayout.WEST);
		add(rightPlateView, BorderLayout.EAST);
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			JButton plabel = belt_buttons[i];
			if (p == null) {
				plabel.setText("");
				plabel.setBackground(Color.GRAY);
			} else {
				plabel.setText(p.getChef().getName() + "'s " + p.getContents().getSushiType().toString());
				switch (p.getColor()) {
				case RED:
					plabel.setBackground(Color.RED); break;
				case GREEN:
					plabel.setBackground(Color.GREEN); break;
				case BLUE:
					plabel.setBackground(Color.CYAN); break;
				case GOLD:
					plabel.setBackground(Color.YELLOW); break;
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		int index = Integer.parseInt(command);
		Plate p = belt.getPlateAtPosition(index);
		if (p == null) {
			defaultRightSide.setVisible(true);
			for (JLabel l : labels) {
				l.setVisible(false);
			}
		} else {
			chefName.setText("Chef: " + p.getChef().getName());

			String pColor = "";
			switch(p.getColor()) {
			case RED:
				pColor = "Red";
				break;
			case GREEN:
				pColor = "Green";
				break;
			case BLUE:
				pColor = "Blue";
				break;
			case GOLD:
				pColor = "Gold";
				break;
			}
			plateColor.setText("Plate Color: " + pColor);  

			String pSushiType = "";
			switch(p.getContents().getSushiType()) {
			case SASHIMI:
				pSushiType = "Sashimi";
				break;
			case NIGIRI:
				pSushiType = "Nigiri";
				break;
			case ROLL:
				pSushiType = "Roll";
				break;
			}
			sushiType.setText("Sushi Type: " + pSushiType);

			if (pSushiType.equals("Sashimi")) {
				rollName.setVisible(false);
				rollIngredients.setVisible(false);
				sashimiType.setVisible(true);
				Sashimi pSashimi = (Sashimi) p.getContents();
				String pSashimiType = "";
				switch (pSashimi.getType()) {
				case CRAB:
					pSashimiType = "Crab";
					break;
				case EEL:
					pSashimiType = "Eel";
					break;
				case SALMON:
					pSashimiType = "Salmon";
					break;
				case SHRIMP:
					pSashimiType = "Shrimp";
					break;
				case TUNA:
					pSashimiType = "Tuna";
					break;
				default:
					break;
				}
				sashimiType.setText("Sashimi Type: " + pSashimiType);	
			} else if (pSushiType.equals("Nigiri")) {
				rollName.setVisible(false);
				rollIngredients.setVisible(false);
				sashimiType.setVisible(true);
				Nigiri pNigiri = (Nigiri) p.getContents();
				String pNigiriType = "";
				switch (pNigiri.getType()) {
				case CRAB:
					pNigiriType = "Crab";
					break;
				case EEL:
					pNigiriType = "Eel";
					break;
				case SALMON:
					pNigiriType = "Salmon";
					break;
				case SHRIMP:
					pNigiriType = "Shrimp";
					break;
				case TUNA:
					pNigiriType = "Tuna";
					break;
				default:
					break;
				}
				sashimiType.setText("Nigiri Type: " + pNigiriType);	
			} else if (pSushiType.equals("Roll")) {
				sashimiType.setVisible(false);
				rollName.setVisible(true);
				String name = p.getContents().getName();
				rollName.setText("Name of Roll: " + name);
				rollName.setVisible(true);
				int ings = p.getContents().getIngredients().length;
				String rollIngs = "<html><body>Roll Ingredients:   <br>";
				for (int i = 0; i<ings; i++) {
					double amount = ((double)((int)(((p.getContents().getIngredients()[i].getAmount())*100)+0.5)))/100.0;
					rollIngs += (p.getContents().getIngredients()[i].getName() + " amount: " + amount + " oz<br>");
				}
				rollIngs += "</body></html>";
				rollIngredients.setText(rollIngs);
				rollIngredients.setVisible(true);
			}

			plateAge.setText("Age of Plate: " + belt.getAgeOfPlateAtPosition(index) + " rotations");  

			chefName.setVisible(true);
			plateColor.setVisible(true);
			sushiType.setVisible(true);
			plateAge.setVisible(true);
		}
	}

}
