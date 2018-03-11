package tiles;

/**
 * Used to simplify the making of tiles of the same size but different kind.
 */
public class Dimensions
{
    private int x;
    private int y;
    private int width;
    private int height;


    private int iconWidth;
    private int iconHeight;
    private int iconX;
    private int iconY;
    private int textX;
    private int textY;

    public Dimensions(int x, int y, int width, int height, int iconWidth, int iconHeight, int iconX, int iconY, int textX,
		      int textY)
    {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.iconWidth = iconWidth;
	this.iconHeight = iconHeight;
	this.iconX = iconX;
	this.iconY = iconY;
	this.textX = textX;
	this.textY = textY;
    }

    public int getIconWidth() {
	return iconWidth;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public int getIconHeight() {
	return iconHeight;
    }

    public int getIconX() {
	return iconX;
    }

    public int getIconY() {
	return iconY;
    }

    public int getTextX() {
	return textX;
    }

    public int getTextY() {
	return textY;
    }
}
