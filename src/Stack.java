//Joey Barton
//This class holds the hikers in their group. Each stack is one group.
//Each group will have an entrance number and a trail prefix that will say which trail they are hiking.
//Array Stack was chosen because each hiking group has a max capacity. 
//It is known how many hikers are in a hiking group before the stack is created

public class Stack {

    private Hiker[] array;
    private int capacity = 10;
    private int top;
    private int numOfHikers = 0;
    private int entranceNumber = 0;
    private String trailPrefix = "";

    //Constructor for majority of hikers
    public Stack(int entranceNum) {
        this.entranceNumber = entranceNum;
        this.numOfHikers = capacity;
        this.array = new Hiker[capacity];
        this.top = - 1;
    }

    //Constructor for final remainder of hikers
    public Stack(int entranceNum, int numberOfHikers) {
        this.entranceNumber = entranceNum;
        this.numOfHikers = numberOfHikers;
        this.array = new Hiker[numOfHikers];
        this.top = - 1;
    }

    public void push(Hiker h) {
        if(top == capacity - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        array[++top] = h;
    }

    public Hiker pop() {
        if(top == - 1) {
            System.out.println("Stack is empty");
            return null;
        }
        return array[top--];
    }

    public Hiker peek() {
        if(top == - 1) {
            System.out.println("Stack is empty");
            return null;
        }

        return array[top];
    }

    public boolean isEmpty() {
        return top == - 1;
    }

    public boolean isFull() {
        return top == capacity;
    }

    public int getEntranceNumber() {
        return this.entranceNumber;
    }

    public int getNumberOfHikers() {
        return this.numOfHikers;
    }

    public void setTrailPrefix(String trail) {
        this.trailPrefix = trail;
    }

    public String getTrailPrefix() {
        return this.trailPrefix;
    }

    public String toString() {
        for(Hiker hiker : array) {
            return hiker.getName();
        }
        return null;
    }

    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;

        if(this.getClass() == obj.getClass()) {
            Stack other = (Stack) obj;
            return entranceNumber == other.entranceNumber;
        }
        return false;
    }
    
}
