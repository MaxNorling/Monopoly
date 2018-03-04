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

    @Override public void landAction(final Player p) {
        Card card = chanceCards.get(random.nextInt(chanceCards.size()));
        switch (card.getActionType()) {
	    case "playerAddMoney":
	        p.setPlayerMoney(card.getAmount());
	        System.out.println("add");
	        break;
	    case "playerLoseMoney":
		p.setPlayerMoney(-card.getAmount());
		System.out.println("lsoe");

	        break;
	    case "getOutOfJail":
	        p.giveOutOfJailCard();
		System.out.println("out");

	        break;
	    case "goToJail":
		System.out.println("in");

	        p.goToJail();
	        break;
	    case "playerTravelTiles":
		System.out.println("t");

	        p.move(card.getAmount());
	        break;
	    default:
	        System.out.println(card.getActionType());
	}
    }
}
