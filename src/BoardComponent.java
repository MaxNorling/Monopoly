import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class BoardComponent extends JComponent
{


    private Board board;


    public BoardComponent(Board b) {
	board = b;
	setPreferredSize(new Dimension(board.getBoardSize(), board.getBoardSize()));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;



        for(HouseTile tile: board.getTiles()){
	    tile.paint(g2d);
	}

    }

}
