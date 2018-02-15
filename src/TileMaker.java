import java.awt.*;

public class TileMaker
{
    private int squareSize, x, y, price;
    private Color color;
    private String name;

    public TileMaker() {


    }

    //TODO write cleaner code D:
    public HouseTile makeBottomTile(int squareSize, int x, int y, Color color, int price, String name) {
	//public HouseTile(int x, int y, int height,int width,int boxWidth, int boxHeight,int boxX, int boxY, int price, Color color, String name) {

	int width = squareSize;
	int height = squareSize * 2;

	x = x * width;
	y = y * height + height / 2;

	int boxX = x;
	int boxY = y;

	int boxHeight = height / 4;
	int boxWidth = width;

	int textX = x;
	int textY = y + (int)(height*0.6);

	return new HouseTile(x, y, height, width, boxWidth, boxHeight, boxX, boxY, price, color, name,textX,textY);
    }

    public HouseTile makeTopTile(int squareSize, int x, int y, Color color, int price, String name) {

	int width = squareSize;
	int height = squareSize * 2;

	x = x * width;
	y = y * height;


	int boxHeight = height / 4;
	int boxWidth = width;

	int boxX = x;
	int boxY = height - boxHeight;


	int textX = x;
	int textY = y + (int)(height*0.4);

	return new HouseTile(x, y, height, width, boxWidth, boxHeight, boxX, boxY, price, color, name,textX,textY);

    }

    public HouseTile makeLeftTile(int squareSize, int x, int y, Color color, int price, String name) {

	int width = squareSize * 2;
	int height = squareSize;

	x = x * width;
	y = y * height;


	int boxHeight = height;
	int boxWidth = width / 4;

	int boxX = width - boxWidth;
	int boxY = y;

	int textX = x + width/8;
	int textY = y + (int)(height*0.6);


	return new HouseTile(x, y, height, width, boxWidth, boxHeight, boxX, boxY, price, color, name,textX,textY);

    }

    public HouseTile makeRightTile(int squareSize, int x, int y, Color color, int price, String name) {

	int width = squareSize * 2;
	int height = squareSize;

	x = x * width + width / 2;
	y = y * height;


	int boxHeight = height;
	int boxWidth = width / 4;

	int boxX = x;
	int boxY = y;

	int textX = x + width/3;
	int textY = y + (int)(height*0.6);


	return new HouseTile(x, y, height, width, boxWidth, boxHeight, boxX, boxY, price, color, name,textX,textY);

    }
}
