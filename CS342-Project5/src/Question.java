
import java.util.ArrayList;
import java.util.Collections;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;

//Name: Tarang Patel
//Netid: tpatel31
//Project 1


public abstract class Question {
	
	protected String questions;
	protected Answer rightAnswer;
	protected Answer studentAnswer;
	protected double maxValue;
	
	protected Question(String question, double val) 
	{
		questions = question;
		maxValue = val;
	}
	
	public Question(Scanner scanner)
	{
		//maxValue = scanner.nextDouble();
		maxValue = Double.parseDouble(scanner.nextLine());
		rightAnswer = null;
		studentAnswer = null;
		//scanner.nextLine();
		//questions = scanner.nextLine();
		if (scanner.hasNextLine())
		{
			questions = scanner.nextLine();
		}
		
	}
	
	
	
	public void print()
	{
		System.out.print(questions);
		System.out.println("");
	}
	
	public void setRightAnswer(Answer ans)
	{
		rightAnswer = ans;
	}
	
	public abstract Answer getNewAnswer();
	
	public abstract void getAnswerFromStudent();
	
	public abstract void getAnswerFromStudent(String studentAnswerText);
	
	public abstract double getValue();
	
	abstract public void printFile(PrintWriter write);
	
	abstract public void save(PrintWriter write);
	
	public void restoreStudentAnswers(Scanner scanner)
	{
		if(scanner.hasNextLine() == false)
		{
			return;
		}
		scanner.nextLine();
		if(scanner.hasNextLine() == false)
		{
			return;
		}
		String typeOfQuestion = scanner.nextLine();
		
		if(typeOfQuestion.equalsIgnoreCase("SAAnswer"))
		{
			String text = scanner.nextLine();
			studentAnswer = new SAAnswer(text);
		}
		else if (typeOfQuestion.equalsIgnoreCase("NumAnswer"))
		{
			String textNum = scanner.nextLine();
			studentAnswer = new NumAnswer(textNum);
		}
		else if (typeOfQuestion.equalsIgnoreCase("MCSAAnswer"))
		{
			String text2 = scanner.nextLine();
			String text = text2.replaceAll("\\s","");
			MCSAQuestion ques = (MCSAQuestion) this;
			
			if(text.equalsIgnoreCase(ques.answers.get(0).answers.replaceAll("\\s","")))
			{
				studentAnswer = ques.answers.get(0);
			}
			else if(text.equalsIgnoreCase(ques.answers.get(1).answers.replaceAll("\\s","")))
			{
				studentAnswer = ques.answers.get(1);
			}
			else if(text.equalsIgnoreCase(ques.answers.get(2).answers.replaceAll("\\s","")))
			{
				studentAnswer = ques.answers.get(2);
			}
			else if(text.equalsIgnoreCase(ques.answers.get(3).answers.replaceAll("\\s","")))
			{
				studentAnswer = ques.answers.get(3);
			}
			else if(text.equalsIgnoreCase(ques.answers.get(4).answers.replaceAll("\\s","")))
			{
				studentAnswer = ques.answers.get(4);
			}
		}
	}
	
	
	public void saveStudentAnswers(PrintWriter write)
	{
		if(this instanceof SAQuestion)
		{
			write.println("SAAnswer");
			
			if (studentAnswer == null)
			{
				write.println("-skipped-");
			}
			else
			{
				studentAnswer.save(write);
			}
		}
		else if(this instanceof NumQuestion)
		{
			write.println("NumAnswer");
			
			if (studentAnswer == null)
			{
				write.println("-skipped-");
			}
			else
			{
				studentAnswer.save(write);
			}
		}
		else if(this instanceof MCSAQuestion)
		{
			write.println("MCSAAnswer");
			
			if (studentAnswer == null)
			{
				write.println("-skipped-");
			}
			else
			{
				studentAnswer.save(write);
			}
		}
		
	}

	public abstract void getAnswerFromStudent_GUI(JTextArea textArea, StringBuilder sb);

	public void print_GUI(JTextArea textArea, StringBuilder sb) {
		// TODO Auto-generated method stub
		sb.append(questions);
		sb.append("");
		textArea.setText(sb.toString());
	}

	public void saveStudentAnswers_GUI(PrintWriter write, ArrayList<Answer> answersArray, int i)
	{
		// TODO Auto-generated method stub
		if(this instanceof SAQuestion)
		{
			write.println("SAAnswer");
			
			if ((answersArray == null) || (answersArray.get(i).toString().equals("")))
			{
				write.println("-skipped-");
			}
			else
			{
				write.println(answersArray.get(i).toString());
			}
		}
		else if(this instanceof NumQuestion)
		{
			write.println("NumAnswer");
			
			if (answersArray == null)
			{
				write.println("-skipped-");
			}
			else
			{
				write.println(answersArray.get(i).toString());
			}
		}
		else if(this instanceof MCSAQuestion)
		{
			write.println("MCSAAnswer");
			
			if ((answersArray == null) || (answersArray.get(i).toString().equals("")))
			{
				write.println("-skipped-");
			}
			else
			{
				//write.println(answersArray.get(i).toString());
				String text = answersArray.get(i).toString();
				
				if (text.length() <= 0)
				{
					return;
				}
				char c = Character.toUpperCase(text.charAt(0));
				
				if(text.equalsIgnoreCase("a"))
				{
					write.println(((MCQuestion)this).printString(0));
				}
				else if(text.equalsIgnoreCase("b"))
				{
					write.println(((MCQuestion)this).printString(1));
				}
				else if(text.equalsIgnoreCase("c"))
				{
					write.println(((MCQuestion)this).printString(2));
				}
				else if(text.equalsIgnoreCase("d"))
				{
					write.println(((MCQuestion)this).printString(3));
				}
				else if(text.equalsIgnoreCase("e"))
				{
					write.println(((MCQuestion)this).printString(4));
				}
				else
				{
					write.println("-null-");
				}
				
			}
		}
		else if(this instanceof MCMAQuestion)
		{
			write.println("MCMAAnswer");
			
			
			if ((answersArray == null) || (answersArray.get(i).toString().equals("")))
			{
				write.println("1");
				write.println("-skipped-");
			}
			else
			{
				String s = answersArray.get(i).toString();
				String[] tokens = s.split("\\s+");
				if (tokens.length > 5)
				{
					write.println("5");
				}
				else
				{
					write.println(tokens.length);
				}
				
				boolean aFlag = false;
				boolean bFlag = false;
				boolean cFlag = false;
				boolean dFlag = false;
				boolean eFlag = false;
				int counter = 1;
				
				for (String text : tokens)
				{
					//write.println(t);
					char c = Character.toUpperCase(text.charAt(0));
					
					if(text.equalsIgnoreCase("a") && aFlag == false)
					{
						write.println(((MCQuestion)this).printString(0));
						aFlag = true;
					}
					else if(text.equalsIgnoreCase("b") && bFlag == false)
					{
						write.println(((MCQuestion)this).printString(1));
						bFlag = true;
					}
					else if(text.equalsIgnoreCase("c") && cFlag == false)
					{
						write.println(((MCQuestion)this).printString(2));
						cFlag = true;
					}
					else if(text.equalsIgnoreCase("d") && dFlag == false)
					{
						write.println(((MCQuestion)this).printString(3));
						dFlag = true;
					}
					else if(text.equalsIgnoreCase("e") && eFlag == false)
					{
						write.println(((MCQuestion)this).printString(4));
						eFlag = true;
					}
					else
					{
						write.println("-null-");
					}
					
					counter++;
					if (counter > 5)
					{
						break;
					}
				}
			}
		}
	}
	
}
