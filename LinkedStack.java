public class LinkedStack {

    // Node for the linked-list-backed stack
    private static class Node {
        double value;
        Node next;

        Node(double value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node top;

    public LinkedStack() {
        this.top = null;
    }

    // Push a new value onto the top of the stack
    public void push(double value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    // Remove and return the top value; throws if empty
    public double pop() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot pop from an empty stack");
        }
        double value = top.value;
        top = top.next;
        return value;
    }

    // Look at the top value without removing it; throws if empty
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot peek an empty stack");
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    // Display current stack contents, top to bottom
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        Node current = top;
        System.out.print("Stack (top -> bottom): ");
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
}