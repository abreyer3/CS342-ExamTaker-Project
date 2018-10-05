/*
 * CS 342 - Term Project Part 4: ExamTaker.java
 * Name: Allen Breyer III
 * Net ID: abreye2
 */

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

public class ExamTaker
{

	public static void main(String[] args) throws IOException
	{
		System.out.println("----------------------------");
		System.out.println("CS 342 - Term Project Part 4: ExamTaker.java");
		System.out.println("Name: Allen Breyer III");
		System.out.println("Net ID: abreye2");
		System.out.println("Partners: Tarang Patel, Mohammed Shafiuddin");
		System.out.println("----------------------------\n");
		
		// STEP 1
		// Get student information
		System.out.print("Please enter your name: ");
		Scanner nameScanner = ScannerFactory.getKeyboardScanner();
		String studentName = nameScanner.nextLine();
		String studentNameNoSpace = studentName.replaceAll("\\s+","");
		
		
		
		// STEP 2
		// Load an arbitrary Exam file
		
		//URL path = ExamTester.class.getResource("sample_exam_file.txt");
		//File f = new File(path.getFile());
		//Scanner sc = new Scanner(f);
		//Exam exam1_Scanner = new Exam(sc);
		
		System.out.println("\n");
		System.out.print("Please enter a file name to take test (including file extension): ");
		Scanner examScanner = ScannerFactory.getKeyboardScanner();
		String filename = examScanner.nextLine();
		URL path = ExamTester.class.getResource(filename);
		if (path == null)
		{
			System.out.println("**ERROR: Filename '" + filename + "' not found! Make sure it's spelled correctly or\n"
					+ "that it's in the correct directory. Exiting program...");
            return;
		}
		
		File filename2 = new File(path.getFile());
		
		/*if(!Desktop.isDesktopSupported())
		{
            System.out.println("**ERROR: Desktop is not supported! Exiting program...");
            return;
        }*/
		if(!(filename2.exists()))
		{
			System.out.println("**ERROR: Filename '" + filename2 + "' not found! Exiting program...");
            return;
		}
		
		examScanner = new Scanner(filename2);
		Exam exam_Scanner = new Exam(examScanner);
		
		
		
		// STEP 3
		// Get answers from the student
		System.out.println("\n");
		System.out.println("-------------------------------BEGIN-TEST-------------------------------");
		System.out.println("\n");
		exam_Scanner.getAnswerFromStudent(-1);
		System.out.println("--------------------------------END-TEST--------------------------------");
		System.out.println("\n");
		
		
		// STEP 4 [Optional]
		// Show confirmation of student choices (ungraded) before saving and exiting
		
		
		
		// STEP 5
		// Save student answers to a file, containing also the name of the corresponding exam file
		File saveAnswerFile = new File(studentNameNoSpace + "_" + filename);
		//File saveAnswerFile = new File("bin/answer2.txt");
		PrintWriter pw1 = new PrintWriter(saveAnswerFile);
		pw1.println(studentName);
		pw1.println(filename);
		exam_Scanner.saveStudentAnswers(pw1);
		pw1.close();
		nameScanner.close();
		examScanner.close();
	}

}
