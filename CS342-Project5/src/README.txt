Author: Mohammed Shafiuddin
NetId: mshafi2
Class: CS 342, Assignment: Hw 4 - Group Project
Responsible for: ExamGrader, MCAnswer, MCSAQuestion, MCSAAnswer, MCMAQuestion,
                 MCMAAnswer, and ScannerFactory

SUMMARY:
This is a group project I have implemented ExamGrader.java which is the main program.

ExamGrader will read the file input and grade the exams from the file. Two or at
least one file needed as an command lin argument. 

I have included this makefile and README file along with additional files, which
probably not required this is just for testing purpose, such as exam-file.txt,
savedExam.txt, saveAnswerFile.txt. 

Each class has private and public parts to it. Private part is kept secrete and
cannot be accessed by the user. Public class can be accessed by the users.

Each class has constructor, which initializes the variables, if the class does not
have a constructor, then a default constructor is allocated.

Each class has methods like setters and getters, as an example in Answer.java, I have
a set method called setSelected, which sets the value boolean value for the selected
answer, this does not return any value. There is also a gettor method called getValue,
which returns the number of points that this answer contributes to the exam score,
based on whether it is selected and its value when it is selected.

Dynamic memory allocation was allocated, such as for array in MCQuesion class, the
array answerArray is extended by accessing member of structure by implementing .size.
This will accomodate the desired data used.

RUNNING THE PROGRAM IN LINUX OR BASH:

To compile this using a makefile, change directory to the file location of the program,
then by entering the word make at the linux or bert command, this program will compile,
and then program is ready to execute. To execute this program after compiling, type java
ExamGrader filename1.txt filename2.txt. You have to enter file name/s along with the
ExamGrader, because this takes 1 or 2 files as an argument. I have used our class example
files as an argument by entering at command line as follows:
"ExamGrader savedExam.txt saveAnswerFile.txt" or you can input 1 file as an argument also.
If you enter more then 2 or less than 1 file as an argument then program will give error
message, and also if the name of the file in the argument is mis-spelled then also the
program will give an error message. After entering the proper command at command line,
since this is a group project, my part of the program will print the name of the student
followed by the time stamp and reports the grade. 

RUNNING THE PROGRAM ON IDE OR WINDOWS DOS:
In order to run this program on IDE or windows DOS, go to the file location prompt, at
the prompt enter javac ExamGrader.java, this will progress to new command line, at this
prompt enter Java ExamGrader along with aforesaid arguments, this will execute the program
resulting in the aforesaid result.

OUTPUT:

This is a group project, my part of the program executes and the output is that, it prints
student name, prints time stamp, and the prints the graded score.


------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------

Author: Allen Breyer III
NetId: abreye2
Class: CS 342, Assignment: Hw 4 - Group Project
Responsible for: ExamTester, Exam, Question, and MCQuestion

The ExamTaker application is responsible for letting a student take an exam and record their
answers. It asks the student for their name. Next, it asks to load up the exam file template.
Then, it gets answers from the students using that exam template. Students can skip answers
or choose not to answer at all. After all of the questions have been asked, it will give the
student an opportunity to go back to any question if they skipped it or if they wanted to
change their answer. After the student is satisfied with their answers, it will save their
answers to a file: (STUDENTS NAME)_(EXAM TEMPLATE NAME).txt along with the timestamp when
they finished the exam.


------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------

Author: Tarang Patel
NetId: tpatel31
Class: CS 342, Assignment: Hw 4 - Group Project
Responsible for: ExamBuilder, Answer, SAQuestion, SAAnswer, NumQuestion, and NumAnswer


The Exambuilder is an interactive program that allows the user to build an exam from scratch or load an existing exam. Through the menu, you can choose to add specific types questions to the exam and determine their values. One can also remove questions from the exam. The exams questions can be reordered and so can the answers. Once satisfied, you can print the exam to the screen or print it to a file for students to take. Finally you can save the exam in a format that allows other programs to use it. Then you can quit or stay on it forever. The menu will be very straightforward and easy to use.
