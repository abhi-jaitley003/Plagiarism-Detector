package edu.rit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WordsTuple {
	private int numberOfWords;
	private List<String> words;
	private String tuple;
	
	public WordsTuple(int numberOfWords){
		this.numberOfWords = numberOfWords;
		words = new ArrayList<String>();
	}
	public WordsTuple(int numberOfWords, List<String> words){
		this.numberOfWords = numberOfWords;
		this.words=words;
	}
	public int getNumberOfWords() {
		return numberOfWords;
	}
	public void setNumberOfWords(int numberOfWords) {
		this.numberOfWords = numberOfWords;
	}
	public List <String> getWords() {
		return words;
	}
	public void setWords(List<String> words) {
		this.words = words;
	}
	public void setTuple(String sentence){
		this.tuple = sentence;
	}
	public String getTuple(){
		return this.tuple;
	}
	@Override
	public int hashCode() { 	
		return this.getTuple().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
	    if(obj == null)                return false;
	    if( !(obj instanceof WordsTuple )) return false;
	    WordsTuple otherWord = (WordsTuple) obj;
	    return this.getTuple().equals(otherWord.getTuple());
	}
	 
}
