public interface SpecialCards
{
    public void playerAddMoney(Player p, int amount);

    public void playerLoseMoney(Player p, int amount);

    public void playerTravelTiles(Player p, int amount);

    public void playerGiveCard(Player p);

    public void playerInJail(Player p);

    public void getOutOfJail(Player p);
}
