
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;

public class MCMAQuestion extends MCQuestion {

	protected ArrayList<MCMAAnswer> studentAnswer = new ArrayList<MCMAAnswer>();
	protected double baseCredit;
	protected int ansNum;
	
	protected MCMAQuestion(String question, double val, double base)
	{
		super(question, val);
		this.baseCredit = base;
	}
	
	protected MCMAQuestion(Scanner scanner) 
	{
		super(scanner);
		baseCredit = scanner.nextDouble();
		ansNum = scanner.nextInt();
		int i;
		for(i = 0; i < ansNum; i++)
		{
			MCMAAnswer answer = new MCMAAnswer(scanner);
			answers.add(answer);
		}
	}
	
	public Answer getNewAnswer(String answer, double val)
	{
		MCMAAnswer ans = new MCMAAnswer(answer, val);
		return ans;
	}
	
	public Answer getNewAnswer()
	{
		MCMAAnswer ans = new MCMAAnswer(questions, maxValue);
		return ans;
	}
	
	public void getAnswerFromStudent()
	{
		super.print();
		
		studentAnswer.clear();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose your answers and then type done# (for example: 'a b c done#') ");
		System.out.println("Or type 'skip#' to skip question and come back to it");
		String text = scanner.next();
		System.out.print("You selected: ");
		
		while(text.equalsIgnoreCase("done#") == false)
		{
			System.out.print(text + " ");
			
			text = text.toLowerCase();
			
			if(text.equals("a"))
			{
				studentAnswer.add((MCMAAnswer) answers.get(0));
			}
			else if(text.equals("b"))
			{
				studentAnswer.add((MCMAAnswer) answers.get(1));
			}
			else if(text.equals("c"))
			{
				studentAnswer.add((MCMAAnswer) answers.get(2));
			}
			else if(text.equals("d"))
			{
				studentAnswer.add((MCMAAnswer) answers.get(3));
			}
			else if(text.equals("e"))
			{
				studentAnswer.add((MCMAAnswer) answers.get(4));
			}
			else if(text.equals("skip#"))
			{
				studentAnswer.clear();
				//studentAnswer.add(new MCMAAnswer("skip#", 0.0));
				break;
			}
			
			if (studentAnswer != null)
			{
				text = scanner.next();
			}
		}
		System.out.println("");
	}
	
	@Override
	public void getAnswerFromStudent(String studentAnswerText) {
		for(MCAnswer answer : answers){
			if(answer.answers.equals(studentAnswerText)){
				studentAnswer.add((MCMAAnswer)answer);
				break;
			}
		}
	}
	
	public void printFile(PrintWriter write)
	{
		write.println(questions);
		super.printFile(write);
	}
	
	public void save(PrintWriter write) 
	{
		write.println("MCMAQuestion");
		write.println(maxValue);
		write.println(questions);
		write.println(baseCredit);
		write.println(ansNum);
		super.save(write);
	}
	
	public double getValue()
	{
		double added = 0;
		for(MCMAAnswer ques : studentAnswer)
		{
			added += (super.getValue(ques) / answers.size());
		}
		double result = (Math.min(1.0,  added) + baseCredit) * maxValue;
		return Math.floor(result);
	}
	
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
		String questionType = scanner.nextLine();
		int numAns = scanner.nextInt();
		scanner.nextLine();
		int i;
		for(i = 0; i < numAns; i++)
		{
			String text = scanner.nextLine();
			text.replaceAll("\\s","");
			for(MCAnswer ans: answers)
			{
				if(ans.answers.equalsIgnoreCase(text));
				studentAnswer.add((MCMAAnswer) ans);
			}
		}
	}
	
	public void saveStudentAnswers(PrintWriter write)
	{
		
		write.println("MCMAAnswer");
		write.println(studentAnswer.size());
		
		if(studentAnswer.size() <= 0)
		{
			write.println("-skipped-");
		}
		
		for(MCMAAnswer ans: studentAnswer)
		{
			ans.save(write);
		}
	}
	
	public void saveStudentAnswers_GUI(PrintWriter write, ArrayList<Answer> answersArray)
	{
		
		write.println("MCMAAnswer");
		write.println(studentAnswer.size());
		
		if(studentAnswer.size() <= 0)
		{
			write.println("-skipped-");
		}
		
		for(MCMAAnswer ans: studentAnswer)
		{
			ans.save(write);
		}
	}
	

	@Override
	public void getAnswerFromStudent_GUI(JTextArea textArea, StringBuilder sb)
	{
		// TODO Auto-generated method stub
		super.print_GUI(textArea, sb);
		
		studentAnswer.clear();
		
		sb.append("\n");
		sb.append("Note: Separate your answers with a space (for example: 'a c d')");
		//sb.append("Or type 'skip#' to skip question and come back to it.");
		//String text = textField.getText();


	}

	
}
