import java.util.ArrayList;

/**
 * Controls the player logic i.e. how much money the player has, what tiles the player owns etc.
 */

public class Player
{
    private int playerMoney;
    private int currentTile;
    private ArrayList<Tile> ownedTiles;

    public Player() {
        this.playerMoney = playerMoney; // Should be assigned to a starting money function
        this.currentTile = currentTile; // Should be assigned to the starting tile "GO"
    	this.ownedTiles = new ArrayList<>();
    }

    public int getPlayerMoney() {
        return this.playerMoney;
    }

    public int getCurrentTile() {
        return this.currentTile;
    }

    public void getOwnedTiles() {} // Should return a Tile object

    public void moveToTile(int amountOfTiles) {}

    public void askToLoan(int amountToLoan) {}
}
