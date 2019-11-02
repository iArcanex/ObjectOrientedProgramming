package Project5;

import java.util.Random;
import java.util.Scanner;

/**Zombie Project
 * CSci 2001-91
 */

/* A MESSAGE: Warning... A lot of code bloat here. This is what happens when you try to program
*  something within a few hours. While looking through the code, think about "What could be done differently?"
*  Looking for algorithm efficiency is key for a lot of problems! */

/* This simulator allows one to go through ten days within a CBT centered in a world
*  that is run with zombies. 25 Begin and are well-armed. Students are able to venture
*  and collect resources. Students can unlock a special weapon to use. A flare can be
*  used to call a helicopter. Difficulty changes how many zombies you'll fight, as
*  the weaker ones have not evolved yet. This project has been balanced so one can win
*  utilizing these tools at least once within 15 runs (that's the worst I got). Good luck! */

public class Apocalypse {

    private int numDays, minZombies, maxZombies, activeZombies, weakZombies,
            currentDay, difficulty, zombieHit, numFlamethrower, numFlare, finalDay;
    private boolean isSaved;
    Scanner input;
    Person students;
    Random hitChance;
	
	// Constructor allows difficulty to be passed through
    // which will be utilized as a multiplier for other logic
    public Apocalypse(int difficultyValue) {

        difficulty = difficultyValue;
        numDays = Utilities.START_NUM_DAYS;
        minZombies = Utilities.MIN_ZOMBIES;
        maxZombies = Utilities.MAX_ZOMBIES;
        currentDay = (numDays) - (numDays - 1);
        zombieHit = 0;
        numFlamethrower = 3; // For balancing, students have three at start
        numFlare = 0;
        isSaved = false;
        students = new Person();
        hitChance = new Random();
        input = new Scanner(System.in);

    }

    public void apocalypseLoop() {

        // Loop designed to continue the simulation
        // assuming a student is alive, the heli hasn't come yet, and it is less than numDays

        while((currentDay <= numDays && students.getLiveCount() > 0) && !isSaved) {

            newDay();

        }

        if(finalDay <= numDays && students.getLiveCount() > 0) {
            System.out.println("##################################");
            System.out.println("The students have survived!");
            System.out.println("##################################");
            System.out.println(" -=-+-=- Final Stats -=-+-=-  ");
            System.out.println("Day(s) Survived: " + finalDay);
            System.out.println("Students Alive: " + students.getLiveCount());
            System.out.println("Units of Food left: " + students.getFoodCount());
            System.out.println("Collective Ammo left: " + students.getAmmo());
            System.out.println("##################################");
            System.out.println("True survivors amongst the long days.");
        }

        if(isSaved) {

            System.out.println("Yet, saved by the armed task force. Lucky.");

        }

        if(students.getLiveCount() <= 0) {

            System.out.println("##################################");
            System.out.println(" -=-+-=- Final Stats -=-+-=-  ");
            System.out.println("Day(s) Survived: " + finalDay);
            System.out.println("Students Alive: " + students.getLiveCount());
            System.out.println("Units of Food left: " + students.getFoodCount());
            System.out.println("Collective Ammo left: " + students.getAmmo());
            System.out.println("A failed attempt. Game OVER.");

        }

        System.out.println("##################################");
    }

    // Every newDay, stats are relayed and events are called.
    public void newDay() {

      // New Day Messages

        System.out.println(".     .     .     .     .     .   ");
        System.out.println("Dawn of a new day...");
        System.out.println("##################################");
        System.out.println(" -=-+-=- Current Stats -=-+-=-  ");
        System.out.println("Current Day: " + currentDay);
        System.out.println("Students Alive: " + students.getLiveCount());
        System.out.println("Units of Food left: " + students.getFoodCount());
        System.out.println("Collective Ammo left: " + students.getAmmo());
        System.out.println("##################################");
        System.out.println(".     .     .     .     .     .   ");
        Utilities.printMessageStudentsNewDay();

      // New Day Events

        // Use a flare!
        useFlare();

        //
        if(!isSaved) {

            // Students are fed
            feedStudents();

            // Search Outside for supplies
            searchOutside();

            System.out.println("After a long day, night approaches!");

            // Zombies Break Into CBT
            fightSequence();

        }

        finalDay = currentDay;
        currentDay++;

    }

    // Students can utilize a flare if they have one. Using a flare yields a chance
    // for them to be rescued. This is an immediate win if achieved.
    public void useFlare() {

        int awaitingInput = 0;

        if(numFlare >= 1) {

            System.out.println(".     .     .     .     .     .   ");
            System.out.println("The students have a flare! \nWhat shall they do?");
            System.out.println("##################################");
            System.out.println("#     Try to call for help?      #");
            System.out.println("#        1: YES     2: NO        #");
            System.out.println("##################################");

            while (awaitingInput == 0) {

                awaitingInput = input.nextInt();
                boolean incorrectInput = awaitingInput == 0 || awaitingInput > 2;

                if (incorrectInput) {

                    System.out.println("Incorrect answer entered. \nPlease re-enter your answer.");
                    awaitingInput = 0;

                }

                else {

                    System.out.println("Answer selected: " + awaitingInput);

                }

            }

            if(awaitingInput == 1) {

                System.out.println(".     .     .     .     .     .   ");
                System.out.println("The students have decided to use the flare!");
                System.out.println("The flare lights up the sky...");

                numFlare -= 1;
                int helicopterChance = Utilities.generateRandomInRange(1, 2);

                if(helicopterChance == 1) {

                    System.out.println("A few minutes pass...");
                    System.out.println("The students become hopeless.");
                    System.out.println(".     .     .     .     .     .   ");
                    System.out.println("Amazing! A helicopter has answered \nthe distress call! Hope exists.");
                    System.out.println("##################################");
                    System.out.println("A group of heavily armed individuals \nstorm the school grounds!");
                    System.out.println("The students are rescued from this hell!");
                    System.out.println("##################################");

                    finalDay = currentDay;
                    isSaved = true;

                }

                else {

                    System.out.println("A few minutes pass...");
                    System.out.println("The students become hopeless.");
                    System.out.println(".     .     .     .     .     .   ");
                    System.out.println("Nothing happens... Hope is lost.");

                }

            }

            else {

                System.out.println("The students have decided not to use the flare!");
                System.out.println("Shall this be a deadly mistake? \nTime shall tell...");


            }

        }

    }

    // Searches outside the school grounds. Highest chance for food to be found, next is ammo,
    // then a flamethrower, afterwards a flare.
    public void searchOutside() {

        System.out.println(".     .     .     .     .     .   ");
        System.out.println("The students have elected to send \nsomeone out to search for supplies!");
        System.out.println(".     .     .     .     .     .   ");
        System.out.println("Some time has passed...");
        System.out.println(".     .     .     .     .     .   ");

        int searchChance = Utilities.generateRandomInRange(1, 100);
        int foodFound = Utilities.generateRandomInRange(1, 30);
        int ammoFound = Utilities.generateRandomInRange(1, 100);

        if(searchChance <= 50) {

            System.out.println("The student has returned!");
            System.out.println("The student has found " + foodFound + " units of food!");
            students.setFoodCount((students.getFoodCount()) + foodFound);
            System.out.println("Current food count is: " + students.getFoodCount());

        }

        else if(searchChance > 50 && searchChance <= 70) {

            System.out.println("The student has returned!");
            System.out.println("The student has found " + ammoFound + " units of ammo!");
            students.setAmmo((students.getAmmo()) + ammoFound);
            System.out.println("Current ammo count is: " + students.getAmmo());

        }

        else if(searchChance > 70 && searchChance <= 85) {

            System.out.println("The student has returned!");
            System.out.println("The student has found a flamethrower!");
            System.out.println("It can only be used once!");
            numFlamethrower += 1;

        }

        else if(searchChance > 85 && searchChance < 100) {

            System.out.println("The student has returned!");
            System.out.println("The student has found a flare!");
            System.out.println("The students can use this to call for help.");
            System.out.println("You may use this when a new day beings!");
            numFlare += 1;

        }

    }

    // The fight sequence has a few quirks. Besides the two main weapons, a flamethrower may be utilized.
    // It has a chance for defeating more than one zombie.
    // Additionally, depending on the difficulty. The amount of active (or "evolved") zombies changes.
    // On easy there is a higher chance of weaker zombies to be among those who've broken in.
    // These weaker zombies are not a threat and cannot fight, so that'll reduce the amount of
    // zombies that would have to fight the students.
    // There are also extra chances for additional hits on zombies depending on the difficulty.
    public void fightSequence() {

        if(minZombies > maxZombies) {

            System.out.println("ERROR: MIN_ZOMBIES GREATER THAN MAX_ZOMBIES. \nSWITCHING VARIABLES...");
            int tempZombies = maxZombies;
            maxZombies = minZombies;
            minZombies = tempZombies;

        }

        activeZombies = Utilities.generateRandomInRange(minZombies, maxZombies);

        System.out.println(activeZombies + " Zombies have stormed CBT!");

        // Difficulty is used to reduce the amount of zombies one needs to fight
        // Calculations are done based off of min and max zombies.
        // The calculations favor a larger culling of zombies for the easiest
        // difficulties, while less for the harder ones.
        switch (difficulty) {

            case 1:

                if(activeZombies > ((minZombies) + (maxZombies / 4) / 2)) {

                    weakZombieCalculator((minZombies / 2), (maxZombies / 2));

                }

                else {

                    System.out.println("They all are raring to go!");

                }

                break;

            case 2:

                if(activeZombies > ((minZombies) + (maxZombies / 3) / 3)) {

                    weakZombieCalculator((minZombies / 3), (maxZombies / 3));

                }

                else {

                    System.out.println("They all are raring to go!");

                }

                break;
            case 3:

                if(activeZombies > ((minZombies) + (maxZombies / 2) / 4)) {

                    weakZombieCalculator((minZombies / 4), (maxZombies / 4));

                }

                else {

                    System.out.println("They all are raring to go!");

                }

                break;

        }

        System.out.println("A confrontation is inevitable!");
        System.out.println("##################################");

        for (int i = 1; i <= activeZombies; ) {

            // If students are alive and zombies are present, if executes
            if (students.getLiveCount() > 0 && activeZombies > 0) {

                System.out.println("A zombie attacks a student!");

                if (numFlamethrower > 0) {

                    numFlamethrower -= 1;
                    System.out.println("The student uses a flamethrower!");

                    if (hitChance.nextInt(2) == 0) {

                        System.out.println("The flames have spread and struck!");
                        System.out.println("Instant DEATH!");
                        activeZombies -= 1;
                        System.out.println("Number of students left: " + students.getLiveCount());
                        System.out.println("Number of zombies left: " + activeZombies);

                        if((hitChance.nextInt(2) == 0) && activeZombies > 0) {

                            System.out.println("The student also happened to catch \nthe other zombies caught on fire!");
                            int engulfedZombies = Utilities.generateRandomInRange(1,4);

                            if(engulfedZombies > activeZombies) {

                                engulfedZombies = (activeZombies);

                            }

                            System.out.println(engulfedZombies + " engulfed zombies caught on fire and died!");
                            activeZombies -= engulfedZombies;

                            if(activeZombies < 0) {

                                activeZombies = 0;

                            }

                        }

                    }

                    else if (hitChance.nextInt(2) == 0) {

                        System.out.println("The flamethrower didn't work!");

                        if(difficulty == 1) {

                            if (hitChance.nextInt(2) == 0) {

                                System.out.println("The student still keeps it just in case...");
                                numFlamethrower += 1;

                            }

                        }

                        if(difficulty == 2) {

                            if (hitChance.nextInt(3) == 0) {

                                System.out.println("The student still keeps it just in case...");
                                numFlamethrower += 1;

                            }

                        }

                        System.out.println("The student is still alive.");
                        System.out.println("Number of students left: " + students.getLiveCount());
                        System.out.println("Number of zombies left: " + activeZombies);


                    }

                    else {

                        System.out.println("The flamethrower didn't work!");
                        System.out.println("The student was slain!");
                        students.setLiveCount((students.getLiveCount()) - 1);
                        System.out.println("Number of students left: " + students.getLiveCount());
                        System.out.println("Number of zombies left: " + activeZombies);

                    }

                }

                // If students have ammo, they will use a gun
                else if (students.getAmmo() > 0) {

                    // Ammo reduced
                    System.out.println("The student is armed and ready!");
                    students.setAmmo(students.getAmmo() - 1);
                    if(students.getAmmo() < 0) {

                        students.setAmmo(0);

                    }

                    // For the assignment, you may change this to a 1/5 chance for a gun to hit
                    // For balancing, this has been changed to 1/2
                    if (hitChance.nextInt(2) == 0) {

                        // A 1/3 chance for the gun to kill
                        // Changed to 1/2 for balancing
                        if (hitChance.nextInt(2) == 0) {

                            System.out.println("The zombie was shot and died immediately.");
                            activeZombies -= 1;

                            System.out.println("Number of students left: " + students.getLiveCount());
                            System.out.println("Number of zombies left: " + activeZombies);

                        }

                        // Otherwise, the zombie lives if not hit at least twice (or whatever it is balanced to)
                        else {

                            System.out.println("The Zombie was shot, but did not die immediately.");
                            zombieHit += 1;

                            if(difficulty == 1) {

                                if (hitChance.nextInt(2) == 0) {

                                    System.out.println("Huh? It appears the zombie is badly hurt...");
                                    zombieHit += 2;

                                }

                            }

                            if(difficulty == 2) {

                                if (hitChance.nextInt(3) == 0) {

                                    System.out.println("Huh? It appears the zombie is badly hurt...");
                                    zombieHit += 1;

                                }

                            }

                            System.out.println("Number of students left: " + students.getLiveCount());
                            System.out.println("Number of zombies left: " + activeZombies);

                        }

                    }

                    // If the gun misses...
                    else {

                        // A 1/2 chance for the student to survive
                        if (hitChance.nextInt(2) == 0) {

                            System.out.println("The shot missed, but the student survives the fight!");

                            System.out.println("Number of students left: " + students.getLiveCount());
                            System.out.println("Number of zombies left: " + activeZombies);

                        }

                        // or die...
                        else {

                            System.out.println("The shot missed, and the zombie has slain a student!");
                            students.setLiveCount((students.getLiveCount()) - 1);

                            System.out.println("Number of students left: " + students.getLiveCount());
                            System.out.println("Number of zombies left: " + activeZombies);

                        }

                    }

                    // zombieHit increments by one if hit. Therefore if there are 2+ hits, it dies.
                    // Balancing has made this so it is 1+ hits
                    if (zombieHit >= 1) {

                        System.out.println("The zombie has accumulated enough damage from the shots! \nIt has died!");
                        activeZombies -= 1;
                        zombieHit = 0;

                        System.out.println("Number of students left: " + students.getLiveCount());
                        System.out.println("Number of zombies left: " + activeZombies);

                    }

                }

                // If students do not have ammo, they will use a blunt weapon
                else if (students.getAmmo() <= 0) {

                    System.out.println("The student is unarmed and uses a blunt weapon!");

                    // A 1/3 chance for the blunt weapon to hit
                    // Changed to 1/2 for balancing
                    if (hitChance.nextInt(2) == 0) {

                        // A 1/10 chance for the weapon to kill
                        // Changed to 1/3 for balancing
                        if (hitChance.nextInt(3) == 0) {

                            System.out.println("Amazing! The student killed a zombie with a blunt weapon!");
                            activeZombies -= 1;

                            System.out.println("Number of students left: " + students.getLiveCount());
                            System.out.println("Number of zombies left: " + activeZombies);

                        }

                        // Otherwise, the zombie lives if not hit at least three times (or to whatever its balanced to)
                        else {

                            System.out.println("The Zombie was hit by the weapon, but not immediately killed!");
                            zombieHit += 1;

                            System.out.println("Number of students left: " + students.getLiveCount());
                            System.out.println("Number of zombies left: " + activeZombies);

                        }

                    }

                    // A miss means death!
                    // At least under the requirements. Changed so that a miss has a 1/3 chance to survive.
                    else if(hitChance.nextInt(2) == 0) {

                        System.out.println("The swing missed, but the student survived!");

                        System.out.println("Number of students left: " + students.getLiveCount());
                        System.out.println("Number of zombies left: " + activeZombies);

                    }

                    else {

                        System.out.println("The swing missed, the zombie has slain a student!");
                        students.setLiveCount((students.getLiveCount()) - 1);

                        System.out.println("Number of students left: " + students.getLiveCount());
                        System.out.println("Number of zombies left: " + activeZombies);

                    }

                    // zombieHit increments by one if hit. Therefore if there are 3+ hits, it dies.
                    // balanced to 1+ as well
                    if (zombieHit >= 1) {

                        System.out.println("The zombie has accumulated enough damage from the hits! \nIt has died!");
                        activeZombies -= 1;
                        zombieHit = 0;

                        System.out.println("Number of students left: " + students.getLiveCount());
                        System.out.println("Number of zombies left: " + activeZombies);

                    }

                }

            }

            // IF everyone is dead, that's that.
            else if(students.getLiveCount() <= 0) {

                // Ends loop
                i += activeZombies;
                System.out.println("The students have all fought valiantly... and died.");

                System.out.println("Number of students left: " + students.getLiveCount());
                System.out.println("Number of zombies left: " + activeZombies);

            }

            // or if the zombies died...
            else if(activeZombies <= 0) {

                System.out.println("The students have all fought valiantly... and won! \nFor now...");

                System.out.println("Number of students left: " + students.getLiveCount());
                System.out.println("Number of zombies left: " + activeZombies);

            }

            System.out.println("Amount of collective ammo left: " + students.getAmmo());

            System.out.println("##################################");


        }

    }

    // Calculates the amount of weakZombies and subtracts from active ones.
    public void weakZombieCalculator(int min, int max) {

        weakZombies = Utilities.generateRandomInRange(min, max);
        System.out.println("Of the " + activeZombies + " zombies, " + weakZombies + " are too weak to fight!");
        activeZombies = activeZombies - weakZombies;

    }

    // Here, everyone eats every day. If no food is left (negative), it'll just become zero.
    // IF food is also at 0, someone must be eaten to keep the others going... (it's a tough world)
    public void feedStudents() {

        int consumedFood = students.getFoodCount() - students.getLiveCount();

        if(consumedFood > 0) {

            students.setFoodCount(consumedFood);
            System.out.println("The students have to eat!" +"\nCurrent Food count is: " + students.getFoodCount());

        }

        if(consumedFood < 0) {

            consumedFood = 0;
            System.out.println("There is not enough food for the students.");
            System.out.println("The students elected someone to be eaten...");
            students.setLiveCount((students.getLiveCount()) - 1);

        }

        students.setFoodCount(consumedFood);


    }

    // Getters and Setters

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public int getMinZombies() {
        return minZombies;
    }

    public void setMinZombies(int minZombies) {
        this.minZombies = minZombies;
    }

    public int getMaxZombies() {
        return maxZombies;
    }

    public void setMaxZombies(int maxZombies) {
        this.maxZombies = maxZombies;
    }

    public int getActiveZombies() {
        return activeZombies;
    }

    public void setActiveZombies(int activeZombies) {
        this.activeZombies = activeZombies;
    }

    public int getWeakZombies() {
        return weakZombies;
    }

    public void setWeakZombies(int weakZombies) {
        this.weakZombies = weakZombies;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getZombieHit() {
        return zombieHit;
    }

    public void setZombieHit(int zombieHit) {
        this.zombieHit = zombieHit;
    }

    public int getNumFlamethrower() {
        return numFlamethrower;
    }

    public void setNumFlamethrower(int numFlamethrower) {
        this.numFlamethrower = numFlamethrower;
    }

    public int getNumFlare() {
        return numFlare;
    }

    public void setNumFlare(int numFlare) {
        this.numFlare = numFlare;
    }

    public int getFinalDay() {
        return finalDay;
    }

    public void setFinalDay(int finalDay) {
        this.finalDay = finalDay;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public Person getStudents() {
        return students;
    }

    public void setStudents(Person students) {
        this.students = students;
    }

    public Random getHitChance() {
        return hitChance;
    }

    public void setHitChance(Random hitChance) {
        this.hitChance = hitChance;
    }

    @Override
    public String toString() {
        return "Apocalypse{" +
                "numDays=" + numDays +
                ", minZombies=" + minZombies +
                ", maxZombies=" + maxZombies +
                ", activeZombies=" + activeZombies +
                ", weakZombies=" + weakZombies +
                ", currentDay=" + currentDay +
                ", difficulty=" + difficulty +
                ", zombieHit=" + zombieHit +
                ", numFlamethrower=" + numFlamethrower +
                ", numFlare=" + numFlare +
                ", finalDay=" + finalDay +
                ", isSaved=" + isSaved +
                ", input=" + input +
                ", students=" + students +
                ", hitChance=" + hitChance +
                '}';
    }
}
