package myTrials;

import java.util.Random;

public class RandomTrials {

	public static void main(String[] args) {
		Random generator = new Random();
		for (int i = 0; i < 50; i++) {
			System.out.println(generator.nextInt(6) + 1);
		}
	}

}
