package Project5;

/**Zombie Project
 * CSci 2001-91
 */

/* Just a main class. Nothing much to see as all/most logic is in the Apocalypse class. */

public class Main {

	public static void main(String[] args) {

	    Apocalypse zombieWorld = new Apocalypse(Utilities.firstRun());
	    zombieWorld.apocalypseLoop();
		
	}

}
