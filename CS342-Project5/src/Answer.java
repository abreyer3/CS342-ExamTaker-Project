import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;




public abstract class Answer {
	
	protected Answer() 
	{
		
	}
	
	public Answer(Scanner scanner)
	{
		
	}
	
	public abstract void print();
	
	public abstract double getCredit(Answer rightAnswer);
	
	abstract public void save(PrintWriter write);
	
	public abstract void print_GUI(JTextArea textArea, StringBuilder sb);
	
	
}
