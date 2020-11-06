package com.arash;

public class LinkedListSimulation<T> implements PlanetList<T> {

    int totalSize;
    Node<T> tailOfNode;
    Node<T> headOfNode;

    // const - LinkedSimulation
    public LinkedListSimulation() {
        headOfNode = tailOfNode = null;
        totalSize = 0;
    }

    // add
    @Override
    public boolean add(T itemN) {
        Node<T> node = new Node(itemN);
        // if null
        if (headOfNode == null) {
            headOfNode = node;
            tailOfNode = node;
            totalSize = 1;
        }
        // not null
        else {
            tailOfNode.next = node;
            tailOfNode = node;
            totalSize++;
        }
        return true;
    }

    // add index
    @Override
    public void add(int pos, T content) {
        Node factNode = new Node(content);

        // if null same as simple add
        if (headOfNode == null) {
            headOfNode = factNode;
            tailOfNode = factNode;
            totalSize = 1;
        }

        // not null follow the process
        else {
            int counter = 0;
            // if pos is 0
            if (pos == 0) {
                factNode.next = headOfNode;
                headOfNode = factNode;
                totalSize++;
            }
            // if it's 1
            else if (pos == 1) {
                factNode.next = headOfNode.next;
                headOfNode.next = factNode;
                totalSize++;
            }
            // not 0 or 1 then:
            else {
                Node prevNode = null;
                for (Node curNode = headOfNode; curNode != null; curNode = curNode.next) {
                    if (counter == pos - 1) {
                        prevNode = curNode;
                    }
                    else if (counter == pos) {
                        prevNode.next = factNode;
                        factNode.next = curNode;
                        totalSize++;
                    }
                }
            }
        }
    }

    // get
    @Override
    public T get(int pos) {
        if (headOfNode == null || totalSize <= pos) {
            return null;
        }
        else {
            Node<T> nodeCurr = headOfNode;
            for (int h = 0; h < pos; h++) {
                if (h == pos) {
                    return nodeCurr.dataSet;
                }
                nodeCurr = nodeCurr.next;
            }
            return nodeCurr.dataSet;
        }
    }

    // remove
    @Override
    public T remove(int pos) {
        if (totalSize <= pos || headOfNode == null) {
            return null;
        }
        else if (pos == 0) {
            Node<T> holdMem = headOfNode;
            headOfNode = headOfNode.next;
            totalSize--;
            return holdMem.dataSet;
        }
        else {
            Node<T> currentNode = headOfNode;
            Node<T> previousNode = null;

            for(int f = 0; f <= pos; f++) {
                if (f == pos - 1) {
                    previousNode = currentNode;
                }
                else if (f == pos) {
                    if (pos == totalSize - 1) {
                        previousNode.next = null;
                        tailOfNode = previousNode;
                    }
                    else {
                        previousNode.next = currentNode.next;
                        currentNode.next = null;
                    }
                    totalSize--;
                    return currentNode.dataSet;
                }
                currentNode = currentNode.next;
            }
            totalSize--;
            return currentNode.dataSet;
        }
    }

    // size
    @Override
    public int size() {
        return this.totalSize;
    }

    // node
    private class Node<T> {
        T dataSet;
        Node next;

        public Node(T dataSet) {
            this.dataSet = dataSet;
            next = null;
        }
    }

    // toString
    private String toString(Node nextNode) {
        if (nextNode == null) {
            return " ";
        }
        else {
            if (nextNode.next != null) {
                return nextNode.dataSet + "==>" + toString(nextNode.next);
            }
            else {
                return " " + nextNode.dataSet;
            }
        }
    }

    // toString
    @Override
    public String toString() {
        return toString(this.headOfNode);
    }
}