
import java.util.ArrayList;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


public class Exam {
	
	//Private members for Exam class with an arraylist of type Question
	private String exams;
	private ArrayList<Question> questions = new ArrayList<Question>();
	
	
	//constructor for Exam class
	public Exam(String exam)
	{
		this.exams = exam;
	}
	
	Exam(Scanner scanner)
	{
		if (scanner.hasNextLine())
		{
			exams = scanner.nextLine();
		}
		
		String typeOfQuestion = null;
		
		//Skip timestamp
		/*if (scanner.hasNextLine())
		{
			scanner.nextLine();
		}*/
		
		while(scanner.hasNextLine())
		{
			typeOfQuestion = scanner.nextLine();
			
			if(scanner.hasNextLine() == false)
			{
				break;
			}
			if(typeOfQuestion.equalsIgnoreCase("SAQuestion"))
			{
				SAQuestion ques = new SAQuestion(scanner);
				addQuestion(ques);
			}
			else if(typeOfQuestion.equalsIgnoreCase("NumQuestion"))
			{
				NumQuestion ques = new NumQuestion(scanner);
				addQuestion(ques);
			}
			else if(typeOfQuestion.equalsIgnoreCase("MCSAQuestion"))
			{
				MCSAQuestion ques = new MCSAQuestion(scanner);
				addQuestion(ques);
			}
			else if(typeOfQuestion.equalsIgnoreCase("MCMAQuestion"))
			{
				MCMAQuestion ques = new MCMAQuestion(scanner);
				addQuestion(ques);
			}
		}
	}
	
	
	
	public void updateTextArea(final String text, JTextArea textArea)
	{
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        textArea.append(text);
	      }
	    });
	    }

	public void redirectSystemStreams(JTextArea textArea)
	{
	    OutputStream out = new OutputStream() {
	      @Override
	      public void write(int b) throws IOException {
	        updateTextArea(String.valueOf((char) b), textArea);
	      }

	      @Override
	      public void write(byte[] b, int off, int len) throws IOException {
	        updateTextArea(new String(b, off, len), textArea);
	      }

	      @Override
	      public void write(byte[] b) throws IOException {
	        write(b, 0, b.length);
	      }
	    };

	    System.setOut(new PrintStream(out, true));
	    System.setErr(new PrintStream(out, true));
	 }
	
	
	
	
	
	//add a question to an exam
	public void addQuestion(Question array)
	{
		questions.add(array);
	}
	
	public void remove(int pos)
	{
		int i;
		if(pos < 0 )
		{
			for(i=0; i < questions.size(); i++ )
			{
				questions.remove(i);
			}
		}
		else 
		{
			questions.remove(pos);
		}
	}
	
	//prints the entire exam with all questions and their answers
	public void print() 
	{
		
		int i;
		System.out.println(exams);
		for(i=0; i < questions.size(); i++ )
		{
			System.out.print((i+1) + ".");
			questions.get(i).print();
			System.out.println("");
		}
	}
	
	
	//reorders the questions in the exam
	public void reorderQuestions()
	{
		Collections.shuffle(questions);
	}
	
	public void reorderMCAnswers(int pos)
	{
		if(pos < 0 )
		{
			for(Question ques : questions)
			{
				if(ques instanceof MCQuestion)
				{
					Collections.shuffle(((MCQuestion) ques).answers);
				}
			}
		}
		else 
		{
			Question ques = questions.get(pos);
			if(ques instanceof MCQuestion)
			{
				Collections.shuffle(((MCQuestion) ques).answers);
			}
		}
	}
	
	public void getAnswerFromStudent(int pos) 
	{
		
		if (pos >= questions.size())
		{
			System.out.println("ERROR: Invalid position index");
			return;
		}
		else if (pos < 0)
		{
			//textArea.
			
			int i = 0;
			for (i = 0; i < questions.size(); ++i)
			{
				System.out.print((i+1) + ") ");
				questions.get(i).getAnswerFromStudent();
				System.out.println("");
			}
			
			System.out.println("Would you like to go back to answer or change some answers to questions?");
			System.out.print("(Type 'Y' for yes. Anything else for no): ");
			
			Scanner scanner = new Scanner(System.in);
			String text = scanner.next();
			
			text = text.toUpperCase();
			
			while (text.equals("Y"))
			{
				System.out.println("Type the question number to answer again: ");
				int num = scanner.nextInt();
				
				if (num > questions.size() || (num <= 0))
				{
					System.out.println("ERROR: Invalid position index\n");
				}
				else
				{
					this.getAnswerFromStudent(num - 1);
				}
				
				System.out.println("Would you like to go back to answer or change some answers to questions?");
				System.out.print("(Type 'Y' for yes. Anything else for no): ");
				text = scanner.next();
				text = text.toUpperCase();
			}
		}
		else
		{
			System.out.print((pos+1) + ") ");
			questions.get(pos).getAnswerFromStudent();
			System.out.println("");
		}
	}
	
	
	public void getAnswerFromStudent_GUI(JTextArea textArea, int pos) 
	{
		StringBuilder sb = new StringBuilder();
		//sb.setLength(0);
		
		if (pos >= questions.size())
		{
			sb.append("ERROR: Invalid position index");
			textArea.setText(sb.toString());
			return;
		}
		else if (pos >= 0)
		{
			sb.append((pos+1) + ") ");
			questions.get(pos).getAnswerFromStudent_GUI(textArea, sb);
			sb.append("");
			textArea.setText(sb.toString());
		}
			
		//sb.setLength(0);
	}
	
	
	public void getAnswerFromStudent(int questionPos, String studentAnswer) 
	{
		Question ques = questions.get(questionPos);
		ques.getAnswerFromStudent(studentAnswer);
	}
	
	//get the overall score of the exam
	public double getValue()
	{
		double value = 0.0;
		int counter = 1;
		
		for(Question ques : questions)
		{
			System.out.println(" " +ques.getValue() + " points from Question number: " + counter);
			value += ques.getValue();
			counter++;
		}
		
		System.out.println(" " + value + " points total.");
		return value;
	}
	
	public void reportQuestionValues() 
	{
		int position = 1;
		
		int i;
		for(i=0; i < questions.size(); i++)
		{
			System.out.println("Question number " + position + ": " + questions.get(i).maxValue + " points");
			position++;
		}
	}
	
	public void restoreStudentAnswers(Scanner scanner)
	{
		String name = scanner.nextLine();
		//Skip the exam file name and timestamp
		scanner.nextLine();
		scanner.nextLine();
		
		System.out.println("Restoring answers for: " + name);
		while(scanner.hasNextLine())
		{
			for(Question ques: questions)
			{
				ques.restoreStudentAnswers(scanner);
			}
		}
	}
	
	public void save(PrintWriter write)
	{
		write.println(exams);
		
		write.println(getTimestamp());
		
		write.println();
		for(Question ques: questions)
		{
			ques.save(write);
			write.println();
		}
	}
	
	
	public void printToFile(PrintWriter write)
	{
		int i=0;
		write.println(exams);
		for(Question ques: questions)
		{
			write.print((i+1) + ".");
			ques.printFile(write);
			write.println();
			i++;
		}
	}
	
	
	private String getTimestamp(){
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		String timestamp = dateFormat.format(date);
		return timestamp;
	}

	public void saveStudentAnswers(PrintWriter write)
	{
		write.println(getTimestamp());
		int i;
		write.println();
		for(i=0; i < questions.size(); i++ )
		{
			questions.get(i).saveStudentAnswers(write);
			write.println();
		}
	}
	
	public void saveStudentAnswers_GUI(PrintWriter write, ArrayList<Answer> answersArray)
	{
		write.println(getTimestamp());
		int i;
		write.println();
		for(i=0; i < questions.size(); i++ )
		{
			//questions.get(i).saveStudentAnswers(write);
			
			questions.get(i).saveStudentAnswers_GUI(write, answersArray, i);
			//answersArray.get(i).save(write);
			write.println();
		}
	}
	
	public ArrayList<Double> getAllQuestionValues(){
		ArrayList<Double> questionValues = new ArrayList<Double>();
		
		for(Question question: questions){
			questionValues.add(question.getValue());
		}
		
		return questionValues;
	}
		
}
