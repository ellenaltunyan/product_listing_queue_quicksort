public class Queue {
    private Node front;
    private Node rear;
    private int size;

    private static class Node {
        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void add(String element) {
        Node newNode = new Node(element);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Added: " + element);
    }

    public String remove() {
        if (front == null) {
            System.out.println("Queue is empty.");
            return null;
        }

        String removedData = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }

        size--;
        System.out.println("Removed: " + removedData);
        return removedData;
    }

    public void printAll() {
        if (front == null) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Queue elements (front to rear):");
        Node current = front;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean search(String element) {
        Node current = front;
        while (current != null) {
            if (current.data.equals(element)) {
                System.out.println("Element found: " + element);
                return true;
            }
            current = current.next;
        }

        System.out.println("Element not found: " + element);
        return false;
    }

    public static Queue quickSort(Queue queue) {
        if (queue.size() <= 1) {
            return queue;
        }

        String pivot = queue.remove();

        Queue less = new Queue();
        Queue greater = new Queue();

        while (queue.size() > 0) {
            String current = queue.remove();
            if (current.compareTo(pivot) < 0) {
                less.add(current);
            } else {
                greater.add(current);
            }
        }

        less = quickSort(less);
        greater = quickSort(greater);

        Queue sortedQueue = new Queue();
        while (less.size() > 0) {
            sortedQueue.add(less.remove());
        }
        sortedQueue.add(pivot);
        while (greater.size() > 0) {
            sortedQueue.add(greater.remove());
        }

        return sortedQueue;
    }
}
