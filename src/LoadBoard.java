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
	this.tileMaker = new TileMaker();
	this.board = board;
	this.tileX = 0;
	this.tileY = 0;
    }

    public void readTileInformation() {
	List<String> tileInfo = new ArrayList<>();

	Scanner scanner = null;

	try {
	    scanner = new Scanner(new File((System.getProperty("user.dir") + "/src/tiles.csv").trim()));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	scanner.useDelimiter(",");

	while (scanner.hasNext()) {
	    String val = scanner.next();

	    if (val.equals("-")) {
		String streetName = tileInfo.get(0);
		int price = Integer.parseInt(tileInfo.get(1));
		String color = tileInfo.get(2);

		switch (color) {
		    case "BLUE":
			createTopTile(streetName, price, Color.BLUE);
			break;
		    case "RED":
			createBottomTile(streetName, price, Color.getColor(color));
			break;
		    case "ORANGE":
			createLeftTile(streetName, price, Color.getColor(color));
			break;
		    case "PINK":
			createRightTile(streetName, price, Color.getColor(color));
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
