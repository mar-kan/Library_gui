import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PreviewJFrame extends JFrame {
    private GroupLayout layout;
    ArrayList<Book>books=new ArrayList<>();
    ArrayList<JButton>buttons=new ArrayList<>();
    int num;
    int i;
    private GroupLayout.ParallelGroup parallel;
    private GroupLayout.SequentialGroup sequential;
    public PreviewJFrame(String name) throws HeadlessException {
        this.setBounds(100,100,900,600);
        super.setTitle(name);

        Scanner input = null;
        try {
            input = new Scanner(new File("books.txt"));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        //read all txt db
        while (input.hasNextLine())
        {
            String title,author,isbn,year,genre,field,type;
            type=input.nextLine();
            title=input.nextLine();
            author=input.nextLine();
            isbn=input.nextLine();
            year=input.nextLine();
            genre=input.nextLine();
            if(type.equals("Επιστημονικό")){
                field=input.nextLine();
                ScientificBook temp=new ScientificBook(title,author,Long.parseLong(isbn),Integer.parseInt(year),genre,field);
                books.add(temp);
            }else{
                LiteratureBook temp=new LiteratureBook(title,author,Long.parseLong(isbn),Integer.parseInt(year),genre);
                books.add(temp);
            }
            input.nextLine();
        }
        num=books.size();
        JPanel panel=create();

        //listener for trash icon
        for(int i=0;i<buttons.size();i++){
            int finalI = i;
            buttons.get(i).addActionListener(new ActionListener() { //listener for deletion of a book
                public void actionPerformed(ActionEvent e) {
                   int index= finalI;

                   //remove book from arraylist
                   books.remove(index);

                   //delete db
                    File file = new File("books.txt");
                    file.delete();

                    //rewrite db without that book
                    try {
                        file.createNewFile();

                        for(Book book:books){
                            FileWriter writer = new FileWriter("books.txt", true);
                            if(book instanceof ScientificBook){
                                writer.write("Επιστημονικό"+"\n");
                            }else{
                                writer.write("Λογοτεχνικό"+"\n");
                            }
                            writer.write(book.getTitle()+"\n");
                            writer.write(book.getAuthor()+"\n");
                            writer.write(String.valueOf(book.getISBN())+"\n");
                            writer.write(String.valueOf(book.getYear_published())+"\n");
                            writer.write(book.getGenre()+"\n");
                            if(book instanceof ScientificBook){
                                ScientificBook temp= (ScientificBook) book;
                                writer.write(temp.getScientificField()+"\n");
                            }
                            writer.write("\n");
                            writer.close();
                            PreviewJFrame.super.dispose();
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }
        this.add(panel);

    }

    //creates layout
    private JPanel create() {
        JPanel panel = new JPanel();
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        parallel = layout.createParallelGroup();
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(parallel));
        sequential = layout.createSequentialGroup();
        layout.setVerticalGroup(sequential);
        for (int i = 0; i < books.size(); i++) {
            JLabel label = new JLabel(books.get(i).getTitle()+"");
            JLabel label2 = new JLabel(books.get(i).getAuthor()+"");
            JLabel label3 = new JLabel(books.get(i).getISBN()+"");
            JLabel label4 = new JLabel(books.get(i).getYear_published()+"");
            JLabel label5 = new JLabel(books.get(i).getGenre()+"");
            JLabel label6 ;

            try {
                ScientificBook sbook = (ScientificBook)books.get(i);
                label6 = new JLabel(sbook.getScientificField());
            }
            catch(Exception e){
                label6=new JLabel("");
            }
            ImageIcon icon = new ImageIcon(PreviewJFrame.class.getResource("/trash.png"));
            JButton btn=new JButton();
            label.setLabelFor(btn);
            buttons.add(btn);
            btn.setIcon(icon);
            parallel.addGroup(layout.createSequentialGroup().
                    addComponent(btn).addComponent(label)
            .addComponent(label2)
            .addComponent(label3)
            .addComponent(label4)
            .addComponent(label5)
            .addComponent(label6)
            );
            sequential.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn)
                    .addComponent(label)
                    .addComponent(label2)
                    .addComponent(label3)
                    .addComponent(label4)
                    .addComponent(label5)
                    .addComponent(label6)
            );

        }
        return panel;
    }
}
