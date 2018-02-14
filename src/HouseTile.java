import java.awt.*;

public class HouseTile implements Tile
{
    private int x, y, width, height ,price;
    private Color color;
    private String name;



    public HouseTile(int x, int y, int width, int height, int price, Color color, String name){
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.price = price;
	this.color = color;
	this.name = name;

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
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y,width, height);
	g2d.setColor(color);
	g2d.fillRect(x,y,width / 8,height);
	if(width > height){
	    g2d.fillRect(x,y,width,height / 8);
	}

	g2d.setFont(new Font("Arial",Font.BOLD,28));

	g2d.drawString("$"+price,x + width/2,y + height/2);
	g2d.drawString(name,x + width/2, y+ height);


    }
}
