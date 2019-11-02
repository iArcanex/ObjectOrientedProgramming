package Project5;

import java.util.Scanner;

/**Zombie Project
 * CSci 2001-91
 */

/* Class composed of constants, random num gen, difficulty select method
*  and some messages regarding certain events.
* */

public class Utilities {

	public static final int START_NUM_DAYS = 10, START_FOOD_COUNT = 80, START_AMMO = 150, START_LIVE_COUNT = 25, MIN_ZOMBIES = 10, MAX_ZOMBIES = 20;
	
	
	// Random Num Generator
	public static int generateRandomInRange(int min, int max) {

		return min + (int)(Math.random() * ((max - min) + 1));

	}

	// Difficulty Select
	public static int firstRun() {

		int difficultyValue = 0;
		Scanner difficultyInput = new Scanner(System.in);

		Utilities.printFirstRun();

		while(difficultyValue == 0) {

			difficultyValue = difficultyInput.nextInt();
			boolean incorrectDifficulty = difficultyValue == 0 || difficultyValue > 3;

			if(incorrectDifficulty) {

				System.out.println("Incorrect difficulty entered. \nPlease re-enter the difficulty...");
				difficultyValue = 0;

			}

			else {

				System.out.println("Difficulty selected: " + difficultyValue);

			}

		}

		return difficultyValue;

	}

	// Methods for Messages
	public static void printFirstRun() {

		Scanner input = new Scanner(System.in);

		System.out.println("##################################");
		System.out.println("# -=-+-=- ZOMBIE ATTACK -=-+-=-  #");
		System.out.println("# Can you survive an apocalyptic #");
		System.out.println("#    event locked within CBT?    #");
		System.out.println("##################################");
		System.out.println("#     Choose your difficulty:    #");
		System.out.println("#  1: EASY   2: MEDIUM  3: HARD  #");
		System.out.println("##################################");


	}

	public static void printMessageStudentsNewDay() {

		// Generates a random value from one to five
		// which will be used for a random message
		int randomMessage = generateRandomInRange(1, 5);
		switch(randomMessage) {

			case 1:
				System.out.println("Over the day, the students cowered in fear...");
				break;
			case 2:
				System.out.println("One of the students seems to be acting strange.");
				break;
			case 3:
				System.out.println("A crazed man was seen slamming himself on the windows.");
				break;
			case 4:
				System.out.println("The students catch whiff of a weird scent.");
				break;
			case 5:
				System.out.println("A helicopter has been heard in the distance.");
				break;

		}

		System.out.println(".     .     .     .     .     .   ");

	}

	// Getters

	public static int getStartNumDays() {
		return START_NUM_DAYS;
	}

	public static int getStartFoodCount() {
		return START_FOOD_COUNT;
	}

	public static int getStartAmmo() {
		return START_AMMO;
	}

	public static int getStartLiveCount() {
		return START_LIVE_COUNT;
	}

	public static int getMinZombies() {
		return MIN_ZOMBIES;
	}

	public static int getMaxZombies() {
		return MAX_ZOMBIES;
	}

}
