package com.zfy.segmenttree;

/*
 * 计算treeIndex的接口
 * */
public interface Merger<E> {
	
	E merge(E a, E b);

}
