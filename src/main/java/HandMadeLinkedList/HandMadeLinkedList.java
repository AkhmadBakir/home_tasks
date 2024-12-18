package HandMadeLinkedList;

public class HandMadeLinkedList<T> {

    //реализуйте класс Node


    public static class Node<E> {

        public E data;
        public Node<E> next;
        public Node<E> prev;

        public Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

    }

    /*
     * Указатель на первый элемент списка. Он же first
     */
    public Node<T> head;

    /*
     Указатель на последний элемент списка. Он же last
    */
    public Node<T> tail;

    private int size = 0;

    public void addFirst(T element) {
        // Реализуйте метод
        Node<T> oldHead = head;
        Node<T> newNode = new Node<>(element, oldHead, null);
        head = newNode;
        if (oldHead == null) {
            tail = newNode;
        } else {
            oldHead.prev = newNode;
        }
        size++;
    }

    public T getFirst() {
        // Реализуйте метод
       if (head == null) {
           return null;
       }
       return head.data;
    }

    public void addLast(T element) {
        // Реализуйте метод
        Node<T> oldTail = tail;
        Node<T> newNode = new Node<>(element, oldTail, null);
        tail = newNode;
        if (oldTail != null) {
            oldTail.next = newNode;
        }
        size++;
    }

    public T getLast() {
        // Реализуйте метод
        if (tail == null) {
            return null;
        }
        return tail.data;
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        HandMadeLinkedList<Integer> integers = new HandMadeLinkedList<Integer>();

        integers.addFirst(1);
        integers.addFirst(2);
        integers.addFirst(3);
        integers.addLast(4);
        integers.addLast(5);
        integers.addFirst(1);

        System.out.println(integers.getFirst());
        System.out.println(integers.size());
        System.out.println(integers.getLast());
        System.out.println(integers.size());
    }
}