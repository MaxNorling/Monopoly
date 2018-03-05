import java.awt.image.BufferedImage;

public class CornerTile extends Tile
{
    private BufferedImage image;
    private String description;
    private String name;

    public CornerTile(int x, int y, int width, int height,BufferedImage img,String name,String description){
	super(x,y,width,height,TileType.CORNER);
	this.image = img;
	this.description = description;
	this.name = name;
    }


    public BufferedImage getImage(){
        return image;
    }


    @Override public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(name);
        out.append("\n \n");
        out.append(description);
	return out.toString();
    }

    @Override public String landAction(Player p) {
	if(name =="GO TO JAIL!"){
	    p.goToJail();
	    return "You got jailed";
	}
	return "";
    }
}
