package sushigame.model;

import comp401.sushi.*;
import comp401.sushi.Nigiri.NigiriType;
import comp401.sushi.Sashimi.SashimiType;

//Encapsulates Information needed for the player to construct a plate, changes based on PlayerChefView Information
public class PlayerPlateComponents {

	private Plate.Color color = null;
	private Plate.SushiType type = null;
	private Sashimi.SashimiType sashimiType = null;
	private Nigiri.NigiriType nigiriType = null;
	private double price;
	private String name;
	private int position;
	
	private AvocadoPortion avocado = null;
	private CrabPortion crab = null;
	private EelPortion eel = null;
	private RicePortion rice = null;
	private SalmonPortion salmon = null;
	private SeaweedPortion seaweed = null;
	private ShrimpPortion shrimp = null;
	private TunaPortion tuna = null;
	
	private IngredientPortion[] ingredients;
	
	public PlayerPlateComponents() {
		color = Plate.Color.RED;
		type = Plate.SushiType.ROLL;
		sashimiType = Sashimi.SashimiType.CRAB;
		nigiriType = Nigiri.NigiriType.CRAB;
		price = 1.0;
		name = "unnamed";
		position = 0;
		avocado = new AvocadoPortion(0.0);
		crab = new CrabPortion(0.0);
		eel = new EelPortion(0.0); 
		rice = new RicePortion(0.0);
		salmon = new SalmonPortion(0.0);
		seaweed = new SeaweedPortion(0.0);
		shrimp = new ShrimpPortion(0.0);
		tuna = new TunaPortion(0.0);
	}
	
	private int nonZeroIngredients() {
		int sum = 0;
		
		if (avocado.getAmount() != 0.0) {
			sum++;
		}
		if (crab.getAmount() != 0.0) {
			sum++;
		}
		if (eel.getAmount() != 0.0) {
			sum++;
		}
		if (rice.getAmount() != 0.0) {
			sum++;
		}
		if (salmon.getAmount() != 0.0) {
			sum++;
		}
		if (seaweed.getAmount() != 0.0) {
			sum++;
		}
		if (shrimp.getAmount() != 0.0) {
			sum++;
		}
		if (tuna.getAmount() != 0.0) {
			sum++;
		}
		return sum;
	}
	
	//Getters
	
	public Plate.Color getPlateColor() {
		return color;
	}
	public Plate.SushiType getPlateFoodType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getPosition() {
		return position;
	}
	public AvocadoPortion getAvocadoPortion() {
		return avocado;
	}
	public CrabPortion getCrabPortion() {
		return crab;
	}
	public EelPortion getEelPortion() {
		return eel;
	}
	public RicePortion getRicePortion() {
		return rice;
	}
	public SalmonPortion getSalmonPortion() {
		return salmon;
	}
	public SeaweedPortion getSeaweedPortion() {
		return seaweed;
	}
	public ShrimpPortion getShrimpPortion() {
		return shrimp;
	}
	public TunaPortion getTunaPortion() {
		return tuna;
	}
	
	// Setters
	public void setPlateColor(Plate.Color a) {
		color = a;
		switch (a){
		case RED: setPrice(1.0); break;
		case GREEN: setPrice(2.0); break;
		case BLUE: setPrice(4.0); break;
		case GOLD:setPrice(5.0); break;
		}
	}
	public void setPlateFoodType(Plate.SushiType a) {
		type = a;
		switch(a) {
		case ROLL: break;
		case SASHIMI: setName("Sashimi"); break;
		case NIGIRI: setName("Nigiri"); break;
		}
	}
	public void setSashimiType(SashimiType a) {
		this.sashimiType = a;
	}
	public void setNigirType(NigiriType a) {
		this.nigiriType = a;
	}
	public void setName(String a) {
		if (getPlateFoodType() == Plate.SushiType.ROLL) {
			name = a;
		}
	}
	public void setPosition(int a) {
		position = a;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setAvocado(double a) {
		avocado = new AvocadoPortion(a);
	}
	public void setCrab(double a) {
		crab = new CrabPortion(a);
	}
	public void setEel(double a) {
		eel = new EelPortion(a);
	}
	public void setRice(double a) {
		rice = new RicePortion(a);
	}
	public void setSalmon(double a) {
		salmon = new SalmonPortion(a);
	}
	public void setSeaweed(double a) {
		seaweed = new SeaweedPortion(a);
	}
	public void setShrimp(double a) {
		shrimp = new ShrimpPortion(a);
	}
	public void setTuna(double a) {
		tuna = new TunaPortion(a);
	}
	
	
	//return IngredientPortion[] with no null ingredients
	public IngredientPortion[] getIngredientPortions() {
		int a = nonZeroIngredients();
		IngredientPortion[] allIngs = new IngredientPortion[]{avocado, crab, eel, rice, salmon, seaweed, shrimp, tuna};
		ingredients = new IngredientPortion[a];
		int zeros = 0;
		for (int i = 0; i < 8; i++) {
			if (allIngs[i].getAmount() == 0.0) {
				zeros ++;
			} else {
				ingredients[i - zeros] = allIngs[i];
			}
		}
		return ingredients;
	}
	
	//return sushi with these components
	public Sushi getSushi() {
		
		switch (getPlateFoodType()) {
		case ROLL: 
			return new Roll(getName(), getIngredientPortions());
		case SASHIMI: return new Sashimi(sashimiType);
		case NIGIRI: return new Nigiri(nigiriType);
		default: return null;
		}
	}
	
}
