import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Board
{
    private int boardSize, tileSize;
    private static final int TILE_AMOUNT = 13;
    private Dice die;
    private int lastThrow = 1;
    private int currentPlayer;
    private ArrayList<Player> players;
    private Bank bank;

    private ArrayList<Tile> tiles;


    public Board(int boardSize) {
	this.boardSize = boardSize;
	this.tileSize = (boardSize / TILE_AMOUNT);
	System.out.println(tileSize);
	tiles = new ArrayList<>();
	die = new Dice(6);
	bank = new Bank();

	players = new ArrayList<>();
	players.add(new Player("Sven", Color.ORANGE));
	players.add(new Player("Pelle", Color.BLUE));
	players.add(new Player("Oskar", Color.RED));
	players.add(new Player("Erik", Color.YELLOW));
	players.add(new Player("Tesrta", Color.CYAN));


	currentPlayer = 0;

	//TODO SPARA OLIKA BANOR I TEXT FILER
	TileMaker tm = new TileMaker();

	ImageLoader il = new ImageLoader();
	BufferedImage img = il.loadImage("images/go.png");
	BufferedImage chance = il.loadImage("images/question.png");
	BufferedImage chest = il.loadImage("images/chest.png");
	tiles.add(tm.makeCornerTile(tileSize, TILE_AMOUNT, TILE_AMOUNT, img, "GO \n Pass this tile to get $200."));

	for (int x = TILE_AMOUNT - 3; x >= 2; x--) {
	    if (x == 3) {
		tiles.add(tm.makeBottomChanceTile(tileSize, x, 5, chance, "Chance"));
	    } else if (x == 9) {
		tiles.add(tm.makeBottomChanceTile(tileSize, x, 5, chest, "Chest"));
	    } else {
		tiles.add(tm.makeBottomHouseTile(tileSize, x, 5, Color.RED, 120, "Valla"));
	    }

	}

	img = il.loadImage("images/jail.png");
	tiles.add(tm.makeCornerTile(tileSize, 2, TILE_AMOUNT, img, "JAIL \n You dont want to be here."));

	for (int y = TILE_AMOUNT - 3; y >= 2; y--) {
	    if (y == 3) {
		tiles.add(tm.makeLeftChanceTile(tileSize, 0, y, chest, "Chest"));
	    } else {
		tiles.add(tm.makeLeftHouseTile(tileSize, 0, y, Color.ORANGE, 100 + y, "Ryd"));
	    }

	}
	img = il.loadImage("images/parking.png");
	tiles.add(tm.makeCornerTile(tileSize, 2, 2, img, "FREE PARKING \n"));


	for (int x = 2; x < TILE_AMOUNT - 2; x++) {
	    if (x == 3) {
		tiles.add(tm.makeTopChanceTile(tileSize, x, 0, chance, "Chance"));
	    } else {
		tiles.add(tm.makeTopHouseTile(tileSize, x, 0, Color.BLUE, 100 + x, "Ipsum"));
	    }

	}
	img = il.loadImage("images/gotoJail.png");
	tiles.add(tm.makeCornerTile(tileSize, TILE_AMOUNT, 2, img, "GO TO JAIL! \n GO DIRECTLY TO JAIl, WITHOUT PASSING GO!"));

	for (int y = 2; y < TILE_AMOUNT - 2; y++) {
	    if (y == 4) {
		tiles.add(tm.makeRightChanceTile(tileSize, 5, y, chest, "Chest"));
	    } else if (y == 7) {
		tiles.add(tm.makeRightChanceTile(tileSize, 5, y, chance, "Chance"));
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

    }

    private void movePlayer(Player player){
	player.move(lastThrow);
	tiles.get(player.getCurrentTile()).landAction(player);
    }

    public int lastThrow() {
	return lastThrow;
    }

    public int getTileAmount() {
	return TILE_AMOUNT - 2; //CORNER TILES ARE 2 TILES
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

    public void nextPlayer() {
	if (getCurrentPlayer().getLoanMoney() > 0) {
	    double loanPayment = getCurrentPlayer().getLoanMoney() - (getCurrentPlayer().getLoanMoney() * bank.getInterestRate());
	    getCurrentPlayer().payLoan((int) loanPayment);

	}

	currentPlayer++;
	if (currentPlayer >= players.size()) {
	    currentPlayer = 0;
	}
    }


}
