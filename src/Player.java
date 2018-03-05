import java.awt.*;
import java.util.ArrayList;

/**
 * Controls the player logic i.e. how much money the player has, what tiles the player owns etc.
 */

public class Player
{
    private int money;
    private int playerWorth;
    private int loanCooldown;
    private int loanMoney;
    private int currentTile;
    private ArrayList<HouseTile> ownedTiles;
    private String name;
    private Color color;
    private boolean jailed;
    private boolean hasMoved;
    private boolean outOfJailCard;
    private boolean gameOver;

    private int jailedTurns;
    private boolean canThrow;

    public Player(String name,Color color) {
	this.money = 1000; // Should be assigned to a starting money function
	this.currentTile = 0; // Should be assigned to the starting tile "GO"
	this.ownedTiles = new ArrayList<>();
	this.name = name;
	this.color = color;
	this.loanCooldown = 0;
	this.loanMoney = 0;
	this.jailedTurns = 0;

	this.jailed = false;
	this.outOfJailCard = false;
	this.playerWorth = playerWorth();
	this.canThrow = true;
	this.hasMoved = false;
	this.gameOver = false;
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

    public boolean canBuyTile(HouseTile tile) {
	return tile.getPrice() > money;
    }

    public int playerWorth() {
	int sumMoney = money;

	for (HouseTile tile : ownedTiles) {
	    sumMoney += tile.getPrice(); // Change to resell value instead
	}

	return sumMoney - loanMoney;

    } // Should sum all the stuff a player owns. to be used in risk-analysis for the bank.


    public int getLoanCooldown() {
	return loanCooldown;
    }

    public void setLoanCooldown() {
	loanCooldown += 5; // Change to be dynamic depending on factors
    }

    public int getLoanMoney() {
	return loanMoney;
    }

    public void setLoanMoney(int amount) {
	loanMoney += amount;
    }

    public void payLoan(int loanPayment) {
	// Should take things from players if player cant pay
	if (loanPayment > money) {
	    int playerWorth = playerWorth() - money;
	    // Iterate over properties and total the required amount to take from player
	    // Should let player decide which properties to sell
	    // Should show a prompt with a list of owned properties and a sell button next to them
	} else {
	    money -= loanPayment;
	}
    }
    public void passedGo(){
	money+=200;
    }

    public boolean buyTile(HouseTile tile) {
	if (hasMoved) {
	    if (tile.getOwner() == null) {
		if (money > tile.getPrice()) {
		    loseMoney(tile.getPrice());
		    tile.setOwner(this);
		    ownedTiles.add(tile);
		    return true;
		}
	    }
	}
	return false;
    }


    public void move(int i) {
	if(!hasMoved){
	    if(!jailed){
	    	currentTile += i;
	    }
	    hasMoved = true;
	    if (currentTile >= 40) { // 40 is the ammount of tiles on the board
		currentTile -= 40;
		passedGo();
	    }
	}
    }

    public void goToJail() {
        this.currentTile = 10;
        this.jailed = true;
    }

    public boolean getOutOfJailCard() {
        return this.outOfJailCard;
    }

    public void giveOutOfJailCard() {
        this.outOfJailCard = true;
    }


    private void setPosition(int position) {
        this.currentTile = position;

    }

    public void specialMove(int amount) {
        // Used when a special card moves the player a specified amount, can't use normal move
	this.currentTile += amount;

	if(currentTile > 40){
       	 currentTile -=40;
	}
    }

    public void loseMoney(int lost){
	money-=lost;
	if (playerWorth <= 0){
	    gameOver();
	}

    }

    public void gameOver(){
	gameOver = true;
    }

    public boolean canThrow() {
	return canThrow;
    }
    public boolean hasMoved(){
        return hasMoved;
    }
    public void setHasMoved(boolean b){
        hasMoved = b;
    }
    public void setCanThrow(boolean b) {
	canThrow = b;
    }

    public String getName() {
	return name;
    }
    public void endTurn(){
	canThrow = true;
	hasMoved = false;

    }

    public ArrayList<HouseTile> getOwnedTiles() { return ownedTiles; }

    public Color getColor() {
	return color;
    }
    public boolean isJailed(){
        return jailed;
    }
    public void leaveJail(){
        jailed = false;
        hasMoved = false;
        jailedTurns = 0;
    }
    public int getJailedTurns(){
        return jailedTurns;
    }
    public void increaseJailedTurns(){
        jailedTurns++;
    }
}
