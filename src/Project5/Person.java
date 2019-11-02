package Project5;

/**Zombie Project
 * CSci 2001-91
 */

/* Nothing special in this class. Just data regarding the ammo available and current count of food/students.
*  They all will automatically change with the constants in utilities. */

public class Person {

    // Instanced variables reliant on constants from Utilities
    int ammo, liveCount, foodCount;

    public Person() {

        liveCount = Utilities.START_LIVE_COUNT;;
        ammo = (Utilities.START_AMMO * liveCount); // Collective ammo of all students
        foodCount = Utilities.START_FOOD_COUNT;

    }

    public int getAmmo() {

        return ammo;

    }

    public void setAmmo(int ammo) {

        this.ammo = ammo;

    }

    public int getLiveCount() {

        return liveCount;

    }

    public void setLiveCount(int liveCount) {

        this.liveCount = liveCount;

    }

    public int getFoodCount() {

        return foodCount;

    }

    public void setFoodCount(int foodCount) {

        this.foodCount = foodCount;

    }

    @Override
    public String toString() {

        return "Person{" +
                "ammo=" + ammo +
                ", liveCount=" + liveCount +
                ", foodCount=" + foodCount +
                '}';

    }

}
