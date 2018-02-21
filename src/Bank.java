/**
 * Controls the bank logic during the game.
 */

public class Bank
{
    private double interestRate = 0.1; // Decide what the intial interest rate should be

    public Bank() {}

    public double getInterestRate() { return interestRate; }

    public String canPlayerLoan(Player player, int toLoan) {
	// && player.getLoanCooldown() != 0 && player.getLoanMoney() > 0

	if (toLoan > player.playerWorth()) {
	    return "Loan greater than worth!";
	} else if (player.getLoanMoney() > 0) {

	    return "You have a loan of: $" + player.getLoanMoney() + ", to pay off first!";
	} else if (player.getLoanCooldown() != 0) {

	    return "Turns until a loan can be taken: " + player.getLoanCooldown();
	}

	setInterestRate(player);
	return "Granted"; // Additional constraints to be added that take player financial levels into decision.
    }

    public double setInterestRate(Player player) {
	return player.playerWorth() / 1000;
        /*
        Set the interest rate depending on how many lots have been bought in the game.
        This will make sure that the players might be more inclined to loan to buy lots in order to be progress the game forward.
        As more and more lots dissappear the interest rate will increase and will most likely only be used in more strategic buys.
        This will also help with the potential problem that the bank might lose a lot of money otherwise.
         */
    }

    public void playerGiveMoney(Player player, int amount) {
	player.setPlayerMoney(amount);
    }

    public void sellTile(Player player, HouseTile tile) {
    } // Should take some kind of identifier to tile as parameter
}
