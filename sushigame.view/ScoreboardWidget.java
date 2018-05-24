package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JPanel balanceDisplay;
	private JPanel soldDisplay;
	private JPanel spoiledDisplay;
	private JLabel header = new JLabel("Scoreboard");

	private JComboBox<String> selectScoreboardType = new JComboBox<String>(new String[] {"Display by Balance", "Display by Food Sold", "Display by Food Spoiled"});

	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);

		setBorder(BorderFactory.createEmptyBorder(10,10,10,30));
		setPreferredSize(new Dimension(300,400));
		setMaximumSize(getPreferredSize());

		header.setFont(new Font("Serif", Font.BOLD, 30));
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		selectScoreboardType.setPreferredSize(new Dimension(200, 50));
		selectScoreboardType.setMaximumSize(new Dimension(200, 50));
		selectScoreboardType.setSelectedIndex(0);
		selectScoreboardType.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		selectScoreboardType.addActionListener(this);

		balanceDisplay = new JPanel(new GridLayout(0,1));
		soldDisplay = new JPanel(new GridLayout(0,1));
		spoiledDisplay = new JPanel(new GridLayout(0,1));
		balanceDisplay.setPreferredSize(new Dimension(300, 400));
		soldDisplay.setPreferredSize(new Dimension(300, 400));
		spoiledDisplay.setPreferredSize(new Dimension(300, 400));
		balanceDisplay.setMaximumSize(new Dimension(300, 400));
		soldDisplay.setMaximumSize(new Dimension(300, 400));
		spoiledDisplay.setMaximumSize(new Dimension(300, 400));
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(header);
		add(selectScoreboardType);
		add(balanceDisplay);
		add(soldDisplay);
		add(spoiledDisplay);
		soldDisplay.setVisible(false);
		soldDisplay.setVisible(false);
		refresh();
	}

	public void refresh() {
		String displayType = (String)selectScoreboardType.getSelectedItem();
		switch (displayType) {
		case "Display by Balance":
			// Create an array of all chefs and sort by balance.
			Chef[] opponent_chefs2= game_model.getOpponentChefs();
			Chef[] chefs2 = new Chef[opponent_chefs2.length+1];
			chefs2[0] = game_model.getPlayerChef();
			for (int i=1; i<chefs2.length; i++) {
				chefs2[i] = opponent_chefs2[i-1];
			}
			Arrays.sort(chefs2, new HighToLowBalanceComparator());

			JLabel[] chefsBalance = new JLabel[chefs2.length];
			balanceDisplay.removeAll();
			for (int i = 0; i < chefs2.length; i++) {
				chefsBalance[i] = new JLabel();
				chefsBalance[i].setText((i+1) + ". " + chefs2[i].getName() + " ($" + chefs2[i].getBalance() + ")");
				balanceDisplay.add(chefsBalance[i]);
			}
			break;
		case "Display by Food Sold":
			// Create an array of all chefs and sort by food sold.
			Chef[] opponent_chefs= game_model.getOpponentChefs();
			Chef[] chefs = new Chef[opponent_chefs.length+1];
			chefs[0] = game_model.getPlayerChef();
			for (int i=1; i<chefs.length; i++) {
				chefs[i] = opponent_chefs[i-1];
			}
			Arrays.sort(chefs, new HighToLowSoldComparator());

			JLabel[] chefsFoodSold = new JLabel[chefs.length];
			soldDisplay.removeAll();
			for (int i = 0; i < chefs.length; i++) {
				chefsFoodSold[i] = new JLabel();
				chefsFoodSold[i].setText((i+1) + ". " + chefs[i].getName() + " (" + chefs[i].getFoodSold() + " oz)");
				soldDisplay.add(chefsFoodSold[i]);
			}

			break;
		case "Display by Food Spoiled":
			// Create an array of all chefs and sort by spoiled food.
			Chef[] opponent_chefs1= game_model.getOpponentChefs();
			Chef[] chefs1 = new Chef[opponent_chefs1.length+1];
			chefs1[0] = game_model.getPlayerChef();
			for (int i=1; i<chefs1.length; i++) {
				chefs1[i] = opponent_chefs1[i-1];
			}
			Arrays.sort(chefs1, new LowToHighSpoiledComparator());

			JLabel[] chefsFoodSpoiled = new JLabel[chefs1.length];
			spoiledDisplay.removeAll();
			for (int i = 0; i < chefs1.length; i++) {
				chefsFoodSpoiled[i] = new JLabel();
				chefsFoodSpoiled[i].setText((i+1) + ". " + chefs1[i].getName() + " (" + chefs1[i].getFoodSpoiled() + " oz)");
				spoiledDisplay.add(chefsFoodSpoiled[i]);
			}
		}
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String displayType = (String)selectScoreboardType.getSelectedItem();
		switch (displayType) {
		case "Display by Balance":
			refresh();
			soldDisplay.setVisible(false);
			spoiledDisplay.setVisible(false);
			balanceDisplay.setVisible(true);
			break;
		case "Display by Food Sold":
			refresh();
			soldDisplay.setVisible(true);
			spoiledDisplay.setVisible(false);
			balanceDisplay.setVisible(false);
			break;
		case "Display by Food Spoiled":
			refresh();
			soldDisplay.setVisible(false);
			spoiledDisplay.setVisible(true);
			balanceDisplay.setVisible(false);
			break;
		}
	}

}
