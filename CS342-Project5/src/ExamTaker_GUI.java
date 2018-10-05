/*
 * CS 342 - Term Project Part 5: ExamTaker_GUI.java
 * Name: Allen Breyer III
 * Net ID: abreye2
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

//import ExamTaker_GUI.CustomOutputStream;

import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class ExamTaker_GUI extends JFrame
{
	private JPanel contentPane;
	private JTextField textField, textField2;
	private JTextArea textArea;
	private JButton btnBrowse, btnTakeTest, btnPrevQues, btnNextQues, btnQuit, btnConfirmAns, btnSave;
	
	private File examFile;
	private String filename, filename2, studentName;
	private int questionCounter;
	private Exam exam_Scanner;
	private String studentNameNoSpace;
	protected ArrayList<Answer> answersArray = new ArrayList<Answer>();
	/*
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ExamTaker_GUI frame = new ExamTaker_GUI();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}


	StringBuilder sb = new StringBuilder();
	private JPanel panel;

	/*
	 * CONSTRUCTOR
	 */
	public ExamTaker_GUI()
	{
		initComponents();
		createEvents();
		//redirectSystemStreams();
	}

	/*
	 * Creates and initalizes components
	 */
	private void initComponents()
	{
		setTitle("Exam Taker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane ExamTakerTitle = new JTextPane();
		ExamTakerTitle.setEditable(false);
		ExamTakerTitle.setBounds(326, 11, 145, 40);
		ExamTakerTitle.setBackground(SystemColor.control);
		ExamTakerTitle.setFont(new Font("Serif", Font.BOLD, 26));
		ExamTakerTitle.setText("Exam Taker");
		contentPane.add(ExamTakerTitle);
		
		JTextPane nameText = new JTextPane();
		nameText.setEditable(false);
		nameText.setBackground(SystemColor.control);
		nameText.setText("by Allen Breyer III");
		nameText.setBounds(350, 49, 108, 20);
		contentPane.add(nameText);
		
		btnBrowse = new JButton("Browse...");
		
		
		
		btnBrowse.setBounds(595, 80, 89, 23);
		contentPane.add(btnBrowse);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener()
		{
			
			
			@Override
		    public void changedUpdate(DocumentEvent e)
			{
				filename = textField.getText();
				examFile = new File(filename);
				btnTakeTest.setEnabled(true);
		    }

			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				filename = textField.getText();
				examFile = new File(filename);
				btnTakeTest.setEnabled(true);
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				if (textField.getText().equals(""))
				{
					btnTakeTest.setEnabled(false);
				}
				else
				{
					filename = textField.getText();
					examFile = new File(filename);
				}
			}
		});
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(112, 80, 473, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		textArea = new JTextArea();
		textArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setBounds(1, 1, 604, 165);
		contentPane.add(textArea);
		
		
		//JTextArea textArea1 = new RedirectText(Main.window.textArea1);
		
		btnPrevQues = new JButton("<-");
		
		btnPrevQues.setEnabled(false);
		btnPrevQues.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnPrevQues.setBounds(20, 260, 72, 68);
		contentPane.add(btnPrevQues);
		
		btnNextQues = new JButton("->");
		
		btnNextQues.setEnabled(false);
		btnNextQues.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnNextQues.setBounds(702, 260, 72, 68);
		contentPane.add(btnNextQues);
		
		btnTakeTest = new JButton("Take Test!");
		btnTakeTest.setEnabled(false);
		btnTakeTest.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTakeTest.setBounds(186, 126, 145, 40);
		contentPane.add(btnTakeTest);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(112, 192, 571, 200);
		contentPane.add(scrollPane);
		
		btnQuit = new JButton("Quit");
		
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQuit.setBounds(444, 126, 145, 40);
		contentPane.add(btnQuit);
		
		panel = new JPanel();
		panel.setBounds(112, 403, 414, 157);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField2 = new JTextField();
		textField2.setEnabled(false);
		textField2.setBounds(148, 11, 253, 20);
		panel.add(textField2);
		textField2.setColumns(10);
		
		JLabel lblEnterAnswerHere = new JLabel("Enter answer here:");
		lblEnterAnswerHere.setBounds(10, 14, 128, 14);
		panel.add(lblEnterAnswerHere);
		
		btnConfirmAns = new JButton("Confirm Answer");
		btnConfirmAns.setEnabled(false);
		btnConfirmAns.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmAns.setBounds(536, 403, 148, 40);
		contentPane.add(btnConfirmAns);
		
		btnSave = new JButton("Save");
		
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(536, 520, 148, 40);
		contentPane.add(btnSave);
		
		//PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		//System.setOut(printStream);
		//System.setErr(printStream);
	}
	
	
	public void updateTextArea(final String text)
	{
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        textArea.append(text);
	      }
	    });
	    }

	public void redirectSystemStreams()
	{
	    OutputStream out = new OutputStream() {
	      @Override
	      public void write(int b) throws IOException {
	        updateTextArea(String.valueOf((char) b));
	      }

	      @Override
	      public void write(byte[] b, int off, int len) throws IOException {
	        updateTextArea(new String(b, off, len));
	      }

	      @Override
	      public void write(byte[] b) throws IOException {
	        write(b, 0, b.length);
	      }
	    };

	    System.setOut(new PrintStream(out, true));
	    System.setErr(new PrintStream(out, true));
	 }
	
	/*
	 * Creates all the events
	 */
	private void createEvents()
	{
		btnBrowse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain Text (*.txt)", "txt");
				fileChooser.setFileFilter(filter);

				int returnVal = fileChooser.showOpenDialog(ExamTaker_GUI.this); 

				if (returnVal == JFileChooser.APPROVE_OPTION)
				{

					examFile = fileChooser.getSelectedFile();
					//Displays the name of the selected file on the button.
					textField.setText(fileChooser.getSelectedFile().getPath());
					filename = textField.getText();
					examFile = new File(filename);
					filename2 = examFile.getName();
					
					btnTakeTest.setEnabled(true);
				}
			}
		});
		
		btnTakeTest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				btnTakeTest.setEnabled(false);
				
				//System.out.println("ExamFile: " + examFile);
					
				Scanner fileInput = null;
				try
				{
					if ((examFile == null) || (!examFile.exists()))
					{
						showErrorDialog("Please choose a valid exam file");
						return;
					}
					fileInput = new Scanner(examFile);
					
					studentName = JOptionPane.showInputDialog(null, "Please enter your name:", "Enter Name", JOptionPane.QUESTION_MESSAGE);
					
					if (studentName != null)
					{
						studentNameNoSpace = studentName.replaceAll("\\s+","");
					}
					
					exam_Scanner = new Exam(fileInput);
					//exam_Scanner.redirectSystemStreams(textArea);
					//exam_Scanner.getAnswerFromStudent(-1);
					textField2.setEnabled(true);
					btnConfirmAns.setEnabled(true);
					btnPrevQues.setEnabled(true);
					btnNextQues.setEnabled(true);
					
					questionCounter = 0;
					exam_Scanner.getAnswerFromStudent_GUI(textArea, questionCounter);
				}
				catch (FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/*while(fileInput.hasNext())
				{
					sb.append(fileInput.nextLine());
					sb.append("\n");
				}
				textArea.setText(sb.toString());*/
				//System.out.println("TEST!!!!!!!!!!!!!!!!!!!");
				
				
				
				
			}
		});
		
		btnQuit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int value = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
				if (value == 0)
				{
					System.exit(0);
				}
				
			}
		});
		
		
		btnConfirmAns.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				SAAnswer answer = new SAAnswer(textField2.getText());
				if (questionCounter >= answersArray.size())
				{
					answersArray.add(questionCounter, answer);
				}
				else
				{
					answersArray.set(questionCounter, answer);
				}
				//System.out.println("filename2: " + filename2);
			}
		});
		
		
		
		btnPrevQues.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				questionCounter--;
				btnNextQues.setEnabled(true);
				textField2.setEnabled(true);
				btnConfirmAns.setEnabled(true);
				
				if (questionCounter < 0)
				{
					questionCounter = 0;
					btnPrevQues.setEnabled(false);
				}
				else
				{
					
				}
				
				//textField2.setText(answersArray.get(questionCounter).toString());
				textField2.setText(answersArray.get(questionCounter).toString());
				
				exam_Scanner.getAnswerFromStudent_GUI(textArea, questionCounter);
			}
		});
		
		
		btnNextQues.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				questionCounter++;
				btnPrevQues.setEnabled(true);
				
				if (questionCounter < 0)
				{
					questionCounter = 0;
				}
				else
				{
					
				}
				
				if (questionCounter < answersArray.size())
				{
					textField2.setText(answersArray.get(questionCounter).toString());
				}
				else if (questionCounter == answersArray.size())
				{
					//SAAnswer answer = new SAAnswer(textField2.getText());
					//answersArray.add(questionCounter, answer);
					textField2.setText("");
				}
				else
				{
					SAAnswer answer = new SAAnswer(textField2.getText());
					answersArray.add(questionCounter-1, answer);
					textField2.setText("");
				}
				
				exam_Scanner.getAnswerFromStudent_GUI(textArea, questionCounter);
				
				if (textArea.getText().equals("ERROR: Invalid position index"))
				{
					textArea.setText("---------END-OF-TEST---------\n\nIf you are satisfied with your answers, hit the save button.");
					btnNextQues.setEnabled(false);
					textField2.setEnabled(false);
					btnConfirmAns.setEnabled(false);
					btnSave.setEnabled(true);
				}
			}
		});
		
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				File saveAnswerFile = new File(studentNameNoSpace + "_" + filename2);
				//File saveAnswerFile = new File("bin/answer2.txt");
				PrintWriter pw1 = null;
				try
				{
					pw1 = new PrintWriter(saveAnswerFile);
				}
				catch (FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				pw1.println(studentName);
				pw1.println(filename2);
				exam_Scanner.saveStudentAnswers_GUI(pw1, answersArray);
				pw1.close();
				JOptionPane.showMessageDialog(new JFrame(), "Successfully saved '" + saveAnswerFile.getName() + "'", "Save Confirmation", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	
	//Shows a message "Error" on the pop up dialogue box when gradeExam is selected without selecting any file. 
		private void showErrorDialog(String message){
			JOptionPane.showMessageDialog(new JFrame(), message, "Error",
					JOptionPane.ERROR_MESSAGE);

		} 
		
		
		
}
