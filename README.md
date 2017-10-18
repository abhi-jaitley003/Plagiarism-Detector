# Plagiarism-Detector
Project:Plagiarism Checker Using N-Tuple Comparison Algorithm
Author: Abhishek Jaitley
Version: 1.0
——————————————————————————————————————————————————————————————————————\

How to run the application:
	1. Extract the zip folder.
	2. Place the Files - File1, File2 and Synonym file in the PlagiarismProject folder.
	3. Run the java file FilePlagiarismChecker.java in src/edu/rit/application with the arguments - SynonymFileName, File1, File2, Number of tuples(optional). Specify just the 	   filenames without including the absolute path. Use a space between the arguments . A couple example of the arguments are :
	   Synonyms.txt File1.txt File2.txt 4
	   Synonyms.txt File1.txt File2.txt
	   (Default value of tuple size is 3)

Packages in the directory : 

1. edu.rit.application: Contains the interface, which takes in the input files and displays the percentage of plagiarism amongst the input files
	Files in this package:
		a. PlagiarismCheckerInterface: Defines the functionality to display the percentage of plagiarism amongst the input files
		b. FilePlagiarismChecker: Implements the plagiarism checking functionality for files.
2. edu.rit.model: Contains the model for N-tuple for the N-tuple 
	Files in this package:
		a. WordsTuple: Defines the structure for the tuple class which specifies the number of words in a tuple and contains the list of words in this tuple.
3. edu.rit.service: Contains the definition for the services classes that are used to perform all the tasks useful for detecting plagiarism.
	Files in this package:
		a. PlagiarismCheckerService: A plagiarism checking service to perform all the sub-tasks needed to be completed to perform a comprehensive plagiarism check.

Assumptions and Validations:
One assumption that I have made while implementing this is that the number of times a tuple appears in the second file does not have an effect on the plagiarism percentage.
Once we find that a tuple is present in the second file we mark it as a tuple that has been plagiarized.

Scenario A: 
File1 has a tuple (has a run) and it is present twice. File2 has the same tuple but 5 times. Once we encounter the tuple in the second file for the first time we increase
the matched count of the tuple in the first file by 2.
Scenario B:
File1 has a tuple (has a run) and it is present twice. File2 has the same tuple but 1 time. Once we encounter the tuple in the second file we increase
the matched count of the tuple in the first file by 2.


File Validations handled: 
	1. File1 Empty validation -> If the file1 (which is the file to be checked) is empty then a message will show stating : The file to be checked is empty.
	2. File2 Empty validation -> If the file2 (which is the file to be compared with) is empty then the plagiarism is 0%.
	3. Tuple Size validation -> 
	   a. If the size of the tuple requested in the input is greater than the number of words in any of the files then we choose the minimum of the total number of words in 	      either of the files.
          	For example: 
			File 1 contains 5 words.
			File 2 contains 3 words.
			and tuple size entered is 4 then we choose 3 as the tuple size.
	   b. If the value for tuple size is less than or equal to 0 then the function exits with a message : ‘Incorrect value for size of tuple’.
	4. Number of arguments validation ->
		a. If the number of arguments entered is more than 4 or less than 3 then the program is exited with a message ‘Invalid number of arguments’.
		b. If the number of arguments is 3 then the tuple size is taken as 3 by default.


Test Cases : 
	Test Case1 : When File1 and file2 have some tuples that match - should display the match percentage
		     arguments :  Synonyms.txt File1a.txt File1b.txt 3
	Test Case2 : File1 has ‘go for a run’ and File2 has ‘go for a jog’- should display a 100% match
		     arguments :  Synonyms.txt File2a.txt File2b.txt 3
	Test Case3 : File1 has ‘go for a run’ and File2 has ‘went for a jog’- should display a 50% match
		     arguments :  Synonyms.txt File2a.txt File2c.txt 3
	Test Case4 : File1 is empty and File2 is empty - should display an error message saying the File to be checked is empty 
		     arguments :  Synonyms.txt File3a.txt File3b.txt 3
	Test Case5 : File1 is not empty and File2 is empty - should display that plagiarism  is 0%.
		     arguments :  Synonyms.txt File1a.txt File3b.txt 3
	Test Case6 : The tuple size is more than the number of words in one of the files- The tuple size is set to the number of words in the file with lesser words.
		     arguments :  Synonyms.txt File1a.txt File4.txt 5

 	
			
	
		


	
