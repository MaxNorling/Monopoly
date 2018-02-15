import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Loads the information on tiles from a csv file
 */
public class LoadBoard
{
    private ArrayList<HouseTile> houseTiles;

    public LoadBoard() {
	this.houseTiles = new ArrayList<>();
    }

    public void readTileInformation() {
	ArrayList<String> tileInfo = new ArrayList<>();

	Scanner scanner = null;

	try {
	    System.out.println();
	    scanner = new Scanner(new File((System.getProperty("user.dir") + "/src/tiles.csv").trim()));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	scanner.useDelimiter(",");

	while (scanner.hasNext()) {
	    String val = scanner.next();

	    if (val.equals("-")) {
		String streetName = "";
		String price = "";
		String color = "";

		for (String info : tileInfo) {
		    if (streetName.equals("")) {
			streetName = info;
		    } else if (price.equals("")) {
			price = info;
		    } else {
			color = info;
		    }
		}

		createTile(streetName, Integer.parseInt(price), Color.getColor(color));
		tileInfo = new ArrayList<>();

	    } else {
		tileInfo.add(val);
	    }
	}
	scanner.close();
    }

    public void createTile(String streetName, int price, Color color) {
	houseTiles.add(new HouseTile(0, 0, (650 / 13) / 2, (650 / 13) / 2, price, color, streetName));
    }
}
