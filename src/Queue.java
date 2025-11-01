//Joey Barton
//This class will hold the hiking groups (stacks) in line for each trail
//Linked-List Queue was chosen because it is unknown how many groups(stacks) will be in each queue.
//Group placement is random, and there could be any number of hikers at the mountain
//Linked-List allows more freedom with the unknown quantity of hikers and groups.

public class Queue {

    private QueueNode front;
    private QueueNode end;

    public Queue() {
        front = null;
        end = null;
    }

    public void enqueue(Stack s) {
        QueueNode newNode = new QueueNode(s);
        if(front == null) {
            front = newNode;
        } else {
            end.next = newNode;
        }
        end = newNode;
    }

    public Stack dequeue() {
        Stack dequeuedGroup = front.group;
        front = front.next;

        if(front == null) {
            end = null;
        }

        return dequeuedGroup;
    }

    public Stack peek() {
        return front.group;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getLength() {
        QueueNode current = front;
        int counter = 0;
        while (current != null) {
            counter++;
            current = current.next;
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder hikersInQueue = new StringBuilder();
        QueueNode current = front;
        while(current != null) {
            hikersInQueue.append("Group_" + current.getEntranceNumber() + " ");
            current = current.next; 
        }
        return hikersInQueue.toString();
    }

    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;

        if(this.getClass() == obj.getClass()) {
            Queue other = (Queue) obj;
            return getLength() == other.getLength();
        }
        return false;
    }
}
