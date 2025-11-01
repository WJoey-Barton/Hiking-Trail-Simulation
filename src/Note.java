//Joey Barton
//This class will hold the information needed to write the hiker's information to a .txt file

public class Note {

    private Hiker hiker;
    private Stack hikingGroup;

    //This constructor is for when we only want the name of the hiker
    public Note(Hiker h) {
        this.hiker = h;
    }

    //This constructor will provide more information about the hikers
    //Group number (Entrance number), as well as which path they are hiking
    public Note(Hiker h, Stack s) {
        this.hiker = h;
        this.hikingGroup = s;
    }

    public String getName() {
        return hiker.getName();
    }

    public String getPrefix() {
        return hikingGroup.getTrailPrefix();
    }

    public int getEntranceNumber() {
        return hikingGroup.getEntranceNumber();
    }

    public Hiker getHiker() {
        return this.hiker;
    }

    public int getHikerSpeed() {
        return this.hiker.getSpeed();
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s%n", getName(), "Group_" + getEntranceNumber(), "Trail: " + getPrefix());
    }

    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;

        if(this.getClass() == obj.getClass()) {
            Note other = (Note) obj;
            return getHiker() == other.getHiker();
        }
        return false;
    }
}
