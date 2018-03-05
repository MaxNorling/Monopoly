import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class OwnedTilesComponent extends JComponent implements MouseListener
{
    private ArrayList<HouseTile> tiles;
    private static int cardWidth;
    private static int cardHeight;
    private HouseTile clicked = null;
    private int clickedIndex = -1;
    private boolean house;


    public OwnedTilesComponent(ArrayList<HouseTile> tiles,int size, boolean house){
        this.tiles = tiles;
	cardWidth = size;
	cardHeight = size/2;
	addMouseListener(this);
	this.house = house;

       // tiles.sort() TODO Sortera efter färg.
    }


    @Override public void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for(int i = 0; i < tiles.size(); i++){
	    HouseTile tile = tiles.get(i);

	    g2d.setColor(tile.getColor());
	    g2d.fillRect(0,i * cardHeight,cardWidth,cardHeight);



	    g2d.setColor(Color.BLACK);
	    String message = ""+tile.getHousePrice();
	    if(!house) {
		message = ""+tile.getSellValue();
	    }
	    g2d.drawString(tile.getName() + ": $" + message, cardWidth / 2, (i * cardHeight) + cardHeight / 2);

	    g2d.drawRect(0,i * cardHeight,cardWidth,cardHeight);
	    if(i == clickedIndex){
	        g2d.setColor(Color.GREEN);
	        g2d.drawRect(0,i * cardHeight,cardWidth,cardHeight);
	    }

	    }
	}


    @Override public void mouseClicked(final MouseEvent e) {

    }

    @Override public void mousePressed(final MouseEvent e) {
	for(int y = 0; y < tiles.size(); y++){
	    Rectangle temp = new Rectangle(0,y * cardHeight, cardWidth, cardHeight);

	    if(temp.contains(e.getPoint())){
	        clicked = tiles.get(y);
	        clickedIndex = y;
	        repaint();
	    }
	}
    }

    @Override public void mouseReleased(final MouseEvent e) {

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }

    public HouseTile getClicked(){
           return clicked;
       }


}

