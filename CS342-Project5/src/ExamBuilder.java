/*
 * CS 342 - Term Project Part 4: ExamBuilder.java
 * Name: Tarang Patel
 * Net ID: tpatel31
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamBuilder {

	public static void main(String[] args) throws IOException
	{
		System.out.println("Name: Tarang Patel");
		System.out.println("NetId: tpatel31 ");
		System.out.println("Project 4");
		System.out.println("Partners: Allen Breyer, Mohammed Shafiuddin");
		System.out.println("");
		
		Exam exam1= new Exam("Exam 1");
		while(true)
		{
			System.out.println("----------------------------");
			System.out.println("Please select from the following choices: ");
			System.out.println("   load - Load a saved Exam from a file");
			System.out.println("   add - Add questions");
			System.out.println("   remove - Remove questions");
			System.out.println("   reorderQ - Reorder questions");
			System.out.println("   reorderA - Reorder answers");
			System.out.println("   print - Print the Exam to screen");
			System.out.println("   printF - Print the Exam to a file");
			System.out.println("   save - Save the Exam");
			System.out.println("   quit - quit the program");
		    System.out.println("----------------------------\n");
		    
		    Scanner scanner = ScannerFactory.getKeyboardScanner();
			String choice = scanner.nextLine();
			if (choice.equalsIgnoreCase("load"))
			{
				System.out.print("Enter file name: ");
				Scanner exam = ScannerFactory.getKeyboardScanner();
				File newFile = new File(exam.nextLine());
				exam = new Scanner(newFile);
				//create exams with details about each exam
				exam1= new Exam(exam);
				exam1.print();
				//System.out.println("loaded\n");
			}
			
			else if (choice.equalsIgnoreCase("add"))
			{
				while (true)
				{
					System.out.println("What type of question would you like to add? Select from the following choices: ");
					System.out.println("   MCMA - multiple choice multiple answer question");
					System.out.println("   MCSA - multiple choice short answer question");
					System.out.println("   SAQuestion - short answer question");
					System.out.println("   NumQuestion - numerical answer question");
					System.out.println("   q - return to main menu");
					String type = scanner.nextLine();
					if (type.equalsIgnoreCase("MCMA"))
					{
						System.out.println("Please enter your question: ");
						String ques = scanner.nextLine();
						System.out.println("Please enter the max value: ");
						String val = scanner.nextLine();
						System.out.println("Please enter the base value: ");
						String base = scanner.nextLine();
						MCMAQuestion ques1 = new MCMAQuestion(ques, Double.parseDouble(val), Double.parseDouble(base));
						while(true)
						{
							System.out.println("To add answers, press a, otherwise press q");
							String ans = scanner.nextLine();
							if (ans.equalsIgnoreCase("a"))
							{
								System.out.println("enter answer: ");
								String Answer = scanner.nextLine();
								System.out.println("enter value for answer: ");
								String val1 = scanner.nextLine();
								MCMAAnswer ans1 = new MCMAAnswer(Answer, Double.parseDouble(val1));
								ques1.addAnswer(ans1);
							}
							else
							{
								break;
							}
						}
						exam1.addQuestion(ques1);
				
					}
					else if (type.equalsIgnoreCase("MCSA"))
					{
						System.out.println("Please enter your question: ");
						String ques = scanner.nextLine();
						System.out.println("Please enter the max value: ");
						String val = scanner.nextLine();
						MCSAQuestion ques1 = new MCSAQuestion(ques, Double.parseDouble(val));
						while(true)
						{
							System.out.println("To add answers, press a, otherwise press q");
							String ans = scanner.nextLine();
							if (ans.equalsIgnoreCase("a"))
							{
								System.out.println("enter answer: ");
								String Answer = scanner.nextLine();
								System.out.println("enter value for answer: ");
								String val1 = scanner.nextLine();
								MCSAAnswer ans1 = new MCSAAnswer(Answer, Double.parseDouble(val1));
								ques1.addAnswer(ans1);
							}
							else
							{
								break;
							}
						}
						exam1.addQuestion(ques1);
				
					}
					else if (type.equalsIgnoreCase("SAQuestion"))
					{
						System.out.println("Please enter your question: ");
						String ques = scanner.nextLine();
						System.out.println("Please enter the max value: ");
						String val = scanner.nextLine();
						SAQuestion ques1 = new SAQuestion(ques, Double.parseDouble(val));
						
						System.out.println("enter answer: ");
						String Answer = scanner.nextLine();
						SAAnswer ans1 = new SAAnswer(Answer);
						ques1.setRightAnswer(ans1);
						exam1.addQuestion(ques1);
					}
					else if(type.equalsIgnoreCase("NumQuestion"))
					{
						System.out.println("Please enter your question: ");
						String ques = scanner.nextLine();
						System.out.println("Please enter the max value: ");
						String val = scanner.nextLine();
						NumQuestion ques1 = new NumQuestion(ques, Double.parseDouble(val));
						
						System.out.println("enter answer: ");
						String Answer = scanner.nextLine();
						NumAnswer ans1 = new NumAnswer(Answer);
						ques1.setRightAnswer(ans1);
						exam1.addQuestion(ques1);
					}
					else
					{
						break;
					}
				}
			}
			
			else if (choice.equalsIgnoreCase("remove"))
			{
				while(true)
				{
					System.out.println("To remove questions, press r, otherwise press q");
					String ans = scanner.nextLine();
					if (ans.equalsIgnoreCase("r"))
					{
						exam1.print();
						System.out.println("Enter a specific question # to remove or enter -1 to remove all");					
						String num = scanner.nextLine();
						exam1.remove(Integer.parseInt(num)-1);
					}	
					else
					{
						break;
					}
				}
			}
			
			else if (choice.equalsIgnoreCase("reorderQ"))
			{
				exam1.reorderQuestions();
				System.out.println("Questions reordered");
			}
			
			else if (choice.equalsIgnoreCase("reorderA"))
			{
				exam1.reorderMCAnswers(-1);
				System.out.println("Answers reordered");
			}
			
			else if (choice.equalsIgnoreCase("print"))
			{
				exam1.print();
			}
			
			else if (choice.equalsIgnoreCase("printF"))
			{
				System.out.println("Enter a file name to print the exam to: (example-  exam.txt  )");
				String name = scanner.nextLine();
				File savedFileExam = new File(name);
				PrintWriter write = new PrintWriter(savedFileExam);
				exam1.printToFile(write);
				write.close();
			}
			
			else if (choice.equalsIgnoreCase("save"))
			{
				System.out.println("Enter a file name to save the exam to: (example-  exam.txt  )");
				String name = scanner.nextLine();
				File savedFileExam = new File(name);
				PrintWriter write = new PrintWriter(savedFileExam);
				exam1.save(write);
				write.close();
			}
			
			else
			{
				System.out.println("quitter");
				break;
			}
		}
	}
}
