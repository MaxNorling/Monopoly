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

    public String getCardType() {
	return this.chanceText;
    }

    public String getActionType() {
	return this.action;
    }

    public int getAmount() {
	return this.amount;
    }
}
