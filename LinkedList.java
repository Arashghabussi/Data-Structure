public class LinkedList<E> {
    // variables
    int sizeLen;
    Node<E> headNode;

    // LinkedList function
    public LinkedList(E node) {
        Node<E> v = new Node(node);
        headNode = v;
        sizeLen ++;
    }

    // add function
    public void add(E Node) {
        Node<E> current = headNode;
        while (current.next != null) {
            current = current.next;
        }
        Node<E> x = new Node(Node);
        current.next = x;
        sizeLen++;
    }

    // remove function
    public Node<E> remove(int position) {
        Node<E> previously = headNode;
        Node<E> current = headNode;
        if (position >= sizeLen || position < 0) {
            System.out.println("Not Valid Pos");
        }
        else if (position == 0) {
            headNode = current.next;
            sizeLen--;
        } else {
            for (int i = 0; i < position; i++) {
                previously = current;
                current = current.next;
            }
            previously.next = current.next;
            sizeLen--;
        }
        return current;
    }

    // add function with pos
    public void add(E Node, int position) {
        Node<E> previously = headNode;
        Node<E> current = headNode;
        Node<E> x = new Node(Node);
        if (position > sizeLen + 1 || position < 0) {
            System.out.println("Not Valid Pos");
        }
        else if (position == 0) {
            headNode = x;
            headNode.next = current;
            sizeLen++;
        }
        else if (position == sizeLen - 1) {
            while (current.next != null) {
                current = current.next;
            }
            current.next = x;
            sizeLen++;
        }
        else {
            for (int i = 0; i < position; i++) {
                previously = current;
                current = current.next;
            }
            x.next = previously.next;
            previously.next = x;
            sizeLen++;
        }
    }

    // reverse function
    public void reverse(Node<E> head) {
        Node<E> next = null;
        Node<E> current = head;
        Node<E> previously = null;
        while (current != null) {
            next = current.next;
            current.next = previously;
            previously = current;
            current = next;
        }
        this.headNode = previously;
    }

    // print function
    public void print() {
        Node<E> current = headNode;
        while (current != null) {
            if (current.next != null) {
                System.out.print(current.value + " ==> ");
            }
            else {
                System.out.print(current.value);
            }
            current = current.next;
        }
        System.out.println();
    }

    // get function
    public Node<E> get(int position) {
        Node<E> current = headNode;
        Node<E> previously = headNode;
        if (position >= sizeLen || position < 0) {
            System.out.println("Not Valid Pos");
        }
        else {
            for (int i = 0; i < position + 1; i++) {
                previously = current;
                if (i == sizeLen - 1) {
                    break;
                }
                current = current.next;
            }
        }
        return headNode;
    }

    // node class
    private class Node<E> {
        E value;
        Node<E> next;

        public Node(E val) {
            value = val;
            next = null;
        }
    }

    // main
    public static void main(String[]args) {
        System.out.println(" -- Lab5 LinkedList -- ");
        System.out.println(" -- --------------- -- ");
        LinkedList<Integer> linkedList=new LinkedList<Integer>(5);
        System.out.println(" - add test - ");
        linkedList.add(9);
        linkedList.add(6);
        linkedList.add(3);
        linkedList.add(0);
        System.out.println(" - add pos test - ");
        linkedList.add(3,5);
        linkedList.add(6,6);
        linkedList.add(9,7);
        linkedList.add(12,8);
        System.out.println(" - get test - ");
        System.out.println(linkedList.get(5).value);
        linkedList.print();
        linkedList.remove(7);
        System.out.println(" - remove test - ");
        linkedList.print();
        linkedList.reverse(linkedList.headNode);
        System.out.println(" - reverse test - ");
        linkedList.print();
    }
}

