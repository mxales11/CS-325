package myTrials;

import java.util.Scanner;

import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;

public class AlphamonDriver {

	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		
		MoveValidator mV = new SimpleMoveValidator();

		Game gameImpl = new GameImpl(mV);

		gameImpl.newGame();
		
		gameImpl.nextTurn();

		while (gameImpl.winner() == Color.NONE) {

			while (gameImpl.getNumberOfMovesLeft() != 0) {

				System.out.println("It is " + gameImpl.getPlayerInTurn()
						+ " turn");
				System.out.println("Type where from and where to do you want to move checker, e.g. R1 R2");

				if (!(gameImpl.move(Location.valueOf(scanner.next()),
						Location.valueOf(scanner.next())))) {
					System.out.println("You made an illegal move, try again");
				}
			}

			if (gameImpl.winner() == Color.NONE) {
				gameImpl.nextTurn();

			}
		}
		
		System.out.println("The winner is" + gameImpl.winner());

	}

}
