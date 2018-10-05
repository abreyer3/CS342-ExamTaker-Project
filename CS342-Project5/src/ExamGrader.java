import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class ExamGrader {

	public void gradeExam(String[] args) throws Exception {
		String examFileName;
		String studFileName;
		

		if(args.length == 0){
			throw new Exception("ERROR: Please provide file names");
		}

		if(args.length == 1){
			//Only student file provided
			studFileName = args[0];

			Scanner studentScanner = new Scanner(new File(studFileName));
			String studentName = studentScanner.nextLine();
			examFileName = studentScanner.nextLine();

			 gradeAndSave(examFileName, studentName, studentScanner);
		}
		else if(args.length == 2){
			examFileName = args[0];
			studFileName = args[1];

			Scanner studentScanner = new Scanner(new File(studFileName));
			String studentName = studentScanner.nextLine();
			String studentExamFileName = studentScanner.nextLine();

			if(!examFileName.equals(studentExamFileName)){
				studentScanner.close();
				throw new Exception("ERROR: Exam file for student does not match the provided exam file argument.");
			}

		gradeAndSave(examFileName, studentName, studentScanner);

		}else {
			throw new Exception("ERROR: Too many arguments. Provide 1 or 2.");
		}

	}
	
	private static void gradeAndSave(String examFileName, String studentName, Scanner studentScanner) throws Exception{
		try{

			Scanner examScanner = new Scanner(new File(examFileName));
			Exam exam = new Exam(examScanner);
			
			gradeExam(exam, studentScanner, studentName);
			
			System.out.println("\nExam Report\n");
			exam.reportQuestionValues();

			//This will also print the exam value
			saveResults(exam, studentName);
		}catch(Exception e){
			throw new Exception("ERROR: The files are incorrectly formatted.");
		}
	}


	private static void saveResults(Exam exam, String studentName) throws FileNotFoundException {
		File studentExamResults = new File("studentExamResults.txt");
		PrintWriter printWriter = new PrintWriter(studentExamResults);

		printWriter.println(studentName);
		printWriter.println(exam.getValue());
		
		ArrayList<Double> questionValues = exam.getAllQuestionValues();

		for(int i=0; i<questionValues.size(); i++){

			if(i == questionValues.size()-1){
				printWriter.print(questionValues.get(i));
			}else{
				printWriter.print(questionValues.get(i) + "," );
			}

		}

		printWriter.close();
	}


	static void gradeExam(Exam exam, Scanner studentScanner, String studentName) throws Exception{

		try {

			String timestamp = studentScanner.nextLine();

			System.out.println("Grading the exam for " + studentName);
			System.out.println("This exam was taken at " + timestamp);

			int answerCount = 0;

			while(studentScanner.hasNextLine() && studentScanner.hasNext(".+")){
				studentScanner.nextLine();

				String answerClassType = studentScanner.nextLine();

				if(answerClassType.equals("SAAnswer") 
						|| answerClassType.equals("NumAnswer") 
						|| answerClassType.equals("MCSAAnswer")){

					String savedStudentAnswer = studentScanner.nextLine();
					savedStudentAnswer = savedStudentAnswer.trim();
					exam.getAnswerFromStudent(answerCount, savedStudentAnswer);

				}else if(answerClassType.equals("MCMAAnswer")){
					int numAnswers = studentScanner.nextInt();
					studentScanner.nextLine();

					for(int i=0; i<numAnswers; i++){
						String savedStudentAnswer = studentScanner.nextLine();
						savedStudentAnswer = savedStudentAnswer.trim();
						exam.getAnswerFromStudent(answerCount, savedStudentAnswer);
					}
				}else{
					throw new Exception("ERROR: The student answer file is incorrectly formatted or contains an unknown answer type.");
				}

				answerCount ++;
			}

		} catch (Exception e) {
			throw new Exception("ERROR: The student answer file is incorrectly formatted.");
		}	
	}

		
}
//-----------------------------------------------------------------------------------------------------------------------------------
//Below is JavaGui.java code
