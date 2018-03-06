public class Community implements SpecialCards
{

    @Override public void playerAddMoney(final Player p, final int amount) {
	p.setPlayerMoney(amount);
    }

    @Override public void playerLoseMoney(final Player p, final int amount) {
	p.setPlayerMoney(-amount);
    }

    @Override public void playerTravelTiles(final Player p, final int amount) {
	System.out.println(p.getCurrentTile());
	p.move(amount);
	System.out.println(p.getCurrentTile());
    }

    @Override public void playerGiveCard(final Player p) {

    }

    @Override public void playerInJail(final Player p) {
	p.goToJail();
    }

    @Override public void getOutOfJail(final Player p) {
	p.giveOutOfJailCard();
    }
}
