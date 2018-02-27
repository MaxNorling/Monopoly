import java.awt.image.BufferedImage;

public class CornerTile extends Tile
{
    private BufferedImage image;
    private String description;

    public CornerTile(int x, int y, int width, int height,BufferedImage img,String description){
	super(x,y,width,height,TileType.CORNER);
	this.image = img;
	this.description = description;
    }


    public BufferedImage getImage(){
        return image;
    }


    @Override public String toString() {
	return description;
    }

    @Override public void landAction(Player p) {

    }
}
