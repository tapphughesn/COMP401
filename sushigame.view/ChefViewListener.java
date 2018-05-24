package sushigame.view;

import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri.NigiriType;
import comp401.sushi.Plate;
import comp401.sushi.Sashimi.SashimiType;
import comp401.sushi.Sushi;

public interface ChefViewListener {

	void handleTypeChangeRequest(comp401.sushi.Plate.SushiType t);
	void handleColorChangeRequest(Plate.Color c);
	void handleIngredientChangeRequest(IngredientPortion ip);
	void handleMakePlateRequest();
	void handlePriceChangeRequest(double price);
	void handlePositionChangeRequest(int pos);
	void handleNigiriTypeRequest(NigiriType nigiriTypeRequested);
	void handleSashimiTypeRequest(SashimiType sashimiTypeRequested);
	void handleNameRequest(String name);
}
