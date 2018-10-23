package com.zfy.setandmap;

public interface Set<E> {

    void add(E e);//不能添加重复元素
    boolean contains(E e);
    void remove(E e);
    int getSize();
    boolean isEmpty();

}

