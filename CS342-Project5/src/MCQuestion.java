import java.util.ArrayList;
import java.util.Collections;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;

abstract class MCQuestion extends Question{
	
	protected ArrayList<MCAnswer> answers = new ArrayList<MCAnswer>();
	
	
	protected MCQuestion(String question, double val)
	{
		super(question, val);
	}
	
	protected MCQuestion(Scanner scanner)
	{
		super(scanner);
	}
	
	public void print() 
	{
		
		int i;
		System.out.println(questions);
		for(i=0; i <= answers.size()-1; i++ )
		{
			System.out.print("  " + (char)('A' + i) + ".");
			answers.get(i).print();
		}
	}
	
	
	public String printString(int i)
	{
		//System.out.println("printString: " + answers.get(i).toString());
		if (i <= answers.size())
		{
			return answers.get(i).toString();
		}
		else
		{
			return null;
		}
		
	}
	
	
	public void addAnswer(MCAnswer array)
	{
		answers.add(array);
	}
	
	public void reorderAnswers()
	{
		Collections.shuffle(answers);
	}
	
	public double getValue(MCAnswer ansStudent)
	{
		double result = 0.0;
		for(MCAnswer iter : answers)
		{
			/*if (iter.getCredit(ansStudent) > 0.0)
			{
				return iter.getCredit(ansStudent) * maxValue;
				
			}*/
			result += iter.getCredit(ansStudent);
		}
		return result * maxValue;
	}
	
	public void save(PrintWriter write)
	{
		for(MCAnswer iter: answers)
		{
			write.print(iter.creditIfSelected + " ");
			write.println(iter.answers);
		}
	}
	
	
	public void printFile(PrintWriter write)
	{
		int i=0;
		for(MCAnswer iter: answers)
		{
			write.print("  " + (char)('A' + i) + ".");
			write.println(iter.answers);
			i++;
		}
	}

	
	public void print_GUI(JTextArea textArea, StringBuilder sb) 
	{
		
		int i;
		sb.append(questions);
		sb.append("\n");
		for(i=0; i <= answers.size()-1; i++ )
		{
			sb.append("  " + (char)('A' + i) + ". ");
			answers.get(i).print_GUI(textArea, sb);
			sb.append("\n");
		}
	}
}
