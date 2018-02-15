import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{


    public Window(Board b){
        BoardComponent bc = new BoardComponent(b);
        setLayout(new BorderLayout());
        add(bc);



	setTitle("Monopoly");
	pack();
	setVisible(true);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        Board board = new Board(910);
        Window w = new Window(board);
    }


}
