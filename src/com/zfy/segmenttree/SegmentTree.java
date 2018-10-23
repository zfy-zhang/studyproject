package com.zfy.segmenttree;

import com.zfy.segmenttree.Merger;

public class SegmentTree<E> {

	private E[] tree;//声明一个树数组
	private E[] data;//声明一个数组
	private Merger<E> merger;
	
	public SegmentTree(E[] arr, Merger<E> merger) {
		
		this.merger = merger;
		
		data = (E[])new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			data[i] = arr[i];
		}
		tree = (E[])new Object[4 * arr.length];
		buildSegmentTree(0, 0, data.length - 1);
	}

	//在treeIndex的位置创建表示区间[l...r]的线段树
	private void buildSegmentTree(int treeIndex, int l, int r) {
		
		//l==r,就是这个里面只有一个节点
		if (l == r) {
			tree[treeIndex] = data[r];
			return;
		}
		
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);

		int mid = l + (r - l) / 2;//区间边界：左边界加上左右边界它们的距离除以2，得到的位置就是中间的位置
		buildSegmentTree(leftTreeIndex, l, mid);
		buildSegmentTree(rightTreeIndex, mid + 1, r);

		tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);//计算treeIndex
		
	}

	//获取个数
    public int getSize(){
        return data.length;
    }

    //按照index获取元素
    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }
    
    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index + 1;//是以数组以0为开始的计算
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index + 2;
    }

	//线段树查询方法，返回区间[queryL, queryR]的值
	public E query(int queryL, int queryR) {

		if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
			throw new IllegalArgumentException("Index is illegal.");

		return query(0, 0, data.length - 1, queryL, queryR);
	}

	//在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
	private E query(int treeIndex, int l, int r, int queryL, int queryR) {

		if (l == queryL && r == queryR)
			return tree[treeIndex];

		int mid = l + (r - l) / 2;

		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);

		//如果queryL > mid，则查询其右孩子。反之则为左孩子
		if (queryL > mid + 1) {
			return query(rightTreeIndex, mid + 1, r, queryL, queryR);
		} else if (queryR <= mid) {
			return query(leftTreeIndex, l, mid, queryL, queryR);
		}

		E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
		E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
		return merger.merge(leftResult, rightResult);

	}
	
	//将index位置的值，更新为e
	public void set(int index, E e) {

		if (index < 0 || index >= data.length)
			throw new IllegalArgumentException("Index is illegal");
		data[index] = e;
		set(0, 0, data.length - 1, index, e);

	}

	//在以treeIndex为根的线段树中更新index的值为e
	private void set(int treeIndex, int l, int r, int index, E e) {

		if (l == r) {
			tree[treeIndex] = e;
			return;
		}
		int mid = l + (r - l) / 2;
		//treeIndex的节点分为[l...mid]和[mid+1...r]两部分

		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		if(index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else // index <= mid
            set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append('[');
		for (int i = 0; i < tree.length; i++) {
			if (tree[i] != null)
				res.append(tree[i]);
			else
				res.append("null");

			if (i != tree.length - 1)
				res.append(", ");
		}
		res.append(']');
		return res.toString();
	}

    
}
