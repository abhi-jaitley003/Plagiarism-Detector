package edu.rit.view;

import edu.rit.service.PlagiarismCheckerService;

public class FilePlagiarismChecker implements PlagiarismCheckerInterface {

	private String synonymFilePath;
	private String fileToBeChecked;
	private String baseFile;
	private int tupleSize;
	private PlagiarismCheckerService checker;
	@Override
	public void inputProcessing(String[] args) {
		System.out.println("inside");
		try{
			if(args.length < 3 || args.length > 4) {
				System.out.println("Invalid number of arguments");
				return;
			}
			synonymFilePath = args[0];
			fileToBeChecked = args[1];
			baseFile = args[2];
			tupleSize = args.length>3? Integer.parseInt(args[3]): 3 ; 
		} catch(NumberFormatException e){
			System.out.println("Incorrect value for size of tuple "+e.getMessage());
		} catch(Exception e){
			System.out.println("Error while parsing command line input "+e.getMessage());
		}
	}

	@Override
	public void displaySummary() {
		double result = checker.getPecentageMatch();
		System.out.println("The percentage of content in File 1 that is copied from File 2 is "+result);
	}
	
	@Override
	public void performPlagiarismCheck() {
		checker = new PlagiarismCheckerService();
		checker.processSynonymsFile(synonymFilePath);
		checker.constructTupleMapFromFile(fileToBeChecked,tupleSize);
		checker.constructTupleSetFromFile(baseFile, tupleSize);	
	}
	
	public static void main(String[] args){
		FilePlagiarismChecker ch = new FilePlagiarismChecker();
		ch.inputProcessing(args);
		ch.performPlagiarismCheck();
		ch.displaySummary();
	}

}
