package tiles;

import gamelogic.Card;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Creates the specified tile type and makes it fit
 * to the specified position.
 */
public class TileMaker
{
    private final static double POSITION_OFFSET_SIDES = 0.6;
    private final static double POSITION_OFFSET_TOP_BOTTOM = 0.4;
    private final static double END_OF_SCREEN_OFFSET = 2.2;
    private static final double STANDARD_BOX_OFFSET = 0.25;
    private static final double STANDARD_TEXT_OFFSET = 3;

    public TileMaker() {

    }


    public CornerTile makeCornerTile(int squareSize, int x, int y, BufferedImage img, String name, String description) {
	int size = squareSize * 2;

	return new CornerTile(x * squareSize - size, y * squareSize - size, size, size, img, name, description);
    }

    private Dimensions getBottomDimensions(int squareSize, int x, int y, TileType type) {
//	int[] res = new int[10];
//
//	int height = squareSize * 2;
//
//	x *= squareSize;
//	y = y * height + height / 2;
//
//	int boxX = x;
//	int boxY = y;
//
//	int boxHeight = height / 4;
//
//	int textX = x;
//	int textY = y + (int) (height * POSITION_OFFSET_SIDES);
//
//	res[0] = squareSize;
//	res[1] = height;
//
//	res[2] = x;
//	res[3] = y;
//
//	res[4] = boxX;
//	res[5] = boxY;
//
//	res[6] = boxHeight;
//	res[7] = squareSize;
//
//	res[8] = textX;
//	res[9] = textY;

	double boxHeightOffset = 1;

	if (type.equals(TileType.HOUSE)) {
	    boxHeightOffset = STANDARD_BOX_OFFSET;
	}

	Dimensions dim =
		createDimensions(squareSize, x, y, 1, 2, squareSize, squareSize * END_OF_SCREEN_OFFSET, 1, boxHeightOffset, 1,
				 1, 10, (int) (STANDARD_TEXT_OFFSET * squareSize * POSITION_OFFSET_TOP_BOTTOM));

	return dim;

    }

    private Dimensions getTopDimensions(int squareSize, int x, int y, TileType type) {

	double boxHeightOffset = 1;

	if (type.equals(TileType.HOUSE)) {
	    boxHeightOffset = STANDARD_BOX_OFFSET;
	}

	Dimensions dim = createDimensions(squareSize, x, y, 1, 2, squareSize, 1, 1, boxHeightOffset, 1, 1, 10,
					  (int) (STANDARD_TEXT_OFFSET * squareSize * POSITION_OFFSET_TOP_BOTTOM));

	return dim;
    }

    private Dimensions getLeftDimensions(int squareSize, int x, int y, TileType type) {

	int width = squareSize * 2;
	double boxWidthOffset = 1;

	if (type.equals(TileType.HOUSE)) {
	    boxWidthOffset = STANDARD_BOX_OFFSET;
	}

	Dimensions dim =
		createDimensions(squareSize, x, y, 2, 1, 1, squareSize, boxWidthOffset, 1, 1, 1, width / STANDARD_TEXT_OFFSET,
				 (int) (squareSize * POSITION_OFFSET_SIDES));

	return dim;
    }

    private Dimensions getRightDimensions(int squareSize, int x, int y, TileType type) {

	int width = squareSize * 2;
	double boxWidthOffset = 1;

	if (type.equals(TileType.HOUSE)) {
	    boxWidthOffset = STANDARD_BOX_OFFSET;
	}

	Dimensions dim =
		createDimensions(squareSize, x, y, 2, 1, squareSize * END_OF_SCREEN_OFFSET, squareSize, boxWidthOffset, 1, 1, 1,
				 width / STANDARD_TEXT_OFFSET, (int) (squareSize * POSITION_OFFSET_SIDES));

	return dim;
    }

    private Dimensions createDimensions(int squareSize, int x, int y, double widthOffSet, double heightOffset, double xOffset,
					double yOffset, double boxWidthOffset, double boxHeightOffset, double boxXOffset,
					double boxYOffset, double textXOffset, double textYOffset)
    {

	int width = (int) (squareSize * widthOffSet);
	int height = (int) (squareSize * heightOffset);

	x *= (int) xOffset;
	y *= (int) yOffset;


	int boxWidth = (int) (width * boxWidthOffset);
	int boxHeight = (int) (height * boxHeightOffset);

	int boxX = (int) (x * boxXOffset);
	int boxY = (int) (y * boxYOffset);

	int textX = (int) (x + textXOffset);
	int textY = (int) (y + textYOffset);

	return new Dimensions(x, y, width, height, boxWidth, boxHeight, boxX, boxY, textX, textY);


    }

    private Dimensions getDim(int squareSize, int x, int y, Position pos, TileType type) {
	Dimensions dim = null;

	switch (pos) {
	    case UP:
		dim = getTopDimensions(squareSize, x, y, type);
		break;
	    case DOWN:
		dim = getBottomDimensions(squareSize, x, y, type);
		break;
	    case LEFT:
		dim = getLeftDimensions(squareSize, x, y, type);
		break;
	    case RIGHT:
		dim = getRightDimensions(squareSize, x, y, type);
		break;
	}

	return dim;
    }

    public ChanceTile makeChanceTile(int squareSize, int x, int y, BufferedImage image, String name,
				     ArrayList<Card> chanceCards, Position pos)
    {

	Dimensions dim = getDim(squareSize, x, y, pos, TileType.CHANCE);

	return new ChanceTile(dim.getX(), dim.getY(), dim.getWidth(), dim.getHeight(), dim.getIconWidth(), dim.getIconHeight(),
			      dim.getIconX(), dim.getIconY(), image, name, dim.getTextX(), dim.getTextY(), chanceCards);
    }


    public HouseTile makeHouseTile(int squareSize, int x, int y, Color color, int price, String name, Position pos) {

	Dimensions dim = getDim(squareSize, x, y, pos, TileType.HOUSE);

	return new HouseTile(dim.getX(), dim.getY(), dim.getWidth(), dim.getHeight(), dim.getIconWidth(), dim.getIconHeight(),
			     dim.getIconX(), dim.getIconY(), price, color, name, dim.getTextX(), dim.getTextY());

    }
}
