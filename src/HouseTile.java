import java.awt.*;

public class HouseTile extends SmallTile
{
    private int price;
    private String name;
    private Color color;
    private String owner;
    private Color ownerColor;
    private int rent,rentOne,rentTwo,rentThree,rentFour,rentHotel;


    public HouseTile(int x, int y, int width,int height,int boxWidth, int boxHeight,int boxX, int boxY, int price, Color color, String name , int textX, int textY) {
	super(x,y,width,height,boxWidth,boxHeight,boxX,boxY,name,textX,textY,TileType.HOUSE);
	this.price = price;
	this.color = color;
	this.name = name;
	owner = "";
	ownerColor = null;

	rent = 20;
	rentOne = 130;
	rentTwo = 280;
	rentThree = 520;
	rentFour = 850;
	rentHotel = 1200;

    }

    public String getOwner(){
        return owner;
    }
    public void setOwner(String name, Color c){
        owner = name;
        ownerColor  = c;
    }
    public int getPrice() {
	return price;
    }

    public Color getColor() {
	return color;
    }
    public int currentRent(){
        return rent;
    }

    public String getName() { return name; }

    public void buyTile(Player player) {
        if (player.canBuyTile(this)) {
            player.setPlayerMoney(-this.price);
            player.getOwnedTiles().add(this);
	}
    }

    public Color getOwnerColor(){
        return ownerColor;
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

	if (owner != "") {
	    res.append("\n \t");
	    res.append("Owner:");
	    res.append(owner);
	}

	return res.toString();
    }

    @Override public void landAction(Player p) {
        if(owner != ""){
            if(p.getColor() != ownerColor)
                p.loseMoney(currentRent());
	}
    }
}
