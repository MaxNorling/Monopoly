package gamelogic;

/**
 * Contains the information for the cards found on chance and community tiles.
 */
public class Card
{
    private String chanceText;
    private String action;
    private int amount;

    public Card(String chanceText, String actionToMake, int amount) {
	this.chanceText = chanceText;
	this.action = actionToMake;
	this.amount = amount;
    }

    public String getChanceText() {
	return this.chanceText;
    }

    public String getAction() {
	return this.action;
    }

    public int getAmount() {
	return this.amount;
    }
}
