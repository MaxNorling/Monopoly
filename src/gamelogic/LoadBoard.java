package gamelogic;

import tiles.HouseTile;
import tiles.Position;
import tiles.TileMaker;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Loads the information on tiles from a csv file
 */
public class LoadBoard
{

    private List<HouseTile> boardTiles;
    private ArrayList<Card> chanceCards;
    private TileMaker tileMaker;
    private Board board;
    private int tileX;
    private int tileY;

    public LoadBoard(Board board) {
	this.boardTiles = new ArrayList<>();
	this.chanceCards = new ArrayList<>();
	readTileInformation("chance.csv");


	this.tileMaker = new TileMaker();
	this.board = board;
	this.tileX = 0;
	this.tileY = 0;
    }

    public static void main(String... args) {
	LoadBoard loadBoard = new LoadBoard(new Board(10));
	loadBoard.readTileInformation("chance.csv");
	System.out.println(loadBoard.boardTiles);
    }

    public void readTileInformation(String fileName) {
	File file = new File(fileName);

	File temp = new File((System.getProperty("user.dir") + "/src/" + file).trim());


	try(Scanner scanner = new Scanner(temp)) {

	    scanner.useDelimiter(",");


	    List<String> tileInfo = new ArrayList<>();
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
	   		    int amountId = 0;
	   		    int travelTiles = 0;

	   		    switch (chanceId) {
	   			case "loseMoney":
	   			case "addMoney":
	   			    amountId = Integer.parseInt(tileInfo.get(2).trim());
	   			    break;
	   			case "travelTiles":
	   			    travelTiles = Integer.parseInt(tileInfo.get(2).trim());
	   			    break;
	   			case "getOutOfJail":
	   			   // String getOutOfJail = tileInfo.get(2).trim();
	   			    break;
	   			case "goToJail":
	   			    //String goToJail = tileInfo.get(2).trim();
	   			    break;
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
	   			    throw new IllegalArgumentException("Invalid GameLogic.Card: " + chanceId);
	   		    }

	   		    tileInfo = new ArrayList<>();

	   		} else {
	   		    tileInfo.add(val);
	   		}
	   	    }
	   	}
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}


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

	boardTiles.add(tileMaker.makeHouseTile(board.getTileSize(), tileX, 5, Color.RED, price, streetName, Position.DOWN));
	tileX += 1;
    }

    public void createRightTile(String streetName, int price, Color color) {
	if (getLastAddedTile() != null && !getLastAddedTile().getColor().equals(color)) {
	    tileY = 0;
	}

	boardTiles.add(tileMaker.makeHouseTile(board.getTileSize(), 5, tileY, Color.PINK, price, streetName, Position.RIGHT));
	tileY += 1;
    }

    public void createLeftTile(String streetName, int price, Color color) {
	if (getLastAddedTile() != null && !getLastAddedTile().getColor().equals(color)) {
	    tileY = 0;
	}

	boardTiles.add(tileMaker.makeHouseTile(board.getTileSize(), 0, tileY, Color.ORANGE, price, streetName, Position.LEFT));
	tileY += 1;
    }

    public void createTopTile(String streetName, int price, Color color) {
	if (getLastAddedTile() != null && !getLastAddedTile().getColor().equals(color)) {
	    tileX = 0;
	}

	boardTiles.add(tileMaker.makeHouseTile(board.getTileSize(), tileX, 0, Color.BLUE, price, streetName, Position.UP));
	tileX += 1;
    }

    public void playerLoseMoney(String chanceText, int amount) {
	Card card = new Card(chanceText, "playerLoseMoney", amount);

	chanceCards.add(card);
	//board.getCurrentPlayer().setPlayerMoney(-amount);
    }

    public void playerAddMoney(String chanceText, int amount) {
	Card card = new Card(chanceText, "playerLoseMoney", amount);
	chanceCards.add(card);
	//board.getCurrentPlayer().setPlayerMoney(amount);
    }

    public void playerTravelTiles(String chanceText, int travelTiles) {
	Card card = new Card(chanceText, "playerTravelTiles", travelTiles);
	chanceCards.add(card);

	//board.getCurrentPlayer().move(travelTiles);
    }

    public void playerGoToJail(String chanceText) {
	Card card = new Card(chanceText, "goToJail", 0);
	chanceCards.add(card);

	//board.getCurrentPlayer().goToJail();
    }

    public void playerGetOutOfJail(String chanceText) {
	Card card = new Card(chanceText, "getOutOfJail", 0);
	chanceCards.add(card);
    }

    public ArrayList<Card> getChanceCards() {
	return this.chanceCards;
    }

}
