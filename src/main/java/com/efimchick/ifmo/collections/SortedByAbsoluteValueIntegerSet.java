package com.efimchick.ifmo.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


class SortedByAbsoluteValueIntegerSet implements Set<Integer> {
    private Integer[] numbers;

    @Override
    public int size() {
        return numbers.length;
    }

    public SortedByAbsoluteValueIntegerSet() {
        numbers = new Integer[0];
    }

    @Override
    public boolean isEmpty() {
        if (numbers == null || numbers.length == 0) return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i].equals(o)){
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Arrays.stream(numbers).iterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(numbers, numbers.length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        a = (T[]) Arrays.copyOf(numbers, numbers.length);
        return a;
    }

    @Override
    public boolean add(Integer integer) {
        if (contains(integer)) {
            return false;
        }
        numbers = Arrays.copyOf(numbers, numbers.length + 1);
        if(numbers.length==1){
            numbers[0] = integer;
        }else{
            int index = getInsertPosition(integer);
            for (int i = numbers.length - 1; i > index; i--) {
                numbers[i] = numbers[i - 1];
            }
            numbers[index] = integer;
        }

        return true;
    }



    private int getInsertPosition(Integer integer) {
        for (int i = 0; i < numbers.length-1; i++) {
            if (Math.abs(numbers[i]) > Math.abs(integer)) {
                return i ;
            }
        }
        return numbers.length-1;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }

        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i].equals(o)){
                for (int j = i; j < numbers.length-1; j++) {
                    numbers[j] = numbers[j+1];
                }

                numbers = Arrays.copyOf(numbers, numbers.length-1);
            }
        }


        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        boolean isInserted = false;

        for (Integer value:c){
            if(add(value)){
                isInserted = true;
            }
        }

        return isInserted;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        this.numbers = null;
    }
}
