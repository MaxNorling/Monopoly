import java.awt.*;

public abstract class Tile
{
    private int x, y, width, height;
    private TileType type;

    protected Tile(int x, int y, int width, int height, TileType type){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }


    public boolean contains(Point point){

        if(point.x >= x && point.x <= x + width){
            return point.y >= y && point.y <= y + height;
	}
        return false;
    }

    public int getX(){
        return x;
    }

    public  int getY(){
        return y;
    }

    public  int getWidth(){
        return width;
    }

    public  TileType getType(){
        return type;
    }

    public  int getHeight(){
        return height;
    }

    public abstract String toString();

    public abstract void landAction(Player p);


}
