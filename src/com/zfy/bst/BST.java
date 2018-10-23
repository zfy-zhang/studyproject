package com.zfy.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 *对于二分搜索树，也支持泛型，因为其不支持所有的类型，对这样的类型有一个限制，这个限制就是这个类型必须要有可比较性 ,所以我们实现了Comparable类
 * */
public class BST<E extends Comparable<E>> {

	private class Node {
		public E e;
		public Node left, right;// 指向子树的链接

		public Node(E e) {
			this.e = e;
			left = null;
			right = null;
		}
	}

	private Node root; // 根节点
	private int size; // 存储的总数

	public BST() {
		root = null;
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// 向二分搜索树中添加新的元素e
	public void add(E e) {
		root = add(root, e);
	}

	// 向以node为根的二分搜索树中插入元素e，递归算法,这样的递归设计是为了让代码更简洁
	// 返回插入新节点后二分搜索树的根
	private Node add(Node node, E e) {

		if (node == null) {
			size++;
			return new Node(e);
		}

		if (e.compareTo(node.e) < 0) {
			node.left = add(node.left, e);
		} else if (e.compareTo(node.e) > 0) {

		} else {
			node.right = add(node.right, e);
		}

		return node;
	}

	// 看二分搜索树中是否包含元素e
	public boolean contains(E e) {
		return conrains(root, e);
	}

	// 看以node为根的二分搜索树中是否包含元素e, 递归算法
	private boolean conrains(Node node, E e) {

		if (node == null)
			return false;
		if (e.compareTo(node.e) == 0) {
			return true;
		} else if (e.compareTo(node.e) < 0) {
			return conrains(node.left, e);
		} else {
			return conrains(node.right, e);
		}
	}

	// 二分搜索树的前序遍历
	public void preOrder() {
		preOrder(root);
	}

	// 前序遍历以node为根的二分搜索树, 递归算法
	private void preOrder(Node node) {
		if (node == null)
			return;

		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}

	//二分搜索树的非递归前序遍历
    public void preOrderNR(){
    	
    	Stack<Node> stack = new Stack<>();
    	stack.push(root);
    	while (!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.println(cur.e);
			
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
    }
    
	//二分搜索树的中序遍历
    public void inOrder(){
        inOrder(root);
    }

    //中序遍历以node为根的二分搜索树, 递归算法
    private void inOrder(Node node){
        if(node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //二分搜索树的后序遍历
    public void postOrder(){
        postOrder(root);
    }

    //后序遍历以node为根的二分搜索树, 递归算法
    private void postOrder(Node node){
        if(node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }
    
    //二分搜索树的层序遍历
    public void levelOrder(){
    	
    	if (root == null) {
			return;
		}
    	
    	Queue<Node> q = new LinkedList<>();
    	q.add(root);
    	while (!q.isEmpty()) {
			Node cur = q.remove();
			System.out.println(cur.e);
			if (cur.left != null) {
				q.add(cur.left);
			}
			if (cur.right != null) {
				q.add(cur.right);
			}
			
		}
    }

    // 寻找二分搜索树的最小元素
    public E minimum(){

    	if(size == 0)
            throw new IllegalArgumentException("BST is empty");
    	
    	return minimum(root).e;
    }
    
    //返回以node为根的二分搜索树的最小值所在的节点
	private Node minimum(Node node) {
		
		if (node.left == null) {
			return node;
		}
		return minimum(node.left);
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		generateBSTString(root, 0, res);
		return res.toString();
	}
	
	//寻找二分搜索树的最大元素
    public E maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root).e;
    }

    //返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node){
        if( node.right == null )
            return node;

        return maximum(node.right);
    }

    //从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin(){
    	
    	E ret = minimum();
    	root = removeMin(root);
    	return ret;
    }
    
    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {
		
		if (node.left == null) {
			Node rightNode = node.right;//如果当前这个最小节点还有有孩子，那么先将它保存
			node.right = null;
			size --;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	//从二分搜索树中删除最大值所在节点
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最大节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){

        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }
    
    // 从二分搜索树中删除元素为e的节点
    public void remove(E e){
    	root = remove(root, e);
    }
    
    //删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    //返回删除节点后新的二分搜索树的根
	private BST<E>.Node remove(Node node, E e) {
		
		if (node == null) {
			return null;
		}
		if (e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
			return node;
		}else if (e.compareTo(node.e) > 0) {
			node.right = remove(node.right, e);
			return node;
		}else {
			
			//待删除节点左子树为空的情况
			if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            
            // 待删除节点左右子树均不为空的情况,俗称：hibbard deletion
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
		}
	}

	//生成以node为根节点，深度为depth的描述二叉树的字符串
	private void generateBSTString(Node node, int depth, StringBuilder res) {
		
		if (node == null) {
			res.append(generateDepthString(depth) + "null\n");
            return;
		}
		
		res.append(generateDepthString(depth) + node.hashCode() + "\n");
		generateBSTString(node.left, depth+1, res);
		generateBSTString(node.right, depth+1, res);
	}

	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < depth; i++) 
			res.append("--");
		return res.toString();
	}
}
