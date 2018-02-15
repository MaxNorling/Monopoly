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
	this.tileSize = (boardSize / TILE_AMOUNT);
	System.out.println(tileSize);
	tiles = new ArrayList<HouseTile>();
	//TODO SPARA OLIKA BANOR I TEXT FILER
	TileMaker tm= new TileMaker();
	/*tiles.add(new HouseTile(0,0,tileSize,120,Color.BLACK,"Rydsvägen",bottom));
	tiles.add(new HouseTile(1,0,tileSize,120,Color.BLACK,"Rydsvägen",bottom));
	tiles.add(new HouseTile(2,0,tileSize,120,Color.RED,"Rydsvägen",bottom));
	tiles.add(new HouseTile(3,0,tileSize,120,Color.BLUE,"Rydsvägen",bottom));
	tiles.add(new HouseTile(4,0,tileSize,120,Color.ORANGE,"Rydsvägen",bottom));
	tiles.add(new HouseTile(5,0,tileSize,120,Color.RED,"Rydsvägen",bottom));
	tiles.add(new HouseTile(6,0,tileSize,120,Color.BLUE,"Rydsvägen",bottom));
	tiles.add(new HouseTile(7,0,tileSize ,120,Color.ORANGE,"Rydsvägen",bottom));
	tiles.add(new HouseTile(8,0,tileSize,120,Color.RED,"Rydsvägen",bottom));
	tiles.add(new HouseTile(9,0,tileSize,120,Color.BLUE,"Rydsvägen",bottom));
	tiles.add(new HouseTile(10,0,tileSize ,120,Color.ORANGE,"Rydsvägen",bottom));
	tiles.add(new HouseTile(11,0,tileSize,120,Color.BLACK,"Rydsvägen",bottom));
	tiles.add(new HouseTile(12,0,tileSize,120,Color.BLACK,"Rydsvägen",bottom));*/
	for(int x = 2; x < TILE_AMOUNT - 2; x++){
	    tiles.add(tm.makeTopTile(tileSize,x,0,Color.BLUE,100 + x,"Rydsvägen"));

	}
	for(int x = 2;x < TILE_AMOUNT - 2; x++){
	    tiles.add(tm.makeBottomTile(tileSize,x,5,Color.RED,120,"Rydsvägen"));

	}

	for(int y = 2;y < TILE_AMOUNT -2; y++){
	    tiles.add(tm.makeLeftTile(tileSize,0,y,Color.ORANGE,100 + y,"Rydsvägen"));

	}
	for(int y = 2;y < TILE_AMOUNT -2; y++){
	    tiles.add(tm.makeRightTile(tileSize,5,y,Color.PINK,100 + y,"Rydsvägen"));
	    }
	//tiles.add(tm.makeBottomTile(tileSize,0,0,Color.RED,120,"Rydsvägen"));
	//tiles.add(tm.makeLeftTile(tileSize,0,2,Color.ORANGE,120,"Rydsvägen"));
	//tiles.add(tm.makeRightTile(tileSize,0,3,Color.ORANGE,120,"Rydsvägen"));


    }

    public int getBoardSize(){
        return boardSize;
    }

    public ArrayList<HouseTile> getTiles(){
	return tiles;
    }

}
