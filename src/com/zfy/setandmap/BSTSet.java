package com.zfy.setandmap;

import com.zfy.bst.BST;

/*
 * 这里是基于之前的二分搜索树实现的，
 * 1.因为集合具有去重的功用，说明它是可以有比较的功能的，所以这里实现了Comparable类，
 * 2.因为集合是可以存各种的数据类型，因此这里设计为泛型
 * */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

	private BST<E> bst;
	
	//所有的BSTSet都是基于BST的
	public BSTSet() {
		bst = new BST<>();
	}
	
	@Override
	public void add(E e) {
		bst.add(e);
	}

	@Override
	public boolean contains(E e) {
		return bst.contains(e);
	}

	@Override
	public void remove(E e) {
		bst.remove(e);
	}

	@Override
	public int getSize() {
		return bst.getSize();
	}

	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}
	
	

}
