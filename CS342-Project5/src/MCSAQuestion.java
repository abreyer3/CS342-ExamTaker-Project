
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;

public class MCSAQuestion extends MCQuestion {
	
	private int ansNum;
	
	protected MCSAQuestion(String question, double val)
	{
		super(question,val);
	}
	
	protected MCSAQuestion(Scanner scanner)
	{
		super(scanner);
		
		ansNum = scanner.nextInt();
		int i;
		for(i = 0; i < ansNum; i++)
		{
			MCSAAnswer answer = new MCSAAnswer(scanner);
			answers.add(answer);
		}
	}

	
	public Answer getNewAnswer() 
	{
		
		MCSAAnswer answer = new MCSAAnswer(questions, maxValue);
		return answer;
	}
	
	public Answer getNewAnswer(String ans, double credit) 
	{
		
		MCSAAnswer answer = new MCSAAnswer(ans, credit);
		return answer;
	}

	public void selectRightAnswer(Answer answer) 
	{
		rightAnswer = answer;
	}
	
	public void getAnswerFromStudent() 
	{
		super.print();
		
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine();
		
		//text = text.toLowerCase();
		if (text.length() <= 0)
		{
			return;
		}
		char c = Character.toUpperCase(text.charAt(0));
		int choice = c - 'A';
		
		if (choice < 0 || choice >= answers.size())
		{
			System.out.println("ERROR: Option doesn't exist. Skipping question...");
			return;
		}
		
		studentAnswer = answers.get(choice);
		
		if (studentAnswer != null)
		{
			System.out.print("You selected: " + c);
		}
		//studentAnswer.print();
		System.out.println("");
	}
	
	@Override
	public void getAnswerFromStudent(String studentAnswerText) {
		for(MCAnswer answer : answers){
			if(answer.answers.equals(studentAnswerText)){
				studentAnswer = answer;
				break;
			}
		}
	}
    
	public void save(PrintWriter write) 
	{
		// TODO Auto-generated method stub
		write.println("MCSAQuestion");
		write.println(maxValue);
		write.println(questions);
		write.println(ansNum);
		super.save(write);
	}
	
	
	public void printFile(PrintWriter write) 
	{
		// TODO Auto-generated method stub
		write.println(questions);
		super.printFile(write);
	}
	
	public double getValue() 
	{
		return super.getValue((MCSAAnswer)studentAnswer);
	}

	@Override
	public void getAnswerFromStudent_GUI(JTextArea textArea, StringBuilder sb) {
		// TODO Auto-generated method stub
		super.print_GUI(textArea, sb);
	}

	

}
