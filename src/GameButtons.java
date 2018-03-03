import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameButtons extends JComponent
{

    private int amountToLoan = 0;
    private JTextField currentPlayer;
    private Board b;
    private BoardComponent bc;
    private OwnedTilesGUI ownedTilesGUI;

    public GameButtons(Board b,BoardComponent bc) {
	setLayout(new GridLayout(3, 3));
	currentPlayer = new JTextField();
	currentPlayer.setText(b.getCurrentPlayer().getName() + " : $" + b.getCurrentPlayer().getMoney());
	add(currentPlayer);
	this.b = b;
	this.bc = bc;

	JButton buyTile = new JButton("Buy Tile!");
	buyTile.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(ActionEvent e)
	    {
		b.buyTile();
		updateScreen();


	    }
	});
	add(buyTile);

	JButton buyHouse = new JButton("Buy house!");
	buyHouse.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(ActionEvent e)
	    {
		HouseTile selected = displayOwnedTiles(b.getCurrentPlayer());
		if(!b.buyHouse(selected)){
		    JOptionPane.showConfirmDialog(null,"ERROR BUYING HOUSE! \n \n You need to own all tiles of the same color", "ERROR",JOptionPane.DEFAULT_OPTION);
		}

		updateScreen();

	    }
	});
	add(buyHouse);


	JButton dice = new JButton("Throw dice!");


	dice.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(ActionEvent e)
	    {
		if (b.getCurrentPlayer().canThrow()) {
		    b.throwDie();

		    b.getCurrentPlayer().setCanThrow(false);
		    dice.setEnabled(b.getCurrentPlayer().canThrow());
		    updateScreen();
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
		updateScreen();

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



			// Add terms
		    } else {
			JOptionPane.showConfirmDialog(null, "You have not been granted a loan.\n" + res, "BANK", JOptionPane.DEFAULT_OPTION);
		    }
		}
		spinner.setValue(0);
		updateScreen();
	    }
	});
	add(loan);



    }

    private HouseTile displayOwnedTiles(Player player){
	ArrayList<HouseTile> owned = b.getOwnedTiles(player);

	if(owned.isEmpty()){
	    JOptionPane.showMessageDialog(this,"You do not own any tiles.","ERROR",JOptionPane.PLAIN_MESSAGE);
	}else{
	    if(ownedTilesGUI != null) {
		ownedTilesGUI.dispose();
	    }

	    ownedTilesGUI =  new OwnedTilesGUI(owned);
	    HouseTile result = ownedTilesGUI.showDialog();
	    return result;
	}
	return null;
    }

    private void updateScreen(){
   	currentPlayer.setText(b.getCurrentPlayer().getName() + " $" + b.getCurrentPlayer().getMoney());
   	bc.repaint();
    }

}
