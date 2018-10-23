package com.zfy.trie;

import java.util.TreeMap;

/*
 * 这里的Trie是基于java的内部类TreeMap
 * */
public class Trie {

	private class Node {

		// 用来描述当我梦幻访问到当前的Node时，是否就已经找到了一个单词
		public boolean isWord;
		// 对于每一个节点要有向下一个节点的映射，因为Trie对于每一个节点向下指向多少个节点是不定的，所以这样的映射是从Character一直到Node这样的一个映射，这里设计为Character，但这仅仅是一种假设，因为这里仅仅是限制于英文的数据类型
		public TreeMap<Character, Node> next;

		public Node(boolean isWord) {
			this.isWord = isWord;
			next = new TreeMap<>();
		}

		public Node() {
			this(false);
		}
	}

	private Node root;// Trie的根节点
	private int size;// Trie中的档次数量

	// Trie的构造函数
	public Trie() {
		root = new Node();
		size = 0;
	}

	// 获得Trie中存储的单词数量
	public int getSize() {
		return size;
	}

	// 向Trie中添加一个新的单词word
	public void add(String word) {

		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			// 检查cur是否已经有指向c这个字符相应的节点,如果没有才会新创建一个节点
			if (cur.next.get(c) == null) {
				cur.next.put(c, new Node());
			}
			cur = cur.next.get(c);
		}

		// 判断cur是否已经在Trie中了，如果不在，才设置isWord为true
		if (!cur.isWord) {
			cur.isWord = true;
			size++;
		}
	}

	// 查询单词word是否在Trie中
	public boolean contains(String word) {

		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (cur.next.get(c) == null) {
				return false;
			}
			cur = cur.next.get(c);
		}
		return cur.isWord;
	}

	// 查询是否在Trie中有单词以prefix为前缀
	public boolean isPrefix(String prefix) {

		Node cur = root;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if (cur.next.get(c) == null)
				return false;
			cur = cur.next.get(c);
		}

		return true;
	}
}
