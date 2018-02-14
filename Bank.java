/**
 * Controls the bank logic during the game.
 */

public class Bank
{
    private int bankMoney;
    private int interestRate;

    public Bank() {}

    private int getBankMoney() {
        return this.bankMoney;
    }

    public boolean canLoanMoney(int toLoan) {
        return getBankMoney() > toLoan; // Additional constraints to be added that take banks finanical levels into decision.
    }

    public boolean canPlayerLoan(int toLoan) {
        return canLoanMoney(toLoan); // Additional constraints to be added that take player financial levels into decision.
    }

    public int setInterestRate() {
        return 0;
        /*
        Set the interest rate depending on how many lots have been bought in the game.
        This will make sure that the players might be more inclined to loan to buy lots in order to be progress the game forward.
        As more and more lots dissappear the interest rate will increase and will most likely only be used in more strategic buys.
        This will also help with the potential problem that the bank might lose a lot of money otherwise.
         */
    }

    public void playerLoanMoney() {}

    public void getMoneyFromLoan() {
        /*
        Allow for the player to pay back a small amount per turn * interest.
        Or
        Pay the whole loan back * interest.
         */
    }

    public void getMoney() {
        /*
        Add money to the bank for various reasons.
         */
    }

    public void handOutMoney() {
        /*
        Gives a player a specified amount for various reasons. e.g. in the beginning of the game.
         */
    }
}
