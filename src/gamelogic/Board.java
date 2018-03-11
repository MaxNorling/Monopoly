package gamelogic;

import gui.ImageLoader;
import tiles.HouseTile;
import tiles.Position;
import tiles.Tile;
import tiles.TileMaker;
import tiles.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls the board logic i.e all the players, the tiles , size of the tiles and what happend during the turn.
 */
public class Board
{
    private static final int TILE_AMOUNT = 13;
    private static final int START_MONEY = 550;
    private static final int PASS_GO_MONEY = 200;
    private static final int AMOUNT_OF_TILES = 40;
    private int boardSize, tileSize;
    private Dice die;
    private int lastThrow = 1;
    private int currentPlayer;
    private List<Player> players;
    private Bank bank;
    private List<Tile> tiles;
    private String turnSummary;

    public Board(int boardSize) {
	this.boardSize = boardSize;
	this.tileSize = (boardSize / TILE_AMOUNT);
	tiles = new ArrayList<>();
	die = new Dice(6);
	bank = new Bank();
	final LoadBoard loadBoard = new LoadBoard(this);
	final ArrayList<Card> chanceCards = loadBoard.getChanceCards();
	players = new ArrayList<>();
	players.add(new Player("Max", Color.ORANGE, START_MONEY, PASS_GO_MONEY, AMOUNT_OF_TILES));
	players.add(new Player("Jacob", Color.BLUE, START_MONEY, PASS_GO_MONEY, AMOUNT_OF_TILES));


	turnSummary = "";

	currentPlayer = 0;

	TileMaker tm = new TileMaker();

	ImageLoader il = new ImageLoader();
	BufferedImage img = il.loadImage("images/go.png");
	BufferedImage chance = il.loadImage("images/question.png");
	BufferedImage chest = il.loadImage("images/chest.png");
	tiles.add(tm.makeCornerTile(tileSize, TILE_AMOUNT, TILE_AMOUNT, img, "GO", "Pass this tile to get $200."));


	for (int x = TILE_AMOUNT - 3; x >= 2; x--) {
	    switch (x) {
		case 3:
		    tiles.add(tm.makeChanceTile(tileSize, x, 5, chance, "Chance", chanceCards, Position.DOWN));
		    break;
		case 9:
		    tiles.add(tm.makeChanceTile(tileSize, x, 5, chest, "Chest", chanceCards, Position.DOWN));

		    break;
		case 10:
		    tiles.add(tm.makeHouseTile(tileSize, x, 5, Color.CYAN, 120, "Valla", Position.DOWN));
		    break;
		case 8:
		    tiles.add(tm.makeHouseTile(tileSize, x, 5, Color.CYAN, 110, "Lambohov", Position.DOWN));
		    break;
		default:
		    tiles.add(tm.makeHouseTile(tileSize, x, 5, Color.RED, 110, "Ryd", Position.DOWN));

		    break;
	    }

	}

	img = il.loadImage("images/jail.png");
	tiles.add(tm.makeCornerTile(tileSize, 2, TILE_AMOUNT, img, "JAIL", "You dont want to be here."));

	for (int y = TILE_AMOUNT - 3; y >= 2; y--) {
	    if (y == 3) {
		tiles.add(tm.makeChanceTile(tileSize, 0, y, chest, "Chest", chanceCards, Position.LEFT));
	    } else {
		tiles.add(tm.makeHouseTile(tileSize, 0, y, Color.ORANGE, 100 + y, "Dolor", Position.LEFT));
	    }

	}
	img = il.loadImage("images/parking.png");
	tiles.add(tm.makeCornerTile(tileSize, 2, 2, img, "Free parking", "Nothing happens here"));


	for (int x = 2; x < TILE_AMOUNT - 2; x++) {
	    if (x == 3) {
		tiles.add(tm.makeChanceTile(tileSize, x, 0, chance, "Chance", chanceCards, Position.UP));
	    } else {
		tiles.add(tm.makeHouseTile(tileSize, x, 0, Color.BLUE, 100 + x, "Ipsum", Position.UP));
	    }

	}
	img = il.loadImage("images/gotoJail.png");
	tiles.add(tm.makeCornerTile(tileSize, TILE_AMOUNT, 2, img, "GO TO JAIL!", "GO DIRECTLY TO JAIl, WITHOUT PASSING GO!"));

	for (int y = 2; y < TILE_AMOUNT - 2; y++) {
	    switch (y) {
		case 4:
		    tiles.add(tm.makeChanceTile(tileSize, 5, y, chest, "Chest", chanceCards, Position.RIGHT));
		    break;
		case 7:
		    tiles.add(tm.makeChanceTile(tileSize, 5, y, chance, "Chance", chanceCards, Position.RIGHT));
		    break;
		default:
		    tiles.add(tm.makeHouseTile(tileSize, 5, y, Color.PINK, 100 + y, "Lorem", Position.RIGHT));
		    break;
	    }
	}


    }

    public int getBoardSize() {
	return boardSize;
    }

    public int getTileSize() {
	return tileSize;
    }

    public Iterable<Tile> getTiles() {
	return tiles;
    }

    public void throwDie() {
	lastThrow = die.throwDie();
	Player player = players.get(currentPlayer);
	movePlayer(player, lastThrow);
	addToSummary("You rolled a " + lastThrow);
    }


    private void movePlayer(Player player, int distance) {
	player.move(distance);

	Tile currentTile = tiles.get(player.getCurrentTile());

	String landed = currentTile.landAction(player);

	if (!landed.isEmpty()) {
	    addToSummary(landed);
	}

	addToSummary("You landed on a " + currentTile.getType());

    }

    public int lastThrow() {
	return lastThrow;
    }

    public Tile getTile(int i) {
	return tiles.get(i);
    }

    public Iterable<Player> getPlayers() {
	return players;
    }

    public Player getCurrentPlayer() {
	return players.get(currentPlayer);
    }

    public Bank getBank() {
	return bank;
    }

    private boolean isHouseTile(Tile tile) {
	return (tile.getType() == TileType.HOUSE);
    }

    public boolean buyHouse(HouseTile tile) {
	Player player = getCurrentPlayer();
	int money = player.getMoney();
	if (tile != null) {
	    if (ownsAll(tile.getColor(), player)) {
		int price = tile.getHousePrice();
		if (money > price) {
		    player.loseMoney(tile.buyHouse());
		    addToSummary("You bought house on " + tile.getName() + " for" + price);
		    return true;
		}
	    }
	}
	return false;
    }

    public boolean sellTile(HouseTile tile) {
	Player player = getCurrentPlayer();
	if (tile != null) {
	    bank.sellTile(player, tile);
	    return true;
	}
	return false;

    }

    public boolean ownsAll(Color color, Player player) {
	for (Tile tile : tiles) {
	    if (tile.getType() == TileType.HOUSE) {
		HouseTile hTile = (HouseTile) tile;
		if (hTile.getColor().equals(color)) {
		    if (hTile.getOwner() == null || !hTile.getOwner().equals(player)) {
			return false;
		    }
		}
	    }
	}

	return true;
    }


    public void buyTile() {
	Player player = getCurrentPlayer();
	Tile currentTile = getTile(player.getCurrentTile());

	if (isHouseTile(currentTile)) {
	    HouseTile cTile = (HouseTile) currentTile;
	    if (player.buyTile(cTile)) {
		addToSummary("You bought the tile " + cTile.getName() + " for" + cTile.getPrice());
	    }
	}
    }

    public ArrayList<HouseTile> getOwnedTiles(Player player) {
	return player.getOwnedTiles();
    }

    public void nextPlayer() {

	if (getCurrentPlayer().getLoanMoney() > 0) {
	    double loanPayment =
		    getCurrentPlayer().getLoanMoney() - (getCurrentPlayer().getLoanMoney() * bank.getInterestRate());

	    getCurrentPlayer().payLoan((int) loanPayment);
	    addToSummary("You paid back " + loanPayment + "$ to the bank.");

	}

	currentPlayer++;
	if (currentPlayer >= players.size()) {
	    currentPlayer = 0;
	}
	if (getCurrentPlayer().isGameOver()) {
	    if (allGameOver()) {
		endGame();
	    } else {
		nextPlayer();
	    }


	}
    }

    private void endGame() {
	JOptionPane.showMessageDialog(null, getCurrentPlayer().getName() + " HAS WON THE GAME"); //TODO Gör det bättre...
	System.exit(0);


    }

    public Player getWinner() {
	if (allGameOver()) {
	    for (Player player : players) {
		if (!player.isGameOver()) {
		    return player;
		}
	    }
	}
	return null;
    }

    public boolean allGameOver() {
	int alivePlayers = 0;
	for (Player player : players) {
	    if (!player.isGameOver()) {
		alivePlayers++;
	    }
	}

	return !(alivePlayers > 1);

    }

    public void addToSummary(String message) {
	StringBuilder builder = new StringBuilder(turnSummary);
	builder.append("\n");
	builder.append(message);

	turnSummary = builder.toString();
    }

    public String getTurnSummary() {
	return turnSummary;
    }

    public void resetTurnSummary() {
	turnSummary = "";
    }


}
