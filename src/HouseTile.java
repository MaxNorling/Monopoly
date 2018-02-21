import java.awt.*;
import java.awt.geom.AffineTransform;

public class HouseTile extends SmallTile
{
    private int price;

    private Color color;
    private Player owner = null;


    public HouseTile(int x, int y, int width,int height,int boxWidth, int boxHeight,int boxX, int boxY, int price, Color color, String name , int textX, int textY) {
	super(x,y,width,height,boxWidth,boxHeight,boxX,boxY,name,textX,textY,TileType.HOUSE);
	this.price = price;
	this.color = color;

    }

    public Player getOwner(){
        return owner;
    }
    public int getPrice() {
	return price;
    }

    public Color getColor() {
	return color;
    }


    @Override public String toString() {
	return "            "+getText() + " : $" + price + "\n\n" + "            " +
	       "Rent 24 \n" +
	       "Rent 1 House : $120 \n" +
	       "Rent 2 House : $360 \n" +
	       "Rent 3 House : $850 \n" +
	       "        Hotel $1240" ;
    }
}
