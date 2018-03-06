import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class OwnedTilesGUI extends JDialog
{
    private static final int SIZE = 300;
    private ArrayList<HouseTile> tiles;
    private OwnedTilesComponent component;

    public OwnedTilesGUI(ArrayList<HouseTile> tiles, boolean house) {
	//MigLayout layout = new MigLayout();
	//setLayout(layout);
	setLayout(new BorderLayout());
	this.tiles = tiles;
	this.setTitle("Owned tiles");
	this.setSize(SIZE / 2, SIZE);
	component = new OwnedTilesComponent(tiles, SIZE / 2, house);
	add(component);
	setModalityType(ModalityType.APPLICATION_MODAL);
	JButton btn = new JButton("OK");
	btn.addActionListener(new ActionListener()
	{

	    @Override public void actionPerformed(final ActionEvent e) {

		dispose();

	    }
	});
	add(btn, BorderLayout.SOUTH);

    }


    public HouseTile showDialog() {
	setVisible(true);
	return component.getClicked();
    }
}
