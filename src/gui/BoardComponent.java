package gui;

import gamelogic.Board;
import gamelogic.Player;
import tiles.ChanceTile;
import tiles.CornerTile;
import tiles.HouseTile;
import tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * The graphical component of the board.
 */
public class BoardComponent extends JComponent implements MouseListener
{


    private static final int SMALL_FONT_SIZE = 12;
    private static final int LARGE_FONT_SIZE = 15;
    private static final int PLAYER_SIZE_RELATIVE_TO_BOARD = 32;
    private static final int DICE_SIZE_RELATIVE_TO_BOARD = 12;
    private final int playerSize;
    private final int diceSize;
    private Board board;
    private BufferedImage[] dice;

    public BoardComponent(Board b) {
	board = b;
	setPreferredSize(new Dimension(board.getBoardSize(), board.getBoardSize()));
	ImageLoader loader = new ImageLoader();
	dice = new BufferedImage[7];

	dice[0] = null;
	dice[1] = loader.loadImage("images/die1.png");
	dice[2] = loader.loadImage("images/die2.png");
	dice[3] = loader.loadImage("images/die3.png");
	dice[4] = loader.loadImage("images/die4.png");
	dice[5] = loader.loadImage("images/die5.png");
	dice[6] = loader.loadImage("images/die6.png");

	playerSize = board.getBoardSize() / PLAYER_SIZE_RELATIVE_TO_BOARD;
	diceSize = board.getBoardSize() / DICE_SIZE_RELATIVE_TO_BOARD;


	addMouseListener(this);


    }

    @Override public void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (Tile tile : board.getTiles()) {
	    switch (tile.getType()) {
		case CORNER:
		    paintCornerTile(g2d, (CornerTile) tile);
		    break;
		case HOUSE:
		    paintHouseTile(g2d, (HouseTile) tile);
		    break;
		case CHANCE:
		    paintChanceTile(g2d, (ChanceTile) tile);
		    break;
	    }
	}
	int count = 0;
	for (Player p : board.getPlayers()) {
	    if (!p.isGameOver()) {
		Tile currentTile = board.getTile(p.getCurrentTile());
		g2d.setColor(p.getColor());
		g2d.fillOval(currentTile.getX() + currentTile.getWidth() / 2 - playerSize,
			     currentTile.getY() + currentTile.getHeight() / 2 + count * 2 - playerSize, playerSize, playerSize);

		g2d.setColor(Color.BLACK);
		g2d.drawOval(currentTile.getX() + currentTile.getWidth() / 2 - playerSize,
			     currentTile.getY() + currentTile.getHeight() / 2 + count * 2 - playerSize, playerSize, playerSize);

		count++;

	    }
	}
	//Dice
	g2d.drawImage(dice[board.lastThrow()], board.getBoardSize() / 2, board.getBoardSize() / 2, diceSize, diceSize, null);


    }


    public void paintHouseTile(Graphics2D g2d, HouseTile tile) {
	int x = tile.getX();
	int y = tile.getY();
	int width = tile.getWidth();
	int height = tile.getHeight();
	Color color = tile.getColor();
	int boxX = tile.getBoxX();
	int boxY = tile.getBoxY();
	int boxWidth = tile.getBoxWidth();
	int boxHeight = tile.getBoxHeight();
	int price = tile.getPrice();
	int textX = tile.getTextX();
	int textY = tile.getTextY();
	String name = tile.getText();


	if (tile.getOwner() != null) {
	    g2d.setColor(tile.getOwnerColor());
	    Stroke oldStroke = g2d.getStroke();

	    g2d.setStroke(new BasicStroke(4));
	    g2d.drawRect(x + 4, y + 4, width - 8, height - 8);

	    g2d.setStroke(oldStroke);
	}
	g2d.setColor(Color.BLACK);
	g2d.drawRect(x, y, width, height);

	//COLORED SQUARE
	g2d.setColor(color);
	g2d.fillRect(boxX, boxY, boxWidth, boxHeight);
	g2d.setColor(Color.BLACK);
	g2d.drawRect(boxX, boxY, boxWidth, boxHeight);

	if (tile.getOwner() != null) {
	    int houses = tile.getHouses();

	    if (houses > 0 && houses <= 4) {

		for (int i = 0; i < houses; i++) { // Houses
		    g2d.setColor(Color.GREEN);
		    g2d.fillRect(boxX + i * boxWidth / 4, boxY, boxWidth / 5, boxHeight);

		    g2d.setColor(Color.BLACK);
		    g2d.drawRect(boxX + i * boxWidth / 4, boxY, boxWidth / 5, boxHeight);
		}

	    } else if (houses > 4) { // Hotel
		g2d.setColor(Color.PINK);
		g2d.fillRect(boxX + boxWidth / 4, boxY + boxHeight / 4, boxWidth / 2, boxHeight / 2);

		g2d.setColor(Color.BLACK);
		g2d.drawRect(boxX + boxWidth / 4, boxY + boxHeight / 4, boxWidth / 2, boxHeight / 2);
	    }
	}

	g2d.setFont(new Font("Serif", Font.BOLD, LARGE_FONT_SIZE));
	g2d.drawString("$" + price, textX, textY);

	g2d.setFont(new Font("Serif", Font.PLAIN, SMALL_FONT_SIZE));
	g2d.drawString(name, textX, textY - LARGE_FONT_SIZE - SMALL_FONT_SIZE);

    }

    public void paintCornerTile(Graphics2D g2d, CornerTile tile) {
	g2d.drawImage(tile.getImage(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight(), null);

	g2d.setColor(Color.BLACK);
	g2d.drawRect(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
    }

    public void paintChanceTile(Graphics2D g2d, ChanceTile tile) {
	int x = tile.getX();
	int y = tile.getY();

	int width = tile.getWidth();
	int height = tile.getHeight();

	int boxX = tile.getBoxX();
	int boxY = tile.getBoxY();

	int boxWidth = tile.getBoxWidth();
	int boxHeight = tile.getBoxHeight();

	g2d.setColor(Color.BLACK);
	g2d.drawRect(x, y, width, height);

	g2d.drawImage(tile.getImage(), boxX, boxY, boxWidth, boxHeight, null);

    }

    @Override public void mouseClicked(final MouseEvent e) {

    }

    @Override public void mousePressed(final MouseEvent e) {
	for (Tile tile : board.getTiles()) {
	    if (tile.contains(e.getPoint())) {
		JOptionPane.showMessageDialog(this, tile, tile.getType().toString(), JOptionPane.PLAIN_MESSAGE);

	    }
	}

    }

    @Override public void mouseReleased(final MouseEvent e) {


    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }

}
