package BasicDS;

public class Stack {

    private Node head;
    private int size;

    // constructor
    public Stack() {
        size = 0;
        head = new Node(null); // STack is LIFO. This null node will be the last node of stack
    }

    // inner class for Node abstraction
    class Node {
        String item;
        Node next;

        Node(String val) {
            this.item = val;
        }
    }

    public void push(String value) {

        Node oldFirst = this.head;
        Node first = new Node(value);
        first.next = oldFirst;
        head = first;
        size++;
    }

    public String pop() {
        String item = head.item;
        head = head.next;
        this.size--;
        return item;
    }

    public int size() {
        return size;
    }


    public static void main(String[] args) {
        Stack test = new Stack();
        test.push("A");
        test.push("B");
        test.push("C");

        System.out.println("Current size:" + test.size());
    }



}

