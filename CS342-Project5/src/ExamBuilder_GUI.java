import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class ExamBuilder_GUI extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamBuilder_GUI frame = new ExamBuilder_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	StringBuilder sb = new StringBuilder();
	private ExamBuilder examBuilder = new ExamBuilder();
	Exam exam1= new Exam("Exam 1");
	MCMAQuestion ques1 = new MCMAQuestion("", 0.0, 0.0);
	MCMAAnswer ans1 = new MCMAAnswer("", 0.0);
	MCSAQuestion ques2 = new MCSAQuestion("", 0.0);
	MCSAAnswer ans2 = new MCSAAnswer("", 0.0);
	SAQuestion ques3 = new SAQuestion("", 0.0);
	SAAnswer ans3 = new SAAnswer("");
	NumQuestion ques4 = new NumQuestion("", 0.0);
	NumAnswer ans4 = new NumAnswer("");
	private JTextField textField;
	private JButton btnLoad;
	
	private File examFile;
	private String filename;
	private Scanner exam;
	private JTextField textFieldRemove;
	private JButton btnRemove;
	private JButton btnPrintExam;
	private JButton btnReorderQuestions;
	private JButton btnReorderAnswers;
	private JTextArea console1;
	private JTextField textPrintFile;
	private JButton btnPrintToFile;
	private JTextField textSave;
	private JButton btnSave;
	private JButton btnQuit;
	private JTextField textMCMAAns;
	private JButton btnAddMcmaAnswer;
	private JButton btnMCMAques;
	private JTextField textMCMAQues;
	private JTextField textMCMAMax;
	private JTextField textMCMABase;
	private JTextField textMCMAVal1;
	private JTextField textMCSAQues;
	private JTextField textMCSAVal;
	private JTextField textMCSAAns;
	private JButton btnAddMcsaQuestion;
	private JButton btnMCSAAns;
	private JTextField textMCSAVal1;
	private JTextField textSAQues;
	private JTextField textSAVal;
	private JTextField textSAAns;
	private JButton btnSAQues;
	private JButton btnAddSaAnswer;
	private JTextField textNumQues;
	private JTextField textNumVal;
	private JTextField textNumAns;
	private JButton btnAddNum;
	private JButton btnNewButton;
	
	/**
	 * Create the frame.
	 */
	public ExamBuilder_GUI() 
	{
		initComponents();
		createEvents();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// INIT COMPONENTS
	/////////////////////////////////////////////////////////////////////////////////////////////////
	private void initComponents() 
	{
		
		setTitle("Exam Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 600);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("load", null, panel, null);
		
		JLabel lblExamBuilder = new JLabel("Exam Builder");
		lblExamBuilder.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		btnLoad = new JButton("load");
		
		JLabel lblLoadASaved = new JLabel("Load a saved Exam from a file");
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener()
		{
			
			
			@Override
		    public void changedUpdate(DocumentEvent e)
			{
				filename = textField.getText();
				examFile = new File(filename);
		    }

			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				filename = textField.getText();
				examFile = new File(filename);
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				//System.out.println("TEST: " + textField.getText());
				if (textField.getText().equals(""))
				{
					JOptionPane.showInputDialog(null, "no exam");
				}
				else
				{
					filename = textField.getText();
					examFile = new File(filename);
				}
			}
		});
		
		JLabel lblPrintExamFrom = new JLabel("print exam from printScreen tab");
       




		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(187, Short.MAX_VALUE)
					.addComponent(lblExamBuilder)
					.addGap(176))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnLoad)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLoadASaved)))
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(39)
					.addComponent(lblPrintExamFrom)
					.addContainerGap(274, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblExamBuilder)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoad)
						.addComponent(lblLoadASaved))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPrintExamFrom)
					.addContainerGap(269, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("add1", null, panel_1, null);
		
		JLabel lblAddQuestionsHere = new JLabel("Add questions here: Please fill all question/answer fields before clicking add");
		lblAddQuestionsHere.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblMultipleChoiceMultiple = new JLabel("multiple choice multiple answer question:");
		lblMultipleChoiceMultiple.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textMCMAQues = new JTextField();
		textMCMAQues.setColumns(10);
		
		textMCMAMax = new JTextField();
		textMCMAMax.setColumns(10);
		
		JLabel lblQuestion = new JLabel("question?");
		
		JLabel lblMaxValue = new JLabel("Max value?");
		
		textMCMABase = new JTextField();
		textMCMABase.setColumns(10);
		
		JLabel lblBaseValue = new JLabel("Base value?");
		
		btnMCMAques = new JButton("Add MCMA question");
		
		
		JLabel lblAddQuestionThen = new JLabel("add question, then add answers");
		
		JLabel lblMcmaAnswers = new JLabel("answers? (change ans fields and keep adding for above question)");
		
		textMCMAAns = new JTextField();
		textMCMAAns.setColumns(10);
		
		btnAddMcmaAnswer = new JButton("Add MCMA answer");
		
		
		textMCMAVal1 = new JTextField();
		textMCMAVal1.setColumns(10);
		
		JLabel lblAnsValuefill = new JLabel("Ans value? (fill before adding ans)");
		
		JLabel lblMutiple = new JLabel("multiple choice short answer question:");
		lblMutiple.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textMCSAQues = new JTextField();
		textMCSAQues.setColumns(10);
		
		JLabel lblQuestion_1 = new JLabel("question?");
		
		textMCSAVal = new JTextField();
		textMCSAVal.setColumns(10);
		
		JLabel lblMaxValue_1 = new JLabel("Max value?");
		
		btnAddMcsaQuestion = new JButton("Add MCSA question");
		btnAddMcsaQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel lblAnswers = new JLabel("answers? (change ans fields and keep adding for above question)");
		
		textMCSAAns = new JTextField();
		textMCSAAns.setColumns(10);
		
		btnMCSAAns = new JButton("Add MCSA answer");
		
		
		textMCSAVal1 = new JTextField();
		textMCSAVal1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ans value? (fill before adding ans)");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMultipleChoiceMultiple)
					.addContainerGap(356, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(55, Short.MAX_VALUE)
					.addComponent(lblAddQuestionsHere)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(textMCMAQues, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblQuestion)
					.addContainerGap(117, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textMCMAMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaxValue))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textMCMABase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnMCMAques)
							.addGap(5)
							.addComponent(lblAddQuestionThen))
						.addComponent(lblBaseValue))
					.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textMCMAVal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblAnsValuefill))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textMCMAAns, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddMcmaAnswer))
						.addComponent(lblMcmaAnswers))
					.addContainerGap(54, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMutiple)
					.addContainerGap(396, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(textMCSAQues, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblQuestion_1)
					.addContainerGap(119, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(textMCSAVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMaxValue_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAddMcsaQuestion)
					.addContainerGap(324, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textMCSAVal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textMCSAAns, Alignment.LEADING)
								.addComponent(lblAnswers, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnMCSAAns)))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddQuestionsHere)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMultipleChoiceMultiple)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMCMAQues, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuestion))
					.addGap(2)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMCMAMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textMCMABase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMCMAques)
						.addComponent(lblAddQuestionThen))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaxValue)
						.addComponent(lblBaseValue))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMcmaAnswers)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMCMAAns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddMcmaAnswer))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMCMAVal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAnsValuefill))
					.addGap(61)
					.addComponent(lblMutiple)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMCSAQues, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuestion_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMCSAVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaxValue_1)
						.addComponent(btnAddMcsaQuestion))
					.addGap(18)
					.addComponent(lblAnswers)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMCSAAns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMCSAAns))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMCSAVal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_9 = new JPanel();
		tabbedPane.addTab("add2", null, panel_9, null);
		
		JLabel lblNewLabel_1 = new JLabel("Add questions here: Please fill all question/answer fields before clicking add");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblShortAnswerQuestion = new JLabel("short answer question:");
		lblShortAnswerQuestion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textSAQues = new JTextField();
		textSAQues.setColumns(10);
		
		JLabel lblQuestion_2 = new JLabel("question?");
		
		textSAVal = new JTextField();
		textSAVal.setColumns(10);
		
		JLabel lblMaxValue_2 = new JLabel("Max value?");
		
		btnSAQues = new JButton("Add SA question");
		
		
		JLabel lblAddQuestionThen_1 = new JLabel("add question, then add answer");
		
		JLabel lblAnswer = new JLabel("answer? (add only one per question)");
		
		textSAAns = new JTextField();
		textSAAns.setColumns(10);
		
		btnAddSaAnswer = new JButton("Add SA answer");
		btnAddSaAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("numerical answer question:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textNumQues = new JTextField();
		textNumQues.setColumns(10);
		
		JLabel lblQuestion_3 = new JLabel("question?");
		
		textNumVal = new JTextField();
		textNumVal.setColumns(10);
		
		JLabel lblMaxValue_3 = new JLabel("Max value?");
		
		btnAddNum = new JButton("Add Num Question");
		
		
		JLabel lblAddQuestionThen_2 = new JLabel("add question, then add answer");
		
		JLabel lblAnsweraddOnly = new JLabel("answer? (add only one per question)");
		
		textNumAns = new JTextField();
		textNumAns.setColumns(10);
		
		btnNewButton = new JButton("Add Num answer");
		
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(54)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_9.createSequentialGroup()
									.addComponent(textSAQues, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblQuestion_2))
								.addComponent(lblShortAnswerQuestion)
								.addGroup(gl_panel_9.createSequentialGroup()
									.addComponent(textSAVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(btnSAQues)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblAddQuestionThen_1))
								.addComponent(lblMaxValue_2)
								.addComponent(lblNewLabel_2)
								.addGroup(gl_panel_9.createSequentialGroup()
									.addComponent(textNumQues, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblQuestion_3))
								.addGroup(gl_panel_9.createSequentialGroup()
									.addComponent(textNumVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(39)
									.addComponent(btnAddNum)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblAddQuestionThen_2))
								.addComponent(lblMaxValue_3)))
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(62)
							.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
								.addComponent(textSAAns, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
								.addComponent(lblAnswer)
								.addComponent(btnAddSaAnswer))))
					.addGap(13))
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addComponent(textNumAns, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.addComponent(btnNewButton)
						.addComponent(lblAnsweraddOnly))
					.addContainerGap())
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblShortAnswerQuestion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(textSAQues, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuestion_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(textSAVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSAQues)
						.addComponent(lblAddQuestionThen_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMaxValue_2)
					.addGap(18)
					.addComponent(lblAnswer)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textSAAns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddSaAnswer)
					.addGap(53)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(textNumQues, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuestion_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_9.createSequentialGroup()
							.addComponent(textNumVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMaxValue_3))
						.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAddNum)
							.addComponent(lblAddQuestionThen_2)))
					.addGap(18)
					.addComponent(lblAnsweraddOnly)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textNumAns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel_9.setLayout(gl_panel_9);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("remove", null, panel_2, null);
		
		textFieldRemove = new JTextField();
		textFieldRemove.setColumns(10);
		
		btnRemove = new JButton("remove");
		
		
		JLabel lblRemoveQuestionsFrom = new JLabel("Remove questions from exam");
		lblRemoveQuestionsFrom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblLookAtExam = new JLabel("Look at exam on printScreen tab and choose a specific question to remove");
		
		JLabel lblOrEnter = new JLabel("OR enter -1 to remove all questions");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(129, Short.MAX_VALUE)
					.addComponent(lblRemoveQuestionsFrom)
					.addGap(170))
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGap(31)
					.addComponent(textFieldRemove, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRemove)
					.addContainerGap(486, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOrEnter)
						.addComponent(lblLookAtExam))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRemoveQuestionsFrom)
					.addGap(25)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRemove)
						.addComponent(textFieldRemove, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLookAtExam)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblOrEnter)
					.addContainerGap(273, Short.MAX_VALUE))
		);
        
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("reorderQues", null, panel_3, null);
		
		JLabel lblReorderAllQuestions = new JLabel("Reorder all questions:");
		
		btnReorderQuestions = new JButton("Reorder Questions");
		
		JLabel lblGoToPrintscreen = new JLabel("go to printScreen tab and print the exam to show results");
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGoToPrintscreen)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblReorderAllQuestions)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnReorderQuestions)))
					.addContainerGap(167, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReorderAllQuestions)
						.addComponent(btnReorderQuestions))
					.addGap(18)
					.addComponent(lblGoToPrintscreen)
					.addContainerGap(308, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("reorderAns", null, panel_4, null);
		
		JLabel lblReorderAllAnswers = new JLabel("Reorder all answers:");
		
		btnReorderAnswers = new JButton("Reorder Answers");
		
		JLabel lblGoToPrintscreen_1 = new JLabel("go to printScreen tab and print the exam to show results");
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(67)
							.addComponent(lblReorderAllAnswers)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnReorderAnswers))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(51)
							.addComponent(lblGoToPrintscreen_1)))
					.addContainerGap(122, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReorderAllAnswers)
						.addComponent(btnReorderAnswers))
					.addGap(18)
					.addComponent(lblGoToPrintscreen_1)
					.addContainerGap(302, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("printScreen", null, panel_5, null);
		
		btnPrintExam = new JButton("Print Exam");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPrintExam))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(22)
					.addComponent(btnPrintExam)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		
		console1 = new JTextArea();
		TextAreaOutputStream taos = new TextAreaOutputStream( console1, 60 );
        PrintStream ps = new PrintStream( taos );
        System.setOut( ps );
        System.setErr( ps );
		scrollPane_1.setViewportView(console1);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("printFile", null, panel_6, null);
		
		textPrintFile = new JTextField();
		textPrintFile.setColumns(10);
		
		JLabel lblEnterAFilename = new JLabel("Enter a filename to print the exam to:");
		
		btnPrintToFile = new JButton("Print to File");
		
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPrintToFile)
						.addComponent(lblEnterAFilename)
						.addComponent(textPrintFile, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(26)
					.addComponent(lblEnterAFilename)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textPrintFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnPrintToFile)
					.addContainerGap(293, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("save", null, panel_7, null);
		
		JLabel lblEnterAFilename_1 = new JLabel("Enter a filename to save the exam to a file:");
		
		btnSave = new JButton("Save to File");
		
		textSave = new JTextField();
		textSave.setColumns(10);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addComponent(textSave, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave)
						.addComponent(lblEnterAFilename_1))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGap(25)
					.addComponent(lblEnterAFilename_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textSave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnSave)
					.addContainerGap(302, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("quit", null, panel_8, null);
		
		btnQuit = new JButton("Quit");
		
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGap(49)
					.addComponent(btnQuit)
					.addContainerGap(351, Short.MAX_VALUE))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGap(43)
					.addComponent(btnQuit)
					.addContainerGap(345, Short.MAX_VALUE))
		);
		panel_8.setLayout(gl_panel_8);

	}


	/////////////////////////////////////////////////////////////////////////////////////////////////
	//CREATE EVENTS
	/////////////////////////////////////////////////////////////////////////////////////////////////
	private void createEvents() 
	{
		btnLoad.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) 
			{
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain Text (*.txt)", "txt");
				fileChooser.setFileFilter(filter);

				int returnVal = fileChooser.showOpenDialog(ExamBuilder_GUI.this); 

				if (returnVal == JFileChooser.APPROVE_OPTION)
				{

					examFile = fileChooser.getSelectedFile();
					//Displays the name of the selected file on the button.
					textField.setText(fileChooser.getSelectedFile().getPath());
					filename = textField.getText();
					examFile = new File(filename);
					try {
						exam = new Scanner(examFile);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//create exams with details about each exam
					exam1= new Exam(exam);
					JOptionPane.showMessageDialog(null, "Done!");
				}
			}
		});
		
		btnMCMAques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ques = textMCMAQues.getText();
				String val = textMCMAMax.getText();
				String base = textMCMABase.getText();
				ques1 = new MCMAQuestion(ques, Double.parseDouble(val), Double.parseDouble(base));
				exam1.addQuestion(ques1);
			}
		});
		
		btnAddMcmaAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Answer = textMCMAAns.getText();
				String val1 = textMCMAVal1.getText();
				ans1 = new MCMAAnswer(Answer, Double.parseDouble(val1));
				ques1.addAnswer(ans1);
			}
		});
		
		btnAddMcsaQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ques = textMCSAQues.getText();
				String val = textMCSAVal.getText();
				ques2 = new MCSAQuestion(ques, Double.parseDouble(val));
				exam1.addQuestion(ques2);
			}
		});
		btnMCSAAns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Answer = textMCSAAns.getText();
				String val1 = textMCSAVal1.getText();
				ans2 = new MCSAAnswer(Answer, Double.parseDouble(val1));
				ques2.addAnswer(ans2);
			}
		});
		
		btnSAQues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ques = textSAQues.getText();
				String val = textSAVal.getText();
				ques3 = new SAQuestion(ques, Double.parseDouble(val));
				exam1.addQuestion(ques3);
			}
		});
		btnAddSaAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Answer = textSAAns.getText();
				ans3 = new SAAnswer(Answer);
				ques3.setRightAnswer(ans3);
			}
		});
		
		btnAddNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ques = textNumQues.getText();
				String val = textNumVal.getText();
				ques4 = new NumQuestion(ques, Double.parseDouble(val));
				exam1.addQuestion(ques4);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Answer = textNumAns.getText();
				ans4 = new NumAnswer(Answer);
				ques4.setRightAnswer(ans4);
			}
		});
		
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String remove = textFieldRemove.getText();
				exam1.remove(Integer.parseInt(remove)-1);
				JOptionPane.showMessageDialog(null, "Done!");
			}
		});
		
		btnReorderQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exam1.reorderQuestions();
				JOptionPane.showMessageDialog(null, "Done!");
			}
		});
		
		btnReorderAnswers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exam1.reorderMCAnswers(-1);
				JOptionPane.showMessageDialog(null, "Done!");
			}
		});
		
		
		
		btnPrintExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("---------------------------------------------");
				exam1.print();
			}
		});
		
		btnPrintToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textPrintFile.getText();
				File savedFileExam = new File(name);
				PrintWriter write = null;
				try {
					write = new PrintWriter(savedFileExam);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				exam1.printToFile(write);
				write.close();
				JOptionPane.showMessageDialog(null, "Done!");
			}
			
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textSave.getText();
				File savedFileExam = new File(name);
				PrintWriter write = null;
				try {
					write = new PrintWriter(savedFileExam);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				exam1.save(write);
				write.close();
				JOptionPane.showMessageDialog(null, "Done!");
			}
		});
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
				if (value == 0)
				{
					System.exit(0);
				}
			}
		});
		
	}
}
