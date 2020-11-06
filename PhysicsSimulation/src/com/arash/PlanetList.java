package com.arash;

public interface PlanetList<T> {
    boolean add(T item);
    int size();
    T get(int index);
    T remove(int index) throws Exception;
    void add(int index, T item) throws Exception;
}
