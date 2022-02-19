package com.efimchick.ifmo.collections;

import java.util.*;

/**
 * Implement a queue of Integer that returns its median element.
 * offer - push element to the queue
 * poll - pull element out of the queue
 * peek - get element on the top of the queue (just get, no pulling out)
 * iterator - iterate over elements of the queue (no need to keep order)
 * size - just amount of current queue elements
 */

class MedianQueue implements Queue<Integer> {

    private Integer[] medianQueue;

    public MedianQueue() {
        medianQueue = new Integer[0];
    }


    @Override
    public int size() {
        return medianQueue.length;
    }

    @Override
    public boolean isEmpty() {

        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        int indexToFind = Arrays.binarySearch(medianQueue, o);
        return indexToFind >= 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Arrays.stream(medianQueue).iterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(medianQueue, medianQueue.length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Arrays.sort(medianQueue);
        a = (T[]) Arrays.copyOf(medianQueue, medianQueue.length);
        return a;
    }

    @Override
    public boolean add(Integer integer) {
        medianQueue = Arrays.copyOf(medianQueue, medianQueue.length + 1);
        medianQueue[medianQueue.length - 1] = integer;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < medianQueue.length; i++)
                if (medianQueue[i] == null) {
                    return true;
                }
        } else {
            for (int i = 0; i < medianQueue.length; i++)
                if (o.equals(medianQueue[i])) {
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new NoSuchElementException();
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        throw new NoSuchElementException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new NoSuchElementException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        this.medianQueue = null;
    }

    @Override
    public boolean equals(Object o) {
        throw new NoSuchElementException();
    }

    @Override
    public int hashCode() {
        throw new NoSuchElementException();
    }

    @Override
    public boolean offer(Integer integer) {
        add(integer);
        return true;
    }

    @Override
    public Integer remove() {
        Arrays.sort(medianQueue);

        Integer result = 0;
        int size = this.medianQueue.length;
        int index = size / 2;


        if (size % 2 != 0) {
            result = medianQueue[index];
        } else {
            index--;
            result = medianQueue[index];
        }

        for (int i = index; i < medianQueue.length - 1; i++) {
            medianQueue[i] = medianQueue[i + 1];
        }

        medianQueue = Arrays.copyOf(medianQueue, medianQueue.length - 1);

        return result;
    }

    @Override
    public Integer poll() { //For instance, if you put 1, 2, 3, 4, 5 to the queue and then pull element of it, queue will return 3.
        return remove();
    }

    @Override
    public Integer element() {
        return null;
    }

    @Override
    public Integer peek() {
        Arrays.sort(medianQueue);
        Integer result = 0;
        int size = this.medianQueue.length;
        int index = size / 2;


        if (size % 2 != 0) {
            result = medianQueue[index];
        } else {
            index--;
            result = medianQueue[index];
        }

        return result;
    }

    private void rearrangeElements() {
        int temp[] = new int[medianQueue.length];


        for (int i = 0; i < medianQueue.length; i++) {

        }
    }

    private boolean isEven(int value) {
        return value % 2 == 0;
    }
}

