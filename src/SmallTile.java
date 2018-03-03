public abstract class SmallTile extends Tile
{
    private int boxWidth,boxHeight,boxX,boxY,textX,textY;
    private String text;

    protected SmallTile(int x, int y, int width, int height, int boxWidth, int boxHeight, int boxX, int boxY, String text , int textX, int textY,TileType type) {
	super(x,y,width,height,type);

  	this.text = text;

  	this.boxWidth = boxWidth;  // Height and width of rectangle or image
  	this.boxHeight = boxHeight;
  	this.boxX = boxX; // Rectangle or image x and y
  	this.boxY = boxY;

  	this.textX = textX;
  	this.textY = textY;

      }

    public int getBoxWidth() {
	return boxWidth;
    }

    public int getBoxHeight() {
	return boxHeight;
    }

    public int getBoxX() {
	return boxX;
    }

    public int getBoxY() {
	return boxY;
    }

    public int getTextX() {
	return textX;
    }

    public int getTextY() {
	return textY;
    }

    public String getText() {
	return text;
    }

}
