package gui;

import gamelogic.Board;
import gamelogic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The window that the game is played in.
 */
public class Window extends JFrame
{

    public Window(Board b) {
	BoardComponent bc = new BoardComponent(b);
	setLayout(new BorderLayout()); // TODO Byt layout
	add(bc, BorderLayout.CENTER);

	GameButtons buttons = new GameButtons(b, bc);


	Iterable<Player> players = b.getPlayers();
	JPanel playerPanel = new JPanel();
	playerPanel.setLayout(new GridLayout(4, 4));

	for (Player p : players) {
	    if (!p.isGameOver()) {
		JButton button = new JButton(p.getName());
		button.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e) {
			displayPlayer(p);
		    }
		});
		playerPanel.add(button);
	    }
	}

	JPanel subPanel = new JPanel();
	subPanel.setLayout(new GridLayout(3, 1));

	add(subPanel, BorderLayout.LINE_END);
	subPanel.add(playerPanel, BorderLayout.SOUTH);
	subPanel.setLayout(new GridLayout(5, 2));

	subPanel.add(buttons, BorderLayout.LINE_END);
	subPanel.add(playerPanel, BorderLayout.SOUTH);
	add(subPanel, BorderLayout.LINE_END);
	setTitle("Monopoly");
	pack();
	setVisible(true);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public void displayPlayer(Player player) {
	if (!player.isGameOver()) {
	    JOptionPane.showMessageDialog(this, player.toString());
	} else {
	    JOptionPane.showMessageDialog(this, player.getName() + " Has ran out of money...");
	}
    }


}
