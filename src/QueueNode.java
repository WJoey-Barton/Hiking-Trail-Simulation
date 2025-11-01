//Joey Barton
//This class will hold the information of one hiking group (stack)

public class QueueNode {

    public Stack group;
    public QueueNode next;

    public QueueNode(Stack dataGroup) {
        this(dataGroup, null);
    }

    public QueueNode(Stack dataGroup, QueueNode nextNode) {
        this.group = dataGroup;
        this.next = nextNode;
    }

    public Stack getGroup() {
        return this.group;
    }

    public int getEntranceNumber() {
        return this.group.getEntranceNumber();
    }

    public String toString() {
        return "Group_" + getEntranceNumber(); 
    }

    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;

        if(this.getClass() == obj.getClass()) {
            QueueNode other = (QueueNode) obj;
            return getGroup() == other.getGroup();
        }
        return false;
    }
}
