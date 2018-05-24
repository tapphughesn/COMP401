package comp401.sushi;

import comp401.sushi.Plate.SushiType;

public class Sashimi implements Sushi {

	public enum SashimiType {TUNA, SALMON, EEL, CRAB, SHRIMP}
	
	private SashimiType type;
	private static double SASHIMI_PORTION_AMOUNT = 0.75;
	
	private IngredientPortion seafood;
	
	public Sashimi(SashimiType type) {
		this.type = type;
		switch(type) {
		case TUNA:
			seafood = new TunaPortion(SASHIMI_PORTION_AMOUNT);
			break;
		case SALMON:
			seafood = new SalmonPortion(SASHIMI_PORTION_AMOUNT);
			break;
		case EEL:
			seafood = new EelPortion(SASHIMI_PORTION_AMOUNT);
			break;
		case CRAB:
			seafood = new CrabPortion(SASHIMI_PORTION_AMOUNT);
			type = SashimiType.TUNA;
			break;
		case SHRIMP:
			seafood = new ShrimpPortion(SASHIMI_PORTION_AMOUNT);
			break;			
		}
	}
	
	@Override
	public String getName() {
		return seafood.getName() + " sashimi";
	}
	
	public SashimiType getType() {
		return type;
	}

	@Override
	public IngredientPortion[] getIngredients() {
		return new IngredientPortion[] {seafood};
	}

	@Override
	public int getCalories() {
		return (int) (seafood.getCalories() + 0.5);
	}

	@Override
	public double getCost() {
		return ((int) (seafood.getCost() * 100.0 + 0.5)) / 100.0;
	}

	@Override
	public boolean getHasRice() {
		return false;
	}

	@Override
	public boolean getHasShellfish() {
		return seafood.getIsShellfish();
	}

	@Override
	public boolean getIsVegetarian() {
		return false;
	}

	@Override
	public SushiType getSushiType() {
		return Plate.SushiType.SASHIMI;
	}

}
