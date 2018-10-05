JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java
		
CLASSES = \
		MCSAAnswer.java \
		MCMAAnswer.java \
		MCAnswer.java \
		SAAnswer.java \
		NumAnswer.java \
		Answer.java \
		MCSAQuestion.java \
		MCMAQuestion.java \
		MCQuestion.java \
		SAQuestion.java \
		NumQuestion.java \
		Question.java \
		Exam.java \
		ScannerFactory.java \
		ExamBuilder.java \
		ExamTaker.java \
		ExamGrader.java \
		ExamTester.java \
        TestRedirect.java \
		TextAreaOutputStream.java \
		ExamBuilder_GUI.java \
		ExamTaker_GUI.java \
		ExamGrader_GUI.java		

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
