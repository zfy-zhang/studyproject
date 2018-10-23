package com.zfy.setandmap;

public class LinkedListMap<K, V> implements Map<K, V> {
	
	/*
	 * 因为前面的链表只能承载一个元素e，因此对于这里的映射类，我们需要重新重新实现Node了
	 * */
    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value){
            this(key, value, null);
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }


    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }
    
    //传来一个k的值，返回这个k所对应的节点的引用，后面的增删改查都将借助于这个方法
    private Node getNode(K key){
    	Node cur = dummyHead.next;//cur对应dummyHead的next节点，就是第一个元素
    	//循环遍历判断cur是否包含传入的key，如果包含则返回cur，否则继续遍历
    	while (cur != null) {
			if (cur.equals(key)) {
				return cur;
			}
			cur = cur.next;
		}
    	return null;
    }
    
	@Override
	public void add(K key, V value) {
		Node node = getNode(key);//首先调用这个方法，然后判断返回后的当前节点中是否包含添加所传入的key键所对应的数据，如果没有则添加，有则覆盖
		if (node == null) {
			dummyHead.next = new Node(key, value, dummyHead.next);
			size ++;
		}else {
			node.value = value;
		}
	}

	//此方法发与之前的类似
	@Override
	public V remove(K key) {
		Node prev = dummyHead;
		while (prev.next != null) {
			if (prev.next.key.equals(key)) {
				break;
			}
			prev = prev.next;
		}
		if (prev != null) {
			Node delNode = prev.next;
			prev.next = delNode.next;
			delNode.next = null;
			size --;
			return delNode.value;
		}
		return null;
	}

	@Override
	public boolean contains(K key) {
		return getNode(key) != null;
	}

	@Override
	public V get(K key) {
		Node node = getNode(key);//先调用getNode，返回所对应的节点，然后对其进行判断
		return node == null ? null :node.value;
	}

	@Override
	public void set(K key, V newValue) {
		Node node = getNode(key);
		if (node == null) {
			throw new IllegalArgumentException(key + " doesn't exist!");
		}else {
			node.value = newValue;
		}
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}
