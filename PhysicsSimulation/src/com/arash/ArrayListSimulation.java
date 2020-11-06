package com.arash;

// ArrayList
public class ArrayListSimulation<T> implements PlanetList<T> {
    T[] factoryArray;
    int sizeOfA;

    // Const - ArrayListSimulation
    public ArrayListSimulation(int capacity) {
        factoryArray = (T[]) new Object[capacity];
        sizeOfA = 0;
    }

    // size
    @Override
    public int size() {
        return this.sizeOfA;
    }

    // get
    @Override
    public T get(int power) {
        return factoryArray[power];
    }

    // add - simple
    @Override
    public boolean add(T element) {
        if (factoryArray[factoryArray.length - 1] == null) {
            sizeOfA++;
            factoryArray[sizeOfA - 1] = element;
        }
        if (factoryArray[factoryArray.length - 1] != null) {
            grow_array();
        }
        return true;
    }

    // grow array
    public void grow_array() {
        T[] array2 = (T[]) new Object[factoryArray.length * 2];
        for (int k = 0; k < factoryArray.length; k++) {
            array2[k] = factoryArray[k];
        }
        factoryArray = array2;
    }

    // add - index
    @Override
    public void add(int power, T element) {
        if (factoryArray[factoryArray.length - 1] == null && power < factoryArray.length) {
            for (int p = factoryArray.length - 1; p > power; p--) {
                factoryArray[p] = factoryArray[p - 1];
            }
            factoryArray[power] = element;
        }
        if (factoryArray[factoryArray.length - 1] != null) {
            grow_array();
        }
        // Out of range
        if (power >= factoryArray.length || power < 0) {
            System.out.println("OUT OF RANGE!");
        }
    }

    // remove
    @Override
    public T remove(int indicant) {
        T removed = factoryArray[indicant];
        if (indicant >= sizeOfA || indicant < 0) {
            System.out.println("OUT OF RANGE!");
            return null;
        } else {
            for (int a = indicant; a < factoryArray.length - 1; a++) {
                factoryArray[a] = factoryArray[a + 1];
            }
            factoryArray[factoryArray.length - 1] = null;
            sizeOfA--;
            return removed;
        }
    }

    @Override
    public String toString() {
        // start
        String ans = " ";
        ans = ans + '{';
        // looping
        for (int counter = 0; counter < factoryArray.length - 1; counter++) {
            ans += factoryArray[counter] + ", ";
        }
        // end
        ans = ans + factoryArray[factoryArray.length - 1];
        ans = ans + '}';
        // return
        return ans;
    }
}