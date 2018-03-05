import java.awt.*;

public class HouseTile extends SmallTile
{
    private static final double BASERENT = 0.2;
    private static final double OFFSET = 0.05;
    private int price, houses;
    private String name;
    private Color color;
    private String owner;
    private Color ownerColor;
    private int rent, rentOne, rentTwo, rentThree, rentFour, rentHotel, hotelPrice, housePrice;


    public HouseTile(int x, int y, int width, int height, int boxWidth, int boxHeight, int boxX, int boxY, int price,
		     Color color, String name, int textX, int textY)
    {
	super(x, y, width, height, boxWidth, boxHeight, boxX, boxY, name, textX, textY, TileType.HOUSE);
	this.price = price;
	this.color = color;
	this.name = name;
	owner = "";
	ownerColor = null;

	houses = 0;

	rent = (int) (price * BASERENT);
	rentOne = (int) (price * (BASERENT + OFFSET));
	rentTwo = (int) (price * (BASERENT + OFFSET * 2));
	rentThree = (int) (price * (BASERENT * 2));
	rentFour = (int) (price * (BASERENT * 3));
	rentHotel = (int) (price * (BASERENT * 4));

	housePrice = (int) (price * (1 + OFFSET * 5));
	hotelPrice = (int) (price * (1 + OFFSET * 10));

    }

    public String getOwner() {
	return owner;
    }

    public void setOwner(String name, Color c) {
	owner = name;
	ownerColor = c;
    }

    public int getPrice() {
	return price;
    }

    public Color getColor() {
	return color;
    }

    public double currentRent() {
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

    public void buyTile(Player player) {
	if (player.canBuyTile(this)) {
	    player.setPlayerMoney(-this.price);
	    player.getOwnedTiles().add(this);
	}
    }

    public double getHousePrice() {
	if (houses <= 3) {
	    return housePrice;
	} else if (houses < 5) {
	    return hotelPrice;
	}
	return 0;
    }

    public double buyHouse() {
	if (!owner.isEmpty() && ownerColor != null) {
	    if (houses < 5) {
		houses++;
		return getHousePrice();
	    }
	}
	return 0;
    }

    public Color getOwnerColor() {
	return ownerColor;
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

	if (!owner.isEmpty()) {
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

    @Override public void landAction(Player p) {
	if (!owner.isEmpty()) {
	    if (p.getColor().equals(ownerColor)) {
		p.loseMoney((int) currentRent());
	    }
	}
    }
}
