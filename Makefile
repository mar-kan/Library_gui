JFLAGS = -g
JC = javac
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
    LibraryMain.java \
    Book.java \
    UpperCaseDocumentFilter.java \
    insertJFrame.java \
    LiteratureBook.java \
    PreviewJFrame.java \
    ScientificBook.java \
    searchJFrame.java \

default: classes

classes: $(CLASSES:.java=.class)

execute:
	java LibraryMain

clean:
	rm -f *.class
