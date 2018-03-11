package gui;

import gamelogic.Board;

/**
 * Used to start the game
 */
public final class GameTest
{
    private static final int BOARD_SIZE = 780;

    private GameTest() {}

    public static void main(String[] args) {
	Board board = new Board(BOARD_SIZE);
	Window window = new Window(board);
    }
}
