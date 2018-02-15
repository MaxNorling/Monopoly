import java.awt.*;
import java.awt.geom.AffineTransform;

public class HouseTile implements Tile
{
    private int x, y,tileSize, price,width,height,boxWidth,boxHeight,boxX,boxY, textX, textY;
    private Color color;
    private String name;


    public HouseTile(int x, int y, int height,int width,int boxWidth, int boxHeight,int boxX, int boxY, int price, Color color, String name , int textX, int textY) {
	this.x = x;
	this.y = y;

	this.tileSize = tileSize;
	this.price = price;
	this.color = color;
	this.name = name;

	this.width = width;
	this.height = height;// * 2;

	this.boxWidth = boxWidth;
	this.boxHeight = boxHeight;
	this.boxX = boxX;
	this.boxY = boxY;

	this.textX = textX;
	this.textY = textY;

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

    public int getPrice() {
	return price;
    }

    public Color getColor() {
	return color;
    }

    public String getName() {
	return name;
    }

    @Override public void landAction() {

    }

    @Override public void paint(final Graphics2D g2d) {
        // ROTATION STUFF
	//AffineTransform transform = new AffineTransform();
	//transform.rotate(Math.toRadians(270), x * width/2, y * height/2);
	//AffineTransform old = g2d.getTransform();
	//g2d.transform(transform);


	g2d.setColor(Color.BLACK);
	g2d.drawRect(x,y, width, height);


	//COLORED SQUARE
	g2d.setColor(color);
	g2d.fillRect(boxX, boxY, boxWidth,boxHeight);
	g2d.setColor(Color.BLACK);
	g2d.drawRect(boxX, boxY, boxWidth,boxHeight);


	g2d.setFont(new Font("Serif", Font.BOLD, 15));
	g2d.drawString("$" + price, textX,textY);

	g2d.setFont(new Font("Serif", Font.PLAIN, 12));
	g2d.drawString(name, textX,textY - 15 - 12);


	//g2d.setTransform(old); // RESET ROTATION


    }
}
