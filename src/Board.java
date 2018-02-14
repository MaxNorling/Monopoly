import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board
{


    private int boardSize, tileSize;
    private static final int TILE_AMOUNT = 13;

    private ArrayList tiles;


    public Board(int boardSize) {
	this.boardSize = boardSize;
	this.tileSize = boardSize / TILE_AMOUNT;
	tiles = new ArrayList<HouseTile>();

	tiles.add(new HouseTile(0,0,tileSize/2,tileSize,120,Color.RED,"Test"));

    }

    public int getBoardSize(){
        return boardSize;
    }

    public ArrayList<HouseTile> getTiles(){
	return tiles;
    }

}
