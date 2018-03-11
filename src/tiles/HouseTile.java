package tiles;

import gamelogic.Player;

import java.awt.*;

/**
 * A small tile you can purchase and build houses on.
 * Extends SmallTile to get the methods in both SmallTile and SmallTiles super class Tile.
 */
public class HouseTile extends SmallTile
{
    private static final double BASERENT = 0.4;
    private static final double OFFSET = 0.25;
    private static final double SELL_VALUE = 0.7;
    private int price;
    private String name;
    private Color color;
    private Player owner;
    private int rent, rentOne, rentTwo, rentThree, rentFour, rentHotel, houses, housePrice, hotelPrice;


    public HouseTile(int x, int y, int width, int height, int boxWidth, int boxHeight, int boxX, int boxY, int price,
		     Color color, String name, int textX, int textY)
    {
	super(x, y, width, height, boxWidth, boxHeight, boxX, boxY, name, textX, textY, TileType.HOUSE);
	this.price = price;
	this.color = color;
	this.name = name;
	owner = null;

	houses = 0;

	rent = (int) (price * BASERENT);
	rentOne = (int) (price * (BASERENT + OFFSET));
	rentTwo = (int) (price * (BASERENT + OFFSET * 4));
	rentThree = (int) (price * (BASERENT * 6));
	rentFour = (int) (price * (BASERENT * 8));
	rentHotel = (int) (price * (BASERENT * 10));

	housePrice = (int) (price * (1 + OFFSET * 5));
	hotelPrice = (int) (price * (1 + OFFSET * 10));

    }

    public Player getOwner() {
	return owner;
    }

    public void setOwner(Player player) {
	owner = player;
    }

    public int getPrice() {
	return price;
    }

    public Color getColor() {
	return color;
    }

    public int currentRent() {
	switch (houses) {
	    case 1:
		return rentOne;
	    case 2:
		return rentTwo;
	    case 3:
		return rentThree;
	    case 4:
		return rentFour;
	    case 5:
		return rentHotel;

	    case 0:
	    default:
		return rent;
	}
    }

    public String getName() { return name; }

    public int getHousePrice() {
	if (houses <= 3) {
	    return housePrice;
	} else if (houses < 5) {
	    return hotelPrice;
	}
	return 0;
    }

    public int buyHouse() {
	if (owner != null) {
	    if (houses < 5) {
		houses++;
		return getHousePrice();
	    }
	}
	return 0;
    }

    public double getSellValue() {
	return price * SELL_VALUE; //TODO Change to somethihng less
    }

    public Color getOwnerColor() {
	return owner.getColor();
    }

    public void removeOwner() {
	owner = null;
    }

    public int getHouses() {
	return houses;
    }

    @Override public String toString() {
	StringBuilder res = new StringBuilder();
	res.append(getText());
	res.append("\t");
	res.append(" : $");
	res.append(price);
	res.append("\n\n");

	res.append("Rent : $");
	res.append(rent);
	res.append("\n");

	res.append("One house : $");
	res.append(rentOne);
	res.append("\n");

	res.append("Two houses : $");
	res.append(rentTwo);
	res.append("\n");

	res.append("Three houses : $");
	res.append(rentThree);
	res.append("\n");

	res.append("Four houses : $");
	res.append(rentFour);
	res.append("\n");

	res.append("Hotel : $");
	res.append(rentHotel);
	res.append("\n");

	if (owner != null) {
	    res.append("\n \t");
	    res.append("Owner:");
	    res.append(owner);
	    if (houses > 0 && houses <= 4) {
		res.append("\n");
		res.append(houses);
		res.append(" houses.");
	    } else if (houses > 4) {
		res.append("\n");
		res.append("Hotel");
	    }
	}

	return res.toString();
    }

    @Override public String landAction(Player p) {
	if (owner != null) {
	    if (!p.equals(owner)) {
		p.loseMoney(currentRent());
		return "You lost " + currentRent() + "$";
	    }
	}
	return "";
    }
}
