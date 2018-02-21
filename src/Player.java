import java.awt.*;
import java.util.ArrayList;

/**
 * Controls the player logic i.e. how much money the player has, what tiles the player owns etc.
 */

public class Player
{
    private int money;
    private int currentTile;
    private ArrayList<Tile> ownedTiles;
    private String name;
    private Color color;
    private boolean jailed;


    private boolean canThrow = true;

    public Player(String name,Color color) {
        this.money = 100; // Should be assigned to a starting money function
        this.currentTile = 0; // Should be assigned to the starting tile "GO"
    	this.ownedTiles = new ArrayList<>();
    	this.name = name;
    	this.color = color;


    	this.jailed = false;
    }

    public int getMoney() {
        return this.money;
    }

    public int getCurrentTile() {
        return this.currentTile;
    }

    public void getOwnedTiles() {} // Should return a Tile object

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
