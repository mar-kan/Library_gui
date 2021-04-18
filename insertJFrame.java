import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;


public class insertJFrame extends JFrame {
    private String[] description = { "Επιστημονικό","Λογοτεχνικό" };
    private String[] genres_lit = {"Μυθιστόρημα", "Νουβέλα", "Διήγημα", "Ποίηση"};
    private String[] genres_sci = {"Περιοδικό", "Βιβλίο", "Πρακτικά Συνεδριών"};

    private String desc="Επιστημονικό";
    private String Genre="Περιοδικό";

    private JPanel contentPane = new JPanel();
    private JTextField title = new JTextField();
    private JTextField author = new JTextField();
    private JTextField ISBN = new JTextField();
    private JTextField year_published = new JTextField();
    private JTextField field = new JTextField();
    private JComboBox genre = new JComboBox();
    private JComboBox type = new JComboBox();
    private JButton insert = new JButton("Εισαγωγή");

    //create frame for insertion
    public insertJFrame(String name) throws HeadlessException, IOException {
        super.setTitle(name);
        this.setBounds(100, 100, 500, 500);

        //set fields for the comboboxes
        type.addItem(description[0]);
        type.addItem(description[1]);

        genre.addItem(genres_sci[0]);
        genre.addItem(genres_sci[1]);
        genre.addItem(genres_sci[2]);

        //capitalize title and author fields
        DocumentFilter filter = new UppercaseDocumentFilter();
        ((AbstractDocument) title.getDocument()).setDocumentFilter(filter);
        ((AbstractDocument) author.getDocument()).setDocumentFilter(filter);

        //create labels for each field
        JLabel title_lab = new JLabel();
        title_lab.setText("Τίτλος");

        JLabel author_lab = new JLabel();
        author_lab.setText("Συγγραφέας");

        JLabel isbn_lab = new JLabel();
        isbn_lab.setText("ISBN");

        JLabel year_lab = new JLabel();
        year_lab.setText("Έτος έκδοσης");

        JLabel gen_lab = new JLabel();
        gen_lab.setText("Είδος");

        JLabel type_lab = new JLabel();
        type_lab.setText("Τύπος");

        JLabel field_lab = new JLabel();
        field_lab.setText("Επιστημονικό πεδίο");

        //create horizontal layout
        GroupLayout groupLayout = new GroupLayout(this.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        //create group of objects
                        .addGroup(groupLayout.createSequentialGroup()
                                //align horizontally and add buttons and text boxes
                                .addGap(185)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                                        .addComponent(type_lab)
                                        .addGap(24)
                                        .addComponent(type)
                                        .addComponent(title_lab)
                                        .addGap(24)
                                        .addComponent(title)
                                        .addComponent(author_lab)
                                        .addGap(24)
                                        .addComponent(author)
                                        .addComponent(isbn_lab)
                                        .addGap(24)
                                        .addComponent(ISBN)
                                        .addComponent(year_lab)
                                        .addGap(24)
                                        .addComponent(year_published)
                                        .addComponent(gen_lab)
                                        .addGap(24)
                                        .addComponent(genre)
                                        .addComponent(field_lab)
                                        .addGap(24)
                                        .addComponent(field)
                                        .addComponent(insert)))
        );

        //create vertical layout
        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        //align vertically and add buttons and textboxes
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(type_lab)
                                .addComponent(type)
                                .addGap(10)
                                .addComponent(title_lab)
                                .addComponent(title)
                                .addGap(10)
                                .addComponent(author_lab)
                                .addComponent(author)
                                .addGap(10)
                                .addComponent(isbn_lab)
                                .addComponent(ISBN)
                                .addGap(10)
                                .addComponent(year_lab)
                                .addComponent(year_published)
                                .addGap(10)
                                .addComponent(gen_lab)
                                .addComponent(genre)
                                .addGap(10)
                                .addComponent(field_lab)
                                .addComponent(field)
                                .addGap(10)
                                .addComponent(insert)
                                .addGap(10))
        );
        this.getContentPane().setLayout(groupLayout);

        //listeners
        type.addItemListener(new ItemListener() {               //changes fields and combo boxes depending on the type of book
            public void itemStateChanged(ItemEvent arg0) {
                desc = type.getSelectedItem().toString();
                if(desc.equals("Λογοτεχνικό")){
                    field_lab.setVisible(false);
                    field.setVisible(false);
                    genre.removeAllItems();
                    genre.addItem(genres_lit[0]);
                    genre.addItem(genres_lit[1]);
                    genre.addItem(genres_lit[2]);
                    genre.addItem(genres_lit[3]);
                }else{
                    field_lab.setVisible(true);
                    field.setVisible(true);
                    genre.removeAllItems();
                    genre.addItem(genres_sci[0]);
                    genre.addItem(genres_sci[1]);
                    genre.addItem(genres_sci[2]);
                }
            }
        });


        insert.addActionListener(new ActionListener() {         //inserts book in 'books.txt' db
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean error=false;
                String Title=title.getText().toString().trim();
                String Author=author.getText().toString().trim();
                String ISBNs=ISBN.getText().toString().trim();
                String Year=year_published.getText().toString().trim();
                Genre = genre.getSelectedItem().toString();

                String Field=null;
                long isbn=0;
                int year=0;

                if(desc.equals("Επιστημονικό")) {
                    Field = field.getText().toString().trim();
                }

                //type checking for year and isbn
                if(Year.length() == 4) {
                     try{
                        year=Integer.parseInt(Year);
                     }
                     catch (Exception e){
                         error = true;
                     }
                }
                else {
                    error = true;
                }
                if (!error)
                {
                    if (ISBNs.length() == 13 && ISBNs.charAt(0) == '9' && ISBNs.charAt(1) == '7' && ( ISBNs.charAt(2) == '9' || ISBNs.charAt(2) == '8'))
                    {
                        try{
                            isbn=Long.parseLong(ISBNs);
                        }
                        catch (Exception e){
                            error = true;
                        }
                    }
                    else{
                        error=true;
                    }
                }
                Book book = null;

                //create book obj
                if(!error){
                    if(desc.equals("Επιστημονικό")){
                        book=new ScientificBook(Title,Author,isbn,year,Genre,Field);
                    }else {
                        book = new LiteratureBook(Title, Author, isbn, year, Genre);
                    }
                        //write book info in file
                        try {
                            FileWriter writer = new FileWriter("books.txt", true);
                            writer.write(desc + "\n");
                            writer.write(book.getTitle() + "\n");
                            writer.write(book.getAuthor() + "\n");
                            writer.write(book.getISBN() + "\n");
                            writer.write(book.getYear_published() + "\n");
                            writer.write(book.getGenre() + "\n");
                            if (desc.equals("Επιστημονικό")) {
                                writer.write((((ScientificBook) book).getScientificField() + "\n"));
                            }
                            writer.write("\n");

                            //close file and frame
                            writer.close();
                            insertJFrame.super.dispose();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        });
    }
}
