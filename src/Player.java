import java.awt.*;
import java.util.ArrayList;

/**
 * Controls the player logic i.e. how much money the player has, what tiles the player owns etc.
 */

public class Player
{
    private int money;
    private int playerWorth;
    private int playerLoanCooldown;
    private int loanMoney;
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
    	this.playerLoanCooldown = 0;
    	this.loanMoney = 0;

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

    public void removeTile() {}

    public boolean canBuyTile(HouseTile tile) {
        if (tile.getPrice() > money) {
            return false;
        }

        return true;
    }

    public int playerWorth() {
        int sumMoney = money;

        for (HouseTile tile: ownedTiles) {
            sumMoney += tile.getPrice(); // Change to resell value instead
        }

        return sumMoney - loanMoney;

    } // Should sum all the stuff a player owns. to be used in risk-analysis for the bank.

    public void passedGo(){

    }

    public int getLoanCooldown() {
        return playerLoanCooldown;
    }

    public void setLoanCooldown() {
        playerLoanCooldown += 5; // Change to be dynamic depending on factors
    }

    public int getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(int amount) {
        loanMoney += amount;
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
    public ArrayList<HouseTile> getOwnedTiles() { return ownedTiles; }

    public Color getColor(){
        return color;
    }
}
