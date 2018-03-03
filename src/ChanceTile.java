import java.awt.image.BufferedImage;

public class ChanceTile extends SmallTile
{

    private BufferedImage image;

    public ChanceTile(int x, int y, int width, int height, int boxWidth, int boxHeight, int boxX, int boxY, BufferedImage image, String name , int textX, int textY) {
	super(x,y,width,height,boxWidth,boxHeight,boxX,boxY,name,textX,textY,TileType.CHANCE);
	this.image = image;

    }

    public BufferedImage getImage() {
	return image;
    }


    @Override public String toString() {
	return "Gives the player a random card";
    }

    @Override public void landAction(final Player p) {

    }
}
