package com.fork52.queue;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 *  Examples:
 *  MonoQueue<Integer> mxq = new MonoQueue<>(arr, Collections.reverseOrder());
 *  MonoQueue<Integer> mnq = new MonoQueue<>(arr, Integer::compare);
 */
public class MonoQueue<T>{
    private final Comparator<T> comparable;
    private final Deque<Integer> dq;
    private final List<T> arr;

    public MonoQueue(List<T> arr, Comparator<T> comparable){
        this.comparable = comparable;
        this.dq = new LinkedList<>();
        this.arr = arr;
    }

    public void add(int index){
        while(!dq.isEmpty() &&
            comparable.compare(arr.get(dq.getLast()), arr.get(index)) >= 0
        ){
            dq.removeLast();
        }
        dq.addLast(index);
    }

    public void removeElementsBefore(int index){
        while(!dq.isEmpty() && dq.getFirst() <= index){
            dq.removeFirst();
        }
    }

    public T getBest(){
        return arr.get(dq.getFirst());
    }
}
