package edu.rit.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.rit.model.WordsTuple;

public class PlagiarismCheckerService {

	private Map<String,Set<String>> synonymMap ;
	private Map<WordsTuple,Integer> firstFileTuples;
	private Set<WordsTuple> secondFileTuples;
	
	public PlagiarismCheckerService(){
		this.synonymMap = new HashMap<String,Set<String>>();
		this.firstFileTuples = new LinkedHashMap<WordsTuple,Integer>();
		secondFileTuples = new HashSet<WordsTuple>();
	}
	
	public Map<String, Set<String>> getSynonymMap() {
		return synonymMap;
	}
	
	public void constructTupleMapFromFile(String fileName, int N){
		 List<String> fileWords = getWordsFromFile(fileName);
		 for(int i =0; i<=fileWords.size()-N; i++) {
			 List<String> currTupleWords = new ArrayList<String>();
		    	StringBuilder currTupleString =  new StringBuilder("");
		    	String currAppend = null;
		    	for(int j = i; j<i+N; j++){
		    		currAppend = j<i+N-1?fileWords.get(j)+" ":fileWords.get(j);
		    		currTupleString.append(currAppend);
		    		currTupleWords.add(fileWords.get(j));
		    	}
		    	WordsTuple currTuple = new WordsTuple(N, currTupleWords);
		    	currTuple.setTuple(currTupleString.toString());
		    	firstFileTuples.put(currTuple, firstFileTuples.getOrDefault(currTuple, 0)+1);
		  }
		    /*
		     * code to display tuples delete
		     * */
		  for(WordsTuple tup : firstFileTuples.keySet()) {
		        System.out.println(tup.getWords()+"    "+firstFileTuples.get(tup));
		  }
	}
	
	public void constructTupleSetFromFile(String fileName2, int N){
		 List<String> fileWords = getWordsFromFile(fileName2);
		    for(int i =0; i<=fileWords.size()-N; i++) {
		    	List<String> currTupleWords = new ArrayList<String>();
		    	StringBuilder currTupleString =  new StringBuilder("");
		    	String currAppend = null;
		    	for(int j = i; j<i+N; j++){
		    		currAppend = j<i+N-1?fileWords.get(j)+" ":fileWords.get(j);
		    		currTupleString.append(currAppend);
		    		currTupleWords.add(fileWords.get(j));
		    	}
		    	WordsTuple currTuple = new WordsTuple(N, currTupleWords);
		    	currTuple.setTuple(currTupleString.toString());
		    	
		    	// test code
		    	if(secondFileTuples.contains(currTuple)){
		    		System.out.println("duplicate  "+currTuple.getWords());
		    	}
		    	secondFileTuples.add(currTuple);
		    }
			for(WordsTuple tup : secondFileTuples) {
			    System.out.println(tup.getWords());
			}
	}
	
	public double getPecentageMatch(){
		double matchPercentage = 0.0;
		int totalTuples=0;
		int matchTuples = 0;
		for(WordsTuple currTuple : firstFileTuples.keySet()){
			if(secondFileTuples.contains(currTuple)){
				System.out.println("match "+currTuple.getWords()+" count in file : "+firstFileTuples.get(currTuple));
				matchTuples+=firstFileTuples.get(currTuple);
			}
			else{
				
				List<String> currTupleWords = currTuple.getWords();
				for(WordsTuple secondFileTuple : secondFileTuples){
					List<String> secondFileTupleWords = secondFileTuple.getWords();
					int i =0;
					while(i<currTupleWords.size()){
						String firstWord = currTupleWords.get(i);
						String secondWord = secondFileTupleWords.get(i);
						if(!firstWord.equals(secondWord) && 
							(!synonymMap.containsKey(firstWord) || !synonymMap.containsKey(secondWord) 
							|| !synonymMap.get(firstWord).contains(secondWord))){
							break;
						}
						i++;
					}
					if(i == currTupleWords.size()){
						System.out.println("match "+currTuple.getWords()+"  "+secondFileTuple.getWords()+"  "+firstFileTuples.get(currTuple));
						matchTuples+=firstFileTuples.get(currTuple);
						break;
					}
				}
			}
			totalTuples+=firstFileTuples.get(currTuple);
		}
		System.out.println(matchTuples);
		System.out.println(totalTuples);
		matchPercentage = ((double) matchTuples / totalTuples) *100; 
		return matchPercentage;
	}
	
	public List<String> getWordsFromFile(String fileName){
		StringBuilder fileText = new StringBuilder("");
		String[] words = null;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	fileText.append(line);
		    	fileText.append(" ");
		    }
		    words = fileText.toString().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		} catch (FileNotFoundException e) {
			System.out.println("File Not found "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception during I/O operation "+e.getMessage());
		}
		return Arrays.asList(words);	
	}
	
	public void processSynonymsFile(String fileName){
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	line = line.toLowerCase();
		    	String[] currWords = line.split(" ");
		    	List<String> currSynonymsList = Arrays.asList(currWords);
		    	Set<String> currSynonymSet = new HashSet<String>();
		    	currSynonymSet.addAll(currSynonymsList);
		    	for(String str : currSynonymsList) {
		    		synonymMap.put(str, currSynonymSet);
		    	}	
		    }
		} catch (IOException e) {
			System.out.println("Exception during file read "+e.getMessage());
		}	
	}
	
	
}
