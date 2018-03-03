import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame
{


    public Window(Board b){
        BoardComponent bc = new BoardComponent(b);
        setLayout(new BorderLayout()); // TODO Byt layout
        add(bc,BorderLayout.CENTER);

        GameButtons buttons = new GameButtons(b,bc);



	ArrayList<Player> players  = b.getPlayers();
	JPanel playerPanel = new JPanel();
	playerPanel.setLayout(new GridLayout(4,4));

	for(Player p: players){

	    JButton button = new JButton(p.getName());
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
		  displayPlayer(p);
	      }
	    } );
	    playerPanel.add(button);
	}

	JPanel subPanel = new JPanel();
	subPanel.setLayout(new GridLayout(3,1));

	add(subPanel,BorderLayout.LINE_END);
	subPanel.add(playerPanel,BorderLayout.SOUTH);
	subPanel.setLayout(new GridLayout(5,2));

	subPanel.add(buttons,BorderLayout.LINE_END);
	subPanel.add(playerPanel,BorderLayout.SOUTH);
	add(subPanel,BorderLayout.LINE_END);
	setTitle("Monopoly");
	pack();
	setVisible(true);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        Board board = new Board(780);
        Window w = new Window(board);
    }


    public void displayPlayer(Player player){
	JOptionPane.showMessageDialog(this, player.getName()+" : $" + player.getMoney()
					    + "\n" + "Color:" + player.getColor() + "\n" + "Owned properites:",
				      player.getName(),JOptionPane.PLAIN_MESSAGE);

    }


}
