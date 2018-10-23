package com.zfy.stackorqueues;

public class LoopQueue<E> implements Queue<E> {
	
	private E[] data;//不再使用之前的数组，我们将从底层写起
	private int front;//头
	private int tail;//尾
	private int size;
	
	public LoopQueue(int capacity) {
		data = (E[]) new Object[capacity + 1];
		front = 0;
		tail = 0;
		size = 0;
	}
	
	public LoopQueue() {
		this(10);//定义无参构造函数，设置其初始长度为10
	}
	
	public int getCapacity() {
		return data.length - 1;//
	}
	
	@Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public int getSize(){
        return size;
    }
	
    @Override
    public void enqueue(E e) {
    	
    	if (tail + 1 % data.length == front) {//判断tail+1余当前数组的length是否等于front，如果等于则队列是满的
			resize(getCapacity() * 2);//这里使用getCapaCity()，是因为我们前面设置的数组length可以减了一个1
		}
    	data[tail] = e;
    	tail = (tail + 1) % data.length;//本来是tail++;但是由于是循环数组，所以需要这样写
    	size ++;
    }
    
    @Override
    public E dequeue() {
    	if(isEmpty())
    		throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
    	E ret = data[front];
    	data[front] = null;
    	front = (front + 1) % data.length;//本来是tail++;但是由于是循环数组，所以需要这样写
    	size --;
    	if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {//为了减少空间浪费，当size为getCapaCity() / 4时，进行缩容操作，且getCapaCity() / 2不能为零
			resize(getCapacity() / 2);
		}
    	return ret;
    }
    
    @Override
    public E getFront() {
    	if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }
    
    private void resize(int newCapacity){
    	
    	E[] newData = (E[]) new Object[newCapacity+1];//因为前面数组的length-1，所以这里要想容纳就必须要+1，这个和构造函数里的+1是一样的
    	for (int i = 0; i < size; i++) {
			newData[i] = data[(i + front) % data.length];//因为是循环数组，所以，新的data的队首对应的就是(i+front)的这样一个偏移位置，但是由于这是循环数组，(i+front)可能会越界，所以我们需要%data.length一下
		}
    	data = newData;
    	front = 0;
    	tail = size;
    }
    
    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
    
    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
