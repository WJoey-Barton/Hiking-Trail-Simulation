//Joey Barton
//This class holds the hiker's name "hiker#" and speed attributes

import java.util.Random;

public class Hiker {

    private String name;
    private int speed;

    public Hiker (String hikerName) {
        this.name = hikerName;
        Random rand = new Random();
        this.speed = rand.nextInt(4);
    }

    public String getName() {
        return this.name;
    }

    public int getSpeed() {
        return this.speed;
    }

    //The initial speed is set randomly
    //The first two groups of hikers will have a slight speed increase
    public void setSpeed(int increase) {
        this.speed = this.speed + increase;
    } 

    public String toString() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;

        if(this.getClass() == obj.getClass()) {
            Hiker other = (Hiker) obj;
            return name.equals(other.getName()) && speed == other.getSpeed();
        }
        return false;
    }
}
