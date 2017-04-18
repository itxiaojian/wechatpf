package com.sliu.framework.app.calmutil.filter.core;


public class FilterService {
	private Node node = null;

	public FilterService(String[] keywords) {
		node = NodeTree.getNoteTree(keywords);
	}

	public String filter(String str, String replacement) {
		return new DFAWordFilter(node).filterKeyWord$Script(str, replacement);
	}

	public String filter(String str) {
		return filter(str, "*");
	}
}
