package trick;

public class Queue<T> {
    
    private class Node {
        private T item;
        private Node next;

        public Node (T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    // Last Node in the queue, can add items using it
    private Node last;

    public Queue() {
        last = null;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public void enqueue (T toAdd) {
        Node newNode = new Node(toAdd, null);
        if (isEmpty()) {
            // Circular LL, points to itself
            last = newNode;
            last.next = last;
        }
        else {
            // Point newNode to front of queue
            newNode.next = last.next;
            // Point last to newNode
            last.next = newNode;
            // Update last with newNode
            last = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node front = last.next;
        T item = front.item;

        // If q has only 1 element, empty queue
        if (last == last.next) last = null;
        // Update circular LL to remove front
        else {
            last.next = front.next;
        }

        return item;
    }

    public T getFront() {
        return last.next.item;
    }
}
