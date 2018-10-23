package com.zfy.linkedlist;

public interface Queue<E> {

	int getSize();//获取队列size
    boolean isEmpty();//判断队列是否为空
    void enqueue(E e);//入队操作
    E dequeue();//出队操作
    E getFront();//获取 front
}
