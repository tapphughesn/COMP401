package sushigame.controller;

import comp401.sushi.BluePlate;
import comp401.sushi.GoldPlate;
import comp401.sushi.GreenPlate;
import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri.NigiriType;
import comp401.sushi.Plate;
import comp401.sushi.Plate.Color;
import comp401.sushi.Plate.SushiType;
import comp401.sushi.PlatePriceException;
import comp401.sushi.RedPlate;
import comp401.sushi.Sashimi.SashimiType;
import comp401.sushi.Sushi;
import sushigame.model.AlreadyPlacedThisRotationException;
import sushigame.model.BeltFullException;
import sushigame.model.Chef;
import sushigame.model.InsufficientBalanceException;
import sushigame.model.PlayerPlateComponents;
import sushigame.view.ChefViewListener;
import sushigame.view.SushiGameView;

public class PlayerChefController implements ChefViewListener {

	private Chef chef;
	private SushiGameView game_view;
	private PlayerPlateComponents playerPlate;

	public PlayerChefController(Chef playerChef, PlayerPlateComponents playerPlate, SushiGameView gv) {
		chef = playerChef;
		this.game_view = gv;
		this.playerPlate = playerPlate;
	}

	private void placePlate(Plate plate, int position) {
		try {
			chef.makeAndPlacePlate(plate, position);
		} catch (InsufficientBalanceException e) {
			game_view.setControllerMessage("Insufficient balance");
		} catch (BeltFullException e) {
			game_view.setControllerMessage("Belt is full");
		} catch (AlreadyPlacedThisRotationException e) {
			game_view.setControllerMessage("Already placed a plate this rotation");
		} catch (Exception e) {
			game_view.setControllerMessage(e.getMessage());
		}
	}

	@Override
	public void handleTypeChangeRequest(SushiType t) {
		playerPlate.setPlateFoodType(t);
	}

	@Override
	public void handleColorChangeRequest(Color c) {
		playerPlate.setPlateColor(c);
	}

	@Override
	public void handleIngredientChangeRequest(IngredientPortion ip) {
		String ingName = ip.getName();
		switch (ingName) {
		case "avocado":
			playerPlate.setAvocado(ip.getAmount());
			break;
		case "crab":
			playerPlate.setCrab(ip.getAmount());
			break;
		case "eel":
			playerPlate.setEel(ip.getAmount());
			break;
		case "rice":
			playerPlate.setRice(ip.getAmount());
			break;
		case "salmon":
			playerPlate.setSalmon(ip.getAmount());
			break;
		case "seaweed":
			playerPlate.setSeaweed(ip.getAmount());
			break;
		case "shrimp":
			playerPlate.setShrimp(ip.getAmount());
			break;
		case "tuna":
			playerPlate.setTuna(ip.getAmount());
			break;
		}
	}

	@Override
	public void handleMakePlateRequest() {
		Plate p = null;
		try {
			switch(playerPlate.getPlateColor()) {
			case RED:
				p = new RedPlate(chef, playerPlate.getSushi());
				break;
			case GREEN:
				p = new GreenPlate(chef, playerPlate.getSushi());
				break;
			case BLUE:
				p = new BluePlate(chef, playerPlate.getSushi());
				break;
			case GOLD:
				p = new GoldPlate(chef, playerPlate.getSushi(), playerPlate.getPrice());
				break;
			}
		} catch (PlatePriceException e) {
			game_view.setControllerMessage("Sushi too costly for a plate of this color.");
		}
		placePlate(p, playerPlate.getPosition());
	}

	@Override
	public void handlePriceChangeRequest(double price) {
		if (playerPlate.getPlateColor() == Plate.Color.GOLD) {
			playerPlate.setPrice(price);
		}
	}

	@Override
	public void handlePositionChangeRequest(int pos) {
		playerPlate.setPosition(pos - 1);
	}

	@Override
	public void handleNigiriTypeRequest(NigiriType nigiriTypeRequested) {
		playerPlate.setNigirType(nigiriTypeRequested);
	}

	@Override
	public void handleSashimiTypeRequest(SashimiType sashimiTypeRequested) {
		playerPlate.setSashimiType(sashimiTypeRequested);
	}

	@Override
	public void handleNameRequest(String name) {
		playerPlate.setName(name);
	}

}
