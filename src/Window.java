import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame
{

    private JTextArea currentPlayer;
    private int amountToLoan = 0;

    public Window(Board b){
        BoardComponent bc = new BoardComponent(b);
        setLayout(new BorderLayout());
        add(bc);
        JButton dice = new JButton("Throw dice!");

	dice.addActionListener( new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        if(b.getCurrentPlayer().canThrow()) {
		    b.throwDie();
		    bc.repaint();
		    b.getCurrentPlayer().setCanThrow(false);
		    dice.setEnabled(b.getCurrentPlayer().canThrow());
		}
	    }
	});
	JButton next = new JButton("Next player!");
	next.addActionListener( new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		b.getCurrentPlayer().setCanThrow(true);
	        b.nextPlayer();
	        dice.setEnabled(b.getCurrentPlayer().canThrow());
		currentPlayer.setText(b.getCurrentPlayer().getName() + " $" + b.getCurrentPlayer().getMoney());
	        bc.repaint();

	    }
	});

	SpinnerModel loanSpinner = new SpinnerNumberModel(0, 0, 1000, 50);
	JSpinner spinner = new JSpinner(loanSpinner);
	spinner.addChangeListener(new ChangeListener() {

	    @Override
	    public void stateChanged(ChangeEvent e) {
	        amountToLoan = (int) ((JSpinner)e.getSource()).getValue();
	    }
	});

	JButton loan = new JButton("Ask for a loan from the bank!");
	loan.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		String res = b.getBank().canPlayerLoan(b.getCurrentPlayer(), amountToLoan);
		if (res.equals("Granted")) {
		    JOptionPane.showConfirmDialog(null, "You have been granted a loan!\n" + "Your interest rate is: " + b.getBank().getInterestRate(),
						  "BANK", JOptionPane.DEFAULT_OPTION);
		    b.getCurrentPlayer().setPlayerMoney(amountToLoan);
		    b.getCurrentPlayer().setLoanMoney(amountToLoan);

		    currentPlayer.setText(b.getCurrentPlayer().getName() + " $" + b.getCurrentPlayer().getMoney());
		    bc.repaint();

		    // Add terms
		} else {
		    JOptionPane.showConfirmDialog(null, "You have not been granted a loan.\n" + res, "BANK", JOptionPane.DEFAULT_OPTION);
		    // Add reason for denial
		}

	    }
	});

	ArrayList<Player> players  = b.getPlayers();
	System.out.println(players);
	JPanel playerPanel = new JPanel();
	playerPanel.setLayout(new GridLayout(4,2));

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
	subPanel.setLayout(new GridLayout(8,8));
	currentPlayer = new JTextArea();
	currentPlayer.setText(b.getCurrentPlayer().getName() + " $" +  b.getCurrentPlayer().getMoney());
	subPanel.add(dice);
	subPanel.add(next);
	subPanel.add(loan);
	subPanel.add(spinner);
	subPanel.add(currentPlayer);
	add(subPanel,BorderLayout.LINE_END);
	subPanel.add(playerPanel,BorderLayout.SOUTH);

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
