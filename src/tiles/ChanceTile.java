package tiles;

import gamelogic.Card;
import gamelogic.Player;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

/**
 * A small tile that gives you a random card if you land on it.
 * Extends SmallTile to get the methods in both SmallTile and SmallTiles super class Tile.
 */
public class ChanceTile extends SmallTile
{

    private BufferedImage image;
    private List<Card> chanceCards;
    private Random random;

    public ChanceTile(int x, int y, int width, int height, int boxWidth, int boxHeight, int boxX, int boxY, BufferedImage image,
		      String name, int textX, int textY, List<Card> chanceCards)
    {
	super(x, y, width, height, boxWidth, boxHeight, boxX, boxY, name, textX, textY, TileType.CHANCE);
	this.image = image;
	this.chanceCards = chanceCards;
	this.random = new Random();
    }

    public BufferedImage getImage() {
	return image;
    }


    @Override public String toString() {
	return "Gives the player a random card";
    }

    @Override public String landAction(final Player p) {
	Card card = chanceCards.get(random.nextInt(chanceCards.size()));
	String whatHappend;
	switch (card.getAction()) {
	    case "playerAddMoney":
		p.setPlayerMoney(card.getAmount());
		whatHappend = "You got" + card.getAmount() + "$";
		break;
	    case "playerLoseMoney":
		p.loseMoney(card.getAmount());
		whatHappend = "You lost " + card.getAmount() + "$";

		break;
	    case "getOutOfJail":
		p.giveOutOfJailCard();
		whatHappend = "You got a get out of jail card";

		break;
	    case "goToJail":
		whatHappend = "You got jailed";

		p.goToJail();
		break;
	    case "playerTravelTiles":

		p.move(card.getAmount());
		whatHappend = "You landed on tile number " + p.getCurrentTile();
		whatHappend += "\nYou traveled " + card.getAmount() + " tiles";

		break;
	    default:
		whatHappend = "Something went wrong" + card.getAmount();

	}
	return whatHappend;
    }
}
