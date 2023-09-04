class Node {
    Board data;
    Node next;

    Node(Board data) {
        this.data = data;
    }

}


public class queue {
    Node head;
    Node tail;
    
    public void enqueue(Board data) {
        Node newNode = new Node(data);
        if(tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    
    public Board dequeue() {
        if(head == null) {
            return null;
        }
        
        Board data = head.data;
        head = head.next;
        if(head == null) {
            tail = null;
        }
        
        return data;
    }

    public void printQueue() {
        Node current = head;
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}