import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class searchJFrame extends JFrame {
    JTextField title = new JTextField();
    JTextField author = new JTextField();
    JButton search = new JButton("Αναζήτηση");

    public searchJFrame(String name) throws HeadlessException {
        super.setTitle(name);
        this.setBounds(0, 0, 500, 500);

        //create labels
        JLabel title_lab = new JLabel();
        title_lab.setText("Τίτλος");

        JLabel author_lab = new JLabel();
        author_lab.setText("Συγγραφέας");

        //create horizontal layout
        GroupLayout groupLayout = new GroupLayout(this.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        //create group of buttons
                        .addGroup(groupLayout.createSequentialGroup()
                                //align horizontally and add buttons
                                .addGap(100)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(title_lab)
                                        .addGap(24)
                                        .addComponent(title, 300, 300, 300)
                                        .addComponent(author_lab)
                                        .addGap(24)
                                        .addComponent(author, 300, 300, 300)
                                        .addComponent(search, 300, 300, 300)))
        );

        //create vertical layout
        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        //align vertically and add buttons
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(25)
                                .addComponent(title_lab)
                                .addComponent(title, 40, 40, 40)
                                .addGap(25)
                                .addComponent(author_lab)
                                .addComponent(author, 40, 40, 40)
                                .addGap(40)
                                .addComponent(search, 40, 40, 40)
                                .addGap(25))
        );

        this.getContentPane().setLayout(groupLayout);
        this.setVisible(true);


        //button listener
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String auth=author.getText().toString();
                String bTitle=title.getText().toString();
                String tempauth;
                String temptitle;
                String type;
                String isbn;
                String year;
                String genre;
                String field;

                //array list that stores every result of the search
                ArrayList<Book>books = new ArrayList<>();
                Scanner input = null;
                try {
                    input = new Scanner(new File("books.txt"));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                //read all file db
                while (input.hasNextLine())
                {
                    type=input.nextLine();
                    temptitle=input.nextLine();
                    tempauth=input.nextLine();
                    isbn=input.nextLine();
                    year=input.nextLine();
                    genre=input.nextLine();

                    //add each match to books arraylist
                    if (!bTitle.equals("") && !auth.equals(""))
                    {
                        if(type.equals("Επιστημονικό")){
                            field=input.nextLine();
                            if(auth.equals(tempauth) && bTitle.equals(temptitle))
                            {
                                ScientificBook temp=new ScientificBook(temptitle,tempauth,Long.parseLong(isbn),Integer.parseInt(year),genre,field);
                                books.add(temp);
                            }
                        }else
                        {
                            if(auth.equals(tempauth) && bTitle.equals(temptitle))
                            {
                                LiteratureBook temp=new LiteratureBook(temptitle,tempauth,Long.parseLong(isbn),Integer.parseInt(year),genre);
                                books.add(temp);
                            }
                        }
                    }
                    else if (bTitle.equals(""))
                    {
                        if(type.equals("Επιστημονικό")){
                            field=input.nextLine();
                            if(auth.equals(tempauth))
                            {
                                ScientificBook temp=new ScientificBook(temptitle,tempauth,Long.parseLong(isbn),Integer.parseInt(year),genre,field);
                                books.add(temp);
                            }
                        }else
                        {
                            if(auth.equals(tempauth))
                            {
                                LiteratureBook temp=new LiteratureBook(temptitle,tempauth,Long.parseLong(isbn),Integer.parseInt(year),genre);
                                books.add(temp);
                            }
                        }
                    }
                    else if (auth.equals(""))
                    {
                        if(type.equals("Επιστημονικό")){
                            field=input.nextLine();
                            if(bTitle.equals(temptitle))
                            {
                                ScientificBook temp=new ScientificBook(temptitle,tempauth,Long.parseLong(isbn),Integer.parseInt(year),genre,field);
                                books.add(temp);
                            }
                        }else
                        {
                            if(bTitle.equals(temptitle))
                            {
                                LiteratureBook temp=new LiteratureBook(temptitle,tempauth,Long.parseLong(isbn),Integer.parseInt(year),genre);
                                books.add(temp);
                            }
                        }
                    }
                    input.nextLine();   //skip new line
                }

                //list of searches
                JFrame f= new JFrame();
                f.setBounds(0, 0, 500, 500);
                DefaultListModel<String> l1 = new DefaultListModel<>();

                //add all contents of books arraylist
                for(Book book:books){
                    if(book instanceof ScientificBook){
                        l1.addElement("Επιστημονικό");
                        l1.addElement(book.getTitle());
                        l1.addElement(book.getAuthor());
                        l1.addElement(String.valueOf(book.getISBN()));
                        l1.addElement(String.valueOf(book.getYear_published()));
                        l1.addElement(book.getGenre());
                        l1.addElement(((ScientificBook) book).getScientificField());
                    }else{
                        l1.addElement("Λογοτεχνικό");
                        l1.addElement(book.getTitle());
                        l1.addElement(book.getAuthor());
                        l1.addElement(String.valueOf(book.getISBN()));
                        l1.addElement(String.valueOf(book.getYear_published()));
                        l1.addElement(book.getGenre());
                    }
                    l1.addElement("\n");    //new line between different books
                }
                //create and add list to layout
                JList<String> list = new JList<>(l1);
                list.setBounds(0,0, 500,500);

                JScrollPane scrollableList = new JScrollPane(list);
                f.add(scrollableList);
                f.setVisible(true);
            }

        });
    }
}
