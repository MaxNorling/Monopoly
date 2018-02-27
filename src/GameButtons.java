import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButtons extends JComponent
{

    private int amountToLoan = 0;

    public GameButtons(Board b,BoardComponent bc) {
	setLayout(new GridLayout(3, 3));

	JTextField currentPlayer = new JTextField();
	currentPlayer.setText(b.getCurrentPlayer().getName() + " : $" + b.getCurrentPlayer().getMoney());
	add(currentPlayer);


	JButton buyTile = new JButton("Buy Tile!");
	buyTile.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(ActionEvent e)
	    {
		Player player = b.getCurrentPlayer();
		Tile currentTile = b.getTile(player.getCurrentTile());
		if (currentTile.getType() == TileType.HOUSE) {
		    player.buyTile((HouseTile) currentTile);
		    bc.repaint();
		}
		currentPlayer.setText(b.getCurrentPlayer().getName() + " $" + b.getCurrentPlayer().getMoney());


	    }
	});
	add(buyTile);

	JButton dice = new JButton("Throw dice!");


	dice.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(ActionEvent e)
	    {
		if (b.getCurrentPlayer().canThrow()) {
		    b.throwDie();

		    b.getCurrentPlayer().setCanThrow(false);
		    dice.setEnabled(b.getCurrentPlayer().canThrow());
		    currentPlayer.setText(b.getCurrentPlayer().getName() + " $" + b.getCurrentPlayer().getMoney());
		    bc.repaint();
		}
	    }
	});
	add(dice);



	JButton next = new JButton("Next player!");
	next.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(ActionEvent e)
	    {
	        if(!b.getCurrentPlayer().isJailed()) {
		    b.getCurrentPlayer().setCanThrow(true);
		}
		b.getCurrentPlayer().setHasMoved(false);

		b.nextPlayer();
		dice.setEnabled(b.getCurrentPlayer().canThrow());
		currentPlayer.setText(b.getCurrentPlayer().getName() + " $" + b.getCurrentPlayer().getMoney());
		bc.repaint();

	    }
	});
	add(next);


	SpinnerModel loanSpinner = new SpinnerNumberModel(0, 0, 1000, 50);
	JSpinner spinner = new JSpinner(loanSpinner);
	spinner.addChangeListener(new ChangeListener() {

	    @Override
	    public void stateChanged(ChangeEvent e) {
	        amountToLoan = (int) ((JSpinner)e.getSource()).getValue();
	    }
	});
	add(spinner);


	JButton loan = new JButton("Ask for a loan from the bank!");
	loan.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        if (amountToLoan < 1) { JOptionPane.showConfirmDialog(null, "Please use the spinner below and specify amount!", "BANK", JOptionPane.DEFAULT_OPTION);
	        } else {
		    String res = b.getBank().canPlayerLoan(b.getCurrentPlayer(), amountToLoan);
		    if (res.equals("Granted")) {
			JOptionPane.showConfirmDialog(null, "You have been granted a loan of $" + amountToLoan + "!\n" +
							    "Your interest rate is: " + b.getBank().getInterestRate(), "BANK",
						      JOptionPane.DEFAULT_OPTION);
			b.getCurrentPlayer().setPlayerMoney(amountToLoan);
			b.getCurrentPlayer().setLoanMoney(amountToLoan);

			currentPlayer.setText(b.getCurrentPlayer().getName() + " $" + b.getCurrentPlayer().getMoney());
			bc.repaint();

			// Add terms
		    } else {
			JOptionPane.showConfirmDialog(null, "You have not been granted a loan.\n" + res, "BANK", JOptionPane.DEFAULT_OPTION);
		    }
		}
		spinner.setValue(0);
	    }
	});
	add(loan);


    }
}
