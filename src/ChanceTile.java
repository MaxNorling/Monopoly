import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class ChanceTile extends SmallTile
{

    private BufferedImage image;
    private LoadBoard loadBoard;
    private ArrayList<Card> chanceCards;
    private Random random;

    public ChanceTile(int x, int y, int width, int height, int boxWidth, int boxHeight, int boxX, int boxY, BufferedImage image, String name , int textX, int textY, ArrayList<Card> chanceCards) {
	super(x,y,width,height,boxWidth,boxHeight,boxX,boxY,name,textX,textY,TileType.CHANCE);
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
        String whatHappend = "";
        switch (card.getActionType()) {
	    case "playerAddMoney":
	        p.setPlayerMoney(card.getAmount());
	        System.out.println("add");
	        whatHappend ="You got" + card.getAmount() + "$";
	        break;
	    case "playerLoseMoney":
		p.setPlayerMoney(-card.getAmount());
		System.out.println("lsoe");
		whatHappend ="You lost" + card.getAmount() + "$";

	        break;
	    case "getOutOfJail":
	        p.giveOutOfJailCard();
		System.out.println("out");
		whatHappend ="You got a get out of jail card";

	        break;
	    case "goToJail":
		System.out.println("in");
		whatHappend ="You got jailed";

	        p.goToJail();
	        break;
	    case "playerTravelTiles":
		System.out.println("t" + card.getAmount());
		System.out.println(p.getCurrentTile());

	        p.specialMove(card.getAmount());
		System.out.println(p.getCurrentTile());
		whatHappend ="You traveled " + card.getAmount() + " tiles";

	        break;
	    default:
	        System.out.println(card.getActionType());
		whatHappend ="Something happened" + card.getAmount();

	}
	return whatHappend;
    }
}
