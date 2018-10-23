package com.zfy.linkedlist;

public interface Stack<E> {
	
	int getSize();//获取栈的大小
	boolean isEmpty();//判断栈是够为空
	void push(E e);//进行压栈操作
	E pop();//进行出站操作
	E peek();//查看栈顶内容

}
