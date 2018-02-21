import java.awt.*;
import java.util.ArrayList;

/**
 * Controls the player logic i.e. how much money the player has, what tiles the player owns etc.
 */

public class Player
{
    private int money;
    private int playerWorth;
    private int currentTile;
    private ArrayList<HouseTile> ownedTiles;
    private String name;
    private Color color;
    private boolean jailed;


    private boolean canThrow;

    public Player(String name,Color color) {
        this.money = 100; // Should be assigned to a starting money function
        this.currentTile = 0; // Should be assigned to the starting tile "GO"
    	this.ownedTiles = new ArrayList<>();
    	this.name = name;
    	this.color = color;

    	this.jailed = false;
    	this.playerWorth = playerWorth();
    	this.canThrow = true;
    }

    public int getMoney() {
        return this.money;
    }

    public void setPlayerMoney(int amount) {
        this.money += amount;
    }

    public int getCurrentTile() {
        return this.currentTile;
    }

    public void removeTile() {

    }

    public void getOwnedTiles() {} // Should return a Tile object

    public int playerWorth() {
        int sumMoney = money;

        for (HouseTile tile: ownedTiles) {
            sumMoney += tile.getPrice(); // Change to resell value instead
        }

        return sumMoney;

    } // Should sum all the stuff a player owns. to be used in risk-analysis for the bank.

    public void moveToTile(int amountOfTiles) {}

    public void askToLoan(int amountToLoan) {}

    public void passedGo(){

    }

    public void move(int i) {
        if(!jailed){
	    currentTile += i;
	    if (currentTile >= 40) { // 40 is the ammount of tiles on the board
		currentTile -=40;
	    passedGo();
	    }
        }
    }
    public boolean canThrow(){
        return canThrow;
    }
    public void setCanThrow(boolean b){
        canThrow = b;
    }
    public String getName(){
        return name;
    }

    public Color getColor(){
        return color;
    }
}
