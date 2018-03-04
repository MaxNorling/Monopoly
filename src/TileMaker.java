import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileMaker
{

    public TileMaker() {

    }


    public CornerTile makeCornerTile(int squareSize, int x, int y, BufferedImage img, String description) {
	int size = squareSize * 2;

	return new CornerTile(x * squareSize - size, y * squareSize - size, size, size, img,description);
    }

    private int[] getBottomDimensions(int squareSize, int x, int y) {

	int[] res = new int[10];

	int height = squareSize * 2;

	x *= squareSize;
	y = y * height + height / 2;

	int boxX = x;
	int boxY = y;

	int boxHeight = height / 4;

	int textX = x;
	int textY = y + (int) (height * 0.6);

	res[0] = squareSize;
	res[1] = height;

	res[2] = x;
	res[3] = y;

	res[4] = boxX;
	res[5] = boxY;

	res[6] = boxHeight;
	res[7] = squareSize;

	res[8] = textX;
	res[9] = textY;

	return res;
    }

    private int[] getTopDimensions(int squareSize, int x, int y) {

	int[] res = new int[10];

	int height = squareSize * 2;

	x *= squareSize;
	y *= height;


	int boxHeight = height / 4;

	int boxX = x;
	int boxY = height - boxHeight;


	int textX = x;
	int textY = y + (int) (height * 0.4);

	res[0] = squareSize;
	res[1] = height;

	res[2] = x;
	res[3] = y;

	res[4] = boxX;
	res[5] = boxY;

	res[6] = boxHeight;
	res[7] = squareSize;

	res[8] = textX;
	res[9] = textY;

	return res;
    }

    private int[] getLeftDimensions(int squareSize, int x, int y) {

   	int[] res = new int[10];

	int width = squareSize * 2;

	x *= width;
	y *= squareSize;


	int boxWidth = width / 4;

	int boxX = width - boxWidth;
	int boxY = y;

	int textX = x + width / 8;
	int textY = y + (int) (squareSize * 0.6);

   	res[0] = width;
   	res[1] = squareSize;

   	res[2] = x;
   	res[3] = y;

   	res[4] = boxX;
   	res[5] = boxY;

   	res[6] = squareSize;
   	res[7] = boxWidth;

   	res[8] = textX;
   	res[9] = textY;

   	return res;
       }

    private int[] getRightDimensions(int squareSize, int x, int y) {

   	int[] res = new int[10];

	int width = squareSize * 2;

	x = x * width + width / 2;
	y *= squareSize;


	int boxWidth = width / 4;

	int boxX = x;
	int boxY = y;

	int textX = x + width / 3;
	int textY = y + (int) (squareSize * 0.6);

   	res[0] = width;
   	res[1] = squareSize;

   	res[2] = x;
   	res[3] = y;

   	res[4] = boxX;
   	res[5] = boxY;

   	res[6] = squareSize;
   	res[7] = boxWidth;

   	res[8] = textX;
   	res[9] = textY;

   	return res;
       }

    public ChanceTile makeBottomChanceTile(int squareSize, int x, int y, BufferedImage image, String name, ArrayList<Card> chanceCards) {
	//public HouseTile(int x, int y, int height,int width,int boxWidth, int boxHeight,int boxX, int boxY, int price, Color color, String name) {
	int[] dimensions = getBottomDimensions(squareSize, x, y);

	return new ChanceTile(dimensions[2], dimensions[3], dimensions[0], dimensions[1], dimensions[7], dimensions[6] * 3,
				     dimensions[4], dimensions[5], image, name, dimensions[8], dimensions[9], chanceCards);
    }

    public ChanceTile makeTopChanceTile(int squareSize, int x, int y, BufferedImage image, String name, ArrayList<Card> chanceCards) {
   	//public HouseTile(int x, int y, int height,int width,int boxWidth, int boxHeight,int boxX, int boxY, int price, Color color, String name) {
   	int[] dimensions = getTopDimensions(squareSize, x, y);

	return new ChanceTile(dimensions[2], dimensions[3], dimensions[0], dimensions[1], dimensions[7], dimensions[6] * 3,
			     dimensions[4], dimensions[5] - dimensions[6] * 3, image, name, dimensions[8], dimensions[9], chanceCards);
       }

    public ChanceTile makeLeftChanceTile(int squareSize, int x, int y, BufferedImage image, String name, ArrayList<Card> chanceCards) {
   	//public HouseTile(int x, int y, int height,int width,int boxWidth, int boxHeight,int boxX, int boxY, int price, Color color, String name) {
   	int[] dimensions = getLeftDimensions(squareSize, x, y);

	return new ChanceTile(dimensions[2], dimensions[3], dimensions[0], dimensions[1], dimensions[7] * 3, dimensions[6],
				     dimensions[4] - dimensions[7] * 3, dimensions[5], image, name, dimensions[8], dimensions[9], chanceCards);
       }

    public ChanceTile makeRightChanceTile(int squareSize, int x, int y, BufferedImage image, String name, ArrayList<Card> chanceCards) {
   	//public HouseTile(int x, int y, int height,int width,int boxWidth, int boxHeight,int boxX, int boxY, int price, Color color, String name) {
   	int[] dimensions = getRightDimensions(squareSize, x, y);

	return new ChanceTile(dimensions[2], dimensions[3], dimensions[0], dimensions[1], dimensions[7] * 3, dimensions[6],
				     dimensions[4], dimensions[5], image, name, dimensions[8], dimensions[9], chanceCards);
       }



    public HouseTile makeBottomHouseTile(int squareSize, int x, int y, Color color, int price, String name) {

	int[] dimensions = getBottomDimensions(squareSize, x, y);

	return new HouseTile(dimensions[2], dimensions[3], dimensions[0], dimensions[1], dimensions[7], dimensions[6],
			     dimensions[4], dimensions[5], price, color, name, dimensions[8], dimensions[9]);
    }

    public HouseTile makeTopHouseTile(int squareSize, int x, int y, Color color, int price, String name) {
	int[] dimensions = getTopDimensions(squareSize, x, y);

	return new HouseTile(dimensions[2], dimensions[3], dimensions[0], dimensions[1], dimensions[7], dimensions[6],
			     dimensions[4], dimensions[5], price, color, name, dimensions[8], dimensions[9]);
    }

    public HouseTile makeLeftHouseTile(int squareSize, int x, int y, Color color, int price, String name) {

	int[] dimensions = getLeftDimensions(squareSize, x, y);


	return new HouseTile(dimensions[2], dimensions[3], dimensions[0], dimensions[1], dimensions[7], dimensions[6],
			     dimensions[4], dimensions[5], price, color, name, dimensions[8], dimensions[9]);

    }

    public HouseTile makeRightHouseTile(int squareSize, int x, int y, Color color, int price, String name) {

	int[] dimensions = getRightDimensions(squareSize, x, y);

	return new HouseTile(dimensions[2], dimensions[3], dimensions[0], dimensions[1], dimensions[7], dimensions[6],
			     dimensions[4], dimensions[5], price, color, name, dimensions[8], dimensions[9]);

    }
}
