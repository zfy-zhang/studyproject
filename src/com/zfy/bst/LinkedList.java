package com.zfy.bst;

public class LinkedList<E> {
	
	//节点内部类
	private class Node {
		public E e;//存放元素
		public Node next;//存放指向next的node
		
		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}
		
		public Node(E e) {
			this(e, null);
		}
		
		public Node() {
			this(null, null);
		}
		
		@Override
		public String toString() {
			return e.toString();
		}
	}
	
	private Node dummyHead;//表头，设置一个虚拟头结点，只是为了编写程序方便而设置的，对用户是没有意义的，对用户也是屏蔽的
	private int size;
	
	public LinkedList() {
		dummyHead = new Node(null, null);
		size = 0;
	}
	
	//获取链表中的元素个数
	public int getSize() {
		return size;
	}
	
	//返回链表是否为空
	public boolean isEmpty() {
		return getSize() == 0;
	}
	
	// 在链表的index(0-based)位置添加新的元素e
    // 在链表中这不是不是一个常用的操作，仅练习用
	public void add(int index, E e){
		if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
		
		Node prev = dummyHead;
		//遍历到待插入节点的前一个位置(因为这里设置的dummyHead是为null的，而dummyHead是这个位置的元素的前一个节点，所以我们此时遍历到index的位置)
		for (int i = 0; i < index; i++) 
			prev = prev.next;
//			Node node = new Node(e);
//			node.next = prev.next;//将prev.next赋给待插入节点的next,是为了指向下一个元素
//			prev.next = node;//将新的节点node指向prev.next 
		//上面代码更优雅的一个写法
		prev.next = new Node(e, prev.next);
		size ++;
	}
	
	//在链表头添加新的元素e
	public void addFirst(E e) {
		add(0, e);
	}
	
	
	// 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }
	
    // 获得链表的第index(0-based)个位置的元素
    // 在链表中不是一个常用的操作，仅练习用
    public E get(int index) {
    	if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
    	
    	Node cur = dummyHead.next;//当前元素从dummyHead.next开始，也就是从链表的第一个位置开始
    	for (int i = 0; i < index; i++) 
    		cur = cur.next;
    	return cur.e;
    }
    
    //获得链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    //获得链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    //修改链表的第index(0-based)个位置的元素为e
    //在链表中不是一个常用的操作，仅练习用
    public void set(int index, E e){
    	if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index.");
    	Node cur = dummyHead.next;
    	for (int i = 0; i < index; i++)
    		cur = cur.next;
    	cur.e = e;
    }
    
    //查找链表中是否有元素e
    public boolean contains(E e){
    	
    	Node cur = dummyHead.next;
    	while (cur != null) {
			if (cur.e.equals(e)) 
				return true;
			cur = cur.next;//如果cur.e.equals(e)	，就返回true，否则看下一个节点
		}
    	return false;
    }

    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    // 在链表中不是一个常用的操作，练习用：）
    public E remove(int index){
    	
    	if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

    	Node prev = dummyHead;
    	for (int i = 0; i < index; i++) 
			prev = prev.next;//删除index节点的元素就是先找到待删除节点的前一个位置然后做相应的 删除操作
    	
    	Node retNode = prev.next;//找到当前待删除的节点
    	prev.next = retNode.next;//把当前待删除节点的next赋给prev.next
    	retNode.next = null;//最后把retNode.next置为null
    	size --;
    	return retNode.e;
    }
    
    //从链表中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    //从链表中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }
    
    //从链表中删除元素e
    public void removeElement(E e){

        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder res = new StringBuilder();
//    	Node cur = dummyHead.next;
//    	while (cur != null) {
//			res.append(cur + "->");
//			cur = cur.next;
//		}
    	for(Node cur = dummyHead.next ; cur != null ; cur = cur.next)
            res.append(cur + "->");
    	res.append("NULL");
    	
    	return res.toString();
    }
}
