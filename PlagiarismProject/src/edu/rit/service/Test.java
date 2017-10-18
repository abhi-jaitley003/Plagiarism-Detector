package edu.rit.service;

public class Test {
	public static void main(String[] args){
		PlagiarismCheckerService ps = new PlagiarismCheckerService();
		ps.processSynonymsFile("/Users/abhishekjaitley/Documents/PlagiarismDetectionProject/Synonyms.txt");
		System.out.println(ps.getSynonymMap());
		ps.constructTupleMapFromFile("/Users/abhishekjaitley/Documents/PlagiarismDetectionProject/File1.txt", 3);
		System.out.println("   second      ");
		ps.constructTupleSetFromFile("/Users/abhishekjaitley/Documents/PlagiarismDetectionProject/File2.txt", 3);
		System.out.println(ps.getPecentageMatch());
	}
}
