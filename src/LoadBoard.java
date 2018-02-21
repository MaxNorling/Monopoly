import java.awt.*;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Loads the information on tiles from a csv file
 */
public class LoadBoard
{

    private ArrayList<HouseTile> boardTiles;
    private TileMaker tileMaker;
    private Board board;
    private int tileX;
    private int tileY;

    public LoadBoard(Board board) {
	this.boardTiles = new ArrayList<>();
	this.board = board;
	this.tileX = 0;
	this.tileY = 0;
    }

    public void readTileInformation() {
	List<String> tileInfo = new ArrayList<>();

	Scanner scanner = null;

	try {
	    System.out.println();
	    scanner = new Scanner(new File((System.getProperty("user.dir") + "/src/tiles.csv").trim()));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	scanner.useDelimiter(",");

	while (scanner.hasNext()) {
	    String val = scanner.next();

	    if (val.equals("-")) {
		String streetName = "";
		String price = "";
		String color = "";

		for (String info : tileInfo) {
		    if (streetName.equals("")) {
			streetName = info;
		    } else if (price.equals("")) {
			price = info;
		    } else {
			color = info;
		    }
		}

		switch (color) {
		    case "Blue":
			createTopTile(streetName, Integer.parseInt(price), Color.getColor(color));
			break;
		    case "Red":
			createBottomTile(streetName, Integer.parseInt(price), Color.getColor(color));
			break;
		    case "Orange":
			createLeftTile(streetName, Integer.parseInt(price), Color.getColor(color));
			break;
		    case "Pink":
			createRightTile(streetName, Integer.parseInt(price), Color.getColor(color));
			break;
		    default:
			throw new IllegalArgumentException("Bad color.");
		}

		tileInfo = new ArrayList<>();

	    } else {
		tileInfo.add(val);
	    }
	}
	scanner.close();
    }

    public HouseTile getLastAddedTile() {
	if (!boardTiles.isEmpty()) {
	    return boardTiles.get(boardTiles.size() - 1);
	}

	return null;
    }

    public void createBottomTile(String streetName, int price, Color color) {
	if (!getLastAddedTile().getColor().equals(color)) {
	    tileX = 0;
	}

	boardTiles.add(tileMaker.makeBottomHouseTile(board.getTileSize(), tileX, 5, Color.RED, price, streetName));
	tileX += 1;
    }

    public void createRightTile(String streetName, int price, Color color) {
	if (getLastAddedTile() != null && !getLastAddedTile().getColor().equals(color)) {
	    tileY = 0;
	}

	boardTiles.add(tileMaker.makeRightHouseTile(board.getTileSize(), 5, tileY, Color.PINK, price, streetName));
	tileY += 1;
    }

    public void createLeftTile(String streetName, int price, Color color) {
	if (!getLastAddedTile().getColor().equals(color)) {
	    tileY = 0;
	}

	boardTiles.add(tileMaker.makeLeftHouseTile(board.getTileSize(), 0, tileY, Color.ORANGE, price, streetName));
	tileY += 1;
    }

    public void createTopTile(String streetName, int price, Color color) {
	if (getLastAddedTile() != null && !getLastAddedTile().getColor().equals(color)) {
	    tileX = 0;
	}

	boardTiles.add(tileMaker.makeTopHouseTile(board.getTileSize(), tileX, 0, Color.BLUE, price, streetName));
	tileX += 1;
    }

    public static void main(String... args) {
        LoadBoard loadBoard = new LoadBoard(new Board(10));
        loadBoard.readTileInformation();
        System.out.println(loadBoard.boardTiles);
    }
}
