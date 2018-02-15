import java.util.ArrayList;

/**
 * Controls the player logic i.e. how much money the player has, what tiles the player owns etc.
 */

public class Player
{
    private int playerMoney;
    private int currentTile;
    private int playerWorth;
    private ArrayList<HouseTile> ownedTiles;

    public Player() {
        this.playerMoney = playerMoney; // Should be assigned to a starting money function
        this.currentTile = 0; // Should be assigned to the starting tile "GO"
    	this.ownedTiles = new ArrayList<>();
    	this.playerWorth = playerWorth();
    }

    public int getPlayerMoney() {
        return this.playerMoney;
    }

    public void setPlayerMoney(int amount) {
        this.playerMoney += amount;
    }

    public int getCurrentTile() {
        return this.currentTile;
    }

    public void removeTile() {

    }

    public void getOwnedTiles() {} // Should return a Tile object

    public int playerWorth() {
        int sumMoney = playerMoney;

        for (HouseTile tile: ownedTiles) {
            sumMoney += tile.getPrice(); // Change to resell value instead
        }

        return sumMoney;

    } // Should sum all the stuff a player owns. to be used in risk-analysis for the bank.

    public void moveToTile(int amountOfTiles) {}

    public void askToLoan(int amountToLoan) {}
}
