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
    private ArrayList<Card> chanceCards;
    private ArrayList<Card> communityCards;
    private TileMaker tileMaker;
    private Board board;
    private int tileX;
    private int tileY;

    public LoadBoard(Board board) {
	this.boardTiles = new ArrayList<>();
	this.chanceCards = new ArrayList<>();
	readTileInformation("chance.csv");


	this.communityCards = new ArrayList<>();
	this.tileMaker = new TileMaker();
	this.board = board;
	this.tileX = 0;
	this.tileY = 0;
    }

    public void readTileInformation(String fileName) {
        File file = new File(fileName);
	List<String> tileInfo = new ArrayList<>();

	Scanner scanner = null;

	try {
	    scanner = new Scanner(new File((System.getProperty("user.dir") + "/src/" + file).trim()));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	scanner.useDelimiter(",");

	while (scanner.hasNext()) {
	    String val = scanner.next();
	    if (file.getPath().equals("tiles.csv")) {
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
	    } else if (file.getPath().equals("chance.csv") || file.getPath().equals("community.csv")) {
		if (val.equals("-")) {
		    String chanceText = tileInfo.get(0);
		    String chanceId = tileInfo.get(1).trim();
		    String getOutOfJail = "";
		    String goToJail = "";
		    int amountId = 0;
		    int travelTiles = 0;

		    if (chanceId.equals("loseMoney") || chanceId.equals("addMoney")) {
			amountId = Integer.parseInt(tileInfo.get(2).trim());
		    } else if (chanceId.equals("travelTiles")) {
			travelTiles = Integer.parseInt(tileInfo.get(2).trim());
		    } else if (chanceId.equals("getOutOfJail")) {
			getOutOfJail = tileInfo.get(2).trim();
		    } else if (chanceId.equals("goToJail")) {
			goToJail = tileInfo.get(2).trim();
		    }

		    switch (chanceId) {
			case "loseMoney":
			    playerLoseMoney(chanceText, amountId);
			    break;
			case "addMoney":
			    playerAddMoney(chanceText, amountId);
			    break;
			case "travelTiles":
			    playerTravelTiles(chanceText, travelTiles);
			    break;
			case "getOutOfJail":
			    playerGetOutOfJail(chanceText);
			    break;
			case "goToJail":
			    playerGoToJail(chanceText);
			    break;
			default:
			    throw new IllegalArgumentException("Invalid Card: " + chanceId);
		    }

		    tileInfo = new ArrayList<>();

		} else {
		    tileInfo.add(val);
		}
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
	if (getLastAddedTile() != null && !getLastAddedTile().getColor().equals(color)) {
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
	if (getLastAddedTile() != null && !getLastAddedTile().getColor().equals(color)) {
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

    public void playerLoseMoney(String chanceText, int amount) {
        Special special = new Special();
        Card card = new Card(chanceText, "playerLoseMoney", amount, special);

        chanceCards.add(card);
        //board.getCurrentPlayer().setPlayerMoney(-amount);
    }

    public void playerAddMoney(String chanceText, int amount) {
	Special special = new Special();
 	Card card = new Card(chanceText, "playerLoseMoney", amount, special);
 	chanceCards.add(card);
	//board.getCurrentPlayer().setPlayerMoney(amount);
    }

    public void playerTravelTiles(String chanceText, int travelTiles) {
        Special special = new Special();
        Card card = new Card(chanceText, "playerTravelTiles", travelTiles, special);
        chanceCards.add(card);

        //board.getCurrentPlayer().move(travelTiles);
    }

    public void playerGoToJail(String chanceText) {
	Special special = new Special();
	Card card = new Card(chanceText, "goToJail", 0, special);
 	chanceCards.add(card);

        //board.getCurrentPlayer().goToJail();
    }

    public void playerGetOutOfJail(String chanceText) {
        Special special = new Special();
        Card card = new Card(chanceText, "getOutOfJail", 0, special);
        chanceCards.add(card);
    }

    public ArrayList<Card> getChanceCards() {
        return this.chanceCards;
    }

    public ArrayList<Card> getCommunityCard() {
        return this.communityCards;
    }

    public ArrayList<HouseTile> getBoardTiles() {
        return this.boardTiles;
    }

    public static void main(String... args) {
	LoadBoard loadBoard = new LoadBoard(new Board(10));
	loadBoard.readTileInformation("chance.csv");
	System.out.println(loadBoard.boardTiles);
    }
}
