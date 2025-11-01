//Joey Barton
//This class takes the number of hikers in its constructor and creates that many hikers

import java.util.ArrayList;

public class Hikers {

    private int totalNumOfHikers;
    private ArrayList<Hiker> allHikers = new ArrayList<Hiker>();

    public Hikers(int numOfHikers){
        this.totalNumOfHikers = numOfHikers;
        allHikers = createAllHikers();
    }

    public ArrayList<Hiker> createAllHikers() {
        for(int i = 1; i <= totalNumOfHikers; i++) {
            String hikerName = "hiker";
            Hiker hiker = new Hiker(hikerName + i);
            allHikers.add(hiker);
        }
        return allHikers;
    }

    public ArrayList<Hiker> getAllHikers() {
        return allHikers;
    }

    @Override
    public String toString() {

        StringBuilder allPersons = new StringBuilder();
        for(Hiker person : allHikers) {
            allPersons.append(person.getName()).append("\n");
        }
        return allPersons.toString();
    }
}
