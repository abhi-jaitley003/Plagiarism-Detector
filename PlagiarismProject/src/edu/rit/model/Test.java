package edu.rit.model;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args){
		WordsTuple wt = new WordsTuple(3);
		List<String> words1 = new ArrayList<String>();
		words1.add("for");
		words1.add("a");
		words1.add("gun");
		wt.setWords(words1);
		WordsTuple tuple2 = new WordsTuple(3);
		
		List<String> words2 = new ArrayList<String>();
		words2.add("for");
		words2.add("a");
		words2.add("gun");
		tuple2.setWords(words2);
		System.out.println(wt.equals(tuple2));
	}
}
