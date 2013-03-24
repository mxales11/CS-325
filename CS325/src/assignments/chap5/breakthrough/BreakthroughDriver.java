package assignments.chap5.breakthrough;

import java.util.Scanner;

public class BreakthroughDriver {

	public static void main(String[] args) {

		Breakthrough breakthrough = new BreakthroughImpl();
		Scanner scanner = new Scanner(System.in);

		printInstruction();

		while (breakthrough.getWinner() == null) {
			System.out.print("It is "
					+ breakthrough.getCurrentPlayer().toString()
					+ " player's turn: ");

			int rowFrom = scanner.nextInt();
			int colFrom = scanner.nextInt();
			int rowTo = scanner.nextInt();
			int colTo = scanner.nextInt();
			try {
				breakthrough.move(rowFrom, colFrom, rowTo, colTo);
			}

			catch (IllegalMoveException iM) {
				printWarning();
			} catch (ArrayIndexOutOfBoundsException iM) {
				printWarning();
			}
		}
	}

	private static void printInstruction() {
		System.out.println("Let's play Breakthrough!!!");
		System.out
				.println("To make a move type 4 coordinates, e.g. 6 2 5 2 to move from [6,2] square into [5,2] square");
	}

	private static void printWarning() {
		System.out.println("This is illegal move. Try again");
	}
}
