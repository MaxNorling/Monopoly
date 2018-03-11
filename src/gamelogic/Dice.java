package gamelogic;

import java.util.Random;

/**
 * Controls the dies used in the game. Ability to use n-sized dies.
 */

public class Dice
{
    private Random randint;
    private int dieSize;

    public Dice(int dieSize) {
	this.randint = new Random();
	this.dieSize = dieSize;
    }

    public int throwDie() {
	return randint.nextInt((this.dieSize + 1) - 1) + 1; // A random number between 1 and including 6
    }
}
