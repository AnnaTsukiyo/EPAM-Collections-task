package com.efimchick.ifmo.collections;

import java.util.*;
import java.util.stream.IntStream;

/**
 * adding
 * adding by index
 * removing of object
 * removing by index
 * getting by index
 * setting by index
 * adding of a collection
 * adding of a collection by index
 * iterator (removing via iterator is not required)
 */

class PairStringList implements List<String> {

    private String[] data;

    public PairStringList() {
        this.data = new String[0];
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        if (data == null || data.length == 0) return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        int indexToFind = Arrays.binarySearch(data, o);
        return indexToFind >= 0;
    }

    @Override
    public Iterator<String> iterator() {
        return Arrays.stream(data).iterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, data.length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        a = (T[]) Arrays.copyOf(data, data.length);
        return a;
    }

    @Override
    public boolean add(String s) {
        data = Arrays.copyOf(data, data.length + 2);
        data[data.length - 2] = data[data.length - 1] = s;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int pos = getPosition(o);
        //if (data.length - 1 - pos >= 0) System.arraycopy(data, pos + 2, data, pos, data.length - 1 - pos);

        for (int i = pos; i < data.length - 2; i += 2) {
            data[i] = data[i + 2];
            data[i + 1] = data[i + 3];
        }
        data = Arrays.copyOf(data, data.length - 2);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new NoSuchElementException();
    }

    @Override
    public boolean addAll(Collection<? extends String> collection) {

        for (String element : collection) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> collection) {
        for (String element : collection) {
            add(index, element);
            index = getNextIndex(index);
        }
        return true;
    }

    private int getNextIndex(int index) {
        index += 2;
        return index;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new NoSuchElementException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        this.data = new String[0];
    }

    @Override
    public String get(int index) {
        return data[index];
    }

    @Override
    public String set(int index, String element) {
        String oldValue = data[index];
        if (index % 2 != 0) {
            index--;
        }
        data[index] = element;
        data[index+1] = element;
        return oldValue;
    }

    @Override
    public void add(int index, String element) {
        data = Arrays.copyOf(data, data.length + 2);
        if (index % 2 != 0) {
            index++;
        }
        for (int i = data.length-1; i>index+1 ; i-=2) {
            data[i] = data[i-2];
            data[i-1] = data[i-3];
        }
        data[index] =element;
        data[index+1] =element;

    }

    @Override
    public String remove(int index) {
        remove(data[index]);

        return null;

    }

    private int getPosition(Object element) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(element)) return i;
        }
        return -1;
    }


    @Override
    public int indexOf(Object o) {
        return IntStream.range(0, data.length).filter(i -> data[i] == o).findFirst().orElse(-1);
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null && data[i].equals(o)
                    || o == null && data[i] == null) return i;
        }
        return -1;
    }

    @Override
    public ListIterator<String> listIterator() {
        return Arrays.asList(data).listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return Arrays.asList(data).listIterator();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        List<String> list = Arrays.asList(data).subList(fromIndex, toIndex);
        return list;
    }
}
