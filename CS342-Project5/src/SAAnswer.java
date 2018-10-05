
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;

public class SAAnswer extends Answer
{

	protected String answers;
	
	protected SAAnswer(String answer)
	{
		this.answers = answer;
	}
	
	public SAAnswer(Scanner scanner)
	{
		answers = scanner.nextLine();
		answers = answers.trim();
	}
	
	
	public void print() 
	{
		System.out.println(answers);	
	}

	public void save(PrintWriter write) 
	{
		write.println(answers);
	}

	public void printFile(PrintWriter write) 
	{
		write.println(answers);
	}
	
	public double getCredit(Answer rightAnswer) 
	{
		if(rightAnswer instanceof SAAnswer)
		{
			if(((SAAnswer) rightAnswer).answers.equalsIgnoreCase(this.answers))
			{
				return 1.0;
			}
			else
				return 0.0;
		}
		else
			return 0.0;
	}
	
	public void print_GUI(JTextArea textArea, StringBuilder sb) 
	{
		sb.append(answers);
	}
	
	@Override
	public String toString()
	{
		return (this.answers);
	}

}
