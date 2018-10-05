import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.OutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;


//************************************************************************************************************************************
public class ExamGrader_GUI extends JFrame {
	
	private ExamGrader examGrader = new ExamGrader();
	private File examFile, studentFile; 
	
	
	public static void main(String[] args) {
		new ExamGrader_GUI();
	}
	
	
//***********************************************************************************************************************************
	public ExamGrader_GUI(){
		
		//Initializing the window to display
		this.setSize(800, 800);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		//Setting the size for the display window
		int xPos = (dim.width/2) - (this.getWidth()/2);
		int yPos = (dim.height/2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);// Sets pop up window to center
		this.setResizable(false);

		//Closing window options
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Prints the text of the title
		this.setTitle("Exam Grader");
		
		setUpNorthPanel();
		setUpSouthPanel();
		setUpCenterPanel();
		
		this.setVisible(true);
	}
	
//************************************************************************************************************************************
	
	private void setUpCenterPanel() {
		
		JPanel centerPanel = new JPanel();
		centerPanel.setFont(new Font("serif", Font.PLAIN, 50));
		JPanel examPanel = setUpFileChooserPanel(centerPanel, "Exam file:", "Choose saved exam file", true);
		JPanel studentPanel = setUpFileChooserPanel(centerPanel, "Saved student answers file:", "Choose saved student answers file", false);
	
		
		centerPanel.setLayout(new BorderLayout());// Increases the size of the bar that contains choose file message
		JPanel northCenterPanel = new JPanel(new BorderLayout());
		northCenterPanel.add(examPanel, BorderLayout.NORTH);
		northCenterPanel.add(studentPanel, BorderLayout.SOUTH);
		northCenterPanel.add(studentPanel, BorderLayout.SOUTH);
		centerPanel.add(BorderLayout.NORTH, northCenterPanel);
		
		
		
		JTextArea textArea = new JTextArea(50, 50); //Initializing textArea
    	centerPanel.add(textArea); // Adding textArea to centerPanel
		
		Color c = new Color(0,0,0,100); //Initializing variable for color
		textArea.setBackground(Color.CYAN); // setting the textArea for color
		
		JScrollPane scrollPane = new JScrollPane(textArea);//Initiating Scroll Pane
		centerPanel.add(scrollPane, BorderLayout.CENTER); //adding Scroll Pane
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		System.setOut(printStream);
		System.setErr(printStream);

		this.getContentPane().add(BorderLayout.CENTER, centerPanel);
	}

//***************************************************************************************************************************************
	private JPanel setUpFileChooserPanel(JPanel centerPanel, String textLabel, String buttonLabel, boolean isExamFile) {
		
		
		JPanel fileChooserPanel = new JPanel();
		fileChooserPanel.setBorder(new EmptyBorder(30, 10, 10, 10));
		fileChooserPanel.setLayout(new BorderLayout());
		fileChooserPanel.setToolTipText("Please select file");//Mouse over message
		centerPanel.setBackground(Color.CYAN);// set background color
		JLabel label = new JLabel(textLabel);
	
		label.setFont(new Font("serif", Font.PLAIN, 18)); 
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		JButton fileButton = new JButton(buttonLabel);
		fileButton.setFont(new Font("serif", Font.BOLD, 20));// Change font size for "choose a saved ExamFil...."
		
		
		//This object calls the associated method
		fileButton.addActionListener(new ActionListener()
		{
			//Anonymous inner class
			public void actionPerformed(ActionEvent e) // Function to check if the action performed is an event or not
			{
				JFileChooser fileChooser = new JFileChooser();

				int returnVal = fileChooser.showOpenDialog(ExamGrader_GUI.this); 

				if (returnVal == JFileChooser.APPROVE_OPTION) { //Condition to check if the correct file is chosen.

					if(isExamFile){
						examFile = fileChooser.getSelectedFile(); //Condition to check if this examFile.

						
					}else{
						studentFile = fileChooser.getSelectedFile(); //Condition to check if this studentFile.
					}


					fileButton.setText(fileChooser.getSelectedFile().getName());//Displays the name of the selected file on the button.
				}
			}
		});
		

		fileChooserPanel.add(BorderLayout.NORTH, label);
		fileChooserPanel.add(BorderLayout.SOUTH, fileButton);
		return fileChooserPanel;	
	}

//************************************************************************************************************************************
	private void setUpSouthPanel() {
		JPanel southPanel = new JPanel();
		JButton gradeButton = new JButton("GRADE EXAM");
		gradeButton.setFont(new Font("Serif", Font.BOLD, 26));

		gradeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {

				if(studentFile == null){
					showErrorDialog("Please choose a student answer file");
					return;
				}

				String[] args = null;

				if(examFile != null && studentFile != null){
					args = new String[2];
					args[0] = examFile.getName(); 
					args[1] = studentFile.getPath();
		
					
				} 

				else if(studentFile != null && examFile == null){
					args = new String[1];
					args[0] = studentFile.getPath(); 
					

				}

				try {
					examGrader.gradeExam(args);
				} catch (Exception e1) {
					String message = "";

					if(e1 instanceof NoSuchElementException){
						message = "ERROR: The files are incorrectly formatted.";
					}else{
						message = e1.getMessage(); // Prints error message from ExamGrader
					}
					showErrorDialog(message); 
				}

			}

		});

		southPanel.add(gradeButton);
		this.getContentPane().add(BorderLayout.SOUTH, southPanel);
		
	}
//************************************************************************************************************************************
	private void setUpNorthPanel() {
		
		//Prints a text in the panel
		JPanel northPanel = new JPanel();
		JLabel titleLabel = new JLabel("Exam Grader");
		titleLabel.setFont(new Font("Serif", Font.BOLD, 26));			
		northPanel.add(titleLabel);// Add text to the panel
		this.getContentPane().add(BorderLayout.NORTH, northPanel);

	}
	
//**************************************************************************************************************************************
	//Shows a message "Error" on the pop up dialogue box when gradeExam is selected without selecting any file. 
	private void showErrorDialog(String message){
		JOptionPane.showMessageDialog(new JFrame(), message, "Error",
				JOptionPane.ERROR_MESSAGE);

	}

	public class CustomOutputStream extends OutputStream {
	private JTextArea textArea;
	

	public CustomOutputStream(JTextArea textArea) {
		this.textArea = textArea;
		Font font = new Font("Times New Roman ", Font.PLAIN, 20); //Initializing script type, font type and size
		textArea.setFont(font); // Initializing variable to set font color
		textArea.setForeground(Color.RED); // Setting font color to Red
		textArea.setText("\n    Please select 2 files above, for only 1 file you must select saveAnswerFile.txt\n\n"); 
		
	}
	@Override
	public void write(int b) {
	// redirects data to the text area
	textArea.append(String.valueOf((char)b));// casting int with char
	textArea.setForeground(Color.BLUE);
	//Font font = new Font("Times New Roman ", Font.BOLD, 25);
	 //scrolls the text area to the end of data. This option can be uncommented to replace scroll option assigned in center Panel for text area
	//textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	}
	
	
}

//*************************************************************************************************************************************
