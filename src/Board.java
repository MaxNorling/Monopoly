import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Board
{
    private static final int TILE_AMOUNT = 13;
    private int boardSize, tileSize;
    private Dice die;
    private int lastThrow = 1;
    private int currentPlayer;
    private ArrayList<Player> players;
    private Bank bank;
    private ArrayList<Tile> tiles;
    private LoadBoard loadBoard;
    private ArrayList<Card> chanceCards;
    private StringBuilder turnSummary;

    public Board(int boardSize) {
	this.boardSize = boardSize;
	this.tileSize = (boardSize / TILE_AMOUNT);
	System.out.println(tileSize);
	tiles = new ArrayList<>();
	die = new Dice(6);
	bank = new Bank();
	loadBoard = new LoadBoard(this);
	chanceCards = loadBoard.getChanceCards();
	players = new ArrayList<>();
	players.add(new Player("Max", Color.ORANGE));
	players.add(new Player("Jacob", Color.BLUE));


	turnSummary = new StringBuilder();

	currentPlayer = 0;

	//TODO SPARA OLIKA BANOR I TEXT FILER
	TileMaker tm = new TileMaker();

	ImageLoader il = new ImageLoader();
	BufferedImage img = il.loadImage("images/go.png");
	BufferedImage chance = il.loadImage("images/question.png");
	BufferedImage chest = il.loadImage("images/chest.png");
	tiles.add(tm.makeCornerTile(tileSize, TILE_AMOUNT, TILE_AMOUNT, img, "GO", "Pass this tile to get $200."));


	for (int x = TILE_AMOUNT - 3; x >= 2; x--) {
	    if (x == 3) {
		tiles.add(tm.makeBottomChanceTile(tileSize, x, 5, chance, "Chance", chanceCards));
	    } else if (x == 9) {
		tiles.add(tm.makeBottomChanceTile(tileSize, x, 5, chest, "Chest", chanceCards));

	    } else if (x == 10) {
		tiles.add(tm.makeBottomHouseTile(tileSize, x, 5, Color.CYAN, 120, "Valla"));
	    } else if (x == 8) {
		tiles.add(tm.makeBottomHouseTile(tileSize, x, 5, Color.CYAN, 110, "Lambohov"));
	    } else {
		tiles.add(tm.makeBottomHouseTile(tileSize, x, 5, Color.RED, 110, "Ryd"));

	    }

	}

	img = il.loadImage("images/jail.png");
	tiles.add(tm.makeCornerTile(tileSize, 2, TILE_AMOUNT, img, "JAIL", "You dont want to be here."));

	for (int y = TILE_AMOUNT - 3; y >= 2; y--) {
	    if (y == 3) {
		tiles.add(tm.makeLeftChanceTile(tileSize, 0, y, chest, "Chest", chanceCards));
	    } else {
		tiles.add(tm.makeLeftHouseTile(tileSize, 0, y, Color.ORANGE, 100 + y, "Dolor"));
	    }

	}
	img = il.loadImage("images/parking.png");
	tiles.add(tm.makeCornerTile(tileSize, 2, 2, img, "Free parking", "Nothing happens here"));


	for (int x = 2; x < TILE_AMOUNT - 2; x++) {
	    if (x == 3) {
		tiles.add(tm.makeTopChanceTile(tileSize, x, 0, chance, "Chance", chanceCards));
	    } else {
		tiles.add(tm.makeTopHouseTile(tileSize, x, 0, Color.BLUE, 100 + x, "Ipsum"));
	    }

	}
	img = il.loadImage("images/gotoJail.png");
	tiles.add(tm.makeCornerTile(tileSize, TILE_AMOUNT, 2, img, "GO TO JAIL!", "GO DIRECTLY TO JAIl, WITHOUT PASSING GO!"));

	for (int y = 2; y < TILE_AMOUNT - 2; y++) {
	    if (y == 4) {
		tiles.add(tm.makeRightChanceTile(tileSize, 5, y, chest, "Chest", chanceCards));
	    } else if (y == 7) {
		tiles.add(tm.makeRightChanceTile(tileSize, 5, y, chance, "Chance", chanceCards));
	    } else {
		tiles.add(tm.makeRightHouseTile(tileSize, 5, y, Color.PINK, 100 + y, "Lorem"));
	    }
	}


    }

    public int getBoardSize() {
	return boardSize;
    }

    public int getTileSize() {
	return tileSize;
    }

    public ArrayList<Tile> getTiles() {
	return tiles;
    }

    public void throwDie() {
	lastThrow = die.throwDie();
	Player player = players.get(currentPlayer);
	movePlayer(player);
	addToSummary("You rolled a " + lastThrow);
    }


    private void movePlayer(Player player) {
	player.move(lastThrow);
	Tile landedTile = tiles.get(player.getCurrentTile());

	String landed = landedTile.landAction(player);
	if (!landed.isEmpty()) {
	    addToSummary(landed);
	}

	Tile currentTile = tiles.get(player.getCurrentTile());
	addToSummary("You landed on a " + currentTile.getType());

	if (!currentTile.equals(landedTile)) {
	    landed = landedTile.landAction(player);
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

    public ArrayList<Player> getPlayers() {
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
	    if (tile instanceof HouseTile) {
		HouseTile hTile = (HouseTile) tile;
		if (hTile.getColor().equals(color)) {
		    if (hTile.getOwner() == null) {
			return false;
		    } else if (!hTile.getOwner().equals(player)) {
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
	turnSummary.append("\n");
	turnSummary.append(message);
    }

    public String getSummary() {
	return turnSummary.toString();
    }

    public void resetTurnSummary() {
	turnSummary = new StringBuilder();
    }


}
