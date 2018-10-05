
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;

public class NumQuestion extends Question
{
	
	public NumQuestion(String question, double val)
	{
		super(question,val);
	}

	public NumQuestion(Scanner scanner)
	{
		super(scanner);
		
		//SAAnswer answer = new SAAnswer(scanner);
		//rightAnswer = answer;
		
		if (scanner.hasNextLine())
		{
			String ans = scanner.nextLine();
			SAAnswer answer = new SAAnswer(ans);
			//rightAnswer = answer;
			//System.out.println(answer);
			
			this.setRightAnswer(answer);
		}
	}
		
	public Answer getNewAnswer(String ans) 
	{
			
		SAAnswer answer = new SAAnswer(ans);
		return answer;
	}
	
	public Answer getNewAnswer() 
	{
			
		SAAnswer answer = new SAAnswer(questions);
		return answer;
	}

		
	public void getAnswerFromStudent() 
	{
		super.print();
			
		Scanner S = new Scanner(System.in);
		if(S.hasNextLine())
		{
			String selection = S.nextLine();
			studentAnswer = (SAAnswer) getNewAnswer(selection);
		}	
	}
	
	@Override
	public void getAnswerFromStudent(String studentAnswerText) {
		studentAnswer = (SAAnswer) getNewAnswer(studentAnswerText);
	}
	
	

		
	public void save(PrintWriter write) 
	{
		write.println("NumQuestion");
		write.println(maxValue);
		write.println(questions);
		rightAnswer.save(write);	
	}
	
	public double getValue() 
	{
		if(rightAnswer.getCredit(studentAnswer) == 1.0)
		{
			return maxValue;
		}
		else
		{
			return 0.0;
		}
	}

	@Override
	public void printFile(PrintWriter write) 
	{
		write.println(questions);
		
	}

	@Override
	public void getAnswerFromStudent_GUI(JTextArea textArea, StringBuilder sb) {
		// TODO Auto-generated method stub
		super.print_GUI(textArea, sb);
	}



}
